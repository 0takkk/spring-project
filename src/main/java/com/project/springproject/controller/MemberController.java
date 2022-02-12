package com.project.springproject.controller;

import com.project.springproject.domain.Member;
import com.project.springproject.dto.MemberDto;
import com.project.springproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createMember(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "members/new";
    }

   /* @PostMapping("/members/new")
    public String create(@Valid MemberDto memberDto, BindingResult bindingResult){
        log.info("create");
        if(bindingResult.hasErrors()){
            return "/members/new";
        }

        memberService.join(memberDto);
        log.info("회원가입 성공");
        return "redirect:/";
    }*/

    @PostMapping("/members/new")
    public String create(@Valid MemberDto memberDto, BindingResult bindingResult){
        log.info("create");
        if(bindingResult.hasErrors()){
            return "/members/new";
        }
        try{
            memberService.join(memberDto);
        } catch(IllegalStateException e){
            log.info("중복 아이디");
            bindingResult.reject("duplicateLogId", "이미 존재하는 아이디입니다.");
            return "/members/new";
        }

        log.info("회원가입 성공");
        return "redirect:/";
    }
}
