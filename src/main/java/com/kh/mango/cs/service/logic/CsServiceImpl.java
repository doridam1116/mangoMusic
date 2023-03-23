package com.kh.mango.cs.service.logic;

import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.domain.CsSearch;
import com.kh.mango.cs.domain.PageInfo;
import com.kh.mango.cs.service.CsService;
import com.kh.mango.cs.store.CsStore;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsServiceImpl implements CsService {
    @Autowired
    private CsStore cStore;
    @Autowired
    private SqlSession session;

    @Override
    public int insertCs(Cs cs) {
        return cStore.insertCs(session, cs);
    }

    @Override
    public int insertQna(Cs cs) {
        return cStore.insertQna(session, cs);
    }

    @Override
    public int updateNotice(Cs cs) {
        return cStore.updateNotice(session, cs);
    }

    @Override
    public int updateQna(Cs cs) {
        return cStore.updateQna(session, cs);
    }

    @Override
    public List<Cs> selectNoticeList(PageInfo pi) {
        return cStore.selectNoticeList(session, pi);
    }

    @Override
    public List<Cs> selectQnaList(PageInfo pi) {
        return cStore.selectQnaList(session, pi);
    }

    @Override
    public int deleteNotice(int csNo) {
        return cStore.deleteNotice(session, csNo);
    }

    @Override
    public int deleteQna(int csNo) {
        return cStore.deleteQna(session, csNo);
    }

    @Override
    public Cs selectOneByNo(int csNo) {
        return cStore.selectOneByNo(session, csNo);
    }

    @Override
    public Cs selectQnaOneByNo(int csNo) {
        return cStore.selectQnaOneByNo(session, csNo);
    }

    @Override
    public List<Cs> selectListByKeyword(PageInfo pi, CsSearch nSearch) {
        return cStore.selectListByKeyword(session, pi, nSearch);
    }

    @Override
    public List<Cs> selectQnaListByKeyword(PageInfo pi, CsSearch qSearch) {
        return cStore.selectQnaListByKeyword(session, pi, qSearch);
    }

    @Override
    public int getListCount() {
        return cStore.getListCount(session);
    }

    @Override
    public int getQListCount() {
        return cStore.getQListCount(session);
    }

    @Override
    public int getListCount(CsSearch nSearch) {
        return cStore.getListCount(session, nSearch);
    }

    @Override
    public int getQListCount(CsSearch qSearch) {
        return cStore.getQListCount(session, qSearch);
    }


}
