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
import com.example.demo.service.BranchService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderDetailService;

@Controller
@RequestMapping(value = "/usedBookHome", method = { RequestMethod.GET, RequestMethod.POST })
public class UsedBookController {

	@Autowired
	BookService bookService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BranchService bracnchService;
	
	@Autowired
	OrderDetailService orderdetailService;

	@GetMapping("")
	public String oldBookregistration(Model model) {
		List<Category> categoryList = categoryService.findCategory();
		model.addAttribute("categoryList", categoryList);
		
		List<Book> usedBooks = bookService.findAll();
		model.addAttribute("usedBooks", usedBooks);
		return "usedBook/usedBookHome";
	}
	
}