package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.User;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping(value = "/mypage")
public class MyPageController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailService orderDetailService;

	@GetMapping("")
	public String getUserMyPage(Model model, Principal principal) {
		String email = principal.getName();
		System.out.println(email);

		User user = userService.getUserByEmail(email);
		System.out.println(user);

		List<OrderDetail> orderDetails = orderDetailService.findOrderDetailsByUser(user);
		model.addAttribute("user", user);
		model.addAttribute("orderDetails", orderDetails);
		return "mypage";
	}
}