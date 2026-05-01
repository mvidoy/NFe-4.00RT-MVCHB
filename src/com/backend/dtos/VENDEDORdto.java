package com.backend.dtos;

import com.backend.models.VENDEDOR;
import com.sun.istack.NotNull;
import java.text.ParseException;
import java.util.List;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class VENDEDORdto {

    @Id
    private Long oid;
    private String VEN_EMPR;
    @NotNull
    private String VEN_CODI;
    @NotNull
    @NotEmpty(message = "Nome é obrigatório")
    private String VEN_NOME;
    private String VEN_ENDE;
    private Double VEN_COMI;
    private List<VENDEDORdto> VENDEDORdto;

    public VENDEDORdto() {
    }

    public Long getoid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getVEN_EMPR() {
        return VEN_EMPR;
    }

    public void setVEN_EMPR(String VEN_EMPR) {
        this.VEN_EMPR = VEN_EMPR;
    }

    public String getVEN_CODI() {
        return VEN_CODI;
    }

    public void setVEN_CODI(String VEN_CODI) {
        this.VEN_CODI = VEN_CODI;
    }

    public String getVEN_NOME() {
        return VEN_NOME;
    }

    public void setVEN_NOME(String VEN_NOME) {
        this.VEN_NOME = VEN_NOME;
    }

    public String getVEN_ENDE() {
        return VEN_ENDE;
    }

    public void setVEN_ENDE(String VEN_ENDE) {
        this.VEN_ENDE = VEN_ENDE;
    }

    public Double getVEN_COMI() {
        return VEN_COMI;
    }

    public void setVEN_COMI(Double VEN_COMI) {
        this.VEN_COMI = VEN_COMI;
    }

    public List<VENDEDORdto> getVENDEDORdto() {
        return VENDEDORdto;
    }

    public void setVENDEDORdto(List<VENDEDORdto> VENDEDORdto) {
        this.VENDEDORdto = VENDEDORdto;
    }

    public static VENDEDORdto convertVENDEDORtoVENDEDORdto(VENDEDOR cliente) throws ParseException {
        VENDEDORdto formVENDEDORdto = new VENDEDORdto();
        formVENDEDORdto.setVEN_CODI(cliente.getVEN_CODI());
        formVENDEDORdto.setVEN_CODI(cliente.getVEN_CODI());
        formVENDEDORdto.setVEN_NOME(cliente.getVEN_NOME());
        formVENDEDORdto.setVEN_ENDE(cliente.getVEN_ENDE());
        formVENDEDORdto.setVEN_COMI(cliente.getVEN_COMI());
        return formVENDEDORdto;
    }

    public static VENDEDOR convertVENDEDORdtotoVENDEDOR(VENDEDORdto VENDEDORdto) {
        VENDEDOR vENDEDOR = new VENDEDOR();
        vENDEDOR.setVEN_CODI(VENDEDORdto.getVEN_CODI());
        vENDEDOR.setVEN_NOME(VENDEDORdto.getVEN_NOME());
        vENDEDOR.setVEN_ENDE(VENDEDORdto.getVEN_ENDE());
        vENDEDOR.setVEN_COMI(VENDEDORdto.getVEN_COMI());
        return vENDEDOR;
    }

}
