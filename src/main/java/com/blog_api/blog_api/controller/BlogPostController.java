package com.blog_api.blog_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.blog_api.blog_api.services.BlogPostService;
import com.blog_api.blog_api.entities.BlogPost;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogPostController {
    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PostMapping("/blog")
    public ResponseEntity<BlogPost> createBlogPost(@RequestParam("username") String username, @RequestBody BlogPost blogPost) {
        BlogPost createdBlogPost = blogPostService.createBlogPost(username, blogPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBlogPost);
    }

    @PutMapping("/blog/{id}")
    public ResponseEntity<BlogPost> editBlogPost(@PathVariable("id") Long id, @RequestBody BlogPost updatedBlogPost) {
        BlogPost editedBlogPost = blogPostService.editBlogPost(id, updatedBlogPost);
        if (editedBlogPost == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editedBlogPost);
    }

    @DeleteMapping("/blog/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable("id") Long id) {
        boolean deleted = blogPostService.deleteBlogPost(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<BlogPost> getBlogPost(@PathVariable("id") Long id) {
        BlogPost blogPost = blogPostService.getBlogPost(id);
        if (blogPost == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(blogPost);
    }

    @GetMapping("/blogs")
    public ResponseEntity<List<BlogPost>> getAllBlogPosts(@RequestParam("username") String username) {
        List<BlogPost> blogPosts = blogPostService.getAllBlogPosts(username);
        return ResponseEntity.ok(blogPosts);
    }
}