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

            BoardDto boardDto3 = new BoardDto();
            boardDto3.setTitle("안녕하세요");
            boardDto3.setWriter("aaa");
            boardDto3.setContent("테스트 테스트 테스트 테스트 테스트 테스트");

            BoardDto boardDto4 = new BoardDto();
            boardDto4.setTitle("ㅎㅇ");
            boardDto4.setWriter("bbb");
            boardDto4.setContent("TEST");

            BoardDto boardDto5 = new BoardDto();
            boardDto5.setTitle("HELLO");
            boardDto5.setWriter("bbb");
            boardDto5.setContent("askdlnaslksadl,kasldkmaslkdsalkn");

            BoardDto boardDto6 = new BoardDto();
            boardDto6.setTitle("test");
            boardDto6.setWriter("3333");
            boardDto6.setContent("게시글 테스트 중2");

            BoardDto boardDto7 = new BoardDto();
            boardDto7.setTitle("444444");
            boardDto7.setWriter("44444");
            boardDto7.setContent("게시글 테스트 중2");

            BoardDto boardDto8 = new BoardDto();
            boardDto8.setTitle("55555555");
            boardDto8.setWriter("55555555");
            boardDto8.setContent("게시글 테스트 중2");

            BoardDto boardDto9 = new BoardDto();
            boardDto9.setTitle("asdklnalk");
            boardDto9.setWriter("sadasd");
            boardDto9.setContent("게시글 테스트 중2");


            boardRepository.save(boardDto1.toEntity());
            boardRepository.save(boardDto2.toEntity());
            boardRepository.save(boardDto3.toEntity());
            boardRepository.save(boardDto4.toEntity());
            boardRepository.save(boardDto5.toEntity());
            boardRepository.save(boardDto6.toEntity());
            boardRepository.save(boardDto7.toEntity());
            boardRepository.save(boardDto8.toEntity());
            boardRepository.save(boardDto9.toEntity());
        }
    }
}
