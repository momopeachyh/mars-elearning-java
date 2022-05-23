package com.training.mars.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.mars.elearning.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}



