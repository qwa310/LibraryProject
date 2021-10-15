package com.example.midtestlms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.midtestlms.domain.Book;
import com.example.midtestlms.domain.BookSearchInfo;
import com.example.midtestlms.service.BookService;

@RestController
public class RestBookController {

	@Autowired
	private BookService service;

	@GetMapping("/showBookList/{bSearch}/{bCon}")
	public ResponseEntity<Object> showBookList(@PathVariable String bSearch, @PathVariable String bCon) {
		BookSearchInfo bookSearchInfo = new BookSearchInfo();
		switch (bCon) {
		case "b_title":
			bookSearchInfo.setB_title(bSearch);
			break;
		case "b_publisher":
			bookSearchInfo.setB_publisher(bSearch);
			break;
		case "c_name":
			bookSearchInfo.setC_name(bSearch);
			break;
		case "b_author":
			bookSearchInfo.setB_author(bSearch);
			break;
		}
		List<BookSearchInfo> dd = service.SearchBookList(bookSearchInfo);
		return ResponseEntity.ok(dd);
	}

	@GetMapping("/bookDetail/{isbn}")
	public ResponseEntity<Object> showBookDetail(@PathVariable String isbn) {
		List<Book> bDetail = service.bookDetailList(isbn);
		return ResponseEntity.ok(bDetail);
	}

	@PostMapping("/extensionBook/")
	public ResponseEntity<Object> extensionBook(@RequestBody int r_id) {
		int extension = service.extensionBook(r_id);
		return ResponseEntity.ok(extension);
	}
	
}
