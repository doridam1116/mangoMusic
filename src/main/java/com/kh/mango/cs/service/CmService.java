package com.kh.mango.cs.service;


import com.kh.mango.cs.domain.Comment;

import java.util.List;

public interface CmService {

    /**
     * 댓글 등록 Service
     * @param comment
     * @return int
     */
    public int insertComment(Comment comment);

    /**
     * 댓글 삭제 Service
     * @param commentNo
     * @return
     */
    int deleteComment(int commentNo);

    /**
     * 댓글 목록 조회 Service
     * @return
     */
    List<Comment> selectCommentList(int csNo);

}
