package com.example.csvbatch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.csvbatch.domain.PolicyHolder;

@Service
public class CsvDatabaseService {

    private static final Logger logger = LoggerFactory.getLogger(CsvDatabaseService.class);

    private final JdbcTemplate postgresJdbcTemplate;

    public CsvDatabaseService(JdbcTemplate postgresJdbcTemplate) {
        this.postgresJdbcTemplate = postgresJdbcTemplate;
    }

    public void savePolicyHolder(PolicyHolder policyHolder) {
        // PostgreSQL에 데이터 저장
        String sql = "INSERT INTO insured_csv_data (policy_number, name_kana, birthdate) VALUES (?, ?, ?)";
        postgresJdbcTemplate.update(sql, 
                policyHolder.getPolicyNumber(), 
                policyHolder.getNameKana(), 
                policyHolder.getBirthdate());
        logger.info("PostgreSQL에 데이터 저장 완료 - {}", policyHolder.getPolicyNumber());
    }
}
