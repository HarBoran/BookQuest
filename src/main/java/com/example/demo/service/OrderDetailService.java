package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Book;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.repository.OrderDetailRepository;

@Service
@Transactional
public class OrderDetailService {

	@Autowired
	private OrderDetailRepository repo;

	public List<OrderDetail> findOrderDetailsByOrder(Order order) {
		// TODO Auto-generated method stub
		return repo.findOrderDetailsByOrder(order);
	}

	public List<Object> bestseller() {
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

}
