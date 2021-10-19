package com.example.midtestlms.dto;
import com.example.midtestlms.domain.Notification;
import com.example.midtestlms.domain.Notification.NotificationBuilder;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class NotificationDto {
    private int n_id;
    private int b_id;
    private int m_id;
    private String name;
    private String email;
    private String apply_date;
    private String resp_date;
    private String b_title;
    private String isbn;

	@Builder
    public NotificationDto(int n_id, int b_id, int m_id, String name, String email, String apply_date, String resp_date,
			String b_title, String isbn) {
		this.n_id = n_id;
		this.b_id = b_id;
		this.m_id = m_id;
		this.name = name;
		this.email = email;
		this.apply_date = apply_date;
		this.resp_date = resp_date;
		this.b_title = b_title;
		this.isbn = isbn;
	}

    public Notification toEntity(){
        return Notification.builder()
                .n_id(n_id)
                .b_id(b_id)
                .m_id(m_id)
                .name(name)
                .email(email)
                .apply_date(apply_date)
                .resp_date(resp_date)
                .b_title(b_title)
                .isbn(isbn)
                .build();
    }

}
