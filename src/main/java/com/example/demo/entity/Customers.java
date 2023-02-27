package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customers")
public class Customers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	
	@Column(length = 255, nullable = false, unique = true)
	private String id;
	
	private String password;
	private String name;
	private String phoneNumber;
	private String email;
	private String address;
	private Date signUpDate;
	private Boolean withdrawal;
	//private String 결제수단;

}

