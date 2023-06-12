package com.example.service;

import java.util.List;

import com.example.model.Category;

public interface CategoryService {
	public List<Category> getAllCategory();
	
	public void addOneCategory(Category category);
	
	public void updateOneCategory(Integer id, Category category);
	
	public void deleteOneCategory(int categoryId);
	
	public Category findOneCategory(int categoryId);
}
