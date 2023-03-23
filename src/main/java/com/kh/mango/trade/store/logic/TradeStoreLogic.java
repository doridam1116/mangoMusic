package com.kh.mango.trade.store.logic;

import com.kh.mango.comment.domain.Comment;
import com.kh.mango.cs.domain.PageInfo;
import com.kh.mango.trade.domain.Trade;
import com.kh.mango.trade.domain.TradeComment;
import com.kh.mango.trade.store.TradeStore;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TradeStoreLogic implements TradeStore {
    @Autowired
    private SqlSession session;
    @Override
    public int getTradeListCount() {
        return session.selectOne("TradeMapper.getTradeListCount");
    }

    @Override
    public List<Trade> selectTradeList(PageInfo pi) {
        int limit = pi.getBoardLimit();
        int currentPage = pi.getCurrentPage();
        int offset = (currentPage - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("TradeMapper.selectTradeList",null,rowBounds);
    }

    @Override
    public Trade selectTradeOneByNo(int tradeNo) {
        session.update("TradeMapper.updateTradeView",tradeNo);
        return session.selectOne("TradeMapper.selectTradeOneByNo",tradeNo);
    }

    @Override
    public List<TradeComment> selectTradeCommentList(int tradeNo) {
        return session.selectList("TradeMapper.selectTradeCommentList",tradeNo);
    }




}
