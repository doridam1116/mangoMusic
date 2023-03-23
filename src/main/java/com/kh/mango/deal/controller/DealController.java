package com.kh.mango.deal.controller;

import com.kh.mango.deal.domain.Deal;
import com.kh.mango.deal.service.DealService;
import com.kh.mango.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class DealController {

    @Autowired
    private DealService dService;

    @PostMapping("/songBuy")
    @ResponseBody
    public String songBuy(int tradeNo, int price, int buyer, int seller, @SessionAttribute("loginUser") User user){
        Deal deal = new Deal(tradeNo,buyer,seller,price);
        user.setPointVal(user.getPointVal()-price);
        int result = dService.insertDeal(deal);
        return "success";
    }
}
