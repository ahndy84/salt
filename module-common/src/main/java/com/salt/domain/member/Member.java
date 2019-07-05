package com.salt.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String nickname;

    @Column
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    public Member setInactive() {
        this.status = MemberStatus.INACTIVE;
        return this;
    }

    @Builder
    public Member(){}

    @Builder
    public Member(String name, String email) {
        this.name = name;
        this.email = email;
        this.nickname = name;
        this.status = MemberStatus.ACTIVE;
    }

    @Builder
    public Member(String name, String email, String nickname) {
        this.name = name;
        this.email = email;
        this.nickname = nickname;
        this.status = MemberStatus.ACTIVE;
    }
}
