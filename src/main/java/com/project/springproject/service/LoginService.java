package com.project.springproject.service;

import com.project.springproject.domain.Member;
import com.project.springproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * return null -> 로그인 실패
     */
    public Member login(String loginId, String password){
        log.info("LoginService login");
        return memberRepository.findByLoginId(loginId).stream()
                .filter(m -> m.getPassword().equals(password))
                .findAny().orElse(null);
    }
}
