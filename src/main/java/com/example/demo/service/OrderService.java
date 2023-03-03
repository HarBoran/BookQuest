package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository repo;

	public List<Order> findOrderByUser(User user) {
		return repo.findOrderByUser(user);
	}

	public void save(Order order) {
		System.out.println("test===============" + order.getUser());
		order.setOrderDate(LocalDateTime.now());
		order.setUser(order.getUser());
		order.setOrderStatus("배송중");
		repo.save(order);
	}

	public void saveTotal(Order order, int price, User user) {
		order.setOrderDate(LocalDateTime.now());
		order.setUser(user);
		order.setTotalPrice(price);
		order.setOrderStatus("배송중");
		repo.save(order);
	}

}