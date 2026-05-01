package com.backend.api.cobranca.bradesco.dtos;

public class BodyJsonCONSULTADEBOLETOBRADESCOdto {

    private CpfCnpj cpfCnpj;
    private int produto;
    private long negociacao;
    private long nossoNumero;
    private int sequencia;
    private int status;

    public CpfCnpj getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(CpfCnpj cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    public long getNegociacao() {
        return negociacao;
    }

    public void setNegociacao(long negociacao) {
        this.negociacao = negociacao;
    }

    public long getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(long nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public int getSequencia() {
        return sequencia;
    }

    public void setSequencia(int sequencia) {
        this.sequencia = sequencia;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class CpfCnpj {

        private long cpfCnpj;
        private int filial;
        private int controle;

        public long getCpfCnpj() {
            return cpfCnpj;
        }

        public void setCpfCnpj(long cpfCnpj) {
            this.cpfCnpj = cpfCnpj;
        }

        public int getFilial() {
            return filial;
        }

        public void setFilial(int filial) {
            this.filial = filial;
        }

        public int getControle() {
            return controle;
        }

        public void setControle(int controle) {
            this.controle = controle;
        }
    }
}
