package com.example.csvbatch.config;

import java.util.Collections;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import com.example.csvbatch.listener.JobCompletionNotificationListener;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    public BatchConfig(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    // RetryTemplate 설정: 3회 재시도
    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        RetryPolicy retryPolicy = new SimpleRetryPolicy(3, Collections.singletonMap(Exception.class, true));
        retryTemplate.setRetryPolicy(retryPolicy);
        return retryTemplate;
    }

    @Bean
    public Job processCsvJob(JobCompletionNotificationListener listener, Step csvProcessingStep) {
        return jobBuilderFactory.get("processCsvJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(csvProcessingStep) // BatchStepConfig에서 정의된 스텝 사용
                .end()
                .build();
    }
}
