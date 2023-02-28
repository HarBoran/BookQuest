package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.ReviewRepository;

@Service
@Transactional
public class ReviewService {
	
	@Autowired
	private ReviewRepository repo;
	
}