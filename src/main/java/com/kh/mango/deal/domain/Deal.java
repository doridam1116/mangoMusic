package com.kh.mango.deal.domain;

import lombok.*;

import java.sql.Timestamp;

@Data
public class Deal {
    private int tradeNo;
    private String dealType;
    private Timestamp dealDate;
    private int buyerNo;
    private int sellerNo;
    private int dealPrice;

    public Deal(int tradeNo, int buyerNo, int sellerNo, int dealPrice) {
        this.tradeNo = tradeNo;
        this.buyerNo = buyerNo;
        this.sellerNo = sellerNo;
        this.dealPrice = dealPrice;
    }
}
