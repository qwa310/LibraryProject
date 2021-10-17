package com.example.midtestlms.dto;

import com.example.midtestlms.domain.BookInfo;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Getter @Setter
@NoArgsConstructor
public class BookInfoDto {
    private String b_title;
    private String isbn;
    private Long c_id;
    private String c_name;
    private String b_publisher;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date b_pdate;
    private String b_author;
    private int available_stock;

    public BookInfo toEntity(){
        return BookInfo.builder()
                .b_title(b_title)
                .isbn(isbn)
                .c_id(c_id)
                .c_name(c_name)
                .b_publisher(b_publisher)
                .b_pdate(b_pdate)
                .b_author(b_author)
                .available_stock(available_stock)
                .build();
    }

    @Builder

    public BookInfoDto(String b_title, String isbn, Long c_id, String c_name, String b_publisher, Date b_pdate, String b_author, int available_stock) {
        this.b_title = b_title;
        this.isbn = isbn;
        this.c_id = c_id;
        this.c_name = c_name;
        this.b_publisher = b_publisher;
        this.b_pdate = b_pdate;
        this.b_author = b_author;
        this.available_stock = available_stock;
    }
}
