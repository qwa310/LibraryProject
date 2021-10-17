package com.example.midtestlms.domain;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Book {
    private Long b_id;
    private String isbn;
    private int b_status;

    @Builder
    public Book(Long b_id, String isbn, int b_status) {
        this.b_id = b_id;
        this.isbn = isbn;
        this.b_status = b_status;
    }
}
