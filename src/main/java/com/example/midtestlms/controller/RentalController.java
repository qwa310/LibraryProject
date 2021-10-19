package com.example.midtestlms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.midtestlms.domain.BookSearchInfo;
import com.example.midtestlms.domain.Member;
import com.example.midtestlms.domain.Notification;
import com.example.midtestlms.service.BookService;
import com.example.midtestlms.service.MemberService;
import com.example.midtestlms.service.NotificationService;
import com.example.midtestlms.service.RentalService;

@Controller
public class RentalController {
	RentalService rentalService;
	MemberService memberService;
	BookService bookService;
	NotificationService notificationService;

	@Autowired
	public RentalController(NotificationService notificationService, MemberService memberService, RentalService rentalService,BookService bookService) {
		this.memberService = memberService;
		this.rentalService = rentalService;
		this.bookService = bookService;
		this.notificationService = notificationService;
	}

	@RequestMapping(value = "/member/rental", method = { RequestMethod.GET, RequestMethod.POST })
	public String rental_btn(@AuthenticationPrincipal User user, @RequestParam("bid") String bid,
			@RequestParam("isbn") String isbn) { // @RequestParam(value = "isbn", required = false) String isbn
		Member member = memberService.findMember(user.getUsername());
		System.out.println("bid:" + bid);
		System.out.println("isbn:" + isbn);
		System.out.println("mid:" + member.getM_id());
		System.out.println("접속중인 유저 email " + user.getUsername());
		rentalService.insertRental(Integer.parseInt(isbn), member.getM_id(), isbn);
		return "redirect:/";
	}

//	    @GetMapping("/book/rental")
//	    public String rental(){
//	        System.out.println("====book/rental=====");
//	        return "member/info";
//	    }

	@PostMapping("/book/rental/extension")
	public String rentalExtension(@AuthenticationPrincipal User user, @RequestParam("r_id") int r_id, Model model) {
		// r_id 를 이용하여 책 대여 연장
		Member member = memberService.findMember(user.getUsername());
		System.out.println("extension" + r_id);
		int result = rentalService.updateDueReturnDate(r_id);
		if (result < 0) {
			System.out.println("책 대여 연장 실패 로직 추가 구현 해야함");
			return "";
		}
		model.addAttribute("member", member);
		return "redirect:/member/info";
	}

	@PostMapping("/book/rental/return")
	public String returnBook(@AuthenticationPrincipal User user, @RequestParam("r_id") int r_id, Model model) {
		// r_id 를 이용하여 책 반납
		Member member = memberService.findMember(user.getUsername());
		System.out.println("extension" + r_id);
		int result = rentalService.returnBook(r_id);
		if (result < 0) {
			System.out.println("반납 실패");
		}
		String isbn = bookService.findbookByRid(r_id);
    	BookSearchInfo bookInfo = bookService.findbookinfoByIsbn(isbn);
    	// 재고 확인 후 이메일 전송
    	int book_status = notificationService.bookStatus(isbn);
    	List<Notification> notification = notificationService.checkNotifiation(isbn);
    	model.addAttribute("notification", notification);
		model.addAttribute("member", member);
    	if(book_status == 1) {
    		model.addAttribute("notification", notification);
    		model.addAttribute("member", member);
//    		List<Notification> emailList = notificationService.add_email(notification, bookInfo, member);
//    		model.addAttribute("emailList", emailList);
//    		model.addAttribute("book_status", book_status);
        	System.out.println("메일 전송 준비 완료");
//        	notificationService.go_email();
        	return "notification/email";
    	}
    	else {
			return "redirect:/member/info";
    	}
    
	}

	@PostMapping("/notification/email")
	public String goEmails() {
		return "redirect:/member/info";
	}
	

	@PostMapping("/book/rental")
	public String rentalBook(@AuthenticationPrincipal User user, 
			@RequestParam("m_id") int m_id, 
			@RequestParam("isbn") String isbn, 
			@RequestParam("b_id") int b_id, Model model) {
		
		System.out.println(m_id);
		// r_id 를 이용하여 책 대여
		Member member = memberService.findStatus(m_id);
		System.out.println("+++++++++++++++++"+isbn);
		int res = rentalService.rentalBook(member, isbn, b_id);
		model.addAttribute("member", member);
		
		return "redirect:/";
	}
	
	 

}
