package com.example.csvbatch.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.csvbatch.processor.CsvItemProcessor;
import com.example.csvbatch.service.CsvDatabaseService;

@Configuration
public class BatchStepConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final CsvItemProcessor csvItemProcessor;
    private final CsvDatabaseService csvDatabaseService;

    public BatchStepConfig(StepBuilderFactory stepBuilderFactory, 
                           CsvItemProcessor csvItemProcessor, 
                           CsvDatabaseService csvDatabaseService) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.csvItemProcessor = csvItemProcessor;
        this.csvDatabaseService = csvDatabaseService;
    }

    @Bean
    public Step csvProcessingStep() {
        return stepBuilderFactory.get("csvProcessingStep")
                .<String, String>chunk(10)
                .processor(csvItemProcessor)
                .writer(csvDatabaseService::savePolicyHolder)
                .faultTolerant()
                .retryLimit(3)
                .retry(Exception.class)
                .build();
    }
}
