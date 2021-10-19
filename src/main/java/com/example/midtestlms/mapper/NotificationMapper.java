package com.example.midtestlms.mapper;
import com.example.midtestlms.domain.*;
import org.apache.ibatis.annotations.*;
import java.util.List;
@Mapper
public interface NotificationMapper {
	
//	@Select("select m_id, email, name from member where email = #{email}")
//	Notification findmemberByEmail(@Param("email") String email);

	@Select("SELECT book_info.b_title, notification.n_id, notification.resp_date, notification.isbn \n" +
            "FROM notification, book_info where m_id = #{member.m_id} and book_info.isbn = notification.isbn ")
    List<Notification> findNotification(@Param("member") final Member member);
    
	// 알람 신청 insert
    @Insert("insert into notification(isbn, m_id, apply_date) values(#{isbn}, #{member.m_id}, now())")
    Long save(@Param("isbn") final String isbn, @Param("member") final Member member);
    
    
    // 메일 전송한 날짜 update
    @Update("update notification set resp_date = now()")
    Long send();

    
    // 책 상태 확인 : 재고 확인 -> 결과가 0 이면 재고 없음
    @Select("SELECT SUM(b_status) as total_count from book where isbn = #{notification.isbn}")
    int check_book_staus(@Param("notification") final Notification notification);
    
    
    @Select("SELECT SUM(b_status) as total_count from book where isbn = #{isbn}")
    int bookStatus(@Param("isbn") final String isbn);
    
    
    // // 이메일 가져오기
    @Select("select email from member where m_id in (select m_id from notification where isbn=#{isbn})")
    List<Notification> checkNotifiation(@Param("isbn") final String isbn);
    
//    // 이메일 가져오기
//    @Select("select distinct email from notification, member where notification.isbn = #{isbn} and notification.m_id = member.m_id")
//    List<Notification> checkEmail(@Param("notification") final Notification notification, @Param("member") final Member member);
//    
    // 책 이름, 메일, 이름 가져오기
    @Select("select distinct (select distinct book_info.b_title from book_info, notification "
    		+ "where notification.isbn = book_info.isbn) as b_title,"
    		+ "member.email, member.name from member, notification where member.email like '%@%' and member.m_id"
    		+ "in (select distinct notification.m_id from notification where notification.isbn=#{book.isbn}")
    List<Notification> addEmailInfo(@Param("notification") final List<Notification> notification, @Param("book_info") final BookSearchInfo bookInfo, @Param("member") final Member member);
}
