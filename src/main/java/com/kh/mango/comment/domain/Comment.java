package com.kh.mango.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int commentNo;
    private int userNo;
    private String commentContent;
    private Timestamp commentDate;
    private int tradeNo;
    private int csNo;
    private String userName;

    public Comment(int userNo, String commentContent, int tradeNo) {
        this.userNo = userNo;
        this.commentContent = commentContent;
        this.tradeNo = tradeNo;
    }
}
