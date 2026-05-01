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
@Table(name = "canfenotfis")
@DynamicUpdate(false)
@XmlRootElement

@NamedQueries({
    @NamedQuery(
            name = "CANFENOTFIS.NamedQueryFindByNota",
            query = "SELECT DISTINCT c FROM CANFENOTFIS c "
            + "WHERE c.NOT_NOTA = :NOT_NOTA "
            + "ORDER BY c.NOT_NOTA ASC" 
    )
    ,
    
    @NamedQuery(name = "CANFENOTFIS.NamedQueryFindByNotPedi",
            query = "SELECT c FROM CANFENOTFIS c WHERE "
            + "TRIM(c.NOT_PEDI) = TRIM(:NOT_PEDI)")
    ,  
    
    @NamedQuery(name = "CANFENOTFIS.NamedQueryFindByLastNota",
            query = "SELECT c FROM CANFENOTFIS c "
            + "WHERE "
            + "c.NOT_NOTA IS NOT NULL AND c.NOT_CLIE IS NOT NULL "
            + "ORDER BY NOT_NOTA DESC")
    ,
    
    @NamedQuery(name = "CANFENOTFIS.NamedQueryFindAll",
            query = "SELECT c "
            + "FROM CANFENOTFIS c "
            + "WHERE "
            + "to_char(c.NOT_DTEM,'YYYY/MM/DD') <> '' AND "
            + "c.NOT_SITU IS NOT NULL AND c.NOT_SITU <> '' "
            + "ORDER BY NOT_NOTA DESC, NOT_DTEM DESC")
    ,

    @NamedQuery(name = "CANFENOTFIS.NamedQueryCountFindAll",
            query = "SELECT COUNT(c) AS regCount "
            + "FROM CANFENOTFIS c "
            + "WHERE c.NOT_NOTA <> '' "
            + "AND to_char(c.NOT_DTEM,'YYYY/MM/DD') <> '' "
            + "GROUP BY c.NOT_NOTA "
            + "ORDER BY c.NOT_NOTA DESC")
    ,
   
   @NamedQuery(name = "CANFENOTFIS.NamedQueryCountFindAllLike",
            query = "SELECT COUNT(NTT) AS regCount "
            + "FROM CANFENOTFIS NTT "
            + "WHERE NTT.NOT_CLIE <> '' AND "
            + "NTT.NOT_CLIE IS NOT NULL "
            + "GROUP BY NTT.NOT_NOTA "
            + "ORDER BY NTT.NOT_NOTA DESC")
    ,   
    
    @NamedQuery(name = "CANFENOTFIS.NamedQueryFindAllLike",
            query = "SELECT NTT "
            + "FROM CANFENOTFIS NTT "
            //+ "JOIN CLIENTE CLI ON "
            //+ "CLI.CLI_CODI = PED.PED_CCLI "
            + "WHERE "
            + "NTT.NOT_DTEM IS NOT NULL "
            + "ORDER BY NOT_DTEM DESC, NOT_CLIE DESC, NOT_NOTA DESC")
})

