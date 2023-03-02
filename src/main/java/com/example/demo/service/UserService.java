package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class UserService {
   
   @Autowired
   private UserRepository repo;
   

   
   public User save(User user) {
      
      System.out.println("test2221221"+user);
      user.setRole("Normal");
      user.setSignupDate(LocalDateTime.now());
      
      return repo.save(user);
   }



   public User getUserByEmail(String userEmail) {
      
      return repo.getUserByEmail(userEmail);
   }
   
}