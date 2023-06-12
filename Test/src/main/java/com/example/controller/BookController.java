package com.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Author;
import com.example.model.Book;
import com.example.model.Category;
import com.example.repository.BookRepository;
import com.example.service.AuthorService;
import com.example.service.BookService;
import com.example.service.CategoryService;

@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AuthorService authorService;


	private static final String UPLOAD_DIR = "src/main/resources/static/images/";

	@GetMapping("/list")
	public String listBook(Model model) {
		List<Book> bookList = bookService.getAllBook();
		List<Category> categories = categoryService.getAllCategory();
		List<Author> authors = authorService.getAllAuthor();
		model.addAttribute("bookList", bookList);
		model.addAttribute("categories", categories);
		model.addAttribute("authors", authors);
		return "book/list";
	}

	@PostMapping("/list")
	public String listBookByCate(Model model, @RequestParam("cateId") Integer cateId) {
		List<Book> bookList = bookService.getAllBookByCateId(cateId);
		List<Category> categoriesList = categoryService.getAllCategory();
		model.addAttribute("bookList", bookList);
		model.addAttribute("categories", categoriesList);
		model.addAttribute("cateId", cateId);
		return "book/list";
	}

	@GetMapping("/add")
	public String addBook(Model model) {
		Book book = new Book();
		List<Category> categoriesList = categoryService.getAllCategory();
		List<Author> authorList = authorService.getAllAuthor();
		model.addAttribute("categories", categoriesList);
		model.addAttribute("authors", authorList);
		model.addAttribute("book", book);
		return "book/add";
	}

	@PostMapping("/save")
	public String saveBook(@ModelAttribute("book") Book book, @RequestParam("imageFile") MultipartFile imageFile)
		throws IOException {
		if (!imageFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
			Path path = Paths.get(UPLOAD_DIR + fileName);
			Files.copy(imageFile.getInputStream(), path);
			book.setImage(fileName);
		}
		bookService.addOneBook(book);
		return "redirect:/book/list";
	}

	@GetMapping("/list/delete/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		bookService.deleteOneBook(id);
		return "redirect:/book/list";
	}

	@GetMapping("/list/edit/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
		Book book = bookService.findOneBook(id);
		List<Category> categoriesList = categoryService.getAllCategory();
		List<Author> authorList = authorService.getAllAuthor();
		model.addAttribute("categories", categoriesList);
		model.addAttribute("authors", authorList);
		model.addAttribute("book", book);
		return "book/update";
	}

	@PostMapping("/list/update")
	public String updateBook(int id, @ModelAttribute("book") Book book) {
		bookService.updateOneBook(id, book);
		return "redirect:/book/list";
	}

}
