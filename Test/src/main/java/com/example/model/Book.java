package com.example.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="image")
	private String image;
	@Column(name="book_name")
	private String bookName;
	@Column(name="price")
	private int price;
	@Column(name="description")
	private String description;
	
	@ManyToOne(optional = true, cascade = CascadeType.MERGE)
	private Category categories;
	
	@ManyToOne(optional = true, cascade = CascadeType.MERGE)
	private Author authors;
}
