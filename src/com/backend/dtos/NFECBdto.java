package com.backend.dtos;

import com.backend.models.NFECB;
import static com.frontend.util.ObjetoUtil.NullToBlank;

public class NFECBdto {

    private Long IDE_NNF;
    private String INFNFE_STATUSNFE;
    private String INFNFE_DANFEXMLENVIADO;
    private String INFNFE_TITULOREGISTRADO;
    private String INFNFE_BOLETOENVIADO;

    public Long getIDE_NNF() {
        return IDE_NNF;
    }

    public String getINFNFE_STATUSNFE() {
        return INFNFE_STATUSNFE;
    }

    public String getINFNFE_DANFEXMLENVIADO() {
        return INFNFE_DANFEXMLENVIADO;
    }

    public String getINFNFE_TITULOREGISTRADO() {
        return INFNFE_TITULOREGISTRADO;
    }

    public String getINFNFE_BOLETOENVIADO() {
        return INFNFE_BOLETOENVIADO;
    }

    public void setIDE_NNF(Long IDE_NNF) {
        this.IDE_NNF = IDE_NNF;
    }

    public void setINFNFE_STATUSNFE(String INFNFE_STATUSNFE) {
        this.INFNFE_STATUSNFE = INFNFE_STATUSNFE;
    }

    public void setINFNFE_DANFEXMLENVIADO(String INFNFE_DANFEXMLENVIADO) {
        this.INFNFE_DANFEXMLENVIADO = INFNFE_DANFEXMLENVIADO;
    }

    public void setINFNFE_TITULOREGISTRADO(String INFNFE_TITULOREGISTRADO) {
        this.INFNFE_TITULOREGISTRADO = INFNFE_TITULOREGISTRADO;
    }

    public void setINFNFE_BOLETOENVIADO(String INFNFE_BOLETOENVIADO) {
        this.INFNFE_BOLETOENVIADO = INFNFE_BOLETOENVIADO;
    }

    public static NFECBdto convertNFECBtoNFECBdto(NFECB nfe) {
        NFECBdto NFEdto = new NFECBdto();
        NFEdto.setIDE_NNF(nfe.getIDE_NNF());
        NFEdto.setINFNFE_STATUSNFE(NullToBlank(nfe.getINFNFE_STATUSNFE()));
        NFEdto.setINFNFE_DANFEXMLENVIADO(NullToBlank(nfe.getINFNFE_DANFEXMLENVIADO()));
        NFEdto.setINFNFE_TITULOREGISTRADO(NullToBlank(nfe.getINFNFE_TITULOREGISTRADO()));
        NFEdto.setINFNFE_BOLETOENVIADO(NullToBlank(nfe.getINFNFE_BOLETOENVIADO()));
        return NFEdto;
    }

    public static NFECB convertNFECBdtotoNFECB(NFECBdto nFEdto) {
        NFECB nFE = new NFECB();
        nFE.setIDE_NNF(nFEdto.getIDE_NNF());
        nFE.setINFNFE_STATUSNFE(nFEdto.getINFNFE_STATUSNFE());
        nFE.setINFNFE_DANFEXMLENVIADO(nFEdto.getINFNFE_DANFEXMLENVIADO());
        nFE.setINFNFE_TITULOREGISTRADO(nFEdto.getINFNFE_TITULOREGISTRADO());
        nFE.setINFNFE_BOLETOENVIADO(nFEdto.getINFNFE_BOLETOENVIADO());
        return nFE;
    }

}
