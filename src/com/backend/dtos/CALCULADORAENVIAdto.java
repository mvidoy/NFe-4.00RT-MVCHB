package com.backend.dtos;

import java.util.List;

public class CALCULADORAENVIAdto {

    private String id;
    private String versao;
    private String dataHoraEmissao;
    private int municipio;
    private String uf;
    private List<ItemEnvio> itens;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getDataHoraEmissao() {
        return dataHoraEmissao;
    }

    public void setDataHoraEmissao(String dataHoraEmissao) {
        this.dataHoraEmissao = dataHoraEmissao;
    }

    public int getMunicipio() {
        return municipio;
    }

    public void setMunicipio(int municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public List<ItemEnvio> getItens() {
        return itens;
    }

    public void setItens(List<ItemEnvio> itens) {
        this.itens = itens;
    }

    public static class ItemEnvio {

        private int numero;
        private String ncm;
        private String cst;
        private double baseCalculo;
        private double quantidade;
        private String unidade;
        private String cClassTrib;

        public int getNumero() {
            return numero;
        }

        public void setNumero(int numero) {
            this.numero = numero;
        }

        public String getNcm() {
            return ncm;
        }

        public void setNcm(String ncm) {
            this.ncm = ncm;
        }

        public String getCst() {
            return cst;
        }

        public void setCst(String cst) {
            this.cst = cst;
        }

        public double getBaseCalculo() {
            return baseCalculo;
        }

        public void setBaseCalculo(double baseCalculo) {
            this.baseCalculo = baseCalculo;
        }

        public double getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(double quantidade) {
            this.quantidade = quantidade;
        }

        public String getUnidade() {
            return unidade;
        }

        public void setUnidade(String unidade) {
            this.unidade = unidade;
        }

        public String getcClassTrib() {
            return cClassTrib;
        }

        public void setcClassTrib(String cClassTrib) {
            this.cClassTrib = cClassTrib;
        }
    }
}
