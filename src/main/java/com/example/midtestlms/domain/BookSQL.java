package com.example.midtestlms.domain;

import org.apache.ibatis.jdbc.SQL;

public class BookSQL {
	public static String searchBookListCon(BookSearchInfo bookSearchInfo) {
		 
		return new SQL() {{
			SELECT("*");
			FROM("book_info b join book_category c on b.c_id = c.c_id");
			
			if(bookSearchInfo.getB_title() != null) {
				WHERE("b_title like CONCAT('%',#{bookSearchInfo.b_title},'%')");
			}else if(bookSearchInfo.getC_name() == null) {
				WHERE("c_name like CONCAT('%',#{bookSearchInfo.c_name},'%')");
			}else if(bookSearchInfo.getB_publisher() != null) {
				System.out.println("b_publisher");
				WHERE("b_publisher like CONCAT('%',#{bookSearchInfo.b_publisher},'%')");
			}else if(bookSearchInfo.getB_author() != null) {
				WHERE("b_author like CONCAT('%',#{bookSearchInfo.b_author},'%')");
			}
		}}.toString();
	}

}