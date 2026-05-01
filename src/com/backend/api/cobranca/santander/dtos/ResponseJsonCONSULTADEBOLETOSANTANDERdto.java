package com.backend.api.cobranca.santander.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseJsonCONSULTADEBOLETOSANTANDERdto {

    /* ===================== DADOS PRINCIPAIS ===================== */
    @JsonProperty("returnCode")
    private String returnCode;

    @JsonProperty("documentNumber")
    private String documentNumber;

    @JsonProperty("beneficiaryCode")
    private Long beneficiaryCode;

    @JsonProperty("bankNumber")
    private Long bankNumber;

    @JsonProperty("clientNumber")
    private String clientNumber;

    @JsonProperty("dueDate")
    private String dueDate;

    @JsonProperty("issueDate")
    private String issueDate;

    @JsonProperty("nominalValue")
    private BigDecimal nominalValue;

    @JsonProperty("participantCode")
    private String participantCode;

    @JsonProperty("status")
    private String status;

    /* ===================== OBJETOS ===================== */
    @JsonProperty("bankSlipData")
    private BankSlipData bankSlipData;

    @JsonProperty("payerData")
    private PayerData payerData;

    @JsonProperty("guarantorData")
    private GuarantorData guarantorData;

    /* ===================== GETTERS / SETTERS ===================== */
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Long getBeneficiaryCode() {
        return beneficiaryCode;
    }

    public void setBeneficiaryCode(Long beneficiaryCode) {
        this.beneficiaryCode = beneficiaryCode;
    }

    public Long getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(Long bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public BigDecimal getNominalValue() {
        return nominalValue;
    }

    public void setNominalValue(BigDecimal nominalValue) {
        this.nominalValue = nominalValue;
    }

    public String getParticipantCode() {
        return participantCode;
    }

    public void setParticipantCode(String participantCode) {
        this.participantCode = participantCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BankSlipData getBankSlipData() {
        return bankSlipData;
    }

    public void setBankSlipData(BankSlipData bankSlipData) {
        this.bankSlipData = bankSlipData;
    }

    public PayerData getPayerData() {
        return payerData;
    }

    public void setPayerData(PayerData payerData) {
        this.payerData = payerData;
    }

    public GuarantorData getGuarantorData() {
        return guarantorData;
    }

    public void setGuarantorData(GuarantorData guarantorData) {
        this.guarantorData = guarantorData;
    }

    /* ===================== CLASSES INTERNAS ===================== */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BankSlipData {

        @JsonProperty("portfolio")
        private Integer portfolio;

        @JsonProperty("modality")
        private Integer modality;

        @JsonProperty("branch")
        private Integer branch;

        @JsonProperty("accountNumber")
        private Long accountNumber;

        @JsonProperty("processingDate")
        private String processingDate;

        @JsonProperty("entryDate")
        private String entryDate;

        @JsonProperty("protestDescription")
        private String protestDescription;

        @JsonProperty("protestQuantityDays")
        private Integer protestQuantityDays;

        @JsonProperty("writeOffIndicativeDescription")
        private String writeOffIndicativeDescription;

        @JsonProperty("writeOffQuantityDays")
        private Integer writeOffQuantityDays;

        @JsonProperty("documentKind")
        private String documentKind;

        @JsonProperty("currency")
        private String currency;

        @JsonProperty("currencyQuantity")
        private BigDecimal currencyQuantity;

        @JsonProperty("ddaPayerIndicative")
        private String ddaPayerIndicative;

        @JsonProperty("warranty")
        private String warranty;

        @JsonProperty("paymentType")
        private String paymentType;

        @JsonProperty("valueOrPercentageIndicative")
        private String valueOrPercentageIndicative;

        @JsonProperty("minValueOrPercentage")
        private BigDecimal minValueOrPercentage;

        @JsonProperty("maxValueOrPercentage")
        private BigDecimal maxValueOrPercentage;

        @JsonProperty("parcelsQuantity")
        private Integer parcelsQuantity;

        @JsonProperty("paidParcelsQuantity")
        private Integer paidParcelsQuantity;

        @JsonProperty("amountReceived")
        private BigDecimal amountReceived;

        @JsonProperty("interestPercentage")
        private BigDecimal interestPercentage;

        @JsonProperty("iofPercentage")
        private BigDecimal iofPercentage;

        @JsonProperty("digitableLine")
        private String digitableLine;

        @JsonProperty("barCode")
        private String barCode;

        /* ===== GETTERS / SETTERS ===== */
        public Integer getPortfolio() {
            return portfolio;
        }

        public void setPortfolio(Integer portfolio) {
            this.portfolio = portfolio;
        }

        public Integer getModality() {
            return modality;
        }

        public void setModality(Integer modality) {
            this.modality = modality;
        }

        public Integer getBranch() {
            return branch;
        }

        public void setBranch(Integer branch) {
            this.branch = branch;
        }

        public Long getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(Long accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getProcessingDate() {
            return processingDate;
        }

        public void setProcessingDate(String processingDate) {
            this.processingDate = processingDate;
        }

        public String getEntryDate() {
            return entryDate;
        }

        public void setEntryDate(String entryDate) {
            this.entryDate = entryDate;
        }

        public BigDecimal getAmountReceived() {
            return amountReceived;
        }

        public void setAmountReceived(BigDecimal amountReceived) {
            this.amountReceived = amountReceived;
        }

        public String getDigitableLine() {
            return digitableLine;
        }

        public void setDigitableLine(String digitableLine) {
            this.digitableLine = digitableLine;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PayerData {

        @JsonProperty("payerDocumentType")
        private Integer payerDocumentType;

        @JsonProperty("payerDocumentNumber")
        private String payerDocumentNumber;

        @JsonProperty("payerName")
        private String payerName;

        @JsonProperty("payerAddress")
        private String payerAddress;

        @JsonProperty("payerNeighborhood")
        private String payerNeighborhood;

        @JsonProperty("payerZipCode")
        private String payerZipCode;

        @JsonProperty("payerCounty")
        private String payerCounty;

        @JsonProperty("payerStateAbbreviation")
        private String payerStateAbbreviation;

        @JsonProperty("payerEmail")
        private String payerEmail;

        /* GETTERS / SETTERS */
        public Integer getPayerDocumentType() {
            return payerDocumentType;
        }

        public void setPayerDocumentType(Integer payerDocumentType) {
            this.payerDocumentType = payerDocumentType;
        }

        public String getPayerDocumentNumber() {
            return payerDocumentNumber;
        }

        public void setPayerDocumentNumber(String payerDocumentNumber) {
            this.payerDocumentNumber = payerDocumentNumber;
        }

        public String getPayerName() {
            return payerName;
        }

        public void setPayerName(String payerName) {
            this.payerName = payerName;
        }

        public String getPayerAddress() {
            return payerAddress;
        }

        public void setPayerAddress(String payerAddress) {
            this.payerAddress = payerAddress;
        }

        public String getPayerNeighborhood() {
            return payerNeighborhood;
        }

        public void setPayerNeighborhood(String payerNeighborhood) {
            this.payerNeighborhood = payerNeighborhood;
        }

        public String getPayerZipCode() {
            return payerZipCode;
        }

        public void setPayerZipCode(String payerZipCode) {
            this.payerZipCode = payerZipCode;
        }

        public String getPayerCounty() {
            return payerCounty;
        }

        public void setPayerCounty(String payerCounty) {
            this.payerCounty = payerCounty;
        }

        public String getPayerStateAbbreviation() {
            return payerStateAbbreviation;
        }

        public void setPayerStateAbbreviation(String payerStateAbbreviation) {
            this.payerStateAbbreviation = payerStateAbbreviation;
        }

        public String getPayerEmail() {
            return payerEmail;
        }

        public void setPayerEmail(String payerEmail) {
            this.payerEmail = payerEmail;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GuarantorData {

        @JsonProperty("guarantorDocumentType")
        private Integer guarantorDocumentType;

        @JsonProperty("guarantorDocumentNumber")
        private String guarantorDocumentNumber;

        @JsonProperty("guarantorName")
        private String guarantorName;

        public Integer getGuarantorDocumentType() {
            return guarantorDocumentType;
        }

        public void setGuarantorDocumentType(Integer guarantorDocumentType) {
            this.guarantorDocumentType = guarantorDocumentType;
        }

        public String getGuarantorDocumentNumber() {
            return guarantorDocumentNumber;
        }

        public void setGuarantorDocumentNumber(String guarantorDocumentNumber) {
            this.guarantorDocumentNumber = guarantorDocumentNumber;
        }

        public String getGuarantorName() {
            return guarantorName;
        }

        public void setGuarantorName(String guarantorName) {
            this.guarantorName = guarantorName;
        }
    }
}
