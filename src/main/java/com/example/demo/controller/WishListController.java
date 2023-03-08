package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.entity.Wishlist;
import com.example.demo.service.WishlistService;

@Controller
@RequestMapping(value = "/wishlist")
public class WishListController {
   
   @Autowired
   private WishlistService wishlistService;
   
   @GetMapping("/")
   public String wishList(Model themodel, Principal principal) {

      return listByPage(1, themodel, principal);
   }

   @GetMapping("/page/{pageNum}")
   public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model, Principal principal) {

      String userEmail = principal.getName();
      User user = wishlistService.getUserByEmail(userEmail);

      Page<Wishlist> page = wishlistService.findWishListByUserpaging(user, pageNum);
      List<Wishlist> wishList = page.getContent();

      long startCount = (pageNum - 1) * wishlistService.WISHLIST_PER_PAGE + 1;
      long endCount = startCount + wishlistService.WISHLIST_PER_PAGE - 1;
      if (endCount > page.getTotalElements()) {
         endCount = page.getTotalElements();
      }

      model.addAttribute("currentpage", pageNum);
      model.addAttribute("pre", page.getNumber());
      model.addAttribute("next", (page.getNumber() + 2));
      model.addAttribute("totalPages", page.getTotalPages());
      model.addAttribute("startCount", startCount);
      model.addAttribute("endCount", endCount);
      model.addAttribute("totalItems", page.getTotalElements());
      model.addAttribute("wishList", wishList);

      return "wishlist";
   }

}