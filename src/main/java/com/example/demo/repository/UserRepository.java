package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.email =:email")
	public User getUserByEmail(@Param("email") String email);

}