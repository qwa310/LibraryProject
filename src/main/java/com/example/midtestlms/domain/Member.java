package com.example.midtestlms.domain;

import lombok.Data;

@Data
public class Member {
    private Long m_id;
    private String email;
    private String pwd;
    private String pid;
    private String phone;
    private String rentable_date;
}
