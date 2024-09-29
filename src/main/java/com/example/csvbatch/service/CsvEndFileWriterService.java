package com.example.csvbatch.service;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class CsvEndFileWriterService {

    public void createEndFile(String filePath) {
        String endFilePath = filePath.replace(".csv", ".end.csv");

        try (FileWriter writer = new FileWriter(endFilePath)) {
            writer.write("End of CSV File");
        } catch (IOException e) {
            throw new RuntimeException("End 파일 생성 실패", e);
        }
    }
}

