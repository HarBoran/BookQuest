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
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Integer orderId;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="order_date")
	private LocalDateTime orderDate;
	
	@Column(name="total_price")
	private Integer totalPrice;
	
	@Column(name="address")
	private String address;
	
	@ManyToOne
	@JoinColumn(name= "payment_id")
	private Payment payment;

	@Column(name= "order_status", length = 45, nullable = false)
	private String orderStatus;
	
//	  PRIMARY KEY (`order_id`),
//	  INDEX `a1_idx` (`user_id` ASC) VISIBLE,
//	  INDEX `a2_idx` (`payment_id` ASC) VISIBLE,
//	  CONSTRAINT `a1`
//	    FOREIGN KEY (`user_id`)
//	    REFERENCES `bookquest`.`users` (`user_id`)
//	    ON DELETE NO ACTION
//	    ON UPDATE NO ACTION,
//	  CONSTRAINT `a2`
//	    FOREIGN KEY (`payment_id`)
//	    REFERENCES `bookquest`.`payment` (`payment_id`)
//	    ON DELETE NO ACTION
//	    ON UPDATE NO ACTION);

			    
	   
	   
}
