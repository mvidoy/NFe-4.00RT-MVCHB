package com.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BradescoBoletoResponseDTO {

    @JsonProperty("seuNumeroTitulo")
    public String seuNumeroTitulo;

    @JsonProperty("nuControleParticipante")
    public String nuControleParticipante;

    @JsonProperty("vlTituloEmitidoBoleto")
    public Double vlTituloEmitidoBoleto;

    @JsonProperty("vlTitulo")
    public Double vlTitulo;

    @JsonProperty("vlMulta")
    public Double vlMulta;

    @JsonProperty("vlJuros")
    public Double vlJuros;

    @JsonProperty("vlJurosAoDia")
    public Double vlJurosAoDia;

    @JsonProperty("vlDesconto1Bonificacao")
    public Double vlDesconto1Bonificacao;

    @JsonProperty("vlDesconto2")
    public Double vlDesconto2;

    @JsonProperty("vlDesconto3")
    public Double vlDesconto3;

    @JsonProperty("vlIOF")
    public Double vlIOF;

    @JsonProperty("vlAbatimento")
    public Double vlAbatimento;

    @JsonProperty("dtVencimento")
    public String dtVencimento;

    @JsonProperty("dtVencimentoBoleto")
    public String dtVencimentoBoleto;

    @JsonProperty("dtRegistro")
    public String dtRegistro;

    @JsonProperty("dtEmissao")
    public String dtEmissao;

    @JsonProperty("dtDesconto1Bonificacao")
    public String dtDesconto1Bonificacao;

    @JsonProperty("dtDesconto2")
    public String dtDesconto2;

    @JsonProperty("dtDesconto3")
    public String dtDesconto3;

    @JsonProperty("cdBarras")
    public String cdBarras;

    @JsonProperty("linhaDigitavel")
    public String linhaDigitavel;

    @JsonProperty("nomePagador")
    public String nomePagador;

    @JsonProperty("cpfcnpjPagador")
    public String cpfcnpjPagador;

    @JsonProperty("enderecoPagador")
    public String enderecoPagador;

    @JsonProperty("bairroPagador")
    public String bairroPagador;

    @JsonProperty("municipioPagador")
    public String municipioPagador;

    @JsonProperty("ufPagador")
    public String ufPagador;

    @JsonProperty("cepPagador")
    public String cepPagador;

    @JsonProperty("nomeBeneficiario")
    public String nomeBeneficiario;

    @JsonProperty("cpfcnpjBeneficiário")
    public String cpfcnpjBeneficiario;

    @JsonProperty("logradouroBeneficiario")
    public String logradouroBeneficiario;

    @JsonProperty("bairroBeneficiario")
    public String bairroBeneficiario;

    @JsonProperty("municipioBeneficiario")
    public String municipioBeneficiario;

    @JsonProperty("ufBeneficiario")
    public String ufBeneficiario;

    @JsonProperty("cepBeneficiario")
    public String cepBeneficiario;

    @JsonProperty("descEspecie")
    public String descEspecie;

    @JsonProperty("descCdMulta")
    public String descCdMulta;

    @JsonProperty("descCdDesconto1Bonificacao")
    public String descCdDesconto1Bonificacao;

    @JsonProperty("descCdDesconto2")
    public String descCdDesconto2;

    @JsonProperty("descCdDesconto3")
    public String descCdDesconto3;

    @JsonProperty("cepComplementoPagador")
    public String cepComplementoPagador;

    @JsonProperty("codigoMoedaTitulo")
    public String codigoMoedaTitulo;

    @JsonProperty("idProduto")
    public Integer idProduto;

    @JsonProperty("status10")
    public String status10;

    @JsonProperty("codigoOrigemTitulo")
    public String codigoOrigemTitulo;

    @JsonProperty("tipoEndosso")
    public String tipoEndosso;

    @JsonProperty("quantidadeMoeda")
    public Integer quantidadeMoeda;

    @JsonProperty("qtdePgtoParcial")
    public Integer qtdePgtoParcial;

    @JsonProperty("permPgtoParcial")
    public String permPgtoParcial;

    @JsonProperty("indInstrucaoProtesto")
    public Integer indInstrucaoProtesto;

    @JsonProperty("tpDesconto1")
    public Integer tpDesconto1;

    @JsonProperty("tpDesconto2")
    public Integer tpDesconto2;

    @JsonProperty("tpDesconto3")
    public Integer tpDesconto3;

    @JsonProperty("cdValorDesconto1Bonificacao")
    public Integer cdValorDesconto1Bonificacao;

    @JsonProperty("cdValorDesconto2")
    public Integer cdValorDesconto2;

    @JsonProperty("cdValorDesconto3")
    public Integer cdValorDesconto3;

    @JsonProperty("cdValorJuros")
    public Integer cdValorJuros;

    @JsonProperty("cdValorMulta")
    public Integer cdValorMulta;

    @JsonProperty("diasJuros")
    public Integer diasJuros;

    @JsonProperty("diasDispensaJuros")
    public Integer diasDispensaJuros;

    @JsonProperty("diasDispensaMulta")
    public Integer diasDispensaMulta;

    @JsonProperty("qtdeCasasDecimaisDesconto1Bonificacao")
    public Integer qtdeCasasDecimaisDesconto1Bonificacao;

    @JsonProperty("qtdeCasasDecimaisDesconto2")
    public Integer qtdeCasasDecimaisDesconto2;

    @JsonProperty("qtdeCasasDecimaisDesconto3")
    public Integer qtdeCasasDecimaisDesconto3;

    @JsonProperty("qtdeCasasDecimaisMulta")
    public Integer qtdeCasasDecimaisMulta;

    @JsonProperty("cepComplementoBeneficiario")
    public Integer cepComplementoBeneficiario;

    @JsonProperty("codigoOrigemProtesto")
    public Integer codigoOrigemProtesto;

    @JsonProperty("dataEnvioCartorio")
    public String dataEnvioCartorio;

    @JsonProperty("dataPedidoSustacao")
    public String dataPedidoSustacao;

    @JsonProperty("dataSustacao")
    public String dataSustacao;

    @JsonProperty("numeroProtocoloCartorio")
    public String numeroProtocoloCartorio;

    @JsonProperty("numeroCartorio")
    public String numeroCartorio;

    @JsonProperty("bancoDeb")
    public Integer bancoDeb;

    @JsonProperty("agenciaDeb")
    public Integer agenciaDeb;

    @JsonProperty("contaDeb")
    public Integer contaDeb;

    @JsonProperty("razaoContaDebito")
    public Integer razaoContaDebito;

    @JsonProperty("agenciaDebDv")
    public Integer agenciaDebDv;

    @JsonProperty("debitoAuto10")
    public String debitoAuto10;

    // Você pode expandir conforme a necessidade...

    // toString, equals, hashCode se necessário
}

