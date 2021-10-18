package com.example.midtestlms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.midtestlms.domain.Member;
import com.example.midtestlms.domain.Notification;
import com.example.midtestlms.domain.Overdue;
import com.example.midtestlms.domain.Rental;
import com.example.midtestlms.dto.MemberDto;
import com.example.midtestlms.dto.NotificationDto;
import com.example.midtestlms.service.MemberService;
import com.example.midtestlms.service.NotificationService;
import com.example.midtestlms.service.OverdueService;
import com.example.midtestlms.service.RentalService;

@Controller
public class MemberController {
	private final MemberService memberService;
	private final RentalService rentalService;
	private final OverdueService overdueService;
	private final NotificationService notificationService;

	// 의존성 주입
	@Autowired
	public MemberController(MemberService memberService, RentalService rentalService, OverdueService overdueService,
			NotificationService notificationService) {
		this.memberService = memberService;
		this.rentalService = rentalService;
		this.overdueService = overdueService;
		this.notificationService = notificationService;
	}

	// 회원가입 페이지
	@GetMapping("/member/new")
	public String join() {
		return "member/joinForm";
	}

	// 회원가입 처리
	@PostMapping("/member/new")
	public String joinProcess(MemberDto memberDto) {
		memberService.joinMember(memberDto);
		System.out.println("회원 가입 성공.");
		return "redirect:/";
	}

	// 로그인 페이지
	@GetMapping("/member/login")
	public String login() {
	   AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
	   if (trustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication())) {
	      // 익명
	      System.out.println("익명일 때");
	      return "member/loginForm";
	   }
	   else {
	      // 로그인한 사용자
	      System.out.println("이미 로그인 했을 때");
	      return "redirect:/";
	   }
	}

	// 로그인 결과 페이지
//    @GetMapping("/member/login/result")
//    public String loginSuccess(@AuthenticationPrincipal User user, Model model) {
//        System.out.println(user.toString());
//        System.out.println(memberService.findMember(user.getUsername().toString()));
//        model.addAttribute("member", memberService.findMember(user.getUsername().toString()));
//
////        if(user.getAuthorities().toString().equals("[ROLE_ADMIN]"))
////            return "redirect:/admin";
////        else return "redirect:/";
//        return "index";
//    }

	@GetMapping("/member/login/result")
	public String loginSuccess(@AuthenticationPrincipal User user) {
		System.out.println(user.toString());
		return "redirect:/";
	}

	// 로그아웃 결과 페이지
	@GetMapping("/member/logout/result")
	public String logoutSuccess() {
		System.out.println("로그아웃 성공!");
		return "redirect:/";
	}

	// 접근 거부 페이지
	@GetMapping("/member/denied")
	public String accessDenied() {
		return "/denied";
	}

	// 내 정보 페이지
	@GetMapping("/member/info")
	public String myInfo(Model model, NotificationDto notificationDto, @AuthenticationPrincipal User user) {
		Member members = memberService.findMember(user.getUsername());
		List<Rental> rentalList = rentalService.findRental(members);
		List<Overdue> overdueList = overdueService.findOverdue(members, members.getM_id().intValue());
		List<Notification> notificationList = notificationService.findNotification(members);

		System.out.println("총 대여 책 개수 : " + rentalList.size());
		System.out.println(members);
		System.out.println(rentalList);
		System.out.println(overdueList);
		System.out.println(notificationList);
		model.addAttribute("member", members);
		model.addAttribute("myinfo", members);
		model.addAttribute("rentalList", rentalList);
		model.addAttribute("overdueList", overdueList);
		model.addAttribute("notificationList", notificationList);
		return "member/myinfo";
	}

	// 내 정보 수정
	@PostMapping("/member/info")
	public String updateMyInfo(@AuthenticationPrincipal User user, @RequestParam("myPwd") String pwd,
			@RequestParam("myPhone") String phone) {
		memberService.updateMember(user.getUsername(), pwd, phone);
		System.out.println("정보 수정 완료");
		return "redirect:/member/info";
	}

	// 어드민 페이지
	@GetMapping("/admin")
	public String admin() {
		return "admin/adm_index";
	}

}