package com.example.midtestlms.mapper;

import com.example.midtestlms.domain.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    @Delete("delete from member where m_id = #{m_id}")
    void removeMemberById(@Param("m_id") Long m_id);

    // 전체 회원 조회
    @Select("select * from member")
    List<Member> findAll();

    // 이메일로 회원 조회
    // : 로그인 후, 로그인 한 회원에 대한 정보를 가진 domain.Member 객체를 view로 반환하기 위해 필요.
    @Select("select * from member where email = #{email}")
    Optional<Member> findByEmail(@Param("email") String email);

    // 회원 가입
    @Insert("insert into member(email, pwd, name, pid, phone) values(#{member.email}, #{member.pwd}, #{member.name}, #{member.pid}, #{member.phone})")
    @Options(useGeneratedKeys = true, keyProperty = "member.m_id")
    Long saveMember(@Param("member") final Member member);


    // 도서 대출자 검색
    @Select("select m.name, r.b_id, r.r_id\n" +
            "from member m, rental_manage r, book b\n" +
            "where m.m_id=r.m_id and r.b_id = b.b_id and r.return_status=0 and r.b_id=#{b_id}")
    String findRentalMemberByBookId(@Param("b_id") Long b_id);
}
