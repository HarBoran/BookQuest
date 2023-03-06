package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.repository.BookRepository;

@Service
@Transactional
public class BookService {
	public final int USERS_PER_PAGE = 10;

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
	
	public Page<Book> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort =Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum-1, USERS_PER_PAGE, sort);
		if(keyword != null) {
			return repo.findAll(keyword, pageable);
		}
		return repo.findAll(pageable);
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


	public void deleteById(Integer bookId) throws Exception {
		if (repo.findById(bookId) == null ) {
			throw new Exception("Could not find any user with ID "+bookId);
		}
		repo.deleteById(bookId);
	}

}