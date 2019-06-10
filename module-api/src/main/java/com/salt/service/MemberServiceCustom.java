package com.salt.service;

import com.salt.domain.member.Member;
import com.salt.domain.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceCustom {
    private MemberRepository memberRepository;

    /*
        Autowired(필드 injection)없이 생성자 injection을 사용하였습니다.
        필드 injection을 많이 사용하는 경우 SpringMVC에 종속적이게 된다는 점과 mock 의존성을 좀 더 자유롭게 사용하기가 힘든점이 있어
        최근에 생성자 injection을 사용하고 있습니다.
     */

    public MemberServiceCustom(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long signup (Member member) {
        return memberRepository.save(member).getId();
    }
}
