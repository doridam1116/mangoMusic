package com.kh.mango.trade.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    private int rowNum;
    private int tradeNo;
    private String tradeSubject;
    private String tradeContent;
    private int userNo;
    private int tradeView;
    private Timestamp tradeDate;
    private String userName;
    private int tradePrice;
    private String tradeType;

    private String uMusicName;
    private String uMusicPath;


}
