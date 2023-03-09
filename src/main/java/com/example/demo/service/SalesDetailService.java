package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.SalesDetail;
import com.example.demo.repository.SalesDetailRepository;

@Service
@Transactional
public class SalesDetailService {

	@Autowired
	private SalesDetailRepository repo;

	public void save(SalesDetail salesDetail) {
		repo.save(salesDetail);
	}


}
