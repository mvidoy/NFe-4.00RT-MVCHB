package com.backend.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "nfe")
@DynamicUpdate(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NFE.NamedQueryfindAll",
            query = "SELECT n FROM NFE n WHERE "
            + "n.INFNFE_STATUSNFE IS NOT NULL ")
    ,  
    
    @NamedQuery(name = "NFE.NamedQueryFindAllByDestCnpj",
            query = "SELECT n FROM NFE n WHERE "
            + "n.DEST_CNPJ = :DEST_CNPJ AND "
            + "n.IDE_TPNF = :IDE_TPNF "
            + "ORDER BY n.IDE_NNF asc")
    ,
    @NamedQuery(
            name = "NFE.NamedQueryFindByIDENNF",
            query = "SELECT nfe FROM NFE nfe WHERE nfe.IDE_NNF = :IDE_NNF"
    )

})

@SuppressWarnings("ConsistentAccessType")
public class NFE implements Serializable {

    @Id
    private Integer IDE_NNF;

    private String INFNFE_STATUSNFE;
    private String INFNFE_VERSAO;
    private String INFNFE_ID;
    private Integer INFNFE_PEDIDOVENDA;
    private String INFNFE_DANFEIMPRESSO;
    private String INFNFE_XMLEXPORTADO;

    private String INFNFE_DANFEXMLENVIADO;
    private String INFNFE_TITULOREGISTRADO;
    private String INFNFE_BOLETOENVIADO;

    private Integer IDE_CUF;
    private String IDE_XUF;
    private Integer IDE_CNF;
    private String IDE_NATOP;
    private Integer IDE_INDPAG;
    private Integer IDE_MOD;
    private Integer IDE_SERIE;

    private Date IDE_DHEMI;
    private Date IDE_DHSAIENT;
    private Integer IDE_TPNF;
    private Integer IDE_IDDEST;
    private Integer IDE_CMUNFG;
    private String IDE_XMUNFG;
    private Integer IDE_CMUNFGIBS;
    private Integer IDE_INDMULTAJUROS;
    private Integer IDE_TPIMP;
    private Integer IDE_TPEMIS;
    private Integer IDE_CDV;
    private Integer IDE_TPAMB;
    private Integer IDE_FINNFE;
    private Integer IDE_TPNFDEBITO;
    private Integer IDE_TPNFCREDITO;
    private Integer IDE_INDFINAL;
    private Integer IDE_INDPRES;
    private Integer IDE_PROCEMI;
    private String IDE_VERPROC;
    private Date IDE_DHCONT;
    private String IDE_XJUST;

    private String EMIT_CNPJ;
    private String EMIT_CPF;
    private String EMIT_XNOME;
    private String EMIT_XFANT;

    private String ENDEREMIT_XLGR;
    private String ENDEREMIT_NRO;
    private String ENDEREMIT_XCPL;
    private String ENDEREMIT_XBAIRRO;
    private Integer ENDEREMIT_CMUN;
    private String ENDEREMIT_XMUN;
    private String ENDEREMIT_UF;
    private Integer ENDEREMIT_CEP;
    private Integer ENDEREMIT_CPAIS;
    private String ENDEREMIT_XPAIS;
    private Long ENDEREMIT_FONE;
    private Long ENDEREMIT_IE;
    private Long ENDEREMIT_IEST;
    private String ENDEREMIT_IM;
    private String ENDEREMIT_CNAE;
    private Integer ENDEREMIT_CRT;

    private String DEST_CNPJ;
    private String DEST_CPF;
    private String DEST_IDESTRANGEIRO;
    private String DEST_XNOME;

    private String ENDERDEST_XLGR;
    private String ENDERDEST_NRO;
    private String ENDERDEST_XCPL;
    private String ENDERDEST_XBAIRRO;
    private Integer ENDERDEST_CMUN;
    private String ENDERDEST_XMUN;
    private String ENDERDEST_UF;
    private Integer ENDERDEST_CEP;
    private Integer ENDERDEST_CPAIS;
    private String ENDERDEST_XPAIS;
    private Long ENDERDEST_FONE;
    private Integer ENDERDEST_INDIEDEST;
    private Long ENDERDEST_IE;
    private Integer ENDERDEST_ISUF;
    private String ENDERDEST_IM;
    private String ENDERDEST_EMAIL;

    private String RETIRADA_CNPJ;
    private String RETIRADA_CPF;
    private String RETIRADA_XLGR;
    private String RETIRADA_NRO;
    private String RETIRADA_XCPL;
    private String RETIRADA_XBAIRRO;
    private Integer RETIRADA_CMUN;
    private String RETIRADA_XMUN;
    private String RETIRADA_UF;

    private String ENTREGA_CNPJ;
    private String ENTREGA_CPF;
    private String ENTREGA_XLGR;
    private String ENTREGA_NRO;
    private String ENTREGA_XCPL;
    private String ENTREGA_XBAIRRO;
    private Integer ENTREGA_CMUN;
    private String ENTREGA_XMUN;
    private String ENTREGA_UF;

    private Double RETENVINFE_VERSAO;
    private Integer RETENVINFE_CSTAT;
    private Date RETENVINFE_DHRECBTO;
    private Integer RETENVINFE_TPAMB;
    private String RETENVINFE_VERAPLIC;
    private String RETENVINFE_XMSG;
    private Integer RETENVINFE_CUF;
    private Double RETENVINFE_TMED;
    private Long RETENVINFE_NREC;
    private Double RETCONSRECINFE_VERSAO;
    private Integer RETCONSRECINFE_CSTAT;
    private Long RETCONSRECINFE_NREC;
    private Date RETCONSRECINFE_DHRECBTO;
    private Integer RETCONSRECINFE_TPAMB;
    private String RETCONSRECINFE_VERAPLIC;
    private String RETCONSRECINFE_XMSG;
    private Integer RETCONSRECINFE_CMSG;
    private String RETCONSRECINFE_XMOTIVO;
    private Integer RETCONSRECINFE_CUF;
    private Long RETCONSRECINFE_PROTNFE_CHNFE;
    private Double RETCONSRECINFE_PROTNFE_VERSAO;
    private Integer RETCONSRECINFE_PROTNFE_TPAMB;
    private String RETCONSRECINFE_PROTNFE_VERAPLIC;
    private Integer RETCONSRECINFE_PROTNFE_CSTAT;
    private String RETCONSRECINFE_PROTNFE_XMOTIVO;
    private Date RETCONSRECINFE_PROTNFE_DHRECBTO;
    private String RETCONSRECINFE_PROTNFE_XMSG;
    private Double PROTNFE_VERSAO;
    private Integer INFPROT_TPAMB;
    private String INFPROT_VERAPLIC;
    private Long INFPROT_CHNFE;
    private Date INFPROT_DHRECBTO;
    private Long INFPROT_NPROT;
    private String INFPROT_DIGVAL;
    private Integer INFPROT_CSTAT;
    private String INFPROT_XMOTIVO;
    private Double ICMSTOT_VBC;
    private Double ICMSTOT_VICMS;
    private Double ICMSTOT_VICMSDESON;
    private Double ICMSTOT_VBCST;
    private Double ICMSTOT_VST;
    private Double ICMSTOT_VPROD;
    private Double ICMSTOT_VFRETE;
    private Double ICMSTOT_VSEG;
    private Double ICMSTOT_VDESC;
    private Double ICMSTOT_VII;
    private Double ICMSTOT_VIPI;
    private Double ICMSTOT_VPIS;
    private Double ICMSTOT_VCOFINS;
    private Double ICMSTOT_VOUTRO;
    private Double ICMSTOT_VNF;
    private Double ICMSTOT_VTOTTRIB;
    private Integer TRANSP_MODFRETE;
    private String TRANSPORTA_CNPJ;
    private String TRANSPORTA_CPF;
    private String TRANSPORTA_XNOME;
    private Long TRANSPORTA_IE;
    private String TRANSPORTA_XENDER;
    private String TRANSPORTA_XMUN;
    private String TRANSPORTA_UF;
    private Long VOL_QVOL;
    private String VOL_ESP;
    private String VOL_MARCA;
    private String VOL_NVOL;
    private Double VOL_PESOL;
    private Double VOL_PESOB;
    private String FAT_NFAT;
    private Double FAT_VORIG;
    private Double FAT_VDESC;
    private Double FAT_VLIQ;
    private String INFADIC_INFADFISCO;
    private String INFADIC_INFCPL;
    private String SIGNATURE_CNPJ;
    private String SIGNATURE_EMISSOR;
    private String SIGNATURE_ASSUNTO;
    private Date SIGNATURE_DATAINICIAL;
    private Date SIGNATURE_DATAFINAL;

