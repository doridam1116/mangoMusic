package com.kh.mango.point.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PointRecord {
    private int userNo;
    private String pointPM;
    private int pointRecord;
    private Timestamp pointRecordDate;
}
