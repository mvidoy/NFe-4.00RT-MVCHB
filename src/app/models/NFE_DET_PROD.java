package app.models;

import services.Conection;

public class NFE_DET_PROD {

    public NFE_DET_PROD() {
    }

    public NFE_DET_PROD eNFE_DET_PROD;

    public NFE_DET_PROD(NFE_DET_PROD eNFE_DET_PROD) {
        this.eNFE_DET_PROD = eNFE_DET_PROD;
    }

    private String det_prod_nnf;
    private String det_prod_item;
    private String det_prod_cprod;
    private String det_prod_xprod;
    private String det_prod_ncm;
    private String det_prod_cfop;
    private String det_prod_ucom;
    private String det_prod_qcom;
    private String det_prod_vuncom;
    private String det_prod_vprod;
    private String det_prod_utrib;
    private String det_prod_qtrib;
    private String det_prod_vuntrib;
    private String det_prod_vfrete;
    private String det_prod_vseg;
    private String det_prod_vdesc;
    private String det_prod_voutro;
    private String det_prod_indtot;
    private String det_imposto_vtottrib;
    private String det_icms_crt;
    private String det_icms_cst;
    private String det_icms_orig;
    private String det_icms00_orig;
    private String det_icms00_cst;
    private String det_icms00_modbc;
    private String det_icms00_vbc;
    private String det_icms00_picms;
    private String det_icms00_vicms;
    private String det_icms10_orig;
    private String det_icms10_cst;
    private String det_icms10_modbc;
    private String det_icms10_vbc;
    private String det_icms10_picms;
    private String det_icms10_vicms;
    private String det_icms10_modbcst;
    private String det_icms10_pmvast;
    private String det_icms10_predbcst;
    private String det_icms10_vbcst;
    private String det_icms10_picmsst;
    private String det_icms10_vicmsst;
    private String det_icms20_orig;
    private String det_icms20_cst;
    private String det_icms20_modbc;
    private String det_icms20_predbc;
    private String det_icms20_vbc;
    private String det_icms20_picms;
    private String det_icms20_vicms;
    private String det_icms20_vicmsdeson;
    private String det_icms20_motdesicms;
    private String det_icms30_orig;
    private String det_icms30_cst;
    private String det_icms30_modbcst;
    private String det_icms30_pmvast;
    private String det_icms30_predbcst;
    private String det_icms30_vbcst;
    private String det_icms30_picmsst;
    private String det_icms30_vicmsst;
    private String det_icms30_vicmsdeson;
    private String det_icms30_motdesicms;
    private String det_icms40_orig;
    private String det_icms40_cst;
    private String det_icms40_vicmsdeson;
    private String det_icms40_motdesicms;
    private String det_icms51_orig;
    private String det_icms51_cst;
    private String det_icms51_modbc;
    private String det_icms51_predbc;
    private String det_icms51_vbc;
    private String det_icms51_picms;
    private String det_icms51_vicmsop;
    private String det_icms51_pdif;
    private String det_icms51_vicmsdif;
    private String det_icms51_vicms;
    private String det_icms60_orig;
    private String det_icms60_cst;
    private String det_icms60_vbcstret;
    private String det_icms60_vicmsstret;
    private String det_icms70_orig;
    private String det_icms70_cst;
    private String det_icms70_modbc;
    private String det_icms70_predbc;
    private String det_icms70_vbc;
    private String det_icms70_picms;
    private String det_icms70_vicms;
    private String det_icms70_modbcst;
    private String det_icms70_pmvast;
    private String det_icms70_predbcst;
    private String det_icms70_vbcst;
    private String det_icms70_picmsst;
    private String det_icms70_vicmsst;
    private String det_icms70_vicmsdeson;
    private String det_icms70_motdesicms;
    private String det_icms90_orig;
    private String det_icms90_cst;
    private String det_icms90_modbc;
    private String det_icms90_vbc;
    private String det_icms90_predbc;
    private String det_icms90_picms;
    private String det_icms90_vicms;
    private String det_icms90_modbcst;
    private String det_icms90_pmvast;
    private String det_icms90_predbcst;
    private String det_icms90_vbcst;
    private String det_icms90_picmsst;
    private String det_icms90_vicmsst;
    private String det_icms90_vicmsdeson;
    private String det_icms90_motdesicms;
    private String det_ipi_clenq;
    private String det_ipi_cnpjprod;
    private String det_ipi_cselo;
    private String det_ipi_qselo;
    private String det_ipi_cenq;
    private String det_ipitrib_cst;
    private String det_ipitrib_vbc;
    private String det_ipitrib_pipi;
    private String det_ipitrib_qunid;
    private String det_ipitrib_vunid;
    private String det_ipitrib_vipi;
    private String det_pisaliq_cst;
    private String det_pisaliq_vbc;
    private String det_pisaliq_ppis;
    private String det_pisaliq_vpis;
    private String det_pisqtde_cst;
    private String det_pisqtde_qbcprod;
    private String det_pisqtde_valiqprod;
    private String det_pisqtde_vpis;
    private String det_pisoutr_cst;
    private String det_pisoutr_vbc;
    private String det_pisoutr_ppis;
    private String det_pisoutr_vpis;
    private String det_pisst_vbc;
    private String det_pisst_ppis;
    private String det_pisst_qbcprod;
    private String det_pisst_valiqprod;
    private String det_pisst_vpis;
    private String det_cofinsaliq_cst;
    private String det_cofinsaliq_vbc;
    private String det_cofinsaliq_pcofins;
    private String det_cofinsaliq_vcofins;
    private String det_cofinsqtde_cst;
    private String det_cofinsqtde_qbcprod;
    private String det_cofinsqtde_valiqprod;
    private String det_cofinsqtde_vcofins;
    private String det_cofinsoutr_cst;
    private String det_cofinsoutr_vbc;
    private String det_cofinsoutr_pcofins;
    private String det_cofinsoutr_vcofins;
    private String det_cofinsst_vbc;
    private String det_cofinsst_pcofins;
    private String det_cofinsst_qbcprod;
    private String det_cofinsst_valiqprod;
    private String det_cofinsst_vcofins;
    private String det_infadprod;
    private String det_prod_cest;
    private String det_prod_cean;
    private String det_prod_ceantrib;

