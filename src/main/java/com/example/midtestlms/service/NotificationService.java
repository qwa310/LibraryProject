package com.example.midtestlms.service;
import com.example.midtestlms.domain.*;
import com.example.midtestlms.mapper.NotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class NotificationService {
    NotificationMapper notificationMapper;
    // 의존성 주입
    @Autowired
    public NotificationService(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }

    public List<Notification> findNotification(Member member){
        return notificationMapper.findNotification(member);
    }
    
    // 알람 신청하기
    @Transactional
    public Long register_notification(String isbn, Member member) {
        return notificationMapper.save(isbn, member);
    } 
    
    
    // 책 상태 확인
    @Transactional
    public int check_book_status(Notification notification) {
        return notificationMapper.check_book_staus(notification);
    } 
    
    public List<Notification> checkNotifiation(String isbn){
    	return notificationMapper.checkNotifiation(isbn);
    }
    
    public int bookStatus(String isbn) {
    	return notificationMapper.bookStatus(isbn);
    }
    
    // 알람 정보 삽입하기
    @Transactional
    public Long go_email() {
       return notificationMapper.send();
    } 
    
    
    // 이메일 전송, 이메일 이름, 책 정보 필요
    @Transactional
    public List<Notification> add_email(List<Notification> notification, BookSearchInfo bookInfo, Member member) {
        return notificationMapper.addEmailInfo(notification, bookInfo, member);
    } 
    
}
