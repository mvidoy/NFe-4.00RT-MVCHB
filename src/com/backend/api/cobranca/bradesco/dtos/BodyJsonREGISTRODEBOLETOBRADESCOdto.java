package com.backend.api.cobranca.bradesco.dtos;

import java.util.List;

public class BodyJsonREGISTRODEBOLETOBRADESCOdto {

    // Identificacao
    private String debitoAutomatico;
    private String codigoUsuarioSolicitante;

    // CNPJ Raiz/Filial/Controle
    private String nuCPFCNPJ;
    private int filialCPFCNPJ;
    private int ctrlCPFCNPJ;

    // Informacoes do Titulo
    private int registraTitulo;
    private int idProduto;
    private long nuNegociacao;
    private long nuTitulo;
    private String nuCliente;
    private String dtEmissaoTitulo;
    private String dtVencimentoTitulo;
    private int tpVencimento;
    private int indicadorMoeda;
    private String vlNominalTitulo;
    private int qmoedaNegocTitlo;
    private int cdEspecieTitulo;
    private String cindcdAceitSacdo;
    private int tpProtestoAutomaticoNegativacao;
    private int prazoProtestoAutomaticoNegativacao;
    private int tipoDiasDecursoProt;
    private int tipoDecursoPrazo;
    private String controleParticipante;
    private String cdPagamentoParcial;
    private int qtdePagamentoParcial;
    private int tipoPrazoDecursoTres;
    private double percentualJuros;
    //private double vlJuros;
    private int qtdeDiasJuros;
    //private double percentualMulta;
    //private double vlMulta;
    //private int qtdeDiasMulta;
    //private double percentualDesconto1 = 0.00;
    //private double vlDesconto1 = 0.00;
    //private String dataLimiteDesconto1;
    //private double percentualDesconto2 = 0.00;
    //private double vlDesconto2 = 0.00;
    //private String dataLimiteDesconto2;
    //private double percentualDesconto3 = 0.00;
    //private double vlDesconto3 = 0.00;
    //private String dataLimiteDesconto3;
    //private int prazoBonificacao;
    //private double percentualBonificacao;
    //private double vlBonificacao;
    //private String dtLimiteBonificacao;
    //private double vlAbatimento;
    //private double vlIOF;

    // Pagador
    private String nomePagador;
    private String logradouroPagador;
    private String nuLogradouroPagador;
    private String complementoLogradouroPagador;
    private int cepPagador;
    private int complementoCepPagador;
    private String bairroPagador;
    private String municipioPagador;
    private String ufPagador;
    private int cdIndCpfcnpjPagador;
    private long nuCpfcnpjPagador;
    private String endEletronicoPagador;
    private int dddFoneSacado;
    private long foneSacado;

    // Débito automático
    private int bancoDoDebAutomatico;
    private int agenciaDoDebAutomatico;
    private int digitoAgenciaDoDebAutomat;
    private int contaDoDebAutomatico;
    private int razaoDoDebAutomatico;

    // Protesto
    private int codBancoDoProtesto;
    private int agenciaDoProtesto;

    // Sacador / Avalista
    private String nomeSacadorAvalista;
    private String logradouroSacadorAvalista;
    private String nuLogradouroSacadorAvalista;
    private String complementoLogradouroSacadorAvalista;
    private int cepSacadorAvalista;
    private int complementoCepSacadorAvalista;
    private String bairroSacadorAvalista;
    private String municipioSacadorAvalista;
    private String ufSacadorAvalista;
    private int cdIndCpfcnpjSacadorAvalista;
    private long nuCpfcnpjSacadorAvalista;
    private String enderecoSacadorAvalista;
    private int dddFoneSacadorAvalista;
    private long foneSacadorAvalista;

    // Lista de mensagens
    private List<Mensagem> listaMsgs;

    public static class Mensagem {

