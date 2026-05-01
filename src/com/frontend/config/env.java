package com.frontend.config;

public class env {

    private final String EMITENTE_DEFAULT = "01";
    private final String EMITENTE_CNPJ_DEFAULT = "52.703.063/0001-08";
    private final String HOST = "192.168.0.5";
    private final String PORT = "3333";
    private final String NODE_ENV = "development";
    private final String APP_NAME = "NFe-4.00RT";
    private final String APP_URL = "HTTP://";
    private final boolean CACHE_VIEWS = false;
    private final String APP_KEY = "";
    private final Number tpAMB = 1;
    private final Number procEmi = 0;
    private final String verProc = "1.1.7";
    private final String PATH_APP = "C:/COBRANCA-4.00/";
    private final String path_pastaAplicacao = "C:/NFe-4.00RT";
    private final String path_schemas = "C:/NFe-4.00RT/schemas";
    private final String path_exportxml = "\\\\" + HOST + "\\nfe\\autorizada\\xml\\rt";
    private final String path_exportpdf = "\\\\" + HOST + "\\nfe\\autorizada\\pdf\\rt";
    private final String path_importtxt = "\\\\" + HOST + "\\nfe\\exporta\\notas\\rt";
    private final String path_report = "C:/NFe-4.00RT/Relatorios/";
    private final String path_certificado = "C:/certificados/";
    private final String name_certificatoemitente = "camar.pfx";
    private final String password_certificatoemitente = "261042jp";
    private final String name_certificadocacerts = "nfecacerts.jks";

    private final String WS_HOMOLOGACAO = "https://homologacao.nfe.fazenda.sp.gov.br/ws/";
    private final String HOMOLOGACAO_DB_DRIVER = "org.postgresql.Driver";
    private final String HOMOLOGACAO_DB_CONNECTION = "jdbc:postgresql://";
    private final String HOMOLOGACAO_DB_HOST = "db_dados.postgresql.dbaas.com.br";
    private final String HOMOLOGACAO_DB_PORT = "5432";
    private final String HOMOLOGACAO_DB_DATABASE = "db_dados";
    private final String HOMOLOGACAO_DB_USER = "db_dados";
    private final String HOMOLOGACAO_DB_PASSWORD = "omv230888";

    private final String WS_PRODUCAO = "https://nfe.fazenda.sp.gov.br/ws/";
    private final String PRODUCAO_DB_DRIVER = "org.postgresql.Driver";
    private final String PRODUCAO_DB_CONNECTION = "jdbc:postgresql://";
    private final String PRODUCAO_DB_HOST = "192.168.0.3";
    private final String PRODUCAO_DB_PORT = "5887";
    private final String PRODUCAO_DB_DATABASE = "bs_dados";
    private final String PRODUCAO_DB_USER = "osvaldo";
    private final String PRODUCAO_DB_PASSWORD = "094567!@#$";

    private final String WS_PRODUCAO_5 = "https://nfe.fazenda.sp.gov.br/ws/";
    private final String PRODUCAO_5_DB_DRIVER = "org.postgresql.Driver";
    private final String PRODUCAO_5_DB_CONNECTION = "jdbc:postgresql://";
    private final String PRODUCAO_5_DB_HOST = "192.168.0.5";
    private final String PRODUCAO_5_DB_PORT = "5887";
    private final String PRODUCAO_5_DB_DATABASE = "bs_dados";
    private final String PRODUCAO_5_DB_USER = "osvaldo";
    private final String PRODUCAO_5_DB_PASSWORD = "094567!@#$";

    private final String PRODUCAO_SITE_DB_DRIVER = "org.postgresql.Driver";
    private final String PRODUCAO_SITE_DB_CONNECTION = "jdbc:postgresql://";
    private final String PRODUCAO_SITE_DB_HOST = "bs_dados.postgresql.dbaas.com.br";
    private final String PRODUCAO_SITE_DB_PORT = "5432";
    private final String PRODUCAO_SITE_DB_DATABASE = "bs_dados";
    private final String PRODUCAO_SITE_DB_USER = "bs_dados";
    private final String PRODUCAO_SITE_DB_PASSWORD = "omv230888";

