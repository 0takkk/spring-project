package com.project.springproject;

import com.project.springproject.dto.MemberDto;
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
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final MemberRepository memberRepository;

        public void initMember() {
            MemberDto memberDto = new MemberDto();
            memberDto.setLoginId("test");
            memberDto.setPassword("1234");
            memberDto.setName("테스터");
            memberDto.setAddress("서울");

            memberRepository.save(memberDto.toEntity());
        }
    }
}
