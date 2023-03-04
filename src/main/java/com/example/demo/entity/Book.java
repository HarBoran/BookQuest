package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_id")
	private Integer bookId;
	
	@Column(length = 128, nullable = false)
	private String title;
	
	@Column(length = 32, nullable = false)
	private String author; 
	
	@Column(length = 32, nullable = false)
	private String publisher; 
	
	@Column(name = "publication_date", nullable = false)
	private LocalDate publicationDate; 
	
	@Column(nullable = false)
	private Integer price; 
	
	@Column(length = 128, nullable = false)
	private String image;
	
	@Column(nullable = false)
	private String description; 
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@Transient
	public String getImagePath() {
		if (bookId == null || image == null) return "/images/blank-book-cover-white.jpg";
		return "/bookCover/" + this.category.getName() + "/" + this.image;
	}

	public Book(){}
	
	public Book(Integer bookId) {
		this.bookId = bookId;
	}


	public Book(String title, String author, String publisher, LocalDate publicationDate, Integer price,
			String image, String description, Category category) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
		this.price = price;
		this.image = image;
		this.description = description;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", publicationDate=" + publicationDate + ", price=" + price + ", image=" + image + ", description="
				+ description + ", category=" + category + "]";
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}	
	


}
