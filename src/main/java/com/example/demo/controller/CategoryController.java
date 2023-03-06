package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;

@Controller
@RequestMapping(value = "/categories", method = { RequestMethod.GET, RequestMethod.POST })
public class CategoryController {
	@Autowired
	private BookService bookservice;

	@Autowired
	CategoryService categoryService;

	@GetMapping("")
	public String listAll(Model model) {
		List<Category> listCategories = categoryService.findCategory();
		model.addAttribute("listCategories", listCategories);
		List<Book> books = bookservice.findAll();
		model.addAttribute("books", books);

		return "category";
	}

}