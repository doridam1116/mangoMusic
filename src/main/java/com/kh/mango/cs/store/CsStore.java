package com.kh.mango.cs.store;

import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.domain.CsSearch;
import com.kh.mango.cs.domain.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface CsStore {

    /**
     * 공지사항 등록
     *
     * @param session
     * @param cs
     * @return int
     */
    int insertCs(SqlSession session, Cs cs);

    /**
     * Q&A 등록     * @param session
     * @param cs
     * @return int
     */
    int insertQna(SqlSession session, Cs cs);

    /**
     * 공지사항 수정
     * @param session
     * @param cs
     * @return int
     */
    int updateNotice(SqlSession session, Cs cs);

    /**
     * Q&A 수정
     * @param session
     * @param cs
     * @return
     */
    int updateQna(SqlSession session, Cs cs);

    /**
     * 공지사항 리스트
     *
     * @param session
     * @return List<Cs>
     */
    List<Cs> selectNoticeList(SqlSession session, PageInfo pi);

    /**
     * Q&A 리스트
     * @param session
     * @return List<Cs>
     */
    List<Cs> selectQnaList(SqlSession session, PageInfo pi);

    /**
     * 공지사항 삭제
     * @param session
     * @param csNo
     * @return int
     */
    int deleteNotice(SqlSession session, int csNo);

    // 공지사항 상세
    Cs selectOneByNo(SqlSession session, int csNo);
    // Q&A 삭제
    int deleteQna(SqlSession session, int csNo);

    // Q&A 상세
    Cs selectQnaOneByNo(SqlSession session, int csNo);

    // 공지사항 검색
    List<Cs> selectListByKeyword(SqlSession session, PageInfo pi, CsSearch nSearch);

    // Q&A 검색
    List<Cs> selectQnaListByKeyword(SqlSession session, PageInfo pi, CsSearch qSearch);

    int getListCount(SqlSession session);

    int getQListCount(SqlSession session);

    int getListCount(SqlSession session, CsSearch nSearch);
    int getQListCount(SqlSession session, CsSearch qSearch);

}
