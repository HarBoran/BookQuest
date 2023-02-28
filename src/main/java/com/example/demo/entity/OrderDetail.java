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
@Table(name="order_details")
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_detail_id")
	private Integer orderDetailId;

	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@Column(name="order_quantity", nullable = false)
	private Integer orderQuantity;
	
	@Column(nullable = false)
	private Integer price;
	
	public OrderDetail(){}

}
