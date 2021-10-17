package com.example.midtestlms.controller;

import com.example.midtestlms.domain.Member;
import com.example.midtestlms.service.BookService;
import com.example.midtestlms.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    private final MemberService memberService;
    private final BookService bookService;
    // 의존성 주입
    @Autowired
    public IndexController(MemberService memberService, BookService bookService) {
        this.memberService = memberService;
        this.bookService = bookService;
    }

    // 접근 거부 페이지
    @GetMapping("/denied")
    public String accessDenied() {
        return "/denied";
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal User user, Model model){
//        Long result = memberService.joinMember(MemberDto.builder()
//                        .email("admin@example.com")
//                        .pwd("1234")
//                        .name("김상연")
//                        .pid("9611222222333")
//                        .phone("01044443333")
//                        .auth("admin")
//                        .build());
//        System.out.println(String.format("result : %d", result));

        // index 페이지로 정상적으로 접속되는지+DB 쿼리 결과가 정상적으로 반환되는지 테스트
        List<Member> members = memberService.findMembers();
        System.out.println(members.toString());

        // 로그인 결과 처리
        if(user!=null){
            System.out.println(user.toString());
            System.out.println(memberService.findMember(user.getUsername().toString()));
            model.addAttribute("member", memberService.findMember(user.getUsername().toString()));
            System.out.println("로그인 성공");
        }
        return "index";
    }


}
