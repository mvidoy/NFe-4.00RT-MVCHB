package com.backend.dtos;

import com.backend.controllers.TRANSPORTADORAcontroller;
import static com.backend.dtos.ESTADOdto.convertESTADOtoESTADOdto;
import static com.backend.dtos.VENDEDORdto.convertVENDEDORtoVENDEDORdto;
import com.backend.models.CLIENTE;
import com.sun.istack.NotNull;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import static util.ObjetoUtil.NullToBlank;

public class CLIENTEdto {

    //@NotNull
    private String CLI_CODI;
    //@NotNull
    //@NotEmpty(message = "Nome é obrigatório")
    private String CLI_NOME;
    @NotNull
    //@NotEmpty(message = "Apelido é obrigatório")
    private String CLI_APEL;
    private String CLI_CGC0;
    private String CLI_INSE;
    //@NotNull
    private String CLI_TIPOSGQ;
    //@NotNull
    private String CLI_JUFI;
    //@NotNull
    private String CLI_SEGMENTO;
    private String CLI_TIPOSIMPLES;
    private Date CLI_DATACADASTRO;
    private String CLI_VEND;
    private String CLI_ENDE;
    private String CLI_BAIR;
    private String CLI_ESTA;
    private ESTADOdto ESTADOdto;
    private VENDEDORdto VENDEDORdto;
    private String CLI_CIDA;
    private String CLI_CODIGOMUNICIPIO;
    private String CLI_CEP0;
    private String CLI_FONE;
    private String CLI_FAX0;
    private String CLI_FONE2;
    private String CLI_FONE3;
    private String CLI_CONT;
    private String CLI_CON2;
    private String CLI_CON3;
    private String CLI_EMAI;
    private String CLI_OBSE;

    private String CLI_CEND;
    private String CLI_CBAI;
    private String CLI_CEST;
    private String CLI_CCID;
    private String CLI_CCEP;
    private String CLI_EMAILFINANCEIRO;

    private String CLI_EEND;
    private String CLI_EBAI;
    private String CLI_EEST;
    private String CLI_ECID;
    private String CLI_ECEP;

    private String CLI_TR01;
    private TRANSPORTADORAdto TRANSPORTADORAdto01;

    private String CLI_TR02;
    private TRANSPORTADORAdto TRANSPORTADORAdto02;

    private String CLI_NFE_EMAIL;

    private List<CLIENTE> Clientes;
    private List<CLIENTEdto> CLIENTEdto;

    public CLIENTEdto() {
    }

    public CLIENTEdto(CLIENTEdto eCLIENTE) {
        this.CLI_CODI = eCLIENTE.getCLI_CODI();
        this.CLI_NOME = eCLIENTE.getCLI_NOME();
        this.CLI_CGC0 = eCLIENTE.getCLI_CGC0();
        this.CLI_INSE = eCLIENTE.getCLI_INSE();
        this.CLI_TIPOSGQ = eCLIENTE.getCLI_TIPOSGQ();
        this.CLI_JUFI = eCLIENTE.getCLI_JUFI();
        this.CLI_SEGMENTO = eCLIENTE.getCLI_SEGMENTO();

    }

    public String getCLI_CODI() {
        return CLI_CODI;
    }

    public void setCLI_CODI(String CLI_CODI) {
        this.CLI_CODI = CLI_CODI;
    }

    public String getCLI_NOME() {
        return CLI_NOME;
    }

    public void setCLI_NOME(String CLI_NOME) {
        this.CLI_NOME = CLI_NOME;
    }

    public String getCLI_APEL() {
        return CLI_APEL;
    }

    public void setCLI_APEL(String CLI_APEL) {
        this.CLI_APEL = CLI_APEL;
    }

    public String getCLI_CGC0() {
        return CLI_CGC0;
    }

    public void setCLI_CGC0(String CLI_CGC0) {
        this.CLI_CGC0 = CLI_CGC0;
    }

    public String getCLI_INSE() {
        return CLI_INSE;
    }

