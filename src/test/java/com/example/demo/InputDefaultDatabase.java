package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.example.demo.entity.Book;
import com.example.demo.entity.Branches;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Category;
import com.example.demo.entity.Payment;
import com.example.demo.entity.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BranchRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.UserRepository;

@DataJpaTest(showSql = false)
//데이터 베이스의 데이터가 더 우세 하니 바꾸지 말아라
@AutoConfigureTestDatabase(replace = Replace.NONE)
//에디터에서 데이터베이스로 데이터 넣기가 가능해짐
@Rollback(false)
public class InputDefaultDatabase {

	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private CategoryRepository caRepo;
	@Autowired
	private BranchRepository brRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PaymentRepository payRepo;
	@Autowired
	private CartRepository cartRepo;

	@Test
	void CreateDefaultDatabase() {

		Category category000 = new Category("국내도서");
		caRepo.save(category000);
		Category category100 = new Category("외국도서");
		caRepo.save(category100);

		caRepo.save(new Category("건강/취미", category000));
		caRepo.save(new Category("경제경영", category000));
		caRepo.save(new Category("공무원 수험서", category000));
		caRepo.save(new Category("과학", category000));
		caRepo.save(new Category("달력/기타", category000));
		caRepo.save(new Category("대학교재", category000));
		caRepo.save(new Category("만화", category000));
		caRepo.save(new Category("사회과학", category000));
		caRepo.save(new Category("소설/시/희곡", category000));
		caRepo.save(new Category("수험서/자격증", category000));
		caRepo.save(new Category("어린이", category000));
		caRepo.save(new Category("에세이", category000));
		caRepo.save(new Category("여행", category000));
		caRepo.save(new Category("역사", category000));
		caRepo.save(new Category("예술/대중문화", category000));
		caRepo.save(new Category("외국어", category000));
		caRepo.save(new Category("요리/살림", category000));
		caRepo.save(new Category("유아", category000));
		caRepo.save(new Category("인문확", category000));
		caRepo.save(new Category("자기계발", category000));
		caRepo.save(new Category("잡지", category000));
		caRepo.save(new Category("장르소설", category000));
		caRepo.save(new Category("전집/중고전집", category000));
		caRepo.save(new Category("종교/역학", category000));
		caRepo.save(new Category("좋은부모", category000));
		caRepo.save(new Category("청소년", category000));
		caRepo.save(new Category("컴퓨터/모바일", category000));
		caRepo.save(new Category("초등학교참고서", category000));
		caRepo.save(new Category("중학교참고서", category000));
		caRepo.save(new Category("고등학교참고서", category000));

		caRepo.save(new Category("영미도서", category100));
		caRepo.save(new Category("ETL/어학/사전", category100));
		caRepo.save(new Category("건강/스포츠", category100));
		caRepo.save(new Category("경제경영", category100));
		caRepo.save(new Category("공예/취미/수집", category100));
		caRepo.save(new Category("만화", category100));
		caRepo.save(new Category("소설/시/희곡", category100));
		caRepo.save(new Category("여행", category100));
		caRepo.save(new Category("역사", category100));
		caRepo.save(new Category("요리", category100));
		caRepo.save(new Category("인문/사회", category100));
		caRepo.save(new Category("종교/명상/점술", category100));
		caRepo.save(new Category("청소년", category100));
		caRepo.save(new Category("해외잡지", category100));
		caRepo.save(new Category("대학교재/전문서", category100));
		caRepo.save(new Category("건축/디자인", category100));
		caRepo.save(new Category("교육/자료", category100));
		caRepo.save(new Category("기술공학", category100));
		caRepo.save(new Category("법률", category100));
		caRepo.save(new Category("수험서", category100));
		caRepo.save(new Category("언어학", category100));
		caRepo.save(new Category("예술/대중문화", category100));
		caRepo.save(new Category("의학", category100));
		caRepo.save(new Category("자연과학", category100));
		caRepo.save(new Category("컴퓨터", category100));
		caRepo.save(new Category("기타언어권", category100));
		caRepo.save(new Category("독일 도서", category100));
		caRepo.save(new Category("스페인 도서", category100));
		caRepo.save(new Category("중국 도서", category100));
		caRepo.save(new Category("어린이", category100));
		caRepo.save(new Category("그림책", category100));
		caRepo.save(new Category("동화책", category100));
		caRepo.save(new Category("리더스", category100));
		caRepo.save(new Category("영어학습", category100));
		caRepo.save(new Category("챕터북", category100));
		caRepo.save(new Category("코스북", category100));

		brRepo.save(new Branches("BookQuest 강남점", "서울 강남구 강남대로 388", 37.4974321151032, 127.02838169552845));
		brRepo.save(new Branches("BookQuest 시청점", "서울 중구 세종대로 지하 101", 37.564663964738195, 126.978106746564));
		brRepo.save(new Branches("BookQuest 노량진점", "서울 동작구 노량진로 138", 37.51317948453074, 126.94122547645269));
		brRepo.save(new Branches("BookQuest 부산 W스퀘어점", "부산 남구 분포로 145 더블유스퀘어동", 35.13356779884351, 129.11356988948617));

	}

	@Test
	public void saveNewUser() {

		User user = new User();
		user.setEmail("1");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode("1"));
		user.setAddress("주소");
		user.setName("이름");
		user.setPhone("010전화번호");
		user.setPhoto("프로필사진");
		user.setRole("admin");
		user.setEnabled(true);
		user.setSignupDate(LocalDateTime.now());
		userRepo.save(user);

		Payment payment = new Payment();
		payment.setCardName("테스트용 결제수단");
		payment.setCardNumber("1234-0456-7189-1005");
		payment.setUser(user);
		payRepo.save(payment);
	}
		
	@Test
	public void saveNewBook() {
		LocalDate nowDate = LocalDate.now();
		String[] testImages = {"뉴진스.jpeg","뉴진스페페1.png","다니엘.jpeg","다니엘2.jpeg","민지.jpeg",
								"하니.jpeg","하니2.jpeg","해린.jpeg","해린2.jpeg","혜인.jpeg"};
		for(int i =0; i<testImages.length; i++) {
			Book book = new Book();
			book.setTitle(testImages[i].substring(0,testImages[i].length()-5));
			book.setAuthor("author");
			book.setPublisher("publisher");
			book.setPublicationDate(nowDate);
			book.setPrice(29000 + i*100);
			book.setDescription("테스트 코드에서 입력 되었습니다.");
			book.setCategory(new Category(3));
			book.setImage(testImages[i]);
			bookRepo.save(book);
		}

	}
	
//	@Test
//	public void saveNewCart() {
//		Book book = new Book(1);
//		User user = new User(1);
//		for(int i =0; i < 3; i++) {
//			Cart cart = new Cart();
//			cart.setBookQuantity(1);
//			cart.setBook(book);
//			cart.setUser(user);
//			cartRepo.save(cart);
//		}
//	}

}