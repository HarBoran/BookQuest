package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.BooksBranchRepository;

@Service
@Transactional
public class BooksBranchService {
	
	@Autowired
	private BooksBranchRepository repo;
	
}