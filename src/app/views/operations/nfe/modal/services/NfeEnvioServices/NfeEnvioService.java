package app.views.operations.nfe.modal.services.NfeEnvioServices;

import app.controllers.NfeController;
import app.models.NFE;

import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.consReciNFe.TRetConsReciNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;

import lib.ConfiguracoesNfeIniciais;
import lib.MontaNfe;

import static br.com.swconsultoria.nfe.Nfe.consultaRecibo;
import static br.com.swconsultoria.nfe.Nfe.enviarNfe;
import static br.com.swconsultoria.nfe.Nfe.montaNfe;
import static br.com.swconsultoria.nfe.util.RetornoUtil.isRetornoAssincrono;
import static br.com.swconsultoria.nfe.util.RetornoUtil.validaAssincrono;
import static br.com.swconsultoria.nfe.util.RetornoUtil.validaSincrono;
import static br.com.swconsultoria.nfe.util.XmlNfeUtil.criaNfeProc;
import static br.com.swconsultoria.nfe.util.XmlNfeUtil.objectToXml;

import java.util.function.Consumer;
import javax.xml.bind.JAXBException;

public class NfeEnvioService {

    private Consumer<Object[]> tableRowListener;
    private Consumer<String> logListener;

    private String ano;
    private String serie;

    public void setTableRowListener(Consumer<Object[]> tableRowListener) {
        this.tableRowListener = tableRowListener;
    }

    public void setLogListener(Consumer<String> logListener) {
        this.logListener = logListener;
    }

    /**
     * Para montar a linha igual ao legado: {"", gAno, gSerie, gNNF, "..."}
     */
    public void setContext(String ano, String serie) {
        this.ano = ano;
        this.serie = serie;
    }

    private void log(String msg) {
        if (logListener != null) {
            logListener.accept(msg);
        } else {
            System.out.println(msg);
        }
    }

    private void row(String nNF, String mensagem) {
        if (tableRowListener != null) {
            tableRowListener.accept(new Object[]{"", ano, serie, nNF, mensagem});
        } else {
            log((ano == null ? "" : ano) + "/" + (serie == null ? "" : serie) + "/" + nNF + " - " + mensagem);
        }
    }

    private void emitResumo(String nNF, String cStat, String xMotivo) {
        row(nNF, cStat + "-" + xMotivo);
    }

    public ResultadoEnvioService enviar(String nNF) {

        ResultadoEnvioService resultado = new ResultadoEnvioService();

        try {

            System.setProperty("simularErroSefaz", "false");

            System.setProperty("simularRejeicaoSefaz", "false");

            // ? SIMULAÇÃO CONTROLADA
            if (Boolean.getBoolean("simularErroSefaz")) {
                throw new RuntimeException("Simulação de erro de comunicação.");
            }

            // ? SIMULAÇÃO DE REJEIÇÃO
            if (Boolean.getBoolean("simularRejeicaoSefaz")) {
                throw new NfeException("999 - Rejeição: Erro não catalogado - Simulação");
            }

            TEnviNFe enviNFe = montarNfe(nNF);

            System.out.println("XML ENVIO: " + objectToXml(enviNFe));

            TRetEnviNFe retEnviNFe = enviarParaSefaz(enviNFe); 

            emitResumo(nNF, retEnviNFe.getCStat(), retEnviNFe.getXMotivo());

            if (isRetornoAssincrono(retEnviNFe)) {
                return processarAssincrono(nNF, enviNFe, retEnviNFe);
            }

            return processarSincrono(nNF, enviNFe, retEnviNFe);

        } catch (NfeException e) {

            // REJEIÇÃO FISCAL (695 etc)
            resultado.setAutorizada(false);
            resultado.setRejeitada(true);
            resultado.setStatus("REJEICAO");
            resultado.setMotivo(e.getMessage());

            emitResumo(nNF, "REJEICAO", e.getMessage());

            return resultado;

        } catch (Exception e) {

            // ERRO TÉCNICO (timeout, SSL, etc)
            resultado.setAutorizada(false);
            resultado.setRejeitada(false); // IMPORTANTE
            resultado.setStatus("ERRO_COMUNICACAO");
            resultado.setMotivo(tratarMensagemTecnica(e));

            emitResumo(nNF, "ERRO_COMUNICACAO", resultado.getMotivo());

            return resultado;
        }
    }

