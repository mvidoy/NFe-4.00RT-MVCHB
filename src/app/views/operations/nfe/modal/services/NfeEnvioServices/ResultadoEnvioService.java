package app.views.operations.nfe.modal.services.NfeEnvioServices;

import br.com.swconsultoria.nfe.schema_4.consReciNFe.TRetConsReciNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;

public class ResultadoEnvioService {

    private boolean autorizada;
    private boolean rejeitada;
    private boolean assincrono;

    private String status;
    private String motivo;
    private String protocolo;
    private String xmlAutorizado;
    private String dataAutorizacao;

    // ? Objetos originais da SEFAZ (para equivalência total)
    private TRetEnviNFe retEnviNFe;
    private TRetConsReciNFe retConsReciNFe;

    public boolean isAutorizada() {
        return autorizada;
    }

    public void setAutorizada(boolean autorizada) {
        this.autorizada = autorizada;
    }

    public boolean isRejeitada() {
        return rejeitada;
    }

    public void setRejeitada(boolean rejeitada) {
        this.rejeitada = rejeitada;
    }

    public boolean isAssincrono() {
        return assincrono;
    }

    public void setAssincrono(boolean assincrono) {
        this.assincrono = assincrono;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getXmlAutorizado() {
        return xmlAutorizado;
    }

    public void setXmlAutorizado(String xmlAutorizado) {
        this.xmlAutorizado = xmlAutorizado;
    }

    public String getDataAutorizacao() {
        return dataAutorizacao;
    }

    public void setDataAutorizacao(String dataAutorizacao) {
        this.dataAutorizacao = dataAutorizacao;
    }

    public TRetEnviNFe getRetEnviNFe() {
        return retEnviNFe;
    }

    public void setRetEnviNFe(TRetEnviNFe retEnviNFe) {
        this.retEnviNFe = retEnviNFe;
    }

    public TRetConsReciNFe getRetConsReciNFe() {
        return retConsReciNFe;
    }

    public void setRetConsReciNFe(TRetConsReciNFe retConsReciNFe) {
        this.retConsReciNFe = retConsReciNFe;
    }
}
