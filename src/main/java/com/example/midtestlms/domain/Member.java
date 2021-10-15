package com.example.midtestlms.domain;

import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor
public class Member {
    private Long m_id;
    private String email;
    private String pwd;
    private String name;
    private String pid;
    private String phone;
    private String rentable_date;
    private String auth;

    @Builder
    public Member(Long m_id, String email, String pwd, String name, String pid, String phone, String rentable_date, String auth) {
        this.m_id = m_id;
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.pid = pid;
        this.phone = phone;
        this.rentable_date = rentable_date;
        this.auth = auth;
    }
//    @Builder
//    public Member(String pwd, String phone) {
//        this.pwd = pwd;
//        this.phone = phone;
//    }

}