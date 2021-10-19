package com.example.midtestlms.domain;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
// 대여 DAO
public class Rental {
    private int r_id;  // 대여 인덱스
    private int b_id;  // 책 인덱스
    private int m_id;  // 회원 인덱스
    private String rent_date;
    private int return_status;  // tinyint
    private int ext_num;  // 책 연장 카운트
    private String due_return_date;  // 반납 예정일
    private String real_return_date;  // 실제 반납 날짜
    private String isbn;
    private String b_title;
    private String b_author;
    private String b_publisher;
    private String rentable_date;
    private int cnt_date;

    @Builder
    public Rental(int r_id, int b_id, int m_id, String rent_date, int return_status, int ext_num, String due_return_date, String real_return_date, String isbn, String b_title, String b_author, String b_publisher, int cnt_date) {
        this.r_id = r_id;
        this.b_id = b_id;
        this.m_id = m_id;
        this.rent_date = rent_date;
        this.return_status = return_status;
        this.ext_num = ext_num;
        this.due_return_date = due_return_date;
        this.real_return_date = real_return_date;
        this.isbn = isbn;
        this.b_title = b_title;
        this.b_author = b_author;
        this.b_publisher = b_publisher;
        this.cnt_date = cnt_date;
    }
}
