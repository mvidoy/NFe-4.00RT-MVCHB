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
@Table(name = "caestado")
@DynamicUpdate(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ESTADO.NamedQueryfindLastEstSigl", query = "SELECT c FROM ESTADO c WHERE "
            + "c.EST_SIGL IS NOT NULL ORDER BY EST_SIGL DESC")
    ,
    @NamedQuery(name = "ESTADO.NamedQueryFindAllLike", query = "SELECT c FROM ESTADO c WHERE "
            + "c.EST_DESC LIKE CONCAT('%',:EST_DESC,'%') AND c.EST_DESC IS NOT NULL ORDER BY c.EST_DESC ASC, c.EST_SIGL ASC")
    ,
    @NamedQuery(name = "ESTADO.NamedQueryFindAll", query = "SELECT c FROM ESTADO c WHERE "
            + "c.EST_SIGL IS NOT NULL AND c.EST_DESC IS NOT NULL ORDER BY EST_DESC ASC, EST_SIGL ASC")
    ,
    @NamedQuery(name = "ESTADO.NamedQueryfindByEstSigl", query = "SELECT c FROM ESTADO c WHERE c.EST_SIGL = :EST_SIGL AND c.EST_SIGL IS NOT NULL ")
})

@SuppressWarnings("ConsistentAccessType")
public class ESTADO implements Serializable {

    @Transient
    private Long oid;
    @Id
    private String EST_SIGL;
    private String EST_DESC;
    private String EST_CODIGONFE;
    private Double EST_AICM;
    private Double EST_AIEN;

    public Long getOid() {
        return oid;
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
}
