package com.example.demo.controller;

import java.io.IOException;
import java.time.LocalDate;
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
@RequestMapping(value = { "/" }, method = { RequestMethod.GET, RequestMethod.POST })
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

		String[][] recommendationList = { { "A.I가 추천해주는 내 취향 도서(책리스트 잘못됨)", "/categories", "randomBooks" },
											{ "신간도서", "/book/new", "newbooks" }, 
											{ "베스트 샐러(order순)", "/book/bestseller", "bestseller" },
											{ "이주의 특가 상품(책리스트 잘못됨)", "/categories", "randomBooks" } };
		if (authentication == null) {
			recommendationList = Arrays.copyOfRange(recommendationList, 1, recommendationList.length);
		}

		model.addAttribute("recommendationList", recommendationList);
		return "home";
	}

	// <div th:replace="commonspace :: menu" />에서는 먹히지 않음
	@GetMapping("/common")
	public String commonspace(Model model) {
		List<Branches> branchList = branchService.findAll();
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

	@PostMapping("/saveBookInformation")
	public String saveBookInformation(Book registering, @RequestParam("uploadBookCover") MultipartFile multipartFile,
			RedirectAttributes redirectAttributes) throws IOException {

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
		redirectAttributes.addFlashAttribute("message", "<" + registering.getTitle()+"> has been saved successfully.");
		return "redirect:/editBookInformation";

	}

	@GetMapping("/editBookInformation")
	public String adminBookListFirst(Model model) {
		return editBookInformation(1, "bookId", "asc", null, model);
	}

	@GetMapping("/editBookInformation/page/{pageNum}")
	public String editBookInformation(@PathVariable(name = "pageNum") int pageNum, @Param("sortField") String sortField,
			@Param("sortDir") String sortDir, @Param("keyword") String keyword, Model model) {
		// List<Book> listBooks = bookService.findAll();

		Page<Book> page = bookService.listByPage(pageNum, sortField, sortDir, keyword);
		List<Book> listBooks = page.getContent();
		model.addAttribute("books", listBooks);

		long startCount = (pageNum - 1) * bookService.USERS_PER_PAGE + 1;
		long endCount = startCount + bookService.USERS_PER_PAGE - 1;
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}

		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);

		final long PartPage = 5; // 보여줄 컨텐츠의 객수
		long totalPage = page.getTotalPages();

		long endPartPage = (long) Math.ceil((double) pageNum / PartPage) * PartPage;
		long startPartPage = endPartPage - PartPage + 1;
		if (endPartPage > totalPage) {
			endPartPage = totalPage;
		}

		model.addAttribute("startPartPage", startPartPage);
		model.addAttribute("endPartPage", endPartPage);
		model.addAttribute("totalPages", page.getTotalPages());

		List<Category> categoryList = categoryService.findCategory();
		model.addAttribute("categoryList", categoryList);
		return "editBookInformation";
	}

	@GetMapping("/editBookInformation/{bookId}")
	public String editBookInformation(@PathVariable("bookId") Integer bookId, RedirectAttributes redirectAttributes,
			Model model) {

		try {
			Book registering = bookService.findById(bookId).get();
			model.addAttribute("registering", registering);
		} catch (Exception e) {

			redirectAttributes.addFlashAttribute("messageNotFound", e.getMessage());
			return "redirect:/users/";
		}
		List<Category> categoryList = categoryService.findCategory();
		model.addAttribute("categoryList", categoryList);

		model.addAttribute("pageTilte", "Edit User (ID : " + bookId + ")");

		return "newBookRegisteringAndRevising";
	}

	@GetMapping("/deleteBookInformation/{bookId}")
	public String deleteBookInformation(@PathVariable("bookId") Integer bookId, RedirectAttributes redirectAttributes) {
		Book book = bookService.findById(bookId).get();
		try {
			bookService.deleteById(bookId);
			String uploadDir = "bookCover/" + book.getCategory().getName();
			FileUploadUtil.delete(uploadDir, book.getImage());
			redirectAttributes.addFlashAttribute("message","<" + book.getTitle() + "> has been deleted successfully.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/editBookInformation";
	}

	@GetMapping("/informationBranch/{id}")
	public String branchInformation(@PathVariable("id") Integer id, Model model) {
		Branches branchInformation = branchService.finById(id);
		model.addAttribute("branchInformation", branchInformation);
		return "informationBranch";
	}


	@RequestMapping(value = {"/checkDeliveryStatus"}, method = { RequestMethod.GET, RequestMethod.POST })
	public String checkDeliveryStatus(@RequestParam(required = false, name = "selectItem") Sales[] sales, Model model) {
		
		if(sales != null) {
			for(Sales sale : sales) {
				System.err.println(sale.getDeliveryStatus());	
				if(sale.getDeliveryStatus().equals("배송완료")) {
					sale.setDeliveryStatus("정산완료");
					salesService.save(sale);
					
					List<SalesDetail> salesDetailList = salesDetailService.findBySales(sale);
					for (SalesDetail salesDetail : salesDetailList) {
						BooksBranch booksBranch = new BooksBranch();
						booksBranch.setBranches(sale.getBranches());
						booksBranch.setBook(salesDetail.getBook());
						booksBranch.setStatus(salesDetail.getBookStatus());
						booksBranch.setQuantity(salesDetail.getSalesQuantity());
						booksBranchService.save(booksBranch);	
					}
					
				}
			}
		}
		
		List<Sales> salesList = salesService.findAll();
		model.addAttribute("salesList", salesList);
		for(Sales s : salesList) {
			for(SalesDetail sd : s.getSalesDetails()) {
				System.err.println(sd.getBook());
			}
		}
		List<BooksBranch> booksBranchList = booksBranchService.findAll();
		model.addAttribute("booksBranchList", booksBranchList);
		

		return "checkDeliveryStatus";
	}
	

}
