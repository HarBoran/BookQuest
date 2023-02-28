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
@Table(name = "wishlist")
public class Wishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wishlist_id")
	private Integer wishlistId;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@Column(name = "wishlist_quantity", nullable = false)
	private Integer wishlistQuantity;

//		   PRIMARY KEY (`wishlist_id`),
//		   INDEX `a8_idx` (`book_id` ASC) VISIBLE,
//		   INDEX `a9_idx` (`user_id` ASC) VISIBLE,
//		   CONSTRAINT `a8`
//		     FOREIGN KEY (`book_id`)
//		     REFERENCES `bookquest`.`books` (`book_id`)
//		     ON DELETE NO ACTION
//		     ON UPDATE NO ACTION,
//		   CONSTRAINT `a9`
//		     FOREIGN KEY (`user_id`)
//		     REFERENCES `bookquest`.`users` (`user_id`)
//		     ON DELETE NO ACTION
//		     ON UPDATE NO ACTION);

}