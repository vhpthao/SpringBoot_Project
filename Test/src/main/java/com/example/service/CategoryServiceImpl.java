package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Category;
import com.example.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public void addOneCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void updateOneCategory(Integer id, Category category) {
		Category myCategory = categoryRepository.findById(id).orElse(null);
		if(myCategory !=null)
			categoryRepository.save(category);
		
	}

	@Override
	public void deleteOneCategory(int categoryId) {
		categoryRepository.deleteById(categoryId);
		
	}

	@Override
	public Category findOneCategory(int categoryId) {
		return categoryRepository.findById(categoryId).orElse(null);
	}

}
