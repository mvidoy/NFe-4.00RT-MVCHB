package com.backend.api.cobranca.bradesco.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseJsonBAIXADEBOLETOBRADESCOdto {

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("transacao")
    private String transacao;

    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("dados")
    private Dados dados;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTransacao() {
        return transacao;
    }

    public void setTransacao(String transacao) {
        this.transacao = transacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Dados getDados() {
        return dados;
    }

    public void setDados(Dados dados) {
        this.dados = dados;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Dados {

        @JsonProperty("dataHoraSolicitacao")
        private String dataHoraSolicitacao;

        @JsonProperty("status")
        private Integer status;

        @JsonProperty("statusAnterior")
        private Integer statusAnterior;

        public String getDataHoraSolicitacao() {
            return dataHoraSolicitacao;
        }

        public void setDataHoraSolicitacao(String dataHoraSolicitacao) {
            this.dataHoraSolicitacao = dataHoraSolicitacao;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getStatusAnterior() {
            return statusAnterior;
        }

        public void setStatusAnterior(Integer statusAnterior) {
            this.statusAnterior = statusAnterior;
        }
    }
}
