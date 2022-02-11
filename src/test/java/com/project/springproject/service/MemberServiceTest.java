package com.project.springproject.service;


import com.project.springproject.domain.Member;
import com.project.springproject.dto.MemberDto;
import com.project.springproject.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입(){
        // given
        MemberDto memberDto = new MemberDto();
        memberDto.setLoginId("kkk");

        // when
        Long saveId = memberService.join(memberDto);

        // then
        assertThat(memberRepository.findById(saveId).getId()).isEqualTo(saveId);
    }

    @Test
    public void 중복회원(){
        MemberDto memberDto1 = new MemberDto();
        memberDto1.setLoginId("kkk");
        memberDto1.setName("asdasd");
        memberDto1.setPassword("asddddd");

        MemberDto memberDto2 = new MemberDto();
        memberDto2.setLoginId("kkk");
        memberDto2.setName("ddd");
        memberDto2.setPassword("asdddddddd");

        memberService.join(memberDto1);

        assertThrows(IllegalStateException.class, () -> memberService.join(memberDto2));
    }
}