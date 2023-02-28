package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.PaymentRepository;

@Service
@Transactional
public class PaymentService {
	
	@Autowired
	private PaymentRepository repo;
	
}