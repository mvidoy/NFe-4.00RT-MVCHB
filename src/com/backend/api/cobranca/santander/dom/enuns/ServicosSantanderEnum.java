package com.backend.api.cobranca.santander.dom.enuns;

public enum ServicosSantanderEnum {

    // Chamadas públicas (lógicas)
    CLIENT_ID,
    CLIENT_SECRET,
    CERTIFICADOPFX,
    CERTIFICADOSANTANDERJKSPRODUCAO,
    SENHADOCERTIFICADO,
    TOKENSANTANDER,
    REGISTRODEBOLETOSANTANDER,
    CONSULTADEBOLETOSANTANDER,
    BAIXADEBOLETOSANTANDER,
    CONSULTALIQUIDACAOBOLETOSANTANDER,
    ALTERABOLETOSANTANDER,
    WORKSPACESANDBOX("579d0608-188d-4f32-b301-da4c388a673b"),
    WORKSPACEPRODUCAO("496a01c5-f6b8-4a8a-83a0-c75573d64a96"),
    Client_ID_SANDBOX("l7GjjO9OOiBc9IED7jK4G3GA6vMXuiGt"),
    Client_Secret_SANDBOX("gvUA66C8lb7Mt7Ps"),
    Client_ID_PRODUCAO("Wrt4drnT19wTMFDA5nHkANesV9or4tzo"),
    Client_Secret_PRODUCAO("ZAz3ETr4AaUxfWwd"),
    CERTIFICADOSANTANDERPFXDOSANDBOX("C:\\certificados\\santanderHomologacao.pfx"),
    CERTIFICADOSANTANDERPFXPRODUCAO("C:\\certificados\\santanderProducao.pfx"),
    CERTIFICADOSANTANDERjksPRODUCAO("C:\\certificados\\santanderProducao.jks"),
    SENHACERTIFICAPFXDOSANDBOX("261042jp"),
    SENHACERTIFICAPFXPRODUCAO("261042jp"),
    TOKENSANTANDERSANDBOX("https://trust-sandbox.api.santander.com.br/auth/oauth/v2/token"),
    TOKENSANTANDERPRODUCAO("https://trust-open.api.santander.com.br/auth/oauth/v2/token"),
    // Registro de boletos
    REGISTRODEBOLETOSANTANDERSANDBOX("https://trust-sandbox.api.santander.com.br/collection_bill_management/v2/workspaces/"
            + WORKSPACESANDBOX.url + "/bank_slips"),
    REGISTRODEBOLETOSANTANDERPRODUCAO("https://trust-open.api.santander.com.br/collection_bill_management/v2/workspaces/"
            + WORKSPACEPRODUCAO.url + "/bank_slips"),
    // Consulta de boletos (por nosso número)
    CONSULTADEBOLETOSANTANDERSANDBOX("https://trust-sandbox.api.santander.com.br/collection_bill_management/v2/bills"),
    CONSULTADEBOLETOSANTANDERPRODUCAO("https://trust-open.api.santander.com.br/collection_bill_management/v2/bills"),
    // Baixa de boletos
    BAIXADEBOLETOSANTANDERSANDBOX("https://trust-sandbox.api.santander.com.br/collection_bill_management/v2/workspaces/"
            + WORKSPACESANDBOX.url + "/bank_slips"),
    BAIXADEBOLETOSANTANDERPRODUCAO("https://trust-open.api.santander.com.br/collection_bill_management/v2/workspaces/"
            + WORKSPACEPRODUCAO.url + "/bank_slips"),
    // Lista de liquidação
    //CONSULTALISTALIQUIDICAODEBOLETOSANTANDERSANDBOX("https://trust-sandbox.api.santander.com.br/collection_bill_management/v2/workspaces/" + WORKSPACESANDBOX + "/bank_slips"),
    //CONSULTALISTALIQUIDICAODEBOLETOSANTANDERPRODUCAO("https://trust-open.api.santander.com.br/collection_bill_management/v2/workspaces/" + WORKSPACEPRODUCAO + "/bank_slips"),
    CONSULTALISTALIQUIDICAODEBOLETOSANTANDERSANDBOX(
            "https://trust-sandbox.api.santander.com.br/collection_bill_management/v2/bills/"),
    CONSULTALISTALIQUIDICAODEBOLETOSANTANDERPRODUCAO("https://trust-open.api.santander.com.br/collection_bill_management/v2/bills/"),
    // Alteração de boletos
    ALTERABOLETOSANTANDERSANDBOX("https://trust-sandbox.api.santander.com.br/collection_bill_management/v2/workspaces/"
            + WORKSPACESANDBOX.url + "/bank_slips"),
    ALTERABOLETOSANTANDERPRODUCAO("https://trust-open.api.santander.com.br/collection_bill_management/v2/workspaces/"
            + WORKSPACEPRODUCAO.url + "/bank_slips");

    private final String url;

    ServicosSantanderEnum() {
        this.url = null;
    }

    ServicosSantanderEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getResolvedValue() {
        return this.resolveAmbiente().getUrl();
    }

    public ServicosSantanderEnum resolveAmbiente() {
        String tpAmb = System.getProperty("tpAmb");
        boolean isHomologacao = "2".equals(tpAmb);
        switch (this) {
            case CLIENT_ID:
                return isHomologacao ? Client_ID_SANDBOX : Client_ID_PRODUCAO;
            case CLIENT_SECRET:
                return isHomologacao ? Client_Secret_SANDBOX : Client_Secret_PRODUCAO;
            case CERTIFICADOSANTANDERJKSPRODUCAO:
                return CERTIFICADOSANTANDERjksPRODUCAO;
            case TOKENSANTANDER:
                return isHomologacao ? TOKENSANTANDERSANDBOX : TOKENSANTANDERPRODUCAO;
            case REGISTRODEBOLETOSANTANDER:
                return isHomologacao ? REGISTRODEBOLETOSANTANDERSANDBOX : REGISTRODEBOLETOSANTANDERPRODUCAO;
            case CONSULTADEBOLETOSANTANDER:
                return isHomologacao ? CONSULTADEBOLETOSANTANDERSANDBOX : CONSULTADEBOLETOSANTANDERPRODUCAO;
            case BAIXADEBOLETOSANTANDER:
                return isHomologacao ? BAIXADEBOLETOSANTANDERSANDBOX : BAIXADEBOLETOSANTANDERPRODUCAO;
            case CONSULTALIQUIDACAOBOLETOSANTANDER:
                return isHomologacao ? CONSULTALISTALIQUIDICAODEBOLETOSANTANDERSANDBOX : CONSULTALISTALIQUIDICAODEBOLETOSANTANDERPRODUCAO;
            case ALTERABOLETOSANTANDER:
                return isHomologacao ? ALTERABOLETOSANTANDERSANDBOX : ALTERABOLETOSANTANDERPRODUCAO;
            case CERTIFICADOPFX:
                return isHomologacao ? CERTIFICADOSANTANDERPFXDOSANDBOX : CERTIFICADOSANTANDERPFXPRODUCAO;
            case SENHADOCERTIFICADO:
                return isHomologacao ? SENHACERTIFICAPFXDOSANDBOX : SENHACERTIFICAPFXPRODUCAO;
            default:
                throw new UnsupportedOperationException("Serviço não reconhecido ou não configurado: " + this.name());
        }
    }

    public static String getCertificadoPath() {
        return CERTIFICADOPFX.resolveAmbiente().getUrl();
    }

    public static String getCertificadoSenha() {
        return SENHADOCERTIFICADO.resolveAmbiente().getUrl();
    }
}
