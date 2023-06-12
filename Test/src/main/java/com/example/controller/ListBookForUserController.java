package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Author;
import com.example.model.Book;
import com.example.model.Category;
import com.example.service.AuthorService;
import com.example.service.BookService;
import com.example.service.CategoryService;

@Controller
public class ListBookForUserController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AuthorService authorService;
	
	
	@GetMapping("/booklist")
	public String bookList(Model model) {
		List<Book> bookList = bookService.getAllBook();
		List<Category> categories = categoryService.getAllCategory();
		List<Author> authors = authorService.getAllAuthor();
		model.addAttribute("bookList", bookList);
		model.addAttribute("categories", categories);
		model.addAttribute("authors", authors);
		return "booklist";
	}
	
	@PostMapping("/booklist")
	public String listBookByCate(Model model, @RequestParam("cateId") Integer cateId) {
		List<Book> bookList = bookService.getAllBookByCateId(cateId);
		List<Category> categoriesList = categoryService.getAllCategory();
		model.addAttribute("bookList", bookList);
		model.addAttribute("categories", categoriesList);
		model.addAttribute("cateId", cateId);
		return "booklist";
	}

}
