package com.backend.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "caclient")
@DynamicUpdate(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CLIENTE.findLastCliCodi", 
            query = "SELECT c FROM CLIENTE c WHERE "
            + "c.CLI_CODI IS NOT NULL ORDER BY CLI_CODI DESC")
    ,
    @NamedQuery(name = "CLIENTE.findAll", 
            query = "SELECT c FROM CLIENTE c WHERE "
            + "c.CLI_NOME IS NOT NULL ")
    ,
    @NamedQuery(name = "CLIENTE.NamedQueryFindAll", 
            query = "SELECT c FROM CLIENTE c WHERE "
            + "c.CLI_NOME IS NOT NULL ORDER BY c.CLI_NOME ASC, c.CLI_CODI ASC")
    ,
    @NamedQuery(name = "CLIENTE.findByCliCodi", 
            query = "SELECT c FROM CLIENTE c WHERE c.CLI_CODI = :CLI_CODI")
    ,
    @NamedQuery(name = "CLIENTE.findByCliCgc0", 
            query = "SELECT c FROM CLIENTE c WHERE c.CLI_CGC0 = :CLI_CGC0")})

@SuppressWarnings("ConsistentAccessType")
public class CLIENTE implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String CLI_CODI;
    private String CLI_NOME;
    private String CLI_APEL;
    private String CLI_CGC0;
    private String CLI_INSE;
    private String CLI_TIPOSGQ;
    private String CLI_JUFI;
    private String CLI_SEGMENTO;
    private Boolean CLI_ISENTOICMS = true;
    private String CLI_TIPOSIMPLES;
    private Date CLI_DATACADASTRO;
    private String CLI_VEND;
    private String CLI_ENDE;
    private String CLI_BAIR;
    private String CLI_ESTA;
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
    private String CLI_TR02;

    private String CLI_NFE_EMAIL;

    
    @OneToOne
    @JoinColumn(name = "cli_esta",
            insertable = false,
            updatable = false,
            referencedColumnName = "EST_SIGL",
            nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @Transient
    private ESTADO ESTADO;
    
    @OneToOne
    @JoinColumn(name = "cli_vend",
            insertable = false,
            updatable = false,
            referencedColumnName = "VEN_CODI",
            nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @Transient
    private VENDEDOR VENDEDOR;
    
    /*
    @OneToOne
    @JoinColumn(name = "cli_vend",
            insertable = false,
            updatable = false,
            referencedColumnName = "VEN_CODI",
            nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private VENDEDOR VENDEDOR;
*/

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

    public Boolean getCLI_ISENTOICMS() {
        return CLI_ISENTOICMS;
    }

    public void setCLI_ISENTOICMS(Boolean CLI_ISENTOICMS) {
        this.CLI_ISENTOICMS = CLI_ISENTOICMS;
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

    public String getCLI_FAX0() {
        return CLI_FAX0;
    }

    public void setCLI_FAX0(String CLI_FAX0) {
        this.CLI_FAX0 = CLI_FAX0;
    }

    public void setCLI_FONE(String CLI_FONE) {
        this.CLI_FONE = CLI_FONE;
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

    public String getCLI_TR02() {
        return CLI_TR02;
    }

    public void setCLI_TR02(String CLI_TR02) {
        this.CLI_TR02 = CLI_TR02;
    }

    public String getCLI_NFE_EMAIL() {
        return CLI_NFE_EMAIL;
    }

    public void setCLI_NFE_EMAIL(String CLI_NFE_EMAIL) {
        this.CLI_NFE_EMAIL = CLI_NFE_EMAIL;
    }

    public ESTADO getESTADO() {
        return ESTADO;
    }

    public void setESTADO(ESTADO ESTADO) {
        this.ESTADO = ESTADO;
    }

    //public VENDEDOR getVENDEDOR() {
   //     return VENDEDOR;
    //}

    //public void setVENDEDOR(VENDEDOR VENDEDOR) {
     //   this.VENDEDOR = VENDEDOR;
    //}

    public VENDEDOR getVENDEDOR() {
        return VENDEDOR;
    }

    public void setVENDEDOR(VENDEDOR VENDEDOR) {
        this.VENDEDOR = VENDEDOR;
    }

}
