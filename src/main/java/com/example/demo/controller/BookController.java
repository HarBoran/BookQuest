package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.entity.OrderDetail;
import com.example.demo.service.BookService;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.ReviewService;

@Controller
@RequestMapping(value = "/book", method = { RequestMethod.GET, RequestMethod.POST })
public class BookController {

	@Autowired
	private BookService bookservice;

	@Autowired
	private OrderDetailService orderdetailservice;
	
	@Autowired
	private ReviewService reviewservice;

	@GetMapping("")
	public String category_book(Category category, Model model, @Param("keyword") String keyword) {

		List<Book> books = new ArrayList<Book>();
		if (keyword == null) {
			books.addAll(bookservice.listbook(category));
		} else if (keyword != null) {
			books.addAll(bookservice.findAll(keyword));
		}
		model.addAttribute("books", books);

		return "book";
	}

	@GetMapping("/new")
	public String new_book(Category category, Model model) {
		List<Book> newbooks = new ArrayList<Book>();
		newbooks.addAll(bookservice.newbooks(category));
		model.addAttribute("books", newbooks);

		return "book";
	}

	@GetMapping("/bestseller")
	public String bestseller(Model model) {
		List<Object> bestseller = new ArrayList<Object>();
		bestseller.addAll(orderdetailservice.bestseller());
		model.addAttribute("books", bestseller);
		return "book";
	}

	@GetMapping("/detail")
	public String bookdetail(Book book, Model model) {
		List<Object> bookdetail = new ArrayList<Object>();
		bookdetail.addAll(reviewservice.findByBookid(book));
		model.addAttribute("bookdetail", bookdetail);
		return "bookdetail";
	}

}