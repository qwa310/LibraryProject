package com.example.midtestlms.controller;

import com.example.midtestlms.dto.MemberDto;
import com.example.midtestlms.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
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
        return "redirect:/";
    }

    // 로그인 페이지
    @GetMapping("/member/login")
    public String login() {
        return "member/loginForm";
    }

    // 로그인 결과 페이지
    @GetMapping("/member/login/result")
    public String loginSuccess(@AuthenticationPrincipal User user) {
        System.out.println(user.toString());
//        if(user.getAuthorities().toString().equals("[ROLE_ADMIN]"))
//            return "redirect:/admin";
//        else return "redirect:/";
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
    public String myInfo() {
        return "member/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String admin() {
        return "admin/adm_index";
    }
}
