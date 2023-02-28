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
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
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

//	  PRIMARY KEY (`books_branch_id`),
//	  INDEX `a12_idx` (`branch_id` ASC) VISIBLE,
//	  INDEX `a13_idx` (`book_id` ASC) VISIBLE,
//	  CONSTRAINT `a12`
//	    FOREIGN KEY (`branch_id`)
//	    REFERENCES `bookquest`.`branch` (`branch_id`)
//	    ON DELETE NO ACTION
//	    ON UPDATE NO ACTION,
//	  CONSTRAINT `a31`
//	    FOREIGN KEY (`book_id`)
//	    REFERENCES `bookquest`.`books` (`book_id`)
//	    ON DELETE NO ACTION
//	    ON UPDATE NO ACTION);


}
