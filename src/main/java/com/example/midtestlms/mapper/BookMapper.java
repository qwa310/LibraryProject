package com.example.midtestlms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.example.midtestlms.domain.Book;
import com.example.midtestlms.domain.BookCategory;
import com.example.midtestlms.domain.BookInfo;
import com.example.midtestlms.domain.BookSQL;
import com.example.midtestlms.domain.BookSearchInfo;
import com.example.midtestlms.dto.BookInfoDto;

@Mapper
public interface BookMapper {
	@Select("select isbn, b_title, c_id, b_publisher, b_pdate, b_author from book_info")
    List<BookSearchInfo> bookList();
    
    @Select("select * from book_category")
    List<BookCategory> bookCategory();
    
    @SelectProvider(type = BookSQL.class, method = "searchBookListCon")
	List<BookSearchInfo> searchBookList(@Param("bookSearchInfo") BookSearchInfo bookSearchInfo);

    @Select("select * from book where isbn = #{isbn}")
    List<Book> bookDetailList(@Param("isbn") String isbn);
    
    @Select(" select isbn,b_title, b_publisher, b_pdate, b_author, image,c_name\r\n"
    		+ "  from book_info b join book_category c on b.c_id = c.c_id\r\n"
    		+ " where isbn = #{isbn}")
    BookSearchInfo bookDetails(@Param("isbn") String isbn);
    
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
    
    // 내 책 정보 조회
    @Select("select * from book where b_id = #{b_id}")
	Book findbookById(@Param("b_id") int b_id);
    
    @Select("select * from book where isbn = #{isbn}")
 	Book findbookByIsbn(@Param("isbn") String isbn);
    
    @Select("select * from book_info where isbn = (select isbn from book where b_id = #{book.b_id})")
	BookSearchInfo findbookinfoById(@Param("book") final Book book);
    
    @Select("select isbn from book where b_id = (select b_id from rental_manage where r_id = #{r_id})")
    String findbookByRid(@Param("r_id") int r_id);
    
    @Select("select * from book_info where isbn = #{isbn}")
    BookSearchInfo findbookinfoByIsbn(@Param("isbn") String isbn);
}
