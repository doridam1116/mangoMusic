package com.kh.mango.cs.controller;

import com.kh.mango.cs.domain.Comment;
import com.kh.mango.cs.domain.Cs;
import com.kh.mango.cs.domain.CsSearch;
import com.kh.mango.cs.domain.PageInfo;
import com.kh.mango.cs.service.CmService;
import com.kh.mango.cs.service.CsService;
import com.kh.mango.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CsController {

    @Autowired
    private CsService cService;

    @Autowired
    private CmService cmSerivce;

    // 공지사항 등록 회면
    @RequestMapping(value = "/noticeWrite", method = RequestMethod.GET)
    public String notice_wrView(){
        return "/noticeWrite";
    }
    // 공지사항 등록
    @RequestMapping(value = "/noticeWrite", method = RequestMethod.POST)
    public String noticeRegister(
            @ModelAttribute Cs cs
            , HttpServletRequest request
            , Model model) {
        int result = cService.insertCs(cs);
        if(result > 0) {
            return "redirect:/notice";
        }else {
            return "/noticeWrite";
        }
    }
    // Q&A 등록 화면
    @RequestMapping(value = "/qnaWrite", method = RequestMethod.GET)
    public String qnaWriteView() { return  "/qnaWrite"; }
    // Q&A 등록
    @RequestMapping(value = "/qnaWrite", method = RequestMethod.POST)
    public String qnaRegister(
            HttpSession session
            , @ModelAttribute Cs cs
            , HttpServletRequest request
            , Model model) {
        // 세션으로 받아오기
        User user = (User)session.getAttribute("loginUser");
        cs.setUserNo(user.getUserNo());
        int result = cService.insertQna(cs);
        if(result > 0) {
            System.out.println(result);
            return "redirect:/qna";
        }else {
            return "/qnaWrite";
        }
    }
    // 공지사항 수정 화면
    @RequestMapping(method = RequestMethod.GET, value = "/noticeModify")
    public String noticeModifyView(
            @RequestParam("csNo") Integer csNo
            , Model model) {
        Cs cs = cService.selectOneByNo(csNo);
        if(cs != null) {
            model.addAttribute("cs", cs);
            return "/noticeModify";
        }else {
            model.addAttribute("error", "실패");
            return "/notice";
        }
    }
    // Q&A 수정 화면
    @GetMapping(value = "/qnaModify")
    public String qnaModifyView(
            @RequestParam("csNo") Integer csNo
            , Model model) {
        Cs cs = cService.selectQnaOneByNo(csNo);
        if(cs != null) {
            model.addAttribute("cs", cs);
            return "/qnaModify";
        }else {
            model.addAttribute("error", "실패");
            return "/qna";
        }
    }
    // 공지사항 수정
    @PostMapping(value = "/noticeModify")
    public String noticeModify(
            @ModelAttribute Cs cs
            , Model model
            , HttpServletRequest request){
        int result = cService.updateNotice(cs);
        if(result > 0) {
            return "redirect:/noticeDetail?csNo="+cs.getCsNo();
        }else {
            model.addAttribute("msg", "실패!");
            return "/noticeModify";
        }
    }
    // Q&A 수정
    @PostMapping(value = "/qnaModify")
    public String qnaModify(@ModelAttribute Cs cs
            , Model model
            , HttpServletRequest request){
        int result = cService.updateQna(cs);
        if(result > 0) {
            return "redirect:/qnaDetail?csNo="+cs.getCsNo();
        }else {
            model.addAttribute("msg", "실패!");
            return "/qnaModify";
        }
    }
    // 공지사항 목록 화면
    @RequestMapping(method = RequestMethod.GET, value = "/notice")
    public ModelAndView noticeListView(
            ModelAndView mv
//            , Model model
            , @RequestParam(value="page", required=false, defaultValue="1") Integer page) {
        int totalCount = cService.getListCount();
        PageInfo pi = this.getPageInfo(page, totalCount);
        List<Cs> noticeList = cService.selectNoticeList(pi);

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < noticeList.size(); i++){
            noticeList.get(i).setRowNum(i+1);
        }

        for(int i = pi.getStartNavi(); i <= pi.getEndNavi(); i++){
            sb.append("<a href='http://localhost:8985/notice?page="+i+"'>"+i+"</a> ");
        }
        mv.addObject("paging",sb);
        mv.addObject("pi", pi);
        mv.addObject("noticeList", noticeList);
        mv.setViewName("notice");
//        mv.addObject("pi", pi);
//        mv.addObject("noticeList",noticeList);
//        return "/notice";
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/adminNotice")
    public ModelAndView adminNotice(
            ModelAndView mv
//            , Model model
            , @RequestParam(value="page", required=false, defaultValue="1") Integer page) {
        int totalCount = cService.getListCount();
        PageInfo pi = this.getPageInfo(page, totalCount);
        List<Cs> noticeList = cService.selectNoticeList(pi);

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < noticeList.size(); i++){
            noticeList.get(i).setRowNum(i+1);
        }

        for(int i = 1; i < pi.getEndNavi(); i++){
            sb.append("<a href='http://localhost:8985/notice?page="+i+"'>"+i+"</a> ");
        }
        mv.addObject("paging",sb);
        mv.addObject("pi", pi);
        mv.addObject("noticeList", noticeList);
        mv.setViewName("adminNotice");
//        mv.addObject("pi", pi);
//        mv.addObject("noticeList",noticeList);
//        return "/notice";
        return mv;
    }


    // Q&A 목록 화면
    @GetMapping(value = "/qna")
    public ModelAndView qnaListView(
            ModelAndView mv
//            , Model model
            , @RequestParam(value="page", required=false, defaultValue="1") Integer page) {
        int totalCount = cService.getQListCount();
        PageInfo pi = this.getPageInfo(page, totalCount);
        List<Cs> qnaList = cService.selectQnaList(pi);

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < qnaList.size(); i++){
            qnaList.get(i).setRowNum(i+1);
        }
        for(int i = 0; i < pi.getEndNavi(); i++){
            sb.append("<a href='http://localhost:8985/qna?page="+(i+1)+"'>"+(i+1)+"</a> ");
        }
