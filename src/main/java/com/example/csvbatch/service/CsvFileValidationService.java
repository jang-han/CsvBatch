package com.example.csvbatch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.csvbatch.domain.PolicyHolder;
import com.example.csvbatch.exception.CsvFileException;

@Service
public class CsvFileValidationService {

    private static final Logger logger = LoggerFactory.getLogger(CsvFileValidationService.class);

    private final JdbcTemplate mssqlJdbcTemplate;

    public CsvFileValidationService(JdbcTemplate mssqlJdbcTemplate) {
        this.mssqlJdbcTemplate = mssqlJdbcTemplate;
    }

    public void validate(PolicyHolder policyHolder) {
        // Microsoft SQL에서 피보험자 정보를 조회
        String sql = "SELECT COUNT(*) FROM insured_table WHERE policy_number = ? AND name_kana = ? AND birthdate = ?";
        Integer count = mssqlJdbcTemplate.queryForObject(sql, Integer.class,
                policyHolder.getPolicyNumber(), 
                policyHolder.getNameKana(), 
                policyHolder.getBirthdate());

        // 일치하지 않으면 에러 발생
        if (count == null || count == 0) {
            logger.error("CSV 데이터 검증 실패 - 피보험자 정보 일치하지 않음: {}", policyHolder.getPolicyNumber());
            throw new CsvFileException("CSV 데이터 검증 실패 - 피보험자 정보 일치하지 않음: " + policyHolder.getPolicyNumber());
        }
        logger.info("CSV 데이터 검증 완료 - {}", policyHolder.getPolicyNumber());
    }
}
