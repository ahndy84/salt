package com.salt.domain;

import com.salt.domain.member.Member;
import com.salt.domain.member.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void add() {
        this.memberRepository.save(new Member("ahndy84", "ahndy84@gmail.com"));
        Member saved = this.memberRepository.findById(1L).orElse(null);
        assertThat(saved.getName(), is("ahndy84"));
    }
}
