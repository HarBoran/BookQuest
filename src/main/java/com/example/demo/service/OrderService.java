package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
   
   @Autowired
   private OrderRepository repo;


   public List<Order> findOrderByUser(User user) {
      // TODO Auto-generated method stub
      return repo.findOrderByUser(user);
   }
   
   
}