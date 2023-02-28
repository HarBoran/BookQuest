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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
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
	
	public Order(){}
	   
}