    private String det_ibs_cst;
    private String det_ibs_cclasstrib;
    private String det_ibs_pibstotal;
    private String det_ibs_pibsuf;
    private String det_ibs_pibsmun;
    private String det_ibscbs_vbc;
    private String det_ibsuf_vbc;
    private String det_ibsuf_pibsuf;
    private String det_ibsuf_vibsuf;
    private String det_ibsmun_vbc;
    private String det_ibsmun_pibsmun;
    private String det_ibsmun_vibsmun;
    private String det_is_cst;
    private String det_is_cclasstrib;
    private String det_is_vbcimpsel;
    private String det_is_pimpsel;
    private String det_is_vimpsel;
    private String det_is_pimpselespec;
    private String det_is_utrib;
    private String det_is_qtrib;
    private String det_is_cstibscbs;
    private String det_is_cclasstribibscbs;
    private String det_cbs_cst;
    private String det_cbs_cclasstrib;
    private String det_cbs_pcbsref;
    private String det_cbs_vbc;
    private String det_cbs_pcbs;
    private String det_cbs_vcbs;

    public String getDet_prod_nnf() {
        return det_prod_nnf;
    }

    public void setDet_prod_nnf(String det_prod_nnf) {
        this.det_prod_nnf = det_prod_nnf;
    }

    public String getDet_prod_item() {
        return det_prod_item;
    }

    public void setDet_prod_item(String det_prod_item) {
        this.det_prod_item = det_prod_item;
    }

    public String getDet_prod_cprod() {
        return det_prod_cprod;
    }

    public void setDet_prod_cprod(String det_prod_cprod) {
        this.det_prod_cprod = det_prod_cprod;
    }

    public String getDet_prod_xprod() {
        return det_prod_xprod;
    }

    public void setDet_prod_xprod(String det_prod_xprod) {
        this.det_prod_xprod = det_prod_xprod;
    }

    public String getDet_prod_ncm() {
        return det_prod_ncm;
    }

    public void setDet_prod_ncm(String det_prod_ncm) {
        this.det_prod_ncm = det_prod_ncm;
    }

    public String getDet_prod_cfop() {
        return det_prod_cfop;
    }

    public void setDet_prod_cfop(String det_prod_cfop) {
        this.det_prod_cfop = det_prod_cfop;
    }

    public String getDet_prod_ucom() {
        return det_prod_ucom;
    }

    public void setDet_prod_ucom(String det_prod_ucom) {
        this.det_prod_ucom = det_prod_ucom;
    }

    public String getDet_prod_qcom() {
        return det_prod_qcom;
    }

    public void setDet_prod_qcom(String det_prod_qcom) {
        this.det_prod_qcom = det_prod_qcom;
    }

    public String getDet_prod_vuncom() {
        return det_prod_vuncom;
    }

    public void setDet_prod_vuncom(String det_prod_vuncom) {
        this.det_prod_vuncom = det_prod_vuncom;
    }

    public String getDet_prod_vprod() {
        return det_prod_vprod;
    }

    public void setDet_prod_vprod(String det_prod_vprod) {
        this.det_prod_vprod = det_prod_vprod;
    }

    public String getDet_prod_utrib() {
        return det_prod_utrib;
    }

    public void setDet_prod_utrib(String det_prod_utrib) {
        this.det_prod_utrib = det_prod_utrib;
    }

    public String getDet_prod_qtrib() {
        return det_prod_qtrib;
    }

    public void setDet_prod_qtrib(String det_prod_qtrib) {
        this.det_prod_qtrib = det_prod_qtrib;
    }

    public String getDet_prod_vuntrib() {
        return det_prod_vuntrib;
    }

    public void setDet_prod_vuntrib(String det_prod_vuntrib) {
        this.det_prod_vuntrib = det_prod_vuntrib;
    }

    public String getDet_prod_vfrete() {
        return det_prod_vfrete;
    }

    public void setDet_prod_vfrete(String det_prod_vfrete) {
        this.det_prod_vfrete = det_prod_vfrete;
    }

    public String getDet_prod_vseg() {
        return det_prod_vseg;
    }

    public void setDet_prod_vseg(String det_prod_vseg) {
        this.det_prod_vseg = det_prod_vseg;
    }

    public String getDet_prod_vdesc() {
        return det_prod_vdesc;
    }

    public void setDet_prod_vdesc(String det_prod_vdesc) {
        this.det_prod_vdesc = det_prod_vdesc;
    }

    public String getDet_prod_voutro() {
        return det_prod_voutro;
    }

    public void setDet_prod_voutro(String det_prod_voutro) {
        this.det_prod_voutro = det_prod_voutro;
    }

    public String getDet_prod_indtot() {
        return det_prod_indtot;
    }

    public void setDet_prod_indtot(String det_prod_indtot) {
        this.det_prod_indtot = det_prod_indtot;
    }

    public String getDet_imposto_vtottrib() {
        return det_imposto_vtottrib;
    }

    public void setDet_imposto_vtottrib(String det_imposto_vtottrib) {
        this.det_imposto_vtottrib = det_imposto_vtottrib;
    }

    public String getDet_icms_crt() {
        return det_icms_crt;
    }

    public void setDet_icms_crt(String det_icms_crt) {
        this.det_icms_crt = det_icms_crt;
    }

    public String getDet_icms_cst() {
        return det_icms_cst;
    }

    public void setDet_icms_cst(String det_icms_cst) {
        this.det_icms_cst = det_icms_cst;
    }

    public String getDet_icms_orig() {
        return det_icms_orig;
    }

    public void setDet_icms_orig(String det_icms_orig) {
        this.det_icms_orig = det_icms_orig;
    }

    public String getDet_icms00_orig() {
        return det_icms00_orig;
    }

    public void setDet_icms00_orig(String det_icms00_orig) {
        this.det_icms00_orig = det_icms00_orig;
    }

    public String getDet_icms00_cst() {
        return det_icms00_cst;
    }

    public void setDet_icms00_cst(String det_icms00_cst) {
        this.det_icms00_cst = det_icms00_cst;
    }

    public String getDet_icms00_modbc() {
        return det_icms00_modbc;
    }

    public void setDet_icms00_modbc(String det_icms00_modbc) {
        this.det_icms00_modbc = det_icms00_modbc;
    }

    public String getDet_icms00_vbc() {
        return det_icms00_vbc;
    }

