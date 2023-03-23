package com.kh.mango.point.controller;

import com.kh.mango.point.domain.Point;
import com.kh.mango.point.domain.PointRecord;
import com.kh.mango.point.service.PointService;
import com.kh.mango.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import java.util.List;

@Controller
public class PointController {

    @Autowired
    private PointService pService;

    @GetMapping("/chargePoint")
    public String pointChargeView(){
        return "chargePoint";
    }

    @GetMapping("/refundPoint")
    public String pointRefundView(){
        return "refundPoint";
    }

    @PostMapping("/ajaxAddPoint")
    @ResponseBody
    public String ajaxAddPoint(int userNo, int pointVal){
        Point point = new Point(userNo, pointVal);
        int result = pService.updateAddPoint(point);
        if(result > 0){
            Point point1 = pService.selectPoint(userNo);
            return String.valueOf(point1.getPointVal());
        }
        return null;
    }

    @PostMapping("/ajaxRefundPoint")
    @ResponseBody
    public String ajaxRefundPoint(int userNo, int pointVal){
        Point point = new Point(userNo, pointVal);
        int result = pService.updateRefundPoint(point);
        if(result > 0){
            Point point1 = pService.selectPoint(userNo);
            return String.valueOf(point1.getPointVal());
        }
        return null;
    }



}
