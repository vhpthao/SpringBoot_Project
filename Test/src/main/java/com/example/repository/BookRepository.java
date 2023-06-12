package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

	@Query("select b from Book b where b.categories.id = :cateId")
	public List<Book> getAllBookByCateId(@Param("cateId") Integer cateId);

}