    public void setDet_icms00_vbc(String det_icms00_vbc) {
        this.det_icms00_vbc = det_icms00_vbc;
    }

    public String getDet_icms00_picms() {
        return det_icms00_picms;
    }

    public void setDet_icms00_picms(String det_icms00_picms) {
        this.det_icms00_picms = det_icms00_picms;
    }

    public String getDet_icms00_vicms() {
        return det_icms00_vicms;
    }

    public void setDet_icms00_vicms(String det_icms00_vicms) {
        this.det_icms00_vicms = det_icms00_vicms;
    }

    public String getDet_icms10_orig() {
        return det_icms10_orig;
    }

    public void setDet_icms10_orig(String det_icms10_orig) {
        this.det_icms10_orig = det_icms10_orig;
    }

    public String getDet_icms10_cst() {
        return det_icms10_cst;
    }

    public void setDet_icms10_cst(String det_icms10_cst) {
        this.det_icms10_cst = det_icms10_cst;
    }

    public String getDet_icms10_modbc() {
        return det_icms10_modbc;
    }

    public void setDet_icms10_modbc(String det_icms10_modbc) {
        this.det_icms10_modbc = det_icms10_modbc;
    }

    public String getDet_icms10_vbc() {
        return det_icms10_vbc;
    }

    public void setDet_icms10_vbc(String det_icms10_vbc) {
        this.det_icms10_vbc = det_icms10_vbc;
    }

    public String getDet_icms10_picms() {
        return det_icms10_picms;
    }

    public void setDet_icms10_picms(String det_icms10_picms) {
        this.det_icms10_picms = det_icms10_picms;
    }

    public String getDet_icms10_vicms() {
        return det_icms10_vicms;
    }

    public void setDet_icms10_vicms(String det_icms10_vicms) {
        this.det_icms10_vicms = det_icms10_vicms;
    }

    public String getDet_icms10_modbcst() {
        return det_icms10_modbcst;
    }

    public void setDet_icms10_modbcst(String det_icms10_modbcst) {
        this.det_icms10_modbcst = det_icms10_modbcst;
    }

    public String getDet_icms10_pmvast() {
        return det_icms10_pmvast;
    }

    public void setDet_icms10_pmvast(String det_icms10_pmvast) {
        this.det_icms10_pmvast = det_icms10_pmvast;
    }

    public String getDet_icms10_predbcst() {
        return det_icms10_predbcst;
    }

    public void setDet_icms10_predbcst(String det_icms10_predbcst) {
        this.det_icms10_predbcst = det_icms10_predbcst;
    }

    public String getDet_icms10_vbcst() {
        return det_icms10_vbcst;
    }

    public void setDet_icms10_vbcst(String det_icms10_vbcst) {
        this.det_icms10_vbcst = det_icms10_vbcst;
    }

    public String getDet_icms10_picmsst() {
        return det_icms10_picmsst;
    }

    public void setDet_icms10_picmsst(String det_icms10_picmsst) {
        this.det_icms10_picmsst = det_icms10_picmsst;
    }

    public String getDet_icms10_vicmsst() {
        return det_icms10_vicmsst;
    }

    public void setDet_icms10_vicmsst(String det_icms10_vicmsst) {
        this.det_icms10_vicmsst = det_icms10_vicmsst;
    }

    public String getDet_icms20_orig() {
        return det_icms20_orig;
    }

    public void setDet_icms20_orig(String det_icms20_orig) {
        this.det_icms20_orig = det_icms20_orig;
    }

    public String getDet_icms20_cst() {
        return det_icms20_cst;
    }

    public void setDet_icms20_cst(String det_icms20_cst) {
        this.det_icms20_cst = det_icms20_cst;
    }

    public String getDet_icms20_modbc() {
        return det_icms20_modbc;
    }

    public void setDet_icms20_modbc(String det_icms20_modbc) {
        this.det_icms20_modbc = det_icms20_modbc;
    }

    public String getDet_icms20_predbc() {
        return det_icms20_predbc;
    }

    public void setDet_icms20_predbc(String det_icms20_predbc) {
        this.det_icms20_predbc = det_icms20_predbc;
    }

    public String getDet_icms20_vbc() {
        return det_icms20_vbc;
    }

    public void setDet_icms20_vbc(String det_icms20_vbc) {
        this.det_icms20_vbc = det_icms20_vbc;
    }

    public String getDet_icms20_picms() {
        return det_icms20_picms;
    }

    public void setDet_icms20_picms(String det_icms20_picms) {
        this.det_icms20_picms = det_icms20_picms;
    }

    public String getDet_icms20_vicms() {
        return det_icms20_vicms;
    }

    public void setDet_icms20_vicms(String det_icms20_vicms) {
        this.det_icms20_vicms = det_icms20_vicms;
    }

    public String getDet_icms20_vicmsdeson() {
        return det_icms20_vicmsdeson;
    }

    public void setDet_icms20_vicmsdeson(String det_icms20_vicmsdeson) {
        this.det_icms20_vicmsdeson = det_icms20_vicmsdeson;
    }

    public String getDet_icms20_motdesicms() {
        return det_icms20_motdesicms;
    }

    public void setDet_icms20_motdesicms(String det_icms20_motdesicms) {
        this.det_icms20_motdesicms = det_icms20_motdesicms;
    }

    public String getDet_icms30_orig() {
        return det_icms30_orig;
    }

    public void setDet_icms30_orig(String det_icms30_orig) {
        this.det_icms30_orig = det_icms30_orig;
    }

    public String getDet_icms30_cst() {
        return det_icms30_cst;
    }

    public void setDet_icms30_cst(String det_icms30_cst) {
        this.det_icms30_cst = det_icms30_cst;
    }

    public String getDet_icms30_modbcst() {
        return det_icms30_modbcst;
    }

    public void setDet_icms30_modbcst(String det_icms30_modbcst) {
        this.det_icms30_modbcst = det_icms30_modbcst;
    }

    public String getDet_icms30_pmvast() {
        return det_icms30_pmvast;
    }

    public void setDet_icms30_pmvast(String det_icms30_pmvast) {
        this.det_icms30_pmvast = det_icms30_pmvast;
    }

    public String getDet_icms30_predbcst() {
        return det_icms30_predbcst;
    }

