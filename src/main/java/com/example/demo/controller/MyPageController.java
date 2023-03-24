package com.example.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Payment;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.MyPageService;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping(value = "/mypage")
public class MyPageController {

	@Autowired
	UserService userService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderDetailService orderDetailService;

	@Autowired
	PaymentService paymentService;

	@Autowired
	MyPageService myPageService;

	@Autowired
	CategoryService categoryService;

	@GetMapping("")
	public String getUserMyPage(Model model, Principal principal) {
		String email = principal.getName();
		User user = userService.getUserByEmail(email);

		// 유저의 모든 주문내역을 가져옴.
		List<OrderDetail> orderDetails = orderDetailService.findOrderDetailsByUser(user);

		// 유저가 구매한 모든 책의 권수와 카테고리를 가져옴.
		Map<String, Integer> bookCountsByCategory = myPageService.getBookCountsByCategory(user);

		// 유저가 구매한 모든 책의 카테고리 이름을 가져옴.
		List<String> listCategories = myPageService.findNameByCategory(user);

		// 유저의 모든 결제수단을 가져옴.
		List<Payment> paymentList = paymentService.findPaymentByUser(user);

		model.addAttribute("user", user);
		model.addAttribute("orderDetails", orderDetails);
		model.addAttribute("bookCountsByCategory", bookCountsByCategory);
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("paymentList", paymentList);

		return "mypage";
	}
}