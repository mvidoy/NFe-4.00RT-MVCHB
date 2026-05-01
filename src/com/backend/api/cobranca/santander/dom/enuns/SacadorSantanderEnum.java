package com.backend.api.cobranca.santander.dom.enuns;

import br.com.ovconsultoria.boleto.bancos.gerador.GeradorDeDigitoSantander;

public enum SacadorSantanderEnum {
    EMPRESA_NOME("CAMAR PLASTICOS LTDA"),
    EMPRESA_CNPJ("052703063000108"),
    EMPRESA_CNPJ_14("52703063000108"),
    BANCO("033"),
    AGENCIA("4502"),
    AGENCIA_DV("0"),
    CONTA_COBRANCA("013000697"),
    CONTA_DV("9"),
    CONVENIO("734843");
    
    private final String valor;

    SacadorSantanderEnum(String valor) {
        this.valor = valor;
    }

    public String get() {
        return valor;
    }

    public long getAsLong() {
        return Long.parseLong(valor);
    }

    /**
     * Código de Transmissão Santander (CNAB240) Agência + Conta (sem DV) +
     * Convênio
     */
    public static String getCodigoTransmissao() {
        return AGENCIA.get()
                + CONTA_COBRANCA.get()
                + CONVENIO.get();
    }

    /**
     * BeneficiaryCode da API (quando exigido) Agência + Conta (sem DV)
     */
    public static String getBeneficiaryCode() {
        return AGENCIA.get() + CONTA_COBRANCA.get();
    }

    public static String gerarNossoNumeroComDV(String numDoc) throws Exception {
        if (numDoc == null) {
            throw new Exception("PAR_NUMDOC está nulo");
        }

        String numeros = numDoc.replaceAll("\\D", "");

        if (numeros.isEmpty()) {
            throw new Exception("PAR_NUMDOC inválido: " + numDoc);
        }

        if (numeros.length() > 12) {
            numeros = numeros.substring(0, 12);
        } else {
            numeros = String.format("%012d", Long.parseLong(numeros));
        }

        GeradorDeDigitoSantander gerador = new GeradorDeDigitoSantander();
        String dv = gerador.calculaDVNossoNumero(numeros);

        return numeros + dv;
    }
}
