package com.example.demo.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="books")
public class Books {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	
	@Column(length = 255, nullable = false, unique = true)
	private String title;
	//작가
	@Column(length = 255, nullable = false)
	private String author; 
	//출판사
	@Column(length = 255, nullable = false)
	private String publisher; 
	//출판일
	@Column
	private Date publication_date; 
	//가격
	@Column(length = 255, nullable = false)
	private int price; 
	
	@Column(length = 64)
	private String photos;
	//책소개
	@Column
	private String description; 
	//한국십진분류표
	@Column
	private String kdc;
	
	@Column
	private String isbn; 
	
//	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "users_roles",joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
//	private Set<Role> roles = new HashSet<>();
//	

}
