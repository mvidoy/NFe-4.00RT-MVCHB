package com.backend.dtos;

import com.backend.models.ESTADO;
import com.sun.istack.NotNull;
import java.text.ParseException;
import java.util.List;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ESTADOdto {

    @Id
    private Long oid;
    @NotNull
    @NotEmpty(message = "Sigla é obrigatória")
    private String EST_SIGL;
    @NotNull
    @NotEmpty(message = "Descrição é obrigatória")
    private String EST_DESC;
    @NotEmpty(message = "Código de UF do IBGE (NFe) é obrigatório")
    private String EST_CODIGONFE;
    @Max(value = 20, message = "A aliquota do ICMS (Saída) deve ser <= 20%, verifique!!!")
    @Min(value = 1, message = "A aliquota do ICMS (Saída) deve ser >= 1%, verifique!!!")
    private Double EST_AICM;
    @Max(value = 20, message = "A aliquota do ICMS (Entrada) deve ser <= 20%, verifique!!!")
    @Min(value = 1, message = "A aliquota do ICMS (Entrada) deve ser >= 1%, verifique!!!")
    private Double EST_AIEN;
    private List<ESTADOdto> ESTADOdto;

    public ESTADOdto() {
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getEST_SIGL() {
        return EST_SIGL;
    }

    public void setEST_SIGL(String EST_SIGL) {
        this.EST_SIGL = EST_SIGL;
    }

    public String getEST_DESC() {
        return EST_DESC;
    }

    public void setEST_DESC(String EST_DESC) {
        this.EST_DESC = EST_DESC;
    }

    public String getEST_CODIGONFE() {
        return EST_CODIGONFE;
    }

    public void setEST_CODIGONFE(String EST_CODIGONFE) {
        this.EST_CODIGONFE = EST_CODIGONFE;
    }

    public Double getEST_AICM() {
        return EST_AICM;
    }

    public void setEST_AICM(Double EST_AICM) {
        this.EST_AICM = EST_AICM;
    }

    public Double getEST_AIEN() {
        return EST_AIEN;
    }

    public void setEST_AIEN(Double EST_AIEN) {
        this.EST_AIEN = EST_AIEN;
    }

    public List<ESTADOdto> getESTADOdto() {
        return ESTADOdto;
    }

    public void setESTADOdto(List<ESTADOdto> ESTADOdto) {
        this.ESTADOdto = ESTADOdto;
    }

    public static ESTADOdto convertESTADOtoESTADOdto(ESTADO estado) throws ParseException {
        ESTADOdto formESTADOdto = new ESTADOdto();
        formESTADOdto.setOid(estado.getOid());
        formESTADOdto.setEST_SIGL(estado.getEST_SIGL());
        formESTADOdto.setEST_DESC(estado.getEST_DESC());
        formESTADOdto.setEST_CODIGONFE(estado.getEST_CODIGONFE());
        formESTADOdto.setEST_AICM(estado.getEST_AICM());
        formESTADOdto.setEST_AIEN(estado.getEST_AIEN());
        return formESTADOdto;
    }

    public static ESTADO convertESTADOdtotoESTADO(ESTADOdto ESTADOdto) {
        ESTADO eSTADO = new ESTADO();
        eSTADO.setEST_SIGL(ESTADOdto.getEST_SIGL());
        eSTADO.setEST_DESC(ESTADOdto.getEST_DESC());
        eSTADO.setEST_CODIGONFE(ESTADOdto.getEST_CODIGONFE());
        eSTADO.setEST_AICM(ESTADOdto.getEST_AICM());
        eSTADO.setEST_AIEN(ESTADOdto.getEST_AIEN());
        return eSTADO;
    }
}
