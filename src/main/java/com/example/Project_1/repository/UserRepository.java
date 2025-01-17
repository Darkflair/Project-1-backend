package com.example.Project_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Project_1.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    User findByUsername(String username);
}