package com.backend.dtos;

import com.backend.models.TRANSPORTADORA;
import com.sun.istack.NotNull;
import java.text.ParseException;
import java.util.List;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import static util.ObjetoUtil.NullToBlank;

public class TRANSPORTADORAdto {

    @Id
    private Long oid;
    private String TRA_CODI;
    @NotNull
    @NotEmpty(message = "Descrição é obrigatória")
    private String TRA_DESC;
    private String TRA_ENDE;
    private String TRA_BAIR;
    private String TRA_ESTA;
    private String TRA_CIDA;
    private String TRA_CEP0;
    private String TRA_CGC0;
    private String TRA_INSE;
    private String TRA_FONE;
    private String TRA_FAX0;
    private List<TRANSPORTADORA> Transportadoras;
    private List<TRANSPORTADORAdto> TRANSPORTADORAdto;

    public TRANSPORTADORAdto() {
    }

    public Long getoid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getTRA_CODI() {
        return TRA_CODI;
    }

    public void setTRA_CODI(String TRA_CODI) {
        this.TRA_CODI = TRA_CODI;
    }

    public String getTRA_DESC() {
        return TRA_DESC;
    }

    public void setTRA_DESC(String TRA_DESC) {
        this.TRA_DESC = TRA_DESC;
    }

    public String getTRA_ENDE() {
        return TRA_ENDE;
    }

    public void setTRA_ENDE(String TRA_ENDE) {
        this.TRA_ENDE = TRA_ENDE;
    }

    public String getTRA_BAIR() {
        return TRA_BAIR;
    }

    public void setTRA_BAIR(String TRA_BAIR) {
        this.TRA_BAIR = TRA_BAIR;
    }

    public String getTRA_ESTA() {
        return TRA_ESTA;
    }

    public void setTRA_ESTA(String TRA_ESTA) {
        this.TRA_ESTA = TRA_ESTA;
    }

    public String getTRA_CIDA() {
        return TRA_CIDA;
    }

    public void setTRA_CIDA(String TRA_CIDA) {
        this.TRA_CIDA = TRA_CIDA;
    }

    public String getTRA_CEP0() {
        return TRA_CEP0;
    }

    public void setTRA_CEP0(String TRA_CEP0) {
        this.TRA_CEP0 = TRA_CEP0;
    }

    public String getTRA_CGC0() {
        return TRA_CGC0;
    }

    public void setTRA_CGC0(String TRA_CGC0) {
        this.TRA_CGC0 = TRA_CGC0;
    }

    public String getTRA_INSE() {
        return TRA_INSE;
    }

    public void setTRA_INSE(String TRA_INSE) {
        this.TRA_INSE = TRA_INSE;
    }

    public String getTRA_FONE() {
        return TRA_FONE;
    }

    public void setTRA_FONE(String TRA_FONE) {
        this.TRA_FONE = TRA_FONE;
    }

    public String getTRA_FAX0() {
        return TRA_FAX0;
    }

    public void setTRA_FAX0(String TRA_FAX0) {
        this.TRA_FAX0 = TRA_FAX0;
    }

    public List<TRANSPORTADORA> getTransportadoras() {
        return Transportadoras;
    }

    public void setTransportadoras(List<TRANSPORTADORA> Transportadoras) {
        this.Transportadoras = Transportadoras;
    }

    public List<TRANSPORTADORAdto> getTRANSPORTADORAdto() {
        return TRANSPORTADORAdto;
    }

    public void setTRANSPORTADORAdto(List<TRANSPORTADORAdto> TRANSPORTADORAdto) {
        this.TRANSPORTADORAdto = TRANSPORTADORAdto;
    }

    public static TRANSPORTADORAdto convertTRANSPORTADORAtoTRANSPORTADORAdto(TRANSPORTADORA transportadora) throws ParseException {
        TRANSPORTADORAdto formTRANSPORTADORAdto = new TRANSPORTADORAdto();
        formTRANSPORTADORAdto.setOid(transportadora.getOid());
        formTRANSPORTADORAdto.setTRA_CODI(NullToBlank(transportadora.getTRA_CODI()));
        formTRANSPORTADORAdto.setTRA_DESC(NullToBlank(transportadora.getTRA_DESC()));
        formTRANSPORTADORAdto.setTRA_ENDE(NullToBlank(transportadora.getTRA_ENDE()));
        formTRANSPORTADORAdto.setTRA_BAIR(NullToBlank(transportadora.getTRA_BAIR()));
        formTRANSPORTADORAdto.setTRA_ESTA(NullToBlank(transportadora.getTRA_ESTA()));
        formTRANSPORTADORAdto.setTRA_CIDA(NullToBlank(transportadora.getTRA_CIDA()));
        formTRANSPORTADORAdto.setTRA_CEP0(NullToBlank(transportadora.getTRA_CEP0()));
        formTRANSPORTADORAdto.setTRA_CGC0(NullToBlank(transportadora.getTRA_CGC0()));
        formTRANSPORTADORAdto.setTRA_INSE(NullToBlank(transportadora.getTRA_INSE()));
        formTRANSPORTADORAdto.setTRA_FONE(NullToBlank(transportadora.getTRA_FONE()));
        formTRANSPORTADORAdto.setTRA_FAX0(NullToBlank(transportadora.getTRA_FAX0()));
        return formTRANSPORTADORAdto;
    }

    public static TRANSPORTADORA convertTRANSPORTADORAdtotoTRANSPORTADORA(TRANSPORTADORAdto TRANSPORTADORAdto) {
        TRANSPORTADORA vENDEDOR = new TRANSPORTADORA();
        vENDEDOR.setTRA_CODI(TRANSPORTADORAdto.getTRA_CODI());
        vENDEDOR.setTRA_DESC(TRANSPORTADORAdto.getTRA_DESC());
        vENDEDOR.setTRA_ENDE(TRANSPORTADORAdto.getTRA_ENDE());
        vENDEDOR.setTRA_BAIR(TRANSPORTADORAdto.getTRA_BAIR());
        vENDEDOR.setTRA_ESTA(TRANSPORTADORAdto.getTRA_ESTA());
        vENDEDOR.setTRA_CIDA(TRANSPORTADORAdto.getTRA_CIDA());
        vENDEDOR.setTRA_CEP0(TRANSPORTADORAdto.getTRA_CEP0());
        vENDEDOR.setTRA_CGC0(TRANSPORTADORAdto.getTRA_CGC0());
        vENDEDOR.setTRA_INSE(TRANSPORTADORAdto.getTRA_INSE());
        vENDEDOR.setTRA_FONE(TRANSPORTADORAdto.getTRA_FONE());
        vENDEDOR.setTRA_FAX0(TRANSPORTADORAdto.getTRA_FAX0());
        return vENDEDOR;
    }

}
