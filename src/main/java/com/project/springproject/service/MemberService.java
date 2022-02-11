package com.project.springproject.service;

import com.project.springproject.domain.Member;
import com.project.springproject.dto.MemberDto;
import com.project.springproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(MemberDto memberDto){
        log.info("MemberService join");
        Member member = memberDto.toEntity();
        validateDuplicateMember(member);  // 중복 아이디 검사
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByLoginId(member.getLoginId());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }


}
