package com.backend.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "canfeduplic")
@DynamicUpdate(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CANFEDUPLIC.NamedQueryFindAllNota",
            query = "SELECT c FROM CANFEDUPLIC c "
            + "WHERE "
            + "c.PAR_CODI IS NOT NULL AND c.PAR_PARC IS NOT NULL AND "
            + "c.PAR_CODI = :PAR_CODI "
            + "ORDER BY c.PAR_CODI ASC, c.PAR_PARC ASC")

    ,
    @NamedQuery(name = "CANFEDUPLIC.NamedQueryFindByNotaParc",
            query = "SELECT c FROM CANFEDUPLIC c "
            + "LEFT JOIN FETCH c.CANFEIRDUPL d "
            + "WHERE "
            + "c.PAR_CODI IS NOT NULL AND c.PAR_PARC IS NOT NULL AND "
            + "c.PAR_CODI = :PAR_CODI AND "
            + "c.PAR_PARC = :PAR_PARC "
            + "ORDER BY c.PAR_CODI ASC, c.PAR_PARC ASC")

    ,
    @NamedQuery(name = "CANFEDUPLIC.NamedQueryFindAllLike",
            query = "SELECT DUP, CLI "
            + "FROM CANFEDUPLIC DUP, CLIENTE CLI "
            + "WHERE "
            + "DUP.PAR_CLIE = CLI.CLI_CODI AND "
            + "(CLI.CLI_NOME LIKE CONCAT('%',:CLI_NOME,'%') OR "
            + ":CLI_NOME IS NULL OR TRIM(:CLI_NOME) = '') AND "
            + "(UPPER(TRIM(DUP.PAR_STATUS)) = :PAR_STATUS OR (:PAR_STATUS IS NULL OR TRIM(:PAR_STATUS) = '' OR TRIM(:PAR_STATUS) = 'TODOS')) AND "
            + "((DUP.PAR_CODI >= :PAR_CODIINICIAL AND DUP.PAR_CODI <= :PAR_CODIFINAL) OR "
            + ":PAR_CODIINICIAL IS NULL OR TRIM(:PAR_CODIINICIAL) = '' OR "
            + ":PAR_CODIFINAL IS NULL OR TRIM(:PAR_CODIFINAL) = '') AND "
            + "(DUP.PAR_BANCO = :PAR_BANCO OR (:PAR_BANCO IS NULL OR TRIM(:PAR_BANCO) = '' OR TRIM(:PAR_BANCO) = 'N/A')) AND "
            + "(DUP.PAR_AGENCIA = :PAR_AGENCIA OR (:PAR_AGENCIA IS NULL OR TRIM(:PAR_AGENCIA) = '' OR TRIM(:PAR_AGENCIA) = 'N/A')) AND "
            + "(DUP.PAR_CONTA = :PAR_CONTA OR (:PAR_CONTA IS NULL OR TRIM(:PAR_CONTA) = '' OR TRIM(:PAR_CONTA) = 'N/A')) AND "
            + "DUP.PAR_DTEM IS NOT NULL "
            + "ORDER BY PAR_CODI DESC, PAR_PARC ASC")
    ,
    @NamedQuery(name = "CANFEDUPLIC.NamedQueryCountFindAll",
            query = "SELECT COUNT(c) AS regCount "
            + "FROM CANFEDUPLIC c "
            + "WHERE c.PAR_CODI <> '' "
            + "AND to_char(c.PAR_DTEM,'YYYY/MM/DD') <> '' "
            + "GROUP BY c.PAR_CODI "
            + "ORDER BY c.PAR_CODI DESC")
    ,
    @NamedQuery(
            name = "CANFEDUPLIC.NamedQueryFindByArquivoRemessa",
            query = "SELECT c FROM CANFEDUPLIC c "
            + "WHERE c.PAR_CODI IS NOT NULL "
            + "AND c.PAR_PARC IS NOT NULL "
            + "AND LOWER(TRIM(c.PAR_ARQREMESSA)) = :PAR_ARQREMESSA "
            + "ORDER BY c.PAR_CODI ASC, c.PAR_PARC ASC"
    )

})
@SuppressWarnings("ConsistentAccessType")
public class CANFEDUPLIC implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long CANFENOTFIS_Id;
    private String PAR_CODI;
    private String PAR_CLIE;
    private Date PAR_DTEM;
    private Date PAR_PAGT;
    private String PAR_PARC;
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
    private String PAR_DTIM;
    private String PAR_FLIM;
    private Boolean PAR_BOLETOENVIADO;
    private String PAR_LINHADIGITAVEL;
    private String PAR_CODIGODEBARRAS;
    

    @OneToMany(targetEntity = CANFEIRDUPL.class,
            mappedBy = "CANFEDUPLIC_Id",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE,
                CascadeType.PERSIST,
                CascadeType.REMOVE,
                CascadeType.ALL},
            orphanRemoval = true) // Adicione esta linha
    @OrderBy("ire_parc ASC")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<CANFEIRDUPL> CANFEIRDUPL;

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

    public Date getPAR_PAGT() {
        return PAR_PAGT;
    }

    public void setPAR_PAGT(Date PAR_PAGT) {
        this.PAR_PAGT = PAR_PAGT;
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

    public Date getPAR_DTIM() throws ParseException {
        if (PAR_DTIM != null && !PAR_DTIM.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(PAR_DTIM);
        }
        return null;
    }

    public void setPAR_DTIM(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.PAR_DTIM = sdf.format(date);
        } else {
            this.PAR_DTIM = null;
        }
    }

    public String getPAR_DTIMAsString() {
        return this.PAR_DTIM;
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

    public List<CANFEIRDUPL> getCANFEIRDUPL() {
        return CANFEIRDUPL;
    }

    public void setCANFEIRDUPL(List<CANFEIRDUPL> CANFEIRDUPL) {
        this.CANFEIRDUPL = CANFEIRDUPL;
    }

}
