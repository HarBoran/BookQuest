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
@Table(name="payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="payment_id")
	private Integer paymentId;
	
	@Column(name="user_id", nullable = false)
	private Integer userId;

	@Column(name = "card_name", length = 32, nullable = false)
	private String cardName;
	
	@Column(name = "card_number", length = 16, nullable = false)
	private String cardNumber;
	
//	  PRIMARY KEY (`payment_id`),
//	  UNIQUE INDEX `card_number_UNIQUE` (`card_number` ASC) VISIBLE,
//	  INDEX `a5_idx` (`user_id` ASC) VISIBLE,
//	  CONSTRAINT `a5`
//	    FOREIGN KEY (`user_id`)
//	    REFERENCES `bookquest`.`users` (`user_id`)
//	    ON DELETE NO ACTION
//	    ON UPDATE NO ACTION);

}
