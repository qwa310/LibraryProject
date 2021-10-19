package com.example.midtestlms.service;

import com.example.midtestlms.domain.*;
import com.example.midtestlms.mapper.OverdueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverdueService {
    private final OverdueMapper overdueMapper;
    // 의존성 주입
    @Autowired
    public OverdueService(OverdueMapper overdueMapper) {
        this.overdueMapper = overdueMapper;
    }

    // 연체 정보 조회
    public List<Overdue> findOverdue(Member member, int m_id){
        return overdueMapper.findCntDate(member, m_id);
    }
}