    public void setCLI_INSE(String CLI_INSE) {
        this.CLI_INSE = CLI_INSE;
    }

    public String getCLI_TIPOSGQ() {
        return CLI_TIPOSGQ;
    }

    public void setCLI_TIPOSGQ(String CLI_TIPOSGQ) {
        this.CLI_TIPOSGQ = CLI_TIPOSGQ;
    }

    public String getCLI_JUFI() {
        return CLI_JUFI;
    }

    public void setCLI_JUFI(String CLI_JUFI) {
        this.CLI_JUFI = CLI_JUFI;
    }

    public String getCLI_SEGMENTO() {
        return CLI_SEGMENTO;
    }

    public void setCLI_SEGMENTO(String CLI_SEGMENTO) {
        this.CLI_SEGMENTO = CLI_SEGMENTO;
    }

    public List<CLIENTE> getClientes() {
        if (Clientes != null && !Clientes.isEmpty()) {
            for (int i = 0; i < Clientes.size(); i++) {

            }
        }
        return Clientes;
    }

    public void setClientes(List<CLIENTE> cLIENTEs) {
        this.Clientes = cLIENTEs;
    }

    public String getCLI_TIPOSIMPLES() {
        return CLI_TIPOSIMPLES;
    }

    public void setCLI_TIPOSIMPLES(String CLI_TIPOSIMPLES) {
        this.CLI_TIPOSIMPLES = CLI_TIPOSIMPLES;
    }

    public Date getCLI_DATACADASTRO() {
        return CLI_DATACADASTRO;
    }

    public void setCLI_DATACADASTRO(Date CLI_DATACADASTRO) {
        this.CLI_DATACADASTRO = CLI_DATACADASTRO;
    }

    public String getCLI_VEND() {
        return CLI_VEND;
    }

    public void setCLI_VEND(String CLI_VEND) {
        this.CLI_VEND = CLI_VEND;
    }

    public String getCLI_ENDE() {
        return CLI_ENDE;
    }

    public void setCLI_ENDE(String CLI_ENDE) {
        this.CLI_ENDE = CLI_ENDE;
    }

    public String getCLI_BAIR() {
        return CLI_BAIR;
    }

    public void setCLI_BAIR(String CLI_BAIR) {
        this.CLI_BAIR = CLI_BAIR;
    }

    public String getCLI_ESTA() {
        return CLI_ESTA;
    }

    public void setCLI_ESTA(String CLI_ESTA) {
        this.CLI_ESTA = CLI_ESTA;
    }

    public ESTADOdto getESTADOdto() {
        return ESTADOdto;
    }

    public void setESTADOdto(ESTADOdto ESTADOdto) {
        this.ESTADOdto = ESTADOdto;
    }

    public VENDEDORdto getVENDEDORdto() {
        return VENDEDORdto;
    }

    public void setVENDEDORdto(VENDEDORdto VENDEDORdto) {
        this.VENDEDORdto = VENDEDORdto;
    }

    public String getCLI_CIDA() {
        return CLI_CIDA;
    }

    public void setCLI_CIDA(String CLI_CIDA) {
        this.CLI_CIDA = CLI_CIDA;
    }

    public String getCLI_CODIGOMUNICIPIO() {
        return CLI_CODIGOMUNICIPIO;
    }

    public void setCLI_CODIGOMUNICIPIO(String CLI_CODIGOMUNICIPIO) {
        this.CLI_CODIGOMUNICIPIO = CLI_CODIGOMUNICIPIO;
    }

    public String getCLI_CEP0() {
        return CLI_CEP0;
    }

    public void setCLI_CEP0(String CLI_CEP0) {
        this.CLI_CEP0 = CLI_CEP0;
    }

    public String getCLI_FONE() {
        return CLI_FONE;
    }

    public void setCLI_FONE(String CLI_FONE) {
        this.CLI_FONE = CLI_FONE;
    }

    public String getCLI_FAX0() {
        return CLI_FAX0;
    }

