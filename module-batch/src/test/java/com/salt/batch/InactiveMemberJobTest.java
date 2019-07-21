package com.salt.batch;

import com.salt.domain.member.Member;
import com.salt.domain.member.MemberRepository;
import com.salt.domain.member.MemberStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class InactiveMemberJobTest {

    @Autowired
    private JobLauncherTestUtils inactiveMemberJobLauncher;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 휴면_회원_전환_테스트() throws  Exception {
        generateMember();
        //assertEquals(10, memberRepository.findAll().size());
        //assertEquals(10, memberRepository.findByStatusEquals(MemberStatus.ACTIVE).size());
        JobExecution jobExecution = inactiveMemberJobLauncher.launchJob();
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
        assertEquals(10, memberRepository.findByStatusEquals(MemberStatus.ACTIVE).size());
    }

    protected void generateMember() {
        for(int i = 0; i < 10; i++) {
            String name = "salt" + i;
            String email = name + "@test.com";
            String nickName = name;
            int amountCharged = 100;
            int amountPaid = 50;
            LocalDate dueDate = LocalDate.now().minusDays(31);
            Member member = new Member(name, email, nickName, amountCharged, amountPaid, dueDate);
            this.memberRepository.save(member);
        }
    }
}