    private final String PRODUCAO_CLOUD_DB_DRIVER = "org.postgresql.Driver";
    private final String PRODUCAO_CLOUD_DB_CONNECTION = "jdbc:postgresql://";
    private final String PRODUCAO_CLOUD_DB_HOST = "191.252.65.147";
    private final String PRODUCAO_CLOUD_DB_PORT = "5432";
    private final String PRODUCAO_CLOUD_DB_DATABASE = "bs_dados";
    private final String PRODUCAO_CLOUD_DB_USER = "osvaldo";
    private final String PRODUCAO_CLOUD_DB_PASSWORD = "094567!@#$";

    private final String Versao = "4.00";
    private final String CUF = "35";
    private final String PrefixInFiles = "camar_";
    private final String infRespTec_CNPJ = "52703063000108";
    private final String infRespTec_XContato = "Osvaldo Vidoy";
    private final String infRespTec_Email = "osvaldo@camarplasticos.com.br";
    private final String infRespTec_Fone = "1930264100";

    public String getCUF() {
        return CUF;
    }

    public String getPrefixInFiles() {
        return PrefixInFiles;
    }

    public String getEMITENTE_DEFAULT() {
        return EMITENTE_DEFAULT;
    }

    public String getEMITENTE_CNPJ_DEFAULT() {
        return EMITENTE_CNPJ_DEFAULT;
    }

    public String getHOST() {
        return HOST;
    }

    public String getPORT() {
        return PORT;
    }

    public String getNODE_ENV() {
        return NODE_ENV;
    }

    public String getAPP_NAME() {
        return APP_NAME;
    }

    public String getPATH_APP() {
        return PATH_APP;
    }

    public String getAPP_URL() {
        return APP_URL;
    }

    public boolean isCACHE_VIEWS() {
        return CACHE_VIEWS;
    }

    public String getAPP_KEY() {
        return APP_KEY;
    }

    public String getHOMOLOGACAO_DB_DRIVER() {
        return HOMOLOGACAO_DB_DRIVER;
    }

    public String getHOMOLOGACAO_DB_CONNECTION() {
        return HOMOLOGACAO_DB_CONNECTION;
    }

    public String getHOMOLOGACAO_DB_HOST() {
        return HOMOLOGACAO_DB_HOST;
    }

    public String getHOMOLOGACAO_DB_PORT() {
        return HOMOLOGACAO_DB_PORT;
    }

    public String getHOMOLOGACAO_DB_USER() {
        return HOMOLOGACAO_DB_USER;
    }

    public String getHOMOLOGACAO_DB_PASSWORD() {
        return HOMOLOGACAO_DB_PASSWORD;
    }

    public String getHOMOLOGACAO_DB_DATABASE() {
        return HOMOLOGACAO_DB_DATABASE;
    }

    public String getPRODUCAO_DB_DRIVER() {
        return PRODUCAO_DB_DRIVER;
    }

    public String getPRODUCAO_DB_CONNECTION() {
        return PRODUCAO_DB_CONNECTION;
    }

    public String getPRODUCAO_DB_HOST() {
        return PRODUCAO_DB_HOST;
    }

    public String getPRODUCAO_DB_PORT() {
        return PRODUCAO_DB_PORT;
    }

    public String getPRODUCAO_DB_USER() {
        return PRODUCAO_DB_USER;
    }

    public String getPRODUCAO_DB_PASSWORD() {
        return PRODUCAO_DB_PASSWORD;
    }

    public String getPRODUCAO_DB_DATABASE() {
        return PRODUCAO_DB_DATABASE;
    }

    public String getWS_PRODUCAO_5() {
        return WS_PRODUCAO_5;
    }

    public String getPRODUCAO_5_DB_DRIVER() {
        return PRODUCAO_5_DB_DRIVER;
    }

    public String getPRODUCAO_5_DB_CONNECTION() {
        return PRODUCAO_5_DB_CONNECTION;
    }