    public void setCLI_FAX0(String CLI_FAX0) {
        this.CLI_FAX0 = CLI_FAX0;
    }

    public String getCLI_FONE2() {
        return CLI_FONE2;
    }

    public void setCLI_FONE2(String CLI_FONE2) {
        this.CLI_FONE2 = CLI_FONE2;
    }

    public String getCLI_FONE3() {
        return CLI_FONE3;
    }

    public void setCLI_FONE3(String CLI_FONE3) {
        this.CLI_FONE3 = CLI_FONE3;
    }

    public String getCLI_CONT() {
        return CLI_CONT;
    }

    public void setCLI_CONT(String CLI_CONT) {
        this.CLI_CONT = CLI_CONT;
    }

    public String getCLI_CON2() {
        return CLI_CON2;
    }

    public void setCLI_CON2(String CLI_CON2) {
        this.CLI_CON2 = CLI_CON2;
    }

    public String getCLI_CON3() {
        return CLI_CON3;
    }

    public void setCLI_CON3(String CLI_CON3) {
        this.CLI_CON3 = CLI_CON3;
    }

    public String getCLI_EMAI() {
        return CLI_EMAI;
    }

    public void setCLI_EMAI(String CLI_EMAI) {
        this.CLI_EMAI = CLI_EMAI;
    }

    public String getCLI_OBSE() {
        return CLI_OBSE;
    }

    public void setCLI_OBSE(String CLI_OBSE) {
        this.CLI_OBSE = CLI_OBSE;
    }

    public String getCLI_CEND() {
        return CLI_CEND;
    }

    public void setCLI_CEND(String CLI_CEND) {
        this.CLI_CEND = CLI_CEND;
    }

    public String getCLI_CBAI() {
        return CLI_CBAI;
    }

    public void setCLI_CBAI(String CLI_CBAI) {
        this.CLI_CBAI = CLI_CBAI;
    }

    public String getCLI_CEST() {
        return CLI_CEST;
    }

    public void setCLI_CEST(String CLI_CEST) {
        this.CLI_CEST = CLI_CEST;
    }

    public String getCLI_CCID() {
        return CLI_CCID;
    }

    public void setCLI_CCID(String CLI_CCID) {
        this.CLI_CCID = CLI_CCID;
    }

    public String getCLI_CCEP() {
        return CLI_CCEP;
    }

    public void setCLI_CCEP(String CLI_CCEP) {
        this.CLI_CCEP = CLI_CCEP;
    }

    public String getCLI_EMAILFINANCEIRO() {
        return CLI_EMAILFINANCEIRO;
    }

    public void setCLI_EMAILFINANCEIRO(String CLI_EMAILFINANCEIRO) {
        this.CLI_EMAILFINANCEIRO = CLI_EMAILFINANCEIRO;
    }

    public String getCLI_EEND() {
        return CLI_EEND;
    }

    public void setCLI_EEND(String CLI_EEND) {
        this.CLI_EEND = CLI_EEND;
    }

    public String getCLI_EBAI() {
        return CLI_EBAI;
    }

    public void setCLI_EBAI(String CLI_EBAI) {
        this.CLI_EBAI = CLI_EBAI;
    }

    public String getCLI_EEST() {
        return CLI_EEST;
    }

    public void setCLI_EEST(String CLI_EEST) {
        this.CLI_EEST = CLI_EEST;
    }

    public String getCLI_ECID() {
        return CLI_ECID;
    }

    public void setCLI_ECID(String CLI_ECID) {
        this.CLI_ECID = CLI_ECID;
    }

    public String getCLI_ECEP() {
        return CLI_ECEP;
    }

    public void setCLI_ECEP(String CLI_ECEP) {
        this.CLI_ECEP = CLI_ECEP;
    }

    public String getCLI_TR01() {
        return CLI_TR01;
    }

    public void setCLI_TR01(String CLI_TR01) {
        this.CLI_TR01 = CLI_TR01;
    }

