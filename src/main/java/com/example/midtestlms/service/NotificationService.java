package com.example.midtestlms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.midtestlms.domain.Member;
import com.example.midtestlms.domain.Notification;
import com.example.midtestlms.mapper.NotificationMapper;

@Service
public class NotificationService {
	
	private final NotificationMapper notificationMapper;

	  // 의존성 주입
    @Autowired
    public NotificationService(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }

    public List<Notification> findNotification(Member member){
        return notificationMapper.findNotification(member);
    }
    
}
