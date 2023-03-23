package com.kh.mango.user.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageInfo {
    private int currentPage; //현재 페이지
    private int boardLimit; // 한 페이지 당 게시글 갯수
    private int naviLimit; // 한 페이지 당 pageNavi 갯수
    private int startNavi; // pageNavi 시작값
    private int endNavi; // pageNavi 끝값
    private int totalCount; //전체 게시글의 갯수
    private int maxPage; // 페이지의 마지막 번호
}
