package com.example.midtestlms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.midtestlms.domain.Book;
import com.example.midtestlms.domain.BookCategory;
import com.example.midtestlms.domain.BookSearchInfo;
import com.example.midtestlms.mapper.BookMapper;


@Service
public class BookService {
	@Autowired
	private BookMapper bookMapper;

    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }
	
	public List<BookSearchInfo> bookList() {
    	return bookMapper.bookList();
    }
    
    public List<BookCategory> bookCategory(){
    	return bookMapper.bookCategory();
    }
    
	public List<BookSearchInfo> SearchBookList(BookSearchInfo bookSearchInfo){
		return bookMapper.searchBookList(bookSearchInfo);
    }
	
	public List<Book> bookDetailList(String isbn){
		return bookMapper.bookDetailList(isbn);
	}
	
	public BookSearchInfo bookDetails(String isbn){
		return bookMapper.bookDetails(isbn);
	}
}
