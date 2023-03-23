package com.kh.mango.point.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PointRecordMap {
    private String userId;
    private String pointPM;
    private int pointRecord;
    private Timestamp pointRecordDate;


}
