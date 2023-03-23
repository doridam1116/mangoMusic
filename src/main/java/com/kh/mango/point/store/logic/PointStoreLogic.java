package com.kh.mango.point.store.logic;

import com.kh.mango.point.domain.Point;
import com.kh.mango.point.domain.PointRecord;
import com.kh.mango.point.store.PointStore;
import com.kh.mango.user.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PointStoreLogic implements PointStore {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public int updateAddPoint(SqlSession session, Point point) {
        session.insert("PointMapper.insertChargePointRecord",point);
        return session.update("PointMapper.chargePoint",point);
    }

    @Override
    public int updateRefundPoint(SqlSession session, Point point) {
        session.insert("PointMapper.insertRefundPointRecord",point);
        return session.update("PointMapper.refundPoint",point);
    }

    @Override
    public Point selectPoint(SqlSession session, int userNo) {
        return session.selectOne("PointMapper.selectPoint",userNo);
    }

    @Override
    public List<User> allPointList() {
        return sqlSession.selectList("PointMapper.AllPointRecordList");
    }


}
