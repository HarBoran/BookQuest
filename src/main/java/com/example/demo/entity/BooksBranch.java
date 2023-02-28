package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name="books_branch")
public class BooksBranch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "books_branch_id")
	private Integer booksBranchId;
	
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private Branch branch;
   
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
   
	@Column(nullable = false) 
	private String status;	 //condition;
   
	@Column(nullable = false)
	private Integer quantity;

	public BooksBranch(){}	
}
