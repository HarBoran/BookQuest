package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.SalesRepository;

@Service
@Transactional
public class SalesService {

	@Autowired
	private SalesRepository repo;


}