package com.backend.api.cobranca.bradesco.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseJsonREGISTRODEBOLETOBRADESCOdto {
    
    @JsonProperty("idProduto")
    private Integer idProduto;

    @JsonProperty("negociacao")
    private Long negociacao;

    @JsonProperty("cpssoaJuridContr")
    private Long cpssoaJuridContr;

    @JsonProperty("ctpoContrNegoc")
    private Integer ctpoContrNegoc;

    @JsonProperty("nseqContrNegoc")
    private Integer nseqContrNegoc;

    @JsonProperty("cprodtServcOper")
    private Integer cprodtServcOper;

    @JsonProperty("nuTituloGerado")
    private Long nuTituloGerado;

    @JsonProperty("tp08Reg1")
    private Integer tp08Reg1;

    @JsonProperty("agencCred10")
    private Integer agencCred10;

    @JsonProperty("ctaCred10")
    private Integer ctaCred10;

    @JsonProperty("digCred10")
    private String digCred10;

    @JsonProperty("cip10")
    private Integer cip10;

    @JsonProperty("codStatus10")
    private Integer codStatus10;

    @JsonProperty("status10")
    private String status10;

    @JsonProperty("nomeBeneficiario")
    private String nomeBeneficiario;

    @JsonProperty("logradouroBeneficiario")
    private String logradouroBeneficiario;

    @JsonProperty("nuLogradouroBeneficiario")
    private String nuLogradouroBeneficiario;

    @JsonProperty("complementoLogradouroBeneficiario")
    private String complementoLogradouroBeneficiario;

    @JsonProperty("bairroBeneficiario")
    private String bairroBeneficiario;

    @JsonProperty("cepBeneficiario")
    private Integer cepBeneficiario;

    @JsonProperty("cepComplementoBeneficiario")
    private Integer cepComplementoBeneficiario;

    @JsonProperty("municipioBeneficiario")
    private String municipioBeneficiario;

    @JsonProperty("ufBeneficiario")
    private String ufBeneficiario;

    @JsonProperty("razCredt10")
    private Integer razCredt10;

    @JsonProperty("nomePagador")
    private String nomePagador;

    @JsonProperty("cpfcnpjPagador")
    private Long cpfcnpjPagador;

    @JsonProperty("enderecoPagador")
    private String enderecoPagador;

    @JsonProperty("bairroPagador")
    private String bairroPagador;

    @JsonProperty("municipioPagador")
    private String municipioPagador;

    @JsonProperty("ufPagador")
    private String ufPagador;

    @JsonProperty("cepPagador")
    private Integer cepPagador;

    @JsonProperty("cepComplementoPagador")
    private String cepComplementoPagador;

    @JsonProperty("cebp10")
    private String cebp10;

    @JsonProperty("debitoAuto10")
    private String debitoAuto10;

    @JsonProperty("aceite10")
    private String aceite10;

    @JsonProperty("endEletronicoPagador")
    private String endEletronicoPagador;

    @JsonProperty("nomeSacadorAvalista")
    private String nomeSacadorAvalista;

    @JsonProperty("cnpjCpfSacadorAvalista")
    private Long cnpjCpfSacadorAvalista;

    @JsonProperty("enderecoSacadorAvalista")
    private String enderecoSacadorAvalista;

    @JsonProperty("municipioSacadorAvalista")
    private String municipioSacadorAvalista;

    @JsonProperty("ufSacadorAvalista")
    private String ufSacadorAvalista;

    @JsonProperty("cepSacadorAvalista")
    private Integer cepSacadorAvalista;

    @JsonProperty("cepComplementoSacadorAvalista")
    private Integer cepComplementoSacadorAvalista;

    @JsonProperty("tp08Reg2")
    private Integer tp08Reg2;

    @JsonProperty("cense10")
    private Integer cense10;

    @JsonProperty("agenOper10")
    private Integer agenOper10;

    @JsonProperty("bcoDepos10")
    private Integer bcoDepos10;

    @JsonProperty("agenDepos10")
    private Integer agenDepos10;

    @JsonProperty("seuNumeroTitulo")
    private String seuNumeroTitulo;

    @JsonProperty("dtRegistro")
    private String dtRegistro;

    @JsonProperty("especieDocumentoTitulo")
    private String especieDocumentoTitulo;

    @JsonProperty("descEspecie")
    private String descEspecie;

    @JsonProperty("vlIOF")
    private Double vlIOF;

    @JsonProperty("dtEmissao")
    private String dtEmissao;

    @JsonProperty("codigoMoedaTitulo")
    private String codigoMoedaTitulo;

    @JsonProperty("quantidadeMoeda")
    private Integer quantidadeMoeda;

    @JsonProperty("quantidadeCasas")
    private Integer quantidadeCasas;

    @JsonProperty("dtVencimento")
    private String dtVencimento;

    @JsonProperty("descricacaoMoeda")
    private String descricacaoMoeda;

    @JsonProperty("vlTitulo")
    private Double vlTitulo;

    @JsonProperty("vlAbatimento")
    private Double vlAbatimento;

    @JsonProperty("dtInstrucaoProtestoNegativação")
    private String dtInstrucaoProtestoNegativacao;

    @JsonProperty("diasInstrucaoProtestoNegativação")
    private Integer diasInstrucaoProtestoNegativacao;

    @JsonProperty("dataEnvioCartorio")
    private String dataEnvioCartorio;

    @JsonProperty("numeroCartorio")
    private String numeroCartorio;

    @JsonProperty("numeroProtocoloCartorio")
    private String numeroProtocoloCartorio;

    @JsonProperty("dataPedidoSustacao")
    private String dataPedidoSustacao;

    @JsonProperty("dataSustacao")
    private String dataSustacao;

    @JsonProperty("dtMulta")
    private String dtMulta;

    @JsonProperty("vlMulta")
    private Double vlMulta;

    @JsonProperty("qtdeCasasDecimaisMulta")
    private Integer qtdeCasasDecimaisMulta;

    @JsonProperty("cdValorMulta")
    private Integer cdValorMulta;

    @JsonProperty("descCdMulta")
    private String descCdMulta;

    @JsonProperty("dtJuros")
    private String dtJuros;

    @JsonProperty("vlJurosAoDia")
    private Double vlJurosAoDia;

    @JsonProperty("dtDesconto1Bonificacao")
    private String dtDesconto1Bonificacao;

    @JsonProperty("vlDesconto1Bonificacao")
    private Double vlDesconto1Bonificacao;

    @JsonProperty("qtdeCasasDecimaisDesconto1Bonificacao")
    private Integer qtdeCasasDecimaisDesconto1Bonificacao;

    @JsonProperty("cdValorDesconto1Bonificacao")
    private Integer cdValorDesconto1Bonificacao;

    @JsonProperty("descCdDesconto1Bonificacao")
    private String descCdDesconto1Bonificacao;

    @JsonProperty("dtDesconto2")
    private String dtDesconto2;

    @JsonProperty("vlDesconto2")
    private Double vlDesconto2;

    @JsonProperty("qtdeCasasDecimaisDesconto2")
    private Integer qtdeCasasDecimaisDesconto2;

    @JsonProperty("cdValorDesconto2")
    private Integer cdValorDesconto2;

    @JsonProperty("descCdDesconto2")
    private String descCdDesconto2;

    @JsonProperty("dtDesconto3")
    private String dtDesconto3;

    @JsonProperty("vlDesconto3")
    private Double vlDesconto3;

    @JsonProperty("qtdeCasasDecimaisDesconto3")
    private Integer qtdeCasasDecimaisDesconto3;

    @JsonProperty("cdValorDesconto3")
    private Integer cdValorDesconto3;

    @JsonProperty("descCdDesconto3")
    private String descCdDesconto3;

    @JsonProperty("diasDispensaMulta")
    private Integer diasDispensaMulta;

    @JsonProperty("diasDispensaJuros")
    private Integer diasDispensaJuros;

    @JsonProperty("cdBarras")
    private String cdBarras;

    @JsonProperty("linhaDigitavel")
    private String linhaDigitavel;

    @JsonProperty("despCart10")
    private Integer despCart10;

    @JsonProperty("bcoCentr10")
    private Integer bcoCentr10;

    @JsonProperty("ageCentr10")
    private Integer ageCentr10;

    @JsonProperty("acessEsc10")
    private Integer acessEsc10;

    @JsonProperty("tipoEndosso")
    private String tipoEndosso;

    @JsonProperty("codigoOrigemProtesto")
    private Integer codigoOrigemProtesto;

    @JsonProperty("codigoOrigemTitulo")
    private String codigoOrigemTitulo;

    @JsonProperty("tpVencimento")
    private Integer tpVencimento;

    @JsonProperty("indInstrucaoProtesto")
    private Integer indInstrucaoProtesto;

    @JsonProperty("indicadorDecurso")
    private Integer indicadorDecurso;

    @JsonProperty("quantidadeDiasDecurso")
    private Integer quantidadeDiasDecurso;

    @JsonProperty("ctpoAbat10")
    private Integer ctpoAbat10;

    @JsonProperty("cdValorJuros")
    private Integer cdValorJuros;

    @JsonProperty("tpDesconto1")
    private Integer tpDesconto1;

    @JsonProperty("tpDesconto2")
    private Integer tpDesconto2;

    @JsonProperty("tpDesconto3")
    private Integer tpDesconto3;

    @JsonProperty("nuControleParticipante")
    private String nuControleParticipante;

    @JsonProperty("diasJuros")
    private Integer diasJuros;

    @JsonProperty("cdJuros")
    private Integer cdJuros;

    @JsonProperty("vlJuros")
    private Double vlJuros;

    @JsonProperty("cpfcnpjBeneficiário")
    private String cpfcnpjBeneficiario;

    @JsonProperty("vlTituloEmitidoBoleto")
    private Double vlTituloEmitidoBoleto;

    @JsonProperty("dtVencimentoBoleto")
    private String dtVencimentoBoleto;

    @JsonProperty("indTitParceld10")
    private String indTitParceld10;

    @JsonProperty("indParcelaPrin10")
    private String indParcelaPrin10;

    @JsonProperty("indBoletoDda10")
    private String indBoletoDda10;

    @JsonProperty("dtLimitePagamentoBoleto")
    private String dtLimitePagamentoBoleto;

    @JsonProperty("dataImpressao10")
    private String dataImpressao10;

    @JsonProperty("horaImpressao10")
    private Integer horaImpressao10;

    @JsonProperty("identTitDda10")
    private Integer identTitDda10;

    @JsonProperty("exibeLinDig10")
    private String exibeLinDig10;

    @JsonProperty("permPgtoParcial")
    private String permPgtoParcial;

    @JsonProperty("qtdePgtoParcial")
    private Integer qtdePgtoParcial;

    @JsonProperty("bancoDeb")
    private Integer bancoDeb;

    @JsonProperty("agenciaDeb")
    private Integer agenciaDeb;

    @JsonProperty("agenciaDebDv")
    private Integer agenciaDebDv;

    @JsonProperty("contaDeb")
    private Integer contaDeb;

    @JsonProperty("razaoContaDebito")
    private Integer razaoContaDebito;

    public Integer getIdProduto() {
        return idProduto;
    }

    public Long getNegociacao() {
        return negociacao;
    }

    public Long getCpssoaJuridContr() {
        return cpssoaJuridContr;
    }

    public Integer getCtpoContrNegoc() {
        return ctpoContrNegoc;
    }

    public Integer getNseqContrNegoc() {
        return nseqContrNegoc;
    }

    public Integer getCprodtServcOper() {
        return cprodtServcOper;
    }

    public Long getNuTituloGerado() {
        return nuTituloGerado;
    }

    public Integer getTp08Reg1() {
        return tp08Reg1;
    }

    public Integer getAgencCred10() {
        return agencCred10;
    }

    public Integer getCtaCred10() {
        return ctaCred10;
    }

    public String getDigCred10() {
        return digCred10;
    }

    public Integer getCip10() {
        return cip10;
    }

    public Integer getCodStatus10() {
        return codStatus10;
    }

    public String getStatus10() {
        return status10;
    }

    public String getNomeBeneficiario() {
        return nomeBeneficiario;
    }

    public String getLogradouroBeneficiario() {
        return logradouroBeneficiario;
    }

    public String getNuLogradouroBeneficiario() {
        return nuLogradouroBeneficiario;
    }

    public String getComplementoLogradouroBeneficiario() {
        return complementoLogradouroBeneficiario;
    }

    public String getBairroBeneficiario() {
        return bairroBeneficiario;
    }

    public Integer getCepBeneficiario() {
        return cepBeneficiario;
    }

    public Integer getCepComplementoBeneficiario() {
        return cepComplementoBeneficiario;
    }

    public String getMunicipioBeneficiario() {
        return municipioBeneficiario;
    }

    public String getUfBeneficiario() {
        return ufBeneficiario;
    }

    public Integer getRazCredt10() {
        return razCredt10;
    }

    public String getNomePagador() {
        return nomePagador;
    }

    public Long getCpfcnpjPagador() {
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

    public Integer getCepPagador() {
        return cepPagador;
    }

    public String getCepComplementoPagador() {
        return cepComplementoPagador;
    }

    public String getCebp10() {
        return cebp10;
    }

    public String getDebitoAuto10() {
        return debitoAuto10;
    }

    public String getAceite10() {
        return aceite10;
    }

    public String getEndEletronicoPagador() {
        return endEletronicoPagador;
    }

    public String getNomeSacadorAvalista() {
        return nomeSacadorAvalista;
    }

    public Long getCnpjCpfSacadorAvalista() {
        return cnpjCpfSacadorAvalista;
    }

    public String getEnderecoSacadorAvalista() {
        return enderecoSacadorAvalista;
    }

    public String getMunicipioSacadorAvalista() {
        return municipioSacadorAvalista;
    }

    public String getUfSacadorAvalista() {
        return ufSacadorAvalista;
    }

    public Integer getCepSacadorAvalista() {
        return cepSacadorAvalista;
    }

    public Integer getCepComplementoSacadorAvalista() {
        return cepComplementoSacadorAvalista;
    }

    public Integer getTp08Reg2() {
        return tp08Reg2;
    }

    public Integer getCense10() {
        return cense10;
    }

    public Integer getAgenOper10() {
        return agenOper10;
    }

    public Integer getBcoDepos10() {
        return bcoDepos10;
    }

    public Integer getAgenDepos10() {
        return agenDepos10;
    }

    public String getSeuNumeroTitulo() {
        return seuNumeroTitulo;
    }

    public String getDtRegistro() {
        return dtRegistro;
    }

    public String getEspecieDocumentoTitulo() {
        return especieDocumentoTitulo;
    }

    public String getDescEspecie() {
        return descEspecie;
    }

    public Double getVlIOF() {
        return vlIOF;
    }

    public String getDtEmissao() {
        return dtEmissao;
    }

    public String getCodigoMoedaTitulo() {
        return codigoMoedaTitulo;
    }

    public Integer getQuantidadeMoeda() {
        return quantidadeMoeda;
    }

    public Integer getQuantidadeCasas() {
        return quantidadeCasas;
    }

    public String getDtVencimento() {
        return dtVencimento;
    }

    public String getDescricacaoMoeda() {
        return descricacaoMoeda;
    }

    public Double getVlTitulo() {
        return vlTitulo;
    }

    public Double getVlAbatimento() {
        return vlAbatimento;
    }

    public String getDtInstrucaoProtestoNegativacao() {
        return dtInstrucaoProtestoNegativacao;
    }

    public Integer getDiasInstrucaoProtestoNegativacao() {
        return diasInstrucaoProtestoNegativacao;
    }

    public String getDataEnvioCartorio() {
        return dataEnvioCartorio;
    }

    public String getNumeroCartorio() {
        return numeroCartorio;
    }

    public String getNumeroProtocoloCartorio() {
        return numeroProtocoloCartorio;
    }

    public String getDataPedidoSustacao() {
        return dataPedidoSustacao;
    }

    public String getDataSustacao() {
        return dataSustacao;
    }

    public String getDtMulta() {
        return dtMulta;
    }

    public Double getVlMulta() {
        return vlMulta;
    }

    public Integer getQtdeCasasDecimaisMulta() {
        return qtdeCasasDecimaisMulta;
    }

    public Integer getCdValorMulta() {
        return cdValorMulta;
    }

    public String getDescCdMulta() {
        return descCdMulta;
    }

    public String getDtJuros() {
        return dtJuros;
    }

    public Double getVlJurosAoDia() {
        return vlJurosAoDia;
    }

    public String getDtDesconto1Bonificacao() {
        return dtDesconto1Bonificacao;
    }

    public Double getVlDesconto1Bonificacao() {
        return vlDesconto1Bonificacao;
    }

    public Integer getQtdeCasasDecimaisDesconto1Bonificacao() {
        return qtdeCasasDecimaisDesconto1Bonificacao;
    }

    public Integer getCdValorDesconto1Bonificacao() {
        return cdValorDesconto1Bonificacao;
    }

    public String getDescCdDesconto1Bonificacao() {
        return descCdDesconto1Bonificacao;
    }

    public String getDtDesconto2() {
        return dtDesconto2;
    }

    public Double getVlDesconto2() {
        return vlDesconto2;
    }

    public Integer getQtdeCasasDecimaisDesconto2() {
        return qtdeCasasDecimaisDesconto2;
    }

    public Integer getCdValorDesconto2() {
        return cdValorDesconto2;
    }

    public String getDescCdDesconto2() {
        return descCdDesconto2;
    }

    public String getDtDesconto3() {
        return dtDesconto3;
    }

    public Double getVlDesconto3() {
        return vlDesconto3;
    }

    public Integer getQtdeCasasDecimaisDesconto3() {
        return qtdeCasasDecimaisDesconto3;
    }

    public Integer getCdValorDesconto3() {
        return cdValorDesconto3;
    }

    public String getDescCdDesconto3() {
        return descCdDesconto3;
    }

    public Integer getDiasDispensaMulta() {
        return diasDispensaMulta;
    }

    public Integer getDiasDispensaJuros() {
        return diasDispensaJuros;
    }

    public String getCdBarras() {
        return cdBarras;
    }

    public String getLinhaDigitavel() {
        return linhaDigitavel;
    }

    public Integer getDespCart10() {
        return despCart10;
    }

    public Integer getBcoCentr10() {
        return bcoCentr10;
    }

    public Integer getAgeCentr10() {
        return ageCentr10;
    }

    public Integer getAcessEsc10() {
        return acessEsc10;
    }

    public String getTipoEndosso() {
        return tipoEndosso;
    }

    public Integer getCodigoOrigemProtesto() {
        return codigoOrigemProtesto;
    }

    public String getCodigoOrigemTitulo() {
        return codigoOrigemTitulo;
    }

    public Integer getTpVencimento() {
        return tpVencimento;
    }

    public Integer getIndInstrucaoProtesto() {
        return indInstrucaoProtesto;
    }

    public Integer getIndicadorDecurso() {
        return indicadorDecurso;
    }

    public Integer getQuantidadeDiasDecurso() {
        return quantidadeDiasDecurso;
    }

    public Integer getCtpoAbat10() {
        return ctpoAbat10;
    }

    public Integer getCdValorJuros() {
        return cdValorJuros;
    }

    public Integer getTpDesconto1() {
        return tpDesconto1;
    }

    public Integer getTpDesconto2() {
        return tpDesconto2;
    }

    public Integer getTpDesconto3() {
        return tpDesconto3;
    }

    public String getNuControleParticipante() {
        return nuControleParticipante;
    }

    public Integer getDiasJuros() {
        return diasJuros;
    }

    public Integer getCdJuros() {
        return cdJuros;
    }

    public Double getVlJuros() {
        return vlJuros;
    }

    public String getCpfcnpjBeneficiario() {
        return cpfcnpjBeneficiario;
    }

    public Double getVlTituloEmitidoBoleto() {
        return vlTituloEmitidoBoleto;
    }

    public String getDtVencimentoBoleto() {
        return dtVencimentoBoleto;
    }

    public String getIndTitParceld10() {
        return indTitParceld10;
    }

    public String getIndParcelaPrin10() {
        return indParcelaPrin10;
    }

    public String getIndBoletoDda10() {
        return indBoletoDda10;
    }

    public String getDtLimitePagamentoBoleto() {
        return dtLimitePagamentoBoleto;
    }

    public String getDataImpressao10() {
        return dataImpressao10;
    }

    public Integer getHoraImpressao10() {
        return horaImpressao10;
    }

    public Integer getIdentTitDda10() {
        return identTitDda10;
    }

    public String getExibeLinDig10() {
        return exibeLinDig10;
    }

    public String getPermPgtoParcial() {
        return permPgtoParcial;
    }

    public Integer getQtdePgtoParcial() {
        return qtdePgtoParcial;
    }

    public Integer getBancoDeb() {
        return bancoDeb;
    }

    public Integer getAgenciaDeb() {
        return agenciaDeb;
    }

    public Integer getAgenciaDebDv() {
        return agenciaDebDv;
    }

    public Integer getContaDeb() {
        return contaDeb;
    }

    public Integer getRazaoContaDebito() {
        return razaoContaDebito;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public void setNegociacao(Long negociacao) {
        this.negociacao = negociacao;
    }

    public void setCpssoaJuridContr(Long cpssoaJuridContr) {
        this.cpssoaJuridContr = cpssoaJuridContr;
    }

    public void setCtpoContrNegoc(Integer ctpoContrNegoc) {
        this.ctpoContrNegoc = ctpoContrNegoc;
    }

    public void setNseqContrNegoc(Integer nseqContrNegoc) {
        this.nseqContrNegoc = nseqContrNegoc;
    }

    public void setCprodtServcOper(Integer cprodtServcOper) {
        this.cprodtServcOper = cprodtServcOper;
    }

    public void setNuTituloGerado(Long nuTituloGerado) {
        this.nuTituloGerado = nuTituloGerado;
    }

    public void setTp08Reg1(Integer tp08Reg1) {
        this.tp08Reg1 = tp08Reg1;
    }

    public void setAgencCred10(Integer agencCred10) {
        this.agencCred10 = agencCred10;
    }

    public void setCtaCred10(Integer ctaCred10) {
        this.ctaCred10 = ctaCred10;
    }

    public void setDigCred10(String digCred10) {
        this.digCred10 = digCred10;
    }

    public void setCip10(Integer cip10) {
        this.cip10 = cip10;
    }

    public void setCodStatus10(Integer codStatus10) {
        this.codStatus10 = codStatus10;
    }

    public void setStatus10(String status10) {
        this.status10 = status10;
    }

    public void setNomeBeneficiario(String nomeBeneficiario) {
        this.nomeBeneficiario = nomeBeneficiario;
    }

    public void setLogradouroBeneficiario(String logradouroBeneficiario) {
        this.logradouroBeneficiario = logradouroBeneficiario;
    }

    public void setNuLogradouroBeneficiario(String nuLogradouroBeneficiario) {
        this.nuLogradouroBeneficiario = nuLogradouroBeneficiario;
    }

    public void setComplementoLogradouroBeneficiario(String complementoLogradouroBeneficiario) {
        this.complementoLogradouroBeneficiario = complementoLogradouroBeneficiario;
    }

    public void setBairroBeneficiario(String bairroBeneficiario) {
        this.bairroBeneficiario = bairroBeneficiario;
    }

    public void setCepBeneficiario(Integer cepBeneficiario) {
        this.cepBeneficiario = cepBeneficiario;
    }

    public void setCepComplementoBeneficiario(Integer cepComplementoBeneficiario) {
        this.cepComplementoBeneficiario = cepComplementoBeneficiario;
    }

    public void setMunicipioBeneficiario(String municipioBeneficiario) {
        this.municipioBeneficiario = municipioBeneficiario;
    }

    public void setUfBeneficiario(String ufBeneficiario) {
        this.ufBeneficiario = ufBeneficiario;
    }

    public void setRazCredt10(Integer razCredt10) {
        this.razCredt10 = razCredt10;
    }

    public void setNomePagador(String nomePagador) {
        this.nomePagador = nomePagador;
    }

    public void setCpfcnpjPagador(Long cpfcnpjPagador) {
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

    public void setCepPagador(Integer cepPagador) {
        this.cepPagador = cepPagador;
    }

    public void setCepComplementoPagador(String cepComplementoPagador) {
        this.cepComplementoPagador = cepComplementoPagador;
    }

    public void setCebp10(String cebp10) {
        this.cebp10 = cebp10;
    }

    public void setDebitoAuto10(String debitoAuto10) {
        this.debitoAuto10 = debitoAuto10;
    }

    public void setAceite10(String aceite10) {
        this.aceite10 = aceite10;
    }

    public void setEndEletronicoPagador(String endEletronicoPagador) {
        this.endEletronicoPagador = endEletronicoPagador;
    }

    public void setNomeSacadorAvalista(String nomeSacadorAvalista) {
        this.nomeSacadorAvalista = nomeSacadorAvalista;
    }

    public void setCnpjCpfSacadorAvalista(Long cnpjCpfSacadorAvalista) {
        this.cnpjCpfSacadorAvalista = cnpjCpfSacadorAvalista;
    }

    public void setEnderecoSacadorAvalista(String enderecoSacadorAvalista) {
        this.enderecoSacadorAvalista = enderecoSacadorAvalista;
    }

    public void setMunicipioSacadorAvalista(String municipioSacadorAvalista) {
        this.municipioSacadorAvalista = municipioSacadorAvalista;
    }

    public void setUfSacadorAvalista(String ufSacadorAvalista) {
        this.ufSacadorAvalista = ufSacadorAvalista;
    }

    public void setCepSacadorAvalista(Integer cepSacadorAvalista) {
        this.cepSacadorAvalista = cepSacadorAvalista;
    }

    public void setCepComplementoSacadorAvalista(Integer cepComplementoSacadorAvalista) {
        this.cepComplementoSacadorAvalista = cepComplementoSacadorAvalista;
    }

    public void setTp08Reg2(Integer tp08Reg2) {
        this.tp08Reg2 = tp08Reg2;
    }

    public void setCense10(Integer cense10) {
        this.cense10 = cense10;
    }

    public void setAgenOper10(Integer agenOper10) {
        this.agenOper10 = agenOper10;
    }

    public void setBcoDepos10(Integer bcoDepos10) {
        this.bcoDepos10 = bcoDepos10;
    }

    public void setAgenDepos10(Integer agenDepos10) {
        this.agenDepos10 = agenDepos10;
    }

    public void setSeuNumeroTitulo(String seuNumeroTitulo) {
        this.seuNumeroTitulo = seuNumeroTitulo;
    }

    public void setDtRegistro(String dtRegistro) {
        this.dtRegistro = dtRegistro;
    }

    public void setEspecieDocumentoTitulo(String especieDocumentoTitulo) {
        this.especieDocumentoTitulo = especieDocumentoTitulo;
    }

    public void setDescEspecie(String descEspecie) {
        this.descEspecie = descEspecie;
    }

    public void setVlIOF(Double vlIOF) {
        this.vlIOF = vlIOF;
    }

    public void setDtEmissao(String dtEmissao) {
        this.dtEmissao = dtEmissao;
    }

    public void setCodigoMoedaTitulo(String codigoMoedaTitulo) {
        this.codigoMoedaTitulo = codigoMoedaTitulo;
    }

    public void setQuantidadeMoeda(Integer quantidadeMoeda) {
        this.quantidadeMoeda = quantidadeMoeda;
    }

    public void setQuantidadeCasas(Integer quantidadeCasas) {
        this.quantidadeCasas = quantidadeCasas;
    }

    public void setDtVencimento(String dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public void setDescricacaoMoeda(String descricacaoMoeda) {
        this.descricacaoMoeda = descricacaoMoeda;
    }

    public void setVlTitulo(Double vlTitulo) {
        this.vlTitulo = vlTitulo;
    }

    public void setVlAbatimento(Double vlAbatimento) {
        this.vlAbatimento = vlAbatimento;
    }

    public void setDtInstrucaoProtestoNegativacao(String dtInstrucaoProtestoNegativacao) {
        this.dtInstrucaoProtestoNegativacao = dtInstrucaoProtestoNegativacao;
    }

    public void setDiasInstrucaoProtestoNegativacao(Integer diasInstrucaoProtestoNegativacao) {
        this.diasInstrucaoProtestoNegativacao = diasInstrucaoProtestoNegativacao;
    }

    public void setDataEnvioCartorio(String dataEnvioCartorio) {
        this.dataEnvioCartorio = dataEnvioCartorio;
    }

    public void setNumeroCartorio(String numeroCartorio) {
        this.numeroCartorio = numeroCartorio;
    }

    public void setNumeroProtocoloCartorio(String numeroProtocoloCartorio) {
        this.numeroProtocoloCartorio = numeroProtocoloCartorio;
    }

    public void setDataPedidoSustacao(String dataPedidoSustacao) {
        this.dataPedidoSustacao = dataPedidoSustacao;
    }

    public void setDataSustacao(String dataSustacao) {
        this.dataSustacao = dataSustacao;
    }

    public void setDtMulta(String dtMulta) {
        this.dtMulta = dtMulta;
    }

    public void setVlMulta(Double vlMulta) {
        this.vlMulta = vlMulta;
    }

    public void setQtdeCasasDecimaisMulta(Integer qtdeCasasDecimaisMulta) {
        this.qtdeCasasDecimaisMulta = qtdeCasasDecimaisMulta;
    }

    public void setCdValorMulta(Integer cdValorMulta) {
        this.cdValorMulta = cdValorMulta;
    }

    public void setDescCdMulta(String descCdMulta) {
        this.descCdMulta = descCdMulta;
    }

    public void setDtJuros(String dtJuros) {
        this.dtJuros = dtJuros;
    }

    public void setVlJurosAoDia(Double vlJurosAoDia) {
        this.vlJurosAoDia = vlJurosAoDia;
    }

    public void setDtDesconto1Bonificacao(String dtDesconto1Bonificacao) {
        this.dtDesconto1Bonificacao = dtDesconto1Bonificacao;
    }

    public void setVlDesconto1Bonificacao(Double vlDesconto1Bonificacao) {
        this.vlDesconto1Bonificacao = vlDesconto1Bonificacao;
    }

    public void setQtdeCasasDecimaisDesconto1Bonificacao(Integer qtdeCasasDecimaisDesconto1Bonificacao) {
        this.qtdeCasasDecimaisDesconto1Bonificacao = qtdeCasasDecimaisDesconto1Bonificacao;
    }

    public void setCdValorDesconto1Bonificacao(Integer cdValorDesconto1Bonificacao) {
        this.cdValorDesconto1Bonificacao = cdValorDesconto1Bonificacao;
    }

    public void setDescCdDesconto1Bonificacao(String descCdDesconto1Bonificacao) {
        this.descCdDesconto1Bonificacao = descCdDesconto1Bonificacao;
    }

    public void setDtDesconto2(String dtDesconto2) {
        this.dtDesconto2 = dtDesconto2;
    }

    public void setVlDesconto2(Double vlDesconto2) {
        this.vlDesconto2 = vlDesconto2;
    }

    public void setQtdeCasasDecimaisDesconto2(Integer qtdeCasasDecimaisDesconto2) {
        this.qtdeCasasDecimaisDesconto2 = qtdeCasasDecimaisDesconto2;
    }

    public void setCdValorDesconto2(Integer cdValorDesconto2) {
        this.cdValorDesconto2 = cdValorDesconto2;
    }

    public void setDescCdDesconto2(String descCdDesconto2) {
        this.descCdDesconto2 = descCdDesconto2;
    }

    public void setDtDesconto3(String dtDesconto3) {
        this.dtDesconto3 = dtDesconto3;
    }

    public void setVlDesconto3(Double vlDesconto3) {
        this.vlDesconto3 = vlDesconto3;
    }

    public void setQtdeCasasDecimaisDesconto3(Integer qtdeCasasDecimaisDesconto3) {
        this.qtdeCasasDecimaisDesconto3 = qtdeCasasDecimaisDesconto3;
    }

    public void setCdValorDesconto3(Integer cdValorDesconto3) {
        this.cdValorDesconto3 = cdValorDesconto3;
    }

    public void setDescCdDesconto3(String descCdDesconto3) {
        this.descCdDesconto3 = descCdDesconto3;
    }

    public void setDiasDispensaMulta(Integer diasDispensaMulta) {
        this.diasDispensaMulta = diasDispensaMulta;
    }

    public void setDiasDispensaJuros(Integer diasDispensaJuros) {
        this.diasDispensaJuros = diasDispensaJuros;
    }

    public void setCdBarras(String cdBarras) {
        this.cdBarras = cdBarras;
    }

    public void setLinhaDigitavel(String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel;
    }

    public void setDespCart10(Integer despCart10) {
        this.despCart10 = despCart10;
    }

    public void setBcoCentr10(Integer bcoCentr10) {
        this.bcoCentr10 = bcoCentr10;
    }

    public void setAgeCentr10(Integer ageCentr10) {
        this.ageCentr10 = ageCentr10;
    }

    public void setAcessEsc10(Integer acessEsc10) {
        this.acessEsc10 = acessEsc10;
    }

    public void setTipoEndosso(String tipoEndosso) {
        this.tipoEndosso = tipoEndosso;
    }

    public void setCodigoOrigemProtesto(Integer codigoOrigemProtesto) {
        this.codigoOrigemProtesto = codigoOrigemProtesto;
    }

    public void setCodigoOrigemTitulo(String codigoOrigemTitulo) {
        this.codigoOrigemTitulo = codigoOrigemTitulo;
    }

    public void setTpVencimento(Integer tpVencimento) {
        this.tpVencimento = tpVencimento;
    }

    public void setIndInstrucaoProtesto(Integer indInstrucaoProtesto) {
        this.indInstrucaoProtesto = indInstrucaoProtesto;
    }

    public void setIndicadorDecurso(Integer indicadorDecurso) {
        this.indicadorDecurso = indicadorDecurso;
    }

    public void setQuantidadeDiasDecurso(Integer quantidadeDiasDecurso) {
        this.quantidadeDiasDecurso = quantidadeDiasDecurso;
    }

    public void setCtpoAbat10(Integer ctpoAbat10) {
        this.ctpoAbat10 = ctpoAbat10;
    }

    public void setCdValorJuros(Integer cdValorJuros) {
        this.cdValorJuros = cdValorJuros;
    }

    public void setTpDesconto1(Integer tpDesconto1) {
        this.tpDesconto1 = tpDesconto1;
    }

    public void setTpDesconto2(Integer tpDesconto2) {
        this.tpDesconto2 = tpDesconto2;
    }

    public void setTpDesconto3(Integer tpDesconto3) {
        this.tpDesconto3 = tpDesconto3;
    }

    public void setNuControleParticipante(String nuControleParticipante) {
        this.nuControleParticipante = nuControleParticipante;
    }

    public void setDiasJuros(Integer diasJuros) {
        this.diasJuros = diasJuros;
    }

    public void setCdJuros(Integer cdJuros) {
        this.cdJuros = cdJuros;
    }

    public void setVlJuros(Double vlJuros) {
        this.vlJuros = vlJuros;
    }

    public void setCpfcnpjBeneficiario(String cpfcnpjBeneficiario) {
        this.cpfcnpjBeneficiario = cpfcnpjBeneficiario;
    }

    public void setVlTituloEmitidoBoleto(Double vlTituloEmitidoBoleto) {
        this.vlTituloEmitidoBoleto = vlTituloEmitidoBoleto;
    }

    public void setDtVencimentoBoleto(String dtVencimentoBoleto) {
        this.dtVencimentoBoleto = dtVencimentoBoleto;
    }

    public void setIndTitParceld10(String indTitParceld10) {
        this.indTitParceld10 = indTitParceld10;
    }

    public void setIndParcelaPrin10(String indParcelaPrin10) {
        this.indParcelaPrin10 = indParcelaPrin10;
    }

    public void setIndBoletoDda10(String indBoletoDda10) {
        this.indBoletoDda10 = indBoletoDda10;
    }

    public void setDtLimitePagamentoBoleto(String dtLimitePagamentoBoleto) {
        this.dtLimitePagamentoBoleto = dtLimitePagamentoBoleto;
    }

    public void setDataImpressao10(String dataImpressao10) {
        this.dataImpressao10 = dataImpressao10;
    }

    public void setHoraImpressao10(Integer horaImpressao10) {
        this.horaImpressao10 = horaImpressao10;
    }

    public void setIdentTitDda10(Integer identTitDda10) {
        this.identTitDda10 = identTitDda10;
    }

    public void setExibeLinDig10(String exibeLinDig10) {
        this.exibeLinDig10 = exibeLinDig10;
    }

    public void setPermPgtoParcial(String permPgtoParcial) {
        this.permPgtoParcial = permPgtoParcial;
    }

    public void setQtdePgtoParcial(Integer qtdePgtoParcial) {
        this.qtdePgtoParcial = qtdePgtoParcial;
    }

    public void setBancoDeb(Integer bancoDeb) {
        this.bancoDeb = bancoDeb;
    }

    public void setAgenciaDeb(Integer agenciaDeb) {
        this.agenciaDeb = agenciaDeb;
    }

    public void setAgenciaDebDv(Integer agenciaDebDv) {
        this.agenciaDebDv = agenciaDebDv;
    }

    public void setContaDeb(Integer contaDeb) {
        this.contaDeb = contaDeb;
    }

    public void setRazaoContaDebito(Integer razaoContaDebito) {
        this.razaoContaDebito = razaoContaDebito;
    }

    
}
