package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Book;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Category;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.service.BookService;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping(value = "/book", method = { RequestMethod.GET, RequestMethod.POST })
public class BookController {

	@Autowired
	private BookService bookservice;

	@Autowired
	private OrderDetailService orderdetailservice;

	@Autowired
	private ReviewService reviewservice;

	@Autowired
	private UserService userservice;

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

		if (reviewservice.findByBookid(book).isEmpty()) {
			Book books = bookservice.findByID(book.getBookId());
			model.addAttribute("bookdetail", books);

		} else if (!reviewservice.findByBookid(book).isEmpty()) {

			List<Object> bookdetail = new ArrayList<Object>();
			bookdetail.addAll(reviewservice.findByBookid(book));
			model.addAttribute("bookdetail", bookdetail);
		}
		model.addAttribute("review", new Review());
		model.addAttribute("cart", new Cart());
		return "bookdetail";
	}

	@PostMapping("/review")
	public String reviewsave(Book book,@ModelAttribute("review") Review review, Model model,Principal principal) {
		Book books = bookservice.findByID(book.getBookId());
		
		String username = principal.getName();
		Optional<User> user = userservice.findByID(username);
		User userId = user.get();
		reviewservice.save(review, userId, books);
		return "redirect:/book/detail";
	}

}