    private String XML_ASSINADO;
    private String XML_AUTORIZADO;
    private String XML_ENVEVENTO_ASSINADO;
    private String XML_ENVEVENTO_AUTORIZADO;

    private Double TOT_IS_VBCSEL;
    private Double TOT_IS_VIMPSEL;
    private Double TOT_IBS_UF_VCRESPRES;
    private Double TOT_IBS_UF_VCREDPRESCONDSUS;
    private Double TOT_IBS_UF_VDIF;
    private Double TOT_IBS_UF_VDEVTRIB;
    private Double TOT_IBS_UF_VDESON;
    private Double TOT_IBS_UF_VIBS;
    private Double TOT_IBS_MUN_VCRESPRES;
    private Double TOT_IBS_MUN_VCREDPRESCONDSUS;
    private Double TOT_IBS_MUN_VDIF;
    private Double TOT_IBS_MUN_VDEVTRIB;
    private Double TOT_IBS_MUN_VDESON;
    private Double TOT_IBS_MUN_VIBS;
    private Double TOT_IBS_VCRESPRES;
    private Double TOT_IBS_VCREDPRESCONDSUS;

    private Double TOT_CBS_VCRESPRES;
    private Double TOT_CBS_VCREDPRESCONDSUS;
    private Double TOT_CBS_VDIF;
    private Double TOT_CBS_VDEVTRIB;
    private Double TOT_CBS_VDESON;
    private Double TOT_CBS_VCBS;
    private Double TOT_VBCIBSCBS;
    private Double TOT_VTOTNF;

