package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Book;
import com.example.demo.entity.BooksBranch;
import com.example.demo.entity.Category;
import com.example.demo.repository.BooksBranchRepository;

@Service
@Transactional
public class BooksBranchService {

	@Autowired
	private BooksBranchRepository repo;

	public List<BooksBranch> findById(Book book) {

		return repo.findByID(book);
	}

	public List<BooksBranch> findByCategory(Category category) {
		return repo.findByCategory(category);
	}

}