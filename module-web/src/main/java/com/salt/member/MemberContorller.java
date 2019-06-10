package com.salt.member;

import com.salt.domain.member.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberContorller {

    @GetMapping("/")
    public Member get() {
        return new Member("salt", "salt@socar.kr", "ahndy84");
    }
}
