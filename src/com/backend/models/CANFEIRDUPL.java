package com.backend.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "canfeirdupl")
@DynamicUpdate(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CANFEIRDUPL.NamedQueryFindAllNota",
            query = "SELECT c FROM CANFEIRDUPL c "
            + "WHERE "
            + "c.IRE_CODI IS NOT NULL AND c.IRE_PARC IS NOT NULL AND "
            + "IRE_CODI = :IRE_CODI "
            + "ORDER BY c.IRE_CODI ASC, c.IRE_PARC ASC")
    ,
    @NamedQuery(name = "CANFEIRDUPL.NamedQueryListByNota",
            query = "SELECT c FROM CANFEIRDUPL c "
            + "WHERE "
            + "c.IRE_CODI IS NOT NULL AND c.IRE_PARC IS NOT NULL AND "
            + "IRE_CODI = :IRE_CODI "
            + "ORDER BY c.IRE_CODI ASC, c.IRE_PARC ASC")
    ,
    @NamedQuery(name = "CANFEIRDUPL.NamedQueryFindByNotaParc",
            query = "SELECT c FROM CANFEIRDUPL c "
            + "WHERE "
            + "c.IRE_CODI IS NOT NULL AND c.IRE_PARC IS NOT NULL AND "
            + "IRE_CODI = :IRE_CODI AND "
            + "IRE_PARC = :IRE_PARC "
            + "ORDER BY c.IRE_CODI ASC, c.IRE_PARC ASC")
})
@SuppressWarnings("ConsistentAccessType")
public class CANFEIRDUPL implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long CANFENOTFIS_Id;
    private Long CANFEDUPLIC_Id;
    private String IRE_CODI;
    private String IRE_CLIE;
    private Date IRE_DTPG;
    private String IRE_PARC;
    private String IRE_TIPO;
    private String IRE_CONT;
    private String IRE_DOCU;
    private String IRE_NONU;
    private String IRE_DTPR;
    private String IRE_FLIM;
    private String IRE_DCDE;
    private String IRE_CHEQ;
    private Double IRE_VLPG;
    private Double IRE_VLDE;
    private Double IRE_VLVE;
    private Double IRE_VLJU;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Long getCANFENOTFIS_Id() {
        return CANFENOTFIS_Id;
    }

    public void setCANFENOTFIS_Id(Long CANFENOTFIS_Id) {
        this.CANFENOTFIS_Id = CANFENOTFIS_Id;
    }

    public Long getCANFEDUPLIC_Id() {
        return CANFEDUPLIC_Id;
    }

    public void setCANFEDUPLIC_Id(Long CANFEDUPLIC_Id) {
        this.CANFEDUPLIC_Id = CANFEDUPLIC_Id;
    }

    public String getIRE_CODI() {
        return IRE_CODI;
    }

    public void setIRE_CODI(String IRE_CODI) {
        this.IRE_CODI = IRE_CODI;
    }

    public String getIRE_CLIE() {
        return IRE_CLIE;
    }

    public void setIRE_CLIE(String IRE_CLIE) {
        this.IRE_CLIE = IRE_CLIE;
    }

    public Date getIRE_DTPG() {
        return IRE_DTPG;
    }

    public void setIRE_DTPG(Date IRE_DTPG) {
        this.IRE_DTPG = IRE_DTPG;
    }

    public String getIRE_PARC() {
        return IRE_PARC;
    }

    public void setIRE_PARC(String IRE_PARC) {
        this.IRE_PARC = IRE_PARC;
    }

    public String getIRE_TIPO() {
        return IRE_TIPO;
    }

    public void setIRE_TIPO(String IRE_TIPO) {
        this.IRE_TIPO = IRE_TIPO;
    }

    public String getIRE_CONT() {
        return IRE_CONT;
    }

    public void setIRE_CONT(String IRE_CONT) {
        this.IRE_CONT = IRE_CONT;
    }

    public String getIRE_DOCU() {
        return IRE_DOCU;
    }

    public void setIRE_DOCU(String IRE_DOCU) {
        this.IRE_DOCU = IRE_DOCU;
    }

    public String getIRE_NONU() {
        return IRE_NONU;
    }

    public void setIRE_NONU(String IRE_NONU) {
        this.IRE_NONU = IRE_NONU;
    }

    public String getIRE_DTPR() {
        return IRE_DTPR;
    }

    public void setIRE_DTPR(String IRE_DTPR) {
        this.IRE_DTPR = IRE_DTPR;
    }

    public String getIRE_FLIM() {
        return IRE_FLIM;
    }

    public void setIRE_FLIM(String IRE_FLIM) {
        this.IRE_FLIM = IRE_FLIM;
    }

    public String getIRE_DCDE() {
        return IRE_DCDE;
    }

    public void setIRE_DCDE(String IRE_DCDE) {
        this.IRE_DCDE = IRE_DCDE;
    }

    public String getIRE_CHEQ() {
        return IRE_CHEQ;
    }

    public void setIRE_CHEQ(String IRE_CHEQ) {
        this.IRE_CHEQ = IRE_CHEQ;
    }

    public Double getIRE_VLPG() {
        return IRE_VLPG;
    }

    public void setIRE_VLPG(Double IRE_VLPG) {
        this.IRE_VLPG = IRE_VLPG;
    }

    public Double getIRE_VLDE() {
        return IRE_VLDE;
    }

    public void setIRE_VLDE(Double IRE_VLDE) {
        this.IRE_VLDE = IRE_VLDE;
    }

    public Double getIRE_VLVE() {
        return IRE_VLVE;
    }

    public void setIRE_VLVE(Double IRE_VLVE) {
        this.IRE_VLVE = IRE_VLVE;
    }

    public Double getIRE_VLJU() {
        return IRE_VLJU;
    }

    public void setIRE_VLJU(Double IRE_VLJU) {
        this.IRE_VLJU = IRE_VLJU;
    }
}
