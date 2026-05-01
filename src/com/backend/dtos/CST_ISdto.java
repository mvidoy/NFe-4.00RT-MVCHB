package com.backend.dtos;

import com.sun.istack.NotNull;
import java.text.ParseException;
import java.util.List;
import javax.persistence.Id;
import app.models.CST_IS;

public class CST_ISdto {

    @Id
    private Long oid;
    @NotNull
    private String CST_CODIGO;
    private String CST_DESCRICAO;
    private List<CST_ISdto> CST_ISdto;

    public CST_ISdto() {

    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getCST_CODIGO() {
        return CST_CODIGO;
    }

    public void setCST_CODIGO(String CST_CODIGO) {
        this.CST_CODIGO = CST_CODIGO;
    }

    public String getCST_DESCRICAO() {
        return CST_DESCRICAO;
    }

    public void setCST_DESCRICAO(String CST_DESCRICAO) {
        this.CST_DESCRICAO = CST_DESCRICAO;
    }

    public List<CST_ISdto> getCST_ISdto() {
        return CST_ISdto;
    }

    public void setCST_ISdto(List<CST_ISdto> CST_ISdto) {
        this.CST_ISdto = CST_ISdto;
    }

    public static CST_ISdto convertCST_IStoCST_ISdto(CST_IS cST_IS) throws ParseException {
        CST_ISdto sITUACAOTRIBUTARIAdto = new CST_ISdto();
        sITUACAOTRIBUTARIAdto.setCST_CODIGO(cST_IS.getCST_CODIGO());
        sITUACAOTRIBUTARIAdto.setCST_CODIGO(cST_IS.getCST_CODIGO());
        sITUACAOTRIBUTARIAdto.setCST_DESCRICAO(cST_IS.getCST_DESCRICAO());
        return sITUACAOTRIBUTARIAdto;
    }

    public static CST_IS convertCST_ISdtotoCST_IS(CST_ISdto CST_ISdto) {
        CST_IS sITUACAOTRIBUTARIA = new CST_IS();
        sITUACAOTRIBUTARIA.setCST_CODIGO(CST_ISdto.getCST_CODIGO());
        sITUACAOTRIBUTARIA.setCST_DESCRICAO(CST_ISdto.getCST_DESCRICAO());
        return sITUACAOTRIBUTARIA;
    }

}
