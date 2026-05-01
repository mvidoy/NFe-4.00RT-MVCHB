package com.backend.api.cobranca.santander.dtos;

import java.math.BigDecimal;
import java.util.List;

public class BodyJsonREGISTRODEBOLETOSANTANDERdto {

    private String environment;               // TESTE / PRODUCAO
    private String nsuCode;                   // X(20)
    private String nsuDate;                   // AAAA-MM-DD
    private String covenantCode;              // 9(09)
    private String bankNumber;                // 9(13)
    private String clientNumber;              // X(15)
    private String dueDate;                   // AAAA-MM-DD
    private String issueDate;                 // AAAA-MM-DD
    private String participantCode;           // X(25)

    // ? AJUSTADO AQUI
    private BigDecimal nominalValue;          // 9(13)V99

    private Payer payer;
    private Beneficiary beneficiary;
    private String documentKind;              // DUPLICATA_MERCANTIL etc
    private Discount discount;
    private String finePercentage;            // 9(03)V99
    private String fineQuantityDays;          // 9(02)
    private String fineDate;                  // AAAA-MM-DD
    private String interestPercentage;        // 9(03)V99
    private String deductionValue;            // 9(13)V99
    private String protestType;
    private String protestQuantityDays;       // 9(02)
    private String writeOffQuantityDays;      // 9(02)
    private String paymentType;               // REGISTRO / PARCIAL / DIVERGENTE
    private String parcelsQuantity;           // 9(02)
    private String valueType;
    private String minValueOrPercentage;      // 9(10)V99999
    private String maxValueOrPercentage;      // 9(10)V99999
    private String iofPercentage;             // 9(03)V99
    private List<Sharing> sharing;
    private Key key;
    private String txId;                      // X(35)
    private List<String> messages;            // X(100)

    // =========================
    // GETTERS E SETTERS
    // =========================
    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

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

    public String getParticipantCode() {
        return participantCode;
    }

    public void setParticipantCode(String participantCode) {
        this.participantCode = participantCode;
    }

    // ? AJUSTADO AQUI
    public BigDecimal getNominalValue() {
        return nominalValue;
    }

    public void setNominalValue(BigDecimal nominalValue) {
        this.nominalValue = nominalValue;
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

    public String getDocumentKind() {
        return documentKind;
    }

    public void setDocumentKind(String documentKind) {
        this.documentKind = documentKind;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public String getFinePercentage() {
        return finePercentage;
    }

    public void setFinePercentage(String finePercentage) {
        this.finePercentage = finePercentage;
    }

    public String getFineQuantityDays() {
        return fineQuantityDays;
    }

    public void setFineQuantityDays(String fineQuantityDays) {
        this.fineQuantityDays = fineQuantityDays;
    }

    public String getFineDate() {
        return fineDate;
    }

    public void setFineDate(String fineDate) {
        this.fineDate = fineDate;
    }

    public String getInterestPercentage() {
        return interestPercentage;
    }

    public void setInterestPercentage(String interestPercentage) {
        this.interestPercentage = interestPercentage;
    }

    public String getDeductionValue() {
        return deductionValue;
    }

    public void setDeductionValue(String deductionValue) {
        this.deductionValue = deductionValue;
    }

    public String getProtestType() {
        return protestType;
    }

    public void setProtestType(String protestType) {
        this.protestType = protestType;
    }

    public String getProtestQuantityDays() {
        return protestQuantityDays;
    }

    public void setProtestQuantityDays(String protestQuantityDays) {
        this.protestQuantityDays = protestQuantityDays;
    }

    public String getWriteOffQuantityDays() {
        return writeOffQuantityDays;
    }

    public void setWriteOffQuantityDays(String writeOffQuantityDays) {
        this.writeOffQuantityDays = writeOffQuantityDays;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getParcelsQuantity() {
        return parcelsQuantity;
    }

    public void setParcelsQuantity(String parcelsQuantity) {
        this.parcelsQuantity = parcelsQuantity;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getMinValueOrPercentage() {
        return minValueOrPercentage;
    }

    public void setMinValueOrPercentage(String minValueOrPercentage) {
        this.minValueOrPercentage = minValueOrPercentage;
    }

    public String getMaxValueOrPercentage() {
        return maxValueOrPercentage;
    }

    public void setMaxValueOrPercentage(String maxValueOrPercentage) {
        this.maxValueOrPercentage = maxValueOrPercentage;
    }

    public String getIofPercentage() {
        return iofPercentage;
    }

    public void setIofPercentage(String iofPercentage) {
        this.iofPercentage = iofPercentage;
    }

    public List<Sharing> getSharing() {
        return sharing;
    }

    public void setSharing(List<Sharing> sharing) {
        this.sharing = sharing;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
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

    // =========================
    // CLASSES INTERNAS (inalteradas)
    // =========================
    public static class Payer {

        private String name;
        private String documentType;
        private String documentNumber;
        private String address;
        private String neighborhood;
        private String city;
        private String state;
        private String zipCode;

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

    public static class Beneficiary {

        private String name;
        private String documentType;
        private String documentNumber;

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

    public static class Discount {

        private String type;
        private DiscountDetail discountOne;
        private DiscountDetail discountTwo;
        private DiscountDetail discountThree;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public DiscountDetail getDiscountOne() {
            return discountOne;
        }

        public void setDiscountOne(DiscountDetail discountOne) {
            this.discountOne = discountOne;
        }

        public DiscountDetail getDiscountTwo() {
            return discountTwo;
        }

        public void setDiscountTwo(DiscountDetail discountTwo) {
            this.discountTwo = discountTwo;
        }

        public DiscountDetail getDiscountThree() {
            return discountThree;
        }

        public void setDiscountThree(DiscountDetail discountThree) {
            this.discountThree = discountThree;
        }
    }

    public static class DiscountDetail {

        private String value;
        private String limitDate;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getLimitDate() {
            return limitDate;
        }

        public void setLimitDate(String limitDate) {
            this.limitDate = limitDate;
        }
    }

    public static class Sharing {

        private String code;
        private String value;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class Key {

        private String type;
        private String dictKey;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDictKey() {
            return dictKey;
        }

        public void setDictKey(String dictKey) {
            this.dictKey = dictKey;
        }
    }
}