    public void setDet_icms30_predbcst(String det_icms30_predbcst) {
        this.det_icms30_predbcst = det_icms30_predbcst;
    }

    public String getDet_icms30_vbcst() {
        return det_icms30_vbcst;
    }

    public void setDet_icms30_vbcst(String det_icms30_vbcst) {
        this.det_icms30_vbcst = det_icms30_vbcst;
    }

    public String getDet_icms30_picmsst() {
        return det_icms30_picmsst;
    }

    public void setDet_icms30_picmsst(String det_icms30_picmsst) {
        this.det_icms30_picmsst = det_icms30_picmsst;
    }

    public String getDet_icms30_vicmsst() {
        return det_icms30_vicmsst;
    }

    public void setDet_icms30_vicmsst(String det_icms30_vicmsst) {
        this.det_icms30_vicmsst = det_icms30_vicmsst;
    }

    public String getDet_icms30_vicmsdeson() {
        return det_icms30_vicmsdeson;
    }

    public void setDet_icms30_vicmsdeson(String det_icms30_vicmsdeson) {
        this.det_icms30_vicmsdeson = det_icms30_vicmsdeson;
    }

    public String getDet_icms30_motdesicms() {
        return det_icms30_motdesicms;
    }

    public void setDet_icms30_motdesicms(String det_icms30_motdesicms) {
        this.det_icms30_motdesicms = det_icms30_motdesicms;
    }

    public String getDet_icms40_orig() {
        return det_icms40_orig;
    }

    public void setDet_icms40_orig(String det_icms40_orig) {
        this.det_icms40_orig = det_icms40_orig;
    }

    public String getDet_icms40_cst() {
        return det_icms40_cst;
    }

    public void setDet_icms40_cst(String det_icms40_cst) {
        this.det_icms40_cst = det_icms40_cst;
    }

    public String getDet_icms40_vicmsdeson() {
        return det_icms40_vicmsdeson;
    }

    public void setDet_icms40_vicmsdeson(String det_icms40_vicmsdeson) {
        this.det_icms40_vicmsdeson = det_icms40_vicmsdeson;
    }

    public String getDet_icms40_motdesicms() {
        return det_icms40_motdesicms;
    }

    public void setDet_icms40_motdesicms(String det_icms40_motdesicms) {
        this.det_icms40_motdesicms = det_icms40_motdesicms;
    }

    public String getDet_icms51_orig() {
        return det_icms51_orig;
    }

    public void setDet_icms51_orig(String det_icms51_orig) {
        this.det_icms51_orig = det_icms51_orig;
    }

    public String getDet_icms51_cst() {
        return det_icms51_cst;
    }

    public void setDet_icms51_cst(String det_icms51_cst) {
        this.det_icms51_cst = det_icms51_cst;
    }

    public String getDet_icms51_modbc() {
        return det_icms51_modbc;
    }

    public void setDet_icms51_modbc(String det_icms51_modbc) {
        this.det_icms51_modbc = det_icms51_modbc;
    }

    public String getDet_icms51_predbc() {
        return det_icms51_predbc;
    }

    public void setDet_icms51_predbc(String det_icms51_predbc) {
        this.det_icms51_predbc = det_icms51_predbc;
    }

    public String getDet_icms51_vbc() {
        return det_icms51_vbc;
    }

    public void setDet_icms51_vbc(String det_icms51_vbc) {
        this.det_icms51_vbc = det_icms51_vbc;
    }

    public String getDet_icms51_picms() {
        return det_icms51_picms;
    }

    public void setDet_icms51_picms(String det_icms51_picms) {
        this.det_icms51_picms = det_icms51_picms;
    }

    public String getDet_icms51_vicmsop() {
        return det_icms51_vicmsop;
    }

    public void setDet_icms51_vicmsop(String det_icms51_vicmsop) {
        this.det_icms51_vicmsop = det_icms51_vicmsop;
    }

    public String getDet_icms51_pdif() {
        return det_icms51_pdif;
    }

    public void setDet_icms51_pdif(String det_icms51_pdif) {
        this.det_icms51_pdif = det_icms51_pdif;
    }

    public String getDet_icms51_vicmsdif() {
        return det_icms51_vicmsdif;
    }

    public void setDet_icms51_vicmsdif(String det_icms51_vicmsdif) {
        this.det_icms51_vicmsdif = det_icms51_vicmsdif;
    }

    public String getDet_icms51_vicms() {
        return det_icms51_vicms;
    }

    public void setDet_icms51_vicms(String det_icms51_vicms) {
        this.det_icms51_vicms = det_icms51_vicms;
    }

    public String getDet_icms60_orig() {
        return det_icms60_orig;
    }

    public void setDet_icms60_orig(String det_icms60_orig) {
        this.det_icms60_orig = det_icms60_orig;
    }

    public String getDet_icms60_cst() {
        return det_icms60_cst;
    }

    public void setDet_icms60_cst(String det_icms60_cst) {
        this.det_icms60_cst = det_icms60_cst;
    }

    public String getDet_icms60_vbcstret() {
        return det_icms60_vbcstret;
    }

    public void setDet_icms60_vbcstret(String det_icms60_vbcstret) {
        this.det_icms60_vbcstret = det_icms60_vbcstret;
    }

    public String getDet_icms60_vicmsstret() {
        return det_icms60_vicmsstret;
    }

    public void setDet_icms60_vicmsstret(String det_icms60_vicmsstret) {
        this.det_icms60_vicmsstret = det_icms60_vicmsstret;
    }

    public String getDet_icms70_orig() {
        return det_icms70_orig;
    }

    public void setDet_icms70_orig(String det_icms70_orig) {
        this.det_icms70_orig = det_icms70_orig;
    }

    public String getDet_icms70_cst() {
        return det_icms70_cst;
    }

    public void setDet_icms70_cst(String det_icms70_cst) {
        this.det_icms70_cst = det_icms70_cst;
    }

    public String getDet_icms70_modbc() {
        return det_icms70_modbc;
    }

    public void setDet_icms70_modbc(String det_icms70_modbc) {
        this.det_icms70_modbc = det_icms70_modbc;
    }

    public String getDet_icms70_predbc() {
        return det_icms70_predbc;
    }

    public void setDet_icms70_predbc(String det_icms70_predbc) {
        this.det_icms70_predbc = det_icms70_predbc;
    }