    private TEnviNFe montarNfe(String nNF) throws Exception {
        ConfiguracoesNfeIniciais config = new ConfiguracoesNfeIniciais();
        TEnviNFe enviNFe = MontaNfe.main(nNF);

        enviNFe.setIndSinc(enviNFe.getNFe().size() > 1 ? "0" : "1");

        return montaNfe(config.iniciaConfiguracoes(), enviNFe, true);
    }

    private TRetEnviNFe enviarParaSefaz(TEnviNFe enviNFe) throws Exception {
        ConfiguracoesNfeIniciais config = new ConfiguracoesNfeIniciais();
        return enviarNfe(config.iniciaConfiguracoes(), enviNFe, DocumentoEnum.NFE);
    }

    // ==============================
    // ASSÍNCRONO (IDÊNTICO AO LEGADO)
    // ==============================
    private ResultadoEnvioService processarAssincrono(
            String nNF,
            TEnviNFe enviNFe,
            TRetEnviNFe retEnviNFe
    ) throws Exception {

        ResultadoEnvioService resultado = new ResultadoEnvioService();
        resultado.setAssincrono(true);
        resultado.setRetEnviNFe(retEnviNFe);

        ConfiguracoesNfeIniciais config = new ConfiguracoesNfeIniciais();

        String recibo = retEnviNFe.getInfRec().getNRec();
        int tentativa = 0;
        TRetConsReciNFe retornoNfe = null;

        while (tentativa < 4) {

            retornoNfe = consultaRecibo(config.iniciaConfiguracoes(), recibo, DocumentoEnum.NFE);

            String cStat = retornoNfe.getCStat();

            if (cStat.equals(StatusEnum.LOTE_EM_PROCESSAMENTO.getCodigo())
                    || cStat.equals(StatusEnum.LOTE_PROCESSADO.getCodigo())
                    || cStat.equals(StatusEnum.LOTE_RECEBIDO.getCodigo())) {

                // IGUAL AO LEGADO: atualiza NFE "Em Processamento na SEFAZ"
                NFE eNFE = new NFE();
                eNFE.setIde_nnf(nNF.trim());
                eNFE.setRetenvinfe_nrec(retEnviNFe.getInfRec().getNRec());
                eNFE.setInfnfe_statusnfe("Em Processamento na SEFAZ");
                NfeController.Update(eNFE);

                if (cStat.equals(StatusEnum.LOTE_PROCESSADO.getCodigo())) {
                    break;
                }

                Thread.sleep(5000);
                tentativa++;
            } else {
                break;
            }
        }

        System.out.println("XML RETORNO: " + objectToXml(retornoNfe));

        // VOLTA COMO ESTAVA: validaAssincrono(retornoNfe);
        validaAssincrono(retornoNfe);

        resultado.setRetConsReciNFe(retornoNfe);

        return analisarRetornoAssincrono(nNF, enviNFe, retornoNfe);
    }

    // ==============================
    // SÍNCRONO (IDÊNTICO AO LEGADO)
    // ==============================
    private ResultadoEnvioService processarSincrono(
            String nNF,
            TEnviNFe enviNFe,
            TRetEnviNFe retEnviNFe
    ) throws Exception {

        // VOLTA COMO ESTAVA: validaSincrono(retEnviNFe);
        validaSincrono(retEnviNFe);

        ResultadoEnvioService resultado = new ResultadoEnvioService();
        resultado.setAssincrono(false);
        resultado.setRetEnviNFe(retEnviNFe);

        String cStatProt = retEnviNFe.getProtNFe().getInfProt().getCStat();
        String xMotivoProt = retEnviNFe.getProtNFe().getInfProt().getXMotivo();

        // IGUAL AO LEGADO:
        // if (retEnviNFe.getCStat().equals("225") || !prot.cStat.equals(100)) rejeita
        if ("225".equals(retEnviNFe.getCStat())
                || !StatusEnum.AUTORIZADO.getCodigo().equals(cStatProt)) {

            resultado.setRejeitada(true);
            resultado.setStatus(cStatProt);
            resultado.setMotivo(xMotivoProt);

            // IGUAL AO LEGADO: TableResumoOperacoes(prot.cStat, prot.xMotivo)
            emitResumo(nNF, cStatProt, xMotivoProt);

            return resultado;
        }

        // AUTORIZADA (igual)
        resultado.setAutorizada(true);
        resultado.setStatus(cStatProt);
        resultado.setMotivo(xMotivoProt);
        resultado.setProtocolo(retEnviNFe.getProtNFe().getInfProt().getNProt());
        resultado.setDataAutorizacao(retEnviNFe.getProtNFe().getInfProt().getDhRecbto());
        resultado.setXmlAutorizado(criaNfeProc(enviNFe, retEnviNFe.getProtNFe()));

        // IGUAL AO LEGADO: TableResumoOperacoes(prot.cStat, prot.xMotivo)
        emitResumo(nNF, cStatProt, xMotivoProt);

        return resultado;
    }

