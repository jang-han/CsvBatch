package com.example.csvbatch.listener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus().isUnsuccessful()) {
            // 실패 처리 로직
        } else {
            // 성공 시 End 파일 생성
            createEndFile();
        }
    }

    private void createEndFile() {
        File endFile = new File("C:/MRS/output/ok/end.csv");
        try (FileWriter writer = new FileWriter(endFile)) {
            writer.write("Processing completed successfully.");
        } catch (IOException e) {
            // 파일 생성 실패 처리
        }
    }
}
