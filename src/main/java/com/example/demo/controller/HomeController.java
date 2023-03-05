	package com.example.demo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.FileUploadUtil;
import com.example.demo.entity.Book;
import com.example.demo.entity.Branches;
import com.example.demo.entity.Category;
import com.example.demo.service.BookService;
import com.example.demo.service.BranchService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderDetailService;

@Controller
@RequestMapping(value = { "/" }, method = { RequestMethod.GET, RequestMethod.POST })
public class HomeController {

	@Autowired
	BookService bookService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BranchService bracnchService;
	
	@Autowired
	OrderDetailService orderdetailService;

	@GetMapping("")
	public String viewHomePage(Authentication authentication,Model model, Category category) {		
		final int showBookMain = 5;
		
		List<Book> randomBooks = bookService.findRandomBooks();
		Long totalBooks = bookService.countTotlaBooks();
		model.addAttribute("randomBooks", totalBooks < showBookMain ? randomBooks : randomBooks.subList(0, showBookMain));

		List<Object> bestseller = orderdetailService.bestseller();
		Long totalBestseller = orderdetailService.countTotalBooks();
		model.addAttribute("bestseller", totalBestseller < showBookMain ? bestseller : bestseller.subList(0, showBookMain));

		List<Book> newbooks = bookService.newbooks(category);
		model.addAttribute("newbooks", totalBooks < showBookMain ? newbooks : newbooks.subList(0, showBookMain));

		String[][] recommendationList = {{"A.I가 추천해주 내 취향 도서", "/categories","randomBooks"},
										{"신상품", "/book/new","newbooks"},
										{"베스트 샐러(order순)", "/book/bestseller","bestseller"},	
										{"이주의 특가 상품", "/categories","randomBooks"}};
		
		if(authentication == null) {
			recommendationList = Arrays.copyOfRange(recommendationList, 1, recommendationList.length);
		}

		model.addAttribute("recommendationList", recommendationList);
		return "home";
	}
	
	//<div th:replace="commonspace :: menu" />에서는 먹히지 않음
	@GetMapping("/common")
	public String commonspace(Model model) {
		List<Branches> branchList = bracnchService.findAll();
		model.addAttribute("branchList", branchList);
		return "commonspace";
	}

	@GetMapping("/newBookRegistration")
	public String newBookregistration(Model model) {
		LocalDate now = LocalDate.now();
		Book registering = new Book();
		registering.setPublicationDate(now);
		model.addAttribute("registering", registering);

		List<Category> categoryList = categoryService.findCategory();
		model.addAttribute("categoryList", categoryList);

		return "newBookRegisteringAndRevising";
	}

	@GetMapping("/usedBookHome")
	public String oldBookregistration(Model model) {
		List<Category> categoryList = categoryService.findCategory();
		model.addAttribute("categoryList", categoryList);
		
		List<Book> usedBooks = bookService.findAll();
		model.addAttribute("usedBooks", usedBooks);
		return "usedBookHome";
	}

	@GetMapping("/informationBranch/{id}")
	public String branchInformation(@PathVariable ("id") Integer id, Model model) {
		//List<Branchs> branchList = bracnchService.findAll();
		Branches branchInformation = bracnchService.finById(id);
		model.addAttribute("branchInformation", branchInformation);
		return "informationBranch";
	}

	@PostMapping("/saveBookInformation")
	public String saveBookInformation(Book registering, @RequestParam("uploadBookCover") MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws IOException {
		

		if (!multipartFile.isEmpty()) {

			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			registering.setImage(fileName);

			Book saveBook = bookService.save(registering);
			String uploadDir = "bookCover/" + saveBook.getCategory().getName();

			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			
		} else {
			bookService.save(registering);
		}
		redirectAttributes.addFlashAttribute("message", "A Book has been saved successfully.");
		return "redirect:/";

	}

}
