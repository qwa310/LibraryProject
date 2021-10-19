package com.example.midtestlms.mapper;

import com.example.midtestlms.domain.Member;
import com.example.midtestlms.domain.Overdue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
// 연체 Mapper
public interface OverdueMapper {

    // 연체 정보 조회
    @Select("select a.r_id, a.b_id, b.isbn, b.b_title, b.c_id, b.b_author, b.b_publisher, (a.cnt_date - (14 + (a.ext_num * 7))) as cnt_date, c.c_name  \n" +
            " from lms.rental_manage a, lms.book_info b, lms.book_category c \n" +
            " where a.isbn = b.isbn and b.c_id = c.c_id and (a.cnt_date - (14 + (a.ext_num * 7)) > 0 ) and a.m_id = #{m_id}")
    List<Overdue> findCntDate(@Param("member") final Member member, @Param("m_id") int m_id);

    // select * from book_category where c_id = 4;
}
