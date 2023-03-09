package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Sales;
import com.example.demo.repository.SalesRepository;

@Service
@Transactional
public class SalesService {

	@Autowired
	private SalesRepository repo;

	public void save(Sales sales) {
		repo.save(sales);	
	}

	public Iterable<Sales> checkDeliveryStatus() {
		return repo.findAll();
		//return (List<SalesService>) repo.checkDeliveryStatus();
	}


}