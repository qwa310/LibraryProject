package com.example.midtestlms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.midtestlms.domain.Book;
import com.example.midtestlms.domain.BookSearchInfo;
import com.example.midtestlms.service.BookService;

@Controller
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping("/book/bookDetail")
    public ModelAndView bookDetail(@RequestParam(required = false) String isbn) {
    	System.out.println(isbn);
    	List<Book> bList = bookService.bookDetailList(isbn);
    	BookSearchInfo bDetails = bookService.bookDetails(isbn);
    	ModelAndView mav = new ModelAndView("/book/bookDetail", "bList", bList);
    	mav.addObject( "bDetails", bDetails);
    	return mav;
    }
    
    @GetMapping("book/bookSearch")
    public String bookSearch(@RequestParam(required=false,name = "search") String search,
    		@RequestParam(required=false,name = "bookcon") String bookcon, Model model) {
    	BookSearchInfo bookSearchInfo = new BookSearchInfo();
		switch (bookcon) {
		case "b_title":
			bookSearchInfo.setB_title(search);
			break;
		case "b_publisher":
			bookSearchInfo.setB_publisher(search);
			break;
		case "c_name":
			bookSearchInfo.setC_name(search);
			break;
		case "b_author":
			bookSearchInfo.setB_author(search);
			break;
		}
		List<BookSearchInfo> bookList = bookService.SearchBookList(bookSearchInfo);
		model.addAttribute("bookList", bookList);
    	return "book/bookSearch";
    }
    
    
    
}
