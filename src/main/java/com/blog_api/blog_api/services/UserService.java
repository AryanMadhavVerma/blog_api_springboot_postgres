package com.blog_api.blog_api.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.blog_api.blog_api.dao.UserDAO;

import com.blog_api.blog_api.entities.User;

@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User createUser(User user) {
        return userDAO.save(user);
    }

    public List<User> getAllUsers() {
        List<User> userList = userDAO.findAll();
        return userList;
        
    }
}