package com.example.midtestlms.domain;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Notification {
    private int n_id;
    private int b_id;
    private int m_id;
    private String apply_date;
    private String resp_date;
    private String b_title;
    private String isbn;

    @Builder
    public Notification(int n_id, int b_id, int m_id, String apply_date, String resp_date, String b_title, String isbn) {
        this.n_id = n_id;
        this.b_id = b_id;
        this.m_id = m_id;
        this.apply_date = apply_date;
        this.resp_date = resp_date;
        this.b_title = b_title;
        this.isbn = isbn;
    }
}
