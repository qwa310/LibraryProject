package com.example.midtestlms.mapper;

import com.example.midtestlms.domain.Overdue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
// 연체 Mapper
public interface OverdueMapper {

    // 연체 정보 조회
    @Select("select a.r_id, a.b_id, b.isbn, b.b_title, b.c_id, b.b_author, b.b_publisher, (a.cnt_date - (14 + (a.ext_num * 7))) as cnt_date \n" +
            "from lms.rental_manage a\n" +
            "join lms.book_info b on a.isbn = b.isbn where a.cnt_date - (14 + (a.ext_num * 7)) > 0\n")
    List<Overdue> findCntDate();
}
