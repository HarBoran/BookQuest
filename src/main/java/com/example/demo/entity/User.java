package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(length = 32, nullable = false, unique = true)
	private String email;
	
	@Column(length = 32, nullable = false)
	private String password;
	
	@Column(length = 16, nullable = false)
	private String name;
	
	@Column(length = 32, nullable = false)
	private String phone;
	
	@Column(length = 45, nullable = false)
	private String photo;
	
	@Column(length = 128, nullable = false)
	private String address;
	
	@Column(name="signup_date", length = 255, nullable = false)
	private LocalDateTime signupDate;
	
	@Column
	private Boolean enabled;
	
	@OneToMany(mappedBy = "user")
	private Set<Payment> payment;
	
	@Column(length = 16, nullable = false)
	private String role;

}