    public TRANSPORTADORAdto getTRANSPORTADORAdto01() {
        return TRANSPORTADORAdto01;
    }

    public void setTRANSPORTADORAdto01(TRANSPORTADORAdto TRANSPORTADORAdto01) {
        this.TRANSPORTADORAdto01 = TRANSPORTADORAdto01;
    }

    public String getCLI_TR02() {
        return CLI_TR02;
    }

    public void setCLI_TR02(String CLI_TR02) {
        this.CLI_TR02 = CLI_TR02;
    }

    public TRANSPORTADORAdto getTRANSPORTADORAdto02() {
        return TRANSPORTADORAdto02;
    }

    public void setTRANSPORTADORAdto02(TRANSPORTADORAdto TRANSPORTADORAdto02) {
        this.TRANSPORTADORAdto02 = TRANSPORTADORAdto02;
    }

    public String getCLI_NFE_EMAIL() {
        return CLI_NFE_EMAIL;
    }

    public void setCLI_NFE_EMAIL(String CLI_NFE_EMAIL) {
        this.CLI_NFE_EMAIL = CLI_NFE_EMAIL;
    }

    public List<CLIENTEdto> getCLIENTEdto() {
        return CLIENTEdto;
    }

    public void setCLIENTEdto(List<CLIENTEdto> CLIENTEdto) {
        this.CLIENTEdto = CLIENTEdto;
    }

