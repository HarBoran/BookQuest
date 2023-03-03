package com.example.demo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping(value = { "/" }, method = { RequestMethod.GET, RequestMethod.POST })
public class HomeController {

	@Autowired
	BookService bookService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BranchService bracnchService;

	@GetMapping("")
	public String viewHomePage(Model theModel) {
		return "home";
	}
	
	//<div th:replace="commonspace :: menu" />에서는 먹히지 않음
	@GetMapping("/common")
	public String commonspace(Model theModel) {
		List<Branches> branchList = bracnchService.findAll();
		theModel.addAttribute("branchList", branchList);
		return "commonspace";
	}

	@GetMapping("/newBookRegistration")
	public String newBookregistration(Model theModel) {
		LocalDate now = LocalDate.now();
		Book registering = new Book();
		registering.setPublicationDate(now);
		theModel.addAttribute("registering", registering);

		List<Category> categoryList = categoryService.findCategory();
		theModel.addAttribute("categoryList", categoryList);

		return "newBookRegisteringAndRevising";
	}

	@GetMapping("/oldBookRegistration")
	public String oldBookregistration(Model theModel) {

		return "oldBookRegisteringAndRevising";
	}

	@GetMapping("/informationBranch/{id}")
	public String branchInformation(@PathVariable ("id") Integer id, Model theModel) {
		//List<Branchs> branchList = bracnchService.findAll();
		Branches branchInformation = bracnchService.finById(id);
		theModel.addAttribute("branchInformation", branchInformation);
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
