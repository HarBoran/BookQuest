package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private Integer categoryId;
	
	@Column(name="pure_notation")
	private String pureNotation;
	
	@Column(length = 32, nullable = false)
	private String name;

	@OneToOne
	@JoinColumn(name = "parent_id")
	private Category parent;
	
	@OneToMany(mappedBy="parent")
	private Set<Category> children = new HashSet<>();

	public Category(){}
	
	public Category(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Category(String pureNotation, String name) {
		this.pureNotation = pureNotation;
		this.name = name;
	}

	public Category(String pureNotation, String name, Category parent) {
		this(pureNotation,name);
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", pureNotation=" + pureNotation + ", name=" + name + "]";
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getPureNotation() {
		return pureNotation;
	}

	public void setPureNotation(String pureNotation) {
		this.pureNotation = pureNotation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getChildren() {
		return children;
	}

	public void setChildren(Set<Category> children) {
		this.children = children;
	}

	
}