    public String getDet_icms70_vbc() {
        return det_icms70_vbc;
    }

    public void setDet_icms70_vbc(String det_icms70_vbc) {
        this.det_icms70_vbc = det_icms70_vbc;
    }

    public String getDet_icms70_picms() {
        return det_icms70_picms;
    }

    public void setDet_icms70_picms(String det_icms70_picms) {
        this.det_icms70_picms = det_icms70_picms;
    }

    public String getDet_icms70_vicms() {
        return det_icms70_vicms;
    }

    public void setDet_icms70_vicms(String det_icms70_vicms) {
        this.det_icms70_vicms = det_icms70_vicms;
    }

    public String getDet_icms70_modbcst() {
        return det_icms70_modbcst;
    }

    public void setDet_icms70_modbcst(String det_icms70_modbcst) {
        this.det_icms70_modbcst = det_icms70_modbcst;
    }

    public String getDet_icms70_pmvast() {
        return det_icms70_pmvast;
    }

    public void setDet_icms70_pmvast(String det_icms70_pmvast) {
        this.det_icms70_pmvast = det_icms70_pmvast;
    }

    public String getDet_icms70_predbcst() {
        return det_icms70_predbcst;
    }

    public void setDet_icms70_predbcst(String det_icms70_predbcst) {
        this.det_icms70_predbcst = det_icms70_predbcst;
    }

    public String getDet_icms70_vbcst() {
        return det_icms70_vbcst;
    }

    public void setDet_icms70_vbcst(String det_icms70_vbcst) {
        this.det_icms70_vbcst = det_icms70_vbcst;
    }

    public String getDet_icms70_picmsst() {
        return det_icms70_picmsst;
    }

    public void setDet_icms70_picmsst(String det_icms70_picmsst) {
        this.det_icms70_picmsst = det_icms70_picmsst;
    }

    public String getDet_icms70_vicmsst() {
        return det_icms70_vicmsst;
    }

    public void setDet_icms70_vicmsst(String det_icms70_vicmsst) {
        this.det_icms70_vicmsst = det_icms70_vicmsst;
    }

    public String getDet_icms70_vicmsdeson() {
        return det_icms70_vicmsdeson;
    }

    public void setDet_icms70_vicmsdeson(String det_icms70_vicmsdeson) {
        this.det_icms70_vicmsdeson = det_icms70_vicmsdeson;
    }

    public String getDet_icms70_motdesicms() {
        return det_icms70_motdesicms;
    }

    public void setDet_icms70_motdesicms(String det_icms70_motdesicms) {
        this.det_icms70_motdesicms = det_icms70_motdesicms;
    }

    public String getDet_icms90_orig() {
        return det_icms90_orig;
    }

    public void setDet_icms90_orig(String det_icms90_orig) {
        this.det_icms90_orig = det_icms90_orig;
    }

    public String getDet_icms90_cst() {
        return det_icms90_cst;
    }

    public void setDet_icms90_cst(String det_icms90_cst) {
        this.det_icms90_cst = det_icms90_cst;
    }

    public String getDet_icms90_modbc() {
        return det_icms90_modbc;
    }

    public void setDet_icms90_modbc(String det_icms90_modbc) {
        this.det_icms90_modbc = det_icms90_modbc;
    }

    public String getDet_icms90_vbc() {
        return det_icms90_vbc;
    }

    public void setDet_icms90_vbc(String det_icms90_vbc) {
        this.det_icms90_vbc = det_icms90_vbc;
    }

    public String getDet_icms90_predbc() {
        return det_icms90_predbc;
    }

    public void setDet_icms90_predbc(String det_icms90_predbc) {
        this.det_icms90_predbc = det_icms90_predbc;
    }

    public String getDet_icms90_picms() {
        return det_icms90_picms;
    }

    public void setDet_icms90_picms(String det_icms90_picms) {
        this.det_icms90_picms = det_icms90_picms;
    }

    public String getDet_icms90_vicms() {
        return det_icms90_vicms;
    }

    public void setDet_icms90_vicms(String det_icms90_vicms) {
        this.det_icms90_vicms = det_icms90_vicms;
    }

    public String getDet_icms90_modbcst() {
        return det_icms90_modbcst;
    }

    public void setDet_icms90_modbcst(String det_icms90_modbcst) {
        this.det_icms90_modbcst = det_icms90_modbcst;
    }

    public String getDet_icms90_pmvast() {
        return det_icms90_pmvast;
    }

    public void setDet_icms90_pmvast(String det_icms90_pmvast) {
        this.det_icms90_pmvast = det_icms90_pmvast;
    }

    public String getDet_icms90_predbcst() {
        return det_icms90_predbcst;
    }

    public void setDet_icms90_predbcst(String det_icms90_predbcst) {
        this.det_icms90_predbcst = det_icms90_predbcst;
    }

    public String getDet_icms90_vbcst() {
        return det_icms90_vbcst;
    }

    public void setDet_icms90_vbcst(String det_icms90_vbcst) {
        this.det_icms90_vbcst = det_icms90_vbcst;
    }

    public String getDet_icms90_picmsst() {
        return det_icms90_picmsst;
    }

    public void setDet_icms90_picmsst(String det_icms90_picmsst) {
        this.det_icms90_picmsst = det_icms90_picmsst;
    }

    public String getDet_icms90_vicmsst() {
        return det_icms90_vicmsst;
    }

    public void setDet_icms90_vicmsst(String det_icms90_vicmsst) {
        this.det_icms90_vicmsst = det_icms90_vicmsst;
    }

    public String getDet_icms90_vicmsdeson() {
        return det_icms90_vicmsdeson;
    }

    public void setDet_icms90_vicmsdeson(String det_icms90_vicmsdeson) {
        this.det_icms90_vicmsdeson = det_icms90_vicmsdeson;
    }

    public String getDet_icms90_motdesicms() {
        return det_icms90_motdesicms;
    }

    public void setDet_icms90_motdesicms(String det_icms90_motdesicms) {
        this.det_icms90_motdesicms = det_icms90_motdesicms;
    }

    public String getDet_ipi_clenq() {
        return det_ipi_clenq;
    }

    public void setDet_ipi_clenq(String det_ipi_clenq) {
        this.det_ipi_clenq = det_ipi_clenq;
    }

