package com.example.midtestlms.controller;

import com.example.midtestlms.domain.Member;
import com.example.midtestlms.dto.MemberDto;
import com.example.midtestlms.service.BookService;
import com.example.midtestlms.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;
    private final BookService bookService;
    // 의존성 주입
    @Autowired
    public MemberController(MemberService memberService, BookService bookService) {
        this.memberService = memberService;
        this.bookService = bookService;
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

}
