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
@Table(name="payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="payment_id")
	private Integer paymentId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@Column(name = "card_name", length = 32, nullable = false)
	private String cardName;
	
	@Column(name = "card_number", length = 16, nullable = false)
	private String cardNumber;
	
	public Payment(){}

}
