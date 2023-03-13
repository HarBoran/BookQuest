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
		bookRepo.save(new Book("김유겸",".저자들은 자신의 전문 분야를 기반으로 수면이 우리 몸을 회복시키는 메커니즘을 설명하고, 궁극적으로 불면을 숙면으로 바꾸는 현실적인 방법들을 안내한다. 불면에 지친 독자들은 이 책을 통해 수면을 보다 잘 이해하고, 나아가 ‘꿀잠’의 기적을 경험하게 될 것이다.",0,"1.jpg",13500,"2023-03-13","위즈덤하우스","꿀잠의 과학",3,0));
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