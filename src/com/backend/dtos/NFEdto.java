package com.backend.dtos;

import com.backend.daos.NFEdaoJpa;
import java.util.Date;
import java.util.List;
import com.backend.models.NFE;
import com.backend.models.NFEINFPROT;

import java.text.ParseException;

public class NFEdto {

    private Long oid;
    private String INFNFE_STATUSNFE;
    private String INFNFE_ID;
    private String INFNFE_VERSAO;
    private Integer INFNFE_PEDIDOVENDA;
    private String INFNFE_DANFEIMPRESSO;
    private String INFNFE_XMLEXPORTADO;

    private String INFNFE_DANFEXMLENVIADO;
    private String INFNFE_TITULOREGISTRADO;
    private String INFNFE_BOLETOENVIADO;

    private Integer IDE_TPNF;
    private Integer IDE_MOD;
    private Integer IDE_SERIE;
    private Integer IDE_NNF;
    private String IDE_NATOP;
    private Date IDE_DHEMI;
    public Integer IDE_INDFINAL;
    private Integer IDE_TPNFDEBITO;
    private Integer IDE_TPNFCREDITO;
    private String DEST_CNPJ;
    private String ENDERDEST_UF;
    private Date INFPROT_DHRECBTO;
    public Long INFPROT_CHNFE;
    private Double ICMSTOT_VNF;

    private List<NFEdto> NFEdto;
    private List<NFEDETPRODdto> NFEDETPRODdto;
    private static NFEDETPRODdto nFEDETPRODdto;
    private List<NFEINFPROT> NFEINFPROT;

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

    private String XML_ASSINADO;
    private String XML_AUTORIZADO;
    private String XML_ENVEVENTO_ASSINADO;
    private String XML_ENVEVENTO_AUTORIZADO;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getINFNFE_STATUSNFE() {
        return INFNFE_STATUSNFE;
    }

    public void setINFNFE_STATUSNFE(String INFNFE_STATUSNFE) {
        this.INFNFE_STATUSNFE = INFNFE_STATUSNFE;
    }

    public String getINFNFE_ID() {
        return INFNFE_ID;
    }

    public void setINFNFE_ID(String INFNFE_ID) {
        this.INFNFE_ID = INFNFE_ID;
    }

    public String getINFNFE_VERSAO() {
        return INFNFE_VERSAO;
    }

    public void setINFNFE_VERSAO(String INFNFE_VERSAO) {
        this.INFNFE_VERSAO = INFNFE_VERSAO;
    }

    private Integer getINFNFE_PEDIDOVENDA() {
        return INFNFE_PEDIDOVENDA;
    }

    public void setINFNFE_PEDIDOVENDA(Integer INFNFE_PEDIDOVENDA) {
        this.INFNFE_PEDIDOVENDA = INFNFE_PEDIDOVENDA;
    }

    public String getINFNFE_DANFEIMPRESSO() {
        return INFNFE_DANFEIMPRESSO;
    }

    public void setINFNFE_DANFEIMPRESSO(String INFNFE_DANFEIMPRESSO) {
        this.INFNFE_DANFEIMPRESSO = INFNFE_DANFEIMPRESSO;
    }

    public String getINFNFE_XMLEXPORTADO() {
        return INFNFE_XMLEXPORTADO;
    }

