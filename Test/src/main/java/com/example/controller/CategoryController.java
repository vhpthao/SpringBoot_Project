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

import com.example.model.Category;
import com.example.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/list")
	public String listCategory(Model model) {
		List<Category> categoryList = categoryService.getAllCategory();
		model.addAttribute("categoryList", categoryList);
		return "category/list";
	}
	
	@GetMapping("/add")
	public String addCategory(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "category/add";
	}
	
	@PostMapping("/save")
	public String saveCategory(@ModelAttribute("category") Category category) {
		categoryService.addOneCategory(category);
		return "redirect:/category/list";
	}
	

	@GetMapping("/list/delete/{id}")
	public String deleteCategory(@PathVariable("id") int id) {
		categoryService.deleteOneCategory(id);
		return "redirect:/category/list";
	}
	
	@GetMapping("/list/edit/{id}")
	public String editCategory(@PathVariable("id") int id, Model model)
	{
		Category category = categoryService.findOneCategory(id);
		model.addAttribute("category", category);
		return "category/update";
	}
	
	@PostMapping("/list/update")
	public String updateCategory(int id, @ModelAttribute("category") Category category)
	{
		categoryService.updateOneCategory(id, category);
		return "redirect:/book/list";
	}
}