    public String getDet_ipi_cnpjprod() {
        return det_ipi_cnpjprod;
    }

    public void setDet_ipi_cnpjprod(String det_ipi_cnpjprod) {
        this.det_ipi_cnpjprod = det_ipi_cnpjprod;
    }

    public String getDet_ipi_cselo() {
        return det_ipi_cselo;
    }

    public void setDet_ipi_cselo(String det_ipi_cselo) {
        this.det_ipi_cselo = det_ipi_cselo;
    }

    public String getDet_ipi_qselo() {
        return det_ipi_qselo;
    }

    public void setDet_ipi_qselo(String det_ipi_qselo) {
        this.det_ipi_qselo = det_ipi_qselo;
    }

    public String getDet_ipi_cenq() {
        return det_ipi_cenq;
    }

    public void setDet_ipi_cenq(String det_ipi_cenq) {
        this.det_ipi_cenq = det_ipi_cenq;
    }

    public String getDet_ipitrib_cst() {
        return det_ipitrib_cst;
    }

    public void setDet_ipitrib_cst(String det_ipitrib_cst) {
        this.det_ipitrib_cst = det_ipitrib_cst;
    }

    public String getDet_ipitrib_vbc() {
        return det_ipitrib_vbc;
    }

    public void setDet_ipitrib_vbc(String det_ipitrib_vbc) {
        this.det_ipitrib_vbc = det_ipitrib_vbc;
    }

    public String getDet_ipitrib_pipi() {
        return det_ipitrib_pipi;
    }

    public void setDet_ipitrib_pipi(String det_ipitrib_pipi) {
        this.det_ipitrib_pipi = det_ipitrib_pipi;
    }

    public String getDet_ipitrib_qunid() {
        return det_ipitrib_qunid;
    }

    public void setDet_ipitrib_qunid(String det_ipitrib_qunid) {
        this.det_ipitrib_qunid = det_ipitrib_qunid;
    }

    public String getDet_ipitrib_vunid() {
        return det_ipitrib_vunid;
    }

    public void setDet_ipitrib_vunid(String det_ipitrib_vunid) {
        this.det_ipitrib_vunid = det_ipitrib_vunid;
    }

    public String getDet_ipitrib_vipi() {
        return det_ipitrib_vipi;
    }

    public void setDet_ipitrib_vipi(String det_ipitrib_vipi) {
        this.det_ipitrib_vipi = det_ipitrib_vipi;
    }

    public String getDet_pisaliq_cst() {
        return det_pisaliq_cst;
    }

    public void setDet_pisaliq_cst(String det_pisaliq_cst) {
        this.det_pisaliq_cst = det_pisaliq_cst;
    }

    public String getDet_pisaliq_vbc() {
        return det_pisaliq_vbc;
    }

    public void setDet_pisaliq_vbc(String det_pisaliq_vbc) {
        this.det_pisaliq_vbc = det_pisaliq_vbc;
    }

    public String getDet_pisaliq_ppis() {
        return det_pisaliq_ppis;
    }

    public void setDet_pisaliq_ppis(String det_pisaliq_ppis) {
        this.det_pisaliq_ppis = det_pisaliq_ppis;
    }

    public String getDet_pisaliq_vpis() {
        return det_pisaliq_vpis;
    }

    public void setDet_pisaliq_vpis(String det_pisaliq_vpis) {
        this.det_pisaliq_vpis = det_pisaliq_vpis;
    }

    public String getDet_pisqtde_cst() {
        return det_pisqtde_cst;
    }

    public void setDet_pisqtde_cst(String det_pisqtde_cst) {
        this.det_pisqtde_cst = det_pisqtde_cst;
    }

    public String getDet_pisqtde_qbcprod() {
        return det_pisqtde_qbcprod;
    }

    public void setDet_pisqtde_qbcprod(String det_pisqtde_qbcprod) {
        this.det_pisqtde_qbcprod = det_pisqtde_qbcprod;
    }

    public String getDet_pisqtde_valiqprod() {
        return det_pisqtde_valiqprod;
    }

    public void setDet_pisqtde_valiqprod(String det_pisqtde_valiqprod) {
        this.det_pisqtde_valiqprod = det_pisqtde_valiqprod;
    }

    public String getDet_pisqtde_vpis() {
        return det_pisqtde_vpis;
    }

    public void setDet_pisqtde_vpis(String det_pisqtde_vpis) {
        this.det_pisqtde_vpis = det_pisqtde_vpis;
    }

    public String getDet_pisoutr_cst() {
        return det_pisoutr_cst;
    }

    public void setDet_pisoutr_cst(String det_pisoutr_cst) {
        this.det_pisoutr_cst = det_pisoutr_cst;
    }

    public String getDet_pisoutr_vbc() {
        return det_pisoutr_vbc;
    }

    public void setDet_pisoutr_vbc(String det_pisoutr_vbc) {
        this.det_pisoutr_vbc = det_pisoutr_vbc;
    }

    public String getDet_pisoutr_ppis() {
        return det_pisoutr_ppis;
    }

    public void setDet_pisoutr_ppis(String det_pisoutr_ppis) {
        this.det_pisoutr_ppis = det_pisoutr_ppis;
    }

    public String getDet_pisoutr_vpis() {
        return det_pisoutr_vpis;
    }

    public void setDet_pisoutr_vpis(String det_pisoutr_vpis) {
        this.det_pisoutr_vpis = det_pisoutr_vpis;
    }

    public String getDet_pisst_vbc() {
        return det_pisst_vbc;
    }

    public void setDet_pisst_vbc(String det_pisst_vbc) {
        this.det_pisst_vbc = det_pisst_vbc;
    }

    public String getDet_pisst_ppis() {
        return det_pisst_ppis;
    }

    public void setDet_pisst_ppis(String det_pisst_ppis) {
        this.det_pisst_ppis = det_pisst_ppis;
    }

    public String getDet_pisst_qbcprod() {
        return det_pisst_qbcprod;
    }

    public void setDet_pisst_qbcprod(String det_pisst_qbcprod) {
        this.det_pisst_qbcprod = det_pisst_qbcprod;
    }

    public String getDet_pisst_valiqprod() {
        return det_pisst_valiqprod;
    }

    public void setDet_pisst_valiqprod(String det_pisst_valiqprod) {
        this.det_pisst_valiqprod = det_pisst_valiqprod;
    }

