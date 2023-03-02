package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Review;

@Repository
public interface ReviewRepository extends PagingAndSortingRepository<Review, Integer> {

	@Query("SELECT r FROM Review r WHERE r.book=:book")
	public List<Object> findByBookid(@Param("book") Book book);

}