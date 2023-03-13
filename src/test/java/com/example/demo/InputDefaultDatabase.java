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
	BookRepository bookRepo;
	@Autowired
	CategoryRepository caRepo;
	@Autowired
	BranchRepository brRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	PaymentRepository payRepo;
	@Autowired
	CartRepository cartRepo;

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
		payment.setBank("국민은행");
		payment.setAccountNumber("1234-0456-7189-1005");
		payment.setUser(user);
		payRepo.save(payment);
	}
		
	@Test
	public void saveNewBook() {
		
		bookRepo.save(new Book("강영현","“주식의 시간은 따로 있다!”\r\n여의도 1타 브로커 강영현이 공개하는\r\n2023 혼돈의 시장을 돌파할 최강 투자 바이블!",0,"2.jpg",19800,"2023-03-13","21세기북스","살 때,팔 때,벌 때",4,0));
        bookRepo.save(new Book("박순혁","알만한 이들은 모두가 기다리고 고대하던\r\n박순혁 이사의 배터리 시장에 대한 단 한 권의 책!",0,"3.jpg",17100,"2023-01-13","지와인","K 배터리 레볼루션",4,0));
        bookRepo.save(new Book("신민철","“13년, 1만 시간을 담은 〈멘탈이 전부다〉 ‘처리형’의 첫 책!”",0,"4.jpg",18900,"2021-03-13","베가북스","돈의 규칙",4,0));
        bookRepo.save(new Book("정재호","중고가 수십만 원에 거래되는 전설의 투자서!\r\n대한민국 대표 주식 전문가 부자아빠 시세관의 정수",0,"5.jpg",19800,"2022-01-13","프런트페이지","주식 시세의 비밀",4,0));
        bookRepo.save(new Book("홍종호","“세상의 흐름을 통찰하고 싶은\r\n모든 청년들에게 일독을 권한다!”",0,"6.jpg",18000,"2022-02-13","다산북스","기후위기부의대전환",4,0));
        bookRepo.save(new Book("김민성","이 책에서 그는 어떻게 성공한 사람처럼 보이는지, 그리고 사람들의 마음을 어떤 식으로 사로잡는지에 대한 구체적인 방법을 다루고 있다.",0,"8.jpg",14220,"2021-06-13","마인드셋","지금 당장 포르쉐를 타라",4,0));
        bookRepo.save(new Book("최한철","월가에서 산전수전 겪은 ‘찐 전문가’의 이기는 투자론\r\n뼈아픈 손실을 겪고 다시 출발선에 선 당신을 위한 제2라운드 지침서",0,"9.jpg",16200,"2021-09-13","에프엔미디어","제2라운드투자수업",4,0));
        bookRepo.save(new Book("이승환","메타버스와 웹 3.0 시대, 돈의 흐름이 바뀐다!\r\n앞으로 10년, 디지털 변화의 큰 방향을 이해하고 투자전략을 세워라!",0,"디지털 부의 미래.jpg",17100,"2021-10-13","위너스북","디지털부의미래",4,0));
        bookRepo.save(new Book("문성후","“오래도록 존경받는 리더의 힘은 태도에서 나온다.”",0,"리더의태도.jpg",15750,"2023-01-13","카시오페아","리더의 태도",4,0));
        bookRepo.save(new Book("최인욱","10분기 연속 슈퍼호스트 선정!\r\n500명의 호스트를 코칭한 에어비앤비 공식 슈퍼호스트가 알려주는\r\n에어비앤비 시작과 운영의 모든 것",0,"돈이 되는 공간.jpg",15300,"2020-06-11","파지트","돈이 되는 공간",4,0));
        bookRepo.save(new Book("박정부","“천 원을 경영해야, 3조를 경영할 수 있다!”\r\n전국 1,500개 매장에 하루 100만 명이 찾는 국민가게 다이소,\r\n창업주 박정부 회장이 최초로 직접 밝힌 ‘천 원 경영’ 성공비결",0,"천 원을 경영하라.jpg",14400,"2013-05-13","쌤얀파커스","천 원을 경영하라",4,0));
        bookRepo.save(new Book("김승호","돈에 대한 기존의 생각을 과감히 수정하다!",0,"돈의 속성.jpg",16020,"2020-01-13","스노우폭스북스","돈의 속성",4,0));
        bookRepo.save(new Book("권은진","유튜브 리치맘 라이프의\r\n상위 1%를 만드는 부자 시스템",0,"부의삼각형.jpg",18000,"2022-03-15","북스고","부의 삼각형",4,0));
        bookRepo.save(new Book("배문성","부동산시장에 휘몰아친 고금리 리스크와\r\n유동성 위기를 어떻게 극복할 것인가?\r\n전례 없는 집값 상승과 하강, 그 변곡점의 순간에\r\n반드시 공부해야 할 자산수호 독법(讀法)!",0,"부동산을 공부할 결심.jpg",22500,"2023-03-13","어바웃어북","부동산을 공부할 결심",4,0));
        bookRepo.save(new Book("전형진","33만 구독자 〈집코노미TV〉 진행, 〈한국경제〉 부동산부 전형진 기자의 첫 책\r\n단 한 채의 집이 없는 당신에게 건네는 단 한 권의 부동산 입문서",0,"인생 첫 부동산 공부.jpg",17100,"2021-04-13","알에치코리아","인생 첫 부동산 공부",4,0));
        bookRepo.save(new Book("김성일","“시황 상관없이 저절로 성장하는\r\n시스템을 구축하라!”\r\n현금을 주식에 몰빵한 개미들을 위한 엑시트 시나리오",0,"마법의 투자 시나리오.jpg",18000,"2020-03-13","다산북스","마법의 투자 시나리오",4,0));
        bookRepo.save(new Book("강환국","게을러도, 바쁜 사람도 꾸준히 수익 내고 자산을 지켜주는 마음 편한 퀀트 투자법\r\n무작정 따라하기 X 퀀트 선구자 강환국이 뭉쳤다! 국내 최초 퀀트 투자 입문서",0,"퀸트 투자 무작정 따라하기.jpg",22500,"2023-03-13","길벗","퀸트투자무작정따라하기",4,0));
        bookRepo.save(new Book("고재홍","“이 책에서 많은 힌트를 얻었다”\r\n이건규, 강환국, 숙향 강력 추천!",0,"현명한 지표 투자.jpg",16920,"2023-01-13","아레미디어","현명한 지표 투자",4,0));
        bookRepo.save(new Book("박수준","투자자를 위한 업종별 투자 가이드",0,"대한민국 산업지도.jpg",19800,"2022-02-13","경이로움","대한민국 산업지도",4,0));
        bookRepo.save(new Book("김도영","네이버 브랜드 기획자가 안내하는\r\n지금 가장 특별한 브랜드들의 ‘관점과 태도’",0,"브랜드로부터 배웁니다.jpg",16200,"2021-03-23","위즈덤하우스","브랜드로부터 배웁니다",4,0));
        bookRepo.save(new Book("이형수","이 책은 발달재활서비스 제공인력 자격인정 및 특수교육에 입문하는 학습자에게 장애아동을 이해시키기 위한 입문서이다. ",0,"장애아동의 이해.jpg",27000,"2023-01-13","학지사메지컬","장애아동의 이해",8,0));
        bookRepo.save(new Book("진선희","근대적 아동문학으로서 동시가 어떤 이름과 모습으로 어떤 마음을 담아서 지어졌고 읽혔는지 어떻게 변화되었는지 구체적 작품을 읽어가며 찬찬히 살펴볼 수 있도록 정리하였다.",0,"일제강점기 동시 연구.jpg",21600,"2023-03-13","이어북스","일제강점기 동시 연구",8,0));
        bookRepo.save(new Book("한현지","전문적인 상담이란 문제가 발생했을 때 해결받기 원하는 내담자와 전문적인 과정을 거친 조력자 간에 신뢰를 바탕으로 바람직한 인간적인 성장과 변화를 위한 조력하는 과정입니다.",0,"상담이론.jpg",19800,"2023-03-14","동문사","상담이론",8,0));
        bookRepo.save(new Book("이현진","우리의 일상생활에서 과학과 기술이 관련되지 않은 부분은 거의 없다. 숨 쉬는 것을 의식하지 않고 생활하듯이",0,"유아과학교육.jpg",18000,"2023-02-23","공동체","유아과학교육",8,0));
        bookRepo.save(new Book("신미경","고교학점제라는 큰 변화 속에서 학생은 무엇을 알고 무엇을 준비해야 평생 살아갈 수 있는 역량을 키울 수 있을까?",0,"학생부의 품격.jpg",18000,"2023-01-15","교육과학사","학생부의 품격",8,0));
        bookRepo.save(new Book("윤정일","이 책은 교육행정학 원론에 대해 다룬 도서입니다. 기초적이고 전반적인 내용을 확인할 수 있도록 구성했습니다.",0,"교육행정학 원론.jpg",23000,"2021-09-30","학지사","교육행정학 원론",8,0));
        bookRepo.save(new Book("성태제","이 책은 최신교육학개론을 다룬 이론서입니다.",0,"최신교육학개론.jpg",20000,"2018-07-25","학지사","최신교육학개론",8,0));
        bookRepo.save(new Book("배식한","비판적 사고의 시대의 중요성을 설명하는 이론서 입니다.",0,"비판적 사고와 토론.jpg",14000,"2023-02-28","태학사","비판적 사고와 토론",8,0));
        bookRepo.save(new Book("윤가현","이 책은 심리학개론을 다룬 이론서입니다. 심리학개론의 기초적이고 전반적인 내용을 학습할 수 있습니다.",0,"심리학의 이해.jpg",22000,"2019-06-30","학지사","심리학의 이해",8,0));
        bookRepo.save(new Book("최정윤","『심리검사의 이해』는 임상심리학의 핵심을 이루고 있는 심리검사에 대한 전반적인 이해와 함께, 임상현장에서의 실제적인 적용에 초점을 두고 쓰여졌다.",0,"심리검사의 이해.jpg",20000,"2016-03-13","시그마프레스","심리검사의 이해",8,0));
        bookRepo.save(new Book("권석만","이 책은 인간관계 심리학을 다룬 이론서입니다. 인간관계 심리학의 기초적이고 전반적인 내용을 학습할 수 있습니다.",0,"인간관계의 심리학.jpg",20000,"2018-08-20","학지사","인간관계의 심리학",8,0));
        bookRepo.save(new Book("현성용","『현대 심리학의 이해』는 〈심리학이란 무엇인가〉, 〈행동의 신경과학 기초〉, 〈감각과 지각〉, 〈학습심리학〉 등 현대 심리학의 이해에 대한 기초적이고 전반적인 내용을 담고 있다.",0,"현대 심리학의 이해.jpg",24000,"2020-08-20","학지사","현대 심리학의 이해",8,0));
        bookRepo.save(new Book("윤희윤","이 책은 문헌정보를 다룬 이론서입니다. 문헌정보의 기초적이고 전반적인 내용을 학습할 수 있습니다.",0,"정보자료분류론.jpg",27000,"2020-02-20","태일사","정보자료분류론",8,0));
        bookRepo.save(new Book("황해익","『아동관찰 및 행동연구』는 〈아동관찰 및 행동연구의 기초〉, 〈아동관찰의 방법〉, 〈아동행동연구의 방법〉, 〈아동관찰 및 행동연구의 활용〉을 수록하고 있는 책이다.",0,"아동관찰 및 행동연구.jpg",18000,"2021-02-15","공동체","아동관찰 및 행동연구",8,0));
        bookRepo.save(new Book("김동일"," 이 책은 특수교육이론을 다룬 이론서입니다. 특수교육이론의 기초적이고 전반적인 내용을 학습할 수 있습니다.",0,"특수교육의 이해.jpg",20000,"2019-03-10","학지사","특수교육의 이해",8,0));
        bookRepo.save(new Book("권석만","『이상심리학의 기초』는 크게 12장으로 구성된 책이다. 이상심리학의 기본적 이해를 비롯하여, 불안장애, 강박장애, 외상후 스트레스 장애와 해리장애, 우울장애와 양극성 장애등을 분석한다",0,"이상심리학의 기초.jpg",20000,"2014-02-15","학지사","아동심리학의 기초",8,0));
        bookRepo.save(new Book("소경희","『교육과정의 이해』는 교육과정의 이해를 다룬 이론서이다. ",0,"교육과정의 이해.jpg",16000,"2017-03-30","교육과학사","교육과정의 이해",8,0));
        bookRepo.save(new Book("이미리"," 이 책은 청소년심리를 다룬 이론서입니다. 청소년심리의 기초적이고 전반적인 내용을 학습할 수 있습니다.",0,"청소년 심리 및 상담.jpg",20000,"2019-01-30","학지사","청소년 심리 및 상담",8,0));
        bookRepo.save(new Book("심현섭","이 책은 의사소통장애 이해에 대해 다룬 도서입니다. 의사소통장애 이해의 기초적이고 전반적인 내용을 확인할 수 있도록 구성했습니다.",0,"의사소통장애의 이해.jpg",21000,"2017-07-20","학지사","의사소통장애의 이해",8,0));
        bookRepo.save(new Book("박성익","이 책은 ‘교육방법의 교육공학적 이해(제5판)’의 내용을 시대적 학문 조류에 맞추어 전면적으로 개정하여, 제6판으로 발행하면서 책의 명칭도 새롭게 ‘교육공학과 수업’으로 변경하였다.",0,"교육공학과 수업.jpg",22000,"2021-06-25","교육과학사","교육공학과 수업",8,0));
        bookRepo.save(new Book("신민수","워싱턴에서 메르세데스 벤츠 최고 세일즈맨으로 자리 잡은 신민수의 이야기를 담은 『꿈을 파는 세일즈』. ",0,"꿈을 파는 세일즈.jpg",10800,"2012-03-23","청림출판","꿈을 파는 세일즈",22,0));
        bookRepo.save(new Book("이영헌","16년째 사비를 털어 지구를 열여섯 바퀴 돌며 250여 번 이상 강의를 해온 이영현 회장은 자신의 이야기를 통해 후배들에게 내리사랑을 실천하는 또 다른 누군가가 생겨나길 바라는 마음에 이 책을 제작했습니다.",0,"메이드 인 코리아.jpg",13500,"2019-07-15","성인당","메이드 인 코리아",22,0));
        bookRepo.save(new Book("이상진","『아들아 너만 봐라』는 유공, 다우케미칼 등에서 30여 년간 직장 생활을 한 저자가 아버지의 입장에서 월급쟁이가 되고자 하는 아들을 위해 정리한 ‘회사생활 노하우’이다. ",0,"아들아 너만 봐라.jpg",17000,"2015-09-25","나남","아들아 너만 봐라",22,0));
        bookRepo.save(new Book("권율","미국 CBS의 리얼리티 쇼 ‘서바이버’의 한인 우승자 권율의 에세이『나는 매일 진화한다』.",0,"나는 매일 진화한다.jpg",11700,"2012-05-24","중앙북스","나는 매일 진화한다",22,0));
        bookRepo.save(new Book("김해영","134센티미터 국제사회복지사 김해영의 희망 멘토링『청춘아, 가슴 뛰는 일을 찾아라』. 이 책은 남부아시아 부탄, 아프리카 보츠와나, 미국을 넘나들며 국제사회복지사로 활동하는 저자의 인생 이야기",0,"청춘아 가슴 뛰는 일을 찾아라.jpg",12800,"2012-02-29","서울문화사","가슴뛰는 일을 찾아라",22,0));
        bookRepo.save(new Book("김경수","청춘 멘토에게 길을 묻다『담 없는 소통』. 한국산업단지공단과 20명의 멘토가 지난 1년간 5개 대학을 다니며 취업과 진로 때문에 고민하는 대학생들과 만나 진솔한 이야기를 나누며 소통한 내용이다.",0,"담 없는 소통.jpg",13500,"2013-02-14","알에치코리아","담없는소통",22,0));
        bookRepo.save(new Book("한동현","청춘이 버려야 할 10가지『청춘 고민상담소』.",0,"청춘 고민상담소.jpg",14500,"2012-08-23","한동헌","청춘 고민상담소",22,0));
        bookRepo.save(new Book("문국현","유한킴벌리의 CEO를 역임한 문국현의 희망 편지, 『사람이 희망이다』. 유한킴벌리의 CEO로서, 34년간 New Way 운동을 통해 유한킴벌리를 가장 존경받는 기업이자 세계적 경쟁력을 갖춘 기업의 노하우",0,"사람이 희망이다.jpg",9000,"2007-09-15","웅진윙스","사람이희망이다",22,0));
        bookRepo.save(new Book("정병갑","힘들고 어려운 시기에 태어나서 여러 가지를 포기하면서 이 땅을 살아가는 20대 젊은이들이 이 책을 통해서 지혜를 배우고 삶의 철학을 깨달을 수 있기를 바라는 아버지의 마음으로 책을 쓰게 되었다",0,"아들아! 너는.jpg",15000,"2019-07-22","생각나눔","아들아! 너는",22,0));
        bookRepo.save(new Book("안창호 ","이다북스에서 도산 안창호의 글들을 묶은 《안창호의 말》을 출간했다. 이 책은 도산 안창호가 신문과 잡지에 게재한 글들 중 그의 민족정신이 잘 드러나는 한편 당시 젊은이들에게 호소한 것들을 모아놨다.",0,"안창호의 말.jpg",14000,"2020-08-20","이다북스","안창호의 말",22,0));
        bookRepo.save(new Book("김태진","광주에 자리잡은 청년들을 위한 공간, 동네줌인. 흰 도화지 같은 공간에는 청년들을 위해 두 팔 걷고 뛰고 있는 또 다른 청년이 있다. 바로 동네줌인의 김태진 대표다. ",0,"조금 다르게 살면 어때.jpg",13800,"2018-08-10","42미디어컨텐츠","조금 다르게 살면 어때",22,0));
        bookRepo.save(new Book("신영준","『졸업선물』은 공학박사이자 삼성디스플레이 책임연구원 출신인 저자가 갓 돌이 된 딸아이와 딸의 상사가 될 지금의 20대, 30대 젊은이들에게 선물하는 현실적인 사회생활 생존 팁을 담아낸 책",0,"졸업선물.jpg",13800,"2023-03-13","로크미디어","졸업선물",22,0));          
        bookRepo.save(new Book("이원희","“시베리아에 발가벗고 나갈래?” 현직 진로 지도교수가 대학생에게 전해주는, 내 삶을 ‘주인공’으로 사는 법!",0,"대학생,진로와 마주하다.jpg",15000,"2018-02-26","행복에너지","대학생,진로와 마주하다",22,0));
        bookRepo.save(new Book("이어령","시대의 지성, 젊은이의 영원한 멘토 이어령의『젊음의 탄생』. 60년 가까운 시간동안 한국문단의 최전선에서 펜을 놓지 않고 끊임없이 창조 생활을 계속 해온 저자 이어령이 방황하는 젊은이들에게 바치는 책",0,"젊음의 탄생.jpg",13500,"2013-09-10","마로니에북스","젊음의 탄생",22,0));
        bookRepo.save(new Book("유홍준","『새내기 멘토링』은 대학교 신입생들을 위한 조언을 담은 책이다. 합격 후 개강준비에서부터 대학생활의 시작, 대인관계, 똑똑한 학업관리, 수업과 과제, 시험, 앞으로의 진로에 대한 조언을 담고 있다.",0,"새내기 멘토링.jpg",13000,"2015-08-13","그린","새내기 멘토링",22,0));
        bookRepo.save(new Book("이서진","많은 예체능 계열의 대학생들은 앞으로의 미래나 졸업 후의 진로에 대하여 수많은 고민을 한다. 물론 다른 대학생들도 많은 고민을 하겠지만, 예체능 계열 대학생의 경우 현재 자신이 하고 있는 전공의 이해도를 높일 필요가 있다.",0,"꿈을 찾는 음대생.jpg",18000,"2018-11-01","렛츠북","꿈을 찾는 음대생",22,0));
        bookRepo.save(new Book("채현국","『쓴맛이 사는 맛』에서 채현국이라는 ‘어른’의 등장은 청년들에게 깊은 울림으로 다가왔다. 기존에 알고 있던 ‘꼰대’의 모습이 아닌, 진심으로 존경하고 따를 수 있는 어른의 자세를 보여주었기 때문이다.",0,"쓴맛이 사는 맛.jpg",13000,"2015-02-27","바이북","쓴맛이 사는 맛",22,0));
        bookRepo.save(new Book("강신주","‘이태백’, ‘청년 실신’, ‘삼포 세대’를 넘어 이제는 무려 ‘칠포 세대’가 되어버린 20대를 아십니까? 과거 꿈과 희망의 아이콘이었던 20대는 사라지고, 그 자리를 결핍과 좌절, 불안과 우울 같은 부정적인 것을 해결할 수 있는 방법을 알려드립니다!",0,"스무 살의 인문학.jpg",15000,"2015-09-25","이학사","스무 살의 인문학",22,0));
        bookRepo.save(new Book("최진호","일, 사랑, 행복에 대한 이야기를 담은 『스무 살의 인생설계』. 이 책은 저출산, 고령화 심화로 이어지는 젊은이들의 팍팍한 삶에 위로를 전해주는 내용을 담은 책입니다.",0,"스무 살의 인생설계.jpg",16000,"2012-08-30","나남","스무 살의 인생설계",22,0));
        bookRepo.save(new Book("고영재","[셋업]은 자신의 인생을 스스로 설계하고 만들어 가기 위해 무엇을 어떻게 해야 하는지가 통합적이고 단계적으로 정리되어 있다.",0,"셋업.jpg",13000,"2023-03-13","인스피레이션","셋업",22,0));
        bookRepo.save(new Book("곽상빈","『공부법 다 똑같다』는 어떤 한 가지 공부방법이 옳다고 주장하고 있는 다양한 공부법 서적들에서 한 발짝 물러나서 객관적으로 자신이 어떤 방식으로 공부해야 시험에 합격하는지 알려주는 책!",0,"공부법 다 똑같다.jpg",15000,"2018-01-20","법를저널","공부법 다 똑같다",22,0));
        bookRepo.save(new Book("김은주","스물일곱 살에 영어 한마디 제대로 못 하고 아무런 준비 없이 미국 유학길에 올랐던 저자가 25년간 CJ, 삼성전자, 마이크로소프트, 퀄컴, 모토로라 등을 거쳐 구글 본사의 수석 디자이너가 되기까지의 길을 소개합니다.",0,"생각이 너무 많은 서른 살에게.jpg",16000,"2021-06-10","메이븐","생각이 너무 많은 서른살",22,0));
        bookRepo.save(new Book("김재우","이 책에는 ‘뚜벅뚜벅’ 걸으며 마주하는 지나간 어제와 다가올 내일의 이야기, 여행의 매력에 ‘풍덩’ 빠져 낯선 곳에서 새로움을 발견하는 경험, ‘푸릇푸릇’ 초록 세상에서 새삼 느껴보는 자연의 아름다움!",0,"재우쌤의 창의여행.jpg",16000,"2023-01-10","비상교육","재우쌤의 창의여행",15,0));
        bookRepo.save(new Book("정꽃나래","1시간 이면 도착하는 가까운 거리, 다양한 먹거리와 즐길 거리까지, 관광, 미식, 쇼핑 3박자를 모두 갖추고 있어 많은 사랑을 받는 한국인의 영원한 스테디셀러 여행지, 후쿠오카!",0,"프렌즈 후쿠오카.jpg",17000,"2023-02-16","중앙북스","프렌즈 후쿠오카",15,0));
        bookRepo.save(new Book("안진현","전통과 현대가 오묘하게 어우러진 동남아시아 No.1 인기 여행지 방콕! 사람 냄새나는 골목부터 찬란한 전통과 문화유산, 휘황찬란한 야경이 어른거리는 짜오프라야 강과 고풍스러운 호텔 & 리조트가 가득한곳 !",0,"프렌즈 방콕.jpg",23000,"2023-02-06","중앙북스","프렌즈 방콕",15,0));
        bookRepo.save(new Book("맹지나","『그리스 블루스』는 한 달이라는 짧지 않은 시간 동안 잿빛 서울을 떠나 파란 그리스를 여행하며 자연과 자유를 만끽한 작가는 직접 만난 각 섬 고유의 풍경을 세밀화로 그려낸 책이다.",0,"그리스 블루스.jpg",36000,"2023-01-31","아담북스","그리스 블루스",15,0));
        bookRepo.save(new Book("배영길","국립대학교 법학과 교수 출신인 저자 배영길이 60대 후반에 정년퇴직을 하면서 본격적으로 시작한 여행 이야기다. ",0,"여행은60부터.jpg",20000,"2022-12-15","하늘책","여행은 60부터",15,0));
        bookRepo.save(new Book("남원상","거창한 목적의 여행이 아니라, 그저 핑곗김에 떠나고 소소한 이유만으로도 숙소를 고르고, 음식을 먹고, 사진을 찍고, 또 반려 동물과 함께한 여행의 작고도 찬란한 기록들.",0,"여행의 핑계.jpg",15000,"2023-02-20","따비","여행의 핑계",15,0));
        bookRepo.save(new Book("박정연","V 도쿄 현지에서 생활하는 한국인과 일본인 남편이 전하는 생생한 정보! V 도쿄 구석구석, 44개 지역을 상세하게 소개! V 지역별 상세지도로 여행지 위치 쉽게 파악! V 머리 아픈 일정 짜기를 손쉽게알려드립니다.",0,"도쿄 여행백서.jpg",20000,"2023-02-15","나무자전거","도쿄 여행백서",15,0));
        bookRepo.save(new Book("신병준","쿠바 여행을 떠날 생각을 하는 이에게 텔레비전 여행 프로그램에서 쿠바가 자주 소개되어도, 아직 쿠바는 한국에서 거리뿐만 아니라 정서적으로도 왠지 먼 나라로 느껴지는 쿠바를 쉽게 소개합니다!",0,"비바,쿠바.jpg",16500,"2023-02-15","하모니북","비바,쿠바",15,0));
        bookRepo.save(new Book("조대현","10년의 시간 이제 아이슬란드 가이드북을 대한민국에서 처음으로 출간한지 10년이 되었다. 그동안 수도 없이 아이슬란드를 들락거렸고 아이슬란드 가이드북은 지속적으로 성장해 너무나도 많은 사랑을 받아왔습니다.",0,"아이슬란드.jpg",17900,"2023-03-13","해시태그","아이슬란드",15,0));
        bookRepo.save(new Book("김치중","문화감성기행 책 한 권 들고 떠나는 여행 이 책은 우리나라의 고향 같은 곳을 찾아 이야기를 담은 여행 수필집입니다.",0,"책 한 권 들고 떠나는 여행.jpg",16000,"2023-02-20","글촌","책 한권 들고 떠나는 여행",15,0));
        bookRepo.save(new Book("김홍래","한 번 가보면 계속 생각나고 곧바로 다시 가고 싶은 여행지가 있다. 몇 번을 여행해도 금세 그리워지는 도시. ",0,"리얼 타이베이.jpg",19800,"2023-03-03","한빛라이프","리얼 타이베이",15,0));
        bookRepo.save(new Book("오남수","미국 여행을 간다면 어느 시기에, 어디로 가면 좋을까? 저자는 망설임 없이 9월 초순에서 10월 중하순, 요세미티 국립공원, 몬태나, 소노마, 힐즈버그, 멘도시노 등에 가라며 명소부터 생소한 지명까지 가 보았습니다 이 경험을 토대로 여러분들께 여행지를 소개해 드립니다! ",0,"다시,서쪽으로간다.jpg",18000,"2023-03-05","브레드","다시,서쪽으로 간다",15,0));
        bookRepo.save(new Book("정꽃나래","휴가가 짧아도, 여행 준비 기간이 부족해도, 두꺼운 책이 부담스러워도, 걱정하지 말자 우리에겐 〈베스트 프렌즈〉가 있으니까! 여행 가이드북의 정석!!",0,"베스트프렌드 도쿄.jpg",13000,"2023-03-06","중앙북스","베스트 프렌즈 도쿄",15,0));
        bookRepo.save(new Book("김성균","지리산은 이 땅의 모든 산 가운데 어머니 산이라고 불린다. 깊은 골 첩첩산중에 바위로 이루어진 삭막한 산이 아니라 깊은 산중에도 농사를 지을 수 있는, 생명을 일구는 땅이기 때문이다.",0,"지리산에 길을 묻다.jpg",40000,"2023-02-28","이담북스","지리산에 길을 묻다",15,0));
        bookRepo.save(new Book("박진주","싱가포르의 상징이자 핵심 관광 도시 마리나 베이, 화려한 쇼핑의 메카 오차드 로드, 세계적인 테마파크가 있는 센토사, 다민족 문화가 살아 숨 쉬는 차이나타운, 나이트라이프의 중심지 리버사이드 등을 느껴보세요!",0,"프렌즈 싱가포르.jpg",20000,"2023-03-06","중앙북스","프렌즈 싱가포르",15,0));
        bookRepo.save(new Book("마연희","해변을 따라 늘어선 고급 리조트와 풀빌라 싱싱한 해산물과 여행의 피로를 풀어 주는 타이 마사지 아름다운 미소가 반짝이는 푸껫! 알찬 정보와 생생한 현지 사진, 세련된 디자인과 구성으로 사랑받아온 푸켓을 소개합니다!",0,"인조이푸껫.jpg",16000,"2023-03-10","넥서스 북스","인조이 푸껫",15,0));
        bookRepo.save(new Book("김준엽","헌법이 보장하고 있는 ‘시민의 행복추구권이 무럭무럭 자라 꽃처럼 형상화된 것’이 여행이다. 모든 시민은 누구나 행복을 위해 여행을 꿈꾸고 준비한다.",0,"시민을 위한 여행.jpg",17000,"2023-03-08","이로츠","시민을 위한 여행",15,0));
        bookRepo.save(new Book("김진애","도시건축가로서의 경험과 통찰을 바탕으로 사회 변화를 위한 정치활동에 매진해온 김진애가 인생의 시간을 풍성하게 만들어주는 여행의 비법을 들려주는 『여행의 시간』으로 독자들을 만난다.",0,"여행의 시간.jpg",18000,"2023-03-03","창비","여행의 시간",15,0));
        bookRepo.save(new Book("김한송","다음 중 국가와 그 수도가 잘못 이어진 것은?”이라는 문제에 여전히 단골로 등장하는 도시 뉴욕. 미국이라는 나라의 수도는 워싱턴DC이지만, 뉴욕이 미국 경제와 문화의 중심이라는 데 이의를 제기하는 사람은 없을 겁니다.",0,"뉴욕을 먹다.jpg",18000,"2023-03-10","따비","뉴욕을 먹다",15,0));
        bookRepo.save(new Book("맹가희","숙소는 단순히 잠만 자는 곳일 수도 있고, 예상보다 많은 시간을 할애하는 곳이 될 수도 있습니다. 기대를 충족시켜 주기도 하고, 기대에 한참 미치지 못하기도 하지요. 예기치 못했던 인연을 만들기도 할 수도 있는 숙소에 대해 알아봐요!",0,"잘 들렸다 갑니다.jpg",18800,"2023-03-13","하모니북","잘 들렸다 갑니다",15,0));
        bookRepo.save(new Book("임윤정","코로나19 팬데믹으로 지난 3년간 굳게 닫혔던 여행의 문이 열린 지금, 해외여행은 빠른 속도로 회복세를 보이는 상황이다. 이러한 시기에 출간된 『잠시, 다녀왔습니다』는 ‘아, 여행이 이런 것이었지!’ 를 일깨워 줍니다.",0,"잠시 다녀왔습니다.jpg",16800,"2023-03-01","비즈토크북","잠시,다녀왔습니다",15,0));
        bookRepo.save(new Book("최용범","연도와 사건을 암기하는 한국사가 아닌 배경과 흐름을 이해하는 한국사 『하룻밤에 읽는 한국사』. 역사학계의 성과를 반영하여 달라진 내용을 바로잡았습니다.",0,"하룻밤에 읽는 한국사.jpg",16800,"2019-09-19","페이퍼로드","하룻밤에 읽는 한국사",16,0));
        bookRepo.save(new Book("이황","이 책은 일반 독자층을 대상으로 한 책으로, 전문적인 내용을 비전공인 일반 독자들도 쉽게 이해할 수 있도록 풀어쓴 교양 도서이다.",0,"퇴계선집.jpg",18000,"2011-11-25","현암사","퇴계선집",16,0));
        bookRepo.save(new Book("조한경","『한 컷 한국사』를 집필한 열 명의 역사 교사들은 고등학교 한국사 교과서를 공동 집필한 경험이 있다. 집필진은 한 컷의 역사 사진에 담겨 있는 시대상을 역사 교사의 시선으로 풀어쓴 책이 있으면 좋을거 같아서 집필하였다.",0,"한컷한국사.jpg",20000,"2022-05-30","해냄에듀","한 컷 한국사",16,0));
        bookRepo.save(new Book("강문종","조선 좀비물’로 인기를 끌었던 화제의 드라마 〈킹덤〉에서 주인공 세자 못지않은 무술 기량을 뽐냈던 ‘영신’\r\n그의 이야기를 들어보자",0,"조선잡사.jpg",18000,"2020-10-23","민음사","조선잡사",16,0));
        bookRepo.save(new Book("박영규","017년 전면개정판『한 권으로 읽는 조선왕조실록』은 더 쉬운 이해를 위해 간결하고 흥미로운「예비지식」을 덧붙였으며, 「숙종실록」의 내용을 보완했다",0,"한 권으로 읽는 조선왕조실록.jpg",20000,"2017-02-10","웅진지식하우스","조선왕조실록",16,0));
        bookRepo.save(new Book("강명관","넓고 깊고 촘촘한 강명관 표 ‘역사 그물’ 역사를 읽는 방법은 다양하다. 왕조를 중심으로 시대를 구분하기도 하고, 인물이나 사건의 추이를 따라 파악하기도 하는 식이다. 이 중 키워드를 중심으로 역사를 알아보자.",0,"노비와 쇠고기.jpg",39000,"2023-02-28","푸른역사","노비와 쇠고기",16,0));
        bookRepo.save(new Book("강부원","격동의 20세기 한국, 시대를 이끈 선도자와 방향을 제시한 지도자가 무수히 이름을 날렸다. 그들은 일평생 부귀와 영달을 누렸다. 하지만 선도자와 지도자만 20세기 한국을 수놓지 않았다. 자신만으 방법으로 역사에 맞선 사람들을 알아보자",0,"역사에 불꽃처럼 맞선 자들.jpg",17000,"2022-05-18","믹스커피","역사에 불꽃처럼 맞선자들",16,0));
        bookRepo.save(new Book("김재원","《세상에서 가장 짧은 한국사》는 고대부터 근현대까지 한국사의 가장 중요한 순간들을 소개하면서, 단 한 권으로 역사의 흐름을 단숨에 파악할 수 있도록 돕는다.",0,"세상에서 가장 짧은 한국사.jpg",17800,"2023-06-15","빅피시","세상에서 가장  짧은한국사",16,0));
        bookRepo.save(new Book("김구","전문연구자의 원전비평에 기초한 정본『백범일지』. 백범 김구의 자서전으로 진솔하고 감동적인 기록을 담았다.",0,"백범일지.jpg",12000,"2005-11-05","돌배게","백범일지",16,0));
        bookRepo.save(new Book("임치균","한류 열풍의 원조로 꼽히는 드라마 「대장금」의 주인공 서장금. 『조선왕조실록』에 실린 의녀 장금의 기록에서 탄생한 캐릭터다. 같은 이름의 소설이 원작인 드라마 「옷소매 붉은 끝동」의 주인공이 되보자",0,"조선의 걸 크러쉬.jpg",19000,"2023-02-24","민음사","조선의 걸 크러쉬",16,0));
        bookRepo.save(new Book("노기섭","이 책은 컴퓨팅사고를 다룬 이론서입니다. 컴퓨팅사고의 기초적이고 전반적인 내용을 학습할 수 있습니다.",0,"컴퓨팅사고.jpg",19000,"2020-02-28","그린","컴퓨팅사고",29,0));
        bookRepo.save(new Book("안기중"," 언택트 문화의 핵심이 바로 디지털 시대의 대명사인 IT 혹은 ICT기술이며 이에 대한 성공적인 전환이 향후 우리의 생존을 좌우하는 중요한 요소일 것입니다. 본서는 이러한 사회변화에 능동적으로 대응할 수 있도록 필요한 기본적 ICT지식을 제공합니다.",0,"IT와 컴퓨터의 이해.jpg",24000,"2020-08-31","문웅당","IT와 컴퓨터의 이해",29,0));
        bookRepo.save(new Book("김대수","『컴퓨터 개론』 은 〈새로운 기술로 나날이 변화하는 세상〉, 〈디지털 혁명과 정보화 사회〉, 〈컴퓨터의 활용 분야〉, 〈컴퓨터 환경의 단계적 발전 전망〉에 대해 설명하는 책입니다.",0,"컴퓨터 개론.jpg",27000,"2020-12-02","생능출판","컴퓨터 개론",29,0));
        bookRepo.save(new Book("김명주","- 컴퓨터와 정보통신 학습을 위한 완벽한 입문서 - 하드웨어, 인터넷, 정보보안, 모바일, 멀티미디어 등의 기본 이론 수록 - 빅데이터, 인공지능 등 최신 기술부터 엣지 컴퓨팅, 데이터 사이언스 등 미래기술들에 대해 서술했습니다.",0,"컴퓨터의 이해.jpg",25000,"2021-03-01","이한미디어","컴퓨터의 이해",29,0));
        bookRepo.save(new Book("유영한","『컴퓨팅 사고와 인공지능』은 〈컴퓨팅사고와 프로그래밍〉, 〈문제해결 전략〉, 〈빅데이터와 인공지능〉등을 수록하고 있는 책이다.",0,"컴퓨팅 사고와 인공지능.jpg",19000,"2023-03-13","부산대학교출판문화원","컴퓨팅 사고와 인공지능",29,0));
        bookRepo.save(new Book("추정호","『금붕어의 양자컴퓨터 이야기』는 〈고전 컴퓨터의 원리〉, 〈양자 물리 소개〉, 〈양자물리에서의 수학〉, 〈양자 컴퓨터의 원리〉를 수록하고 있는 책이다.",0,"금붕어의 양자컴퓨터 이야기.jpg",15000,"2021-04-20","서울경제경영","양자컴퓨터 이야기",29,0));
        bookRepo.save(new Book("김대수"," 인공지능 기술, 디지털 혁명을 비롯한 컴퓨터와 관련된 전반적인 이슈들을 개괄적으로 다루고, 4차 산업혁명 시대의 분야 등을 간략하게 다루는 책 입니다.",0,"인공지능 시대의 컴퓨터 개론.jpg",28000,"2022-12-27","생능출판","인공지능 컴퓨터 개론",29,0));
        bookRepo.save(new Book("박동규","이 책은 컴퓨터 과학과 코딩에 대하여 배우고자 하는 이들이 반드시 알아야 할 주제를 담고 있다.",0,"으뜸 컴퓨터 개론.jpg",28000,"2023-01-16","생능출판","으뜸 컴퓨터 개론",29,0));
        bookRepo.save(new Book("이민철","이 책은 양자역학을 다룬 이론서이다. 양자컴퓨팅의 기초적이고 전반적인 내용을 학습할 수 있도록 구성하였다.",0,"양자컴퓨팅.jpg",45000,"2023-03-13","청범출판사","양자컴퓨팅",29,0));
        bookRepo.save(new Book("안성진","이 책은 컴퓨터공학을 다룬 이론서입니다.",0,"컴퓨터처럼 생각하기.jpg",18000,"2019-03-01","이한미디어","컴퓨터처럼 생각하기",29,0));

//		LocalDate nowDate = LocalDate.now();
//		String[] testImages = {"뉴진스.jpeg","뉴진스페페1.png","다니엘.jpeg","다니엘2.jpeg","민지.jpeg",
//								"하니.jpeg","하니2.jpeg","해린.jpeg","해린2.jpeg","혜인.jpeg"};
//		for(int i =0; i<testImages.length; i++) {
//			Book book = new Book();
//			book.setTitle(testImages[i].substring(0,testImages[i].length()-5));
//			book.setAuthor("author");
//			book.setPublisher("publisher");
//			book.setPublicationDate(nowDate);
//			book.setPrice(29000 + i*100);
//			book.setDescription("테스트 코드에서 입력 되었습니다.");
//			book.setCategory(new Category(3));
//			book.setImage(testImages[i]);
//			bookRepo.save(book);
//		}

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