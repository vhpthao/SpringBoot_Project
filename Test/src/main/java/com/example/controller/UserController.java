package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.User;
import com.example.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public String listUser(Model model) {
		List<User> userList = userService.getAllUser();
		model.addAttribute("userList", userList);
		return "user/list";
	}
	
	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User user){
		userService.addOneUser(user);
		return "redirect:/user/list";
	}
	
	@GetMapping("/list/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		userService.deleteOneUser(id);
		return "redirect:/user/list";
	}
	
	@GetMapping("/list/edit/{id}")
	public String editUser(@PathVariable("id") int id, Model model) {
		User user = userService.findOneUser(id);
		model.addAttribute("user", user);
		return "user/update";
	}

	@PostMapping("/list/update")
	public String updateUser(int id, @ModelAttribute("user") User user) {
		userService.updateOneUser(id, user);
		return "redirect:/user/list";
	}
	
}