    public String getDet_pisst_vpis() {
        return det_pisst_vpis;
    }

    public void setDet_pisst_vpis(String det_pisst_vpis) {
        this.det_pisst_vpis = det_pisst_vpis;
    }

    public String getDet_cofinsaliq_cst() {
        return det_cofinsaliq_cst;
    }

    public void setDet_cofinsaliq_cst(String det_cofinsaliq_cst) {
        this.det_cofinsaliq_cst = det_cofinsaliq_cst;
    }

    public String getDet_cofinsaliq_vbc() {
        return det_cofinsaliq_vbc;
    }

    public void setDet_cofinsaliq_vbc(String det_cofinsaliq_vbc) {
        this.det_cofinsaliq_vbc = det_cofinsaliq_vbc;
    }

    public String getDet_cofinsaliq_pcofins() {
        return det_cofinsaliq_pcofins;
    }

    public void setDet_cofinsaliq_pcofins(String det_cofinsaliq_pcofins) {
        this.det_cofinsaliq_pcofins = det_cofinsaliq_pcofins;
    }

    public String getDet_cofinsaliq_vcofins() {
        return det_cofinsaliq_vcofins;
    }

    public void setDet_cofinsaliq_vcofins(String det_cofinsaliq_vcofins) {
        this.det_cofinsaliq_vcofins = det_cofinsaliq_vcofins;
    }

    public String getDet_cofinsqtde_cst() {
        return det_cofinsqtde_cst;
    }

    public void setDet_cofinsqtde_cst(String det_cofinsqtde_cst) {
        this.det_cofinsqtde_cst = det_cofinsqtde_cst;
    }

    public String getDet_cofinsqtde_qbcprod() {
        return det_cofinsqtde_qbcprod;
    }

    public void setDet_cofinsqtde_qbcprod(String det_cofinsqtde_qbcprod) {
        this.det_cofinsqtde_qbcprod = det_cofinsqtde_qbcprod;
    }

    public String getDet_cofinsqtde_valiqprod() {
        return det_cofinsqtde_valiqprod;
    }

    public void setDet_cofinsqtde_valiqprod(String det_cofinsqtde_valiqprod) {
        this.det_cofinsqtde_valiqprod = det_cofinsqtde_valiqprod;
    }

    public String getDet_cofinsqtde_vcofins() {
        return det_cofinsqtde_vcofins;
    }

    public void setDet_cofinsqtde_vcofins(String det_cofinsqtde_vcofins) {
        this.det_cofinsqtde_vcofins = det_cofinsqtde_vcofins;
    }

    public String getDet_cofinsoutr_cst() {
        return det_cofinsoutr_cst;
    }

    public void setDet_cofinsoutr_cst(String det_cofinsoutr_cst) {
        this.det_cofinsoutr_cst = det_cofinsoutr_cst;
    }

    public String getDet_cofinsoutr_vbc() {
        return det_cofinsoutr_vbc;
    }

    public void setDet_cofinsoutr_vbc(String det_cofinsoutr_vbc) {
        this.det_cofinsoutr_vbc = det_cofinsoutr_vbc;
    }

    public String getDet_cofinsoutr_pcofins() {
        return det_cofinsoutr_pcofins;
    }

    public void setDet_cofinsoutr_pcofins(String det_cofinsoutr_pcofins) {
        this.det_cofinsoutr_pcofins = det_cofinsoutr_pcofins;
    }

    public String getDet_cofinsoutr_vcofins() {
        return det_cofinsoutr_vcofins;
    }

    public void setDet_cofinsoutr_vcofins(String det_cofinsoutr_vcofins) {
        this.det_cofinsoutr_vcofins = det_cofinsoutr_vcofins;
    }

    public String getDet_cofinsst_vbc() {
        return det_cofinsst_vbc;
    }

    public void setDet_cofinsst_vbc(String det_cofinsst_vbc) {
        this.det_cofinsst_vbc = det_cofinsst_vbc;
    }

    public String getDet_cofinsst_pcofins() {
        return det_cofinsst_pcofins;
    }

    public void setDet_cofinsst_pcofins(String det_cofinsst_pcofins) {
        this.det_cofinsst_pcofins = det_cofinsst_pcofins;
    }

    public String getDet_cofinsst_qbcprod() {
        return det_cofinsst_qbcprod;
    }

    public void setDet_cofinsst_qbcprod(String det_cofinsst_qbcprod) {
        this.det_cofinsst_qbcprod = det_cofinsst_qbcprod;
    }

    public String getDet_cofinsst_valiqprod() {
        return det_cofinsst_valiqprod;
    }

    public void setDet_cofinsst_valiqprod(String det_cofinsst_valiqprod) {
        this.det_cofinsst_valiqprod = det_cofinsst_valiqprod;
    }

    public String getDet_cofinsst_vcofins() {
        return det_cofinsst_vcofins;
    }

    public void setDet_cofinsst_vcofins(String det_cofinsst_vcofins) {
        this.det_cofinsst_vcofins = det_cofinsst_vcofins;
    }

    public String getDet_infadprod() {
        return det_infadprod;
    }

    public void setDet_infadprod(String det_infadprod) {
        this.det_infadprod = det_infadprod;
    }

    public String getDet_prod_cest() {
        return det_prod_cest;
    }

    public void setDet_prod_cest(String det_prod_cest) {
        this.det_prod_cest = det_prod_cest;
    }

    public String getDet_prod_cean() {
        return det_prod_cean;
    }

    public void setDet_prod_cean(String det_prod_cean) {
        this.det_prod_cean = det_prod_cean;
    }

    public String getDet_prod_ceantrib() {
        return det_prod_ceantrib;
    }

    public void setDet_prod_ceantrib(String det_prod_ceantrib) {
        this.det_prod_ceantrib = det_prod_ceantrib;
    }

    public String getDet_ibs_cst() {
        return det_ibs_cst;
    }

    public String getDet_ibs_cclasstrib() {
        return det_ibs_cclasstrib;
    }

    public String getDet_ibs_pibstotal() {
        return det_ibs_pibstotal;
    }

    public String getDet_ibs_pibsuf() {
        return det_ibs_pibsuf;
    }

    public String getDet_ibs_pibsmun() {
        return det_ibs_pibsmun;
    }

