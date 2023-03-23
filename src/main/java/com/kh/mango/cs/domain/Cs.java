package com.kh.mango.cs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cs {
    private int rowNum;
    private int csNo;
    private int csCategory;
    private String csSubject;
    private String csContent;
    private Timestamp csDate;
    private int userNo;
    private String userName;


}
