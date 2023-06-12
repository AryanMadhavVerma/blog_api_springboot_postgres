package com.aryan.blogapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.aryan.blogapi.repositories.BlogPostRepository;
import com.aryan.blogapi.repositories.UserRepository;
import com.aryan.blogapi.entities.User;
import com.aryan.blogapi.entities.BlogPost;


@Component
public class TestDataInitializer implements CommandLineRunner {

    private final BlogPostRepository blogPostRepository;
    private final UserRepository userRepository;

    @Autowired
    public TestDataInitializer(BlogPostRepository blogPostRepository, UserRepository userRepository) {
        this.blogPostRepository = blogPostRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create and save test users
        User user1 = new User(1, "Rohan");
        User user2 = new User(2, "Kartik");
        userRepository.save(user1);
        userRepository.save(user2);

        // Create and save test blog posts
        BlogPost blogPost1 = new BlogPost(1, "Title of post", "Lorem ipsum dolor sit amet.", user1);
        BlogPost blogPost2 = new BlogPost(2, "Title of kartik's post", "Lorem ipsum dolor sit amet.", user2);
        blogPostRepository.save(blogPost1);
        blogPostRepository.save(blogPost2);
    }
}

