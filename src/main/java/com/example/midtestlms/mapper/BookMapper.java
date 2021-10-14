package com.example.midtestlms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.example.midtestlms.domain.Book;
import com.example.midtestlms.domain.BookCategory;
import com.example.midtestlms.domain.BookSQL;
import com.example.midtestlms.domain.BookSearchInfo;

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
    
    @Select(" select isbn,b_title, b_publisher, b_pdate, b_author, c_name\r\n"
    		+ "  from book_info b join book_category c on b.c_id = c.c_id\r\n"
    		+ " where isbn = #{isbn}")
    BookSearchInfo bookDetails(@Param("isbn") String isbn);
}
