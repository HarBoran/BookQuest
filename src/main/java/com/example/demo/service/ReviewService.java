package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Book;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.repository.ReviewRepository;

@Service
@Transactional
public class ReviewService {

	@Autowired
	private ReviewRepository repo;

	public List<Review> findByBookid(Book book) {
		return repo.findByBookid(book);
	}

	public void save(Review review, User userId, Book book) {
		review.setBook(book);
		review.setUser(userId);
		review.setCommentDate(LocalDateTime.now());
		repo.save(review);

	}

	public Float avgstar(Book book) {
		Float avgstar = repo.sumstar(book) / repo.countbook(book);
		return avgstar;
	}

	public void delete(Review review) {
		repo.delete(review);
	}

	public void editReview(Review review, String updateReivew) {
		
//      if(review.getComment().substring(review.getComment().length()-5).equals("(수정됨)")) {
//		if(review.getComment().endsWith("(수정됨)")) {
//			review.setComment(updateReivew);
//		}else {
//			review.setComment(updateReivew + "(수정됨)");
//		}
		review.setComment(updateReivew);	
		repo.save(review);
	}

}