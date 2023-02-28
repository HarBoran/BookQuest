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
@Table(name="order_details")
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_detail_id")
	private Integer orderDetailId;
	
	@Column(name="order_id", nullable = false)
	private Integer orderId;
	
	@Column(name="book_id", nullable = false)
	private Integer bookId;
	
	@Column(name="order_quantity", nullable = false)
	private Integer orderQuantity;
	
	@Column(nullable = false)
	private Integer price;

//	  PRIMARY KEY (`order_detail_id`),
//	  INDEX `a3_idx` (`book_id` ASC) VISIBLE,
//	  INDEX `a4_idx` (`order_id` ASC) VISIBLE,
//	  CONSTRAINT `a3`
//	    FOREIGN KEY (`book_id`)
//	    REFERENCES `bookquest`.`books` (`book_id`)
//	    ON DELETE NO ACTION
//	    ON UPDATE NO ACTION,
//	  CONSTRAINT `a4`
//	    FOREIGN KEY (`order_id`)
//	    REFERENCES `bookquest`.`orders` (`order_id`)
//	    ON DELETE NO ACTION
//	    ON UPDATE NO ACTION);

}