        private String mensagem;

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }
    }

    public String getDebitoAutomatico() {
        return debitoAutomatico;
    }

    public String getCodigoUsuarioSolicitante() {
        return codigoUsuarioSolicitante;
    }

    public String getNuCPFCNPJ() {
        return nuCPFCNPJ;
    }

    public int getFilialCPFCNPJ() {
        return filialCPFCNPJ;
    }

    public int getCtrlCPFCNPJ() {
        return ctrlCPFCNPJ;
    }

    public int getRegistraTitulo() {
        return registraTitulo;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public long getNuNegociacao() {
        return nuNegociacao;
    }

    public long getNuTitulo() {
        return nuTitulo;
    }

    public String getNuCliente() {
        return nuCliente;
    }

    public String getDtEmissaoTitulo() {
        return dtEmissaoTitulo;
    }

    public String getDtVencimentoTitulo() {
        return dtVencimentoTitulo;
    }

    public int getTpVencimento() {
        return tpVencimento;
    }

    public int getIndicadorMoeda() {
        return indicadorMoeda;
    }

    public String getVlNominalTitulo() {
        return vlNominalTitulo;
    }

    public int getQmoedaNegocTitlo() {
        return qmoedaNegocTitlo;
    }

    public int getCdEspecieTitulo() {
        return cdEspecieTitulo;
    }

    public String getCindcdAceitSacdo() {
        return cindcdAceitSacdo;
    }

    public int getTpProtestoAutomaticoNegativacao() {
        return tpProtestoAutomaticoNegativacao;
    }

    public int getPrazoProtestoAutomaticoNegativacao() {
        return prazoProtestoAutomaticoNegativacao;
    }

    public int getTipoDiasDecursoProt() {
        return tipoDiasDecursoProt;
    }

    public int getTipoDecursoPrazo() {
        return tipoDecursoPrazo;
    }

    public String getControleParticipante() {
        return controleParticipante;
    }

    public String getCdPagamentoParcial() {
        return cdPagamentoParcial;
    }

    public int getQtdePagamentoParcial() {
        return qtdePagamentoParcial;
    }

    public int getTipoPrazoDecursoTres() {
        return tipoPrazoDecursoTres;
    }

    public double getPercentualJuros() {
        return percentualJuros;
    }

    //public double getVlJuros() {
    //    return vlJuros;
    //}

    public int getQtdeDiasJuros() {
        return qtdeDiasJuros;
    }

    //public double getPercentualMulta() {
    //    return percentualMulta;
    //}

    //public double getVlMulta() {
    //    return vlMulta;
    //}

    //public int getQtdeDiasMulta() {
    //    return qtdeDiasMulta;
    //}

    //public double getPercentualDesconto1() {
    //    return percentualDesconto1;
    //}

    //public double getVlDesconto1() {
    //    return vlDesconto1;
    //}

    //public String getDataLimiteDesconto1() {
    //    return dataLimiteDesconto1;
    //}

    //public double getPercentualDesconto2() {
    //    return percentualDesconto2;
    //}

    //public double getVlDesconto2() {
    //    return vlDesconto2;
    //}

    //public String getDataLimiteDesconto2() {
    //    return dataLimiteDesconto2;
    //}

    //public double getPercentualDesconto3() {
    //    return percentualDesconto3;
    //}

    //public double getVlDesconto3() {
    //    return vlDesconto3;
    //}

    //public String getDataLimiteDesconto3() {
    //    return dataLimiteDesconto3;
    //}

    //public int getPrazoBonificacao() {
    //    return prazoBonificacao;
    //}

    //public double getPercentualBonificacao() {
    //    return percentualBonificacao;
    //}

    //public double getVlBonificacao() {
    //    return vlBonificacao;
    //}

    //public String getDtLimiteBonificacao() {
    //    return dtLimiteBonificacao;
    //}

    //public double getVlAbatimento() {
    //    return vlAbatimento;
    //}

    //public double getVlIOF() {
    //    return vlIOF;
    //}

    public String getNomePagador() {
        return nomePagador;
    }

    public String getLogradouroPagador() {
        return logradouroPagador;
    }

    public String getNuLogradouroPagador() {
        return nuLogradouroPagador;
    }

    public String getComplementoLogradouroPagador() {
        return complementoLogradouroPagador;
    }

    public int getCepPagador() {
        return cepPagador;
    }

    public int getComplementoCepPagador() {
        return complementoCepPagador;
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

    public int getCdIndCpfcnpjPagador() {
        return cdIndCpfcnpjPagador;
    }

    public long getNuCpfcnpjPagador() {
        return nuCpfcnpjPagador;
    }

    public String getEndEletronicoPagador() {
        return endEletronicoPagador;
    }

    public int getDddFoneSacado() {
        return dddFoneSacado;
    }

    public long getFoneSacado() {
        return foneSacado;
    }

    public int getBancoDoDebAutomatico() {
        return bancoDoDebAutomatico;
    }

    public int getAgenciaDoDebAutomatico() {
        return agenciaDoDebAutomatico;
    }

    public int getDigitoAgenciaDoDebAutomat() {
        return digitoAgenciaDoDebAutomat;
    }

    public int getContaDoDebAutomatico() {
        return contaDoDebAutomatico;
    }

    public int getRazaoDoDebAutomatico() {
        return razaoDoDebAutomatico;
    }

    public int getCodBancoDoProtesto() {
        return codBancoDoProtesto;
    }

    public int getAgenciaDoProtesto() {
        return agenciaDoProtesto;
    }

    public String getNomeSacadorAvalista() {
        return nomeSacadorAvalista;
    }

    public String getLogradouroSacadorAvalista() {
        return logradouroSacadorAvalista;
    }

    public String getNuLogradouroSacadorAvalista() {
        return nuLogradouroSacadorAvalista;
    }

    public String getComplementoLogradouroSacadorAvalista() {
        return complementoLogradouroSacadorAvalista;
    }

    public int getCepSacadorAvalista() {
        return cepSacadorAvalista;
    }

    public int getComplementoCepSacadorAvalista() {
        return complementoCepSacadorAvalista;
    }

    public String getBairroSacadorAvalista() {
        return bairroSacadorAvalista;
    }

    public String getMunicipioSacadorAvalista() {
        return municipioSacadorAvalista;
    }

    public String getUfSacadorAvalista() {
        return ufSacadorAvalista;
    }

    public int getCdIndCpfcnpjSacadorAvalista() {
        return cdIndCpfcnpjSacadorAvalista;
    }

    public long getNuCpfcnpjSacadorAvalista() {
        return nuCpfcnpjSacadorAvalista;
    }

    public String getEnderecoSacadorAvalista() {
        return enderecoSacadorAvalista;
    }

    public int getDddFoneSacadorAvalista() {
        return dddFoneSacadorAvalista;
    }

    public long getFoneSacadorAvalista() {
        return foneSacadorAvalista;
    }

    public List<Mensagem> getListaMsgs() {
        return listaMsgs;
    }

    public void setDebitoAutomatico(String debitoAutomatico) {
        this.debitoAutomatico = debitoAutomatico;
    }

    public void setCodigoUsuarioSolicitante(String codigoUsuarioSolicitante) {
        this.codigoUsuarioSolicitante = codigoUsuarioSolicitante;
    }

    public void setNuCPFCNPJ(String nuCPFCNPJ) {
        this.nuCPFCNPJ = nuCPFCNPJ;
    }

    public void setFilialCPFCNPJ(int filialCPFCNPJ) {
        this.filialCPFCNPJ = filialCPFCNPJ;
    }

    public void setCtrlCPFCNPJ(int ctrlCPFCNPJ) {
        this.ctrlCPFCNPJ = ctrlCPFCNPJ;
    }

    public void setRegistraTitulo(int registraTitulo) {
        this.registraTitulo = registraTitulo;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public void setNuNegociacao(long nuNegociacao) {
        this.nuNegociacao = nuNegociacao;
    }

    public void setNuTitulo(long nuTitulo) {
        this.nuTitulo = nuTitulo;
    }

    public void setNuCliente(String nuCliente) {
        this.nuCliente = nuCliente;
    }

    public void setDtEmissaoTitulo(String dtEmissaoTitulo) {
        this.dtEmissaoTitulo = dtEmissaoTitulo;
    }

    public void setDtVencimentoTitulo(String dtVencimentoTitulo) {
        this.dtVencimentoTitulo = dtVencimentoTitulo;
    }

    public void setTpVencimento(int tpVencimento) {
        this.tpVencimento = tpVencimento;
    }

    public void setIndicadorMoeda(int indicadorMoeda) {
        this.indicadorMoeda = indicadorMoeda;
    }

    public void setVlNominalTitulo(String vlNominalTitulo) {
        this.vlNominalTitulo = vlNominalTitulo;
    }

    public void setQmoedaNegocTitlo(int qmoedaNegocTitlo) {
        this.qmoedaNegocTitlo = qmoedaNegocTitlo;
    }

    public void setCdEspecieTitulo(int cdEspecieTitulo) {
        this.cdEspecieTitulo = cdEspecieTitulo;
    }

    public void setCindcdAceitSacdo(String cindcdAceitSacdo) {
        this.cindcdAceitSacdo = cindcdAceitSacdo;
    }

    public void setTpProtestoAutomaticoNegativacao(int tpProtestoAutomaticoNegativacao) {
        this.tpProtestoAutomaticoNegativacao = tpProtestoAutomaticoNegativacao;
    }

    public void setPrazoProtestoAutomaticoNegativacao(int prazoProtestoAutomaticoNegativacao) {
        this.prazoProtestoAutomaticoNegativacao = prazoProtestoAutomaticoNegativacao;
    }

    public void setTipoDiasDecursoProt(int tipoDiasDecursoProt) {
        this.tipoDiasDecursoProt = tipoDiasDecursoProt;
    }

    public void setTipoDecursoPrazo(int tipoDecursoPrazo) {
        this.tipoDecursoPrazo = tipoDecursoPrazo;
    }

    public void setControleParticipante(String controleParticipante) {
        this.controleParticipante = controleParticipante;
    }

    public void setCdPagamentoParcial(String cdPagamentoParcial) {
        this.cdPagamentoParcial = cdPagamentoParcial;
    }

    public void setQtdePagamentoParcial(int qtdePagamentoParcial) {
        this.qtdePagamentoParcial = qtdePagamentoParcial;
    }

    public void setTipoPrazoDecursoTres(int tipoPrazoDecursoTres) {
        this.tipoPrazoDecursoTres = tipoPrazoDecursoTres;
    }

    public void setPercentualJuros(double percentualJuros) {
        this.percentualJuros = percentualJuros;
    }

    //public void setVlJuros(double vlJuros) {
    //    this.vlJuros = vlJuros;
    //}

    public void setQtdeDiasJuros(int qtdeDiasJuros) {
        this.qtdeDiasJuros = qtdeDiasJuros;
    }

    //public void setPercentualMulta(double percentualMulta) {
    //    this.percentualMulta = percentualMulta;
    //}

    //public void setVlMulta(double vlMulta) {
    //    this.vlMulta = vlMulta;
    //}

    //public void setQtdeDiasMulta(int qtdeDiasMulta) {
    //    this.qtdeDiasMulta = qtdeDiasMulta;
    //}

    //public void setPercentualDesconto1(double percentualDesconto1) {
    //    this.percentualDesconto1 = percentualDesconto1;
    //}

    //public void setVlDesconto1(double vlDesconto1) {
    //    this.vlDesconto1 = vlDesconto1;
    //}

    //public void setDataLimiteDesconto1(String dataLimiteDesconto1) {
    //    this.dataLimiteDesconto1 = dataLimiteDesconto1;
    //}

    //public void setPercentualDesconto2(double percentualDesconto2) {
    //    this.percentualDesconto2 = percentualDesconto2;
    //}

    //public void setVlDesconto2(double vlDesconto2) {
    //    this.vlDesconto2 = vlDesconto2;
    //}

    //public void setDataLimiteDesconto2(String dataLimiteDesconto2) {
    //    this.dataLimiteDesconto2 = dataLimiteDesconto2;
    //}

    //public void setPercentualDesconto3(double percentualDesconto3) {
    //    this.percentualDesconto3 = percentualDesconto3;
    //}

    //public void setVlDesconto3(double vlDesconto3) {
    //    this.vlDesconto3 = vlDesconto3;
    //}

    //public void setDataLimiteDesconto3(String dataLimiteDesconto3) {
    //    this.dataLimiteDesconto3 = dataLimiteDesconto3;
    //}

    //public void setPrazoBonificacao(int prazoBonificacao) {
    //    this.prazoBonificacao = prazoBonificacao;
    //}

    //public void setPercentualBonificacao(double percentualBonificacao) {
    //    this.percentualBonificacao = percentualBonificacao;
    //}

    //public void setVlBonificacao(double vlBonificacao) {
    //    this.vlBonificacao = vlBonificacao;
    //}

    //public void setDtLimiteBonificacao(String dtLimiteBonificacao) {
    //    this.dtLimiteBonificacao = dtLimiteBonificacao;
    //}

    //public void setVlAbatimento(double vlAbatimento) {
    //    this.vlAbatimento = vlAbatimento;
    //}

    //public void setVlIOF(double vlIOF) {
    //    this.vlIOF = vlIOF;
    //}

    public void setNomePagador(String nomePagador) {
        this.nomePagador = nomePagador;
    }

    public void setLogradouroPagador(String logradouroPagador) {
        this.logradouroPagador = logradouroPagador;
    }

    public void setNuLogradouroPagador(String nuLogradouroPagador) {
        this.nuLogradouroPagador = nuLogradouroPagador;
    }

    public void setComplementoLogradouroPagador(String complementoLogradouroPagador) {
        this.complementoLogradouroPagador = complementoLogradouroPagador;
    }

    public void setCepPagador(int cepPagador) {
        this.cepPagador = cepPagador;
    }

    public void setComplementoCepPagador(int complementoCepPagador) {
        this.complementoCepPagador = complementoCepPagador;
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

    public void setCdIndCpfcnpjPagador(int cdIndCpfcnpjPagador) {
        this.cdIndCpfcnpjPagador = cdIndCpfcnpjPagador;
    }

    public void setNuCpfcnpjPagador(long nuCpfcnpjPagador) {
        this.nuCpfcnpjPagador = nuCpfcnpjPagador;
    }

    public void setEndEletronicoPagador(String endEletronicoPagador) {
        this.endEletronicoPagador = endEletronicoPagador;
    }

    public void setDddFoneSacado(int dddFoneSacado) {
        this.dddFoneSacado = dddFoneSacado;
    }

    public void setFoneSacado(long foneSacado) {
        this.foneSacado = foneSacado;
    }

    public void setBancoDoDebAutomatico(int bancoDoDebAutomatico) {
        this.bancoDoDebAutomatico = bancoDoDebAutomatico;
    }

    public void setAgenciaDoDebAutomatico(int agenciaDoDebAutomatico) {
        this.agenciaDoDebAutomatico = agenciaDoDebAutomatico;
    }

    public void setDigitoAgenciaDoDebAutomat(int digitoAgenciaDoDebAutomat) {
        this.digitoAgenciaDoDebAutomat = digitoAgenciaDoDebAutomat;
    }

    public void setContaDoDebAutomatico(int contaDoDebAutomatico) {
        this.contaDoDebAutomatico = contaDoDebAutomatico;
    }

    public void setRazaoDoDebAutomatico(int razaoDoDebAutomatico) {
        this.razaoDoDebAutomatico = razaoDoDebAutomatico;
    }

    public void setCodBancoDoProtesto(int codBancoDoProtesto) {
        this.codBancoDoProtesto = codBancoDoProtesto;
    }

    public void setAgenciaDoProtesto(int agenciaDoProtesto) {
        this.agenciaDoProtesto = agenciaDoProtesto;
    }

    public void setNomeSacadorAvalista(String nomeSacadorAvalista) {
        this.nomeSacadorAvalista = nomeSacadorAvalista;
    }

    public void setLogradouroSacadorAvalista(String logradouroSacadorAvalista) {
        this.logradouroSacadorAvalista = logradouroSacadorAvalista;
    }

    public void setNuLogradouroSacadorAvalista(String nuLogradouroSacadorAvalista) {
        this.nuLogradouroSacadorAvalista = nuLogradouroSacadorAvalista;
    }

    public void setComplementoLogradouroSacadorAvalista(String complementoLogradouroSacadorAvalista) {
        this.complementoLogradouroSacadorAvalista = complementoLogradouroSacadorAvalista;
    }

    public void setCepSacadorAvalista(int cepSacadorAvalista) {
        this.cepSacadorAvalista = cepSacadorAvalista;
    }

    public void setComplementoCepSacadorAvalista(int complementoCepSacadorAvalista) {
        this.complementoCepSacadorAvalista = complementoCepSacadorAvalista;
    }

    public void setBairroSacadorAvalista(String bairroSacadorAvalista) {
        this.bairroSacadorAvalista = bairroSacadorAvalista;
    }

    public void setMunicipioSacadorAvalista(String municipioSacadorAvalista) {
        this.municipioSacadorAvalista = municipioSacadorAvalista;
    }

    public void setUfSacadorAvalista(String ufSacadorAvalista) {
        this.ufSacadorAvalista = ufSacadorAvalista;
    }

    public void setCdIndCpfcnpjSacadorAvalista(int cdIndCpfcnpjSacadorAvalista) {
        this.cdIndCpfcnpjSacadorAvalista = cdIndCpfcnpjSacadorAvalista;
    }

    public void setNuCpfcnpjSacadorAvalista(long nuCpfcnpjSacadorAvalista) {
        this.nuCpfcnpjSacadorAvalista = nuCpfcnpjSacadorAvalista;
    }

    public void setEnderecoSacadorAvalista(String enderecoSacadorAvalista) {
        this.enderecoSacadorAvalista = enderecoSacadorAvalista;
    }

    public void setDddFoneSacadorAvalista(int dddFoneSacadorAvalista) {
        this.dddFoneSacadorAvalista = dddFoneSacadorAvalista;
    }

    public void setFoneSacadorAvalista(long foneSacadorAvalista) {
        this.foneSacadorAvalista = foneSacadorAvalista;
    }

    public void setListaMsgs(List<Mensagem> listaMsgs) {
        this.listaMsgs = listaMsgs;
    }
}
