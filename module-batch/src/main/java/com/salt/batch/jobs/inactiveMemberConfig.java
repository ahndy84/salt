package com.salt.batch.jobs;

import com.salt.domain.member.Member;
import com.salt.domain.member.MemberRepository;
import com.salt.domain.member.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Configuration
public class inactiveMemberConfig {

    @Autowired
    private MemberRepository memberRepository;

    @Bean
    public Job inactiveMemberJob(
            JobBuilderFactory jobBuilderFactory,
            Step inactiveJobStep
    ) {
        log.info(">>>>> This is inactiveMemberJob");
        return jobBuilderFactory.get("inactiveMemberJob")
                .preventRestart()
                .start(inactiveJobStep)
                .build();
    }

    @Bean
    public Step inactiveMemberStep(
            StepBuilderFactory stepBuilderFactory
    ) {
        log.info(">>>>> This is inactiveMemberStep");

        return stepBuilderFactory.get("inactiveMemberStep")
                .<Member, Member> chunk(10)
                .reader(inactiveMemberReader())
                .processor(inactiveMemberProcessor())
                .writer(inactiveMemberWriter())
                .build();
    }

    @Bean
    @StepScope
    public ListItemReader<Member> inactiveMemberReader() {
        List<Member> oldMember = memberRepository.findByStatusNot(MemberStatus.INACTIVE);
        return new ListItemReader<>(oldMember);
    }

    public ItemProcessor<Member, Member> inactiveMemberProcessor() {
        return Member::setInactive;
        /*
        return new ItemProcessor<Member, Member>() {
            @Override
            public User process(Member member) throws Exception {
                return member.setInactive();
            }
        };
        */
    }

    public ItemWriter<Member> inactiveMemberWriter() {
        return ((List<? extends Member> memberList) ->
                memberRepository.saveAll(memberList));
    }
}
