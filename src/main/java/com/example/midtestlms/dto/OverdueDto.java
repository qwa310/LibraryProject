package com.example.midtestlms.dto;

import com.example.midtestlms.domain.Overdue;
import com.example.midtestlms.domain.Rental;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
// 연체 DTO
public class OverdueDto {
    private int r_id;
    private int b_id;
    private String isbn;
    private String b_title;
    private int c_id;
    private String b_author;
    private String b_publisher;
    private int cnt_date;

    public Overdue toEntity() {
        return Overdue.builder()
                .r_id(r_id)
                .b_id(b_id)
                .isbn(isbn)
                .b_title(b_title)
                .c_id(c_id)
                .b_author(b_author)
                .b_publisher(b_publisher)
                .build();
    }

    public OverdueDto(int r_id, int b_id, String isbn, String b_title, int c_id, String b_author, String b_publisher, int cnt_date) {
        this.r_id = r_id;
        this.b_id = b_id;
        this.isbn = isbn;
        this.b_title = b_title;
        this.c_id = c_id;
        this.b_author = b_author;
        this.b_publisher = b_publisher;
        this.cnt_date = cnt_date;
    }
}
