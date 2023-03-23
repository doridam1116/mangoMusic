package com.kh.mango.deal.store.logic;

import com.kh.mango.deal.domain.Deal;
import com.kh.mango.deal.store.DealStore;
import com.kh.mango.user.store.logic.UserStoreLogic;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DealStoreLogic implements DealStore {
    @Autowired
    private SqlSession session;
    @Override
    public int insertDeal(Deal deal) {
        int result = session.update("DealMapper.updateBuyer",deal);
        result += session.update("DealMapper.updateSeller",deal);

        result += session.insert("DealMapper.insertBuyerPointRecord",deal);
        result += session.insert("DealMapper.insertSellerPointRecord",deal);

        result += session.insert("DealMapper.insertDealBuyer",deal);
        result += session.insert("DealMapper.insertDealSeller",deal);
        if(result < 5){
            session.rollback();
        }
        return result;
    }
}