//        for(int i = 0; i < qnaList.size(); i++) {
//            qnaList.get(i).setRowNum(i+1);
//        }
        mv.addObject("paging",sb);
        mv.addObject("pi", pi);
        mv.addObject("qnaList", qnaList);
        mv.setViewName("qna");
//        model.addAttribute("qnaList", qnaList);
        return mv;
    }


    // 공지사항 삭제
    @GetMapping(value = "/noticeRemove")
    public String noticeRemove(
            @RequestParam("csNo") int csNo
            , Model model) {
        int result = cService.deleteNotice(csNo);
        if(result > 0) {
            return "redirect:/notice";
        }else {
            model.addAttribute("msg", "삭제 실패!");
            return "/noticeDetail";
        }
    }
    // Q&A 삭제
    @GetMapping(value = "qnaRemove")
    public String qnaRemove(
            @RequestParam("csNo") int csNo
            , Model model) {
        int result = cService.deleteQna(csNo);
        if(result > 0) {
            return "redirect:/qna";
        }else {
            model.addAttribute("msg", "삭제 실패!");
            return "/qnaDetail";
        }
    }

    // 공지사항 상세
    @GetMapping(value = "/noticeDetail")
    public String noticeDetailView(
            @RequestParam("csNo") int csNo
            , Model model){
        Cs cs = cService.selectOneByNo(csNo);

        model.addAttribute("cs", cs);
        return "/noticeDetail";
    }
    // Q&A 상세
    @GetMapping(value = "/qnaDetail")
    public String qnaDetailView(
            @RequestParam("csNo") int csNo
            , Model model) {
        Cs cs = cService.selectQnaOneByNo(csNo);
        model.addAttribute("csNo", csNo);
        List<Comment> cmList = cmSerivce.selectCommentList(csNo);
        model.addAttribute("cs", cs);
        model.addAttribute("cmList", cmList);
        return "/qnaDetail";
    }

    // 공지사항 검색
    @GetMapping(value = "/noticeSearch")
    public String noticeSearchView(
//            HttpServletRequest request
            @ModelAttribute CsSearch nSearch
//            , @RequestParam("searchValue") String keyword
//            , @RequestParam(value = "searchCondition") String condition
            , @RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage
            , Model model) {
        int totalCount = cService.getListCount(nSearch);
        PageInfo pi = this.getPageInfo(currentPage, totalCount);
        List<Cs> searchList = cService.selectListByKeyword(pi, nSearch);
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < searchList.size(); i++){
            searchList.get(i).setRowNum(i+1);
        }

        for(int i = 0; i < pi.getEndNavi()-1; i++){
            sb.append("<a href='http://localhost:8985/noticeSearch?page="+(i+1)+"'>"+(i+1)+"</a> ");
        }

        if(!searchList.isEmpty()) {
            model.addAttribute("paging", sb);
            model.addAttribute("search", nSearch);
            model.addAttribute("pi", pi);
            model.addAttribute("nSearchList", searchList);
            return "/noticeSearch";
        }else {
            model.addAttribute("msg", "조회 실패!");
            return "/notice";
        }
    }
    // Q&A 검색
    @RequestMapping(value = "/qnaSearch", method = RequestMethod.GET)
    public String qnaSearchView(
            @ModelAttribute CsSearch qSearch
//            , @RequestParam("searchValue") String keyword
//            , @RequestParam(value = "searchCondition") String condition
            , @RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage
            , Model model) {
        int totalCount = cService.getQListCount(qSearch);
        PageInfo pi = this.getPageInfo(currentPage, totalCount);
        List<Cs> searchList = cService.selectQnaListByKeyword(pi, qSearch);
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < searchList.size(); i++){
            searchList.get(i).setRowNum(i+1);
        }
        for(int i = 0; i < pi.getEndNavi()-1; i++) {
            sb.append("<a href='http://localhost:8985/qnaSearch?page="+(i+1)+"'>"+(i+1)+"</a> ");
        }
        if (!searchList.isEmpty()) {
            model.addAttribute("paging", sb);
            model.addAttribute("search", qSearch);
            model.addAttribute("pi", pi);
            model.addAttribute("qSearchList", searchList);
            return "/qnaSearch";
        } else {
            model.addAttribute("error", "조회실패!");
            return "/qna";
        }
        // Q&A 검색