    public String getDet_ibscbs_vbc() {
        return det_ibscbs_vbc;
    }

    public void setDet_ibscbs_vbc(String det_ibscbs_vbc) {
        this.det_ibscbs_vbc = det_ibscbs_vbc;
    }

    public String getDet_ibsuf_vbc() {
        return det_ibsuf_vbc;
    }

    public String getDet_ibsuf_pibs() {
        return det_ibsuf_pibsuf;
    }

    public String getDet_ibsuf_vibsuf() {
        return det_ibsuf_vibsuf;
    }

    public String getDet_ibsmun_vbc() {
        return det_ibsmun_vbc;
    }

    public String getDet_ibsmun_pibsmun() {
        return det_ibsmun_pibsmun;
    }

    public String getDet_ibsmun_vibsmun() {
        return det_ibsmun_vibsmun;
    }

    public String getDet_is_cst() {
        return det_is_cst;
    }

    public String getDet_is_cclasstrib() {
        return det_is_cclasstrib;
    }

    public String getDet_is_vbcimpsel() {
        return det_is_vbcimpsel;
    }

    public String getDet_is_pimpsel() {
        return det_is_pimpsel;
    }

    public String getDet_is_vimpsel() {
        return det_is_vimpsel;
    }

    public String getDet_is_pimpselespec() {
        return det_is_pimpselespec;
    }

    public String getDet_is_utrib() {
        return det_is_utrib;
    }

    public String getDet_is_qtrib() {
        return det_is_qtrib;
    }

    public String getDet_is_cstibscbs() {
        return det_is_cstibscbs;
    }

    public String getDet_is_cclasstribibscbs() {
        return det_is_cclasstribibscbs;
    }

    public String getDet_cbs_cst() {
        return det_cbs_cst;
    }

    public String getDet_cbs_cclasstrib() {
        return det_cbs_cclasstrib;
    }

    public String getDet_cbs_pcbsref() {
        return det_cbs_pcbsref;
    }

    public String getDet_cbs_vbc() {
        return det_cbs_vbc;
    }

    public String getDet_cbs_pcbs() {
        return det_cbs_pcbs;
    }

    public String getDet_cbs_vcbs() {
        return det_cbs_vcbs;
    }

    public void setDet_ibs_cst(String det_ibs_cst) {
        this.det_ibs_cst = det_ibs_cst;
    }

    public void setDet_ibs_cclasstrib(String det_ibs_cclasstrib) {
        this.det_ibs_cclasstrib = det_ibs_cclasstrib;
    }

    public void setDet_ibs_pibstotal(String det_ibs_pibstotal) {
        this.det_ibs_pibstotal = det_ibs_pibstotal;
    }

    public void setDet_ibs_pibsuf(String det_ibs_pibsuf) {
        this.det_ibs_pibsuf = det_ibs_pibsuf;
    }

    public void setDet_ibs_pibsmun(String det_ibs_pibsmun) {
        this.det_ibs_pibsmun = det_ibs_pibsmun;
    }

    public void setDet_ibsuf_vbc(String det_ibsuf_vbc) {
        this.det_ibsuf_vbc = det_ibsuf_vbc;
    }

    public void setDet_ibsuf_pibsuf(String det_ibsuf_pibsuf) {
        this.det_ibsuf_pibsuf = det_ibsuf_pibsuf;
    }

    public void setDet_ibsuf_vibsuf(String det_ibsuf_vtribop) {
        this.det_ibsuf_vibsuf = det_ibsuf_vtribop;
    }

    public void setDet_ibsmun_vbc(String det_ibsmun_vbc) {
        this.det_ibsmun_vbc = det_ibsmun_vbc;
    }

    public void setDet_ibsmun_pibsmun(String det_ibsmun_pibsmun) {
        this.det_ibsmun_pibsmun = det_ibsmun_pibsmun;
    }

    public void setDet_ibsmun_vibsmun(String det_ibsmun_vtribop) {
        this.det_ibsmun_vibsmun = det_ibsmun_vtribop;
    }

    public void setDet_is_cst(String det_is_cst) {
        this.det_is_cst = det_is_cst;
    }

    public void setDet_is_cclasstrib(String det_is_cclasstrib) {
        this.det_is_cclasstrib = det_is_cclasstrib;
    }

    public void setDet_is_vbcimpsel(String det_is_vbcimpsel) {
        this.det_is_vbcimpsel = det_is_vbcimpsel;
    }

    public void setDet_is_pimpsel(String det_is_pimpsel) {
        this.det_is_pimpsel = det_is_pimpsel;
    }

    public void setDet_is_vimpsel(String det_is_vimpsel) {
        this.det_is_vimpsel = det_is_vimpsel;
    }

    public void setDet_is_pimpselespec(String det_is_pimpselespec) {
        this.det_is_pimpselespec = det_is_pimpselespec;
    }

    public void setDet_is_utrib(String det_is_utrib) {
        this.det_is_utrib = det_is_utrib;
    }

    public void setDet_is_qtrib(String det_is_qtrib) {
        this.det_is_qtrib = det_is_qtrib;
    }

    public void setDet_is_cstibscbs(String det_is_cstibscbs) {
        this.det_is_cstibscbs = det_is_cstibscbs;
    }

    public void setDet_is_cclasstribibscbs(String det_is_cclasstribibscbs) {
        this.det_is_cclasstribibscbs = det_is_cclasstribibscbs;
    }

    public void setDet_cbs_cst(String det_cbs_cst) {
        this.det_cbs_cst = det_cbs_cst;
    }

    public void setDet_cbs_cclasstrib(String det_cbs_cclasstrib) {
        this.det_cbs_cclasstrib = det_cbs_cclasstrib;
    }

    public void setDet_cbs_pcbsref(String det_cbs_pcbsref) {
        this.det_cbs_pcbsref = det_cbs_pcbsref;
    }

    public void setDet_cbs_vbc(String det_cbs_vbc) {
        this.det_cbs_vbc = det_cbs_vbc;
    }

    public void setDet_cbs_pcbs(String det_cbs_pcbs) {
        this.det_cbs_pcbs = det_cbs_pcbs;
    }

    public void setDet_cbs_vcbs(String det_cbs_vcbs) {
        this.det_cbs_vcbs = det_cbs_vcbs;
    }

}
