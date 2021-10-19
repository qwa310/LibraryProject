## LMS (Library Management System) 도서 관리 시스템
- 오프라인 도서관과 같이 도서 검색, 대여, 반납, 연장, 연체 등의 기본 기능 설계 및 개발
- 오프라인 도서관의 기능을 사용자가 웹에서 직접 사용할 수 있도록 관리자가 아닌 사용자 중심 기능 구현
- MVC 구조 기반 개발 : 로직의 모듈화 및 재사용성 향상

### 
## LMS (Library Management System) 도서 관리 시스템
### ERD
###
![캡처](https://user-images.githubusercontent.com/46439700/137826263-0c4a0df1-46d1-48e3-9bb7-21f36dafd0c4.PNG)
### Data Flow
![image](https://user-images.githubusercontent.com/46439700/137826687-b7c5552c-9d5b-4ef4-ad72-da71200a9917.png)

### 기능
|기능|설명|기여자|
|:---:|:---:|:---:|
|회원가입, 로그인|Spring Security 를 사용한 인증 관리 및 비밀번호 암호화|[@최예준](http://www.google.co.kr)|
|회원가입, 로그인|Spring Security 를 사용한 인증 관리 및 비밀번호 암호화|[@최예준](http://www.google.co.kr)|
|마이 페이지|로그인 한 사용자의 회원정보 수정(Update) 및 도서 대출 내역, 도서 연체 연장 내역, 도서 알림 신청 내역을 DB 에서 확인(Select)하는 로직 개발|[@김상연](https://github.com/cafe9210)|
|도서 대여 기간 연장|로그인 한 사용자의 도서 대여 기간을 연장(Update) 하는 로직 개발|[@김상연](https://github.com/cafe9210)|
|도서 대여|로그인 한 사용자가 도서 대여를 하면 DB 에 대여 정보가 Insert 및 Update 되는 로직 개발|[@이우주](https://github.com/leewoojju)|
|웹 프론트엔드|HTML, CSS, JS 를 통해 웹 프론트엔드 개발|[@이우주](https://github.com/leewoojju)|
|도서 재고 알림 신청 및 이메일 전송|로그인 한 사용자에 한해 재고가 없는 도서를 알림 신청하고 재고가 발생하면 알림 신청한 사용자의 이메일로 알림 전송 (EmailJs 사용)|[@박정민](https://github.com/qwa310)|
|도서 반납|로그인 한 사용자가 도서 반납을 하고 DB 에 Update, Insert 되는 로직 개발|[@이태훈](https://github.com/taehoon95)|
|도서 검색|index 화면에서 사용자가 필터에 따라 도서 검색을 하면 DB에서 관련 도서를 Select 해와서 관련 결과를 띄워준다. 도서 상세 페이지로 넘어가는 로직 개발|[@이태훈](https://github.com/taehoon95)|

### 기술 스택
|사항|기술|
|:---:|:---:|
|FrontEnd|HTML, CSS , JS|
|BackEnd|SPRING BOOT, MYBATIS, Spring Security, MVC Pattern, Linux|
|Database|MYSQL, AWS RDS|
