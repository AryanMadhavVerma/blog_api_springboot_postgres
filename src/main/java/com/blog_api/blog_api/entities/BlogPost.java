package com.blog_api.blog_api.entities;

import javax.persistence.*;

@Entity
@Table(name = "blog_post")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    //we define a relation between the user and blog posts. A single author can have multiple 
    //blog posts, but a single blog post has just one author.
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public BlogPost() {
        super();
    }

    public BlogPost(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    


}
