package com.backend.api.cobranca.santander.dtos;

public class BodyJsonBAIXADEBOLETOSANTANDERdto {

    private String covenantCode;
    private String bankNumber;
    private String operation;

    public String getCovenantCode() {
        return covenantCode;
    }

    public void setCovenantCode(String covenantCode) {
        this.covenantCode = covenantCode;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
