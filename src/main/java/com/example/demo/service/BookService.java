package com.example.demo.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<Book> findAll(String keyword) {
		return repo.findAll(keyword);
	}

}