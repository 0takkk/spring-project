package com.project.springproject;

import com.project.springproject.dto.BoardDto;
import com.project.springproject.dto.MemberDto;
import com.project.springproject.repository.BoardRepository;
import com.project.springproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.initMember();
        initService.initBoard();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final MemberRepository memberRepository;
        private final BoardRepository boardRepository;

        public void initMember() {
            MemberDto memberDto = new MemberDto();
            memberDto.setLoginId("test");
            memberDto.setPassword("1234");
            memberDto.setName("테스터");
            memberDto.setAddress("서울");

            memberRepository.save(memberDto.toEntity());
        }

        public void initBoard(){
            BoardDto boardDto1 = new BoardDto();
            boardDto1.setTitle("테스트1");
            boardDto1.setWriter("테스터1");
            boardDto1.setContent("게시글 테스트 중1");

            BoardDto boardDto2 = new BoardDto();
            boardDto2.setTitle("테스트2");
            boardDto2.setWriter("테스터2");
            boardDto2.setContent("게시글 테스트 중2");

            boardRepository.save(boardDto1.toEntity());
            boardRepository.save(boardDto2.toEntity());
        }
    }
}
