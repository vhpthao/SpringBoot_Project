package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Author;
import com.example.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService{
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public List<Author> getAllAuthor() {
		return authorRepository.findAll();
	}

	@Override
	public void addOneAuthor(Author author) {
		authorRepository.save(author);
		
	}

	@Override
	public void updateOneAuthor(Integer id, Author author) {
		Author myAuthor = authorRepository.findById(id).orElse(null);
		if(myAuthor !=null)
			authorRepository.save(author);
	}

	@Override
	public void deleteOneAuthor(int authorId) {
		authorRepository.deleteById(authorId);
		
	}

	@Override
	public Author findOneAuthor(int authorId) {
		return authorRepository.findById(authorId).orElse(null);
	}
	
	

}
