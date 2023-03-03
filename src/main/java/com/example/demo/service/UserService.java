package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User save(User user) {

		System.out.println("test2221221" + user);
		user.setRole("Normal");
		user.setSignupDate(LocalDateTime.now());

		return repo.save(user);
	}

	public User getUserByEmail(String userEmail) {

		return repo.getUserByEmail(userEmail);
	}

	public Optional<User> findById(int userid) {
		return repo.findById(userid);
	}

	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}

	public Optional<User> findByID(String username) {
		// TODO Auto-generated method stub
		return repo.finByID(username);
	}

}