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
import com.example.demo.entity.Order;
import com.example.demo.entity.Payment;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.service.BookService;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping(value = "/book", method = { RequestMethod.GET, RequestMethod.POST })
public class BookController {

	@Autowired
	private BookService bookservice;

	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ReviewService reviewservice;

	@Autowired
	private UserService userservice;
	
	@Autowired
	private PaymentService paymentService;

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
		bestseller.addAll(orderDetailService.bestseller());
		model.addAttribute("books", bestseller);
		return "book";
	}

	@GetMapping("/detail")
	public String bookdetail(Book book, Model model) {

		Optional<Book> books = bookservice.findById(book.getBookId());
		model.addAttribute("bookdetail", books.get());

		List<Review> review = reviewservice.findByBookid(book);
		System.out.println("review====" + review);
		model.addAttribute("reviewdetail", review);

		model.addAttribute("review", new Review());
		model.addAttribute("cart", new Cart());
		return "bookdetail";
	}

	@PostMapping("/review")
	public String reviewsave(@RequestParam("book") int book, @ModelAttribute("review") Review review, Model model,
			Principal principal) {
		System.out.println(book);
		Optional<Book> books = bookservice.findById(book);
		Book bookId = books.get();
		String username = principal.getName();
		Optional<User> user = userservice.findByID(username);
		User userId = user.get();
		reviewservice.save(review, userId, bookId);
		return "redirect:/book/detail?book=" + book;
	}

	@GetMapping("/redirectbuy")
	public String redirectbuy(Book book, Model model, Principal principal,
			@RequestParam("bookquantity") int bookquantity) {

		Optional<Book> books = bookservice.findById(book.getBookId());
		model.addAttribute("bookdetail", books.get());
		String username = principal.getName();
		Optional<User> user = userservice.findByID(username);
		List<Payment> paymentList = paymentService.findPaymentByUser(user.get());
		model.addAttribute("bookquantity", bookquantity);
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("user", user.get());
		model.addAttribute("orders", new Order());
		return "redirectbuy";
	}

	@PostMapping("/orderbuy")
	public String orderbuy(Model model, Principal principal, @ModelAttribute("orders") Order order,
			@RequestParam("totalPrice") int totalPrice, Book book, @RequestParam("bookquantity") int bookquantity) {
		System.out.println(order);
		System.out.println(totalPrice);
		System.out.println(book);
		System.out.println(bookquantity);
		String userEmail = principal.getName();
		User user = userservice.getUserByEmail(userEmail);
		order.setTotalPrice(totalPrice);
		order.setUser(user);
		orderService.save(order);

		Optional<Book> books = bookservice.findById(book.getBookId());

		Book book1 = books.get();
		int bookPrice = book1.getPrice();

		orderDetailService.saveOrderDetails(order, book1, bookPrice, bookquantity);

		return "redirect:/";
	}

}