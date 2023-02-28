package com.example.demo.entity;

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
@Table(name="used_book")
public class UsedBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="used_book_id")
	private Integer usedBookId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@Column(nullable = false) 
	private String status;
	
	@Column(nullable = false)
	private Integer amount;
	
	public UsedBook(){}
}
