package com.example.midtestlms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.midtestlms.domain.Book;
import com.example.midtestlms.domain.BookCategory;
import com.example.midtestlms.domain.BookSearchInfo;
import com.example.midtestlms.service.BookService;

@Controller
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping("/bookDetail")
    public ModelAndView bookDetail(@RequestParam(required = false) String isbn) {
    	System.out.println(isbn);
    	List<Book> bList = bookService.bookDetailList(isbn);
    	BookSearchInfo bDetails = bookService.bookDetails(isbn);
    	ModelAndView mav = new ModelAndView("bookDetail", "bList", bList);
    	mav.addObject( "bDetails", bDetails);
    	return mav;
    }
    
}