@SuppressWarnings("ConsistentAccessType")
public class CANFENOTFIS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String NOT_NOTA;
    private String NOT_PEDI;

    private String NOT_DOP1;
    private String NOT_DOP2;
    private Double NOT_ALIC;
    private Double NOT_BSIC;
    private Double NOT_VLIC;
    private Double NOT_VLPR;
    private Double NOT_VLFR;
    private Double NOT_VLSE;
    private Double NOT_VLDA;
    private Double NOT_VLIP;
    private Double NOT_VLNO;
    private Double NOT_ABATIMENTO5901;
    private String NOT_PLAC;
    private Double NOT_QTDE;
    private String NOT_ESPE;
    private String NOT_MARC;
    private Double NOT_NUME;
    private Double NOT_PBRU;
    private Double NOT_PLIQ;
    private String NOT_CLIE;
    private String NOT_OBSE;
    //private String NOT_INFCPL;
    private String NOT_NFFO;
    private String NOT_FORN;
    private String NOT_TRAN;
    private String NOT_ESTR;
    private String NOT_FLEM;
    private Integer NOT_EMDE;
    private String NOT_OPER;
    private String NOT_OPE2;
    private Date NOT_DTSA;
    private Date NOT_DTEM;
    private Date NOT_DTFO;
    private String NOT_ENSA;
    private String NOT_TIPP;
    private String NOT_SITU;
    private String NOT_CBTP;
    private String NOT_CBBA;
    private Double NOT_NFEBASEPIS;
    private Double NOT_NFEVALORPIS;
    private Double NOT_NFEBASECOFINS;
    private Double NOT_NFEVALORCOFINS;
    private Integer NOT_CDP1;
    private Integer NOT_CDP2;
    private Integer NOT_CDP3;
    private Integer NOT_CDP4;
    private Integer NOT_CDP5;
    private Integer NOT_CDP6;

    private String NOT_INFNFE_TPAMB;
    private String NOT_INFNFE_TPEMIS;
    private String NOT_INFNFE_TPIMP;
    private String NOT_INFNFE_FINNFE;
    private Integer NOT_NFE_ENVIOEMAIL;

    private String NOT_INFPROT_CHNFE;
    private String NOT_INFPROT_CSTAT;
    private String NOT_INFPROT_XMOTIVO;
    private String NOT_INFPROT_NPROT;
    private String NOT_INFPROT_DHRECBTO;

    private String NOT_INFREC_NREC;
    private String NOT_INFREC_DHRECBTO;

    private String NOT_INFCANC_NPROT;
    private String NOT_INFCANC_DHRECBTO;
    private String NOT_INFCANC_XMOTIVO;
    private String NOT_RETCONSRECINFE_CSTAT;
    private String NOT_RETCONSRECINFE_XMOTIVO;

    /*
    @OneToOne(targetEntity = NFE.class)
    @JoinColumn(name = "not_nota",
            insertable = false,
            updatable = false,
            referencedColumnName = "IDE_NNF",
            nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
     */
    
    
    /*
    @Transient
    private NFE NFE;

    @OneToMany(targetEntity = CANFEDUPLIC.class,
            mappedBy = "CANFENOTFIS_Id",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE,
                CascadeType.PERSIST,
                CascadeType.REMOVE,
                CascadeType.ALL},
            orphanRemoval = true) // Adicione esta linha
    @OrderBy("par_parc ASC")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<CANFEDUPLIC> CANFEDUPLIC;

    @OneToOne
    @JoinColumn(name = "not_clie",
            insertable = false,
            updatable = false,
            referencedColumnName = "CLI_CODI",
            nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private CLIENTE CLIENTE;

    @OneToOne
    @JoinColumn(name = "not_tran",
            insertable = false,
            updatable = false,
            referencedColumnName = "TRA_CODI",
            nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private TRANSPORTADORA TRANSPORTADORA;

*/
    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNOT_NOTA() {
        return NOT_NOTA;
    }

    public void setNOT_NOTA(String NOT_NOTA) {
        this.NOT_NOTA = NOT_NOTA;
    }

    public String getNOT_PEDI() {
        return NOT_PEDI;
    }

    public void setNOT_PEDI(String NOT_PEDI) {
        this.NOT_PEDI = NOT_PEDI;
    }

    public String getNOT_DOP1() {
        return NOT_DOP1;
    }

    public void setNOT_DOP1(String NOT_DOP1) {
        this.NOT_DOP1 = NOT_DOP1;
    }

    public String getNOT_DOP2() {
        return NOT_DOP2;
    }

    public void setNOT_DOP2(String NOT_DOP2) {
        this.NOT_DOP2 = NOT_DOP2;
    }

    public Double getNOT_ALIC() {
        return NOT_ALIC;
    }

    public void setNOT_ALIC(Double NOT_ALIC) {
        this.NOT_ALIC = NOT_ALIC;
    }

    public Double getNOT_BSIC() {
        return NOT_BSIC;
    }

    public void setNOT_BSIC(Double NOT_BSIC) {
        this.NOT_BSIC = NOT_BSIC;
    }

    public Double getNOT_VLIC() {
        return NOT_VLIC;
    }

    public void setNOT_VLIC(Double NOT_VLIC) {
        this.NOT_VLIC = NOT_VLIC;
    }

    public Double getNOT_VLPR() {
        return NOT_VLPR;
    }

    public void setNOT_VLPR(Double NOT_VLPR) {
        this.NOT_VLPR = NOT_VLPR;
    }

    public Double getNOT_VLFR() {
        return NOT_VLFR;
    }

    public void setNOT_VLFR(Double NOT_VLFR) {
        this.NOT_VLFR = NOT_VLFR;
    }

    public Double getNOT_VLSE() {
        return NOT_VLSE;
    }

    public void setNOT_VLSE(Double NOT_VLSE) {
        this.NOT_VLSE = NOT_VLSE;
    }

    public Double getNOT_VLDA() {
        return NOT_VLDA;
    }

    public void setNOT_VLDA(Double NOT_VLDA) {
        this.NOT_VLDA = NOT_VLDA;
    }

    public Double getNOT_VLIP() {
        return NOT_VLIP;
    }

    public void setNOT_VLIP(Double NOT_VLIP) {
        this.NOT_VLIP = NOT_VLIP;
    }

    public Double getNOT_VLNO() {
        return NOT_VLNO;
    }

    public void setNOT_VLNO(Double NOT_VLNO) {
        this.NOT_VLNO = NOT_VLNO;
    }

    public Double getNOT_ABATIMENTO5901() {
        return NOT_ABATIMENTO5901;
    }

    public void setNOT_ABATIMENTO5901(Double NOT_ABATIMENTO5901) {
        this.NOT_ABATIMENTO5901 = NOT_ABATIMENTO5901;
    }

    public String getNOT_PLAC() {
        return NOT_PLAC;
    }

    public void setNOT_PLAC(String NOT_PLAC) {
        this.NOT_PLAC = NOT_PLAC;
    }

    public Double getNOT_QTDE() {
        return NOT_QTDE;
    }

    public void setNOT_QTDE(Double NOT_QTDE) {
        this.NOT_QTDE = NOT_QTDE;
    }

    public String getNOT_ESPE() {
        return NOT_ESPE;
    }

    public void setNOT_ESPE(String NOT_ESPE) {
        this.NOT_ESPE = NOT_ESPE;
    }

    public String getNOT_MARC() {
        return NOT_MARC;
    }

    public void setNOT_MARC(String NOT_MARC) {
        this.NOT_MARC = NOT_MARC;
    }

    public Double getNOT_NUME() {
        return NOT_NUME;
    }

    public void setNOT_NUME(Double NOT_NUME) {
        this.NOT_NUME = NOT_NUME;
    }

    public Double getNOT_PBRU() {
        return NOT_PBRU;
    }

    public void setNOT_PBRU(Double NOT_PBRU) {
        this.NOT_PBRU = NOT_PBRU;
    }

    public Double getNOT_PLIQ() {
        return NOT_PLIQ;
    }

    public void setNOT_PLIQ(Double NOT_PLIQ) {
        this.NOT_PLIQ = NOT_PLIQ;
    }

    public String getNOT_CLIE() {
        return NOT_CLIE;
    }

    public void setNOT_CLIE(String NOT_CLIE) {
        this.NOT_CLIE = NOT_CLIE;
    }

    public String getNOT_OBSE() {
        return NOT_OBSE;
    }

    public void setNOT_OBSE(String NOT_OBSE) {
        this.NOT_OBSE = NOT_OBSE;
    }

    //public String getNOT_INFCPL() {
    //    return NOT_INFCPL;
    //}
    //public void setNOT_INFCPL(String NOT_INFCPL) {
    //    this.NOT_INFCPL = NOT_INFCPL;
    //}
    public String getNOT_NFFO() {
        return NOT_NFFO;
    }

    public void setNOT_NFFO(String NOT_NFFO) {
        this.NOT_NFFO = NOT_NFFO;
    }

    public String getNOT_FORN() {
        return NOT_FORN;
    }

    public void setNOT_FORN(String NOT_FORN) {
        this.NOT_FORN = NOT_FORN;
    }

    public String getNOT_TRAN() {
        return NOT_TRAN;
    }

    public void setNOT_TRAN(String NOT_TRAN) {
        this.NOT_TRAN = NOT_TRAN;
    }

    public String getNOT_ESTR() {
        return NOT_ESTR;
    }

    public void setNOT_ESTR(String NOT_ESTR) {
        this.NOT_ESTR = NOT_ESTR;
    }

    public String getNOT_FLEM() {
        return NOT_FLEM;
    }

    public void setNOT_FLEM(String NOT_FLEM) {
        this.NOT_FLEM = NOT_FLEM;
    }

    public Integer getNOT_EMDE() {
        return NOT_EMDE;
    }

    public void setNOT_EMDE(Integer NOT_EMDE) {
        this.NOT_EMDE = NOT_EMDE;
    }

    public String getNOT_OPER() {
        return NOT_OPER;
    }

    public void setNOT_OPER(String NOT_OPER) {
        this.NOT_OPER = NOT_OPER;
    }

    public String getNOT_OPE2() {
        return NOT_OPE2;
    }

    public void setNOT_OPE2(String NOT_OPE2) {
        this.NOT_OPE2 = NOT_OPE2;
    }

    public Date getNOT_DTSA() {
        return NOT_DTSA;
    }

    public void setNOT_DTSA(Date NOT_DTSA) {
        this.NOT_DTSA = NOT_DTSA;
    }

    public Date getNOT_DTEM() {
        return NOT_DTEM;
    }

    public void setNOT_DTEM(Date NOT_DTEM) {
        this.NOT_DTEM = NOT_DTEM;
    }

    public Date getNOT_DTFO() {
        return NOT_DTFO;
    }

    public void setNOT_DTFO(Date NOT_DTFO) {
        this.NOT_DTFO = NOT_DTFO;
    }

    public String getNOT_ENSA() {
        return NOT_ENSA;
    }

    public void setNOT_ENSA(String NOT_ENSA) {
        this.NOT_ENSA = NOT_ENSA;
    }

    public String getNOT_TIPP() {
        return NOT_TIPP;
    }

    public void setNOT_TIPP(String NOT_TIPP) {
        this.NOT_TIPP = NOT_TIPP;
    }

    public String getNOT_SITU() {
        return NOT_SITU;
    }

    public void setNOT_SITU(String NOT_SITU) {
        this.NOT_SITU = NOT_SITU;
    }

    public String getNOT_CBTP() {
        return NOT_CBTP;
    }

    public void setNOT_CBTP(String NOT_CBTP) {
        this.NOT_CBTP = NOT_CBTP;
    }

    public String getNOT_CBBA() {
        return NOT_CBBA;
    }

    public void setNOT_CBBA(String NOT_CBBA) {
        this.NOT_CBBA = NOT_CBBA;
    }

    public Double getNOT_NFEBASEPIS() {
        return NOT_NFEBASEPIS;
    }

    public void setNOT_NFEBASEPIS(Double NOT_NFEBASEPIS) {
        this.NOT_NFEBASEPIS = NOT_NFEBASEPIS;
    }

    public Double getNOT_NFEVALORPIS() {
        return NOT_NFEVALORPIS;
    }

    public void setNOT_NFEVALORPIS(Double NOT_NFEVALORPIS) {
        this.NOT_NFEVALORPIS = NOT_NFEVALORPIS;
    }

    public Double getNOT_NFEBASECOFINS() {
        return NOT_NFEBASECOFINS;
    }

    public void setNOT_NFEBASECOFINS(Double NOT_NFEBASECOFINS) {
        this.NOT_NFEBASECOFINS = NOT_NFEBASECOFINS;
    }

    public Double getNOT_NFEVALORCOFINS() {
        return NOT_NFEVALORCOFINS;
    }

    public void setNOT_NFEVALORCOFINS(Double NOT_NFEVALORCOFINS) {
        this.NOT_NFEVALORCOFINS = NOT_NFEVALORCOFINS;
    }

    public Integer getNOT_CDP1() {
        return NOT_CDP1;
    }

    public void setNOT_CDP1(Integer NOT_CDP1) {
        this.NOT_CDP1 = NOT_CDP1;
    }

    public Integer getNOT_CDP2() {
        return NOT_CDP2;
    }

    public void setNOT_CDP2(Integer NOT_CDP2) {
        this.NOT_CDP2 = NOT_CDP2;
    }

    public Integer getNOT_CDP3() {
        return NOT_CDP3;
    }

    public void setNOT_CDP3(Integer NOT_CDP3) {
        this.NOT_CDP3 = NOT_CDP3;
    }

    public Integer getNOT_CDP4() {
        return NOT_CDP4;
    }

    public void setNOT_CDP4(Integer NOT_CDP4) {
        this.NOT_CDP4 = NOT_CDP4;
    }

    public Integer getNOT_CDP5() {
        return NOT_CDP5;
    }

    public void setNOT_CDP5(Integer NOT_CDP5) {
        this.NOT_CDP5 = NOT_CDP5;
    }

    public Integer getNOT_CDP6() {
        return NOT_CDP6;
    }

    public void setNOT_CDP6(Integer NOT_CDP6) {
        this.NOT_CDP6 = NOT_CDP6;
    }

    public String getNOT_INFNFE_TPAMB() {
        return NOT_INFNFE_TPAMB;
    }

    public void setNOT_INFNFE_TPAMB(String NOT_INFNFE_TPAMB) {
        this.NOT_INFNFE_TPAMB = NOT_INFNFE_TPAMB;
    }

    public String getNOT_INFNFE_TPEMIS() {
        return NOT_INFNFE_TPEMIS;
    }

    public void setNOT_INFNFE_TPEMIS(String NOT_INFNFE_TPEMIS) {
        this.NOT_INFNFE_TPEMIS = NOT_INFNFE_TPEMIS;
    }

    public String getNOT_INFNFE_TPIMP() {
        return NOT_INFNFE_TPIMP;
    }

    public void setNOT_INFNFE_TPIMP(String NOT_INFNFE_TPIMP) {
        this.NOT_INFNFE_TPIMP = NOT_INFNFE_TPIMP;
    }

    public String getNOT_INFNFE_FINNFE() {
        return NOT_INFNFE_FINNFE;
    }

    public void setNOT_INFNFE_FINNFE(String NOT_INFNFE_FINNFE) {
        this.NOT_INFNFE_FINNFE = NOT_INFNFE_FINNFE;
    }

    public Integer getNOT_NFE_ENVIOEMAIL() {
        return NOT_NFE_ENVIOEMAIL;
    }

    public void setNOT_NFE_ENVIOEMAIL(Integer NOT_NFE_ENVIOEMAIL) {
        this.NOT_NFE_ENVIOEMAIL = NOT_NFE_ENVIOEMAIL;
    }

    public String getNOT_INFPROT_CHNFE() {
        return NOT_INFPROT_CHNFE;
    }

    public void setNOT_INFPROT_CHNFE(String NOT_INFPROT_CHNFE) {
        this.NOT_INFPROT_CHNFE = NOT_INFPROT_CHNFE;
    }

    public String getNOT_INFPROT_CSTAT() {
        return NOT_INFPROT_CSTAT;
    }

    public void setNOT_INFPROT_CSTAT(String NOT_INFPROT_CSTAT) {
        this.NOT_INFPROT_CSTAT = NOT_INFPROT_CSTAT;
    }

    public String getNOT_INFPROT_XMOTIVO() {
        return NOT_INFPROT_XMOTIVO;
    }

    public void setNOT_INFPROT_XMOTIVO(String NOT_INFPROT_XMOTIVO) {
        this.NOT_INFPROT_XMOTIVO = NOT_INFPROT_XMOTIVO;
    }

    public String getNOT_INFPROT_NPROT() {
        return NOT_INFPROT_NPROT;
    }

    public void setNOT_INFPROT_NPROT(String NOT_INFPROT_NPROT) {
        this.NOT_INFPROT_NPROT = NOT_INFPROT_NPROT;
    }

    public String getNOT_INFPROT_DHRECBTO() {
        return NOT_INFPROT_DHRECBTO;
    }

    public void setNOT_INFPROT_DHRECBTO(String NOT_INFPROT_DHRECBTO) {
        this.NOT_INFPROT_DHRECBTO = NOT_INFPROT_DHRECBTO;
    }

    public String getNOT_INFREC_NREC() {
        return NOT_INFREC_NREC;
    }

    public void setNOT_INFREC_NREC(String NOT_INFREC_NREC) {
        this.NOT_INFREC_NREC = NOT_INFREC_NREC;
    }

    public String getNOT_INFREC_DHRECBTO() {
        return NOT_INFREC_DHRECBTO;
    }

    public void setNOT_INFREC_DHRECBTO(String NOT_INFREC_DHRECBTO) {
        this.NOT_INFREC_DHRECBTO = NOT_INFREC_DHRECBTO;
    }

    public String getNOT_INFCANC_NPROT() {
        return NOT_INFCANC_NPROT;
    }

    public void setNOT_INFCANC_NPROT(String NOT_INFCANC_NPROT) {
        this.NOT_INFCANC_NPROT = NOT_INFCANC_NPROT;
    }

    public String getNOT_INFCANC_DHRECBTO() {
        return NOT_INFCANC_DHRECBTO;
    }

    public void setNOT_INFCANC_DHRECBTO(String NOT_INFCANC_DHRECBTO) {
        this.NOT_INFCANC_DHRECBTO = NOT_INFCANC_DHRECBTO;
    }

    public String getNOT_INFCANC_XMOTIVO() {
        return NOT_INFCANC_XMOTIVO;
    }

    public void setNOT_INFCANC_XMOTIVO(String NOT_INFCANC_XMOTIVO) {
        this.NOT_INFCANC_XMOTIVO = NOT_INFCANC_XMOTIVO;
    }

    public String getNOT_RETCONSRECINFE_CSTAT() {
        return NOT_RETCONSRECINFE_CSTAT;
    }

    public void setNOT_RETCONSRECINFE_CSTAT(String NOT_RETCONSRECINFE_CSTAT) {
        this.NOT_RETCONSRECINFE_CSTAT = NOT_RETCONSRECINFE_CSTAT;
    }

    public String getNOT_RETCONSRECINFE_XMOTIVO() {
        return NOT_RETCONSRECINFE_XMOTIVO;
    }

    public void setNOT_RETCONSRECINFE_XMOTIVO(String NOT_RETCONSRECINFE_XMOTIVO) {
        this.NOT_RETCONSRECINFE_XMOTIVO = NOT_RETCONSRECINFE_XMOTIVO;
    }

    //public NFE getNFE() {
    //    return NFE;
    //}

    //public void setNFE(NFE NFE) {
    //    this.NFE = NFE;
    //}

    //public CAENTPRO getCAENTPRO() {
    //    return CAENTPRO;
    //}
    //public void setCAENTPRO(CAENTPRO CAENTPRO) {
    //    this.CAENTPRO = CAENTPRO;
    //}
    //public CLIENTE getCLIENTE() {
    //    return CLIENTE;
    //}

    //public void setCLIENTE(CLIENTE CLIENTE) {
    //    this.CLIENTE = CLIENTE;
   // }

    //public TRANSPORTADORA getTRANSPORTADORA() {
   //    return TRANSPORTADORA;
    //}

    //public void setTRANSPORTADORA(TRANSPORTADORA TRANSPORTADORA) {
    //    this.TRANSPORTADORA = TRANSPORTADORA;
    //}

    //public List<CANFEDUPLIC> getCANFEDUPLIC() {
   //     return CANFEDUPLIC;
    //}

    //public void setCANFEDUPLIC(List<CANFEDUPLIC> CANFEDUPLIC) {
   //     this.CANFEDUPLIC = CANFEDUPLIC;
    //}

}
