package com.example.midtestlms.service;

import com.example.midtestlms.domain.Notification;
import com.example.midtestlms.mapper.NotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    NotificationMapper notificationMapper;
    // 의존성 주입
    @Autowired
    public NotificationService(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }

    public List<Notification> findNotification(){

        return notificationMapper.findNotification();
    }
}
