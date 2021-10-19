package com.example.midtestlms.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.midtestlms.domain.Member;

@Mapper
public interface MemberMapper {
	@Delete("delete from member where m_id = #{m_id}")
    void removeMemberById(@Param("m_id") Long m_id);
	
    // 전체 회원 조회
    @Select("select * from member")
    List<Member> findAll();
    
    @Select(" select m_id, email, pwd, name, pid, phone, auth, rentable_date,  "
    	   + "    if( (date(rentable_date) < date(now()) or rentable_date is null ),1,0) as member_status "
    	   + "  from member where m_id = #{m_id}")
    Member findStatus(@Param("m_id") int m_id);

      
    // 이메일로 회원 조회
    // : 로그인 후, 로그인 한 회원에 대한 정보를 가진 domain.Member 객체를 view로 반환하기 위해 필요.
    @Select("select * from member where email = #{email}")
    Optional<Member> findByEmail(@Param("email") String email);

//    // mypage 회원정보 조회
//    @Select("select * from member where m_id = #{member.m_id}")
//    Member findById(@Param("member") final Member member);

    // mypage 회원정보 수정
    @Update("update lms.member set \n" +
            "pwd = #{pwd},\n" +
            "phone = #{phone}\n" +
            "where email = #{email}")
    Long updateInfo(@Param("email") String email, @Param("pwd") final String pwd, @Param("phone") final String phone);


    // 회원 가입
    @Insert("insert into member(email, pwd, name, pid, phone) values(#{member.email}, #{member.pwd}, #{member.name}, #{member.pid}, #{member.phone})")
    @Options(useGeneratedKeys = true, keyProperty = "member.m_id")
    Long save(@Param("member") final Member member);

//    @Insert("insert into member(email, pwd, name, pid, phone, auth) values(#{member.email}, #{member.pwd}, #{member.name}, #{member.pid}, #{member.phone}, 'admin')")
//    @Options(useGeneratedKeys = true, keyProperty = "member.m_id")
//    Long saveAdmin(@Param("member") final Member member);

}