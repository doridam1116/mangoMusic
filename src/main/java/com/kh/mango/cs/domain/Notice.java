package com.kh.mango.cs.domain;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Notice {
    private int csNo;
    private int rowNum;

    private String csSubject;
    private String userName;
    private Timestamp csDate;



}
