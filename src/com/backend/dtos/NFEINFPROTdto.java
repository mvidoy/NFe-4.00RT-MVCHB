package com.backend.dtos;

import com.backend.models.NFEINFPROT;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class NFEINFPROTdto {

    private Integer PROTNFE_NNF;
    private Integer PROTNFE_SEQUENCIA;
    private String PROTNFE_VERSAO;
    private String INFPROT_TPAMB;
    private String INFPROT_VERAPLIC;
    private String INFPROT_CHNFE;
    private String INFPROT_DHRECBTO;
    private String INFPROT_NPROT;
    private String INFPROT_NID;
    private String INFPROT_DIGVAL;
    private String INFPROT_CSTAT;
    private String INFPROT_XMOTIVO;
    private String INFPROT_XJUST;
    private String INFPROT_XCORRECAO;
    private String INFPROT_XCONDUSO;
    private List<NFEINFPROTdto> NFEINFPROTdto;

  

    public Integer getPROTNFE_NNF() {
        return PROTNFE_NNF;
    }

    public void setPROTNFE_NNF(Integer PROTNFE_NNF) {
        this.PROTNFE_NNF = PROTNFE_NNF;
    }

    public Integer getPROTNFE_SEQUENCIA() {
        return PROTNFE_SEQUENCIA;
    }

    public void setPROTNFE_SEQUENCIA(Integer PROTNFE_SEQUENCIA) {
        this.PROTNFE_SEQUENCIA = PROTNFE_SEQUENCIA;
    }

    public String getPROTNFE_VERSAO() {
        return PROTNFE_VERSAO;
    }

    public void setPROTNFE_VERSAO(String PROTNFE_VERSAO) {
        this.PROTNFE_VERSAO = PROTNFE_VERSAO;
    }

    public String getINFPROT_TPAMB() {
        return INFPROT_TPAMB;
    }

    public void setINFPROT_TPAMB(String INFPROT_TPAMB) {
        this.INFPROT_TPAMB = INFPROT_TPAMB;
    }

    public String getINFPROT_VERAPLIC() {
        return INFPROT_VERAPLIC;
    }

    public void setINFPROT_VERAPLIC(String INFPROT_VERAPLIC) {
        this.INFPROT_VERAPLIC = INFPROT_VERAPLIC;
    }

    public String getINFPROT_CHNFE() {
        return INFPROT_CHNFE;
    }

    public void setINFPROT_CHNFE(String INFPROT_CHNFE) {
        this.INFPROT_CHNFE = INFPROT_CHNFE;
    }

    public String getINFPROT_DHRECBTO() {
        return INFPROT_DHRECBTO;
    }

    public void setINFPROT_DHRECBTO(String INFPROT_DHRECBTO) {
        this.INFPROT_DHRECBTO = INFPROT_DHRECBTO;
    }

    public String getINFPROT_NPROT() {
        return INFPROT_NPROT;
    }

    public void setINFPROT_NPROT(String INFPROT_NPROT) {
        this.INFPROT_NPROT = INFPROT_NPROT;
    }

    public String getINFPROT_NID() {
        return INFPROT_NID;
    }

    public void setINFPROT_NID(String INFPROT_NID) {
        this.INFPROT_NID = INFPROT_NID;
    }

    public String getINFPROT_DIGVAL() {
        return INFPROT_DIGVAL;
    }

    public void setINFPROT_DIGVAL(String INFPROT_DIGVAL) {
        this.INFPROT_DIGVAL = INFPROT_DIGVAL;
    }

    public String getINFPROT_CSTAT() {
        return INFPROT_CSTAT;
    }

    public void setINFPROT_CSTAT(String INFPROT_CSTAT) {
        this.INFPROT_CSTAT = INFPROT_CSTAT;
    }

    public String getINFPROT_XMOTIVO() {
        return INFPROT_XMOTIVO;
    }

    public void setINFPROT_XMOTIVO(String INFPROT_XMOTIVO) {
        this.INFPROT_XMOTIVO = INFPROT_XMOTIVO;
    }

    public String getINFPROT_XJUST() {
        return INFPROT_XJUST;
    }

    public void setINFPROT_XJUST(String INFPROT_XJUST) {
        this.INFPROT_XJUST = INFPROT_XJUST;
    }

    public String getINFPROT_XCORRECAO() {
        return INFPROT_XCORRECAO;
    }

    public void setINFPROT_XCORRECAO(String INFPROT_XCORRECAO) {
        this.INFPROT_XCORRECAO = INFPROT_XCORRECAO;
    }

    public String getINFPROT_XCONDUSO() {
        return INFPROT_XCONDUSO;
    }

    public void setINFPROT_XCONDUSO(String INFPROT_XCONDUSO) {
        this.INFPROT_XCONDUSO = INFPROT_XCONDUSO;
    }

    public List<NFEINFPROTdto> getNFEINFPROTdto() {
        return NFEINFPROTdto;
    }

    public void setNFEINFPROTdto(List<NFEINFPROTdto> NFEINFPROTdto) {
        this.NFEINFPROTdto = NFEINFPROTdto;
    }

    public static NFEINFPROTdto convertNFEINFPROTtoNFEINFPROTdto(NFEINFPROT nfe_INFPROT) {
        NFEINFPROTdto nfe_INFPROTdto = new NFEINFPROTdto();
        nfe_INFPROTdto.setPROTNFE_NNF(nfe_INFPROT.getPROTNFE_NNF());
        nfe_INFPROTdto.setPROTNFE_SEQUENCIA(nfe_INFPROT.getPROTNFE_SEQUENCIA());
        nfe_INFPROTdto.setPROTNFE_VERSAO(nfe_INFPROT.getPROTNFE_VERSAO().trim());
        nfe_INFPROTdto.setINFPROT_TPAMB(nfe_INFPROT.getINFPROT_TPAMB().trim());
        nfe_INFPROTdto.setINFPROT_VERAPLIC(nfe_INFPROT.getINFPROT_VERAPLIC().trim());
        nfe_INFPROTdto.setINFPROT_CHNFE(nfe_INFPROT.getINFPROT_CHNFE().trim());
        nfe_INFPROTdto.setINFPROT_DHRECBTO(nfe_INFPROT.getINFPROT_DHRECBTO().trim());
        nfe_INFPROTdto.setINFPROT_NPROT(nfe_INFPROT.getINFPROT_NPROT().trim());
        nfe_INFPROTdto.setINFPROT_NID("");
        nfe_INFPROTdto.setINFPROT_DIGVAL("");
        nfe_INFPROTdto.setINFPROT_CSTAT(nfe_INFPROT.getINFPROT_CSTAT().trim());
        nfe_INFPROTdto.setINFPROT_XMOTIVO(nfe_INFPROT.getINFPROT_XMOTIVO().trim());
        nfe_INFPROTdto.setINFPROT_XJUST("");
        nfe_INFPROTdto.setINFPROT_XCORRECAO("");
        nfe_INFPROTdto.setINFPROT_XCONDUSO("");

        return nfe_INFPROTdto;
    }

    public static List<NFEINFPROTdto> convertListNFEINFPROTtoListNFEINFPROTdto(List listNFEINFPROT) throws ParseException {
        List<NFEINFPROTdto> ListNFEINFPROTdto = new ArrayList<>();
        for (int i = 0; i < listNFEINFPROT.size(); i++) {
            NFEINFPROT nfe_INFPROT = (NFEINFPROT) listNFEINFPROT.get(i);
            ListNFEINFPROTdto.add(convertNFEINFPROTtoNFEINFPROTdto(nfe_INFPROT));

        }
        return ListNFEINFPROTdto;
    }

}
