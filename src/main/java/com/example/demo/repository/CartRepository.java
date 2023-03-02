package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;

@Repository
public interface CartRepository extends PagingAndSortingRepository<Cart, Integer> {

	@Query("SELECT u FROM User u WHERE u.email =:email")
	public User getUserByEmail(@Param("email") String email);

	@Query("SELECT c FROM Cart c WHERE c.user =:user")
	public List<Cart> findCartByUser(User user);

}