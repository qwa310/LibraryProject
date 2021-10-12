package com.example.midtestlms.mapper;

import com.example.midtestlms.domain.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {
    @Select("select * from member")
    List<Member> findAll();
}
