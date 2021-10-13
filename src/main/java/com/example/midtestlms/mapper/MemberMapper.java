package com.example.midtestlms.mapper;

import com.example.midtestlms.domain.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    @Select("select * from member")
    List<Member> findAll();

    @Insert("insert into member(email, pwd, name, pid, phone, auth) values(#{member.email}, #{member.pwd}, #{member.name}, #{member.pid}, #{member.phone}, #{member.auth})")
    @Options(useGeneratedKeys = true, keyProperty = "member.m_id")
    Long save(@Param("member") final Member member);

//    @Insert("insert into member(email, pwd, name, pid, phone, auth) values(#{member.email}, #{member.pwd}, #{member.name}, #{member.pid}, #{member.phone}, 'admin')")
//    @Options(useGeneratedKeys = true, keyProperty = "member.m_id")
//    Long saveAdmin(@Param("member") final Member member);

    @Select("select * from member where email = #{email}")
    Optional<Member> findByEmail(@Param("email") String email);
}
