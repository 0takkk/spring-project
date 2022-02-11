package com.project.springproject.dto;

import com.project.springproject.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    @NotEmpty(message = "아이디는 필수입니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;

    @NotEmpty(message = "이름은 필수입니다.")
    private String name;

    private String address;

    public Member toEntity(){
        Member member = Member.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .address(address)
                .build();
        return member;
    }

    @Builder
    public MemberDto(String loginId, String password, String name, String address) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
    }
}
