package com.backend.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "nfe_infprot")
@DynamicUpdate(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NFEINFPROT.NamedQueryFindByPROTNFE_NNF",
            query = "SELECT n FROM NFEINFPROT n WHERE "
            + "n.PROTNFE_NNF = :PROTNFE_NNF")
})
public class NFEINFPROT implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer PROTNFE_NNF;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
