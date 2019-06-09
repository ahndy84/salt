package com.salt.service;

import com.salt.domain.member.Member;
import com.salt.domain.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceCustom {
    private MemberRepository memberRepository;

    public MemberServiceCustom(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long signup (Member member) {
        return memberRepository.save(member).getId();
    }
}
