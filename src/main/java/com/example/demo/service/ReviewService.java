package com.example.demo.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Book;
import com.example.demo.entity.Review;
import com.example.demo.repository.ReviewRepository;

@Service
@Transactional
public class ReviewService {
   
   @Autowired
   private ReviewRepository repo;
   
   public List<Object> findByBookid(Book book){
      return repo.findByBookid(book);
   }

}