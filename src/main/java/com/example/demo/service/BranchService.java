package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Book;
import com.example.demo.entity.Branchs;
import com.example.demo.repository.BranchRepository;

@Service
@Transactional
public class BranchService {
	
	@Autowired
	private BranchRepository repo;

	public List<Branchs> findBranch() {
		return (List<Branchs>) repo.findAll();
	}

}