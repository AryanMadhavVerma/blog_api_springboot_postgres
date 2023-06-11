package com.blog_api.blog_api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog_api.blog_api.entities.BlogPost;
import com.blog_api.blog_api.entities.User;

@Repository
public interface BlogPostDAO extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findByUser(User user);
}
