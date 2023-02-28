package com.example.demo.entity;

import java.time.LocalDateTime;

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
@Table(name="reviews")
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reviews_id")
	private Integer reviewsid;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@Column(name="star_rating", nullable = false)
	private String starRating;
	
	@Column(nullable = false)
	private String comment;
	
	@Column(name = "comment_date",length = 255)
	private LocalDateTime commentDate;
	
//	  PRIMARY KEY (`reviews_id`),
//	  INDEX `a10_idx` (`user_id` ASC) VISIBLE,
//	  INDEX `a11_idx` (`book_id` ASC) VISIBLE,
//	  CONSTRAINT `a10`
//	    FOREIGN KEY (`user_id`)
//	    REFERENCES `bookquest`.`users` (`user_id`)
//	    ON DELETE NO ACTION
//	    ON UPDATE NO ACTION,
//	  CONSTRAINT `a11`
//	    FOREIGN KEY (`book_id`)
//	    REFERENCES `bookquest`.`books` (`book_id`)
//	    ON DELETE NO ACTION
//	    ON UPDATE NO ACTION);

}
