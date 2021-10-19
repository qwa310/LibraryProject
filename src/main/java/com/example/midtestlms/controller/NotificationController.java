package com.example.midtestlms.controller;
import com.example.midtestlms.domain.*;
import com.example.midtestlms.service.*;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NotificationController {
    private final NotificationService notificationService;
    private final MemberService memberService;
    private final BookService bookService;
    
    @Autowired
    public NotificationController(NotificationService notificationService, MemberService memberService, BookService bookService) {
        this.notificationService = notificationService;
        this.memberService = memberService;
        this.bookService = bookService;
    }
//
//    @GetMapping("/book/notification")
//    public String notification_btn() {
//        return "notification/alarm";
//    }

    @PostMapping("/book/notification")
    public String register_notification(
    		@Param("isbn") String isbn, @AuthenticationPrincipal User user) {
    	System.out.println(isbn);
    	Member member = memberService.findMember(user.getUsername());
//    	Book book = bookService.findbookByIsbm(bookInfo);
//    	Book book = bookService.findbookByIsbn(isbn);
//    	BookSearchInfo bookInfo = bookService.findbookinfoById(book);
    	System.out.println(member);
//    	System.out.println(book);
//    	System.out.println(bookInfo);
    	notificationService.register_notification(isbn, member);
    	System.out.println("알람 신청 완료");
    	return "redirect:/";
    }
    
//    // 이메일 전송 전
//    public String goNotificationEmail(Model model, Notification notification, @AuthenticationPrincipal User user) {
//    	Member member = memberService.findMember(user.getUsername());
////    	Book book = bookService.findbookByIsbm(bookInfo);
//    	Book book = bookService.findbookById(2);
//    	BookSearchInfo bookInfo = bookService.findbookinfoById(book);
//    	System.out.println(member);
//    	System.out.println(book);
//    	System.out.println(bookInfo);
//    	notificationService.go_email();
//    	// 재고 확인
//    	int book_status = notificationService.check_book_status(notification);
//    	if(book_status == 1) {
//    		List<Notification> emailList = notificationService.add_email(notification, bookInfo, member);
//    		model.addAttribute("emailList", emailList);
//    	}
//    	model.addAttribute("book_status", book_status);
//    	
//    	System.out.println("메일 전송 준비 완료");
//    	return "notification/email";
//    }
//    
//   // 이메일 전송 후 update
//   public String updateEmailDate() {
//	   notificationService.go_email();
//	   return "notification/email/complete";
//   }
    
//    // 이메일 전송
//    public String (Model model, Notification notification, @AuthenticationPrincipal User user) {
//    	Member member = memberService.findMember(user.getUsername());
////    	Book book = bookService.findbookByIsbm(bookInfo);
//    	Book book = bookService.findbookById(2);
//    	BookSearchInfo bookInfo = bookService.findbookinfoById(book);
//    	System.out.println(member);
//    	System.out.println(book);
//    	System.out.println(bookInfo);
//    	notificationService.go_email();
//    	// 재고 확인
//    	int book_status = notificationService.check_book_status(notification);
//    	model.addAttribute("book_status", book_status);
//    	
//    	List<Notification> emailList = notificationService.add_email(notification, bookInfo, member);
//    	System.out.println("메일 전송 완료");
//    	return "notification/email";
//    }
}

