package com.example.midtestlms.controller;

import com.example.midtestlms.domain.Overdue;
import com.example.midtestlms.domain.Rental;
import com.example.midtestlms.domain.Member;
import com.example.midtestlms.dto.MemberDto;
import com.example.midtestlms.service.OverdueService;
import com.example.midtestlms.service.RentalService;
import com.example.midtestlms.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;
    private final RentalService rentalService;
    private final OverdueService overdueService;
    private MemberDto memberDto;

    // 의존성 주입
    @Autowired
    public MemberController(MemberService memberService, RentalService rentalService, OverdueService overdueService) {
        this.memberService = memberService;
        this.rentalService = rentalService;
        this.overdueService = overdueService;
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
        return "member/loginForm";
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
    public String myInfo(Model model) {
        Member members = memberService.findMember();
        List<Rental> rentalList = rentalService.findRental();
        List<Overdue> overdueList = overdueService.findOverdue();

        System.out.println("총 대여 책 개수 : "+rentalList.size());
        model.addAttribute("myinfo",members);
        model.addAttribute("rentalList",rentalList);
        model.addAttribute("overdueList",overdueList);
        return "member/myinfo";
    }

    // 내 정보 수정
    @PostMapping("/member/info")
    public String updateMyInfo(@RequestParam("myPwd") String pwd, @RequestParam("myPhone") String phone, Member member){
        memberService.updateMember(member,pwd,phone);
        System.out.println("정보 수정 완료");
        return "redirect:/member/info";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String admin() {
        return "admin/adm_index";
    }
}
