package com.example.midtestlms.controller;

import com.example.midtestlms.domain.Member;
import com.example.midtestlms.dto.MemberDto;
import com.example.midtestlms.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class IndexController {
    private final MemberService memberService;

    @Autowired
    public IndexController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String index(){
//        Long result = memberService.joinMember(MemberDto.builder()
//                        .email("admin@example.com")
//                        .pwd("1234")
//                        .name("김상연")
//                        .pid("9611222222333")
//                        .phone("01044443333")
//                        .auth("admin")
//                        .build());
//        System.out.println(String.format("result : %d", result));
        List<Member> members = memberService.findMembers();
        System.out.println(members.toString());
        return "index";
    }
}
