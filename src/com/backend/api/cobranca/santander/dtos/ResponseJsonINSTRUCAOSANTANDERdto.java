package com.backend.api.cobranca.santander.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseJsonINSTRUCAOSANTANDERdto {

    @JsonProperty("returnCode")
    private String returnCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("covenantCode")
    private String covenantCode;

    @JsonProperty("bankNumber")
    private String bankNumber;

    @JsonProperty("clientNumber")
    private String clientNumber;

    @JsonProperty("participantCode")
    private String participantCode;

    @JsonProperty("entryDate")
    private String entryDate;

    @JsonProperty("traceId")
    private String traceId;

    /* ================= GETTERS / SETTERS ================= */

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

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

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getParticipantCode() {
        return participantCode;
    }

    public void setParticipantCode(String participantCode) {
        this.participantCode = participantCode;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    /* ================= HELPERS ================= */

    public boolean isSucesso() {
        return "000".equals(this.returnCode);
    }
}
