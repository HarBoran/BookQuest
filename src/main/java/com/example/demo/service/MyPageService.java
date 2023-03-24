package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Category;
import com.example.demo.entity.User;
import com.example.demo.repository.OrderDetailRepository;

@Service
@Transactional
public class MyPageService {
   
   @Autowired
   private OrderDetailRepository orderDetailRepo;
   
   public Map<String, Integer> getBookCountsByCategory(User user) {
      Pageable pageable = PageRequest.of(0, 6);
        List<Object[]> results = orderDetailRepo.findCategoriesAndBookCountsByUser(user, pageable);

        Map<String, Integer> bookCountsByCategory = new HashMap<>();

        for (Object[] result : results) {
            Category category = (Category) result[0];
            Integer bookCount = ((Number) result[1]).intValue();
            bookCountsByCategory.put(category.getName(), bookCount);
        }

        return bookCountsByCategory;
    }
   
   public List<String> findNameByCategory(User user) {
      return orderDetailRepo.findCategoriesAndBookCountsByUser(user);
   }
}