package com.example.midtestlms.domain;

import lombok.*;

import java.util.Date;

@Data
@Getter @Setter
@NoArgsConstructor
public class BookSearchInfo {
	private String isbn;
    private String b_title;
    private String c_name;
    private String b_publisher;
    private String b_author;
    private Date b_pdate;

    @Builder
    public BookSearchInfo(String b_title, String c_name, String b_publisher, String b_author) {
        this.b_title = b_title;
        this.c_name = c_name;
        this.b_publisher = b_publisher;
        this.b_author = b_author;
    }
}