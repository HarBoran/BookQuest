package com.example.demo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.entity.Book;
import com.example.demo.entity.Branchs;
import com.example.demo.entity.Category;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BranchRepository;

@DataJpaTest(showSql =true)
//데이터 베이스의 데이터가 더 우세 하니 바꾸지 말아라
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(false) // 에디터에서 데이터베이스로 데이터 넣기가 가능해짐
class BookQuestApplicationTests {

	@Autowired
	private BookRepository repo;
	
	@Autowired
	private BranchRepository BrRepo;
	
	@Test
	void saveNewBookInformation() {
		
		String dateString = "2023-02-28";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(dateString, formatter);
		
		Book book1 = new Book();
		book1.setTitle("title");
		book1.setAuthor("author");
		book1.setPublisher("publisher");
		book1.setPublicationDate(date);
		book1.setPrice(30000);
		book1.setImage("아직준비중");
		book1.setDescription("좋은 책입니다");
		book1.setCategory(new Category(1));		
		repo.save(book1);	
	}
	
	
	@Test
	void findBranchListAll() {
		
		List<Book> BookList = (List<Book>) repo.findAll();
		System.err.println(BookList);
	
		List<Branchs> brachList = (List<Branchs>) BrRepo.findAll();
		System.out.println(brachList);
		
	}
	
}
