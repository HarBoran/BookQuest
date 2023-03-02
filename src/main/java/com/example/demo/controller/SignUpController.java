
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.FileUploadUtil;
import com.example.demo.entity.User;
import com.example.demo.service.BookService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping(value = "/sign")
public class SignUpController {

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String signUpPage(Model theModel, Principal principal) {

		/*
		 * System.out.println("test====="+principal.getName()); String email =
		 * principal.getName(); List<User> UserInfo = userService.UserInfo(email);
		 * System.out.println(UserInfo);
		 */
		// theModel.addAttribute("userInfo", UserInfo);
		User user = new User();
		theModel.addAttribute("user", user);
		return "signUpPage";
	}

	@PostMapping("/save")
	public String Save(@ModelAttribute("user") User user, Model themodel, RedirectAttributes ttt,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		System.out.println(user.getPhoto());
		if (multipartFile.getOriginalFilename().isEmpty()) {
			userService.save(user);

		} else if (!multipartFile.getOriginalFilename().isEmpty()) {

			if (!multipartFile.isEmpty()) {
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

				user.setPhoto(fileName);
				System.out.println("fileName====" + fileName);
				User saveUser = userService.save(user);
				String uploadDir = "user-photos/" + saveUser.getUserId(); // 유저의 번호를 가져와서 폴더를 만드는 과정

				FileUploadUtil.cleanDir(uploadDir);

				FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

			}

		}
		ttt.addFlashAttribute("message", "The user has been saved successfully");
		return "redirect:/";
	}

}