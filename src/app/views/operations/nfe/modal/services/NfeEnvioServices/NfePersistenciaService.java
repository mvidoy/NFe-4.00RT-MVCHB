package app.views.operations.nfe.modal.services.NfeEnvioServices;

import app.controllers.NfeController;
import app.controllers.NfeInfProtController;
import app.models.NFE;
import app.models.NFE_INFPROT;

import br.com.swconsultoria.nfe.schema_4.consReciNFe.TRetConsReciNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;

import java.util.function.Consumer;

public class NfePersistenciaService {

    private Consumer<Object[]> tableRowListener;
    private String ano;
    private String serie;

    public void setTableRowListener(Consumer<Object[]> tableRowListener) {
        this.tableRowListener = tableRowListener;
    }

    public void setContext(String ano, String serie) {
        this.ano = ano;
        this.serie = serie;
    }

    private void row(String nNF, String mensagem) {
        if (tableRowListener != null) {
            tableRowListener.accept(new Object[]{"", ano, serie, nNF, mensagem});
        }
    }

    public void persistir(String nNF, ResultadoEnvioService resultado) throws InstantiationException, IllegalAccessException {

        if (resultado == null) {
            return;
        }

        if (resultado == null) {
            return;
        }

        // ? BLOQUEIO TOTAL PARA ERRO TÉCNICO
        // Se não foi autorizada e não foi rejeitada,
        // significa erro de comunicação.
        if (!resultado.isAutorizada() && !resultado.isRejeitada()) {
            return;
        }

        if (resultado.isAssincrono()) {
            persistirAssincrono(nNF, resultado.getRetConsReciNFe(), resultado.getXmlAutorizado(), resultado.isAutorizada(), resultado.isRejeitada());
        } else {
            persistirSincrono(nNF, resultado.getRetEnviNFe(), resultado.getXmlAutorizado(), resultado.isAutorizada(), resultado.isRejeitada());
        }
    }

    // =====================================
    // ASSÍNCRONO – CAMPOS COMO NO LEGADO
    // =====================================
    private void persistirAssincrono(
            String nNF,
            TRetConsReciNFe retornoNfe,
            String xmlAutorizado,
            boolean autorizada,
            boolean rejeitada
    ) throws InstantiationException, IllegalAccessException {

        if (retornoNfe == null
                || retornoNfe.getProtNFe() == null
                || retornoNfe.getProtNFe().isEmpty()) {
            return;
        }

        // Legado fazia Delete antes
        NfeInfProtController.Delete(nNF.trim());

        String cStatProt = retornoNfe.getProtNFe().get(0).getInfProt().getCStat();

        // Legado fazia Create
        NfeInfProtController.Create(nNF.trim(), cStatProt);

        // Preenche NFE_INFPROT (campos completos)
        NFE_INFPROT eNFE_INFPROT = new NFE_INFPROT();
        eNFE_INFPROT.setProtnfe_nnf(nNF.trim());
        eNFE_INFPROT.setProtnfe_versao(retornoNfe.getProtNFe().get(0).getVersao());
        eNFE_INFPROT.setProtnfe_sequencia("1");
        eNFE_INFPROT.setInfprot_cstat(cStatProt);
        eNFE_INFPROT.setInfprot_chnfe(retornoNfe.getProtNFe().get(0).getInfProt().getChNFe());
        eNFE_INFPROT.setInfprot_dhrecbto(retornoNfe.getProtNFe().get(0).getInfProt().getDhRecbto()
                .replace("T", " ").replace("-03:00", ""));
        eNFE_INFPROT.setInfprot_nid(retornoNfe.getProtNFe().get(0).getInfProt().getId());

        // No legado teve uma linha errada em um ponto (setInfprot_nprot(retornoNfe.getNRec()))
        // mas o correto e mais usado é NProt.
        eNFE_INFPROT.setInfprot_nprot(retornoNfe.getProtNFe().get(0).getInfProt().getNProt());

        eNFE_INFPROT.setInfprot_tpamb(retornoNfe.getProtNFe().get(0).getInfProt().getTpAmb());
        eNFE_INFPROT.setInfprot_veraplic(retornoNfe.getProtNFe().get(0).getInfProt().getVerAplic());
        eNFE_INFPROT.setInfprot_xmotivo(retornoNfe.getProtNFe().get(0).getInfProt().getXMotivo()
                .replace("Autorizado o uso da NF-e", "Autorização de Uso"));

        NfeInfProtController.Update(eNFE_INFPROT);

        // Atualiza NFE (Autorizada/Rejeitada)
        NFE eNFE = new NFE();
        eNFE.setIde_nnf(nNF.trim());

        if (rejeitada && !autorizada) {
            eNFE.setInfnfe_statusnfe("Rejeitada");
            NfeController.Update(eNFE);
            return;
        }

        if (autorizada) {
            eNFE.setInfnfe_statusnfe("Autorizada");
            eNFE.setInfprot_nprot(retornoNfe.getProtNFe().get(0).getInfProt().getNProt());
            eNFE.setInfprot_dhrecbto(retornoNfe.getProtNFe().get(0).getInfProt().getDhRecbto()
                    .replace("T", " ").replace("-03:00", ""));
            eNFE.setXml_autorizado(xmlAutorizado);
            NfeController.Update(eNFE);
        }
    }