//    @RequestMapping(value = "/qnaSearch", method = RequestMethod.GET)
//    public String qnaSearchView(
//            @ModelAttribute CsSearch qSearch
////            , @RequestParam("searchValue") String keyword
////            , @RequestParam(value = "searchCondition") String condition
//            , @RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage
//            , Model model) {
//        int totalCount = cService.getQListCount(qSearch);
//        PageInfo pi = this.getPageInfo(currentPage, totalCount);
//        List<Cs> searchList = cService.selectQnaListByKeyword(pi, qSearch);
//        if (!searchList.isEmpty()) {
//            model.addAttribute("search", qSearch);
//            model.addAttribute("pi", pi);
//            model.addAttribute("qSearchList", searchList);
//            return "/qnaSearch";
//        } else {
//            model.addAttribute("error", "조회실패!");
//            return "/qna";
//        }
    }
    // navigator start, end값 설정 method()
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
//    private PageInfo getQPageInfo(int currentPage, int totalCount) {
//        PageInfo qpi = null;
//        int boardLimit = 10;
//        int naviLimit = 5;
//        int maxPage;
//        int startNavi;
//        int endNavi;
//        maxPage = (int) ((double)totalCount/boardLimit+0.9);
//        Math.ceil((double)totalCount/boardLimit);
//        startNavi = (((int)((double)currentPage/naviLimit+0.9))-1)*naviLimit+1;
//        endNavi = startNavi + naviLimit -1;
//        if(endNavi > maxPage) {
//            endNavi = maxPage;
//        }
//        qpi = new PageInfo(currentPage, boardLimit, naviLimit, startNavi, endNavi, totalCount, maxPage);
//        return qpi;
//    }

}
