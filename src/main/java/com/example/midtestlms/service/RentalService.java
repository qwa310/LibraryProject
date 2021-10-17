package com.example.midtestlms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.midtestlms.domain.Member;
import com.example.midtestlms.domain.Rental;
import com.example.midtestlms.mapper.RentalMapper;

@Service
public class RentalService {
    private final RentalMapper rentalMapper;
    // 의존성 주입
    @Autowired
    public RentalService(RentalMapper rentalMapper) {
        this.rentalMapper = rentalMapper;
    }

 // 책 대여정보 조회
    public List<Rental> findRental(Member member){
        return rentalMapper.findRental(member);
    }

    // 책 대여
    public void insertRental(int bid, Long mid, String isbn){
        rentalMapper.insertRental(bid, mid, isbn);
    }

    // 책 대여 연장
    public int updateDueReturnDate(int r_id){
        return rentalMapper.updateDueReturnDate(r_id);
    }

    
    // 반납하기
    public int returnBook(int r_Id) {
    	System.out.println(r_Id);
    	Rental rental = new Rental();
    	
    	rental.setR_id(r_Id);
    	
    	rental = rentalMapper.selectRentalInfo(rental);
    	System.out.println(rental);
    	int res = rentalMapper.returnBook(rental);
    	System.out.println("1. " +res);
    	res += rentalMapper.returnBookMember(rental);
    	System.out.println("2. " +res);
    	int cntDate = rental.getCnt_date() - 14+7*rental.getExt_num();
    	if(cntDate > 0) {
    		rental.setCnt_date(cntDate);
    		res += rentalMapper.returnRentableDate(rental);
    		System.out.println("3. " +res);
    	}
    	return res;
    }


	// 대여하기
	public int rentalBook(Member member, String isbn, int b_id) {
		System.out.println("hihi : "+member.getM_id().intValue());
		List<Member> memberList = rentalMapper.rentableDate(member.getM_id().intValue());
		
		System.out.println(memberList +"나 memberList");
		System.out.println(memberList.size());
		if (memberList.size() > 0) {
			System.out.println("1111111111111");
			// 책 상태 대출하기로 바꾸기 : 쿼리문
			rentalMapper.bookStatus(b_id);
			
			System.out.println("22222222222");
			// rental_manage에 대출 된 책 insert : 쿼리문
			rentalMapper.rentalBook(member.getM_id().intValue(), isbn, b_id);
			System.out.println("대여성공");
			return 1;
		}
		return 0;
	}
}
