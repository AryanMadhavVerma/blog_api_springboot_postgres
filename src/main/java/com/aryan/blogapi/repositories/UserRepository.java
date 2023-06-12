package com.aryan.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aryan.blogapi.entities.User;


//to interface with JPA and operate on Postgres tablles
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