    public String getPRODUCAO_5_DB_HOST() {
        return PRODUCAO_5_DB_HOST;
    }

    public String getPRODUCAO_5_DB_PORT() {
        return PRODUCAO_5_DB_PORT;
    }

    public String getPRODUCAO_5_DB_DATABASE() {
        return PRODUCAO_5_DB_DATABASE;
    }

    public String getPRODUCAO_5_DB_USER() {
        return PRODUCAO_5_DB_USER;
    }

    public String getPRODUCAO_5_DB_PASSWORD() {
        return PRODUCAO_5_DB_PASSWORD;
    }

    public String getPRODUCAO_SITE_DB_DRIVER() {
        return PRODUCAO_SITE_DB_DRIVER;
    }

    public String getPRODUCAO_SITE_DB_CONNECTION() {
        return PRODUCAO_SITE_DB_CONNECTION;
    }

    public String getPRODUCAO_SITE_DB_HOST() {
        return PRODUCAO_SITE_DB_HOST;
    }

    public String getPRODUCAO_SITE_DB_PORT() {
        return PRODUCAO_SITE_DB_PORT;
    }

    public String getPRODUCAO_SITE_DB_USER() {
        return PRODUCAO_SITE_DB_USER;
    }

    public String getPRODUCAO_SITE_DB_PASSWORD() {
        return PRODUCAO_SITE_DB_PASSWORD;
    }

    public String getPRODUCAO_SITE_DB_DATABASE() {
        return PRODUCAO_SITE_DB_DATABASE;
    }

    public String getPRODUCAO_CLOUD_DB_DRIVER() {
        return PRODUCAO_CLOUD_DB_DRIVER;
    }

    public String getPRODUCAO_CLOUD_DB_CONNECTION() {
        return PRODUCAO_CLOUD_DB_CONNECTION;
    }

    public String getPRODUCAO_CLOUD_DB_HOST() {
        return PRODUCAO_CLOUD_DB_HOST;
    }

    public String getPRODUCAO_CLOUD_DB_PORT() {
        return PRODUCAO_CLOUD_DB_PORT;
    }

    public String getPRODUCAO_CLOUD_DB_USER() {
        return PRODUCAO_CLOUD_DB_USER;
    }

    public String getPRODUCAO_CLOUD_DB_PASSWORD() {
        return PRODUCAO_CLOUD_DB_PASSWORD;
    }

    public String getPRODUCAO_CLOUD_DB_DATABASE() {
        return PRODUCAO_CLOUD_DB_DATABASE;
    }

    public Number gettpAMB() {
        return tpAMB;
    }

    public Number getprocEmi() {
        return procEmi;
    }

    public String getverProc() {
        return verProc;
    }

    public String getpath_pastaAplicacao() {
        return path_pastaAplicacao;
    }

    public String getpath_schemas() {
        return path_schemas;
    }

    public String getpath_exportxml() {
        return path_exportxml;
    }

    public String getpath_exportpdf() {
        return path_exportpdf;
    }

    public String getpath_importtxt() {
        return path_importtxt;
    }

    public String getpath_certificado() {
        return path_certificado;
    }

    public String getpath_report() {
        return path_report;
    }

    public String getname_certificatoemitente() {
        return name_certificatoemitente;
    }

    public String getpassword_certificatoemitente() {
        return password_certificatoemitente;
    }

    public String getname_certificadocacerts() {
        return name_certificadocacerts;
    }

    public String getWS_HOMOLOGACAO() {
        return WS_HOMOLOGACAO;
    }

    public String getWS_PRODUCAO() {
        return WS_PRODUCAO;
    }

    public String getinfRespTec_CNPJ() {
        return infRespTec_CNPJ;
    }

    public String getinfRespTec_XContato() {
        return infRespTec_XContato;
    }

    public String getinfRespTec_Email() {
        return infRespTec_Email;
    }

    public String getinfRespTec_Fone() {
        return infRespTec_Fone;
    }

    public String getVersao() {
        return Versao;
    }
}
