package com.backend.api.cobranca.bradesco.dom.enuns;

public enum SacadorBradescoEnum {

    EMPRESA_NOME("CAMAR PLASTICOS LTDA"),
    EMPRESA_CNPJ("052703063000108"),
    EMPRESA_CNPJ_14("52703063000108"),
    EMPRESA_ENDERECO("RUA DA BLENDA"),
    EMPRESA_NUMERO("206"),
    EMPRESA_BAIRRO("JARDIM PÉROLA"),
    EMPRESA_CIDADE("SANTA BARBARA D'OESTE"),
    EMPRESA_UF("SP"),
    EMPRESA_CEP("13454189"),
    EMPRESA_FONE("1930264100"),
    CACTABAN_BRADESCO("1"),
    CACTABAN_SANTANDER("5"),
    AGENCIA("3366"),
    CONTA("0047654");

    //AGENCIA("3371"),
    //CONTA("0477654");
    private final String valor;

    SacadorBradescoEnum(String valor) {
        this.valor = valor;
    }

    public String getCodigo() {
        return valor;
    }

    public String get() {
        return valor;
    }

    public long getAsLong() {
        return Long.parseLong(valor);
    }

    public int getAsInt() {
        return Integer.parseInt(valor);
    }

    /**
     * Retorna a negociação = Agência + Conta (Ex.: 33660047654)
     */
    public static long getNegociacao() {
        return Long.parseLong(
                AGENCIA.get() + CONTA.get()
        );
    }
}
