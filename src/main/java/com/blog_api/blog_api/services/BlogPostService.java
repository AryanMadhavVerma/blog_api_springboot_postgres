package com.blog_api.blog_api.services;



import java.util.List;

import org.springframework.stereotype.Service;

import com.blog_api.blog_api.dao.BlogPostDAO;
import com.blog_api.blog_api.dao.UserDAO;
import com.blog_api.blog_api.entities.BlogPost;
import com.blog_api.blog_api.entities.User;

@Service
public class BlogPostService {


    private final BlogPostDAO blogPostDAO;
    private final UserDAO userDAO;

    //setting an instance of the DAO interfaces to interact with JPA

    public BlogPostService(BlogPostDAO blogPostDAO, UserDAO userDAO) {
        this.blogPostDAO = blogPostDAO;
        this.userDAO = userDAO;
    }

    public BlogPost createBlogPost(String username, BlogPost blogPost) {
        User user = userDAO.findByUsername(username);

        if(user == null) {
            return null;
        }
        blogPost.setUser(user);
        return blogPostDAO.saveAndFlush(blogPost);
    }

    public BlogPost editBlogPost(Long id, BlogPost updatedBlogPost) {
        BlogPost blogPost = null;
        for (BlogPost post : blogPostDAO.findAll()) {
            if(post.getId() == id) {
              blogPost = post;   
            }
        }
        if(blogPost==null) {
            return null;
        }

        return blogPostDAO.saveAndFlush(blogPost);
    }

    public boolean deleteBlogPost(Long id) {
        if(blogPostDAO.existsById(id)) {
            blogPostDAO.deleteById(id);
            return true;
        }
        return false;
    }
    public BlogPost getBlogPost(Long id) {
        return blogPostDAO.findById(id).orElse(null);
    }

    public List<BlogPost> getAllBlogPosts(String username) {
        User user = userDAO.findByUsername(username);
        return blogPostDAO.findByUser(user);
        
    }
    

}
