package com.kh.mango.point.service.logic;

import com.kh.mango.point.domain.Point;
import com.kh.mango.point.domain.PointRecord;
import com.kh.mango.point.service.PointService;
import com.kh.mango.point.store.PointStore;
import com.kh.mango.user.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointServiceImpl implements PointService {
    @Autowired
    private PointStore pStore;
    @Autowired
    private SqlSession session;
    @Override
    public int updateAddPoint(Point point) {
        return pStore.updateAddPoint(session,point);
    }

    @Override
    public int updateRefundPoint(Point point) {
        return pStore.updateRefundPoint(session,point);
    }

    @Override
    public Point selectPoint(int userNo) {
        return pStore.selectPoint(session,userNo);
    }

    @Override
    public List<User> allPointList() {
        return pStore.allPointList();
    }


}
