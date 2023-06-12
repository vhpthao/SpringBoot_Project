package com.example.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="date_create")
	private LocalDate dateCreate;
	
	@ManyToOne(optional = true)
	@JoinColumn(name="book_id")
	private Book books;
//	
	@ManyToOne(optional = true)
	@JoinColumn(name = "user_id")
	private User users;

}
