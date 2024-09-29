package com.example.csvbatch.service;

import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Service
public class CsvProcessService {

    private final RetryTemplate retryTemplate;

    public CsvProcessService(RetryTemplate retryTemplate) {
        this.retryTemplate = retryTemplate;
    }

    public void processCsvFile() {
        retryTemplate.execute(context -> {
            // CSV 파일 처리 로직
            try {
                // CSV 데이터를 읽고 검증하는 로직
                validateAndProcessCsv();
            } catch (Exception e) {
                throw new RuntimeException("CSV 파일 처리 중 오류 발생", e);
            }
            return null;  // 성공 시 반환
        });
    }

    private void validateAndProcessCsv() {
        // Microsoft SQL Server에서 데이터 확인 및 처리 로직
    }
}
