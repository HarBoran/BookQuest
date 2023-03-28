package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
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
		
		//유저의 배송중, 배송완료의 개수를 가져옴
		int shipping = orderService.countByDeliveryStatus(user, "배송준비중");
		int deliveryCompleted = orderService.countByDeliveryStatus(user, "배송완료");
		
		List level= new ArrayList<>(); 	
		if (deliveryCompleted < 2) {
			level = Arrays.asList("책린이", "Lv.1", "/images/level=1Lv.png", "/images/clap.png");
		} else if (deliveryCompleted < 6 && deliveryCompleted >= 2) {
			level = Arrays.asList("우수한 독서가", "Lv.2", "/images/level=2Lv.png", "/images/fire.png");
		} else if (deliveryCompleted < 10 && deliveryCompleted >= 6) {
			level = Arrays.asList("성실한 독서가", "Lv.3", "/images/level=3Lv.png", "/images/fire.png");
		} else if (deliveryCompleted < 15 && deliveryCompleted >= 10) {
			level = Arrays.asList("열정적인 다독왕", "Lv.4", "/images/level=4Lv.png", "/images/crown.png");
		} else {
			level = Arrays.asList("살아있는 도서관", "Lv.5", "/images/level=5Lv.png", "/images/crown.png");
		}
	

		
		model.addAttribute("user", user);				
		model.addAttribute("shipping", shipping);
		model.addAttribute("deliveryCompleted", deliveryCompleted);
		model.addAttribute("level", level);
		model.addAttribute("orderDetails", orderDetails);
		model.addAttribute("bookCountsByCategory", bookCountsByCategory);
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("paymentList", paymentList);

		return "mypage";
	}
}