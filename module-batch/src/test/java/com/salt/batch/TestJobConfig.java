package com.salt.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class TestJobConfig {
    @Bean
    public JobLauncherTestUtils inactiveMemberJobLauncher() {
        return new JobLauncherTestUtils() {
            @Override
            @Autowired
            public  void setJob(@Qualifier("inactiveMemberJob") Job job) {
                super.setJob(job);
            }
        };
    }
}
