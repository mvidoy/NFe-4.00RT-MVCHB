package com.backend.api.cobranca.santander.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseJsonREGISTRODEBOLETOSANTANDERdto {

    /* ===== Dados de controle ===== */
    @JsonProperty("nsuCode")
    private String nsuCode;

    @JsonProperty("nsuDate")
    private String nsuDate;

    @JsonProperty("environment")
    private String environment;

    @JsonProperty("covenantCode")
    private String covenantCode;

    /* ===== Partes ===== */
    @JsonProperty("payer")
    private Payer payer;

    @JsonProperty("beneficiary")
    private Beneficiary beneficiary;

    /* ===== Dados do boleto ===== */
    @JsonProperty("bankNumber")
    private String bankNumber;

    @JsonProperty("clientNumber")
    private String clientNumber;

    @JsonProperty("dueDate")
    private String dueDate;

    @JsonProperty("issueDate")
    private String issueDate;

    @JsonProperty("documentKind")
    private String documentKind;

    @JsonProperty("nominalValue")
    private BigDecimal nominalValue;

    @JsonProperty("participantCode")
    private String participantCode;

    @JsonProperty("paymentType")
    private String paymentType;

    /* ===== Encargos / regras ===== */
    @JsonProperty("finePercentage")
    private BigDecimal finePercentage;

    @JsonProperty("fineQuantityDays")
    private Integer fineQuantityDays;

    @JsonProperty("interestPercentage")
    private BigDecimal interestPercentage;

    @JsonProperty("deductionValue")
    private BigDecimal deductionValue;

    @JsonProperty("writeOffQuantityDays")
    private Integer writeOffQuantityDays;

    /* ===== Desconto ===== */
    @JsonProperty("discount")
    private Discount discount;

    /* ===== Mensagens ===== */
    @JsonProperty("messages")
    private List<String> messages;

    /* ===== Dados visuais ===== */
    @JsonProperty("barCode")
    private String barCode;

    @JsonProperty("digitableLine")
    private String digitableLine;

    @JsonProperty("entryDate")
    private String entryDate;

    /* ===== PIX ===== */
    @JsonProperty("qrCodePix")
    private String qrCodePix;

    @JsonProperty("qrCodeUrl")
    private String qrCodeUrl;

    @JsonProperty("txId")
    private String txId;

    /* =======================================================================
       CLASSES INTERNAS
       ======================================================================= */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payer {

        @JsonProperty("documentType")
        private String documentType;

        @JsonProperty("documentNumber")
        private String documentNumber;

        @JsonProperty("name")
        private String name;

        @JsonProperty("address")
        private String address;

        @JsonProperty("neighborhood")
        private String neighborhood;

        @JsonProperty("city")
        private String city;

        @JsonProperty("state")
        private String state;

        @JsonProperty("zipCode")
        private String zipCode;

        /* getters e setters */
        public String getDocumentType() {
            return documentType;
        }

        public void setDocumentType(String documentType) {
            this.documentType = documentType;
        }

        public String getDocumentNumber() {
            return documentNumber;
        }

        public void setDocumentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNeighborhood() {
            return neighborhood;
        }

        public void setNeighborhood(String neighborhood) {
            this.neighborhood = neighborhood;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Beneficiary {

        @JsonProperty("name")
        private String name;

        @JsonProperty("documentType")
        private String documentType;

        @JsonProperty("documentNumber")
        private String documentNumber;

        /* getters e setters */
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDocumentType() {
            return documentType;
        }

        public void setDocumentType(String documentType) {
            this.documentType = documentType;
        }

        public String getDocumentNumber() {
            return documentNumber;
        }

        public void setDocumentNumber(String documentNumber) {
            this.documentNumber = documentNumber;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Discount {

        @JsonProperty("type")
        private String type;

        @JsonProperty("discountOne")
        private DiscountItem discountOne;

        @JsonProperty("discountTwo")
        private DiscountItem discountTwo;

        @JsonProperty("discountThree")
        private DiscountItem discountThree;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public DiscountItem getDiscountOne() {
            return discountOne;
        }

        public void setDiscountOne(DiscountItem discountOne) {
            this.discountOne = discountOne;
        }

        public DiscountItem getDiscountTwo() {
            return discountTwo;
        }

        public void setDiscountTwo(DiscountItem discountTwo) {
            this.discountTwo = discountTwo;
        }

        public DiscountItem getDiscountThree() {
            return discountThree;
        }

        public void setDiscountThree(DiscountItem discountThree) {
            this.discountThree = discountThree;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DiscountItem {

        @JsonProperty("value")
        private BigDecimal value;

        @JsonProperty("limitDate")
        private String limitDate;

        public BigDecimal getValue() {
            return value;
        }

        public void setValue(BigDecimal value) {
            this.value = value;
        }

        public String getLimitDate() {
            return limitDate;
        }

        public void setLimitDate(String limitDate) {
            this.limitDate = limitDate;
        }
    }

    /* =======================================================================
       GETTERS E SETTERS PRINCIPAIS
       ======================================================================= */
    public String getNsuCode() {
        return nsuCode;
    }

    public void setNsuCode(String nsuCode) {
        this.nsuCode = nsuCode;
    }

    public String getNsuDate() {
        return nsuDate;
    }

    public void setNsuDate(String nsuDate) {
        this.nsuDate = nsuDate;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getCovenantCode() {
        return covenantCode;
    }

    public void setCovenantCode(String covenantCode) {
        this.covenantCode = covenantCode;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
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

    public String getDocumentKind() {
        return documentKind;
    }

    public void setDocumentKind(String documentKind) {
        this.documentKind = documentKind;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getBarcode() {
        return barCode;
    }

    public void setBarcode(String barCode) {
        this.barCode = barCode;
    }

    public String getDigitableLine() {
        return digitableLine;
    }

    public void setDigitableLine(String digitableLine) {
        this.digitableLine = digitableLine;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getQrCodePix() {
        return qrCodePix;
    }

    public void setQrCodePix(String qrCodePix) {
        this.qrCodePix = qrCodePix;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
