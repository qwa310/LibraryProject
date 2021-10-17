package com.example.midtestlms.mapper;

import com.example.midtestlms.domain.BookInfo;
import com.example.midtestlms.domain.Book;
import com.example.midtestlms.dto.BookInfoDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    // 도서 정보 검색 - 전체
    @Select("select bi.b_title, bi.isbn, bc.c_name, bi.b_publisher, bi.b_pdate, bi.b_author, count(bi.b_title) as available_stock\n" +
            "from book b, book_info bi, book_category bc\n" +
            "where b.isbn=bi.isbn and bi.c_id=bc.c_id\n" +
            "group by bi.isbn")
    List<BookInfo> findBookInfoAll();

    // 도서 정보 검색 - title
    @Select("select bi.b_title, bi.isbn, bc.c_name, bi.b_publisher, bi.b_pdate, bi.b_author, count(bi.b_title) as available_stock\n" +
            "from book b, book_info bi, book_category bc\n" +
            "where b.isbn=bi.isbn and bi.c_id=bc.c_id and b_title like '%${title}%'\n" +
            "group by bi.isbn")
    List<BookInfo> findBookInfoByTitle(@Param("title") String title);

    // 도서 검색
    @Select("select b.b_id, b.isbn, b.b_status from book b, book_info bi where b.isbn=bi.isbn and bi.b_title = #{title}")
    List<Book> findBooksByTitle(@Param("title") String title);

    // 도서 삭제
    @Delete("delete from book_info where isbn = #{isbn}")
    void removeBookByIsbn(String isbn);

    // 신규 도서 추가
    @Insert("insert into book_info(isbn, b_title, c_id, b_publisher, b_pdate, b_author) values(#{bookInfo.isbn}, #{bookInfo.b_title}, #{bookInfo.c_id}, #{bookInfo.b_publisher}, #{bookInfo.b_pdate}, #{bookInfo.b_author})")
    void saveBookInfo(@Param("bookInfo") BookInfoDto bookInfoDto);

    // 기존 도서 재고 추가
    @Insert("insert into book(isbn) value(#{isbn})")
    void saveBook(@Param("isbn") String isbn);

    // 도서 정보 수정
    @Update("update book_info set b_title=#{bookInfoDto.b_title}, c_id=#{bookInfoDto.c_id}, b_publisher=#{bookInfoDto.b_publisher}, b_pdate=#{bookInfoDto.b_pdate}, b_author=#{bookInfoDto.b_author} where isbn=#{bookInfoDto.isbn}")
    void updateBookInfo(@Param("bookInfoDto") BookInfoDto bookInfoDto);

    // 알림 버튼 활성화 체크
    @Select("select count(b.b_status) from book b, book_info bi where b.isbn=bi.isbn and bi.b_title = #{title} and b.b_status=0")
    int getCntForRentalStatusIsFalse(@Param("title") String title);


}