    public static CLIENTEdto convertCLIENTEtoCLIENTEdto(CLIENTE cLIENTE, boolean single) throws ParseException, SQLException {
        CLIENTEdto cLIENTEdto = new CLIENTEdto();
        cLIENTEdto.setCLI_CODI(cLIENTE.getCLI_CODI());
        cLIENTEdto.setCLI_NOME(cLIENTE.getCLI_NOME());
        cLIENTEdto.setCLI_APEL(cLIENTE.getCLI_APEL());
        cLIENTEdto.setCLI_CGC0(cLIENTE.getCLI_CGC0());
        cLIENTEdto.setCLI_INSE(cLIENTE.getCLI_INSE());
        cLIENTEdto.setCLI_TIPOSGQ(cLIENTE.getCLI_TIPOSGQ());
        cLIENTEdto.setCLI_JUFI(cLIENTE.getCLI_JUFI());
        cLIENTEdto.setCLI_SEGMENTO(cLIENTE.getCLI_SEGMENTO());
        cLIENTEdto.setCLI_TIPOSIMPLES(cLIENTE.getCLI_TIPOSIMPLES());
        cLIENTEdto.setCLI_DATACADASTRO(cLIENTE.getCLI_DATACADASTRO());
        cLIENTEdto.setCLI_VEND(cLIENTE.getCLI_VEND());
        cLIENTEdto.setCLI_ENDE(cLIENTE.getCLI_ENDE());
        cLIENTEdto.setCLI_BAIR(cLIENTE.getCLI_BAIR());
        cLIENTEdto.setCLI_ESTA(cLIENTE.getCLI_ESTA());
        if (cLIENTE.getESTADO() != null) {
            cLIENTEdto.setESTADOdto(convertESTADOtoESTADOdto(cLIENTE.getESTADO()));
        }
        if (cLIENTE.getVENDEDOR() != null) {
            cLIENTEdto.setVENDEDORdto(convertVENDEDORtoVENDEDORdto(cLIENTE.getVENDEDOR()));
        }
        cLIENTEdto.setCLI_CIDA(cLIENTE.getCLI_CIDA());
        cLIENTEdto.setCLI_CODIGOMUNICIPIO(cLIENTE.getCLI_CODIGOMUNICIPIO() == null ? "" : cLIENTE.getCLI_CODIGOMUNICIPIO());
        cLIENTEdto.setCLI_CEP0(cLIENTE.getCLI_CEP0());
        cLIENTEdto.setCLI_FONE(cLIENTE.getCLI_FONE());
        cLIENTEdto.setCLI_FAX0(NullToBlank(cLIENTE.getCLI_FAX0()));
        cLIENTEdto.setCLI_FONE2(NullToBlank(cLIENTE.getCLI_FONE2()));
        cLIENTEdto.setCLI_FONE3(NullToBlank(cLIENTE.getCLI_FONE3()));
        cLIENTEdto.setCLI_CONT(NullToBlank(cLIENTE.getCLI_CONT()));
        cLIENTEdto.setCLI_CON2(NullToBlank(cLIENTE.getCLI_CON2()));
        cLIENTEdto.setCLI_CON3(NullToBlank(cLIENTE.getCLI_CON3()));
        cLIENTEdto.setCLI_EMAI(NullToBlank(cLIENTE.getCLI_EMAI()));
        cLIENTEdto.setCLI_OBSE(NullToBlank(cLIENTE.getCLI_OBSE()));
        cLIENTEdto.setCLI_CEND(NullToBlank(cLIENTE.getCLI_CEND()));
        cLIENTEdto.setCLI_CBAI(NullToBlank(cLIENTE.getCLI_CBAI()));
        cLIENTEdto.setCLI_CEST(NullToBlank(cLIENTE.getCLI_CEST()));
        cLIENTEdto.setCLI_CCID(NullToBlank(cLIENTE.getCLI_CCID()));
        cLIENTEdto.setCLI_CCEP(NullToBlank(cLIENTE.getCLI_CCEP()));
        cLIENTEdto.setCLI_EMAILFINANCEIRO(NullToBlank(cLIENTE.getCLI_EMAILFINANCEIRO()));

        cLIENTEdto.setCLI_EEND(NullToBlank(cLIENTE.getCLI_EEND()));
        cLIENTEdto.setCLI_EBAI(NullToBlank(cLIENTE.getCLI_EBAI()));
        cLIENTEdto.setCLI_EEST(NullToBlank(cLIENTE.getCLI_EEST()));
        cLIENTEdto.setCLI_ECID(NullToBlank(cLIENTE.getCLI_ECID()));
        cLIENTEdto.setCLI_ECEP(NullToBlank(cLIENTE.getCLI_ECEP()));

        cLIENTEdto.setCLI_TR01(NullToBlank(cLIENTE.getCLI_TR01()));
        cLIENTEdto.setCLI_TR02(NullToBlank(cLIENTE.getCLI_TR02()));

        cLIENTEdto.setCLI_NFE_EMAIL(NullToBlank(cLIENTE.getCLI_NFE_EMAIL()));

        cLIENTEdto.setCLI_NOME(cLIENTE.getCLI_NOME() == null
                ? ""
                : cLIENTE.getCLI_NOME().trim());

        cLIENTEdto.setCLI_APEL(cLIENTE.getCLI_APEL() == null
                ? ""
                : cLIENTE.getCLI_APEL().trim());

        if (single
                && NullToBlank(cLIENTE.getCLI_TR01()).trim().length() > 0
                && !cLIENTE.getCLI_TR01().trim().equals("0000")) {
            cLIENTEdto.setTRANSPORTADORAdto01(TRANSPORTADORAcontroller.FindCodigo(NullToBlank(cLIENTE.getCLI_TR01())));
        }

        return cLIENTEdto;
    }

