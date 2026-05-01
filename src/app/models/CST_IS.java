package app.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "cst_is")
@DynamicUpdate(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CST_IS.NamedQueryFindLastCodigo",
            query = "SELECT c FROM CST_IS c WHERE "
            + "c.CST_CODIGO IS NOT NULL "
            + "ORDER BY CST_CODIGO DESC")
    ,
    @NamedQuery(name = "CST_IS.NamedQueryFindAll",
            query = "SELECT c FROM CST_IS c WHERE "
            + "c.CST_DESCRICAO IS NOT NULL "
            + "ORDER BY c.CST_CODIGO ASC, c.CST_DESCRICAO ASC")
    ,
    @NamedQuery(name = "CST_IS.NamedQueryFindAllLike",
            query = "SELECT c FROM CST_IS c WHERE "
            + "c.CST_DESCRICAO LIKE CONCAT('%',:CST_DESCRICAO,'%') "
            + "AND c.CST_DESCRICAO IS NOT NULL "
            + "ORDER BY CST_DESCRICAO ASC")
    ,
    @NamedQuery(name = "CST_IS.NamedQueryFindByCodigo",
            query = "SELECT c FROM CST_IS c WHERE "
            + "c.CST_CODIGO = :CST_CODIGO")
})

@SuppressWarnings("ConsistentAccessType")
public class CST_IS implements Serializable {

    @Id
    private String CST_CODIGO;
    private String CST_DESCRICAO;

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

}
