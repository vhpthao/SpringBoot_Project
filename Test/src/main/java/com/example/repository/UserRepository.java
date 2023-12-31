package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where u.userEmail = :userEmail")
	public User findLoginUser(String userEmail);

	@Query("select u from User u where u.password = :encryptionPassword")
	public User saveAll(String encryptionPassword);
}
