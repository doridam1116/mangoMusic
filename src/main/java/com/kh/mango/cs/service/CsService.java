package com.kh.mango.cs.service;

import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.domain.CsSearch;
import com.kh.mango.cs.domain.PageInfo;

import java.util.List;

public interface CsService {

    /**
     * 공지사항 등록
     * @param cs
     * @return int
     */
    int insertCs(Cs cs);

    /**
     * Q&A 등록
     * @param cs
     * @return int
     */
    int insertQna(Cs cs);

    /**
     * 공지사항 수정
     * @param cs
     * @return int
     */
    int updateNotice(Cs cs);

    /**
     * Q&A 수정
     * @param cs
     * @return int
     */
    int updateQna(Cs cs);


    /**
     * 공지사항 리스트
     * @param pi
     * @return List<Cs>
     */
    List<Cs> selectNoticeList(PageInfo pi);

    /**
     * Q&A 리스트
     * @return
     */
    List<Cs> selectQnaList(PageInfo pi);

    /**
     * 공지사항 삭제
     * @param csNo
     * @return int
     */
    int deleteNotice(int csNo);

    /**
     * Q&A 삭제
     * @param csNo
     * @return int
     */
    int deleteQna(int csNo);

    /**
     * 공지사항 상세
     * @param csNo
     * @return Cs
     */
    Cs selectOneByNo(int csNo);

    /**
     * Q&A 상세
     * @param csNo
     * @return Cs
     */
    Cs selectQnaOneByNo(int csNo);

    /**
     * 공지사항 검색
     * @param nSearch
     * @return List<Cs>
     */
    List<Cs> selectListByKeyword(PageInfo pi, CsSearch nSearch);

    /**
     * Q&A 검색
     * @param qSearch
     * @return List<Cs>
     */
    List<Cs> selectQnaListByKeyword(PageInfo pi, CsSearch qSearch);

    /**
     * 공지사항 검색 Service
     * @return int
     */
    int getListCount();

    /**
     *Q&A 검색 Service
     * @return int
     */
    int getQListCount();
    /**
     * 공지사항 검색 게시물 전체 개수 Service
     * @param nSearch
     * @return int
     */
    int getListCount(CsSearch nSearch);

    /**
     * Q&A검색 게시물 전체 개수 Service
     * @param qSearch
     * @return int
     */
    int getQListCount(CsSearch qSearch);
}
