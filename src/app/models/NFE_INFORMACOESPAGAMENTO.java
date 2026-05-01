package app.models;

public class NFE_INFORMACOESPAGAMENTO {

    public NFE_INFORMACOESPAGAMENTO() {
    }

    public NFE_INFORMACOESPAGAMENTO eNFE_INFORMACOESPAGAMENTO;

    public NFE_INFORMACOESPAGAMENTO(NFE_INFORMACOESPAGAMENTO eNFE_INFORMACOESPAGAMENTO) {
        this.eNFE_INFORMACOESPAGAMENTO = eNFE_INFORMACOESPAGAMENTO;
    }

    private String pag_nnf;
    private String pag_tpag;
    private String pag_vpag;
    private String pag_indpag;
    private String pag_item;

    public String getPag_nnf() {
        return pag_nnf;
    }

    public void setPag_nnf(String pag_nnf) {
        this.pag_nnf = pag_nnf;
    }

    public String getPag_tpag() {
        return pag_tpag;
    }

    public void setPag_tpag(String pag_tpag) {
        this.pag_tpag = pag_tpag;
    }

    public String getPag_vpag() {
        return pag_vpag;
    }

    public void setPag_vpag(String pag_vpag) {
        this.pag_vpag = pag_vpag;
    }

    public String getPag_indpag() {
        return pag_indpag;
    }

    public void setPag_indpag(String pag_indpag) {
        this.pag_indpag = pag_indpag;
    }

    public String getPag_item() {
        return pag_item;
    }

    public void setPag_item(String pag_item) {
        this.pag_item = pag_item;
    }
}
