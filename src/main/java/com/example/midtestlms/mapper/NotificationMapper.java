package com.example.midtestlms.mapper;

import com.example.midtestlms.domain.Member;
import com.example.midtestlms.domain.Notification;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotificationMapper {
	@Select("select book_info.b_title, book_info.isbn, notification.n_id, notification.b_id, notification.resp_date from book_info,book,notification \n" +
            "where book_info.isbn = book.isbn and notification.b_id = book.b_id and notification.m_id = #{member.m_id}")
    List<Notification> findNotification(@Param("member") final Member member);
}
