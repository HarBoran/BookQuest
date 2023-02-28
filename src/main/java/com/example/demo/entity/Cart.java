package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="carts")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cart_id")
	private Integer cartId;
	
	@Column(name="user_id", nullable = false)
	private Integer userId;
	
	@Column(name="book_id", nullable = false)
	private Integer bookId;
	
	@Column(name="cart_quantity", nullable = false)
	private Integer cartQuantity;
	
//	  PRIMARY KEY (`cart_id`),
//	  INDEX `a6_idx` (`book_id` ASC) VISIBLE,
//	  INDEX `a7_idx` (`user_id` ASC) VISIBLE,
//	  CONSTRAINT `a6`
//	    FOREIGN KEY (`book_id`)
//	    REFERENCES `bookquest`.`books` (`book_id`)
//	    ON DELETE NO ACTION
//	    ON UPDATE NO ACTION,
//	  CONSTRAINT `a7`
//	    FOREIGN KEY (`user_id`)
//	    REFERENCES `bookquest`.`users` (`user_id`)
//	    ON DELETE NO ACTION
//	    ON UPDATE NO ACTION);

	   
}
