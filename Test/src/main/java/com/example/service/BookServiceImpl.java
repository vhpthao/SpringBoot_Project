package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Book;
import com.example.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> getAllBook() {
		return bookRepository.findAll();
	}

	@Override
	public void addOneBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public void updateOneBook(Integer id, Book book) {
		Book myBook = bookRepository.findById(id).orElse(null);
		if(myBook !=null)
			bookRepository.save(book);
	}

	@Override
	public void deleteOneBook(Integer bookId) {
		bookRepository.deleteById(bookId);
	}

	@Override
	public Book findOneBook(Integer bookId) {
		return  bookRepository.findById(bookId).orElse(null);
	}

	@Override
	public List<Book> getAllBookByCateId(Integer cateId) {
		return bookRepository.getAllBookByCateId(cateId);
	}

}
