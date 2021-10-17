package com.example.midtestlms.controller;

import com.example.midtestlms.domain.BookInfo;
import com.example.midtestlms.domain.Member;
import com.example.midtestlms.dto.BookInfoDto;
import com.example.midtestlms.service.BookService;
import com.example.midtestlms.service.MemberService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {
    private final MemberService memberService;
    private final BookService bookService;
    // 의존성 주입
    @Autowired
    public AdminController(MemberService memberService, BookService bookService) {
        this.memberService = memberService;
        this.bookService = bookService;
    }

    // Admin - 회원 목록
    @GetMapping("/admin/member-manage")
    public String adminShowMemberList(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "admin/memberManage";
    }

    // Admin - 회원 삭제
    @PostMapping("/admin/member-manage")
    public String adminDeleteMember(Long m_id) {
        System.out.println("AdminController.adminDeleteMember : " + m_id);
        memberService.removeMember(m_id);
        return "redirect:/admin/memberManage";
    }

    // ADMIN - 도서 목록 페이지
    @GetMapping("/admin/book-manage")
    public String adminBookInfoList(Model model) {
        List<BookInfo> books = bookService.findBookInfos();
        model.addAttribute("books", books);
        return "admin/bookManage";
    }

    // ADMIN - 도서 상세
    @GetMapping("/admin/book-list")
    public String bookList(@Param("b_title") String b_title, @Param("isbn") String isbn, Model model){
        model.addAttribute("books",bookService.findBooksByTitle(b_title));
        model.addAttribute("b_title", b_title);
        model.addAttribute("isbn", isbn);
        model.addAttribute("cnt_bStatus_false", bookService.checkBtnNotificationActCondition(b_title));

        return "admin/bookList";
    }

    // Admin - 도서 추가 Form
    @GetMapping("/admin/book-manage/new")
    public String adminAddBookForm(){
        return "/admin/bookAdd";
    }

    // Admin - 도서 추가 처리
    @PostMapping("/admin/book-manage/new")
    public String adminAddBook(BookInfoDto bookInfoDto){
        bookService.addBookInfo(bookInfoDto);
        return "redirect:/admin/book-manage";
    }

    // Admin - 도서 정보 수정 Form
    @GetMapping("admin/book-manage/edit")
    public String adminEditBookForm(BookInfoDto bookInfoDto, Model model){
        System.out.println(bookInfoDto.toString());
        model.addAttribute("book", bookInfoDto);
        return "admin/bookEdit";
    }

    // Admin - 도서 정보 수정 처리
    @PostMapping("admin/book-manage/edit")
    public String adminEditBook(BookInfoDto bookInfoDto){
        System.out.println("AdminController.adminEditBook - 받은 데이터 : " + bookInfoDto.toString());
        bookService.editBookInfo(bookInfoDto);
        System.out.println("도서 정보 수정 성공");
        System.out.println("admin/bookmanage 이동");
        return "redirect:/admin/book-manage";
    }

    // Admin - 도서 삭제 Form
    @PostMapping("admin/book-manage/del")
    public String adminDelBook(BookInfoDto bookInfoDto){
        System.out.println("AdminController.adminDelBook");
        System.out.println(bookInfoDto);
        bookService.removeBook(bookInfoDto.getIsbn());
        System.out.println("도서 삭제 완료");
        return "redirect:/admin/book-manage";
    }
}
