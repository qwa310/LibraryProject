package com.example.midtestlms.mapper;

import com.example.midtestlms.domain.Member;
import com.example.midtestlms.dto.MemberDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    // 전체 회원 조회
    @Select("select * from member")
    List<Member> findAll();


    // 이메일로 회원 조회
    // : 로그인 후, 로그인 한 회원에 대한 정보를 가진 domain.Member 객체를 view로 반환하기 위해 필요.
    @Select("select * from member where email = #{email}")
    Optional<Member> findByEmail(@Param("email") String email);

    // mypage 회원정보 조회
    @Select("select * from member where m_id = 12")
    Member findById();

    // mypage 회원정보 수정
    @Update("update lms.member set \n" +
            "pwd = #{pwd},\n" +
            "phone = #{phone}\n" +
            "where m_id = 12")
    Long updateInfo(@Param("pwd") final String pwd, @Param("phone") final String phone);


    // 회원 가입
    @Insert("insert into member(email, pwd, name, pid, phone, auth) values(#{member.email}, #{member.pwd}, #{member.name}, #{member.pid}, #{member.phone}, #{member.auth})")

    @Options(useGeneratedKeys = true, keyProperty = "member.m_id")
    Long save(@Param("member") final Member member);

//    @Insert("insert into member(email, pwd, name, pid, phone, auth) values(#{member.email}, #{member.pwd}, #{member.name}, #{member.pid}, #{member.phone}, 'admin')")
//    @Options(useGeneratedKeys = true, keyProperty = "member.m_id")
//    Long saveAdmin(@Param("member") final Member member);

}
