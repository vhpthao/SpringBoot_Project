package com.example.service;

import java.util.List;

import com.example.model.Book;

public interface BookService {

	public List<Book> getAllBook();
	
	public List<Book> getAllBookByCateId(Integer cateId);

	public void addOneBook(Book book);

	public void updateOneBook(Integer id, Book book);

	public void deleteOneBook(Integer bookId);

	public Book findOneBook(Integer bookId);

}
