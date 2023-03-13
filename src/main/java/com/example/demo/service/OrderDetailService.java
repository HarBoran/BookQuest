package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Book;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.User;
import com.example.demo.repository.OrderDetailRepository;

@Service
@Transactional
public class OrderDetailService {

	@Autowired
	private OrderDetailRepository repo;
	
	@Autowired
	private BookService bookService;

	public List<OrderDetail> findOrderDetailsByOrder(Order order) {
		return repo.findOrderDetailsByOrder(order);
	}

	public List<Book> bestseller() {
		return repo.bestseller();
	}

	public void saveOrderDetails(Order order, Book book1, int bookPrice, int orderQuantity) {

		OrderDetail orderdetail = new OrderDetail();
		orderdetail.setBook(book1);
		orderdetail.setOrder(order);
		orderdetail.setPrice(bookPrice);
		orderdetail.setOrderQuantity(orderQuantity);

		repo.save(orderdetail);
	}

	public List<OrderDetail> findOrderDetailsByUser(User user) {
		return repo.findOrderDetailsByUser(user);
	}

	public Long countTotalBooks() {
		return repo.countTotalBooks();

	}

	public Page<Book> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort =Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum-1, bookService.USERS_PER_PAGE, sort);
		if(keyword != null) {
			return repo.bestseller(keyword, pageable);
		}
		return repo.bestseller(pageable);
	}


	

}
