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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="sales")
public class Sales {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sales_id")
	private Integer salesId;

	@ManyToOne
	@JoinColumn(name="user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="branches_id")
	private Branches branches;
	
	@Column(name="sales_date")
	private LocalDateTime salesDate;
	
	@Column(name="total_price")
	private Integer totalPrice;
	
	@Column(name="address")
	private String address;
	
	@ManyToOne
	@JoinColumn(name= "payment_id")
	private Payment payment;

	@Column(name= "delivery_status", length = 45, nullable = false)
	private String deliveryStatus;
	
	public Sales(){}
   
}
