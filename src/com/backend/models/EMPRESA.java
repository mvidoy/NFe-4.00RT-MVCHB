package com.backend.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "caempres")
@DynamicUpdate(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EMPRESA.findLastEmpCodi", query = "SELECT c FROM EMPRESA c WHERE c.EMP_CODI IS NOT NULL ORDER BY EMP_CODI DESC")
    ,
    @NamedQuery(name = "EMPRESA.findAll", query = "SELECT c FROM EMPRESA c WHERE c.EMP_RZSO IS NOT NULL")
    ,
    @NamedQuery(name = "EMPRESA.findByEmpCodi", query = "SELECT c FROM EMPRESA c WHERE c.EMP_CODI = :EMP_CODI")
    ,
    @NamedQuery(name = "EMPRESA.findByEmpCgc0", query = "SELECT c FROM EMPRESA c WHERE c.EMP_CGC0 = :EMP_CGC0")})

@SuppressWarnings("ConsistentAccessType")
public class EMPRESA implements Serializable {

    private Long ID;

    public EMPRESA() {
    }

    public EMPRESA eEMITENTE;

    public EMPRESA(EMPRESA eEMITENTE) {
        this.eEMITENTE = eEMITENTE;
    }

    //private static final long serialVersionUID = 1L;
    private String EMP_CODI;
    private String EMP_RZSO;
    private String EMP_CGC0;
    private String EMP_INSE;
    private String EMP_ENDE;
    private String EMP_NUMERO;
    private String EMP_COMPLEMENTO;
    private String EMP_BAIR;
    private String EMP_CEP0;
    private String EMP_PAIS;
    private String EMP_ESTA;
    private String EMP_CIDA;
    private String EMP_FONE;
    private String EMP_INSCRISAOMUNICIPAL;
    private String EMP_CNAEFISCAL;
    private String EMP_CODIGOMUNICIPIO;
    private String EMP_ULNO;

    @Id
    @GeneratedValue
    public String getEMP_CODI() {
        return EMP_CODI;
    }

    public void setEMP_CODI(String EMP_CODI) {
        this.EMP_CODI = EMP_CODI;
    }

    public String getEMP_RZSO() {
        return EMP_RZSO;
    }

    public void setEMP_RZSO(String EMP_RZSO) {
        this.EMP_RZSO = EMP_RZSO;
    }

    public String getEMP_CGC0() {
        return EMP_CGC0;
    }

    public void setEMP_CGC0(String EMP_CGC0) {
        this.EMP_CGC0 = EMP_CGC0;
    }

    public String getEMP_INSE() {
        return EMP_INSE;
    }

    public void setEMP_INSE(String EMP_INSE) {
        this.EMP_INSE = EMP_INSE;
    }

    public String getEMP_ENDE() {
        return EMP_ENDE;
    }

    public void setEMP_ENDE(String EMP_ENDE) {
        this.EMP_ENDE = EMP_ENDE;
    }

    public String getEMP_NUMERO() {
        return EMP_NUMERO;
    }

    public void setEMP_NUMERO(String EMP_NUMERO) {
        this.EMP_NUMERO = EMP_NUMERO;
    }

    public String getEMP_COMPLEMENTO() {
        return EMP_COMPLEMENTO;
    }

    public void setEMP_COMPLEMENTO(String EMP_COMPLEMENTO) {
        this.EMP_COMPLEMENTO = EMP_COMPLEMENTO;
    }

    public String getEMP_BAIR() {
        return EMP_BAIR;
    }

    public void setEMP_BAIR(String EMP_BAIR) {
        this.EMP_BAIR = EMP_BAIR;
    }

    public String getEMP_CEP0() {
        return EMP_CEP0;
    }

    public void setEMP_CEP0(String EMP_CEP0) {
        this.EMP_CEP0 = EMP_CEP0;
    }

    public String getEMP_PAIS() {
        return EMP_PAIS;
    }

    public void setEMP_PAIS(String EMP_PAIS) {
        this.EMP_PAIS = EMP_PAIS;
    }

    public String getEMP_ESTA() {
        return EMP_ESTA;
    }

    public void setEMP_ESTA(String EMP_ESTA) {
        this.EMP_ESTA = EMP_ESTA;
    }

    public String getEMP_CIDA() {
        return EMP_CIDA;
    }

    public void setEMP_CIDA(String EMP_CIDA) {
        this.EMP_CIDA = EMP_CIDA;
    }

    public String getEMP_FONE() {
        return EMP_FONE;
    }

    public void setEMP_FONE(String EMP_FONE) {
        this.EMP_FONE = EMP_FONE;
    }

    public String getEMP_INSCRISAOMUNICIPAL() {
        return EMP_INSCRISAOMUNICIPAL;
    }

    public void setEMP_INSCRISAOMUNICIPAL(String EMP_INSCRISAOMUNICIPAL) {
        this.EMP_INSCRISAOMUNICIPAL = EMP_INSCRISAOMUNICIPAL;
    }

    public String getEMP_CNAEFISCAL() {
        return EMP_CNAEFISCAL;
    }

    public void setEMP_CNAEFISCAL(String EMP_CNAEFISCAL) {
        this.EMP_CNAEFISCAL = EMP_CNAEFISCAL;
    }

    public String getEMP_CODIGOMUNICIPIO() {
        return EMP_CODIGOMUNICIPIO;
    }

    public void setEMP_CODIGOMUNICIPIO(String EMP_CODIGOMUNICIPIO) {
        this.EMP_CODIGOMUNICIPIO = EMP_CODIGOMUNICIPIO;
    }

    public String getEMP_ULNO() {
        return EMP_ULNO;
    }

    public void setEMP_ULNO(String EMP_ULNO) {
        this.EMP_ULNO = EMP_ULNO;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (EMP_CODI != null ? EMP_CODI.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caempres)) {
            return false;
        }
        Caempres other = (Caempres) object;
        if ((this.EMP_CODI == null && other.EMP_CODI != null) || (this.EMP_CODI != null && !this.EMP_CODI.equals(other.EMP_CODI))) {
            return false;
        }
        return true;
    }

}
