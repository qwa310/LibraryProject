package com.example.midtestlms.dto;

import com.example.midtestlms.domain.Book;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class BookDto {
    private Long b_id;
    private String isbn;
    private int b_status;

    public Book toEntity(){
        return Book.builder()
                .b_id(b_id)
                .isbn(isbn)
                .b_status(b_status)
                .build();
    }

    @Builder
    public BookDto(Long b_id, String isbn, int b_status) {
        this.b_id = b_id;
        this.isbn = isbn;
        this.b_status = b_status;
    }
}