    //@OneToMany(targetEntity = NFEINFPROT.class)
    //@NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(targetEntity = NFEINFPROT.class,
            mappedBy = "PROTNFE_NNF"
    //cascade = {CascadeType.MERGE,
    //CascadeType.PERSIST,
    //CascadeType.REMOVE,
    //CascadeType.ALL},
    //orphanRemoval = true
    )
    @OrderBy("protnfe_nnf ASC, protnfe_sequencia ASC")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<NFEINFPROT> NFEINFPROT;

    //@OneToMany(targetEntity = NFEINFPROT.class)
    //@NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(targetEntity = NFEDETPROD.class,
            mappedBy = "DET_PROD_NNF"
    //cascade = {CascadeType.MERGE,
    //CascadeType.PERSIST,
    //CascadeType.REMOVE,
    //CascadeType.ALL},
    //orphanRemoval = true
    )
    @OrderBy("det_prod_nnf ASC, det_prod_item ASC")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<NFEDETPROD> NFEDETPROD;

    public String getINFNFE_STATUSNFE() {
        return INFNFE_STATUSNFE;
    }

    public String getINFNFE_VERSAO() {
        return INFNFE_VERSAO;
    }

    public String getINFNFE_ID() {
        return INFNFE_ID;
    }

    public Integer getINFNFE_PEDIDOVENDA() {
        return INFNFE_PEDIDOVENDA;
    }

    public String getINFNFE_DANFEIMPRESSO() {
        return INFNFE_DANFEIMPRESSO;
    }

    public String getINFNFE_XMLEXPORTADO() {
        return INFNFE_XMLEXPORTADO;
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

    public void setINFNFE_DANFEXMLENVIADO(String INFNFE_DANFEXMLENVIADO) {
        this.INFNFE_DANFEXMLENVIADO = INFNFE_DANFEXMLENVIADO;
    }

    public void setINFNFE_TITULOREGISTRADO(String INFNFE_TITULOREGISTRADO) {
        this.INFNFE_TITULOREGISTRADO = INFNFE_TITULOREGISTRADO;
    }

    public void setINFNFE_BOLETOENVIADO(String INFNFE_BOLETOENVIADO) {
        this.INFNFE_BOLETOENVIADO = INFNFE_BOLETOENVIADO;
    }

    public Integer getIDE_CUF() {
        return IDE_CUF;
    }

    public String getIDE_XUF() {
        return IDE_XUF;
    }

    public Integer getIDE_CNF() {
        return IDE_CNF;
    }

    public String getIDE_NATOP() {
        return IDE_NATOP;
    }

    public Integer getIDE_INDPAG() {
        return IDE_INDPAG;
    }

    public Integer getIDE_MOD() {
        return IDE_MOD;
    }

    public Integer getIDE_SERIE() {
        return IDE_SERIE;
    }

    public Integer getIDE_NNF() {
        return IDE_NNF;
    }

    public Date getIDE_DHEMI() {
        return IDE_DHEMI;
    }

    public Date getIDE_DHSAIENT() {
        return IDE_DHSAIENT;
    }

    public Integer getIDE_TPNF() {
        return IDE_TPNF;
    }

    public Integer getIDE_IDDEST() {
        return IDE_IDDEST;
    }

    public Integer getIDE_CMUNFG() {
        return IDE_CMUNFG;
    }

    public String getIDE_XMUNFG() {
        return IDE_XMUNFG;
    }

    public Integer getIDE_TPIMP() {
        return IDE_TPIMP;
    }

    public Integer getIDE_TPEMIS() {
        return IDE_TPEMIS;
    }

    public Integer getIDE_CDV() {
        return IDE_CDV;
    }

    public Integer getIDE_TPAMB() {
        return IDE_TPAMB;
    }

    public Integer getIDE_FINNFE() {
        return IDE_FINNFE;
    }

    public Integer getIDE_INDFINAL() {
        return IDE_INDFINAL;
    }

    public Integer getIDE_INDPRES() {
        return IDE_INDPRES;
    }

    public Integer getIDE_PROCEMI() {
        return IDE_PROCEMI;
    }

    public String getIDE_VERPROC() {
        return IDE_VERPROC;
    }

    public Date getIDE_DHCONT() {
        return IDE_DHCONT;
    }

    public String getIDE_XJUST() {
        return IDE_XJUST;
    }

    public String getEMIT_CNPJ() {
        return EMIT_CNPJ;
    }

    public String getEMIT_CPF() {
        return EMIT_CPF;
    }

    public String getEMIT_XNOME() {
        return EMIT_XNOME;
    }

    public String getEMIT_XFANT() {
        return EMIT_XFANT;
    }

    public String getENDEREMIT_XLGR() {
        return ENDEREMIT_XLGR;
    }

    public String getENDEREMIT_NRO() {
        return ENDEREMIT_NRO;
    }

    public String getENDEREMIT_XCPL() {
        return ENDEREMIT_XCPL;
    }

    public String getENDEREMIT_XBAIRRO() {
        return ENDEREMIT_XBAIRRO;
    }

    public Integer getENDEREMIT_CMUN() {
        return ENDEREMIT_CMUN;
    }

    public String getENDEREMIT_XMUN() {
        return ENDEREMIT_XMUN;
    }

    public String getENDEREMIT_UF() {
        return ENDEREMIT_UF;
    }

    public Integer getENDEREMIT_CEP() {
        return ENDEREMIT_CEP;
    }

    public Integer getENDEREMIT_CPAIS() {
        return ENDEREMIT_CPAIS;
    }

    public String getENDEREMIT_XPAIS() {
        return ENDEREMIT_XPAIS;
    }

    public Long getENDEREMIT_FONE() {
        return ENDEREMIT_FONE;
    }

    public Long getENDEREMIT_IE() {
        return ENDEREMIT_IE;
    }

    public Long getENDEREMIT_IEST() {
        return ENDEREMIT_IEST;
    }

    public String getENDEREMIT_IM() {
        return ENDEREMIT_IM;
    }

    public String getENDEREMIT_CNAE() {
        return ENDEREMIT_CNAE;
    }

    public Integer getENDEREMIT_CRT() {
        return ENDEREMIT_CRT;
    }

    public String getDEST_CNPJ() {
        return DEST_CNPJ;
    }

    public String getDEST_CPF() {
        return DEST_CPF;
    }

    public String getDEST_IDESTRANGEIRO() {
        return DEST_IDESTRANGEIRO;
    }

    public String getDEST_XNOME() {
        return DEST_XNOME;
    }

    public String getENDERDEST_XLGR() {
        return ENDERDEST_XLGR;
    }

    public String getENDERDEST_NRO() {
        return ENDERDEST_NRO;
    }

    public String getENDERDEST_XCPL() {
        return ENDERDEST_XCPL;
    }

    public String getENDERDEST_XBAIRRO() {
        return ENDERDEST_XBAIRRO;
    }

    public Integer getENDERDEST_CMUN() {
        return ENDERDEST_CMUN;
    }

    public String getENDERDEST_XMUN() {
        return ENDERDEST_XMUN;
    }

    public String getENDERDEST_UF() {
        return ENDERDEST_UF;
    }

    public Integer getENDERDEST_CEP() {
        return ENDERDEST_CEP;
    }

    public Integer getENDERDEST_CPAIS() {
        return ENDERDEST_CPAIS;
    }

    public String getENDERDEST_XPAIS() {
        return ENDERDEST_XPAIS;
    }

    public Long getENDERDEST_FONE() {
        return ENDERDEST_FONE;
    }

    public Integer getENDERDEST_INDIEDEST() {
        return ENDERDEST_INDIEDEST;
    }

    public Long getENDERDEST_IE() {
        return ENDERDEST_IE;
    }

    public Integer getENDERDEST_ISUF() {
        return ENDERDEST_ISUF;
    }

    public String getENDERDEST_IM() {
        return ENDERDEST_IM;
    }

    public String getENDERDEST_EMAIL() {
        return ENDERDEST_EMAIL;
    }

    public String getRETIRADA_CNPJ() {
        return RETIRADA_CNPJ;
    }

    public String getRETIRADA_CPF() {
        return RETIRADA_CPF;
    }

    public String getRETIRADA_XLGR() {
        return RETIRADA_XLGR;
    }

    public String getRETIRADA_NRO() {
        return RETIRADA_NRO;
    }

    public String getRETIRADA_XCPL() {
        return RETIRADA_XCPL;
    }

    public String getRETIRADA_XBAIRRO() {
        return RETIRADA_XBAIRRO;
    }

    public Integer getRETIRADA_CMUN() {
        return RETIRADA_CMUN;
    }

    public String getRETIRADA_XMUN() {
        return RETIRADA_XMUN;
    }

    public String getRETIRADA_UF() {
        return RETIRADA_UF;
    }

    public String getENTREGA_CNPJ() {
        return ENTREGA_CNPJ;
    }

    public String getENTREGA_CPF() {
        return ENTREGA_CPF;
    }

    public String getENTREGA_XLGR() {
        return ENTREGA_XLGR;
    }

    public String getENTREGA_NRO() {
        return ENTREGA_NRO;
    }

    public String getENTREGA_XCPL() {
        return ENTREGA_XCPL;
    }

    public String getENTREGA_XBAIRRO() {
        return ENTREGA_XBAIRRO;
    }

    public Integer getENTREGA_CMUN() {
        return ENTREGA_CMUN;
    }

    public String getENTREGA_XMUN() {
        return ENTREGA_XMUN;
    }

    public String getENTREGA_UF() {
        return ENTREGA_UF;
    }

    public Double getRETENVINFE_VERSAO() {
        return RETENVINFE_VERSAO;
    }

    public Integer getRETENVINFE_CSTAT() {
        return RETENVINFE_CSTAT;
    }

    public Date getRETENVINFE_DHRECBTO() {
        return RETENVINFE_DHRECBTO;
    }

    public Integer getRETENVINFE_TPAMB() {
        return RETENVINFE_TPAMB;
    }

    public String getRETENVINFE_VERAPLIC() {
        return RETENVINFE_VERAPLIC;
    }

    public String getRETENVINFE_XMSG() {
        return RETENVINFE_XMSG;
    }

    public Integer getRETENVINFE_CUF() {
        return RETENVINFE_CUF;
    }

    public Double getRETENVINFE_TMED() {
        return RETENVINFE_TMED;
    }

    public Long getRETENVINFE_NREC() {
        return RETENVINFE_NREC;
    }

    public Double getRETCONSRECINFE_VERSAO() {
        return RETCONSRECINFE_VERSAO;
    }

    public Integer getRETCONSRECINFE_CSTAT() {
        return RETCONSRECINFE_CSTAT;
    }

    public Long getRETCONSRECINFE_NREC() {
        return RETCONSRECINFE_NREC;
    }

    public Date getRETCONSRECINFE_DHRECBTO() {
        return RETCONSRECINFE_DHRECBTO;
    }

    public Integer getRETCONSRECINFE_TPAMB() {
        return RETCONSRECINFE_TPAMB;
    }

    public String getRETCONSRECINFE_VERAPLIC() {
        return RETCONSRECINFE_VERAPLIC;
    }

    public String getRETCONSRECINFE_XMSG() {
        return RETCONSRECINFE_XMSG;
    }

    public Integer getRETCONSRECINFE_CMSG() {
        return RETCONSRECINFE_CMSG;
    }

    public String getRETCONSRECINFE_XMOTIVO() {
        return RETCONSRECINFE_XMOTIVO;
    }

    public Integer getRETCONSRECINFE_CUF() {
        return RETCONSRECINFE_CUF;
    }

    public Long getRETCONSRECINFE_PROTNFE_CHNFE() {
        return RETCONSRECINFE_PROTNFE_CHNFE;
    }

    public Double getRETCONSRECINFE_PROTNFE_VERSAO() {
        return RETCONSRECINFE_PROTNFE_VERSAO;
    }

    public Integer getRETCONSRECINFE_PROTNFE_TPAMB() {
        return RETCONSRECINFE_PROTNFE_TPAMB;
    }

    public String getRETCONSRECINFE_PROTNFE_VERAPLIC() {
        return RETCONSRECINFE_PROTNFE_VERAPLIC;
    }

    public Integer getRETCONSRECINFE_PROTNFE_CSTAT() {
        return RETCONSRECINFE_PROTNFE_CSTAT;
    }

    public String getRETCONSRECINFE_PROTNFE_XMOTIVO() {
        return RETCONSRECINFE_PROTNFE_XMOTIVO;
    }

    public Date getRETCONSRECINFE_PROTNFE_DHRECBTO() {
        return RETCONSRECINFE_PROTNFE_DHRECBTO;
    }

    public String getRETCONSRECINFE_PROTNFE_XMSG() {
        return RETCONSRECINFE_PROTNFE_XMSG;
    }

    public Double getPROTNFE_VERSAO() {
        return PROTNFE_VERSAO;
    }

    public Integer getINFPROT_TPAMB() {
        return INFPROT_TPAMB;
    }

    public String getINFPROT_VERAPLIC() {
        return INFPROT_VERAPLIC;
    }

    public Long getINFPROT_CHNFE() {
        return INFPROT_CHNFE;
    }

    public Date getINFPROT_DHRECBTO() {
        return INFPROT_DHRECBTO;
    }

    public Long getINFPROT_NPROT() {
        return INFPROT_NPROT;
    }

    public String getINFPROT_DIGVAL() {
        return INFPROT_DIGVAL;
    }

    public Integer getINFPROT_CSTAT() {
        return INFPROT_CSTAT;
    }

    public String getINFPROT_XMOTIVO() {
        return INFPROT_XMOTIVO;
    }

    public Double getICMSTOT_VBC() {
        return ICMSTOT_VBC;
    }

    public Double getICMSTOT_VICMS() {
        return ICMSTOT_VICMS;
    }

    public Double getICMSTOT_VICMSDESON() {
        return ICMSTOT_VICMSDESON;
    }

    public Double getICMSTOT_VBCST() {
        return ICMSTOT_VBCST;
    }

    public Double getICMSTOT_VST() {
        return ICMSTOT_VST;
    }

    public Double getICMSTOT_VPROD() {
        return ICMSTOT_VPROD;
    }

    public Double getICMSTOT_VFRETE() {
        return ICMSTOT_VFRETE;
    }

    public Double getICMSTOT_VSEG() {
        return ICMSTOT_VSEG;
    }

    public Double getICMSTOT_VDESC() {
        return ICMSTOT_VDESC;
    }

    public Double getICMSTOT_VII() {
        return ICMSTOT_VII;
    }

    public Double getICMSTOT_VIPI() {
        return ICMSTOT_VIPI;
    }

    public Double getICMSTOT_VPIS() {
        return ICMSTOT_VPIS;
    }

    public Double getICMSTOT_VCOFINS() {
        return ICMSTOT_VCOFINS;
    }

    public Double getICMSTOT_VOUTRO() {
        return ICMSTOT_VOUTRO;
    }

    public Double getICMSTOT_VNF() {
        return ICMSTOT_VNF;
    }

    public Double getICMSTOT_VTOTTRIB() {
        return ICMSTOT_VTOTTRIB;
    }

    public Integer getTRANSP_MODFRETE() {
        return TRANSP_MODFRETE;
    }

    public String getTRANSPORTA_CNPJ() {
        return TRANSPORTA_CNPJ;
    }

    public String getTRANSPORTA_CPF() {
        return TRANSPORTA_CPF;
    }

    public String getTRANSPORTA_XNOME() {
        return TRANSPORTA_XNOME;
    }

    public Long getTRANSPORTA_IE() {
        return TRANSPORTA_IE;
    }

    public String getTRANSPORTA_XENDER() {
        return TRANSPORTA_XENDER;
    }

    public String getTRANSPORTA_XMUN() {
        return TRANSPORTA_XMUN;
    }

    public String getTRANSPORTA_UF() {
        return TRANSPORTA_UF;
    }

    public Long getVOL_QVOL() {
        return VOL_QVOL;
    }

    public String getVOL_ESP() {
        return VOL_ESP;
    }

    public String getVOL_MARCA() {
        return VOL_MARCA;
    }

    public String getVOL_NVOL() {
        return VOL_NVOL;
    }

    public Double getVOL_PESOL() {
        return VOL_PESOL;
    }

    public Double getVOL_PESOB() {
        return VOL_PESOB;
    }

    public String getFAT_NFAT() {
        return FAT_NFAT;
    }

    public Double getFAT_VORIG() {
        return FAT_VORIG;
    }

    public Double getFAT_VDESC() {
        return FAT_VDESC;
    }

    public Double getFAT_VLIQ() {
        return FAT_VLIQ;
    }

    public String getINFADIC_INFADFISCO() {
        return INFADIC_INFADFISCO;
    }

    public String getINFADIC_INFCPL() {
        return INFADIC_INFCPL;
    }

    public String getSIGNATURE_CNPJ() {
        return SIGNATURE_CNPJ;
    }

    public String getSIGNATURE_EMISSOR() {
        return SIGNATURE_EMISSOR;
    }

    public String getSIGNATURE_ASSUNTO() {
        return SIGNATURE_ASSUNTO;
    }

    public Date getSIGNATURE_DATAINICIAL() {
        return SIGNATURE_DATAINICIAL;
    }

    public Date getSIGNATURE_DATAFINAL() {
        return SIGNATURE_DATAFINAL;
    }

    public void setRETENVINFE_VERSAO(Double RETENVINFE_VERSAO) {
        this.RETENVINFE_VERSAO = RETENVINFE_VERSAO;
    }

    public void setRETENVINFE_CSTAT(Integer RETENVINFE_CSTAT) {
        this.RETENVINFE_CSTAT = RETENVINFE_CSTAT;
    }

    public void setRETENVINFE_DHRECBTO(Date RETENVINFE_DHRECBTO) {
        this.RETENVINFE_DHRECBTO = RETENVINFE_DHRECBTO;
    }

    public void setRETENVINFE_TPAMB(Integer RETENVINFE_TPAMB) {
        this.RETENVINFE_TPAMB = RETENVINFE_TPAMB;
    }

    public void setRETENVINFE_VERAPLIC(String RETENVINFE_VERAPLIC) {
        this.RETENVINFE_VERAPLIC = RETENVINFE_VERAPLIC;
    }

    public void setRETENVINFE_XMSG(String RETENVINFE_XMSG) {
        this.RETENVINFE_XMSG = RETENVINFE_XMSG;
    }

    public void setRETENVINFE_CUF(Integer RETENVINFE_CUF) {
        this.RETENVINFE_CUF = RETENVINFE_CUF;
    }

    public void setRETENVINFE_TMED(Double RETENVINFE_TMED) {
        this.RETENVINFE_TMED = RETENVINFE_TMED;
    }

    public void setRETENVINFE_NREC(Long RETENVINFE_NREC) {
        this.RETENVINFE_NREC = RETENVINFE_NREC;
    }

    public void setRETCONSRECINFE_VERSAO(Double RETCONSRECINFE_VERSAO) {
        this.RETCONSRECINFE_VERSAO = RETCONSRECINFE_VERSAO;
    }

    public void setRETCONSRECINFE_CSTAT(Integer RETCONSRECINFE_CSTAT) {
        this.RETCONSRECINFE_CSTAT = RETCONSRECINFE_CSTAT;
    }

    public void setRETCONSRECINFE_NREC(Long RETCONSRECINFE_NREC) {
        this.RETCONSRECINFE_NREC = RETCONSRECINFE_NREC;
    }

    public void setRETCONSRECINFE_DHRECBTO(Date RETCONSRECINFE_DHRECBTO) {
        this.RETCONSRECINFE_DHRECBTO = RETCONSRECINFE_DHRECBTO;
    }

    public void setRETCONSRECINFE_TPAMB(Integer RETCONSRECINFE_TPAMB) {
        this.RETCONSRECINFE_TPAMB = RETCONSRECINFE_TPAMB;
    }

    public void setRETCONSRECINFE_VERAPLIC(String RETCONSRECINFE_VERAPLIC) {
        this.RETCONSRECINFE_VERAPLIC = RETCONSRECINFE_VERAPLIC;
    }

    public void setRETCONSRECINFE_XMSG(String RETCONSRECINFE_XMSG) {
        this.RETCONSRECINFE_XMSG = RETCONSRECINFE_XMSG;
    }

    public void setRETCONSRECINFE_CMSG(Integer RETCONSRECINFE_CMSG) {
        this.RETCONSRECINFE_CMSG = RETCONSRECINFE_CMSG;
    }

    public void setRETCONSRECINFE_XMOTIVO(String RETCONSRECINFE_XMOTIVO) {
        this.RETCONSRECINFE_XMOTIVO = RETCONSRECINFE_XMOTIVO;
    }

    public void setRETCONSRECINFE_CUF(Integer RETCONSRECINFE_CUF) {
        this.RETCONSRECINFE_CUF = RETCONSRECINFE_CUF;
    }

    public void setRETCONSRECINFE_PROTNFE_CHNFE(Long RETCONSRECINFE_PROTNFE_CHNFE) {
        this.RETCONSRECINFE_PROTNFE_CHNFE = RETCONSRECINFE_PROTNFE_CHNFE;
    }

    public void setRETCONSRECINFE_PROTNFE_VERSAO(Double RETCONSRECINFE_PROTNFE_VERSAO) {
        this.RETCONSRECINFE_PROTNFE_VERSAO = RETCONSRECINFE_PROTNFE_VERSAO;
    }

    public void setRETCONSRECINFE_PROTNFE_TPAMB(Integer RETCONSRECINFE_PROTNFE_TPAMB) {
        this.RETCONSRECINFE_PROTNFE_TPAMB = RETCONSRECINFE_PROTNFE_TPAMB;
    }

    public void setRETCONSRECINFE_PROTNFE_VERAPLIC(String RETCONSRECINFE_PROTNFE_VERAPLIC) {
        this.RETCONSRECINFE_PROTNFE_VERAPLIC = RETCONSRECINFE_PROTNFE_VERAPLIC;
    }

    public void setRETCONSRECINFE_PROTNFE_CSTAT(Integer RETCONSRECINFE_PROTNFE_CSTAT) {
        this.RETCONSRECINFE_PROTNFE_CSTAT = RETCONSRECINFE_PROTNFE_CSTAT;
    }

    public void setRETCONSRECINFE_PROTNFE_XMOTIVO(String RETCONSRECINFE_PROTNFE_XMOTIVO) {
        this.RETCONSRECINFE_PROTNFE_XMOTIVO = RETCONSRECINFE_PROTNFE_XMOTIVO;
    }

    public void setRETCONSRECINFE_PROTNFE_DHRECBTO(Date RETCONSRECINFE_PROTNFE_DHRECBTO) {
        this.RETCONSRECINFE_PROTNFE_DHRECBTO = RETCONSRECINFE_PROTNFE_DHRECBTO;
    }

    public void setRETCONSRECINFE_PROTNFE_XMSG(String RETCONSRECINFE_PROTNFE_XMSG) {
        this.RETCONSRECINFE_PROTNFE_XMSG = RETCONSRECINFE_PROTNFE_XMSG;
    }

    public void setPROTNFE_VERSAO(Double PROTNFE_VERSAO) {
        this.PROTNFE_VERSAO = PROTNFE_VERSAO;
    }

    public void setINFPROT_TPAMB(Integer INFPROT_TPAMB) {
        this.INFPROT_TPAMB = INFPROT_TPAMB;
    }

    public void setINFPROT_VERAPLIC(String INFPROT_VERAPLIC) {
        this.INFPROT_VERAPLIC = INFPROT_VERAPLIC;
    }

    public void setINFPROT_CHNFE(Long INFPROT_CHNFE) {
        this.INFPROT_CHNFE = INFPROT_CHNFE;
    }

    public void setINFPROT_DHRECBTO(Date INFPROT_DHRECBTO) {
        this.INFPROT_DHRECBTO = INFPROT_DHRECBTO;
    }

    public void setINFPROT_NPROT(Long INFPROT_NPROT) {
        this.INFPROT_NPROT = INFPROT_NPROT;
    }

    public void setINFPROT_DIGVAL(String INFPROT_DIGVAL) {
        this.INFPROT_DIGVAL = INFPROT_DIGVAL;
    }

    public void setINFPROT_CSTAT(Integer INFPROT_CSTAT) {
        this.INFPROT_CSTAT = INFPROT_CSTAT;
    }

    public void setINFPROT_XMOTIVO(String INFPROT_XMOTIVO) {
        this.INFPROT_XMOTIVO = INFPROT_XMOTIVO;
    }

    public void setICMSTOT_VBC(Double ICMSTOT_VBC) {
        this.ICMSTOT_VBC = ICMSTOT_VBC;
    }

    public void setICMSTOT_VICMS(Double ICMSTOT_VICMS) {
        this.ICMSTOT_VICMS = ICMSTOT_VICMS;
    }

    public void setICMSTOT_VICMSDESON(Double ICMSTOT_VICMSDESON) {
        this.ICMSTOT_VICMSDESON = ICMSTOT_VICMSDESON;
    }

    public void setICMSTOT_VBCST(Double ICMSTOT_VBCST) {
        this.ICMSTOT_VBCST = ICMSTOT_VBCST;
    }

    public void setICMSTOT_VST(Double ICMSTOT_VST) {
        this.ICMSTOT_VST = ICMSTOT_VST;
    }

    public void setICMSTOT_VPROD(Double ICMSTOT_VPROD) {
        this.ICMSTOT_VPROD = ICMSTOT_VPROD;
    }

    public void setICMSTOT_VFRETE(Double ICMSTOT_VFRETE) {
        this.ICMSTOT_VFRETE = ICMSTOT_VFRETE;
    }

    public void setICMSTOT_VSEG(Double ICMSTOT_VSEG) {
        this.ICMSTOT_VSEG = ICMSTOT_VSEG;
    }

    public void setICMSTOT_VDESC(Double ICMSTOT_VDESC) {
        this.ICMSTOT_VDESC = ICMSTOT_VDESC;
    }

    public void setICMSTOT_VII(Double ICMSTOT_VII) {
        this.ICMSTOT_VII = ICMSTOT_VII;
    }

    public void setICMSTOT_VIPI(Double ICMSTOT_VIPI) {
        this.ICMSTOT_VIPI = ICMSTOT_VIPI;
    }

    public void setICMSTOT_VPIS(Double ICMSTOT_VPIS) {
        this.ICMSTOT_VPIS = ICMSTOT_VPIS;
    }

    public void setICMSTOT_VCOFINS(Double ICMSTOT_VCOFINS) {
        this.ICMSTOT_VCOFINS = ICMSTOT_VCOFINS;
    }

    public void setICMSTOT_VOUTRO(Double ICMSTOT_VOUTRO) {
        this.ICMSTOT_VOUTRO = ICMSTOT_VOUTRO;
    }

    public void setICMSTOT_VNF(Double ICMSTOT_VNF) {
        this.ICMSTOT_VNF = ICMSTOT_VNF;
    }

    public void setICMSTOT_VTOTTRIB(Double ICMSTOT_VTOTTRIB) {
        this.ICMSTOT_VTOTTRIB = ICMSTOT_VTOTTRIB;
    }

    public void setTRANSP_MODFRETE(Integer TRANSP_MODFRETE) {
        this.TRANSP_MODFRETE = TRANSP_MODFRETE;
    }

    public void setTRANSPORTA_CNPJ(String TRANSPORTA_CNPJ) {
        this.TRANSPORTA_CNPJ = TRANSPORTA_CNPJ;
    }

    public void setTRANSPORTA_CPF(String TRANSPORTA_CPF) {
        this.TRANSPORTA_CPF = TRANSPORTA_CPF;
    }

    public void setTRANSPORTA_XNOME(String TRANSPORTA_XNOME) {
        this.TRANSPORTA_XNOME = TRANSPORTA_XNOME;
    }

    public void setTRANSPORTA_IE(Long TRANSPORTA_IE) {
        this.TRANSPORTA_IE = TRANSPORTA_IE;
    }

    public void setTRANSPORTA_XENDER(String TRANSPORTA_XENDER) {
        this.TRANSPORTA_XENDER = TRANSPORTA_XENDER;
    }

    public void setTRANSPORTA_XMUN(String TRANSPORTA_XMUN) {
        this.TRANSPORTA_XMUN = TRANSPORTA_XMUN;
    }

    public void setTRANSPORTA_UF(String TRANSPORTA_UF) {
        this.TRANSPORTA_UF = TRANSPORTA_UF;
    }

    public void setVOL_QVOL(Long VOL_QVOL) {
        this.VOL_QVOL = VOL_QVOL;
    }

    public void setVOL_ESP(String VOL_ESP) {
        this.VOL_ESP = VOL_ESP;
    }

    public void setVOL_MARCA(String VOL_MARCA) {
        this.VOL_MARCA = VOL_MARCA;
    }

    public void setVOL_NVOL(String VOL_NVOL) {
        this.VOL_NVOL = VOL_NVOL;
    }

    public void setVOL_PESOL(Double VOL_PESOL) {
        this.VOL_PESOL = VOL_PESOL;
    }

    public void setVOL_PESOB(Double VOL_PESOB) {
        this.VOL_PESOB = VOL_PESOB;
    }

    public void setFAT_NFAT(String FAT_NFAT) {
        this.FAT_NFAT = FAT_NFAT;
    }

    public void setFAT_VORIG(Double FAT_VORIG) {
        this.FAT_VORIG = FAT_VORIG;
    }

    public void setFAT_VDESC(Double FAT_VDESC) {
        this.FAT_VDESC = FAT_VDESC;
    }

    public void setFAT_VLIQ(Double FAT_VLIQ) {
        this.FAT_VLIQ = FAT_VLIQ;
    }

    public void setINFADIC_INFADFISCO(String INFADIC_INFADFISCO) {
        this.INFADIC_INFADFISCO = INFADIC_INFADFISCO;
    }

    public void setINFADIC_INFCPL(String INFADIC_INFCPL) {
        this.INFADIC_INFCPL = INFADIC_INFCPL;
    }

    public void setSIGNATURE_CNPJ(String SIGNATURE_CNPJ) {
        this.SIGNATURE_CNPJ = SIGNATURE_CNPJ;
    }

    public void setSIGNATURE_EMISSOR(String SIGNATURE_EMISSOR) {
        this.SIGNATURE_EMISSOR = SIGNATURE_EMISSOR;
    }

    public void setSIGNATURE_ASSUNTO(String SIGNATURE_ASSUNTO) {
        this.SIGNATURE_ASSUNTO = SIGNATURE_ASSUNTO;
    }

    public void setSIGNATURE_DATAINICIAL(Date SIGNATURE_DATAINICIAL) {
        this.SIGNATURE_DATAINICIAL = SIGNATURE_DATAINICIAL;
    }

    public void setSIGNATURE_DATAFINAL(Date SIGNATURE_DATAFINAL) {
        this.SIGNATURE_DATAFINAL = SIGNATURE_DATAFINAL;
    }

    public void setINFNFE_STATUSNFE(String INFNFE_STATUSNFE) {
        this.INFNFE_STATUSNFE = INFNFE_STATUSNFE;
    }

    public void setINFNFE_VERSAO(String INFNFE_VERSAO) {
        this.INFNFE_VERSAO = INFNFE_VERSAO;
    }

    public void setINFNFE_ID(String INFNFE_ID) {
        this.INFNFE_ID = INFNFE_ID;
    }

    public void setINFNFE_PEDIDOVENDA(Integer INFNFE_PEDIDOVENDA) {
        this.INFNFE_PEDIDOVENDA = INFNFE_PEDIDOVENDA;
    }

    public void setINFNFE_DANFEIMPRESSO(String INFNFE_DANFEIMPRESSO) {
        this.INFNFE_DANFEIMPRESSO = INFNFE_DANFEIMPRESSO;
    }

    public void setINFNFE_XMLEXPORTADO(String INFNFE_XMLEXPORTADO) {
        this.INFNFE_XMLEXPORTADO = INFNFE_XMLEXPORTADO;
    }

    public void setIDE_CUF(Integer IDE_CUF) {
        this.IDE_CUF = IDE_CUF;
    }

    public void setIDE_XUF(String IDE_XUF) {
        this.IDE_XUF = IDE_XUF;
    }

    public void setIDE_CNF(Integer IDE_CNF) {
        this.IDE_CNF = IDE_CNF;
    }

    public void setIDE_NATOP(String IDE_NATOP) {
        this.IDE_NATOP = IDE_NATOP;
    }

    public void setIDE_INDPAG(Integer IDE_INDPAG) {
        this.IDE_INDPAG = IDE_INDPAG;
    }

    public void setIDE_MOD(Integer IDE_MOD) {
        this.IDE_MOD = IDE_MOD;
    }

    public void setIDE_SERIE(Integer IDE_SERIE) {
        this.IDE_SERIE = IDE_SERIE;
    }

    public void setIDE_NNF(Integer IDE_NNF) {
        this.IDE_NNF = IDE_NNF;
    }

    public void setIDE_DHEMI(Date IDE_DHEMI) {
        this.IDE_DHEMI = IDE_DHEMI;
    }

    public void setIDE_DHSAIENT(Date IDE_DHSAIENT) {
        this.IDE_DHSAIENT = IDE_DHSAIENT;
    }

    public void setIDE_TPNF(Integer IDE_TPNF) {
        this.IDE_TPNF = IDE_TPNF;
    }

    public void setIDE_IDDEST(Integer IDE_IDDEST) {
        this.IDE_IDDEST = IDE_IDDEST;
    }

    public void setIDE_CMUNFG(Integer IDE_CMUNFG) {
        this.IDE_CMUNFG = IDE_CMUNFG;
    }

    public void setIDE_XMUNFG(String IDE_XMUNFG) {
        this.IDE_XMUNFG = IDE_XMUNFG;
    }

    public Integer getIDE_CMUNFGIBS() {
        return IDE_CMUNFGIBS;
    }

    public void setIDE_CMUNFGIBS(Integer IDE_CMUNFGIBS) {
        this.IDE_CMUNFGIBS = IDE_CMUNFGIBS;
    }

    public Integer getIDE_INDMULTAJUROS() {
        return IDE_INDMULTAJUROS;
    }

    public void setIDE_INDMULTAJUROS(Integer IDE_INDMULTAJUROS) {
        this.IDE_INDMULTAJUROS = IDE_INDMULTAJUROS;
    }

    public void setIDE_TPIMP(Integer IDE_TPIMP) {
        this.IDE_TPIMP = IDE_TPIMP;
    }

    public void setIDE_TPEMIS(Integer IDE_TPEMIS) {
        this.IDE_TPEMIS = IDE_TPEMIS;
    }

    public void setIDE_CDV(Integer IDE_CDV) {
        this.IDE_CDV = IDE_CDV;
    }

    public void setIDE_TPAMB(Integer IDE_TPAMB) {
        this.IDE_TPAMB = IDE_TPAMB;
    }

    public void setIDE_FINNFE(Integer IDE_FINNFE) {
        this.IDE_FINNFE = IDE_FINNFE;
    }

    public Integer getIDE_TPNFDEBITO() {
        return IDE_TPNFDEBITO;
    }

    public Integer getIDE_TPNFCREDITO() {
        return IDE_TPNFCREDITO;
    }

    public void setIDE_TPNFDEBITO(Integer IDE_TPNFDEBITO) {
        this.IDE_TPNFDEBITO = IDE_TPNFDEBITO;
    }

    public void setIDE_TPNFCREDITO(Integer IDE_TPNFCREDITO) {
        this.IDE_TPNFCREDITO = IDE_TPNFCREDITO;
    }

    public void setIDE_INDFINAL(Integer IDE_INDFINAL) {
        this.IDE_INDFINAL = IDE_INDFINAL;
    }

    public void setIDE_INDPRES(Integer IDE_INDPRES) {
        this.IDE_INDPRES = IDE_INDPRES;
    }

    public void setIDE_PROCEMI(Integer IDE_PROCEMI) {
        this.IDE_PROCEMI = IDE_PROCEMI;
    }

    public void setIDE_VERPROC(String IDE_VERPROC) {
        this.IDE_VERPROC = IDE_VERPROC;
    }

    public void setIDE_DHCONT(Date IDE_DHCONT) {
        this.IDE_DHCONT = IDE_DHCONT;
    }

    public void setIDE_XJUST(String IDE_XJUST) {
        this.IDE_XJUST = IDE_XJUST;
    }

    public void setEMIT_CNPJ(String EMIT_CNPJ) {
        this.EMIT_CNPJ = EMIT_CNPJ;
    }

    public void setEMIT_CPF(String EMIT_CPF) {
        this.EMIT_CPF = EMIT_CPF;
    }

    public void setEMIT_XNOME(String EMIT_XNOME) {
        this.EMIT_XNOME = EMIT_XNOME;
    }

    public void setEMIT_XFANT(String EMIT_XFANT) {
        this.EMIT_XFANT = EMIT_XFANT;
    }

    public void setENDEREMIT_XLGR(String ENDEREMIT_XLGR) {
        this.ENDEREMIT_XLGR = ENDEREMIT_XLGR;
    }

    public void setENDEREMIT_NRO(String ENDEREMIT_NRO) {
        this.ENDEREMIT_NRO = ENDEREMIT_NRO;
    }

    public void setENDEREMIT_XCPL(String ENDEREMIT_XCPL) {
        this.ENDEREMIT_XCPL = ENDEREMIT_XCPL;
    }

    public void setENDEREMIT_XBAIRRO(String ENDEREMIT_XBAIRRO) {
        this.ENDEREMIT_XBAIRRO = ENDEREMIT_XBAIRRO;
    }

    public void setENDEREMIT_CMUN(Integer ENDEREMIT_CMUN) {
        this.ENDEREMIT_CMUN = ENDEREMIT_CMUN;
    }

    public void setENDEREMIT_XMUN(String ENDEREMIT_XMUN) {
        this.ENDEREMIT_XMUN = ENDEREMIT_XMUN;
    }

    public void setENDEREMIT_UF(String ENDEREMIT_UF) {
        this.ENDEREMIT_UF = ENDEREMIT_UF;
    }

    public void setENDEREMIT_CEP(Integer ENDEREMIT_CEP) {
        this.ENDEREMIT_CEP = ENDEREMIT_CEP;
    }

    public void setENDEREMIT_CPAIS(Integer ENDEREMIT_CPAIS) {
        this.ENDEREMIT_CPAIS = ENDEREMIT_CPAIS;
    }

    public void setENDEREMIT_XPAIS(String ENDEREMIT_XPAIS) {
        this.ENDEREMIT_XPAIS = ENDEREMIT_XPAIS;
    }

    public void setENDEREMIT_FONE(Long ENDEREMIT_FONE) {
        this.ENDEREMIT_FONE = ENDEREMIT_FONE;
    }

    public void setENDEREMIT_IE(Long ENDEREMIT_IE) {
        this.ENDEREMIT_IE = ENDEREMIT_IE;
    }

    public void setENDEREMIT_IEST(Long ENDEREMIT_IEST) {
        this.ENDEREMIT_IEST = ENDEREMIT_IEST;
    }

    public void setENDEREMIT_IM(String ENDEREMIT_IM) {
        this.ENDEREMIT_IM = ENDEREMIT_IM;
    }

    public void setENDEREMIT_CNAE(String ENDEREMIT_CNAE) {
        this.ENDEREMIT_CNAE = ENDEREMIT_CNAE;
    }

    public void setENDEREMIT_CRT(Integer ENDEREMIT_CRT) {
        this.ENDEREMIT_CRT = ENDEREMIT_CRT;
    }

    public void setDEST_CNPJ(String DEST_CNPJ) {
        this.DEST_CNPJ = DEST_CNPJ;
    }

    public void setDEST_CPF(String DEST_CPF) {
        this.DEST_CPF = DEST_CPF;
    }

    public void setDEST_IDESTRANGEIRO(String DEST_IDESTRANGEIRO) {
        this.DEST_IDESTRANGEIRO = DEST_IDESTRANGEIRO;
    }

    public void setDEST_XNOME(String DEST_XNOME) {
        this.DEST_XNOME = DEST_XNOME;
    }

    public void setENDERDEST_XLGR(String ENDERDEST_XLGR) {
        this.ENDERDEST_XLGR = ENDERDEST_XLGR;
    }

    public void setENDERDEST_NRO(String ENDERDEST_NRO) {
        this.ENDERDEST_NRO = ENDERDEST_NRO;
    }

    public void setENDERDEST_XCPL(String ENDERDEST_XCPL) {
        this.ENDERDEST_XCPL = ENDERDEST_XCPL;
    }

    public void setENDERDEST_XBAIRRO(String ENDERDEST_XBAIRRO) {
        this.ENDERDEST_XBAIRRO = ENDERDEST_XBAIRRO;
    }

    public void setENDERDEST_CMUN(Integer ENDERDEST_CMUN) {
        this.ENDERDEST_CMUN = ENDERDEST_CMUN;
    }

    public void setENDERDEST_XMUN(String ENDERDEST_XMUN) {
        this.ENDERDEST_XMUN = ENDERDEST_XMUN;
    }

    public void setENDERDEST_UF(String ENDERDEST_UF) {
        this.ENDERDEST_UF = ENDERDEST_UF;
    }

    public void setENDERDEST_CEP(Integer ENDERDEST_CEP) {
        this.ENDERDEST_CEP = ENDERDEST_CEP;
    }

    public void setENDERDEST_CPAIS(Integer ENDERDEST_CPAIS) {
        this.ENDERDEST_CPAIS = ENDERDEST_CPAIS;
    }

    public void setENDERDEST_XPAIS(String ENDERDEST_XPAIS) {
        this.ENDERDEST_XPAIS = ENDERDEST_XPAIS;
    }

    public void setENDERDEST_FONE(Long ENDERDEST_FONE) {
        this.ENDERDEST_FONE = ENDERDEST_FONE;
    }

    public void setENDERDEST_INDIEDEST(Integer ENDERDEST_INDIEDEST) {
        this.ENDERDEST_INDIEDEST = ENDERDEST_INDIEDEST;
    }

    public void setENDERDEST_IE(Long ENDERDEST_IE) {
        this.ENDERDEST_IE = ENDERDEST_IE;
    }

    public void setENDERDEST_ISUF(Integer ENDERDEST_ISUF) {
        this.ENDERDEST_ISUF = ENDERDEST_ISUF;
    }

    public void setENDERDEST_IM(String ENDERDEST_IM) {
        this.ENDERDEST_IM = ENDERDEST_IM;
    }

    public void setENDERDEST_EMAIL(String ENDERDEST_EMAIL) {
        this.ENDERDEST_EMAIL = ENDERDEST_EMAIL;
    }

    public void setRETIRADA_CNPJ(String RETIRADA_CNPJ) {
        this.RETIRADA_CNPJ = RETIRADA_CNPJ;
    }

    public void setRETIRADA_CPF(String RETIRADA_CPF) {
        this.RETIRADA_CPF = RETIRADA_CPF;
    }

    public void setRETIRADA_XLGR(String RETIRADA_XLGR) {
        this.RETIRADA_XLGR = RETIRADA_XLGR;
    }

    public void setRETIRADA_NRO(String RETIRADA_NRO) {
        this.RETIRADA_NRO = RETIRADA_NRO;
    }

    public void setRETIRADA_XCPL(String RETIRADA_XCPL) {
        this.RETIRADA_XCPL = RETIRADA_XCPL;
    }

    public void setRETIRADA_XBAIRRO(String RETIRADA_XBAIRRO) {
        this.RETIRADA_XBAIRRO = RETIRADA_XBAIRRO;
    }

    public void setRETIRADA_CMUN(Integer RETIRADA_CMUN) {
        this.RETIRADA_CMUN = RETIRADA_CMUN;
    }

    public void setRETIRADA_XMUN(String RETIRADA_XMUN) {
        this.RETIRADA_XMUN = RETIRADA_XMUN;
    }

    public void setRETIRADA_UF(String RETIRADA_UF) {
        this.RETIRADA_UF = RETIRADA_UF;
    }

    public void setENTREGA_CNPJ(String ENTREGA_CNPJ) {
        this.ENTREGA_CNPJ = ENTREGA_CNPJ;
    }

    public void setENTREGA_CPF(String ENTREGA_CPF) {
        this.ENTREGA_CPF = ENTREGA_CPF;
    }

    public void setENTREGA_XLGR(String ENTREGA_XLGR) {
        this.ENTREGA_XLGR = ENTREGA_XLGR;
    }

    public void setENTREGA_NRO(String ENTREGA_NRO) {
        this.ENTREGA_NRO = ENTREGA_NRO;
    }

    public void setENTREGA_XCPL(String ENTREGA_XCPL) {
        this.ENTREGA_XCPL = ENTREGA_XCPL;
    }

    public void setENTREGA_XBAIRRO(String ENTREGA_XBAIRRO) {
        this.ENTREGA_XBAIRRO = ENTREGA_XBAIRRO;
    }

    public void setENTREGA_CMUN(Integer ENTREGA_CMUN) {
        this.ENTREGA_CMUN = ENTREGA_CMUN;
    }

    public void setENTREGA_XMUN(String ENTREGA_XMUN) {
        this.ENTREGA_XMUN = ENTREGA_XMUN;
    }

    public void setENTREGA_UF(String ENTREGA_UF) {
        this.ENTREGA_UF = ENTREGA_UF;
    }

    public List<NFEINFPROT> getNFEINFPROT() {
        return NFEINFPROT;
    }

    public void setNFEINFPROT(List<NFEINFPROT> NFEINFPROT) {
        this.NFEINFPROT = NFEINFPROT;
    }

    public List<NFEDETPROD> getNFEDETPROD() {
        return NFEDETPROD;
    }

    public void setNFEDETPROD(List<NFEDETPROD> NFEDETPROD) {
        this.NFEDETPROD = NFEDETPROD;
    }

    public Double getTOT_IS_VBCSEL() {
        return TOT_IS_VBCSEL;
    }

    public Double getTOT_IS_VIMPSEL() {
        return TOT_IS_VIMPSEL;
    }

    public Double getTOT_IBS_UF_VCRESPRES() {
        return TOT_IBS_UF_VCRESPRES;
    }

    public Double getTOT_IBS_UF_VCREDPRESCONDSUS() {
        return TOT_IBS_UF_VCREDPRESCONDSUS;
    }

    public Double getTOT_IBS_UF_VDIF() {
        return TOT_IBS_UF_VDIF;
    }

    public Double getTOT_IBS_UF_VDEVTRIB() {
        return TOT_IBS_UF_VDEVTRIB;
    }

    public Double getTOT_IBS_UF_VDESON() {
        return TOT_IBS_UF_VDESON;
    }

    public Double getTOT_IBS_UF_VIBS() {
        return TOT_IBS_UF_VIBS;
    }

    public Double getTOT_IBS_MUN_VCRESPRES() {
        return TOT_IBS_MUN_VCRESPRES;
    }

    public Double getTOT_IBS_MUN_VCREDPRESCONDSUS() {
        return TOT_IBS_MUN_VCREDPRESCONDSUS;
    }

    public Double getTOT_IBS_MUN_VDIF() {
        return TOT_IBS_MUN_VDIF;
    }

    public Double getTOT_IBS_MUN_VDEVTRIB() {
        return TOT_IBS_MUN_VDEVTRIB;
    }

    public Double getTOT_IBS_MUN_VDESON() {
        return TOT_IBS_MUN_VDESON;
    }

    public Double getTOT_IBS_MUN_VIBS() {
        return TOT_IBS_MUN_VIBS;
    }

    public Double getTOT_CBS_VCRESPRES() {
        return TOT_CBS_VCRESPRES;
    }

    public Double getTOT_CBS_VCREDPRESCONDSUS() {
        return TOT_CBS_VCREDPRESCONDSUS;
    }

    public Double getTOT_CBS_VDIF() {
        return TOT_CBS_VDIF;
    }

    public Double getTOT_CBS_VDEVTRIB() {
        return TOT_CBS_VDEVTRIB;
    }

    public Double getTOT_CBS_VDESON() {
        return TOT_CBS_VDESON;
    }

    public Double getTOT_CBS_VCBS() {
        return TOT_CBS_VCBS;
    }

    public Double getTOT_VBCIBSCBS() {
        return TOT_VBCIBSCBS;
    }

    public Double getTOT_VTOTNF() {
        return TOT_VTOTNF;
    }

    public void setTOT_IS_VBCSEL(Double TOT_IS_VBCSEL) {
        this.TOT_IS_VBCSEL = TOT_IS_VBCSEL;
    }

    public void setTOT_IS_VIMPSEL(Double TOT_IS_VIMPSEL) {
        this.TOT_IS_VIMPSEL = TOT_IS_VIMPSEL;
    }

    public void setTOT_IBS_UF_VCRESPRES(Double TOT_IBS_UF_VCRESPRES) {
        this.TOT_IBS_UF_VCRESPRES = TOT_IBS_UF_VCRESPRES;
    }

    public void setTOT_IBS_UF_VCREDPRESCONDSUS(Double TOT_IBS_UF_VCREDPRESCONDSUS) {
        this.TOT_IBS_UF_VCREDPRESCONDSUS = TOT_IBS_UF_VCREDPRESCONDSUS;
    }

    public void setTOT_IBS_UF_VDIF(Double TOT_IBS_UF_VDIF) {
        this.TOT_IBS_UF_VDIF = TOT_IBS_UF_VDIF;
    }

    public void setTOT_IBS_UF_VDEVTRIB(Double TOT_IBS_UF_VDEVTRIB) {
        this.TOT_IBS_UF_VDEVTRIB = TOT_IBS_UF_VDEVTRIB;
    }

    public void setTOT_IBS_UF_VDESON(Double TOT_IBS_UF_VDESON) {
        this.TOT_IBS_UF_VDESON = TOT_IBS_UF_VDESON;
    }

    public void setTOT_IBS_UF_VIBS(Double TOT_IBS_UF_VIBS) {
        this.TOT_IBS_UF_VIBS = TOT_IBS_UF_VIBS;
    }

    public void setTOT_IBS_MUN_VCRESPRES(Double TOT_IBS_MUN_VCRESPRES) {
        this.TOT_IBS_MUN_VCRESPRES = TOT_IBS_MUN_VCRESPRES;
    }

    public void setTOT_IBS_MUN_VCREDPRESCONDSUS(Double TOT_IBS_MUN_VCREDPRESCONDSUS) {
        this.TOT_IBS_MUN_VCREDPRESCONDSUS = TOT_IBS_MUN_VCREDPRESCONDSUS;
    }

    public void setTOT_IBS_MUN_VDIF(Double TOT_IBS_MUN_VDIF) {
        this.TOT_IBS_MUN_VDIF = TOT_IBS_MUN_VDIF;
    }

    public void setTOT_IBS_MUN_VDEVTRIB(Double TOT_IBS_MUN_VDEVTRIB) {
        this.TOT_IBS_MUN_VDEVTRIB = TOT_IBS_MUN_VDEVTRIB;
    }

    public void setTOT_IBS_MUN_VDESON(Double TOT_IBS_MUN_VDESON) {
        this.TOT_IBS_MUN_VDESON = TOT_IBS_MUN_VDESON;
    }

    public void setTOT_IBS_MUN_VIBS(Double TOT_IBS_MUN_VIBS) {
        this.TOT_IBS_MUN_VIBS = TOT_IBS_MUN_VIBS;
    }

    public Double getTOT_IBS_VCRESPRES() {
        return TOT_IBS_VCRESPRES;
    }

    public Double getTOT_IBS_VCREDPRESCONDSUS() {
        return TOT_IBS_VCREDPRESCONDSUS;
    }

    public void setTOT_IBS_VCRESPRES(Double TOT_IBS_VCRESPRES) {
        this.TOT_IBS_VCRESPRES = TOT_IBS_VCRESPRES;
    }

    public void setTOT_IBS_VCREDPRESCONDSUS(Double TOT_IBS_VCREDPRESCONDSUS) {
        this.TOT_IBS_VCREDPRESCONDSUS = TOT_IBS_VCREDPRESCONDSUS;
    }

    public void setTOT_CBS_VCRESPRES(Double TOT_CBS_VCRESPRES) {
        this.TOT_CBS_VCRESPRES = TOT_CBS_VCRESPRES;
    }

    public void setTOT_CBS_VCREDPRESCONDSUS(Double TOT_CBS_VCREDPRESCONDSUS) {
        this.TOT_CBS_VCREDPRESCONDSUS = TOT_CBS_VCREDPRESCONDSUS;
    }

    public void setTOT_CBS_VDIF(Double TOT_CBS_VDIF) {
        this.TOT_CBS_VDIF = TOT_CBS_VDIF;
    }

    public void setTOT_CBS_VDEVTRIB(Double TOT_CBS_VDEVTRIB) {
        this.TOT_CBS_VDEVTRIB = TOT_CBS_VDEVTRIB;
    }

    public void setTOT_CBS_VDESON(Double TOT_CBS_VDESON) {
        this.TOT_CBS_VDESON = TOT_CBS_VDESON;
    }

    public void setTOT_CBS_VCBS(Double TOT_CBS_VCBS) {
        this.TOT_CBS_VCBS = TOT_CBS_VCBS;
    }

    public void setTOT_VBCIBSCBS(Double TOT_VBCIBSCBS) {
        this.TOT_VBCIBSCBS = TOT_VBCIBSCBS;
    }

    public void setTOT_VTOTNF(Double TOT_VTOTNF) {
        this.TOT_VTOTNF = TOT_VTOTNF;
    }

    public String getXML_ASSINADO() {
        return XML_ASSINADO;
    }

    public void setXML_ASSINADO(String XML_ASSINADO) {
        this.XML_ASSINADO = XML_ASSINADO;
    }

    public String getXML_AUTORIZADO() {
        return XML_AUTORIZADO;
    }

    public void setXML_AUTORIZADO(String XML_AUTORIZADO) {
        this.XML_AUTORIZADO = XML_AUTORIZADO;
    }

    public String getXML_ENVEVENTO_ASSINADO() {
        return XML_ENVEVENTO_ASSINADO;
    }

    public void setXML_ENVEVENTO_ASSINADO(String XML_ENVEVENTO_ASSINADO) {
        this.XML_ENVEVENTO_ASSINADO = XML_ENVEVENTO_ASSINADO;
    }

    public String getXML_ENVEVENTO_AUTORIZADO() {
        return XML_ENVEVENTO_AUTORIZADO;
    }

    public void setXML_ENVEVENTO_AUTORIZADO(String XML_ENVEVENTO_AUTORIZADO) {
        this.XML_ENVEVENTO_AUTORIZADO = XML_ENVEVENTO_AUTORIZADO;
    }
}
