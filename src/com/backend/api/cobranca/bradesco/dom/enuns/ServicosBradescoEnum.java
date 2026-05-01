package com.backend.api.cobranca.bradesco.dom.enuns;

public enum ServicosBradescoEnum {

    // Chamadas públicas (lógicas)
    CLIENT_ID,
    CLIENT_SECRET,
    CERTIFICADOPFX,
    SENHADOCERTIFICADO,
    TOKENBRADESCO,
    REGISTRODEBOLETOBRADESCO,
    CONSULTADEBOLETOBRADESCO,
    BAIXADEBOLETOBRADESCO,
    CONSULTALIQUIDACAOBOLETOBRADESCO,
    ALTERABOLETOBRADESCO,
    // URLs internas (não usar diretamente fora do enum)
    //24/10/2025 - Nome da Credencial = COBRANÇA
    //Client_ID_SANDBOX("c22fe833-707d-41fa-92f2-b3bd9cb94419"),
    //Client_Secret_SANDBOX("4f388d4c-e408-4ca8-8831-fc1ce7de5daf"),
    //23/02/2026 - Nome da Credencial = Credencial Homologação - 23/02/2026
    Client_ID_SANDBOX("40ba65aa-68c9-4906-9d9e-f15fc4c2de0e"),
    Client_Secret_SANDBOX("ffd7de78-47bb-4b54-91d0-487581108e88"),
    //29/10/2025 - Nome da Credencial = Cobranca Producao - 29/10/2025
    //Client_ID_PRODUCAO("958bbb62-002d-407a-9970-79d3fa95dabd"),
    //Client_Secret_PRODUCAO("77f23512-67e7-44cd-b646-6e8bfa718362"),
     //21/02/2026 - Nome da Credencial = Cobranca Producao - 21/02/2026
    Client_ID_PRODUCAO("4e889f01-e7ee-4def-98b6-9f11ae971885"),
    Client_Secret_PRODUCAO("841ba636-c69b-487e-90f7-2a9a2760485a"),
    CERTIFICADOBRADESCOPFXDOSANDBOX("C:\\certificados\\bradescoHomologacao.pfx"),
    CERTIFICADOBRADESCOPFXPRODUCAO("C:\\certificados\\bradescoProducao.pfx"),
    SENHACERTIFICAPFXDOSANDBOX("261042jp"),
    SENHACERTIFICAPFXPRODUCAO("261042jp"),
    TOKENBRADESCOSANDBOX("https://openapisandbox.prebanco.com.br/auth/server-mtls/v2/token"),
    TOKENBRADESCOPRODUCAO("https://openapi.bradesco.com.br/auth/server-mtls/v2/token"),
    REGISTRODEBOLETOBRADESCOSANDBOX("https://openapisandbox.prebanco.com.br/boleto/cobranca-registro/v1/cobranca"),
    REGISTRODEBOLETOBRADESCOPRODUCAO("https://openapi.bradesco.com.br/boleto/cobranca-registro/v1/cobranca"),
    CONSULTADEBOLETOBRADESCOSANDBOX("https://openapisandbox.prebanco.com.br/boleto/cobranca-consulta/v1/consultar"),
    CONSULTADEBOLETOBRADESCOPRODUCAO("https://openapi.bradesco.com.br/boleto/cobranca-consulta/v1/consultar"),
    BAIXADEBOLETOBRADESCOSANDBOX("https://openapisandbox.prebanco.com.br/boleto/cobranca-baixa/v1/baixar"),
    BAIXADEBOLETOBRADESCOPRODUCAO("https://openapi.bradesco.com.br/boleto/cobranca-baixa/v1/baixar"),
    CONSULTALISTALIQUIDICAODEBOLETOBRADESCOSANDBOX("https://openapisandbox.prebanco.com.br/boleto/cobranca-lista/v1/listar"),
    CONSULTALISTALIQUIDICAODEBOLETOBRADESCOPRODUCAO("https://openapi.bradesco.com.br/boleto/cobranca-lista/v1/listar"),
    ALTERABOLETOBRADESCOSANDBOX("https://openapisandbox.prebanco.com.br/boleto/cobranca-altera/v1/alterar"),
    ALTERABOLETOBRADESCOPRODUCAO("https://openapi.bradesco.com.br/boleto/cobranca-altera/v1/alterar");

    private final String url;

    ServicosBradescoEnum() {
        this.url = null;
    }

    ServicosBradescoEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getResolvedValue() {
        return this.resolveAmbiente().getUrl();
    }

    public ServicosBradescoEnum resolveAmbiente() {
        String tpAmb = System.getProperty("tpAmb"); // default para homologação
        boolean isHomologacao = tpAmb.equals("2");

        switch (this) {
            case CLIENT_ID:
                return isHomologacao ? Client_ID_SANDBOX : Client_ID_PRODUCAO;
            case CLIENT_SECRET:
                return isHomologacao ? Client_Secret_SANDBOX : Client_Secret_PRODUCAO;
            case TOKENBRADESCO:
                return isHomologacao ? TOKENBRADESCOSANDBOX : TOKENBRADESCOPRODUCAO;
            case REGISTRODEBOLETOBRADESCO:
                return isHomologacao ? REGISTRODEBOLETOBRADESCOSANDBOX : REGISTRODEBOLETOBRADESCOPRODUCAO;
            case CONSULTADEBOLETOBRADESCO:
                return isHomologacao ? CONSULTADEBOLETOBRADESCOSANDBOX : CONSULTADEBOLETOBRADESCOPRODUCAO;
            case BAIXADEBOLETOBRADESCO:
                return isHomologacao ? BAIXADEBOLETOBRADESCOSANDBOX : BAIXADEBOLETOBRADESCOPRODUCAO;
            case CONSULTALIQUIDACAOBOLETOBRADESCO:
                return isHomologacao ? CONSULTALISTALIQUIDICAODEBOLETOBRADESCOSANDBOX : CONSULTALISTALIQUIDICAODEBOLETOBRADESCOPRODUCAO;
            case ALTERABOLETOBRADESCO:
                return isHomologacao ? ALTERABOLETOBRADESCOSANDBOX : ALTERABOLETOBRADESCOPRODUCAO;
            case CERTIFICADOPFX:
                return isHomologacao ? CERTIFICADOBRADESCOPFXDOSANDBOX : CERTIFICADOBRADESCOPFXPRODUCAO;
            case SENHADOCERTIFICADO:
                return isHomologacao ? SENHACERTIFICAPFXDOSANDBOX : SENHACERTIFICAPFXPRODUCAO;
            default:
                throw new UnsupportedOperationException("Serviço não reconhecido ou não configurado para ambiente: " + this.name());
        }
    }

    public static String getCertificadoPath() {
        return CERTIFICADOPFX.resolveAmbiente().getUrl();
    }

    public static String getCertificadoSenha() {
        return SENHADOCERTIFICADO.resolveAmbiente().getUrl();
    }
}