    private ResultadoEnvioService analisarRetornoAssincrono(
            String nNF,
            TEnviNFe enviNFe,
            TRetConsReciNFe retorno
    ) throws NfeException, JAXBException {

        ResultadoEnvioService resultado = new ResultadoEnvioService();
        resultado.setAssincrono(true);
        resultado.setRetConsReciNFe(retorno);

        if (retorno == null || retorno.getProtNFe() == null || retorno.getProtNFe().isEmpty()) {
            resultado.setRejeitada(true);
            resultado.setMotivo("Retorno inválido da SEFAZ.");
            return resultado;
        }

        // === IGUAL AO LEGADO ===
        // 1) Checa retorno.getCStat() (mantido como estava, mesmo sendo estranho)
        if ("225".equals(retorno.getCStat())
                || !StatusEnum.AUTORIZADO.getCodigo().equals(retorno.getCStat())) {

            resultado.setRejeitada(true);
            resultado.setStatus(retorno.getCStat());
            resultado.setMotivo(retorno.getXMotivo());

            // IGUAL AO LEGADO:
            // TableResumoOperacoes(retorno.getCStat(), retorno.getXMotivo());
            emitResumo(nNF, retorno.getCStat(), retorno.getXMotivo());

            return resultado;
        }

        // 2) Checa protocolo
        String cStatProt = retorno.getProtNFe().get(0).getInfProt().getCStat();
        String xMotivoProt = retorno.getProtNFe().get(0).getInfProt().getXMotivo();

        if (!StatusEnum.AUTORIZADO.getCodigo().equals(cStatProt)) {

            resultado.setRejeitada(true);
            resultado.setStatus(cStatProt);
            resultado.setMotivo(xMotivoProt);

            // IGUAL AO LEGADO:
            emitResumo(nNF, cStatProt, xMotivoProt);

            return resultado;
        }

        // IGUAL AO LEGADO:
        // TableResumoOperacoes(prot.cStat, prot.xMotivo);
        emitResumo(nNF, cStatProt, xMotivoProt);

        // validaAssincrono já foi chamada antes, mas no legado ele "reimprime" depois.
        // Mantemos somente o resumo (mesmo efeito visual).
        // Se você quiser 100% idêntico até em duplicidade de linha, descomente a linha abaixo:
        //emitResumo(nNF, cStatProt, xMotivoProt);
        resultado.setAutorizada(true);
        resultado.setStatus(cStatProt);
        resultado.setMotivo(xMotivoProt);
        resultado.setProtocolo(retorno.getProtNFe().get(0).getInfProt().getNProt());
        resultado.setDataAutorizacao(retorno.getProtNFe().get(0).getInfProt().getDhRecbto());
        resultado.setXmlAutorizado(criaNfeProc(enviNFe, retorno.getProtNFe().get(0)));

        return resultado;
    }

    private String tratarMensagemTecnica(Exception e) {

        String msg = e.getMessage() == null ? "" : e.getMessage().toLowerCase();

        if (msg.contains("timed out")) {
            return "Tempo de conexão com a SEFAZ excedido.";
        }

        if (msg.contains("ssl")) {
            return "Falha de comunicação segura (SSL) com a SEFAZ.";
        }

        if (msg.contains("connection reset")) {
            return "Conexão com a SEFAZ foi encerrada.";
        }

        return "Falha de comunicação com a SEFAZ.";
    }
}
