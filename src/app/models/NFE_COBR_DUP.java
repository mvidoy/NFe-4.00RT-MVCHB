package app.models;

import services.Conection;

public class NFE_COBR_DUP {

    public NFE_COBR_DUP() {
    }

    public NFE_COBR_DUP eNFE_COBR_DUP;

    public NFE_COBR_DUP(NFE_COBR_DUP eNFE_COBR_DUP) {
        this.eNFE_COBR_DUP = eNFE_COBR_DUP;
    }

    private String cobr_dup_nnf;
    private String cobr_dup_item;
    private String cobr_dup_ndup;
    private String cobr_dup_dvenc;
    private String cobr_dup_vdup;

    public String getCobr_dup_nnf() {
        return cobr_dup_nnf;
    }

    public void setCobr_dup_nnf(String cobr_dup_nnf) {
        this.cobr_dup_nnf = cobr_dup_nnf;
    }

    public String getCobr_dup_item() {
        return cobr_dup_item;
    }

    public void setCobr_dup_item(String cobr_dup_item) {
        this.cobr_dup_item = cobr_dup_item;
    }

    public String getCobr_dup_ndup() {
        return cobr_dup_ndup;
    }

    public void setCobr_dup_ndup(String cobr_dup_ndup) {
        this.cobr_dup_ndup = cobr_dup_ndup;
    }

    public String getCobr_dup_dvenc() {
        return cobr_dup_dvenc;
    }

    public void setCobr_dup_dvenc(String cobr_dup_dvenc) {
        this.cobr_dup_dvenc = cobr_dup_dvenc;
    }

    public String getCobr_dup_vdup() {
        return cobr_dup_vdup;
    }

    public void setCobr_dup_vdup(String cobr_dup_vdup) {
        this.cobr_dup_vdup = cobr_dup_vdup;
    }
}
