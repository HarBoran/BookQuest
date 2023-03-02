package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Cart;
import com.example.demo.entity.User;
import com.example.demo.repository.CartRepository;

@Service
@Transactional
public class CartService {

	@Autowired
	private CartRepository repo;

	public User getUserByEmail(String userEmail) {

		return repo.getUserByEmail(userEmail);
	}

	public List<Cart> findCartByUser(User user) {

		return repo.findCartByUser(user);
	}

	public Optional<Cart> findById(int cartId) {
		// TODO Auto-generated method stub
		return repo.findById(cartId);
	}

}