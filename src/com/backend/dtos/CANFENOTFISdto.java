package com.backend.dtos;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.backend.models.CANFENOTFIS;
import com.backend.models.NFE;
import static com.frontend.util.ObjetoUtil.*;

public class CANFENOTFISdto {

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
    private String NOT_INFADFISCO;
    private String NOT_INFCPL;

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
    private Double TOT_CBS_VCRESPRES;
    private Double TOT_CBS_VCREDPRESCONDSUS;
    private Double TOT_CBS_VDIF;
    private Double TOT_CBS_VDEVTRIB;
    private Double TOT_CBS_VDESON;
    private Double TOT_CBS_VCBS;
    private Double TOT_VBCIBSCBS;
    private Double TOT_VTOTNF;

    private List<CANFENOTFISdto> CANFENOTFISdto;
    private List<CANFEDUPLICdto> CANFEDUPLICdto;
    private List<CANFEIRDUPLdto> CANFEIRDUPLdto;
    private CLIENTEdto CLIENTEdto;
    private EMPRESAdto EMPRESAdto;
    private TRANSPORTADORAdto TRANSPORTADORAdto;
    private NFE NFEdto;

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

    public String getNOT_INFADFISCO() {
        return NOT_INFADFISCO;
    }

    public void setNOT_INFADFISCO(String NOT_INFADFISCO) {
        this.NOT_INFADFISCO = NOT_INFADFISCO;
    }

    public String getNOT_INFCPL() {
        return NOT_INFCPL;
    }