    public static CLIENTE convertCLIENTEdtotoCLIENTE(CLIENTEdto CLIENTEdto) {
        CLIENTE cLIENTE = new CLIENTE();
        cLIENTE.setCLI_CODI(CLIENTEdto.getCLI_CODI());
        if (CLIENTEdto.getCLI_NOME().trim().length() > 40) {
            cLIENTE.setCLI_NOME(CLIENTEdto.getCLI_NOME().substring(0, 40));
        } else {
            cLIENTE.setCLI_NOME(CLIENTEdto.getCLI_NOME().trim());
        }
        cLIENTE.setCLI_APEL(CLIENTEdto.getCLI_APEL());
        cLIENTE.setCLI_CGC0(CLIENTEdto.getCLI_CGC0());
        cLIENTE.setCLI_INSE(CLIENTEdto.getCLI_INSE());
        cLIENTE.setCLI_TIPOSGQ(CLIENTEdto.getCLI_TIPOSGQ());
        cLIENTE.setCLI_JUFI(CLIENTEdto.getCLI_JUFI());
        cLIENTE.setCLI_SEGMENTO(CLIENTEdto.getCLI_SEGMENTO());
        cLIENTE.setCLI_TIPOSIMPLES(CLIENTEdto.getCLI_TIPOSIMPLES());
        cLIENTE.setCLI_VEND(CLIENTEdto.getCLI_VEND());
        cLIENTE.setCLI_ENDE(CLIENTEdto.getCLI_ENDE());
        cLIENTE.setCLI_BAIR(CLIENTEdto.getCLI_BAIR());
        cLIENTE.setCLI_ESTA(CLIENTEdto.getCLI_ESTA());
        //cLIENTE.setESTADO(convertESTADOdtotoESTADO(CLIENTEdto.getESTADOdto()));
        cLIENTE.setCLI_CIDA(CLIENTEdto.getCLI_CIDA());
        cLIENTE.setCLI_CODIGOMUNICIPIO(CLIENTEdto.getCLI_CODIGOMUNICIPIO());
        cLIENTE.setCLI_CEP0(CLIENTEdto.getCLI_CEP0());
        cLIENTE.setCLI_FONE(CLIENTEdto.getCLI_FONE());
        cLIENTE.setCLI_FAX0(CLIENTEdto.getCLI_FAX0());
        cLIENTE.setCLI_FONE2(CLIENTEdto.getCLI_FONE2());
        cLIENTE.setCLI_FONE3(CLIENTEdto.getCLI_FONE3());
        cLIENTE.setCLI_CONT(CLIENTEdto.getCLI_CONT());
        cLIENTE.setCLI_CON2(CLIENTEdto.getCLI_CON2());
        cLIENTE.setCLI_CON3(CLIENTEdto.getCLI_CON3());
        cLIENTE.setCLI_EMAI(CLIENTEdto.getCLI_EMAI());
        cLIENTE.setCLI_OBSE(CLIENTEdto.getCLI_OBSE());
        cLIENTE.setCLI_CEND(CLIENTEdto.getCLI_CEND());
        cLIENTE.setCLI_CBAI(CLIENTEdto.getCLI_CBAI());
        cLIENTE.setCLI_CEST(CLIENTEdto.getCLI_CEST());
        cLIENTE.setCLI_CCID(CLIENTEdto.getCLI_CCID());
        cLIENTE.setCLI_CCEP(CLIENTEdto.getCLI_CCEP());
        cLIENTE.setCLI_EMAILFINANCEIRO(CLIENTEdto.getCLI_EMAILFINANCEIRO());

        cLIENTE.setCLI_EEND(CLIENTEdto.getCLI_EEND());
        cLIENTE.setCLI_EBAI(CLIENTEdto.getCLI_EBAI());
        cLIENTE.setCLI_EEST(CLIENTEdto.getCLI_EEST());
        cLIENTE.setCLI_ECID(CLIENTEdto.getCLI_ECID());
        cLIENTE.setCLI_ECEP(CLIENTEdto.getCLI_ECEP());

        cLIENTE.setCLI_TR01(CLIENTEdto.getCLI_TR01());
        cLIENTE.setCLI_TR02(CLIENTEdto.getCLI_TR02());

        cLIENTE.setCLI_NFE_EMAIL(NullToBlank(CLIENTEdto.getCLI_NFE_EMAIL()));

        cLIENTE.setCLI_NOME(cLIENTE.getCLI_NOME() == null
                ? ""
                : cLIENTE.getCLI_NOME().trim());

        cLIENTE.setCLI_APEL(cLIENTE.getCLI_APEL() == null
                ? ""
                : cLIENTE.getCLI_APEL().trim());
        //cLIENTE.setCLI_ISENTOICMS(false);
        return cLIENTE;
    }

}
