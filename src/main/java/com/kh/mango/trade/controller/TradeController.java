package com.kh.mango.trade.controller;

import com.kh.mango.comment.domain.Comment;
import com.kh.mango.cs.domain.PageInfo;
import com.kh.mango.trade.domain.Trade;
import com.kh.mango.trade.domain.TradeComment;
import com.kh.mango.trade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TradeController {
    @Autowired
    private TradeService tService;

    @GetMapping("/trade")
    public String tradeView( @RequestParam(value="page", required=false, defaultValue="1") Integer page, Model model){
        int totalCount = tService.getTradeListCount();
        PageInfo pi = this.getPageInfo(page, totalCount);
        List<Trade> tList =  tService.selectTradeList(pi);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tList.size(); i++){
            tList.get(i).setRowNum(i+1);
        }
        for(int i = 0; i < pi.getEndNavi(); i++){
            sb.append("<a href='http://localhost:8985/trade?page="+(i+1)+"'>"+(i+1)+"</a> ");
        }
        model.addAttribute("paging",sb);
        model.addAttribute("pi",pi);
        model.addAttribute("tList",tList);
        return "trade";
    }



    private PageInfo getPageInfo(int currentPage, int totalCount) {
        PageInfo pi = null;
        int boardLimit = 10;
        int naviLimit = 5;
        int maxPage;
        int startNavi;
        int endNavi;

        maxPage = (int) ((double)totalCount/boardLimit+0.9);
        Math.ceil((double)totalCount/boardLimit);
        startNavi = (((int)((double)currentPage/naviLimit+0.9))-1)*naviLimit+1;
        endNavi = startNavi + naviLimit -1;
        if(endNavi > maxPage) {
            endNavi = maxPage;
        }
        pi = new PageInfo(currentPage, boardLimit, naviLimit, startNavi, endNavi, totalCount, maxPage);
        return pi;
    }


    @GetMapping("/tradeDetail")
    public String tradeDetail(
            @RequestParam("tradeNo") int tradeNo,
            Model model
    ){
        Trade trade = tService.selectTradeOneByNo(tradeNo);
        List<TradeComment> tc = tService.selectTradeCommentList(tradeNo);
        model.addAttribute("tradeDetail",trade);
        model.addAttribute("cmList",tc);
        return "tradeDetail";
    }

    @GetMapping("/tradeWrite")
    public String tradeWriteView(){
        return "tradeWrite";
    }

//    @PostMapping("/tradeWrite")
//    public String tradeWrite(@RequestParam()Trade trade){
//
//    }








}

