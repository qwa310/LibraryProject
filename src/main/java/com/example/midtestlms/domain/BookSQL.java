package com.example.midtestlms.domain;

import org.apache.ibatis.jdbc.SQL;

public class BookSQL {
	public static String searchBookListCon(BookSearchInfo bookSearchInfo) {
		 	System.out.println(bookSearchInfo);
		return new SQL() {{
			SELECT("b.isbn, b.b_title, b.b_publisher, b.b_pdate, b.b_author, c.c_name, image ");
			FROM("book_info b join book_category c on b.c_id = c.c_id");
			
			if(bookSearchInfo.getB_title() != null) {
				System.out.println("1");
				WHERE("b_title like CONCAT('%',#{bookSearchInfo.b_title},'%')");
			}else if(bookSearchInfo.getC_name() != null) {
				System.out.println("2");
				WHERE("c_name like CONCAT('%',#{bookSearchInfo.c_name},'%')");
			}else if(bookSearchInfo.getB_publisher() != null) {
				System.out.println("3");
				System.out.println("b_publisher");
				WHERE("b_publisher like CONCAT('%',#{bookSearchInfo.b_publisher},'%')");
			}else if(bookSearchInfo.getB_author() != null) {
				System.out.println("4");
				WHERE("b_author like CONCAT('%',#{bookSearchInfo.b_author},'%')");
			}
		}}.toString();
	}

}