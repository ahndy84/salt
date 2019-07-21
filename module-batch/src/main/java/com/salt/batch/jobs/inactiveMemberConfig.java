package com.salt.batch.jobs;

import com.salt.domain.member.Member;
import com.salt.domain.member.MemberRepository;
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

@Slf4j  // log 사용을 위한 lombok Annotation
@AllArgsConstructor  // 생성자 DI를 위한 lombok Annotation
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
                .processor(this.inactiveMemberProcessor())
                .writer(this.inactiveMemberWriter())
                .build();
    }

    @Bean
    @StepScope
    public ListItemReader<Member> inactiveMemberReader() {
        List<Member> members = memberRepository.findAll();
        log.info(">>>>>>>>>> This is inactiveMemberReader : " + members.size());
        return new ListItemReader<>(members);
    }

    public ItemProcessor<Member, Member> inactiveMemberProcessor() {
//        return Member::setStatusByUnPaid;
        return new ItemProcessor<Member, Member>() {
            @Override
            public Member process(Member member) throws Exception {
                log.info(">>>>>>>>>> This is inactiveMemberProcessor");
                return member.setStatusByUnPaid();
            }
        };
    }

    public ItemWriter<Member> inactiveMemberWriter() {
        log.info(">>>>>>>>>> This is inactiveMemberWriter");
        return ((List<? extends Member> memberList) ->
                memberRepository.saveAll(memberList));
    }
}
