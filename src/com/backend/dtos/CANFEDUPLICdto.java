package com.backend.dtos;

import static com.backend.dtos.CANFEIRDUPLdto.convertListCANFEIRDUPLdtotoListCANFEIRDUPL;
import static com.backend.dtos.CANFEIRDUPLdto.convertListCANFEIRDUPLtoListCANFEIRDUPLdto;
import com.backend.models.*;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CANFEDUPLICdto implements Serializable {

    private Long Id;
    private Long CANFENOTFIS_Id;
    private String PAR_CODI;
    private String PAR_PARC;
    private String PAR_CLIE;
    private Date PAR_DTEM;
    private Date PAR_DTPG;
    private Double PAR_VALO;
    private Date PAR_VENC;
    private Double PAR_VLPG;
    private Double PAR_VANO;
    private Double PAR_VLIO;
    private Double PAR_VLAB;
    private Double PAR_JUROSPAGO;
    private Double PAR_VALORPAGO;
    private Double PAR_JURO;
    private Double PAR_DESC;
    private String PAR_FLEM;
    private String PAR_ESPECIEDOC;
    private String PAR_LOCALIMP;
    private String PAR_ACEITE;
    private Integer PAR_PRAZOPROT;
    private String PAR_AGENCIADEB;
    private String PAR_RAZAODEB;
    private String PAR_CONTADEB;
    private String PAR_CARTEIRA;
    private String PAR_STATUS;
    private String PAR_BANCO;
    private String PAR_AGENCIA;
    private String PAR_CONTA;
    private Date PAR_VENCTITULO;
    private String PAR_NONU;
    private Double PAR_VLBO;
    private String PAR_DEBEMITE;
    private String PAR_REGDEB;
    private String PAR_AGENCIADEP;
    private String PAR_TIPOINSCRICAO;
    private String PAR_INSCRICAO;
    private String PAR_NOME;
    private String PAR_ARQREMESSA;
    private Date PAR_DATAREMESSA;
    private Date PAR_DTPRCESS;
    private Date PAR_DTREMESSA;
    private Date PAR_DTOCORR;
    private String PAR_OCORRENCIA;
    private String PAR_MOTIVOS;
    private String PAR_ENDERECO;
    private String PAR_CEP;
    private String PAR_CIDADE;
    private String PAR_UF;
    private String PAR_BAIRRO;
    private String PAR_EMAIL;
    private String PAR_CODIGOSAC;
    private String PAR_CODIGOSAR;
    private String PAR_NUMDOC;
    private String PAR_PARTICIPA;
    private String PAR_BANCOCBR;
    private String PAR_AGENCIACBR;
    private String PAR_ARQRETORNO;
    private String PAR_NUMREGRETORNO;
    private Date PAR_DATARETORNO;
    private Date PAR_DTIM;
    private String PAR_FLIM;
    private Boolean PAR_BOLETOENVIADO;
    private String PAR_LINHADIGITAVEL;
    private String PAR_CODIGODEBARRAS;

    private List<CANFEDUPLICdto> CANFEDUPLICdto;
    private List<CANFEIRDUPLdto> CANFEIRDUPLdto;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Long getCANFENOTFIS_Id() {
        return CANFENOTFIS_Id;
    }

    public void setCANFENOTFIS_Id(Long CANFENOTFIS_Id) {
        this.CANFENOTFIS_Id = CANFENOTFIS_Id;
    }

    public String getPAR_CODI() {
        return PAR_CODI;
    }

    public void setPAR_CODI(String PAR_CODI) {
        this.PAR_CODI = PAR_CODI;
    }

    public String getPAR_CLIE() {
        return PAR_CLIE;
    }

    public void setPAR_CLIE(String PAR_CLIE) {
        this.PAR_CLIE = PAR_CLIE;
    }

    public Date getPAR_DTEM() {
        return PAR_DTEM;
    }

    public void setPAR_DTEM(Date PAR_DTEM) {
        this.PAR_DTEM = PAR_DTEM;
    }

    public Date getPAR_DTPG() {
        return PAR_DTPG;
    }

    public void setPAR_DTPG(Date PAR_DTPG) {
        this.PAR_DTPG = PAR_DTPG;
    }

    public String getPAR_PARC() {
        return PAR_PARC;
    }

    public void setPAR_PARC(String PAR_PARC) {
        this.PAR_PARC = PAR_PARC;
    }

    public Double getPAR_VALO() {
        return PAR_VALO;
    }

    public void setPAR_VALO(Double PAR_VALO) {
        this.PAR_VALO = PAR_VALO;
    }

    public Date getPAR_VENC() {
        return PAR_VENC;
    }

    public void setPAR_VENC(Date PAR_VENC) {
        this.PAR_VENC = PAR_VENC;
    }

    public Double getPAR_VLPG() {
        return PAR_VLPG;
    }

    public void setPAR_VLPG(Double PAR_VLPG) {
        this.PAR_VLPG = PAR_VLPG;
    }

    public Double getPAR_VANO() {
        return PAR_VANO;
    }

    public void setPAR_VANO(Double PAR_VANO) {
        this.PAR_VANO = PAR_VANO;
    }

    public Double getPAR_VLIO() {
        return PAR_VLIO;
    }

    public void setPAR_VLIO(Double PAR_VLIO) {
        this.PAR_VLIO = PAR_VLIO;
    }

    public Double getPAR_VLAB() {
        return PAR_VLAB;
    }

    public void setPAR_VLAB(Double PAR_VLAB) {
        this.PAR_VLAB = PAR_VLAB;
    }

    public Double getPAR_JUROSPAGO() {
        return PAR_JUROSPAGO;
    }

    public void setPAR_JUROSPAGO(Double PAR_JUROSPAGO) {
        this.PAR_JUROSPAGO = PAR_JUROSPAGO;
    }

    public Double getPAR_VALORPAGO() {
        return PAR_VALORPAGO;
    }

    public void setPAR_VALORPAGO(Double PAR_VALORPAGO) {
        this.PAR_VALORPAGO = PAR_VALORPAGO;
    }

    public Double getPAR_JURO() {
        return PAR_JURO;
    }

    public void setPAR_JURO(Double PAR_JURO) {
        this.PAR_JURO = PAR_JURO;
    }

    public Double getPAR_DESC() {
        return PAR_DESC;
    }

    public void setPAR_DESC(Double PAR_DESC) {
        this.PAR_DESC = PAR_DESC;
    }

    public String getPAR_FLEM() {
        return PAR_FLEM;
    }

    public void setPAR_FLEM(String PAR_FLEM) {
        this.PAR_FLEM = PAR_FLEM;
    }

    public String getPAR_ESPECIEDOC() {
        return PAR_ESPECIEDOC;
    }

    public void setPAR_ESPECIEDOC(String PAR_ESPECIEDOC) {
        this.PAR_ESPECIEDOC = PAR_ESPECIEDOC;
    }

    public String getPAR_LOCALIMP() {
        return PAR_LOCALIMP;
    }

    public void setPAR_LOCALIMP(String PAR_LOCALIMP) {
        this.PAR_LOCALIMP = PAR_LOCALIMP;
    }

    public String getPAR_ACEITE() {
        return PAR_ACEITE;
    }

    public void setPAR_ACEITE(String PAR_ACEITE) {
        this.PAR_ACEITE = PAR_ACEITE;
    }

    public Integer getPAR_PRAZOPROT() {
        return PAR_PRAZOPROT;
    }

    public void setPAR_PRAZOPROT(Integer PAR_PRAZOPROT) {
        this.PAR_PRAZOPROT = PAR_PRAZOPROT;
    }

    public String getPAR_AGENCIADEB() {
        return PAR_AGENCIADEB;
    }

    public void setPAR_AGENCIADEB(String PAR_AGENCIADEB) {
        this.PAR_AGENCIADEB = PAR_AGENCIADEB;
    }

    public String getPAR_RAZAODEB() {
        return PAR_RAZAODEB;
    }

    public void setPAR_RAZAODEB(String PAR_RAZAODEB) {
        this.PAR_RAZAODEB = PAR_RAZAODEB;
    }

    public String getPAR_CONTADEB() {
        return PAR_CONTADEB;
    }

    public void setPAR_CONTADEB(String PAR_CONTADEB) {
        this.PAR_CONTADEB = PAR_CONTADEB;
    }

    public String getPAR_CARTEIRA() {
        return PAR_CARTEIRA;
    }

    public void setPAR_CARTEIRA(String PAR_CARTEIRA) {
        this.PAR_CARTEIRA = PAR_CARTEIRA;
    }

    public String getPAR_STATUS() {
        return PAR_STATUS;
    }

    public void setPAR_STATUS(String PAR_STATUS) {
        this.PAR_STATUS = PAR_STATUS;
    }

    public String getPAR_BANCO() {
        return PAR_BANCO;
    }

    public void setPAR_BANCO(String PAR_BANCO) {
        this.PAR_BANCO = PAR_BANCO;
    }

    public String getPAR_AGENCIA() {
        return PAR_AGENCIA;
    }

    public void setPAR_AGENCIA(String PAR_AGENCIA) {
        this.PAR_AGENCIA = PAR_AGENCIA;
    }

    public String getPAR_CONTA() {
        return PAR_CONTA;
    }

    public void setPAR_CONTA(String PAR_CONTA) {
        this.PAR_CONTA = PAR_CONTA;
    }

    public Date getPAR_VENCTITULO() {
        return PAR_VENCTITULO;
    }

    public void setPAR_VENCTITULO(Date PAR_VENCTITULO) {
        this.PAR_VENCTITULO = PAR_VENCTITULO;
    }

    public String getPAR_NONU() {
        return PAR_NONU;
    }

    public void setPAR_NONU(String PAR_NONU) {
        this.PAR_NONU = PAR_NONU;
    }

    public Double getPAR_VLBO() {
        return PAR_VLBO;
    }

    public void setPAR_VLBO(Double PAR_VLBO) {
        this.PAR_VLBO = PAR_VLBO;
    }

    public String getPAR_DEBEMITE() {
        return PAR_DEBEMITE;
    }

    public void setPAR_DEBEMITE(String PAR_DEBEMITE) {
        this.PAR_DEBEMITE = PAR_DEBEMITE;
    }

    public String getPAR_REGDEB() {
        return PAR_REGDEB;
    }

    public void setPAR_REGDEB(String PAR_REGDEB) {
        this.PAR_REGDEB = PAR_REGDEB;
    }

    public String getPAR_AGENCIADEP() {
        return PAR_AGENCIADEP;
    }

    public void setPAR_AGENCIADEP(String PAR_AGENCIADEP) {
        this.PAR_AGENCIADEP = PAR_AGENCIADEP;
    }

    public String getPAR_TIPOINSCRICAO() {
        return PAR_TIPOINSCRICAO;
    }

    public void setPAR_TIPOINSCRICAO(String PAR_TIPOINSCRICAO) {
        this.PAR_TIPOINSCRICAO = PAR_TIPOINSCRICAO;
    }

    public String getPAR_INSCRICAO() {
        return PAR_INSCRICAO;
    }

    public void setPAR_INSCRICAO(String PAR_INSCRICAO) {
        this.PAR_INSCRICAO = PAR_INSCRICAO;
    }

    public String getPAR_NOME() {
        return PAR_NOME;
    }

    public void setPAR_NOME(String PAR_NOME) {
        this.PAR_NOME = PAR_NOME;
    }

    public String getPAR_ARQREMESSA() {
        return PAR_ARQREMESSA;
    }

    public void setPAR_ARQREMESSA(String PAR_ARQREMESSA) {
        this.PAR_ARQREMESSA = PAR_ARQREMESSA;
    }

    public Date getPAR_DATAREMESSA() {
        return PAR_DATAREMESSA;
    }

    public void setPAR_DATAREMESSA(Date PAR_DATAREMESSA) {
        this.PAR_DATAREMESSA = PAR_DATAREMESSA;
    }

    public Date getPAR_DTPRCESS() {
        return PAR_DTPRCESS;
    }

    public void setPAR_DTPRCESS(Date PAR_DTPRCESS) {
        this.PAR_DTPRCESS = PAR_DTPRCESS;
    }

    public Date getPAR_DTREMESSA() {
        return PAR_DTREMESSA;
    }

    public void setPAR_DTREMESSA(Date PAR_DTREMESSA) {
        this.PAR_DTREMESSA = PAR_DTREMESSA;
    }

    public Date getPAR_DTOCORR() {
        return PAR_DTOCORR;
    }

    public void setPAR_DTOCORR(Date PAR_DTOCORR) {
        this.PAR_DTOCORR = PAR_DTOCORR;
    }

    public String getPAR_OCORRENCIA() {
        return PAR_OCORRENCIA;
    }

    public void setPAR_OCORRENCIA(String PAR_OCORRENCIA) {
        this.PAR_OCORRENCIA = PAR_OCORRENCIA;
    }

    public String getPAR_MOTIVOS() {
        return PAR_MOTIVOS;
    }

    public void setPAR_MOTIVOS(String PAR_MOTIVOS) {
        this.PAR_MOTIVOS = PAR_MOTIVOS;
    }

    public String getPAR_ENDERECO() {
        return PAR_ENDERECO;
    }

    public void setPAR_ENDERECO(String PAR_ENDERECO) {
        this.PAR_ENDERECO = PAR_ENDERECO;
    }

    public String getPAR_CEP() {
        return PAR_CEP;
    }

    public void setPAR_CEP(String PAR_CEP) {
        this.PAR_CEP = PAR_CEP;
    }

    public String getPAR_CIDADE() {
        return PAR_CIDADE;
    }

    public void setPAR_CIDADE(String PAR_CIDADE) {
        this.PAR_CIDADE = PAR_CIDADE;
    }

    public String getPAR_UF() {
        return PAR_UF;
    }

    public void setPAR_UF(String PAR_UF) {
        this.PAR_UF = PAR_UF;
    }

    public String getPAR_BAIRRO() {
        return PAR_BAIRRO;
    }

    public void setPAR_BAIRRO(String PAR_BAIRRO) {
        this.PAR_BAIRRO = PAR_BAIRRO;
    }

    public String getPAR_EMAIL() {
        return PAR_EMAIL;
    }

    public void setPAR_EMAIL(String PAR_EMAIL) {
        this.PAR_EMAIL = PAR_EMAIL;
    }

    public String getPAR_CODIGOSAC() {
        return PAR_CODIGOSAC;
    }

    public void setPAR_CODIGOSAC(String PAR_CODIGOSAC) {
        this.PAR_CODIGOSAC = PAR_CODIGOSAC;
    }

    public String getPAR_CODIGOSAR() {
        return PAR_CODIGOSAR;
    }

    public void setPAR_CODIGOSAR(String PAR_CODIGOSAR) {
        this.PAR_CODIGOSAR = PAR_CODIGOSAR;
    }

    public String getPAR_NUMDOC() {
        return PAR_NUMDOC;
    }

    public void setPAR_NUMDOC(String PAR_NUMDOC) {
        this.PAR_NUMDOC = PAR_NUMDOC;
    }

    public String getPAR_PARTICIPA() {
        return PAR_PARTICIPA;
    }

    public void setPAR_PARTICIPA(String PAR_PARTICIPA) {
        this.PAR_PARTICIPA = PAR_PARTICIPA;
    }

    public String getPAR_BANCOCBR() {
        return PAR_BANCOCBR;
    }

    public void setPAR_BANCOCBR(String PAR_BANCOCBR) {
        this.PAR_BANCOCBR = PAR_BANCOCBR;
    }

    public String getPAR_AGENCIACBR() {
        return PAR_AGENCIACBR;
    }

    public void setPAR_AGENCIACBR(String PAR_AGENCIACBR) {
        this.PAR_AGENCIACBR = PAR_AGENCIACBR;
    }

    public String getPAR_ARQRETORNO() {
        return PAR_ARQRETORNO;
    }

    public void setPAR_ARQRETORNO(String PAR_ARQRETORNO) {
        this.PAR_ARQRETORNO = PAR_ARQRETORNO;
    }

    public String getPAR_NUMREGRETORNO() {
        return PAR_NUMREGRETORNO;
    }

    public void setPAR_NUMREGRETORNO(String PAR_NUMREGRETORNO) {
        this.PAR_NUMREGRETORNO = PAR_NUMREGRETORNO;
    }

    public Date getPAR_DATARETORNO() {
        return PAR_DATARETORNO;
    }

    public void setPAR_DATARETORNO(Date PAR_DATARETORNO) {
        this.PAR_DATARETORNO = PAR_DATARETORNO;
    }

    public Date getPAR_DTIM() {
        return PAR_DTIM;
    }

    public void setPAR_DTIM(Date PAR_DTIM) {
        this.PAR_DTIM = PAR_DTIM;
    }

    public String getPAR_FLIM() {
        return PAR_FLIM;
    }

    public void setPAR_FLIM(String PAR_FLIM) {
        this.PAR_FLIM = PAR_FLIM;
    }

    public Boolean isPAR_BOLETOENVIADO() {
        return PAR_BOLETOENVIADO;
    }

    public void setPAR_BOLETOENVIADO(Boolean PAR_BOLETOENVIADO) {
        this.PAR_BOLETOENVIADO = PAR_BOLETOENVIADO;
    }

    public String getPAR_LINHADIGITAVEL() {
        return PAR_LINHADIGITAVEL;
    }

    public String getPAR_CODIGODEBARRAS() {
        return PAR_CODIGODEBARRAS;
    }

    public void setPAR_LINHADIGITAVEL(String PAR_LINHADIGITAVEL) {
        this.PAR_LINHADIGITAVEL = PAR_LINHADIGITAVEL;
    }

    public void setPAR_CODIGODEBARRAS(String PAR_CODIGODEBARRAS) {
        this.PAR_CODIGODEBARRAS = PAR_CODIGODEBARRAS;
    }

    public List<CANFEDUPLICdto> getCANFEDUPLICdto() {
        return CANFEDUPLICdto;
    }

    public void setCANFEDUPLICdto(List<CANFEDUPLICdto> CANFEDUPLICdto) {
        this.CANFEDUPLICdto = CANFEDUPLICdto;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public static CANFEDUPLICdto convertCANFEDUPLICtoCANFEDUPLICdto(CANFEDUPLIC caNFEDUPLIC, Boolean caNFENOTFIS) throws ParseException, SQLException {

        CANFEDUPLICdto caNFEDUPLICdto = new CANFEDUPLICdto();
        if (caNFEDUPLIC != null) {
            caNFEDUPLICdto.setId(caNFEDUPLIC.getId());
            caNFEDUPLICdto.setCANFENOTFIS_Id(caNFEDUPLIC.getCANFENOTFIS_Id());
            caNFEDUPLICdto.setPAR_CODI(caNFEDUPLIC.getPAR_CODI());
            caNFEDUPLICdto.setPAR_PARC(caNFEDUPLIC.getPAR_PARC());
            caNFEDUPLICdto.setPAR_CLIE(caNFEDUPLIC.getPAR_CLIE());
            caNFEDUPLICdto.setPAR_DTEM(caNFEDUPLIC.getPAR_DTEM());
            caNFEDUPLICdto.setPAR_VALO(caNFEDUPLIC.getPAR_VALO());
            caNFEDUPLICdto.setPAR_VENC(caNFEDUPLIC.getPAR_VENC());
            caNFEDUPLICdto.setPAR_VLPG(caNFEDUPLIC.getPAR_VLPG());
            caNFEDUPLICdto.setPAR_VANO(caNFEDUPLIC.getPAR_VANO());
            caNFEDUPLICdto.setPAR_VLIO(caNFEDUPLIC.getPAR_VLIO());
            caNFEDUPLICdto.setPAR_VLAB(caNFEDUPLIC.getPAR_VLAB());
            caNFEDUPLICdto.setPAR_JUROSPAGO(caNFEDUPLIC.getPAR_JUROSPAGO());
            caNFEDUPLICdto.setPAR_VALORPAGO(caNFEDUPLIC.getPAR_VALORPAGO());
            caNFEDUPLICdto.setPAR_JURO(caNFEDUPLIC.getPAR_JURO());
            caNFEDUPLICdto.setPAR_DESC(caNFEDUPLIC.getPAR_DESC());
            caNFEDUPLICdto.setPAR_FLEM(caNFEDUPLIC.getPAR_FLEM());
            caNFEDUPLICdto.setPAR_ESPECIEDOC(caNFEDUPLIC.getPAR_ESPECIEDOC());
            caNFEDUPLICdto.setPAR_LOCALIMP(caNFEDUPLIC.getPAR_LOCALIMP());
            caNFEDUPLICdto.setPAR_ACEITE(caNFEDUPLIC.getPAR_ACEITE());
            caNFEDUPLICdto.setPAR_PRAZOPROT(caNFEDUPLIC.getPAR_PRAZOPROT());
            caNFEDUPLICdto.setPAR_AGENCIADEB(caNFEDUPLIC.getPAR_AGENCIADEB());
            caNFEDUPLICdto.setPAR_RAZAODEB(caNFEDUPLIC.getPAR_RAZAODEB());
            caNFEDUPLICdto.setPAR_CONTADEB(caNFEDUPLIC.getPAR_CONTADEB());
            caNFEDUPLICdto.setPAR_CARTEIRA(caNFEDUPLIC.getPAR_CARTEIRA());
            caNFEDUPLICdto.setPAR_STATUS(caNFEDUPLIC.getPAR_STATUS());
            caNFEDUPLICdto.setPAR_BANCO(caNFEDUPLIC.getPAR_BANCO());
            caNFEDUPLICdto.setPAR_AGENCIA(caNFEDUPLIC.getPAR_AGENCIA());
            caNFEDUPLICdto.setPAR_CONTA(caNFEDUPLIC.getPAR_CONTA());
            caNFEDUPLICdto.setPAR_VENCTITULO(caNFEDUPLIC.getPAR_VENCTITULO());
            caNFEDUPLICdto.setPAR_NONU(caNFEDUPLIC.getPAR_NONU());
            caNFEDUPLICdto.setPAR_VLBO(caNFEDUPLIC.getPAR_VLBO());
            caNFEDUPLICdto.setPAR_DEBEMITE(caNFEDUPLIC.getPAR_DEBEMITE());
            caNFEDUPLICdto.setPAR_REGDEB(caNFEDUPLIC.getPAR_REGDEB());
            caNFEDUPLICdto.setPAR_AGENCIADEP(caNFEDUPLIC.getPAR_AGENCIADEP());
            caNFEDUPLICdto.setPAR_TIPOINSCRICAO(caNFEDUPLIC.getPAR_TIPOINSCRICAO());
            caNFEDUPLICdto.setPAR_INSCRICAO(caNFEDUPLIC.getPAR_INSCRICAO());
            caNFEDUPLICdto.setPAR_NOME(caNFEDUPLIC.getPAR_NOME());
            caNFEDUPLICdto.setPAR_ARQREMESSA(caNFEDUPLIC.getPAR_ARQREMESSA());
            caNFEDUPLICdto.setPAR_DATAREMESSA(caNFEDUPLIC.getPAR_DATAREMESSA());
            caNFEDUPLICdto.setPAR_DTPRCESS(caNFEDUPLIC.getPAR_DTPRCESS());
            caNFEDUPLICdto.setPAR_DTREMESSA(caNFEDUPLIC.getPAR_DTREMESSA());
            caNFEDUPLICdto.setPAR_DTOCORR(caNFEDUPLIC.getPAR_DTOCORR());
            caNFEDUPLICdto.setPAR_OCORRENCIA(caNFEDUPLIC.getPAR_OCORRENCIA());
            caNFEDUPLICdto.setPAR_MOTIVOS(caNFEDUPLIC.getPAR_MOTIVOS());
            caNFEDUPLICdto.setPAR_ENDERECO(caNFEDUPLIC.getPAR_ENDERECO());
            caNFEDUPLICdto.setPAR_CEP(caNFEDUPLIC.getPAR_CEP());
            caNFEDUPLICdto.setPAR_CIDADE(caNFEDUPLIC.getPAR_CIDADE());
            caNFEDUPLICdto.setPAR_UF(caNFEDUPLIC.getPAR_UF());
            caNFEDUPLICdto.setPAR_BAIRRO(caNFEDUPLIC.getPAR_BAIRRO());
            caNFEDUPLICdto.setPAR_EMAIL(caNFEDUPLIC.getPAR_EMAIL());
            caNFEDUPLICdto.setPAR_CODIGOSAC(caNFEDUPLIC.getPAR_CODIGOSAC());
            caNFEDUPLICdto.setPAR_CODIGOSAR(caNFEDUPLIC.getPAR_CODIGOSAR());
            caNFEDUPLICdto.setPAR_NUMDOC(caNFEDUPLIC.getPAR_NUMDOC());
            caNFEDUPLICdto.setPAR_PARTICIPA(caNFEDUPLIC.getPAR_PARTICIPA());
            if (caNFEDUPLIC.getCANFEIRDUPL() != null
                    && !caNFENOTFIS) {
                caNFEDUPLICdto.setCANFEIRDUPLdto(convertListCANFEIRDUPLtoListCANFEIRDUPLdto(caNFEDUPLIC.getCANFEIRDUPL(), caNFENOTFIS));
            }
            caNFEDUPLICdto.setPAR_BANCOCBR(caNFEDUPLIC.getPAR_BANCOCBR());
            caNFEDUPLICdto.setPAR_AGENCIACBR(caNFEDUPLIC.getPAR_AGENCIACBR());
            caNFEDUPLICdto.setPAR_ARQRETORNO(caNFEDUPLIC.getPAR_ARQRETORNO());
            caNFEDUPLICdto.setPAR_NUMREGRETORNO(caNFEDUPLIC.getPAR_NUMREGRETORNO());
            caNFEDUPLICdto.setPAR_DATARETORNO(caNFEDUPLIC.getPAR_DATARETORNO());
            caNFEDUPLICdto.setPAR_DTIM(caNFEDUPLIC.getPAR_DTIM());
            caNFEDUPLICdto.setPAR_FLIM(caNFEDUPLIC.getPAR_FLIM());
            caNFEDUPLICdto.setPAR_BOLETOENVIADO(caNFEDUPLIC.isPAR_BOLETOENVIADO());
            caNFEDUPLICdto.setPAR_LINHADIGITAVEL(caNFEDUPLIC.getPAR_LINHADIGITAVEL());
            caNFEDUPLICdto.setPAR_CODIGODEBARRAS(caNFEDUPLIC.getPAR_CODIGODEBARRAS());
        }
        return caNFEDUPLICdto;
    }

    public static CANFEDUPLIC convertCANFEDUPLICdtotoCANFEDUPLIC(CANFEDUPLICdto caNFEDUPLICdto) throws ParseException {
        CANFEDUPLIC caNFEDUPLIC = new CANFEDUPLIC();
        caNFEDUPLIC.setId(caNFEDUPLICdto.getId());
        caNFEDUPLIC.setCANFENOTFIS_Id(caNFEDUPLICdto.getCANFENOTFIS_Id());
        caNFEDUPLIC.setPAR_CODI(caNFEDUPLICdto.getPAR_CODI());
        caNFEDUPLIC.setPAR_PARC(caNFEDUPLICdto.getPAR_PARC());
        caNFEDUPLIC.setPAR_CLIE(caNFEDUPLICdto.getPAR_CLIE());
        caNFEDUPLIC.setPAR_DTEM(caNFEDUPLICdto.getPAR_DTEM());
        caNFEDUPLIC.setPAR_VALO(caNFEDUPLICdto.getPAR_VALO());
        caNFEDUPLIC.setPAR_VENC(caNFEDUPLICdto.getPAR_VENC());
        caNFEDUPLIC.setPAR_VLPG(caNFEDUPLICdto.getPAR_VLPG());
        caNFEDUPLIC.setPAR_VANO(caNFEDUPLICdto.getPAR_VANO());
        caNFEDUPLIC.setPAR_VLIO(caNFEDUPLICdto.getPAR_VLIO());
        caNFEDUPLIC.setPAR_VLAB(caNFEDUPLICdto.getPAR_VLAB());
        caNFEDUPLIC.setPAR_JUROSPAGO(caNFEDUPLICdto.getPAR_JUROSPAGO());
        caNFEDUPLIC.setPAR_VALORPAGO(caNFEDUPLICdto.getPAR_VALORPAGO());
        caNFEDUPLIC.setPAR_JURO(caNFEDUPLICdto.getPAR_JURO());
        caNFEDUPLIC.setPAR_DESC(caNFEDUPLICdto.getPAR_DESC());
        caNFEDUPLIC.setPAR_FLEM(caNFEDUPLICdto.getPAR_FLEM());
        caNFEDUPLIC.setPAR_ESPECIEDOC(caNFEDUPLICdto.getPAR_ESPECIEDOC());
        caNFEDUPLIC.setPAR_LOCALIMP(caNFEDUPLICdto.getPAR_LOCALIMP());
        caNFEDUPLIC.setPAR_ACEITE(caNFEDUPLICdto.getPAR_ACEITE());
        caNFEDUPLIC.setPAR_PRAZOPROT(caNFEDUPLICdto.getPAR_PRAZOPROT());
        caNFEDUPLIC.setPAR_AGENCIADEB(caNFEDUPLICdto.getPAR_AGENCIADEB());
        caNFEDUPLIC.setPAR_RAZAODEB(caNFEDUPLICdto.getPAR_RAZAODEB());
        caNFEDUPLIC.setPAR_CONTADEB(caNFEDUPLICdto.getPAR_CONTADEB());
        caNFEDUPLIC.setPAR_CARTEIRA(caNFEDUPLICdto.getPAR_CARTEIRA());
        caNFEDUPLIC.setPAR_STATUS(caNFEDUPLICdto.getPAR_STATUS());
        caNFEDUPLIC.setPAR_BANCO(caNFEDUPLICdto.getPAR_BANCO());
        caNFEDUPLIC.setPAR_AGENCIA(caNFEDUPLICdto.getPAR_AGENCIA());
        caNFEDUPLIC.setPAR_CONTA(caNFEDUPLICdto.getPAR_CONTA());
        caNFEDUPLIC.setPAR_VENCTITULO(caNFEDUPLICdto.getPAR_VENCTITULO());
        caNFEDUPLIC.setPAR_NONU(caNFEDUPLICdto.getPAR_NONU());
        caNFEDUPLIC.setPAR_VLBO(caNFEDUPLICdto.getPAR_VLBO());
        caNFEDUPLIC.setPAR_DEBEMITE(caNFEDUPLICdto.getPAR_DEBEMITE());
        caNFEDUPLIC.setPAR_REGDEB(caNFEDUPLICdto.getPAR_REGDEB());
        caNFEDUPLIC.setPAR_AGENCIADEP(caNFEDUPLICdto.getPAR_AGENCIADEP());
        caNFEDUPLIC.setPAR_TIPOINSCRICAO(caNFEDUPLICdto.getPAR_TIPOINSCRICAO());
        caNFEDUPLIC.setPAR_INSCRICAO(caNFEDUPLICdto.getPAR_INSCRICAO());
        caNFEDUPLIC.setPAR_NOME(caNFEDUPLICdto.getPAR_NOME());
        caNFEDUPLIC.setPAR_ARQREMESSA(caNFEDUPLICdto.getPAR_ARQREMESSA());
        caNFEDUPLIC.setPAR_DATAREMESSA(caNFEDUPLICdto.getPAR_DATAREMESSA());
        caNFEDUPLIC.setPAR_DTPRCESS(caNFEDUPLICdto.getPAR_DTPRCESS());
        caNFEDUPLIC.setPAR_DTREMESSA(caNFEDUPLICdto.getPAR_DTREMESSA());
        caNFEDUPLIC.setPAR_DTOCORR(caNFEDUPLICdto.getPAR_DTOCORR());
        caNFEDUPLIC.setPAR_OCORRENCIA(caNFEDUPLICdto.getPAR_OCORRENCIA());
        caNFEDUPLIC.setPAR_MOTIVOS(caNFEDUPLICdto.getPAR_MOTIVOS());
        caNFEDUPLIC.setPAR_ENDERECO(caNFEDUPLICdto.getPAR_ENDERECO());
        caNFEDUPLIC.setPAR_CEP(caNFEDUPLICdto.getPAR_CEP());
        caNFEDUPLIC.setPAR_CIDADE(caNFEDUPLICdto.getPAR_CIDADE());
        caNFEDUPLIC.setPAR_UF(caNFEDUPLICdto.getPAR_UF());
        caNFEDUPLIC.setPAR_BAIRRO(caNFEDUPLICdto.getPAR_BAIRRO());
        caNFEDUPLIC.setPAR_EMAIL(caNFEDUPLICdto.getPAR_EMAIL());
        caNFEDUPLIC.setPAR_CODIGOSAC(caNFEDUPLICdto.getPAR_CODIGOSAC());
        caNFEDUPLIC.setPAR_CODIGOSAR(caNFEDUPLICdto.getPAR_CODIGOSAR());
        caNFEDUPLIC.setPAR_NUMDOC(caNFEDUPLICdto.getPAR_NUMDOC());
        caNFEDUPLIC.setPAR_PARTICIPA(caNFEDUPLICdto.getPAR_PARTICIPA());
        if (caNFEDUPLICdto.getCANFEIRDUPLdto() != null) {
            caNFEDUPLIC.setCANFEIRDUPL(convertListCANFEIRDUPLdtotoListCANFEIRDUPL(caNFEDUPLICdto.getCANFEIRDUPLdto()));
        }
        caNFEDUPLIC.setPAR_BANCOCBR(caNFEDUPLICdto.getPAR_BANCOCBR());
        caNFEDUPLIC.setPAR_AGENCIACBR(caNFEDUPLICdto.getPAR_AGENCIACBR());
        caNFEDUPLIC.setPAR_ARQRETORNO(caNFEDUPLICdto.getPAR_ARQRETORNO());
        caNFEDUPLIC.setPAR_NUMREGRETORNO(caNFEDUPLICdto.getPAR_NUMREGRETORNO());
        caNFEDUPLIC.setPAR_DATARETORNO(caNFEDUPLICdto.getPAR_DATARETORNO());
        caNFEDUPLIC.setPAR_DTIM(caNFEDUPLICdto.getPAR_DTIM());
        caNFEDUPLIC.setPAR_FLIM(caNFEDUPLICdto.getPAR_FLIM());
        caNFEDUPLIC.setPAR_BOLETOENVIADO(caNFEDUPLICdto.isPAR_BOLETOENVIADO());
        caNFEDUPLIC.setPAR_LINHADIGITAVEL(caNFEDUPLICdto.getPAR_LINHADIGITAVEL());
        caNFEDUPLIC.setPAR_CODIGODEBARRAS(caNFEDUPLICdto.getPAR_CODIGODEBARRAS());
        return caNFEDUPLIC;
    }

    public static List<CANFEDUPLIC> convertListCANFEDUPLICdtotoListCANFEDUPLIC(List listCANFEDUPLICdto) throws ParseException {
        List<CANFEDUPLIC> ListCANFEDUPLIC = new ArrayList<>();
        for (int i = 0; i < listCANFEDUPLICdto.size(); i++) {
            CANFEDUPLICdto caNFEDUPLICdto = (CANFEDUPLICdto) listCANFEDUPLICdto.get(i);
            ListCANFEDUPLIC.add(convertCANFEDUPLICdtotoCANFEDUPLIC(caNFEDUPLICdto));
        }
        return ListCANFEDUPLIC;
    }

    public static List<CANFEDUPLICdto> convertListCANFEDUPLICtoListCANFEDUPLICdto(List listCANFEDUPLIC, Boolean caNFENOTFIS) throws ParseException, SQLException {
        List<CANFEDUPLICdto> ListCANFEDUPLICdto = new ArrayList<>();
        for (int i = 0; i < listCANFEDUPLIC.size(); i++) {
            CANFEDUPLIC caNFEDUPLIC = (CANFEDUPLIC) listCANFEDUPLIC.get(i);
            ListCANFEDUPLICdto.add(convertCANFEDUPLICtoCANFEDUPLICdto(caNFEDUPLIC, caNFENOTFIS));
        }
        return ListCANFEDUPLICdto;
    }

    public List<CANFEIRDUPLdto> getCANFEIRDUPLdto() {
        return CANFEIRDUPLdto;
    }

    public void setCANFEIRDUPLdto(List<CANFEIRDUPLdto> CANFEIRDUPLdto) {
        this.CANFEIRDUPLdto = CANFEIRDUPLdto;
    }

}
