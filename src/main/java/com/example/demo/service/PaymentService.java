package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Payment;
import com.example.demo.entity.User;
import com.example.demo.repository.PaymentRepository;

@Service
@Transactional
public class PaymentService {

	@Autowired
	private PaymentRepository repo;

	public List<Payment> findPaymentByUser(User user) {

		return repo.findPaymentByUser(user);
	}

}