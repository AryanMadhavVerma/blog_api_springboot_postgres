package com.aryan.blogapi.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aryan.blogapi.repositories.UserRepository;
import com.aryan.blogapi.entities.BlogPost;
import com.aryan.blogapi.repositories.BlogPostRepository;
import com.aryan.blogapi.entities.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blog-posts")
public class BlogPostController {

    private final BlogPostRepository blogPostRepository;
    private final UserRepository userRepository;

    @Autowired
    public BlogPostController(BlogPostRepository blogPostRepository, UserRepository userRepository) {
        this.blogPostRepository = blogPostRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<BlogPost>> getAllBlogPosts() {
        List<BlogPost> blogPosts = blogPostRepository.findAll();
        return ResponseEntity.ok(blogPosts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Long id) {
        Optional<BlogPost> optionalBlogPost = blogPostRepository.findById(id);
        if (optionalBlogPost.isPresent()) {
            return ResponseEntity.ok(optionalBlogPost.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BlogPost> createBlogPost(@RequestBody BlogPost receivedBlogPost) {
        Optional<User> user = userRepository.findById(receivedBlogPost.getUser().getId());
        if (user.isPresent()) {
            User blogUser = user.get();
            BlogPost blogPost = new BlogPost();
            blogPost.setTitle(receivedBlogPost.getTitle());
            blogPost.setContent(receivedBlogPost.getContent());
            blogPost.setUser(blogUser);
            BlogPost createdBlogPost = blogPostRepository.save(blogPost);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBlogPost);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable Long id, @RequestBody BlogPost updatedBlogPostRequest) {
        Optional<BlogPost> tempBlogPost = blogPostRepository.findById(id);
        if (tempBlogPost.isPresent()) {
            Optional<User> tempUser = userRepository.findById(updatedBlogPostRequest.getUser().getId());
            if (tempUser.isPresent()) {
                User user = tempUser.get();
                BlogPost blogPost = tempBlogPost.get();
                blogPost.setTitle(updatedBlogPostRequest.getTitle());
                blogPost.setContent(updatedBlogPostRequest.getContent());
                blogPost.setUser(user);
                BlogPost savedBlogPost = blogPostRepository.save(blogPost);
                return ResponseEntity.ok(savedBlogPost);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Long id) {
        Optional<BlogPost> optionalBlogPost = blogPostRepository.findById(id);
        if (optionalBlogPost.isPresent()) {
            blogPostRepository.delete(optionalBlogPost.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

