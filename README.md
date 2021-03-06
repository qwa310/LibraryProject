## LMS (Library Management System) 도서 관리 시스템
- 오프라인 도서관과 같이 도서 검색, 대여, 반납, 연장, 연체 등의 기본 기능 설계 및 개발
- 오프라인 도서관의 기능을 사용자가 웹에서 직접 사용할 수 있도록 관리자가 아닌 사용자 중심 기능 구현
- MVC 구조 기반 개발 : 로직의 모듈화 및 재사용성 향상

### 
## LMS (Library Management System) 도서 관리 시스템

```java spring boot
Application.java 에서 실행
```
### ERD
###
![캡처](https://user-images.githubusercontent.com/46439700/137826263-0c4a0df1-46d1-48e3-9bb7-21f36dafd0c4.PNG)
### 
### Data Flow
![image](https://user-images.githubusercontent.com/46439700/137826687-b7c5552c-9d5b-4ef4-ad72-da71200a9917.png)
### 
### 기능
|기능|설명|기여자|
|:---:|:---:|:----:|
|회원가입,</br>로그인,</br>관리자페이지|Spring Security 를 사용한 인증 관리 및 비밀번호 암호화,</br>관리자로 로그인 할 시, 도서 추가, 수정, 삭제 및 회원 정보 삭제를 수행할 수</br> 있는 페이지를 만들고 DB 에 Delete, Update, Insert, Update를 할 수 있는 로직 개발|[@최예준](http://www.google.co.kr)|
|마이페이지,</br>도서대여기간연장|로그인 한 사용자의 회원정보 수정(Update) 및 도서 대출 내역, 도서 연체 연장 내역,</br>도서 알림 신청 내역을 DB 에서 확인(Select)하는 로직 개발,</br>로그인 한 사용자의 도서 대여 기간을 연장(Update) 하는 로직 개발|[@김상연](https://github.com/cafe9210)|
|도서대여,</br>웹프론트엔드|로그인 한 사용자가 도서 대여를 하면 DB 에 대여 정보가 Insert 및 Update 되는 로직 개발,</br>HTML, CSS, JS 를 통해 웹 프론트엔드 개발|[@이우주](https://github.com/leewoojju)|
|도서재고알림신청</br>이메일전송|로그인 한 사용자에 한해 재고가 없는 도서를 알림 신청하고 재고가 발생하면</br>알림 신청한 사용자의 이메일로 알림 전송 (EmailJs 사용)|[@박정민](https://github.com/qwa310)|
|도서반납,</br>도서검색창|로그인 한 사용자가 도서 반납을 하고 DB 에 Update, Insert 되는 로직 개발,</br>index 화면에서 사용자가 필터에 따라 도서 검색을 하면 DB 에서 </br>관련 도서를 Select 해와서 관련 결과를 띄워준다.</br>도서 상세 페이지로 넘어가는 로직 개발|[@이태훈](https://github.com/taehoon95)|
### 
### 주안점
- E-BOOK 처럼 온라인에서 사용자가 도서를 대여하고 반납할 수 있도록 개발하였다.
- 같은 책이 여러 권 있을 경우 각 책의 대여 및 반납상태를 어떤 방식으로 관리할지에 대해 고민하며 DB 설계를 했다. 
- 오프라인에서는 같은 책이 여러 권 있더라도 각각의 도서 고유 번호가 다르게 부여 되기 때문에 이것을 참고해서 DB 설계를 했다.
- 우선 기본적으로 도서 별로 각각 ISBN 이라는 국제 표준 도서 번호를 부여한 다음 B_ID 라는 다른 PRIMARY KEY 를 하나 만들어서 같은 책이 여러 권 있다면 ISBN 은 같으나 B_ID 는 다르게 구분을 할 수 있고 각각의 책의 상태를 관리할 수 있도록 하였다.
### 
### 기술 스택
|사항|기술|
|:---:|:---:|
|FrontEnd|HTML, CSS , JS|
|BackEnd|SPRING BOOT, MYBATIS, Spring Security, MVC Pattern, Linux|
|Database|MYSQL, AWS RDS|
