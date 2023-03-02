package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}