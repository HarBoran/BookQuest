package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name="branch")
public class Branch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="branch_id")
	private Integer branchId;
	
	@Column(name="branch_name", length = 16, nullable = false)
	private String branchName;

	@Column(name="branch_address",length = 64, nullable = false)
	private String branchAddress;

	
	public Branch(){}
}
