package com.example.service;

import java.util.List;

import com.example.model.Author;

public interface AuthorService {
	public List<Author> getAllAuthor();
	
	public void addOneAuthor(Author author);
	
	public void updateOneAuthor(Integer id, Author author);
	
	public void deleteOneAuthor(int authorId);
	
	public Author findOneAuthor(int auhtorId);
	

}
