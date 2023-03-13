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
import com.example.demo.entity.BooksBranch;
import com.example.demo.entity.Branches;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Category;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.entity.Wishlist;
import com.example.demo.service.BookService;
import com.example.demo.service.BooksBranchService;
import com.example.demo.service.BranchService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;
import com.example.demo.service.WishlistService;

@Controller
@RequestMapping(value = "/book", method = { RequestMethod.GET, RequestMethod.POST })
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private UserService userService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private WishlistService wishlistService;

	@Autowired
	private BooksBranchService booksbranchService;

	@GetMapping("")
	public String category_book(Category category, Model model, @Param("keyword") String keyword) {
		List<Category> listCategories = categoryService.findCategory();
		List<Book> books = new ArrayList<Book>();

		if (keyword == null) {
			books.addAll(bookService.listbook(category));
		} else if (keyword != null) {
			books.addAll(bookService.findAll(keyword));
		}

		model.addAttribute("books", books);
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("msg", "도서 찾기");
		return "book";
	}

	@GetMapping("/categories")
	public String listAllBefore(Model model) {
		List<Category> listCategories = categoryService.findCategory();
		List<Book> books = bookService.findAll();

		model.addAttribute("listCategories", listCategories);
		model.addAttribute("books", books);
		model.addAttribute("msg", " 도서찾기");
		return "book";
	}

	@GetMapping("/new")
	public String new_book(Category category, Model model) {
		List<Book> newbooks = new ArrayList<Book>();
		newbooks.addAll(bookService.newbooks(category));
		List<Category> listCategories = categoryService.findCategory();

		model.addAttribute("books", newbooks);
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("msg", "신간 도서");
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

	@PostMapping("/review")
	public String reviewsave(@RequestParam("book") int book, @ModelAttribute("review") Review review, Model model,
			Principal principal) {
		Optional<Book> books = bookService.findById(book);
		Book bookId = books.get();
		String username = principal.getName();
		Optional<User> user = userService.findByID(username);
		User userId = user.get();
		reviewService.save(review, userId, bookId);
		return "redirect:/book/detail?book=" + book;
	}

	@GetMapping("/descReview")
	public String descReview(Book book, Model model) {
		List<Category> listCategories = categoryService.findCategory();

		model.addAttribute("listCategories", listCategories);
		model.addAttribute("msg", "평점순");
		return "book";
	}

	@GetMapping("/detail")
	public String bookdetail(Book book, Model model, Principal principal) {

		if (principal == null) {
			model.addAttribute("check", "b");
			model.addAttribute("bookdetail", book);
			model.addAttribute("review", new Review());
			List<Review> review = reviewService.findByBookid(book);
			model.addAttribute("reviewdetail", review);
			model.addAttribute("cart", new Cart());
			List<BooksBranch> bookbranch = booksbranchService.findById(book);
			model.addAttribute("bookbranch", bookbranch);
			if (!review.isEmpty()) {
				model.addAttribute("avgstar", reviewService.avgstar(book));
			}

		} else {
			int bookId = book.getBookId();
			String username = principal.getName();
			Optional<User> user = userService.findByID(username);
			User userId = user.get();

			List<Wishlist> wishlistForUser = wishlistService.findByid(userId);

			List<Integer> booksnumber = new ArrayList<>();

			for (int i = 0; i < wishlistForUser.size(); i++) {
				booksnumber.add(wishlistForUser.get(i).getBook().getBookId());
			}

			if (booksnumber.contains(bookId)) {
				model.addAttribute("check", "a");

			} else if (!booksnumber.contains(bookId)) {
				model.addAttribute("check", "b");

			}

			Optional<Book> books = bookService.findById(book.getBookId());
			model.addAttribute("bookdetail", books.get());
			List<Review> review = reviewService.findByBookid(book);
			model.addAttribute("reviewdetail", review);
			if (!review.isEmpty()) {
				model.addAttribute("avgstar", reviewService.avgstar(book));
			}
			model.addAttribute("review", new Review());
			model.addAttribute("cart", new Cart());

			if (!review.isEmpty()) {
				bookService.saveavg(book, reviewService.avgstar(book));
				model.addAttribute("avgstar", reviewService.avgstar(book));
			}
			model.addAttribute("review", new Review());
			model.addAttribute("cart", new Cart());
			List<BooksBranch> bookbranch = booksbranchService.findById(book);

			if (!bookbranch.isEmpty()) {
				model.addAttribute("bookbranch", bookbranch);
			} else if (bookbranch.isEmpty()) {
				model.addAttribute("branchmsg", "재고수량이 없습니다");
			}
		}
		return "bookdetail";
	}

	@GetMapping("/sortPrice")
	public String sortPrice(Model model) {
		List<Category> listCategories = categoryService.findCategory();
		model.addAttribute("listCategories", listCategories);
		List<Book> sortprice = new ArrayList<Book>();
		sortprice.addAll(bookService.sortprice());
		model.addAttribute("books", sortprice);
		return "book";
	}

	@GetMapping("/sortTitle")
	public String sortTitle(Model model) {
		List<Category> listCategories = categoryService.findCategory();
		model.addAttribute("listCategories", listCategories);
		List<Book> sortTitle = new ArrayList<Book>();
		sortTitle.addAll(bookService.sortTitle());
		model.addAttribute("books", sortTitle);
		return "book";
	}

}