package com.kh.mango.point.store;

import com.kh.mango.point.domain.Point;
import com.kh.mango.point.domain.PointRecord;
import com.kh.mango.user.domain.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface PointStore {
    int updateAddPoint(SqlSession session, Point point);

    int updateRefundPoint(SqlSession session, Point point);


    List<User> allPointList();

    Point selectPoint(SqlSession session, int userNo);
}
