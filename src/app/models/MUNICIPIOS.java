package app.models;

public class MUNICIPIOS {

    public MUNICIPIOS() {
    }

    public MUNICIPIOS eMUNICIPIOS;

    public MUNICIPIOS(MUNICIPIOS eMUNICIPIOS) {
        this.eMUNICIPIOS = eMUNICIPIOS;
    }
    private String mun_codigo;
    private String mun_descricao;

    public String getMun_codigo() {
        return mun_codigo;
    }

    public void setMun_codigo(String mun_codigo) {
        this.mun_codigo = mun_codigo;
    }

    public String getMun_descricao() {
        return mun_descricao;
    }

    public void setMun_descricao(String mun_descricao) {
        this.mun_descricao = mun_descricao;
    }
}
