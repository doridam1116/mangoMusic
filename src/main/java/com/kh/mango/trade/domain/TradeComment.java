package com.kh.mango.trade.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TradeComment {
    private int commentNo;
    private int userNo;
    private String commentContent;
    private Timestamp commentDate;
    private int tradeNo;
    private int csNo;

    private String userName;
}
