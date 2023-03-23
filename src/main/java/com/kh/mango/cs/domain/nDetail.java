package com.kh.mango.cs.domain;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class nDetail {

    private String csSubject;
    private String csContent;
    private String userName;
    private Timestamp csDate;
    private int csNo;

}
