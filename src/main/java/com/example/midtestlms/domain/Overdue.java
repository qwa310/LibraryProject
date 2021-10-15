package com.example.midtestlms.domain;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
// 연체 DAO
public class Overdue {
    private int r_id;
    private int b_id;
    private String isbn;
    private String b_title;
    private int c_id;
    private String b_author;
    private String b_publisher;
    private int cnt_date;

    @Builder
    public Overdue(int r_id, int b_id, String isbn, String b_title, int c_id, String b_author, String b_publisher, int cnt_date) {
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
