package com.example.midtestlms.service;

import com.example.midtestlms.domain.Book;
import com.example.midtestlms.domain.BookInfo;
import com.example.midtestlms.dto.BookInfoDto;
import com.example.midtestlms.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class BookService{
    private final BookMapper bookMapper;
    // 의존성 주입
    @Autowired
    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    // 도서 정보 검색 - 전체
    public List<BookInfo> findBookInfos(){
        return bookMapper.findBookInfoAll();
    }

    // 도서 검색
    public List<Book> findBooksByTitle(String title){
        return bookMapper.findBooksByTitle(title);
    }

    // 도서 삭제
    public void removeBook(String isbn){
        bookMapper.removeBookByIsbn(isbn);
        System.out.println("BookService.removeBook : " + isbn);
    }

    // 도서 추가
    public void addBookInfo(BookInfoDto bookInfoDto){
        try{
            // 신규 도서 추가 - book-info 테이블에 데이터 추가
            System.out.println("BookService.addBookInfo : 신규 도서 추가 시도. . .");
            bookMapper.saveBookInfo(bookInfoDto);
            bookMapper.saveBook(bookInfoDto.getIsbn());
            System.out.println("BookService.addBookInfo : 신규 도서 추가 시도 성공");
        }catch(DuplicateKeyException e) {
            // 이미 존재하는 도서(bookInfoDto의 isbn으로 판별)일 경우, book 테이블에 데이터 추가
            System.out.println("BookService.addBookInfo : 이미 존재하는 도서 입니다. 기존 도서에 재고를 1 늘립니다.");
            bookMapper.saveBook(bookInfoDto.getIsbn());
            System.out.println("BookService.addBookInfo : 기존 도서 재고 추가 완료.");
        }
    }

    // 도서 정보 수정
    public void editBookInfo(BookInfoDto bookInfoDto) {
        bookMapper.updateBookInfo(bookInfoDto);
    }


    // 알림 버튼 활성화 체크
    public int checkBtnNotificationActCondition(String title){
        return bookMapper.getCntForRentalStatusIsFalse(title);
    }


}
