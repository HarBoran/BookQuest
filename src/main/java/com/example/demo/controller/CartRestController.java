package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Cart;
import com.example.demo.service.BookService;
import com.example.demo.service.CartService;
import com.example.demo.service.UserService;

@Controller
public class CartRestController {

	@Autowired
	private BookService bookservice;
	@Autowired
	private UserService userservice;
	@Autowired
	private CartService cartService;

	@PostMapping("/up")
	@ResponseBody
	public int up(@RequestBody int cartId) {

		System.out.println("cartId=" + cartId);
		Cart cartOriginal = cartService.findById(cartId);
		int UpcartQuantity = cartOriginal.getBookQuantity() + 1;
		cartService.upQuantity(cartId, UpcartQuantity);

		return UpcartQuantity;
	}

	@PostMapping("/down")
	@ResponseBody
	public int Down(@RequestBody int cartId) {
		System.out.println("cartId=" + cartId);

		Cart cart = cartService.findById(cartId);
		int DowncartQuantity = cart.getBookQuantity() - 1;
		System.out.println("getBookQuantity=" + DowncartQuantity);
		cartService.DownQuantity(cartId, DowncartQuantity);

		return DowncartQuantity;
	}

}
