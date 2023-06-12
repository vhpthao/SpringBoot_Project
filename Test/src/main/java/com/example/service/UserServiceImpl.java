package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	@Override
	public void signup(User user) {
		user.setRole("ROLE_GENERAL");
		// password encryption
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		userRepository.save(user);
		
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public void addOneUser(User user) {
		userRepository.save(user);
		
	}

	@Override
	public void updateOneUser(Integer id,User user) {
		User myUser = userRepository.findById(id).orElse(null);
		if(myUser != null) {
			userRepository.save(user);
		}
	}

	@Override
	public void deleteOneUser(Integer userId) {
		userRepository.deleteById(userId);
		
	}

	@Override
	public User findOneUser(Integer userId) {
		return userRepository.findById(userId).orElse(null);
	}

	
	
}
