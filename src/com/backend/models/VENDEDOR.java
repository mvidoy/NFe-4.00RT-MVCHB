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
@Table(name = "cavended")
@DynamicUpdate(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VENDEDOR.NamedQueryFindLastGruCodi", query = "SELECT c FROM VENDEDOR c WHERE "
            + "c.VEN_CODI IS NOT NULL ORDER BY VEN_CODI DESC")
    ,
    @NamedQuery(name = "VENDEDOR.NamedQueryFindAll", query = "SELECT c FROM VENDEDOR c WHERE "
            + "c.VEN_NOME IS NOT NULL ORDER BY c.VEN_NOME ASC, c.VEN_CODI ASC")
    ,
    @NamedQuery(name = "VENDEDOR.NamedQueryFindAllLike", query = "SELECT c FROM VENDEDOR c WHERE "
            + "c.VEN_NOME LIKE CONCAT('%',:VEN_NOME,'%') AND c.VEN_NOME IS NOT NULL ORDER BY VEN_NOME ASC")
    ,    
    @NamedQuery(name = "VENDEDOR.findLastVenCodi", query = "SELECT c FROM VENDEDOR c WHERE "
            + "c.VEN_CODI IS NOT NULL ORDER BY VEN_CODI DESC")
    ,
    @NamedQuery(name = "VENDEDOR.findAll", query = "SELECT c FROM VENDEDOR c WHERE "
            + "c.VEN_NOME IS NOT NULL ")
    ,
    @NamedQuery(name = "VENDEDOR.findByVenCodi", query = "SELECT c FROM VENDEDOR c WHERE c.VEN_CODI = :VEN_CODI")
})

@SuppressWarnings("ConsistentAccessType")
public class VENDEDOR implements Serializable {

    @Id@Transient
    private Long oid;
    private String VEN_EMPR;
    @Id
    private String VEN_CODI;
    private String VEN_NOME;
    private String VEN_ENDE;
    private Double VEN_COMI;

    public Long getoid() {
        return oid;
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

}
