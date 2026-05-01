/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.dtos;

import java.math.BigDecimal;

/**
 *
 * @author Osvaldo Vidoy
 */
public class BOLETOBRADESCOREQUESTdto {

    private String seuNumeroTitulo;
    private BigDecimal vlTituloEmitidoBoleto;
    private String dtVencimento;
    private String dtEmissao;
    private String nomePagador;
    private String cpfcnpjPagador;
    private String enderecoPagador;
    private String bairroPagador;
    private String municipioPagador;
    private String ufPagador;
    private String codigoMoedaTitulo = "09";
    private String cdEspecieTitulo = "25";
    private BigDecimal vlJuros = BigDecimal.ZERO;
    private BigDecimal vlMulta = BigDecimal.ZERO;
    private BigDecimal vlDesconto1Bonificacao = BigDecimal.ZERO;
    private Integer cdValorJuros = 0;
    private Integer cdValorMulta = 0;
    private Integer cdValorDesconto1Bonificacao = 0;
    private Integer qtdeCasasDecimaisDesconto1Bonificacao = 0;
    private String dtDesconto1Bonificacao;
    private String descCdDesconto1Bonificacao = "";
    private String nuControleParticipante;
    private Integer cepPagador;

    public String getSeuNumeroTitulo() {
        return seuNumeroTitulo;
    }

    public BigDecimal getVlTituloEmitidoBoleto() {
        return vlTituloEmitidoBoleto;
    }

    public String getDtVencimento() {
        return dtVencimento;
    }

    public String getDtEmissao() {
        return dtEmissao;
    }

    public String getNomePagador() {
        return nomePagador;
    }

    public String getCpfcnpjPagador() {
        return cpfcnpjPagador;
    }

    public String getEnderecoPagador() {
        return enderecoPagador;
    }

    public String getBairroPagador() {
        return bairroPagador;
    }

    public String getMunicipioPagador() {
        return municipioPagador;
    }

    public String getUfPagador() {
        return ufPagador;
    }

    public String getCodigoMoedaTitulo() {
        return codigoMoedaTitulo;
    }

    public String getCdEspecieTitulo() {
        return cdEspecieTitulo;
    }

    public BigDecimal getVlJuros() {
        return vlJuros;
    }

    public BigDecimal getVlMulta() {
        return vlMulta;
    }

    public BigDecimal getVlDesconto1Bonificacao() {
        return vlDesconto1Bonificacao;
    }

    public Integer getCdValorJuros() {
        return cdValorJuros;
    }

    public Integer getCdValorMulta() {
        return cdValorMulta;
    }

    public Integer getCdValorDesconto1Bonificacao() {
        return cdValorDesconto1Bonificacao;
    }

    public Integer getQtdeCasasDecimaisDesconto1Bonificacao() {
        return qtdeCasasDecimaisDesconto1Bonificacao;
    }

    public String getDtDesconto1Bonificacao() {
        return dtDesconto1Bonificacao;
    }

    public String getDescCdDesconto1Bonificacao() {
        return descCdDesconto1Bonificacao;
    }

    public String getNuControleParticipante() {
        return nuControleParticipante;
    }

    public Integer getCepPagador() {
        return cepPagador;
    }

    public void setSeuNumeroTitulo(String seuNumeroTitulo) {
        this.seuNumeroTitulo = seuNumeroTitulo;
    }

    public void setVlTituloEmitidoBoleto(BigDecimal vlTituloEmitidoBoleto) {
        this.vlTituloEmitidoBoleto = vlTituloEmitidoBoleto;
    }

    public void setDtVencimento(String dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public void setDtEmissao(String dtEmissao) {
        this.dtEmissao = dtEmissao;
    }

    public void setNomePagador(String nomePagador) {
        this.nomePagador = nomePagador;
    }

    public void setCpfcnpjPagador(String cpfcnpjPagador) {
        this.cpfcnpjPagador = cpfcnpjPagador;
    }

    public void setEnderecoPagador(String enderecoPagador) {
        this.enderecoPagador = enderecoPagador;
    }

    public void setBairroPagador(String bairroPagador) {
        this.bairroPagador = bairroPagador;
    }

    public void setMunicipioPagador(String municipioPagador) {
        this.municipioPagador = municipioPagador;
    }

    public void setUfPagador(String ufPagador) {
        this.ufPagador = ufPagador;
    }

    public void setCodigoMoedaTitulo(String codigoMoedaTitulo) {
        this.codigoMoedaTitulo = codigoMoedaTitulo;
    }

    public void setCdEspecieTitulo(String cdEspecieTitulo) {
        this.cdEspecieTitulo = cdEspecieTitulo;
    }

    public void setVlJuros(BigDecimal vlJuros) {
        this.vlJuros = vlJuros;
    }

    public void setVlMulta(BigDecimal vlMulta) {
        this.vlMulta = vlMulta;
    }

    public void setVlDesconto1Bonificacao(BigDecimal vlDesconto1Bonificacao) {
        this.vlDesconto1Bonificacao = vlDesconto1Bonificacao;
    }

    public void setCdValorJuros(Integer cdValorJuros) {
        this.cdValorJuros = cdValorJuros;
    }

    public void setCdValorMulta(Integer cdValorMulta) {
        this.cdValorMulta = cdValorMulta;
    }

    public void setCdValorDesconto1Bonificacao(Integer cdValorDesconto1Bonificacao) {
        this.cdValorDesconto1Bonificacao = cdValorDesconto1Bonificacao;
    }

    public void setQtdeCasasDecimaisDesconto1Bonificacao(Integer qtdeCasasDecimaisDesconto1Bonificacao) {
        this.qtdeCasasDecimaisDesconto1Bonificacao = qtdeCasasDecimaisDesconto1Bonificacao;
    }

    public void setDtDesconto1Bonificacao(String dtDesconto1Bonificacao) {
        this.dtDesconto1Bonificacao = dtDesconto1Bonificacao;
    }

    public void setDescCdDesconto1Bonificacao(String descCdDesconto1Bonificacao) {
        this.descCdDesconto1Bonificacao = descCdDesconto1Bonificacao;
    }

    public void setNuControleParticipante(String nuControleParticipante) {
        this.nuControleParticipante = nuControleParticipante;
    }

    public void setCepPagador(Integer cepPagador) {
        this.cepPagador = cepPagador;
    }

}
