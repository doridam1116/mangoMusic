package com.kh.mango.point.service;

import com.kh.mango.point.domain.Point;
import com.kh.mango.point.domain.PointRecord;
import com.kh.mango.user.domain.User;

import java.util.List;

public interface PointService {
    int updateAddPoint(Point point);

    int updateRefundPoint(Point point);


    List<User> allPointList();

    Point selectPoint(int userNo);
}
