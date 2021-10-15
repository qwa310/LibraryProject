package com.example.midtestlms.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@NoArgsConstructor
public class Book {
	private int b_id;
	private String isbn;
	private int b_status;
	
    @Builder
    public Book(int b_id, String isbn, int b_status) {
		this.b_id = b_id;
		this.isbn = isbn;
		this.b_status = b_status;
	}
}