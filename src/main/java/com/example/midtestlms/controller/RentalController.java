package com.example.midtestlms.controller;

import com.example.midtestlms.mapper.RentalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RentalController {
    RentalMapper rentalMapper;
    @Autowired
    public RentalController(RentalMapper rentalMapper) {
        this.rentalMapper = rentalMapper;
    }


}
