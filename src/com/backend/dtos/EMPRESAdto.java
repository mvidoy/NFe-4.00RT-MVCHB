package com.backend.dtos;

import com.backend.models.EMPRESA;

public class EMPRESAdto {

    private Long ID;

    public EMPRESAdto() {
    }

    public EMPRESAdto eEMPRESA;

    public EMPRESAdto(EMPRESAdto eEMPRESA) {
        this.eEMPRESA = eEMPRESA;
    }

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
    private byte[] EMP_CERTIFICADO;
    private String EMP_LOCALCERTIFICADO;
    private String EMP_LOCALCERTIFICADOCACERTSMDFE;
    private String EMP_SENHACERTIFICADO;
    private String EMP_CODIGOMUNICIPIO;
    private String EMP_ULNO;

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

    public byte[] getEMP_CERTIFICADO() {
        return EMP_CERTIFICADO;
    }

    public void setEMP_CERTIFICADO(byte[] EMP_CERTIFICADO) {
        this.EMP_CERTIFICADO = EMP_CERTIFICADO;
    }

    public String getEMP_LOCALCERTIFICADO() {
        return EMP_LOCALCERTIFICADO;
    }

    public void setEMP_LOCALCERTIFICADO(String EMP_LOCALCERTIFICADO) {
        this.EMP_LOCALCERTIFICADO = EMP_LOCALCERTIFICADO;
    }

    public String getEMP_LOCALCERTIFICADOCACERTSMDFE() {
        return EMP_LOCALCERTIFICADOCACERTSMDFE;
    }

    public void setEMP_LOCALCERTIFICADOCACERTSMDFE(String EMP_LOCALCERTIFICADOCACERTSMDFE) {
        this.EMP_LOCALCERTIFICADOCACERTSMDFE = EMP_LOCALCERTIFICADOCACERTSMDFE;
    }

    public String getEMP_SENHACERTIFICADO() {
        return EMP_SENHACERTIFICADO;
    }

    public void setEMP_SENHACERTIFICADO(String EMP_SENHACERTIFICADO) {
        this.EMP_SENHACERTIFICADO = EMP_SENHACERTIFICADO;
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

    public static EMPRESAdto convertEMPRESAtoEMPRESAdto(EMPRESA eMPRESA) {
        EMPRESAdto eMPRESAdto = new EMPRESAdto();
        eMPRESAdto.setEMP_CODI(eMPRESA.getEMP_CODI());
        eMPRESAdto.setEMP_RZSO(eMPRESA.getEMP_RZSO());
        eMPRESAdto.setEMP_CGC0(eMPRESA.getEMP_CGC0());
        eMPRESAdto.setEMP_INSE(eMPRESA.getEMP_INSE());
        eMPRESAdto.setEMP_ENDE(eMPRESA.getEMP_ENDE());
        eMPRESAdto.setEMP_NUMERO(eMPRESA.getEMP_NUMERO());
        eMPRESAdto.setEMP_COMPLEMENTO(eMPRESA.getEMP_COMPLEMENTO());
        eMPRESAdto.setEMP_BAIR(eMPRESA.getEMP_BAIR());
        eMPRESAdto.setEMP_CEP0(eMPRESA.getEMP_CEP0());
        eMPRESAdto.setEMP_PAIS(eMPRESA.getEMP_PAIS());
        eMPRESAdto.setEMP_ESTA(eMPRESA.getEMP_ESTA());
        eMPRESAdto.setEMP_CIDA(eMPRESA.getEMP_CIDA());
        eMPRESAdto.setEMP_FONE(eMPRESA.getEMP_FONE());
        eMPRESAdto.setEMP_INSCRISAOMUNICIPAL(eMPRESA.getEMP_INSCRISAOMUNICIPAL());
        eMPRESAdto.setEMP_CNAEFISCAL(eMPRESA.getEMP_CNAEFISCAL());
        eMPRESAdto.setEMP_CODIGOMUNICIPIO(eMPRESA.getEMP_CODIGOMUNICIPIO());
        eMPRESAdto.setEMP_ULNO(eMPRESA.getEMP_ULNO());
        return eMPRESAdto;
    }

    public static EMPRESA convertEMPRESAdtotoEMPRESA(EMPRESAdto eMPRESAdto) {
        EMPRESA eMPRESA = new EMPRESA();
        eMPRESA.setEMP_CODI(eMPRESAdto.getEMP_CODI());
        eMPRESA.setEMP_RZSO(eMPRESAdto.getEMP_RZSO());
        eMPRESA.setEMP_CGC0(eMPRESAdto.getEMP_CGC0());
        eMPRESA.setEMP_INSE(eMPRESAdto.getEMP_INSE());
        eMPRESA.setEMP_ENDE(eMPRESAdto.getEMP_ENDE());
        eMPRESA.setEMP_NUMERO(eMPRESAdto.getEMP_NUMERO());
        eMPRESA.setEMP_COMPLEMENTO(eMPRESAdto.getEMP_COMPLEMENTO());
        eMPRESA.setEMP_BAIR(eMPRESAdto.getEMP_BAIR());
        eMPRESA.setEMP_CEP0(eMPRESAdto.getEMP_CEP0());
        eMPRESA.setEMP_PAIS(eMPRESAdto.getEMP_PAIS());
        eMPRESA.setEMP_ESTA(eMPRESAdto.getEMP_ESTA());
        eMPRESA.setEMP_CIDA(eMPRESAdto.getEMP_CIDA());
        eMPRESA.setEMP_FONE(eMPRESAdto.getEMP_FONE());
        eMPRESA.setEMP_INSCRISAOMUNICIPAL(eMPRESAdto.getEMP_INSCRISAOMUNICIPAL());
        eMPRESA.setEMP_CNAEFISCAL(eMPRESAdto.getEMP_CNAEFISCAL());
        eMPRESA.setEMP_CODIGOMUNICIPIO(eMPRESAdto.getEMP_CODIGOMUNICIPIO());
        eMPRESA.setEMP_ULNO(eMPRESAdto.getEMP_ULNO());
        return eMPRESA;
    }

}
