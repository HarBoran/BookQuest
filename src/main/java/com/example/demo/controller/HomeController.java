package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;

@Controller
@RequestMapping(value = {"/"}, method = { RequestMethod.GET, RequestMethod.POST })
public class HomeController {

	@Autowired
	BookService bookService;
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("")
	public String viewHomePage(Model theModel) {
		return "home";
	}
	
	@GetMapping("/bookregistration")
	public String viewCommonSpcae(Model theModel) {
		Book registeringAndRevising = new Book();
		List<Category> categoryList = categoryService.findCategory();

		theModel.addAttribute("registeringAndRevising", registeringAndRevising);
		theModel.addAttribute("categoryList", categoryList);
		
		return "registeringAndRevising";
	}


}