    public void setNOT_INFCPL(String NOT_INFCPL) {
        this.NOT_INFCPL = NOT_INFCPL;
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

    public List<CANFENOTFISdto> getCANFENOTFISdto() {
        return CANFENOTFISdto;
    }

    public void setCANFENOTFISdto(List<CANFENOTFISdto> CANFENOTFISdto) {
        this.CANFENOTFISdto = CANFENOTFISdto;
    }

    public List<CANFEDUPLICdto> getCANFEDUPLICdto() {
        return CANFEDUPLICdto;
    }

    public void setCANFEDUPLICdto(List<CANFEDUPLICdto> CANFEDUPLICdto) {
        this.CANFEDUPLICdto = CANFEDUPLICdto;
    }

    public List<CANFEIRDUPLdto> getCANFEIRDUPLdto() {
        return CANFEIRDUPLdto;
    }

    public void setCANFEIRDUPLdto(List<CANFEIRDUPLdto> CANFEIRDUPLdto) {
        this.CANFEIRDUPLdto = CANFEIRDUPLdto;
    }

    public CLIENTEdto getCLIENTEdto() {
        return CLIENTEdto;
    }

    public void setCLIENTEdto(CLIENTEdto CLIENTEdto) {
        this.CLIENTEdto = CLIENTEdto;
    }

    public EMPRESAdto getEMPRESAdto() {
        return EMPRESAdto;
    }

    public void setEMPRESAdto(EMPRESAdto EMPRESAdto) {
        this.EMPRESAdto = EMPRESAdto;
    }

    public TRANSPORTADORAdto getTRANSPORTADORAdto() {
        return TRANSPORTADORAdto;
    }

    public void setTRANSPORTADORAdto(TRANSPORTADORAdto TRANSPORTADORAdto) {
        this.TRANSPORTADORAdto = TRANSPORTADORAdto;
    }

    public NFE getNFEdto() {
        return NFEdto;
    }

    public void setNFEdto(NFE NFEdto) {
        this.NFEdto = NFEdto;
    }

    public static CANFENOTFISdto convertCANFENOTFIStoCANFENOTFISdto(CANFENOTFIS caNFENOTFIS) throws ParseException, SQLException {
        CANFENOTFISdto caNFENOTFISdto = new CANFENOTFISdto();

        caNFENOTFISdto.setId(caNFENOTFIS.getId());
        caNFENOTFISdto.setNOT_NOTA(caNFENOTFIS.getNOT_NOTA());
        caNFENOTFISdto.setNOT_PEDI(caNFENOTFIS.getNOT_PEDI());
        caNFENOTFISdto.setNOT_DOP1(caNFENOTFIS.getNOT_DOP1());
        caNFENOTFISdto.setNOT_DOP2(caNFENOTFIS.getNOT_DOP2());
        caNFENOTFISdto.setNOT_ALIC(caNFENOTFIS.getNOT_ALIC());
        caNFENOTFISdto.setNOT_BSIC(caNFENOTFIS.getNOT_BSIC());
        caNFENOTFISdto.setNOT_VLIC(caNFENOTFIS.getNOT_VLIC());
        caNFENOTFISdto.setNOT_VLPR(caNFENOTFIS.getNOT_VLPR());
        caNFENOTFISdto.setNOT_VLFR(caNFENOTFIS.getNOT_VLFR());
        caNFENOTFISdto.setNOT_VLSE(caNFENOTFIS.getNOT_VLSE());
        caNFENOTFISdto.setNOT_VLDA(caNFENOTFIS.getNOT_VLDA());
        caNFENOTFISdto.setNOT_VLIP(caNFENOTFIS.getNOT_VLIP());
        caNFENOTFISdto.setNOT_VLNO(caNFENOTFIS.getNOT_VLNO());
        caNFENOTFISdto.setNOT_ABATIMENTO5901(caNFENOTFIS.getNOT_ABATIMENTO5901());
        caNFENOTFISdto.setNOT_PLAC(caNFENOTFIS.getNOT_PLAC());
        caNFENOTFISdto.setNOT_QTDE(caNFENOTFIS.getNOT_QTDE());
        caNFENOTFISdto.setNOT_ESPE(caNFENOTFIS.getNOT_ESPE());
        caNFENOTFISdto.setNOT_MARC(caNFENOTFIS.getNOT_MARC());
        caNFENOTFISdto.setNOT_NUME(caNFENOTFIS.getNOT_NUME());
        caNFENOTFISdto.setNOT_PBRU(caNFENOTFIS.getNOT_PBRU());
        caNFENOTFISdto.setNOT_PLIQ(caNFENOTFIS.getNOT_PLIQ());
        caNFENOTFISdto.setNOT_CLIE(caNFENOTFIS.getNOT_CLIE());
        caNFENOTFISdto.setNOT_OBSE(caNFENOTFIS.getNOT_OBSE());
        //caNFENOTFISdto.setNOT_INFCPL(caNFENOTFIS.getNOT_INFCPL());
        caNFENOTFISdto.setNOT_NFFO(caNFENOTFIS.getNOT_NFFO());
        caNFENOTFISdto.setNOT_FORN(caNFENOTFIS.getNOT_FORN());
        caNFENOTFISdto.setNOT_TRAN(caNFENOTFIS.getNOT_TRAN());
        caNFENOTFISdto.setNOT_ESTR(caNFENOTFIS.getNOT_ESTR());
        caNFENOTFISdto.setNOT_FLEM(caNFENOTFIS.getNOT_FLEM());
        caNFENOTFISdto.setNOT_EMDE(caNFENOTFIS.getNOT_EMDE() == 0 ? 3 : caNFENOTFIS.getNOT_EMDE());
        caNFENOTFISdto.setNOT_OPER(caNFENOTFIS.getNOT_OPER());
        caNFENOTFISdto.setNOT_OPE2(caNFENOTFIS.getNOT_OPE2().trim().equals("0000") ? "N/A" : caNFENOTFIS.getNOT_OPE2().trim());
        caNFENOTFISdto.setNOT_DTSA(caNFENOTFIS.getNOT_DTSA());
        caNFENOTFISdto.setNOT_DTEM(caNFENOTFIS.getNOT_DTEM());
        caNFENOTFISdto.setNOT_DTFO(caNFENOTFIS.getNOT_DTFO());
        caNFENOTFISdto.setNOT_ENSA(caNFENOTFIS.getNOT_ENSA().equals("E") ? "ENTRADA" : caNFENOTFIS.getNOT_ENSA().equals("S") ? "SAIDA" : "DEVOLUCAO");

        if (caNFENOTFIS.getNOT_NOTA().trim().equals(caNFENOTFIS.getNOT_PEDI().trim())) {
            caNFENOTFISdto.setNOT_ENSA("NFe");
        }

        caNFENOTFISdto.setNOT_TIPP(caNFENOTFIS.getNOT_TIPP());
        caNFENOTFISdto.setNOT_SITU(caNFENOTFIS.getNOT_SITU().equals("N") ? "NORMAL" : "CANCELADA");
        caNFENOTFISdto.setNOT_CBTP(NullToBlank(caNFENOTFIS.getNOT_CBTP()).trim().equals("N/A") ? "" : NullToBlank(caNFENOTFIS.getNOT_CBTP()));
        caNFENOTFISdto.setNOT_CBBA(NullToBlank(caNFENOTFIS.getNOT_CBBA()).trim().equals("N/A") ? "" : NullToBlank(caNFENOTFIS.getNOT_CBBA()));
        caNFENOTFISdto.setNOT_NFEBASEPIS(caNFENOTFIS.getNOT_NFEBASEPIS() != null ? getRoundDouble(caNFENOTFIS.getNOT_NFEBASEPIS(), 2) : null);
        caNFENOTFISdto.setNOT_NFEVALORPIS(caNFENOTFIS.getNOT_NFEVALORPIS() != null ? getRoundDouble(caNFENOTFIS.getNOT_NFEVALORPIS(), 2) : null);
        caNFENOTFISdto.setNOT_NFEBASECOFINS(caNFENOTFIS.getNOT_NFEBASECOFINS() != null ? getRoundDouble(caNFENOTFIS.getNOT_NFEBASECOFINS(), 2) : null);
        caNFENOTFISdto.setNOT_NFEVALORCOFINS(caNFENOTFIS.getNOT_NFEVALORCOFINS() != null ? getRoundDouble(caNFENOTFIS.getNOT_NFEVALORCOFINS(), 2) : null);
        caNFENOTFISdto.setNOT_CDP1(caNFENOTFIS.getNOT_CDP1());
        caNFENOTFISdto.setNOT_CDP2(caNFENOTFIS.getNOT_CDP2());
        caNFENOTFISdto.setNOT_CDP3(caNFENOTFIS.getNOT_CDP3());
        caNFENOTFISdto.setNOT_CDP4(caNFENOTFIS.getNOT_CDP4());
        caNFENOTFISdto.setNOT_CDP5(caNFENOTFIS.getNOT_CDP5());
        caNFENOTFISdto.setNOT_CDP6(caNFENOTFIS.getNOT_CDP6());

        caNFENOTFISdto.setNOT_INFNFE_TPAMB(caNFENOTFIS.getNOT_INFNFE_TPAMB());
        caNFENOTFISdto.setNOT_INFNFE_TPEMIS(caNFENOTFIS.getNOT_INFNFE_TPEMIS());
        caNFENOTFISdto.setNOT_INFNFE_TPIMP(caNFENOTFIS.getNOT_INFNFE_TPIMP());
        caNFENOTFISdto.setNOT_INFNFE_FINNFE(caNFENOTFIS.getNOT_INFNFE_FINNFE());
        caNFENOTFISdto.setNOT_NFE_ENVIOEMAIL(caNFENOTFIS.getNOT_NFE_ENVIOEMAIL());

        caNFENOTFISdto.setNOT_INFPROT_CHNFE(caNFENOTFIS.getNOT_INFPROT_CHNFE());
        caNFENOTFISdto.setNOT_INFPROT_CSTAT(caNFENOTFIS.getNOT_INFPROT_CSTAT());
        caNFENOTFISdto.setNOT_INFPROT_XMOTIVO(caNFENOTFIS.getNOT_INFPROT_XMOTIVO());
        caNFENOTFISdto.setNOT_INFPROT_NPROT(caNFENOTFIS.getNOT_INFPROT_NPROT());
        caNFENOTFISdto.setNOT_INFPROT_DHRECBTO(caNFENOTFIS.getNOT_INFPROT_DHRECBTO());

        caNFENOTFISdto.setNOT_INFREC_NREC(caNFENOTFIS.getNOT_INFREC_NREC());
        caNFENOTFISdto.setNOT_INFREC_DHRECBTO(caNFENOTFIS.getNOT_INFREC_DHRECBTO());

        caNFENOTFISdto.setNOT_INFCANC_NPROT(caNFENOTFIS.getNOT_INFCANC_NPROT());
        caNFENOTFISdto.setNOT_INFCANC_DHRECBTO(caNFENOTFIS.getNOT_INFCANC_DHRECBTO());
        caNFENOTFISdto.setNOT_INFCANC_XMOTIVO(caNFENOTFIS.getNOT_INFCANC_XMOTIVO());
        caNFENOTFISdto.setNOT_RETCONSRECINFE_CSTAT(caNFENOTFIS.getNOT_RETCONSRECINFE_CSTAT());
        caNFENOTFISdto.setNOT_RETCONSRECINFE_XMOTIVO(caNFENOTFIS.getNOT_RETCONSRECINFE_XMOTIVO());

        //if (caNFENOTFIS.getCLIENTE() != null) {
        //    caNFENOTFISdto.setCLIENTEdto(convertCLIENTEtoCLIENTEdto(caNFENOTFIS.getCLIENTE(), true));
        //}
        //if (caNFENOTFIS.getTRANSPORTADORA() != null) {
        //    caNFENOTFISdto.setTRANSPORTADORAdto(convertTRANSPORTADORAtoTRANSPORTADORAdto(caNFENOTFIS.getTRANSPORTADORA()));
        //}
        //if (caNFENOTFIS.getCANFEDUPLIC() != null) {
        //    caNFENOTFISdto.setCANFEDUPLICdto(convertListCANFEDUPLICtoListCANFEDUPLICdto(caNFENOTFIS.getCANFEDUPLIC(), true));
        //}
        //caNFENOTFISdto.setNFEdto(caNFENOTFIS.getNFE());
        return caNFENOTFISdto;
    }

    public static CANFENOTFIS convertCANFENOTFISdtotoCANFENOTFIS(CANFENOTFISdto caNFENOTFISdto) throws ParseException {
        CANFENOTFIS caNFENOTFIS = new CANFENOTFIS();
        caNFENOTFIS.setId(caNFENOTFISdto.getId());
        caNFENOTFIS.setNOT_NOTA(caNFENOTFISdto.getNOT_NOTA());
        caNFENOTFIS.setNOT_PEDI(caNFENOTFISdto.getNOT_PEDI());
        caNFENOTFIS.setNOT_DOP1(caNFENOTFISdto.getNOT_DOP1());
        caNFENOTFIS.setNOT_DOP2(caNFENOTFISdto.getNOT_DOP2());
        caNFENOTFIS.setNOT_ALIC(caNFENOTFISdto.getNOT_ALIC());
        caNFENOTFIS.setNOT_BSIC(caNFENOTFISdto.getNOT_BSIC());
        caNFENOTFIS.setNOT_VLIC(caNFENOTFISdto.getNOT_VLIC());
        caNFENOTFIS.setNOT_VLPR(caNFENOTFISdto.getNOT_VLPR());
        caNFENOTFIS.setNOT_VLFR(caNFENOTFISdto.getNOT_VLFR());
        caNFENOTFIS.setNOT_VLSE(caNFENOTFISdto.getNOT_VLSE());
        caNFENOTFIS.setNOT_VLDA(caNFENOTFISdto.getNOT_VLDA());
        caNFENOTFIS.setNOT_VLIP(caNFENOTFISdto.getNOT_VLIP());
        caNFENOTFIS.setNOT_VLNO(caNFENOTFISdto.getNOT_VLNO());
        caNFENOTFIS.setNOT_ABATIMENTO5901(caNFENOTFISdto.getNOT_ABATIMENTO5901());
        caNFENOTFIS.setNOT_PLAC(caNFENOTFISdto.getNOT_PLAC());
        caNFENOTFIS.setNOT_QTDE(caNFENOTFISdto.getNOT_QTDE());
        caNFENOTFIS.setNOT_ESPE(caNFENOTFISdto.getNOT_ESPE());
        caNFENOTFIS.setNOT_MARC(caNFENOTFISdto.getNOT_MARC());
        caNFENOTFIS.setNOT_NUME(caNFENOTFISdto.getNOT_NUME());
        caNFENOTFIS.setNOT_PBRU(caNFENOTFISdto.getNOT_PBRU());
        caNFENOTFIS.setNOT_PLIQ(caNFENOTFISdto.getNOT_PLIQ());
        caNFENOTFIS.setNOT_CLIE(caNFENOTFISdto.getNOT_CLIE());
        caNFENOTFIS.setNOT_OBSE(caNFENOTFISdto.getNOT_OBSE());
        //caNFENOTFIS.setNOT_INFCPL(caNFENOTFISdto.getNOT_INFCPL());
        caNFENOTFIS.setNOT_NFFO(caNFENOTFISdto.getNOT_NFFO());
        caNFENOTFIS.setNOT_FORN(caNFENOTFISdto.getNOT_FORN());
        caNFENOTFIS.setNOT_TRAN(caNFENOTFISdto.getNOT_TRAN());
        caNFENOTFIS.setNOT_ESTR(caNFENOTFISdto.getNOT_ESTR());
        caNFENOTFIS.setNOT_FLEM(caNFENOTFISdto.getNOT_FLEM());
        caNFENOTFIS.setNOT_EMDE(caNFENOTFISdto.getNOT_EMDE() == 3 ? 0 : caNFENOTFISdto.getNOT_EMDE());
        caNFENOTFIS.setNOT_OPER(caNFENOTFISdto.getNOT_OPER());
        caNFENOTFIS.setNOT_OPE2(caNFENOTFISdto.getNOT_OPE2().equals("N/A") ? "0000" : caNFENOTFISdto.getNOT_OPE2().trim());
        caNFENOTFIS.setNOT_DTSA(caNFENOTFISdto.getNOT_DTSA());
        caNFENOTFIS.setNOT_DTEM(caNFENOTFISdto.getNOT_DTEM());
        caNFENOTFIS.setNOT_DTFO(caNFENOTFISdto.getNOT_DTFO());
        caNFENOTFIS.setNOT_ENSA(caNFENOTFISdto.getNOT_ENSA().equals("ENTRADA") ? "E" : caNFENOTFISdto.getNOT_ENSA().equals("SAIDA") ? "S" : caNFENOTFISdto.getNOT_ENSA().equals("ENTRADA") ? "E" : caNFENOTFISdto.getNOT_ENSA().equals("NFe") ? "S" : "D");
        caNFENOTFIS.setNOT_TIPP(caNFENOTFISdto.getNOT_TIPP());
        caNFENOTFIS.setNOT_SITU(caNFENOTFISdto.getNOT_SITU().equals("NORMAL")
                || caNFENOTFISdto.getNOT_SITU().equals("N") ? "N" : "S");
        caNFENOTFIS.setNOT_CBTP(caNFENOTFISdto.getNOT_CBTP().equals("N/A") ? "" : NullToBlank(caNFENOTFISdto.getNOT_CBTP()));
        caNFENOTFIS.setNOT_CBBA(caNFENOTFISdto.getNOT_CBBA().equals("N/A") ? "" : NullToBlank(caNFENOTFISdto.getNOT_CBBA()));
        caNFENOTFIS.setNOT_NFEBASEPIS(getRoundDouble(caNFENOTFISdto.getNOT_NFEBASEPIS(), 2));
        caNFENOTFIS.setNOT_NFEVALORPIS(getRoundDouble(caNFENOTFISdto.getNOT_NFEVALORPIS(), 2));
        caNFENOTFIS.setNOT_NFEBASECOFINS(getRoundDouble(caNFENOTFISdto.getNOT_NFEBASECOFINS(), 2));
        caNFENOTFIS.setNOT_NFEVALORCOFINS(getRoundDouble(caNFENOTFISdto.getNOT_NFEVALORCOFINS(), 2));
        caNFENOTFIS.setNOT_CDP1(caNFENOTFISdto.getNOT_CDP1());
        caNFENOTFIS.setNOT_CDP2(caNFENOTFISdto.getNOT_CDP2());
        caNFENOTFIS.setNOT_CDP3(caNFENOTFISdto.getNOT_CDP3());
        caNFENOTFIS.setNOT_CDP4(caNFENOTFISdto.getNOT_CDP4());
        caNFENOTFIS.setNOT_CDP5(caNFENOTFISdto.getNOT_CDP5());
        caNFENOTFIS.setNOT_CDP6(caNFENOTFISdto.getNOT_CDP6());

        caNFENOTFIS.setNOT_INFNFE_TPAMB(caNFENOTFISdto.getNOT_INFNFE_TPAMB());
        caNFENOTFIS.setNOT_INFNFE_TPEMIS(caNFENOTFISdto.getNOT_INFNFE_TPEMIS());
        caNFENOTFIS.setNOT_INFNFE_TPIMP(caNFENOTFISdto.getNOT_INFNFE_TPIMP());
        caNFENOTFIS.setNOT_INFNFE_FINNFE(caNFENOTFISdto.getNOT_INFNFE_FINNFE());
        caNFENOTFIS.setNOT_NFE_ENVIOEMAIL(caNFENOTFISdto.getNOT_NFE_ENVIOEMAIL());

        caNFENOTFIS.setNOT_INFPROT_CHNFE(caNFENOTFISdto.getNOT_INFPROT_CHNFE());
        caNFENOTFIS.setNOT_INFPROT_CSTAT(caNFENOTFISdto.getNOT_INFPROT_CSTAT());
        caNFENOTFIS.setNOT_INFPROT_XMOTIVO(caNFENOTFISdto.getNOT_INFPROT_XMOTIVO());
        caNFENOTFIS.setNOT_INFPROT_NPROT(caNFENOTFISdto.getNOT_INFPROT_NPROT());
        caNFENOTFIS.setNOT_INFPROT_DHRECBTO(caNFENOTFISdto.getNOT_INFPROT_DHRECBTO());

        caNFENOTFIS.setNOT_INFREC_NREC(caNFENOTFISdto.getNOT_INFREC_NREC());
        caNFENOTFIS.setNOT_INFREC_DHRECBTO(caNFENOTFISdto.getNOT_INFREC_DHRECBTO());

        caNFENOTFIS.setNOT_INFCANC_NPROT(caNFENOTFISdto.getNOT_INFCANC_NPROT());
        caNFENOTFIS.setNOT_INFCANC_DHRECBTO(caNFENOTFISdto.getNOT_INFCANC_DHRECBTO());
        caNFENOTFIS.setNOT_INFCANC_XMOTIVO(caNFENOTFISdto.getNOT_INFCANC_XMOTIVO());
        caNFENOTFIS.setNOT_RETCONSRECINFE_CSTAT(caNFENOTFISdto.getNOT_RETCONSRECINFE_CSTAT());
        caNFENOTFIS.setNOT_RETCONSRECINFE_XMOTIVO(caNFENOTFISdto.getNOT_RETCONSRECINFE_XMOTIVO());

        if (caNFENOTFISdto.getCLIENTEdto() != null) {
            //caNFENOTFIS.setCLIENTE(convertCLIENTEdtotoCLIENTE(caNFENOTFISdto.getCLIENTEdto()));
        }

        if (caNFENOTFISdto.getCLIENTEdto() != null) {
            //caNFENOTFIS.setCLIENTE(convertCLIENTEdtotoCLIENTE(caNFENOTFISdto.getCLIENTEdto()));
        }

        if (caNFENOTFISdto.getTRANSPORTADORAdto() != null) {
            //caNFENOTFIS.setTRANSPORTADORA(convertTRANSPORTADORAdtotoTRANSPORTADORA(caNFENOTFISdto.getTRANSPORTADORAdto()));
        }

        if (caNFENOTFISdto.getCANFEDUPLICdto() != null) {
            //caNFENOTFIS.setCANFEDUPLIC(convertListCANFEDUPLICdtotoListCANFEDUPLIC(caNFENOTFISdto.getCANFEDUPLICdto()));
        }

        return caNFENOTFIS;
    }

}
