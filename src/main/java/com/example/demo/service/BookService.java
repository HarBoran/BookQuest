package com.example.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.repository.BookRepository;

@Service
@Transactional
public class BookService {

	@Autowired
	private BookRepository repo;

	public List<Book> listbook(Category category) {
		return repo.findByCategoryId(category);
	}

	public List<Book> newbooks(Category category) {
		return repo.finByNewBook(category);
	}

	public List<Book> findAll() {
		return (List<Book>) repo.findAll();
	}

	public List<Book> findRandomBooks() {
		return (List<Book>) repo.findRandomBooks();
	}

	public List<Book> findAll(String keyword) {
		return repo.findAll(keyword);
	}

	public Book save(Book registering) {
		return repo.save(registering);
	}

	public Optional<Book> findById(int bookId) {
		return repo.findById(bookId);
	}

	public Long countTotlaBooks() {
		return repo.countTotlaBooks();
	}

}