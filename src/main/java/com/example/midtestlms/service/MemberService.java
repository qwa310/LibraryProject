package com.example.midtestlms.service;

import com.example.midtestlms.domain.Member;
import com.example.midtestlms.domain.Role;
import com.example.midtestlms.dto.MemberDto;
import com.example.midtestlms.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService{
    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public List<Member> findMembers(){
        return memberMapper.findAll();
    }

    @Transactional
    public Long joinMember(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberDto.setPwd(passwordEncoder.encode(memberDto.getPwd()));

        return memberMapper.save(memberDto.toEntity());
    }

//    @Transactional
//    public Long joinAdmin() {
//        MemberDto memberDto = new MemberDto().builder()
//                .email("admin@example.com")
//                .pwd("1234")
//                .name("김상연")
//                .pid("9607167777777")
//                .phone("01088884444")
//                .build();
//
//        // 비밀번호 암호화
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
////        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
//        memberDto.setPwd(passwordEncoder.encode(memberDto.getPwd()));
//        return memberMapper.saveAdmin(memberDto.toEntity());
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> memberWrapper = memberMapper.findByEmail(email);
        Member member = memberWrapper.isPresent() ? memberWrapper.get() : null;

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(email)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(member.getEmail(), member.getPwd(), authorities);
    }
}
