package com.project.springproject.controller;

import com.project.springproject.domain.Member;
import com.project.springproject.dto.BoardDto;
import com.project.springproject.service.BoardService;
import com.project.springproject.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum){
        List<BoardDto> boardList = boardService.getBoardList(pageNum-1);
        Long pageCount = boardService.getPageCount();

        log.info("pageCount={}",pageCount);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageCount", pageCount);

        return "boards/list";
    }

    @GetMapping("/newBoard")
    public String createBoard(Model model){
        log.info("createBoard");

        model.addAttribute("boardDto", new BoardDto());
        return "boards/new";
    }

    @PostMapping("/newBoard")
    public String create(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                         @Validated BoardDto boardDto, BindingResult bindingResult){
        log.info("create Board");

        if(bindingResult.hasErrors()){
            return "boards/new";
        }

        boardService.saveBoard(boardDto, loginMember);
        return "redirect:/boards";
    }

    @GetMapping("/boards/{id}")
    public String getBoard(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                           @PathVariable Long id, Model model){

        BoardDto boardDto = boardService.getBoard(id);
        model.addAttribute("boardDto", boardDto);

        if(loginMember == null || !loginMember.getName().equals(boardDto.getWriter())){
            log.info("detail");
            return "boards/detail";
        }

        log.info("myDetail");
        return "boards/mydetail";
    }

    @GetMapping("/boards/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        log.info("edit");

        BoardDto boardDto = boardService.getBoard(id);
        model.addAttribute("boardDto", boardDto);

        return "boards/update";
    }

    @PutMapping("/boards/edit/{id}")
    public String update(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                         @Validated BoardDto boardDto, BindingResult bindingResult, @PathVariable Long id){

        log.info("update");

        if(bindingResult.hasErrors()){
            return "boards/update";
        }

        boardService.saveBoard(boardDto, loginMember);
        return "redirect:/boards/" + id;
    }

    @DeleteMapping("/boards/{id}")
    public String delete(@PathVariable Long id){
        log.info("delete Board");
        boardService.deleteBoard(id);
        log.info("delete success");
        return "redirect:/boards";
    }

    @GetMapping("/boards/search")
    public String search(@RequestParam String keyword, @RequestParam String searchType, Model model){
        log.info("searchType={}", searchType);

        List<BoardDto> boardList = new ArrayList<>();

        if(searchType.equals("title")){
            boardList = boardService.searchBoardByTitle(keyword);
        } else if(searchType.equals("writer")){
            boardList = boardService.searchBoardByWriter(keyword);
        }

        model.addAttribute("boardList", boardList);
        return "boards/list";
    }
}
