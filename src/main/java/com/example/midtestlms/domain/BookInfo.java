package com.example.midtestlms.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Getter @Setter
@NoArgsConstructor
public class BookInfo {
    private String isbn;
    private String b_title;
    private Long c_id;
    private String c_name;
    private String b_publisher;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date b_pdate;
    private String b_author;
    private int available_stock;

    @Builder

    public BookInfo(String isbn, String b_title, Long c_id, String c_name, String b_publisher, Date b_pdate, String b_author, int available_stock) {
        this.isbn = isbn;
        this.b_title = b_title;
        this.c_id = c_id;
        this.c_name = c_name;
        this.b_publisher = b_publisher;
        this.b_pdate = b_pdate;
        this.b_author = b_author;
        this.available_stock = available_stock;
    }
}
