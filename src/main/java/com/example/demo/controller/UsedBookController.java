package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.service.BookService;
import com.example.demo.service.BranchService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderDetailService;

@Controller
@RequestMapping("/usedBookHome")
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
	public String usedBookHome(Model model) {
		List<Category> categoryList = categoryService.findCategory();
		model.addAttribute("categoryList", categoryList);
		
		List<Book> usedBooks = bookService.findAll();
		model.addAttribute("usedBooks", usedBooks);
		return "usedBookHome";
	}
	
	@GetMapping("/usedBookSearchPage")
	public String usedBookSearchPage(@Param("keyword") String keyword, Model model) {
		List<Category> categoryList = categoryService.findCategory();
		model.addAttribute("categoryList", categoryList);
		
		List<Book> usedBooks = bookService.findAll(keyword);
		model.addAttribute("usedBooks", usedBooks);
		return "usedBookSearchPage";
	}

	@RequestMapping(value = "/usedBookSell/{bookId}", method = { RequestMethod.GET, RequestMethod.POST })
	public String usedBookSell(@PathVariable("bookId") Integer bookId, Model model) {
		Book sellBook = bookService.findById(bookId).get();
		model.addAttribute("sellBook", sellBook);
		return "usedBookSell";
	}
	
	@PostMapping("/usedBookBuy/{bookId}")
	public String usedBookBuy(@PathVariable("bookId") Integer bookId, Model model) {
		Book buyBook = bookService.findById(bookId).get();
		model.addAttribute("buyBook", buyBook);
		return "usedBookBuy";
	}	
}