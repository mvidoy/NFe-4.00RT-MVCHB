package com.backend.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "nfe")
@DynamicUpdate(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NFECB.NamedQueryfindAll",
            query = "SELECT n FROM NFE n WHERE "
            + "n.INFNFE_STATUSNFE IS NOT NULL ")
    ,  
    
    @NamedQuery(name = "NFECB.NamedQueryFindAllByDestCnpj",
            query = "SELECT n FROM NFE n WHERE "
            + "n.DEST_CNPJ = :DEST_CNPJ AND n.IDE_TPNF = :IDE_TPNF ORDER BY n.IDE_NNF asc")
    ,
     
    @NamedQuery(
            name = "NFECB.NamedQueryCBFindByIDENNF",
            query = "SELECT nfe FROM NFECB nfe "
            + "WHERE LPAD(TO_CHAR(nfe.IDE_NNF, 'FM000000'), 6, '0') = :IDE_NNF"
    )

})

@SuppressWarnings("ConsistentAccessType")
public class NFECB implements Serializable {

    @Id
    private Long IDE_NNF;
    private String INFNFE_STATUSNFE;
    private String INFNFE_DANFEXMLENVIADO;
    private String INFNFE_TITULOREGISTRADO;
    private String INFNFE_BOLETOENVIADO;

    public Long getIDE_NNF() {
        return IDE_NNF;
    }

    public String getINFNFE_STATUSNFE() {
        return INFNFE_STATUSNFE;
    }

    public String getINFNFE_DANFEXMLENVIADO() {
        return INFNFE_DANFEXMLENVIADO;
    }

    public String getINFNFE_TITULOREGISTRADO() {
        return INFNFE_TITULOREGISTRADO;
    }

    public String getINFNFE_BOLETOENVIADO() {
        return INFNFE_BOLETOENVIADO;
    }

    public void setIDE_NNF(Long IDE_NNF) {
        this.IDE_NNF = IDE_NNF;
    }

    public void setINFNFE_STATUSNFE(String INFNFE_STATUSNFE) {
        this.INFNFE_STATUSNFE = INFNFE_STATUSNFE;
    }

    public void setINFNFE_DANFEXMLENVIADO(String INFNFE_DANFEXMLENVIADO) {
        this.INFNFE_DANFEXMLENVIADO = INFNFE_DANFEXMLENVIADO;
    }

    public void setINFNFE_TITULOREGISTRADO(String INFNFE_TITULOREGISTRADO) {
        this.INFNFE_TITULOREGISTRADO = INFNFE_TITULOREGISTRADO;
    }

    public void setINFNFE_BOLETOENVIADO(String INFNFE_BOLETOENVIADO) {
        this.INFNFE_BOLETOENVIADO = INFNFE_BOLETOENVIADO;
    }

}
