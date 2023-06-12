package com.example.service;

import java.util.List;

import com.example.model.User;

public interface UserService {
	public void signup(User user);
	 
	public List<User> getAllUser();
	
	public void addOneUser(User user);
	
	public void updateOneUser(Integer id,User user);
	
	public void deleteOneUser(Integer userId);
	
	public User findOneUser(Integer userId);
	
	
}
