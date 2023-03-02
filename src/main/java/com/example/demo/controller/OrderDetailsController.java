package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.User;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
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

}