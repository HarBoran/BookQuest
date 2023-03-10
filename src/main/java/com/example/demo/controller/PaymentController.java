
package com.example.demo.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.FileUploadUtil;
import com.example.demo.entity.Payment;
import com.example.demo.entity.User;
import com.example.demo.service.BookService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	@Autowired
	PaymentService paymentService;

	@GetMapping("/paymentadd")
	public String signUpPage(Model theModel, Payment payment) {
		Payment payments = new Payment();
		theModel.addAttribute("payment", payments);
		return "paymentadd";
	}

	@PostMapping("/save")
	public String Save(@ModelAttribute("payment") Payment payment, Model themodel, Principal principal) {
		User user = userService.findByEmail(principal.getName());
		payment.setUser(user);
		paymentService.save(payment);
		return "home";

	}

	@GetMapping("/update/{paymentId}")
	public String edit(@PathVariable(name = "paymentId") int paymentId, Model model, RedirectAttributes rttr)
			throws Exception {
		Payment payment = paymentService.get(paymentId);
		model.addAttribute("payment", payment);
		return "paymentadd";

	}

	@GetMapping("/delete/{paymentId}")
	public String deleteById(@PathVariable(name = "paymentId") int paymentId) {
		paymentService.delete(paymentId);
		return "home";
	}

}