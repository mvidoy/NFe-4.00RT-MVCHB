package com.backend.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "catransp")
@DynamicUpdate(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRANSPORTADORA.findLastTraCodi", query = "SELECT c FROM TRANSPORTADORA c WHERE "
            + "c.TRA_CODI IS NOT NULL ORDER BY TRA_CODI DESC")
    ,
    @NamedQuery(name = "TRANSPORTADORA.findAll", query = "SELECT c FROM TRANSPORTADORA c WHERE "
            + "c.TRA_DESC IS NOT NULL AND LENGTH(TRIM(c.TRA_DESC)) > 0 ORDER BY c.TRA_DESC ASC, c.TRA_CODI ASC" )
    ,
    @NamedQuery(name = "TRANSPORTADORA.findByTraCodi", query = "SELECT c FROM TRANSPORTADORA c WHERE c.TRA_CODI = :TRA_CODI")
})

@SuppressWarnings("ConsistentAccessType")
public class TRANSPORTADORA implements Serializable {

    @Transient
    private Long oid;
    @Id
    private String TRA_CODI;
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

    public Long getOid() {
        return oid;
    }

    public String getTRA_CODI() {
        return TRA_CODI;
    }

    public void setTRA_CODI(String TRA_CODI) {
        this.TRA_CODI = TRA_CODI;
    }

    public String getTRA_DESC() {
        return TRA_DESC == null ? "" : TRA_DESC;
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

}
