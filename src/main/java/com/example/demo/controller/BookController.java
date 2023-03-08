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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Book;
import com.example.demo.entity.BooksBranch;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Category;
import com.example.demo.entity.Order;
import com.example.demo.entity.Payment;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.service.BookService;
import com.example.demo.service.BooksBranchService;
import com.example.demo.service.CategoryService;
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

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BooksBranchService booksbranchService;

	@GetMapping("")
	public String category_book(Category category, Model model, @Param("keyword") String keyword) {
		List<Category> listCategories = categoryService.findCategory();
		model.addAttribute("listCategories", listCategories);
		List<Book> books = new ArrayList<Book>();
		if (keyword == null) {
			books.addAll(bookservice.listbook(category));
		} else if (keyword != null) {
			books.addAll(bookservice.findAll(keyword));
		}
		model.addAttribute("books", books);
		model.addAttribute("msg", "도서 찾기");
		return "book";
	}

	@GetMapping("/new")
	public String new_book(Category category, Model model) {
		List<Book> newbooks = new ArrayList<Book>();
		newbooks.addAll(bookservice.newbooks(category));
		model.addAttribute("books", newbooks);
		model.addAttribute("msg", "신간 도서");
		List<Category> listCategories = categoryService.findCategory();
		model.addAttribute("listCategories", listCategories);

		return "book";
	}

	@GetMapping("/bestseller")
	public String bestseller(Model model) {
		List<Object> bestseller = new ArrayList<Object>();
		bestseller.addAll(orderDetailService.bestseller());
		model.addAttribute("books", bestseller);
		model.addAttribute("msg", "베스트 셀러");
		List<Category> listCategories = categoryService.findCategory();
		model.addAttribute("listCategories", listCategories);
		return "book";
	}

	@GetMapping("/detail")
	public String bookdetail(Book book, Model model) {

		Optional<Book> books = bookservice.findById(book.getBookId());
		model.addAttribute("bookdetail", books.get());
		List<Review> review = reviewservice.findByBookid(book);
		model.addAttribute("reviewdetail", review);

		if (!review.isEmpty()) {
			model.addAttribute("avgstar", reviewservice.avgstar(book));
		}
		model.addAttribute("review", new Review());
		model.addAttribute("cart", new Cart());

		List<BooksBranch> bookbranch = booksbranchService.findById(book);
		if (!bookbranch.isEmpty()) {
			model.addAttribute("bookbranch", bookbranch);
		} else if (bookbranch.isEmpty()) {
			model.addAttribute("branchmsg", "재고수량이 없습니다");
		}

		return "bookdetail";
	}

	@PostMapping("/review")
	public String reviewsave(@RequestParam("book") int book, @ModelAttribute("review") Review review, Model model,
			Principal principal) {
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
			@RequestParam("bookquantity") int bookquantity, RedirectAttributes r) {
		if (bookquantity == 0) {
			r.addFlashAttribute("rmsg", "책의 수량을 선택해주세요");
		} else if (bookquantity != 0) {
			Optional<Book> books = bookservice.findById(book.getBookId());
			model.addAttribute("bookdetail", books.get());
			String username = principal.getName();
			Optional<User> user = userservice.findByID(username);
			List<Payment> paymentList = paymentService.findPaymentByUser(user.get());
			model.addAttribute("bookquantity", bookquantity);
			model.addAttribute("paymentList", paymentList);
			model.addAttribute("user", user.get());
			Order order = new Order();
			order.setUser(user.get());
			order.setAddress(user.get().getAddress());
			model.addAttribute("orders", order);

			return "redirectbuy";
		}
		return "redirect:/book/detail?book=" + book.getBookId();
	}

	@PostMapping("/orderbuy")
	public String orderbuy(Model model, Principal principal, @ModelAttribute("orders") Order order,
			@RequestParam("totalPrice") int totalPrice, Book book, @RequestParam("bookquantity") int bookquantity) {
		String userEmail = principal.getName();
		User user = userservice.getUserByEmail(userEmail);
		order.setTotalPrice(totalPrice);
		order.setUser(user);
		orderService.save(order);

		System.err.println(user);
		System.err.println(order);

		Optional<Book> books = bookservice.findById(book.getBookId());

		Book book1 = books.get();
		int bookPrice = book1.getPrice();

		orderDetailService.saveOrderDetails(order, book1, bookPrice, bookquantity);

		return "redirect:/";
	}

	@GetMapping("/descReview")
	public String descReview(Book book, Model model) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>" + book);
		List<Category> listCategories = categoryService.findCategory();
		model.addAttribute("listCategories", listCategories);

		model.addAttribute("msg", "평점순");
		return "test";
	}

}