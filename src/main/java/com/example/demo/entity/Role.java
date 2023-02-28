package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name="roles")
public class Role {

	@Id
	@Column(name="role_name", length = 16, nullable = false, unique = true)
	private String roleName;
	
	public Role(){}

}
