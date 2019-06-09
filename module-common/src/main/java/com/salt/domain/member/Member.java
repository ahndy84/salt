package com.salt.domain.member;

import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String nickname;

    public Member() {
    }

    public Member(String name, String email, String nickname) {
        this.name = name;
        this.email = email;
        this.nickname = nickname;
    }

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
