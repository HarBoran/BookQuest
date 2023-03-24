package com.example.demo.restcontroller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.MyPageService;
import com.example.demo.service.UserService;

@RestController
public class MyPageRestController {

	@Autowired
	UserService userService;

	@Autowired
	MyPageService myPageService;

	// @ResponseBody는 위에 @RestController가 아니라 기본 @Controller 일 때 붙여줌.
	@PostMapping("/bookCountsByCategory")
	public Map<String, Integer> getBookCountsByCategory(Principal principal) {
		String email = principal.getName();
		User user = userService.getUserByEmail(email);
		Map<String, Integer> bookCountsByCategory = myPageService.getBookCountsByCategory(user);
		return bookCountsByCategory;
	}

	@PostMapping("/selectedCategory")
	public Map<String, Integer> getCategoryByUser(Principal principal, @RequestBody List<String> data) {
		return null;
	}
}