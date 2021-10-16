package com.example.midtestlms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.midtestlms.domain.Book;
import com.example.midtestlms.domain.Member;
import com.example.midtestlms.domain.Rental;


@Mapper
public interface RentalMapper {

	// 대여정보 조회 return_status = 1 현재 대여중 상태
    @Select("select a.r_id, a.b_id, a.m_id, a.isbn, b.b_title, b.b_author, b.b_publisher , " +
            "a.rent_date, a.due_return_date, a.return_status, a.ext_num \n" +
            "from lms.rental_manage a\n" +
            "join lms.book_info b on a.isbn = b.isbn\n" +
            "where m_id = #{member.m_id}")
    List<Rental> findRental(@Param("member") Member member);

    // 책 대여
    @Insert("insert into lms.rental_manage(b_id,m_id,isbn,due_return_date) values(#{bid},#{mid},#{isbn}, date_add(now(), INTERVAL 14 DAY))")
    void insertRental(@Param("bid") int bid, @Param("mid") Long mid, @Param("isbn") String isbn);

    // 책 대여 연장하기
    @Update("update rental_manage set ext_num = ext_num + 1 , due_return_date = date_add(due_return_date,Interval 7 Day)\n" +
            "where r_id = ${r_id}")
    int updateDueReturnDate(@Param("r_id") int r_id);
    
    // 반납하기
    
    // 0. Member table에서 rentable_date값 가져오기
    @Select("  select r.r_id, b_id, m.m_id, r.isbn, r.rent_date, r.cnt_date, r.return_status, r.ext_num, r.due_return_date, r. real_return_date,rentable_date\r\n"
    		+ "   from lms.rental_manage r\r\n"
    		+ "        join lms.book_info b \r\n"
    		+ "          on r.isbn = b.isbn\r\n"
    		+ "	    join member m\r\n"
    		+ "          on m.m_id = r.m_id\r\n"
    		+ "  where	r_id = #{rental.r_id}")
    Rental selectRentalInfo(@Param("rental") final Rental rental);

    
    // 1. rental_manage : return_status = 0, real_return_date = now()
    @Update(" UPDATE `lms`.`rental_manage` "
    	  + "    SET `real_return_date` = now(), return_status = 1 "
    	  + "  WHERE (`r_id` = #{rental.r_id}) ")
    int returnBook(@Param("rental") final Rental rental);
    
    // 2. book의 상태 1 (반납완료)로 바꾸기
    @Update(" UPDATE `lms`.`book` "
    	  + "    SET `b_status` = '1' "
    	  + "  WHERE (`b_id` = #{rental.b_id}) ")
    int returnBookMember(@Param("rental") final Rental rental);
    
    // 3. Member table에서 rentable_date 바꾸기
    @Update("UPDATE `lms`.`member` "
    		+ "   SET `rentable_date` = if( (`rentable_date` = #{rental.rentable_date} is null || `rentable_date` = #{rental.rentable_date} < now() ), "
    		+ "                              date_add(now(), Interval #{rental.cnt_date} Day), "
    		+ "                              date_add(`rentable_date`, Interval #{rental.cnt_date} Day) ) "
    		+ " WHERE `m_id` = #{rental.m_id}")
    int returnRentableDate(@Param("rental") Rental rental);
    
   	// 대여하기
    // 1. 대여 가능한 사람인지 체크
    @Select(" SELECT * "
    		+   "   FROM lms.member "
    		+   "  WHERE date(rentable_date) < date(now()) and m_id = #{rental.m_id} ")
    int rentableDate(@Param("rental") final Rental rental);
    
    // 2. 책 상태 대출하기로 바꾸기 
    @Update( " UPDATE `lms`.`book` SET `b_status` = '1' WHERE (b_id = #{rental.b_id}) ")
    int bookStatus(@Param("rental") final Rental rental);	
    
    // 3. rental_manage에 대출 된 책 insert
    @Insert( " INSERT INTO lms.rental_manage "
    		+ " (b_id, m_id, rent_date, due_return_date, isbn)"
    		+ " values" +
    		" (#{rental.b_id}, #{rental.m_id}, now(), " +
    		" date_add(now(), INTERVAL 14 DAY), #{rental.isbn} ")
    int rentalBook(@Param("rental") final Rental rental);	
}
