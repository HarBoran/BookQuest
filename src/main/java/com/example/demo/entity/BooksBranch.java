package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="books_branch")
public class BooksBranch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "books_branch_id")
	private Integer booksBranchId;
	
	@ManyToOne
	@JoinColumn(name ="branchs_id")
	private Branchs branchs;
   
	@ManyToOne
	@JoinColumn(name ="book_id")
	private Book book;
   
	@Column(nullable = false) 
	private String status;	 //condition;
   
	@Column(nullable = false)
	private Integer quantity;

	public BooksBranch(){}

	@Override
	public String toString() {
		return "BooksBranch [booksBranchId=" + booksBranchId + ", branch=" + branchs + ", book=" + book + ", status="
				+ status + ", quantity=" + quantity + "]";
	}

	public Integer getBooksBranchId() {
		return booksBranchId;
	}

	public void setBooksBranchId(Integer booksBranchId) {
		this.booksBranchId = booksBranchId;
	}

	public Branchs getBranch() {
		return branchs;
	}

	public void setBranch(Branchs branchs) {
		this.branchs = branchs;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}	
	
	
	
	
	
	
	
}
