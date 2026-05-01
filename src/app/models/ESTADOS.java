package app.models;

public class ESTADOS {

    public ESTADOS() {
    }

    public ESTADOS eESTADOS;

    public ESTADOS(ESTADOS eESTADOS) {
        this.eESTADOS = eESTADOS;
    }

    private String est_sigl;
    private String est_desc;
    private String est_codigonfe;

    public String getEst_sigl() {
        return est_sigl;
    }

    public void setEst_sigl(String est_sigl) {
        this.est_sigl = est_sigl;
    }

    public String getEst_desc() {
        return est_desc;
    }

    public void setEst_desc(String est_desc) {
        this.est_desc = est_desc;
    }

    public String getEst_codigonfe() {
        return est_codigonfe;
    }

    public void setEst_codigonfe(String est_codigonfe) {
        this.est_codigonfe = est_desc;
    }
}
