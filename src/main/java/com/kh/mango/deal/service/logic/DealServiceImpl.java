package com.kh.mango.deal.service.logic;

import com.kh.mango.deal.domain.Deal;
import com.kh.mango.deal.service.DealService;
import com.kh.mango.deal.store.DealStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealServiceImpl implements DealService {
    @Autowired
    private DealStore dStore;
    @Override
    public int insertDeal(Deal deal) {
        return dStore.insertDeal(deal);
    }
}
