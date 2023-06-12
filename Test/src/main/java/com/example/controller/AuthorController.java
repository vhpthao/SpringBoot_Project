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

import com.example.model.Author;
import com.example.service.AuthorService;

@Controller
@RequestMapping("/author")
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/list")
	public String listAuthor(Model model) {
		List<Author> authorList = authorService.getAllAuthor();
		model.addAttribute("authorList", authorList);
		return "author/list";
	}
	
	@GetMapping("/add")
	public String addAuthor(Model model) {
		Author author = new Author();
		model.addAttribute("author", author);
		return "author/add";
	}
	
	@PostMapping("/save")
	public String saveAuthor(@ModelAttribute("author") Author author) {
		authorService.addOneAuthor(author);
		return "redirect:/author/list";
	}
	

	@GetMapping("/list/delete/{id}")
	public String deleteAuthor(@PathVariable("id") int id) {
		authorService.deleteOneAuthor(id);
		return "redirect:/author/list";
	}
	
	@GetMapping("/list/edit/{id}")
	public String editBook(@PathVariable("id") int id, Model model)
	{
		Author author = authorService.findOneAuthor(id);
		model.addAttribute("author", author);
		return "author/update";
	}
	
	@PostMapping("/list/update")
	public String updateAuthor(int id, @ModelAttribute("author") Author author)
	{
		authorService.updateOneAuthor(id, author);
		return "redirect:/book/list";
	}

}
