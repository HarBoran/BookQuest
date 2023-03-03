package com.example.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import com.example.demo.entity.User;
import com.example.demo.service.BookService;
import com.example.demo.service.CartService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

	@Autowired
	private BookService bookservice;
	@Autowired
	private UserService userservice;
	@Autowired
	private CartService cartService;

	@GetMapping("/")
	public String CartList(Model themodel, Principal principal) {

		String userEmail = principal.getName();
		User user = cartService.getUserByEmail(userEmail);

		List<Cart> cartList = cartService.findCartByUser(user);

		System.out.println(cartList);
		themodel.addAttribute("cartList", cartList);
		return "cart";
	}


	   @PostMapping("/save")
	   public String savecart(Model model, @RequestParam("number") int number, Principal principal,
	         @Param("book")int book, @ModelAttribute("cart") Cart cart) {
	      Optional<Book> books = bookservice.findById(book);
	      String username = principal.getName();
	      Optional<User> user = userservice.findByID(username);
	      User userId = user.get();
	      cartService.save(cart, number, books.get(), userId);
	      return "redirect:/cart/";
	   }

	@GetMapping("/delete/{cartId}")
	public String deletebook(@PathVariable(name = "cartId") int cartId) {

		cartService.deletecartId(cartId);
		return "home";
	}

	@GetMapping("/up/{cartId}/{cartQuantity}")
	public String CountUp(@PathVariable(name = "cartId") int cartId,
			@PathVariable(name = "cartQuantity") int cartQuantity) {

		System.out.println(cartId);
		int UpcartQuantity = cartQuantity + 1;
		cartService.upQuantity(cartId, UpcartQuantity);

		return "redirect:/cart/";
	}

	@GetMapping("/down/{cartId}/{cartQuantity}")
	public String CountDown(@PathVariable(name = "cartId") int cartId,
			@PathVariable(name = "cartQuantity") int cartQuantity) {

		System.out.println(cartId);
		int DowncartQuantity = cartQuantity - 1;
		cartService.DownQuantity(cartId, DowncartQuantity);

		return "redirect:/cart/";
	}


}