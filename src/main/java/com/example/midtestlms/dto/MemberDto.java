package com.example.midtestlms.dto;

import com.example.midtestlms.domain.Member;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor
public class MemberDto {
    private Long m_id;
    private String email;
    private String pwd;
    private String name;
    private String pid;
    private String phone;
    private String rentable_date;
    private String auth;

    public Member toEntity(){
        return Member.builder()
                .m_id(m_id)
                .email(email)
                .pwd(pwd)
                .name(name)
                .pid(pid)
                .phone(phone)
                .rentable_date(rentable_date)
                .auth(auth)
                .build();
    }

    @Builder
    public MemberDto(Long m_id, String email, String pwd, String name, String pid, String phone, String rentable_date, String auth) {
        this.m_id = m_id;
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.pid = pid;
        this.phone = phone;
        this.rentable_date = rentable_date;
        this.auth = auth;
    }
}
