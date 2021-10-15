package com.example.midtestlms.mapper;

import com.example.midtestlms.domain.Notification;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotificationMapper {
    @Select("select (select b_title from book_info where book_info.isbn = " +
            "(select a.isbn from book a join notification b on a.b_id = b.b_id)) as b_title, " +
            "(select a.isbn from book a join notification b on a.b_id = b.b_id) as isbn, " +
            "n_id, b_id, resp_date from notification where m_id=12;\n")
    List<Notification> findNotification();
}
