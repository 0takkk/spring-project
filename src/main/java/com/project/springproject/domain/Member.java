package com.project.springproject.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name="members")
@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(length=10, nullable = false)
    private String loginId;

    @Column(length = 18, nullable = false)
    private String password;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 30)
    private String address;

    @Builder
    public Member(Long id, String loginId, String password, String name, String address) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
    }
}
