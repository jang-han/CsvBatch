package com.example.csvbatch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.example.csvbatch.domain.PolicyHolder;
import com.example.csvbatch.service.CsvFileValidationService;

public class CsvItemProcessor implements ItemProcessor<PolicyHolder, PolicyHolder> {

    private final CsvFileValidationService validationService;

    public CsvItemProcessor(CsvFileValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public PolicyHolder process(PolicyHolder item) throws Exception {
        // Microsoft SQL에서 가져온 데이터와 비교하여 검증
        validationService.validate(item);
        return item;  // 검증된 데이터만 다음 단계로 전달
    }
}
