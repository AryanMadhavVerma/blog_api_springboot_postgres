package com.blog_api.blog_api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog_api.blog_api.entities.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
