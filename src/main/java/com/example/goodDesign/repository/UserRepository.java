package com.example.goodDesign.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.goodDesign.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

         // Example custom query method
	boolean existsByName(String name);
}