    // =====================================
    // SÍNCRONO – CAMPOS COMO NO LEGADO
    // =====================================
    private void persistirSincrono(
            String nNF,
            TRetEnviNFe retEnviNFe,
            String xmlAutorizado,
            boolean autorizada,
            boolean rejeitada
    ) throws InstantiationException, IllegalAccessException {

        if (retEnviNFe == null || retEnviNFe.getProtNFe() == null) {
            return;
        }

        // Legado fazia Create (não fazia Delete aqui, mas podemos manter compatível com o fluxo geral)
        String cStatProt = retEnviNFe.getProtNFe().getInfProt().getCStat();
        NfeInfProtController.Create(nNF.trim(), cStatProt);

        NFE_INFPROT eNFE_INFPROT = new NFE_INFPROT();
        eNFE_INFPROT.setProtnfe_nnf(nNF.trim());
        eNFE_INFPROT.setProtnfe_versao(retEnviNFe.getProtNFe().getVersao());
        eNFE_INFPROT.setProtnfe_sequencia("1");
        eNFE_INFPROT.setInfprot_cstat(cStatProt);
        eNFE_INFPROT.setInfprot_chnfe(retEnviNFe.getProtNFe().getInfProt().getChNFe());
        eNFE_INFPROT.setInfprot_dhrecbto(retEnviNFe.getProtNFe().getInfProt().getDhRecbto()
                .replace("T", " ").replace("-03:00", ""));
        eNFE_INFPROT.setInfprot_nid(retEnviNFe.getProtNFe().getInfProt().getId());
        eNFE_INFPROT.setInfprot_nprot(retEnviNFe.getProtNFe().getInfProt().getNProt());
        eNFE_INFPROT.setInfprot_tpamb(retEnviNFe.getProtNFe().getInfProt().getTpAmb());
        eNFE_INFPROT.setInfprot_veraplic(retEnviNFe.getProtNFe().getInfProt().getVerAplic());
        eNFE_INFPROT.setInfprot_xmotivo(retEnviNFe.getProtNFe().getInfProt().getXMotivo()
                .replace("Autorizado o uso da NF-e", "Autorização de Uso"));

        NfeInfProtController.Update(eNFE_INFPROT);

        NFE eNFE = new NFE();
        eNFE.setIde_nnf(nNF.trim());

        if (rejeitada && !autorizada) {
            eNFE.setInfnfe_statusnfe("Rejeitada");
            NfeController.Update(eNFE);
            return;
        }

        if (autorizada) {
            eNFE.setInfnfe_statusnfe("Autorizada");
            eNFE.setXml_autorizado(xmlAutorizado);
            eNFE.setInfprot_nprot(retEnviNFe.getProtNFe().getInfProt().getNProt());
            eNFE.setInfprot_dhrecbto(retEnviNFe.getProtNFe().getInfProt().getDhRecbto()
                    .replace("T", " ").replace("-03:00", ""));
            NfeController.Update(eNFE);
        }
    }
}
