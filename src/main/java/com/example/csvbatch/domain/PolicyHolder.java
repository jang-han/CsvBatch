package com.example.csvbatch.domain;

public class PolicyHolder {
    private String policyNumber;
    private String nameKana;
    private String birthdate;

    public PolicyHolder(String policyNumber, String nameKana, String birthdate) {
        this.policyNumber = policyNumber;
        this.nameKana = nameKana;
        this.birthdate = birthdate;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getNameKana() {
        return nameKana;
    }

    public String getBirthdate() {
        return birthdate;
    }
}
