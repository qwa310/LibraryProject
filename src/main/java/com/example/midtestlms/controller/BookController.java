package com.example.midtestlms.controller;

import com.example.midtestlms.domain.BookInfo;
import com.example.midtestlms.service.BookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    // 의존성 주입
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }



    // 도서 검색
//    @PostMapping("/book-search")
////    @ResponseBody
//    public String BookInfoList(@Param("search_op") String search_op, @Param("keyword") String keyword, Model model) {
////        return search_op + " " + keyword;
//        switch(search_op){
//            case "title":
//                model.addAttribute("resultTitle", bookService.findBooksByTitle(keyword));
//
//                break;
//            case "category":
//                break;
//            case "author":
//                break;
//            case "publisher":
//                break;
//        }
//        return
//    }



    @PostMapping("/admin/book")
    public void s(){

    }
}
