package com.salt;

import com.salt.domain.member.Member;
import com.salt.service.MemberServiceCustom;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModuleApiApplicationTests {

    @Autowired
    private MemberServiceCustom memberServiceCustom;

    @Test
    public void save() {
        Member member = new Member("salt", "salt@socar.kr");
        Long id = memberServiceCustom.signup(member);
        assertThat(id, CoreMatchers.is(1l));
    }
}
