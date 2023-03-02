package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Book;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Payment;
import com.example.demo.entity.User;
import com.example.demo.service.BookService;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping(value = "/order")
public class OrderDetailsController {

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CartService cartService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private BookService bookService;

	@GetMapping("/")
	public String OrderDetails(Model theModel, Principal principal) {

		String userEmail = principal.getName();
		User user = userService.getUserByEmail(userEmail);
		// int userId = user.getUserId();
		List<Order> orders = orderService.findOrderByUser(user);

		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		for (int i = 0; i < orders.size(); i++) {
			orderDetails.addAll(orderDetailService.findOrderDetailsByOrder(orders.get(i)));

		}

		theModel.addAttribute("orderDetails", orderDetails);

		System.out.println("order==========" + orders);
		System.out.println("orderDetails==========" + orderDetails);
		return "orderDetails";
	}

	@GetMapping("/buy/{cartId}")
	public String buyBook(@PathVariable(name = "cartId") int cartId, Model model, Principal principal) {
		String userEmail = principal.getName();
		User user = userService.getUserByEmail(userEmail);

		List<Payment> paymentList = paymentService.findPaymentByUser(user);

		Cart cartList = cartService.findById(cartId).get();
		// int bookPrice = cartLsit.get().getBook().getPrice();
		model.addAttribute("cartList", cartList);
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("order", new Order());

		return "buyInfoDetails";
	}

	@PostMapping("/save")
	public String saveOrder(@ModelAttribute("order") Order order, @RequestParam("totalPrice") int totalPrice,
			Principal principal, @RequestParam("bookId") int bookId, @RequestParam("cartId") int cartId) {
		String userEmail = principal.getName();
		User user = userService.getUserByEmail(userEmail);
		order.setTotalPrice(totalPrice);
		order.setUser(user);
		orderService.save(order);

		Optional<Book> book = bookService.findById(bookId);
		Book book1 = book.get();
		int bookPrice = book.get().getPrice();
		Optional<Cart> cart = cartService.findById(cartId);
		int orderQuantity = cart.get().getBookQuantity();
		orderDetailService.saveOrderDetails(order, book1, bookPrice, orderQuantity);

		return "home";
	}

}