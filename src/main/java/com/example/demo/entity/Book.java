package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name="books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_id")
	private Integer bookId;
	
	@Column(length = 128, nullable = false)
	private String title;
	//작가
	@Column(length = 32, nullable = false)
	private String author; 
	//출판사
	@Column(length = 32, nullable = false)
	private String publisher; 
	//출판일
	@Column(name = "publication_date", nullable = false)
	private LocalDate publicationDate; 
	//가격
	@Column(nullable = false)
	private Integer price; 
	
	@Column(length = 128, nullable = false)
	private String image;
	//책소개
	@Column(nullable = false)
	private String description; 
	//한국십진분류표
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	public Book(){}	


}
