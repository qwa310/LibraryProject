package com.example.midtestlms.controller;

import com.example.midtestlms.domain.Member;
import com.example.midtestlms.dto.MemberDto;
import com.example.midtestlms.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {
    private final MemberService memberService;
    // 의존성 주입
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
