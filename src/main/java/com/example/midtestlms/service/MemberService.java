package com.example.midtestlms.service;

import com.example.midtestlms.domain.Member;
import com.example.midtestlms.mapper.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public List<Member> getAllMembers() {
        return memberMapper.findAll();
    }
}