    public void setINFNFE_XMLEXPORTADO(String INFNFE_XMLEXPORTADO) {
        this.INFNFE_XMLEXPORTADO = INFNFE_XMLEXPORTADO;
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

    public Integer getIDE_TPNF() {
        return IDE_TPNF;
    }

    public void setIDE_TPNF(Integer IDE_TPNF) {
        this.IDE_TPNF = IDE_TPNF;
    }

    public Integer getIDE_MOD() {
        return IDE_MOD;
    }

    public void setIDE_MOD(Integer IDE_MOD) {
        this.IDE_MOD = IDE_MOD;
    }

    public Integer getIDE_SERIE() {
        return IDE_SERIE;
    }

    public void setIDE_SERIE(Integer IDE_SERIE) {
        this.IDE_SERIE = IDE_SERIE;
    }

    public Integer getIDE_NNF() {
        return IDE_NNF;
    }

    public void setIDE_NNF(Integer IDE_NNF) {
        this.IDE_NNF = IDE_NNF;
    }

    public String getIDE_NATOP() {
        return IDE_NATOP;
    }

    public void setIDE_NATOP(String IDE_NATOP) {
        this.IDE_NATOP = IDE_NATOP;
    }

    public Date getIDE_DHEMI() {
        return IDE_DHEMI;
    }

    public void setIDE_DHEMI(Date IDE_DHEMI) {
        this.IDE_DHEMI = IDE_DHEMI;
    }

    public Integer getIDE_INDFINAL() {
        return IDE_INDFINAL;
    }

    public void setIDE_INDFINAL(Integer IDE_INDFINAL) {
        this.IDE_INDFINAL = IDE_INDFINAL;
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

    public String getDEST_CNPJ() {
        return DEST_CNPJ;
    }

    public void setDEST_CNPJ(String DEST_CNPJ) {
        this.DEST_CNPJ = DEST_CNPJ;
    }

    public String getENDERDEST_UF() {
        return ENDERDEST_UF;
    }

    public void setENDERDEST_UF(String ENDERDEST_UF) {
        this.ENDERDEST_UF = ENDERDEST_UF;
    }

    public Date getINFPROT_DHRECBTO() {
        return INFPROT_DHRECBTO;
    }

    public void setINFPROT_DHRECBTO(Date INFPROT_DHRECBTO) {
        this.INFPROT_DHRECBTO = INFPROT_DHRECBTO;
    }

    public Long getINFPROT_CHNFE() {
        return INFPROT_CHNFE;
    }

    public void setINFPROT_CHNFE(Long INFPROT_CHNFE) {
        this.INFPROT_CHNFE = INFPROT_CHNFE;
    }

    public Double getICMSTOT_VNF() {
        return ICMSTOT_VNF;
    }

    public void setICMSTOT_VNF(Double ICMSTOT_VNF) {
        this.ICMSTOT_VNF = ICMSTOT_VNF;
    }

    public List<NFEdto> getNFEdto() {
        return NFEdto;
    }

    public void setNFEdto(List<NFEdto> NFEdto) {
        this.NFEdto = NFEdto;
    }

    public List<NFEDETPRODdto> getNFEDETPRODdto() {
        return NFEDETPRODdto;
    }

    public void setNFEDETPRODdto(List<NFEDETPRODdto> NFEDETPRODdto) {
        this.NFEDETPRODdto = NFEDETPRODdto;
    }

    public List<NFEINFPROT> getNFEINFPROT() {
        return NFEINFPROT;
    }

    public void setNFEINFPROT(List<NFEINFPROT> NFEINFPROT) {
        this.NFEINFPROT = NFEINFPROT;
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

    public String getXML_AUTORIZADO() {
        return XML_AUTORIZADO;
    }

    public String getXML_ENVEVENTO_ASSINADO() {
        return XML_ENVEVENTO_ASSINADO;
    }

    public String getXML_ENVEVENTO_AUTORIZADO() {
        return XML_ENVEVENTO_AUTORIZADO;
    }

    public void setXML_ASSINADO(String XML_ASSINADO) {
        this.XML_ASSINADO = XML_ASSINADO;
    }

    public void setXML_AUTORIZADO(String XML_AUTORIZADO) {
        this.XML_AUTORIZADO = XML_AUTORIZADO;
    }

    public void setXML_ENVEVENTO_ASSINADO(String XML_ENVEVENTO_ASSINADO) {
        this.XML_ENVEVENTO_ASSINADO = XML_ENVEVENTO_ASSINADO;
    }

    public void setXML_ENVEVENTO_AUTORIZADO(String XML_ENVEVENTO_AUTORIZADO) {
        this.XML_ENVEVENTO_AUTORIZADO = XML_ENVEVENTO_AUTORIZADO;
    }

    public static NFEdto convertNFEtoNFEdto(NFE nFE) throws ParseException {
        NFEdto NFEdto = new NFEdto();
        NFEdto.setINFNFE_STATUSNFE(nFE.getINFNFE_STATUSNFE());
        NFEdto.setINFNFE_ID(nFE.getINFNFE_ID());
        NFEdto.setINFNFE_VERSAO(nFE.getINFNFE_VERSAO());
        NFEdto.setINFNFE_PEDIDOVENDA(nFE.getINFNFE_PEDIDOVENDA());
        NFEdto.setINFNFE_DANFEIMPRESSO(nFE.getINFNFE_DANFEIMPRESSO());
        NFEdto.setINFNFE_XMLEXPORTADO(nFE.getINFNFE_XMLEXPORTADO());

        NFEdto.setINFNFE_DANFEXMLENVIADO(nFE.getINFNFE_DANFEXMLENVIADO());
        NFEdto.setINFNFE_TITULOREGISTRADO(nFE.getINFNFE_TITULOREGISTRADO());
        NFEdto.setINFNFE_BOLETOENVIADO(nFE.getINFNFE_BOLETOENVIADO());

        NFEdto.setIDE_MOD(nFE.getIDE_MOD());
        NFEdto.setIDE_SERIE(nFE.getIDE_SERIE());
        NFEdto.setIDE_NNF(nFE.getIDE_NNF());
        NFEdto.setIDE_TPNF(nFE.getIDE_TPNF());
        NFEdto.setIDE_NATOP(nFE.getIDE_NATOP());
        NFEdto.setIDE_DHEMI(nFE.getIDE_DHEMI());
        NFEdto.setIDE_INDFINAL(nFE.getIDE_INDFINAL());
        NFEdto.setDEST_CNPJ(nFE.getDEST_CNPJ());
        NFEdto.setENDERDEST_UF(nFE.getENDERDEST_UF());
        NFEdto.setINFPROT_DHRECBTO(nFE.getINFPROT_DHRECBTO());
        NFEdto.setINFPROT_CHNFE(nFE.getINFPROT_CHNFE());
        NFEdto.setICMSTOT_VNF(nFE.getICMSTOT_VNF());
        NFEdto.setXML_AUTORIZADO(nFE.getXML_AUTORIZADO());
        // NFEdto.setNFEINFPROT(nFE.getNFEINFPROT());
        NFEdto.setNFEDETPRODdto(NFEdaoJpa.NamedQueryFindByDetProdNNF(nFE.getIDE_NNF()));
        return NFEdto;
    }

    public static NFE convertNFEdtotoNFE(NFEdto nFEdto) {
        NFE nFE = new NFE();
        nFE.setINFNFE_STATUSNFE(nFEdto.getINFNFE_STATUSNFE());
        nFE.setINFNFE_ID(nFEdto.getINFNFE_ID());
        nFE.setINFNFE_VERSAO(nFEdto.getINFNFE_VERSAO());
        nFE.setINFNFE_PEDIDOVENDA(nFEdto.getINFNFE_PEDIDOVENDA());
        nFE.setINFNFE_DANFEIMPRESSO(nFEdto.getINFNFE_DANFEIMPRESSO());
        //nFE.setINFNFE_XMLEXPORTADO(nFEdto.getINFNFE_XMLEXPORTADO());

        nFE.setINFNFE_DANFEXMLENVIADO(nFEdto.getINFNFE_DANFEXMLENVIADO());
        nFE.setINFNFE_TITULOREGISTRADO(nFEdto.getINFNFE_TITULOREGISTRADO());
        nFE.setINFNFE_BOLETOENVIADO(nFEdto.getINFNFE_BOLETOENVIADO());

        nFE.setIDE_TPNF(nFEdto.getIDE_TPNF());
        nFE.setIDE_MOD(nFEdto.getIDE_MOD());
        nFE.setIDE_SERIE(nFEdto.getIDE_SERIE());
        nFE.setIDE_NNF(nFEdto.getIDE_NNF());
        nFE.setIDE_TPNF(nFEdto.getIDE_TPNF());
        nFE.setIDE_NATOP(nFEdto.getIDE_NATOP());
        nFE.setIDE_DHEMI(nFEdto.getIDE_DHEMI());
        nFE.setIDE_INDFINAL(nFEdto.getIDE_INDFINAL());
        nFE.setIDE_TPNFDEBITO(nFEdto.getIDE_TPNFDEBITO());
        nFE.setIDE_TPNFCREDITO(nFEdto.getIDE_TPNFCREDITO());
        nFE.setDEST_CNPJ(nFEdto.getDEST_CNPJ());
        nFE.setENDERDEST_UF(nFEdto.getENDERDEST_UF());
        nFE.setINFPROT_DHRECBTO(nFEdto.getINFPROT_DHRECBTO());
        nFE.setINFPROT_CHNFE(nFEdto.getINFPROT_CHNFE());
        nFE.setINFPROT_CHNFE(nFEdto.getINFPROT_CHNFE());
        //nFE.setNFEINFPROT(nFEdto.getNFEINFPROT());

        nFE.setTOT_IS_VBCSEL(nFEdto.getTOT_IS_VBCSEL());
        nFE.setTOT_IS_VIMPSEL(nFEdto.getTOT_IS_VIMPSEL());
        nFE.setTOT_IBS_UF_VCRESPRES(nFEdto.getTOT_IBS_UF_VCRESPRES());
        nFE.setTOT_IBS_UF_VCREDPRESCONDSUS(nFEdto.getTOT_IBS_UF_VCREDPRESCONDSUS());
        nFE.setTOT_IBS_UF_VDIF(nFEdto.getTOT_IBS_UF_VDIF());
        nFE.setTOT_IBS_UF_VDEVTRIB(nFEdto.getTOT_IBS_UF_VDEVTRIB());
        nFE.setTOT_IBS_UF_VDESON(nFEdto.getTOT_IBS_UF_VDESON());
        nFE.setTOT_IBS_UF_VIBS(nFEdto.getTOT_IBS_UF_VIBS());
        nFE.setTOT_IBS_MUN_VCRESPRES(nFEdto.getTOT_IBS_MUN_VCRESPRES());
        nFE.setTOT_IBS_MUN_VCREDPRESCONDSUS(nFEdto.getTOT_IBS_MUN_VCREDPRESCONDSUS());
        nFE.setTOT_IBS_MUN_VDIF(nFEdto.getTOT_IBS_MUN_VDIF());
        nFE.setTOT_IBS_MUN_VDEVTRIB(nFEdto.getTOT_IBS_MUN_VDEVTRIB());
        nFE.setTOT_IBS_MUN_VDESON(nFEdto.getTOT_IBS_MUN_VDESON());
        nFE.setTOT_IBS_MUN_VIBS(nFEdto.getTOT_IBS_MUN_VIBS());
        nFE.setTOT_IBS_VCRESPRES(nFEdto.getTOT_IBS_VCRESPRES());
        nFE.setTOT_IBS_VCREDPRESCONDSUS(nFEdto.getTOT_IBS_VCREDPRESCONDSUS());
        nFE.setTOT_CBS_VCRESPRES(nFEdto.getTOT_CBS_VCRESPRES());
        nFE.setTOT_CBS_VCREDPRESCONDSUS(nFEdto.getTOT_CBS_VCREDPRESCONDSUS());
        nFE.setTOT_CBS_VDIF(nFEdto.getTOT_CBS_VDIF());
        nFE.setTOT_CBS_VDEVTRIB(nFEdto.getTOT_CBS_VDEVTRIB());
        nFE.setTOT_CBS_VDESON(nFEdto.getTOT_CBS_VDESON());
        nFE.setTOT_CBS_VCBS(nFEdto.getTOT_CBS_VCBS());
        nFE.setTOT_VBCIBSCBS(nFEdto.getTOT_VBCIBSCBS());
        nFE.setTOT_VTOTNF(nFEdto.getTOT_VTOTNF());

        return nFE;
    }

}
