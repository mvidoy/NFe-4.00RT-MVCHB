package com.backend.api.cobranca.bradesco.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseJsonCONSULTADEBOLETOBRADESCOdto {

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("transacao")
    private String transacao;

    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("causa")
    private String causa;

    @JsonProperty("titulo")
    private Titulo titulo;

    @JsonProperty("quantidadeMensagens")
    private Integer quantidadeMensagens;

    @JsonProperty("lista")
    private List<Object> lista;

    // Getters e setters omitidos por brevidade
    public Integer getStatus() {
        return status;
    }

    public String getTransacao() {
        return transacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getCausa() {
        return causa;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public Integer getQuantidadeMensagens() {
        return quantidadeMensagens;
    }

    public List<Object> getLista() {
        return lista;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setTransacao(String transacao) {
        this.transacao = transacao;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public void setQuantidadeMensagens(Integer quantidadeMensagens) {
        this.quantidadeMensagens = quantidadeMensagens;
    }

    public void setLista(List<Object> lista) {
        this.lista = lista;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Titulo {

        @JsonProperty("agencCred")
        private Integer agencCred;

        @JsonProperty("ctaCred")
        private Integer ctaCred;

        @JsonProperty("digCred")
        private String digCred;

        @JsonProperty("razCredt")
        private Integer razCredt;

        @JsonProperty("cip")
        private Integer cip;

        @JsonProperty("codStatus")
        private Integer codStatus;

        @JsonProperty("status")
        private String status;

        @JsonProperty("cedente")
        private Cedente cedente;

        @JsonProperty("sacado")
        private Sacado sacado;

        @JsonProperty("sacador")
        private Sacador sacador;

        @JsonProperty("snumero")
        private String snumero;

        @JsonProperty("especDocto")
        private String especDocto;

        @JsonProperty("descrEspec")
        private String descrEspec;

        @JsonProperty("dataReg")
        private String dataReg;

        @JsonProperty("dataEmis")
        private String dataEmis;

        @JsonProperty("dataVencto")
        private String dataVencto;

        @JsonProperty("especMoeda")
        private String especMoeda;

        @JsonProperty("qtdeMoeda")
        private Integer qtdeMoeda;

        @JsonProperty("qtdeCas")
        private Integer qtdeCas;

        @JsonProperty("descrMoeda")
        private String descrMoeda;

        @JsonProperty("valMoeda")
        private Double valMoeda;

        @JsonProperty("valorIof")
        private Double valorIof;

        @JsonProperty("valAbat")
        private Double valAbat;

        @JsonProperty("dataMulta")
        private String dataMulta;

        @JsonProperty("diasMulta")
        private Integer diasMulta;

        @JsonProperty("valMulta")
        private Double valMulta;

        @JsonProperty("qtdeCasMul")
        private Integer qtdeCasMul;

        @JsonProperty("codValMul")
        private Integer codValMul;

        @JsonProperty("descrMulta")
        private String descrMulta;

        @JsonProperty("dataPerm")
        private String dataPerm;

        @JsonProperty("diasJuros")
        private Integer diasJuros;

        @JsonProperty("valPerm")
        private Double valPerm;

        @JsonProperty("codComisPerm")
        private Integer codComisPerm;

        @JsonProperty("dataDesc1")
        private String dataDesc1;

        @JsonProperty("valDesc1")
        private Double valDesc1;

        @JsonProperty("qtdeCasDe1")
        private Integer qtdeCasDe1;

        @JsonProperty("codValDe1")
        private Integer codValDe1;

        @JsonProperty("descrDesc1")
        private String descrDesc1;

        @JsonProperty("dataDesc2")
        private String dataDesc2;

        @JsonProperty("valDesc2")
        private Double valDesc2;

        @JsonProperty("qtdeCasDe2")
        private Integer qtdeCasDe2;

        @JsonProperty("codValDe2")
        private Integer codValDe2;

        @JsonProperty("descrDesc2")
        private String descrDesc2;

        @JsonProperty("dataDesc3")
        private String dataDesc3;

        @JsonProperty("valDesc3")
        private Double valDesc3;

        @JsonProperty("qtdeCasDe3")
        private Integer qtdeCasDe3;

        @JsonProperty("codValDe3")
        private Integer codValDe3;

        @JsonProperty("descrDesc3")
        private String descrDesc3;

        @JsonProperty("dataInstr")
        private String dataInstr;

        @JsonProperty("diasProt")
        private Integer diasProt;

        @JsonProperty("dataCartor")
        private String dataCartor;

        @JsonProperty("numCartor")
        private String numCartor;

        @JsonProperty("numProtoc")
        private String numProtoc;

        @JsonProperty("dataPedSus")
        private String dataPedSus;

        @JsonProperty("dataSust")
        private String dataSust;

        @JsonProperty("despCart")
        private Double despCart;

        @JsonProperty("bcoCentr")
        private Integer bcoCentr;

        @JsonProperty("ageCentr")
        private Integer ageCentr;

        @JsonProperty("acessEsc")
        private Integer acessEsc;

        @JsonProperty("tipEndo")
        private String tipEndo;

        @JsonProperty("oriProt")
        private Integer oriProt;

        @JsonProperty("corige35")
        private String corige35;

        @JsonProperty("ctpoVencto")
        private Integer ctpoVencto;

        @JsonProperty("codInscrProt")
        private Integer codInscrProt;

        @JsonProperty("qtdDiasDecurPrz")
        private Integer qtdDiasDecurPrz;

        @JsonProperty("ctrlPartic")
        private String ctrlPartic;

        @JsonProperty("diasComisPerm")
        private Integer diasComisPerm;

        @JsonProperty("qmoedaComisPerm")
        private Integer qmoedaComisPerm;

        @JsonProperty("indTitParceld")
        private String indTitParceld;

        @JsonProperty("indParcelaPrin")
        private String indParcelaPrin;

        @JsonProperty("indBoletoDda")
        private String indBoletoDda;

        @JsonProperty("codBarras")
        private String codBarras;

        @JsonProperty("linhaDig")
        private String linhaDig;

        @JsonProperty("valorMoedaBol")
        private Double valorMoedaBol;

        @JsonProperty("dataVenctoBol")
        private String dataVenctoBol;

        @JsonProperty("dataLimitePgt")
        private String dataLimitePgt;

        @JsonProperty("dataImpressao")
        private String dataImpressao;

        @JsonProperty("horaImpressao")
        private String horaImpressao;

        @JsonProperty("identTitDda")
        private Long identTitDda;

        @JsonProperty("exibeLinDig")
        private String exibeLinDig;

        @JsonProperty("permitePgtoParcial")
        private String permitePgtoParcial;

        @JsonProperty("qtdePgtoParcial")
        private Integer qtdePgtoParcial;

        @JsonProperty("dtPagto")
        private String dtPagto;

        @JsonProperty("vlrPagto")
        private Double vlrPagto;

        @JsonProperty("qtdPagto")
        private Integer qtdPagto;

        @JsonProperty("bcoProc")
        private Integer bcoProc;

        @JsonProperty("ageProc")
        private Integer ageProc;

        @JsonProperty("enderecoEma")
        private String enderecoEma;

        @JsonProperty("cebp")
        private String cepb;

        @JsonProperty("debitoAuto")
        private String debitoAuto;

        @JsonProperty("aceite")
        private String aceite;

        @JsonProperty("cense")
        private Integer cense;

        @JsonProperty("agenOper")
        private Integer agenOper;

        @JsonProperty("bcoDepos")
        private Integer bcoDepos;

        @JsonProperty("agenDepos")
        private Integer agenDepos;

        @JsonProperty("baixa")
        private Baixa baixa;

        // Getters e Setters omitidos para brevidade
        public Integer getAgencCred() {
            return agencCred;
        }

        public Integer getCtaCred() {
            return ctaCred;
        }

        public String getDigCred() {
            return digCred;
        }

        public Integer getRazCredt() {
            return razCredt;
        }

        public Integer getCip() {
            return cip;
        }

        public Integer getCodStatus() {
            return codStatus;
        }

        public String getStatus() {
            return status;
        }

        public Cedente getCedente() {
            return cedente;
        }

        public Sacado getSacado() {
            return sacado;
        }

        public Sacador getSacador() {
            return sacador;
        }

        public String getSnumero() {
            return snumero;
        }

        public String getEspecDocto() {
            return especDocto;
        }

        public String getDescrEspec() {
            return descrEspec;
        }

        public String getDataReg() {
            return dataReg;
        }

        public String getDataEmis() {
            return dataEmis;
        }

        public String getDataVencto() {
            return dataVencto;
        }

        public String getEspecMoeda() {
            return especMoeda;
        }

        public Integer getQtdeMoeda() {
            return qtdeMoeda;
        }

        public Integer getQtdeCas() {
            return qtdeCas;
        }

        public String getDescrMoeda() {
            return descrMoeda;
        }

        public Double getValMoeda() {
            return valMoeda;
        }

        public Double getValorIof() {
            return valorIof;
        }

        public Double getValAbat() {
            return valAbat;
        }

        public String getDataMulta() {
            return dataMulta;
        }

        public Integer getDiasMulta() {
            return diasMulta;
        }

        public Double getValMulta() {
            return valMulta;
        }

        public Integer getQtdeCasMul() {
            return qtdeCasMul;
        }

        public Integer getCodValMul() {
            return codValMul;
        }

        public String getDescrMulta() {
            return descrMulta;
        }

        public String getDataPerm() {
            return dataPerm;
        }

        public Integer getDiasJuros() {
            return diasJuros;
        }

        public Double getValPerm() {
            return valPerm;
        }

        public Integer getCodComisPerm() {
            return codComisPerm;
        }

        public String getDataDesc1() {
            return dataDesc1;
        }

        public Double getValDesc1() {
            return valDesc1;
        }

        public Integer getQtdeCasDe1() {
            return qtdeCasDe1;
        }

        public Integer getCodValDe1() {
            return codValDe1;
        }

        public String getDescrDesc1() {
            return descrDesc1;
        }

        public String getDataDesc2() {
            return dataDesc2;
        }

        public Double getValDesc2() {
            return valDesc2;
        }

        public Integer getQtdeCasDe2() {
            return qtdeCasDe2;
        }

        public Integer getCodValDe2() {
            return codValDe2;
        }

        public String getDescrDesc2() {
            return descrDesc2;
        }

        public String getDataDesc3() {
            return dataDesc3;
        }

        public Double getValDesc3() {
            return valDesc3;
        }

        public Integer getQtdeCasDe3() {
            return qtdeCasDe3;
        }

        public Integer getCodValDe3() {
            return codValDe3;
        }

        public String getDescrDesc3() {
            return descrDesc3;
        }

        public String getDataInstr() {
            return dataInstr;
        }

        public Integer getDiasProt() {
            return diasProt;
        }

        public String getDataCartor() {
            return dataCartor;
        }

        public String getNumCartor() {
            return numCartor;
        }

        public String getNumProtoc() {
            return numProtoc;
        }

        public String getDataPedSus() {
            return dataPedSus;
        }

        public String getDataSust() {
            return dataSust;
        }

        public Double getDespCart() {
            return despCart;
        }

        public Integer getBcoCentr() {
            return bcoCentr;
        }

        public Integer getAgeCentr() {
            return ageCentr;
        }

        public Integer getAcessEsc() {
            return acessEsc;
        }

        public String getTipEndo() {
            return tipEndo;
        }

        public Integer getOriProt() {
            return oriProt;
        }

        public String getCorige35() {
            return corige35;
        }

        public Integer getCtpoVencto() {
            return ctpoVencto;
        }

        public Integer getCodInscrProt() {
            return codInscrProt;
        }

        public Integer getQtdDiasDecurPrz() {
            return qtdDiasDecurPrz;
        }

        public String getCtrlPartic() {
            return ctrlPartic;
        }

        public Integer getDiasComisPerm() {
            return diasComisPerm;
        }

        public Integer getQmoedaComisPerm() {
            return qmoedaComisPerm;
        }

        public String getIndTitParceld() {
            return indTitParceld;
        }

        public String getIndParcelaPrin() {
            return indParcelaPrin;
        }

        public String getIndBoletoDda() {
            return indBoletoDda;
        }

        public String getCodBarras() {
            return codBarras;
        }

        public String getLinhaDig() {
            return linhaDig;
        }

        public Double getValorMoedaBol() {
            return valorMoedaBol;
        }

        public String getDataVenctoBol() {
            return dataVenctoBol;
        }

        public String getDataLimitePgt() {
            return dataLimitePgt;
        }

        public String getDataImpressao() {
            return dataImpressao;
        }

        public String getHoraImpressao() {
            return horaImpressao;
        }

        public Long getIdentTitDda() {
            return identTitDda;
        }

        public String getExibeLinDig() {
            return exibeLinDig;
        }

        public String getPermitePgtoParcial() {
            return permitePgtoParcial;
        }

        public Integer getQtdePgtoParcial() {
            return qtdePgtoParcial;
        }

        public String getDtPagto() {
            return dtPagto;
        }

        public Double getVlrPagto() {
            return vlrPagto;
        }

        public Integer getQtdPagto() {
            return qtdPagto;
        }

        public Integer getBcoProc() {
            return bcoProc;
        }

        public Integer getAgeProc() {
            return ageProc;
        }

        public String getEnderecoEma() {
            return enderecoEma;
        }

        public String getCepb() {
            return cepb;
        }

        public String getDebitoAuto() {
            return debitoAuto;
        }

        public String getAceite() {
            return aceite;
        }

        public Integer getCense() {
            return cense;
        }

        public Integer getAgenOper() {
            return agenOper;
        }

        public Integer getBcoDepos() {
            return bcoDepos;
        }

        public Integer getAgenDepos() {
            return agenDepos;
        }

        public Baixa getBaixa() {
            return baixa;
        }

        public void setAgencCred(Integer agencCred) {
            this.agencCred = agencCred;
        }

        public void setCtaCred(Integer ctaCred) {
            this.ctaCred = ctaCred;
        }

        public void setDigCred(String digCred) {
            this.digCred = digCred;
        }

        public void setRazCredt(Integer razCredt) {
            this.razCredt = razCredt;
        }

        public void setCip(Integer cip) {
            this.cip = cip;
        }

        public void setCodStatus(Integer codStatus) {
            this.codStatus = codStatus;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setCedente(Cedente cedente) {
            this.cedente = cedente;
        }

        public void setSacado(Sacado sacado) {
            this.sacado = sacado;
        }

        public void setSacador(Sacador sacador) {
            this.sacador = sacador;
        }

        public void setSnumero(String snumero) {
            this.snumero = snumero;
        }

        public void setEspecDocto(String especDocto) {
            this.especDocto = especDocto;
        }

        public void setDescrEspec(String descrEspec) {
            this.descrEspec = descrEspec;
        }

        public void setDataReg(String dataReg) {
            this.dataReg = dataReg;
        }

        public void setDataEmis(String dataEmis) {
            this.dataEmis = dataEmis;
        }

        public void setDataVencto(String dataVencto) {
            this.dataVencto = dataVencto;
        }

        public void setEspecMoeda(String especMoeda) {
            this.especMoeda = especMoeda;
        }

        public void setQtdeMoeda(Integer qtdeMoeda) {
            this.qtdeMoeda = qtdeMoeda;
        }

        public void setQtdeCas(Integer qtdeCas) {
            this.qtdeCas = qtdeCas;
        }

        public void setDescrMoeda(String descrMoeda) {
            this.descrMoeda = descrMoeda;
        }

        public void setValMoeda(Double valMoeda) {
            this.valMoeda = valMoeda;
        }

        public void setValorIof(Double valorIof) {
            this.valorIof = valorIof;
        }

        public void setValAbat(Double valAbat) {
            this.valAbat = valAbat;
        }

        public void setDataMulta(String dataMulta) {
            this.dataMulta = dataMulta;
        }

        public void setDiasMulta(Integer diasMulta) {
            this.diasMulta = diasMulta;
        }

        public void setValMulta(Double valMulta) {
            this.valMulta = valMulta;
        }

        public void setQtdeCasMul(Integer qtdeCasMul) {
            this.qtdeCasMul = qtdeCasMul;
        }

        public void setCodValMul(Integer codValMul) {
            this.codValMul = codValMul;
        }

        public void setDescrMulta(String descrMulta) {
            this.descrMulta = descrMulta;
        }

        public void setDataPerm(String dataPerm) {
            this.dataPerm = dataPerm;
        }

        public void setDiasJuros(Integer diasJuros) {
            this.diasJuros = diasJuros;
        }

        public void setValPerm(Double valPerm) {
            this.valPerm = valPerm;
        }

        public void setCodComisPerm(Integer codComisPerm) {
            this.codComisPerm = codComisPerm;
        }

        public void setDataDesc1(String dataDesc1) {
            this.dataDesc1 = dataDesc1;
        }

        public void setValDesc1(Double valDesc1) {
            this.valDesc1 = valDesc1;
        }

        public void setQtdeCasDe1(Integer qtdeCasDe1) {
            this.qtdeCasDe1 = qtdeCasDe1;
        }

        public void setCodValDe1(Integer codValDe1) {
            this.codValDe1 = codValDe1;
        }

        public void setDescrDesc1(String descrDesc1) {
            this.descrDesc1 = descrDesc1;
        }

        public void setDataDesc2(String dataDesc2) {
            this.dataDesc2 = dataDesc2;
        }

        public void setValDesc2(Double valDesc2) {
            this.valDesc2 = valDesc2;
        }

        public void setQtdeCasDe2(Integer qtdeCasDe2) {
            this.qtdeCasDe2 = qtdeCasDe2;
        }

        public void setCodValDe2(Integer codValDe2) {
            this.codValDe2 = codValDe2;
        }

        public void setDescrDesc2(String descrDesc2) {
            this.descrDesc2 = descrDesc2;
        }

        public void setDataDesc3(String dataDesc3) {
            this.dataDesc3 = dataDesc3;
        }

        public void setValDesc3(Double valDesc3) {
            this.valDesc3 = valDesc3;
        }

        public void setQtdeCasDe3(Integer qtdeCasDe3) {
            this.qtdeCasDe3 = qtdeCasDe3;
        }

        public void setCodValDe3(Integer codValDe3) {
            this.codValDe3 = codValDe3;
        }

        public void setDescrDesc3(String descrDesc3) {
            this.descrDesc3 = descrDesc3;
        }

        public void setDataInstr(String dataInstr) {
            this.dataInstr = dataInstr;
        }

        public void setDiasProt(Integer diasProt) {
            this.diasProt = diasProt;
        }

        public void setDataCartor(String dataCartor) {
            this.dataCartor = dataCartor;
        }

        public void setNumCartor(String numCartor) {
            this.numCartor = numCartor;
        }

        public void setNumProtoc(String numProtoc) {
            this.numProtoc = numProtoc;
        }

        public void setDataPedSus(String dataPedSus) {
            this.dataPedSus = dataPedSus;
        }

        public void setDataSust(String dataSust) {
            this.dataSust = dataSust;
        }

        public void setDespCart(Double despCart) {
            this.despCart = despCart;
        }

        public void setBcoCentr(Integer bcoCentr) {
            this.bcoCentr = bcoCentr;
        }

        public void setAgeCentr(Integer ageCentr) {
            this.ageCentr = ageCentr;
        }

        public void setAcessEsc(Integer acessEsc) {
            this.acessEsc = acessEsc;
        }

        public void setTipEndo(String tipEndo) {
            this.tipEndo = tipEndo;
        }

        public void setOriProt(Integer oriProt) {
            this.oriProt = oriProt;
        }

        public void setCorige35(String corige35) {
            this.corige35 = corige35;
        }

        public void setCtpoVencto(Integer ctpoVencto) {
            this.ctpoVencto = ctpoVencto;
        }

        public void setCodInscrProt(Integer codInscrProt) {
            this.codInscrProt = codInscrProt;
        }

        public void setQtdDiasDecurPrz(Integer qtdDiasDecurPrz) {
            this.qtdDiasDecurPrz = qtdDiasDecurPrz;
        }

        public void setCtrlPartic(String ctrlPartic) {
            this.ctrlPartic = ctrlPartic;
        }

        public void setDiasComisPerm(Integer diasComisPerm) {
            this.diasComisPerm = diasComisPerm;
        }

        public void setQmoedaComisPerm(Integer qmoedaComisPerm) {
            this.qmoedaComisPerm = qmoedaComisPerm;
        }

        public void setIndTitParceld(String indTitParceld) {
            this.indTitParceld = indTitParceld;
        }

        public void setIndParcelaPrin(String indParcelaPrin) {
            this.indParcelaPrin = indParcelaPrin;
        }

        public void setIndBoletoDda(String indBoletoDda) {
            this.indBoletoDda = indBoletoDda;
        }

        public void setCodBarras(String codBarras) {
            this.codBarras = codBarras;
        }

        public void setLinhaDig(String linhaDig) {
            this.linhaDig = linhaDig;
        }

        public void setValorMoedaBol(Double valorMoedaBol) {
            this.valorMoedaBol = valorMoedaBol;
        }

        public void setDataVenctoBol(String dataVenctoBol) {
            this.dataVenctoBol = dataVenctoBol;
        }

        public void setDataLimitePgt(String dataLimitePgt) {
            this.dataLimitePgt = dataLimitePgt;
        }

        public void setDataImpressao(String dataImpressao) {
            this.dataImpressao = dataImpressao;
        }

        public void setHoraImpressao(String horaImpressao) {
            this.horaImpressao = horaImpressao;
        }

        public void setIdentTitDda(Long identTitDda) {
            this.identTitDda = identTitDda;
        }

        public void setExibeLinDig(String exibeLinDig) {
            this.exibeLinDig = exibeLinDig;
        }

        public void setPermitePgtoParcial(String permitePgtoParcial) {
            this.permitePgtoParcial = permitePgtoParcial;
        }

        public void setQtdePgtoParcial(Integer qtdePgtoParcial) {
            this.qtdePgtoParcial = qtdePgtoParcial;
        }

        public void setDtPagto(String dtPagto) {
            this.dtPagto = dtPagto;
        }

        public void setVlrPagto(Double vlrPagto) {
            this.vlrPagto = vlrPagto;
        }

        public void setQtdPagto(Integer qtdPagto) {
            this.qtdPagto = qtdPagto;
        }

        public void setBcoProc(Integer bcoProc) {
            this.bcoProc = bcoProc;
        }

        public void setAgeProc(Integer ageProc) {
            this.ageProc = ageProc;
        }

        public void setEnderecoEma(String enderecoEma) {
            this.enderecoEma = enderecoEma;
        }

        public void setCepb(String cepb) {
            this.cepb = cepb;
        }

        public void setDebitoAuto(String debitoAuto) {
            this.debitoAuto = debitoAuto;
        }

        public void setAceite(String aceite) {
            this.aceite = aceite;
        }

        public void setCense(Integer cense) {
            this.cense = cense;
        }

        public void setAgenOper(Integer agenOper) {
            this.agenOper = agenOper;
        }

        public void setBcoDepos(Integer bcoDepos) {
            this.bcoDepos = bcoDepos;
        }

        public void setAgenDepos(Integer agenDepos) {
            this.agenDepos = agenDepos;
        }

        public void setBaixa(Baixa baixa) {
            this.baixa = baixa;
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Cedente {

        @JsonProperty("cnpj")
        private Long cnpj;
        @JsonProperty("nome")
        private String nome;
        @JsonProperty("endereco")
        private String endereco;
        @JsonProperty("numero")
        private String numero;
        @JsonProperty("complemento")
        private String complemento;
        @JsonProperty("bairro")
        private String bairro;
        @JsonProperty("cep")
        private Integer cep;
        @JsonProperty("cepc")
        private String cepc;
        @JsonProperty("cidade")
        private String cidade;
        @JsonProperty("uf")
        private String uf;

        // Getters e Setters omitidos
        public Long getCnpj() {
            return cnpj;
        }

        public String getNome() {
            return nome;
        }

        public String getEndereco() {
            return endereco;
        }

        public String getNumero() {
            return numero;
        }

        public String getComplemento() {
            return complemento;
        }

        public String getBairro() {
            return bairro;
        }

        public Integer getCep() {
            return cep;
        }

        public String getCepc() {
            return cepc;
        }

        public String getCidade() {
            return cidade;
        }

        public String getUf() {
            return uf;
        }

        public void setCnpj(Long cnpj) {
            this.cnpj = cnpj;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public void setComplemento(String complemento) {
            this.complemento = complemento;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public void setCep(Integer cep) {
            this.cep = cep;
        }

        public void setCepc(String cepc) {
            this.cepc = cepc;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

        public void setUf(String uf) {
            this.uf = uf;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sacado extends Cedente {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sacador extends Cedente {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Baixa {

        @JsonProperty("codigo")
        private Integer codigo;
        @JsonProperty("descricao")
        private String descricao;
        @JsonProperty("data")
        private String data;

        // Getters e Setters omitidos
        public Integer getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        public String getData() {
            return data;
        }

        public void setCodigo(Integer codigo) {
            this.codigo = codigo;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
