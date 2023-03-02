package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Cart;
import com.example.demo.entity.User;
import com.example.demo.service.CartService;

@Controller
@RequestMapping(value="/cart")
public class CartController {

   
   @Autowired
   private CartService cartService;
   
   @GetMapping("/")
   public String CartList(Model themodel, Principal principal) {
       
      String userEmail = principal.getName(); 
      User user = cartService.getUserByEmail(userEmail);
      
      List<Cart> cartList = cartService.findCartByUser(user);
      
      System.out.println(cartList);
      themodel.addAttribute("cartList", cartList);
      return "cart";
   }
   
   
}