package com.example.midtestlms.mapper;

import com.example.midtestlms.domain.Rental;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface RentalMapper {

    // 대여정보 조회 return_status = 1 현재 대여중 상태
    @Select("select a.r_id, a.b_id, a.m_id, a.isbn, b.b_title, b.b_author, b.b_publisher , " +
            "a.rent_date, a.due_return_date, a.return_status, a.ext_num \n" +
            "from lms.rental_manage a\n" +
            "join lms.book_info b on a.isbn = b.isbn\n" +
            "where m_id = 12")
    List<Rental> findRental();

}
