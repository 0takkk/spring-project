package com.project.springproject.controller;

import com.project.springproject.domain.Member;
import com.project.springproject.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class HomeController {

    /*@RequestMapping("/")
    public String home(){
        log.info("home controller");


        return "home";
    }*/

    /*@RequestMapping("/")
    public String home(HttpServletRequest request, Model model){
        log.info("home controller");

        HttpSession session = request.getSession(false);
        if(session == null){
            return "home";
        }

        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션에 회원 데이터가 없으면 home
        if(loginMember == null){
            return "home";
        }

        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }*/

    @RequestMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){
        log.info("home controller");

        // 세션에 회원 데이터가 없으면 home
        if(loginMember == null){
            return "home";
        }

        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("loginMember", loginMember);
        return "loginHome";
    }


}
