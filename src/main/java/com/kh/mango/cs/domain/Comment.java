package com.kh.mango.cs.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int commentNo;
    private int userNo;
    private String commentContent;
    private Timestamp commentDate;
    private int tradeNo;
    private int csNo;
    private String userName;
}
