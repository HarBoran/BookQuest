package com.example.demo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
import com.example.demo.entity.BooksBranch;
import com.example.demo.entity.Branches;
import com.example.demo.entity.Category;
import com.example.demo.entity.Sales;
import com.example.demo.entity.SalesDetail;
import com.example.demo.service.BookService;
import com.example.demo.service.BooksBranchService;
import com.example.demo.service.BranchService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.SalesDetailService;
import com.example.demo.service.SalesService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	BookService bookService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	BranchService branchService;

	@Autowired
	OrderDetailService orderdetailService;
	
	@Autowired
	SalesService salesService;
	
	@Autowired
	SalesDetailService salesDetailService;
	
	@Autowired
	BooksBranchService booksBranchService;

	@GetMapping("")
	public String viewHomePage(Authentication authentication, Model model, Category category) {
		final int showBookMain = 5;

		List<Book> randomBooks = bookService.findRandomBooks();
		Long totalBooks = bookService.countTotlaBooks();
		model.addAttribute("randomBooks",
				totalBooks < showBookMain ? randomBooks : randomBooks.subList(0, showBookMain));

		List<Object> bestseller = orderdetailService.bestseller();
		Long totalBestseller = orderdetailService.countTotalBooks();
		model.addAttribute("bestseller",
				totalBestseller < showBookMain ? bestseller : bestseller.subList(0, showBookMain));

		List<Book> newbooks = bookService.newbooks(category);
		model.addAttribute("newbooks", totalBooks < showBookMain ? newbooks : newbooks.subList(0, showBookMain));

		String[][] recommendationList = {{ "A.I가 추천해주는 내 취향 도서(책리스트 잘못됨)", "/categories", "randomBooks" },
											{ "신간도서", "/book/new", "newbooks" }, 
											{ "베스트 샐러(order순)", "/book/bestseller", "bestseller" },
											{ "이주의 특가 상품(책리스트 잘못됨)", "/categories", "randomBooks" }};
		if (authentication == null) {
			recommendationList = Arrays.copyOfRange(recommendationList, 1, recommendationList.length);
		}

		model.addAttribute("recommendationList", recommendationList);
		return "home";
	}

	// <div th:replace="commonspace :: menu" />에서는 먹히지 않음
	//	@GetMapping("/common")
	//	public String commonspace(Model model) {
	//		List<Branches> branchList = branchService.findAll();
	//		model.addAttribute("branchList", branchList);
	//		return "commonspace";
	//	}

	@GetMapping("/informationBranch/{id}")
	public String branchInformation(@PathVariable("id") Integer id, Model model) {
		Branches branchInformation = branchService.finById(id);
		List<Book> branchBooks = bookService.findByBranch(id);
		List<Category> listCategories = categoryService.findCategory();
		
		model.addAttribute("branchInformation", branchInformation);
		model.addAttribute("books", branchBooks);
		model.addAttribute("listCategories", listCategories);
		return "informationBranch";
	}


}
