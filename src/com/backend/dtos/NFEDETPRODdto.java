package com.backend.dtos;

import com.backend.models.NFEDETPROD;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class NFEDETPRODdto {

    private Integer DET_PROD_NNF;
    private Integer DET_PROD_ITEM;
    private String DET_PROD_CPROD;
    private String DET_PROD_XPROD;
    private Long DET_PROD_NCM;
    private Integer DET_PROD_CFOP;
    private String DET_PROD_UCOM;
    private Double DET_PROD_QCOM;
    private Double DET_PROD_VUNCOM;
    private Double DET_PROD_VPROD;
    private String DET_PROD_UTRIB;
    private Double DET_PROD_QTRIB;
    private Double DET_PROD_VUNTRIB;
    private Double DET_PROD_VFRETE;
    private Double DET_PROD_VSEG;
    private Double DET_PROD_VDESC;
    private Double DET_PROD_VOUTRO;
    private Integer DET_PROD_INDTOT;
    private Double DET_IMPOSTO_VTOTTRIB;
    private Integer DET_ICMS_CRT;
    private Integer DET_ICMS_CST;
    private Integer DET_ICMS_ORIG;
    private Integer DET_ICMS00_ORIG;
    private Integer DET_ICMS00_CST;
    private Integer DET_ICMS00_MODBC;
    private Double DET_ICMS00_VBC;
    private Double DET_ICMS00_PICMS;
    private Double DET_ICMS00_VICMS;
    private Integer DET_ICMS10_ORIG;
    private Integer DET_ICMS10_CST;
    private Integer DET_ICMS10_MODBC;
    private Double DET_ICMS10_VBC;
    private Double DET_ICMS10_PICMS;
    private Double DET_ICMS10_VICMS;
    private Integer DET_ICMS10_MODBCST;
    private Double DET_ICMS10_PMVAST;
    private Double DET_ICMS10_PREDBCST;
    private Double DET_ICMS10_VBCST;
    private Double DET_ICMS10_PICMSST;
    private Double DET_ICMS10_VICMSST;
    private Integer DET_ICMS20_ORIG;
    private Integer DET_ICMS20_CST;
    private Integer DET_ICMS20_MODBC;
    private Double DET_ICMS20_PREDBC;
    private Double DET_ICMS20_VBC;
    private Double DET_ICMS20_PICMS;
    private Double DET_ICMS20_VICMS;
    private Double DET_ICMS20_VICMSDESON;
    private Integer DET_ICMS20_MOTDESICMS;
    private Integer DET_ICMS30_ORIG;
    private Integer DET_ICMS30_CST;
    private Integer DET_ICMS30_MODBCST;
    private Double DET_ICMS30_PMVAST;
    private Double DET_ICMS30_PREDBCST;
    private Double DET_ICMS30_VBCST;
    private Double DET_ICMS30_PICMSST;
    private Double DET_ICMS30_VICMSST;
    private Double DET_ICMS30_VICMSDESON;
    private Integer DET_ICMS30_MOTDESICMS;
    private Integer DET_ICMS40_ORIG;
    private Integer DET_ICMS40_CST;
    private Double DET_ICMS40_VICMSDESON;
    private Integer DET_ICMS40_MOTDESICMS;
    private Integer DET_ICMS51_ORIG;
    private Integer DET_ICMS51_CST;
    private Integer DET_ICMS51_MODBC;
    private Double DET_ICMS51_PREDBC;
    private Double DET_ICMS51_VBC;
    private Double DET_ICMS51_PICMS;
    private Double DET_ICMS51_VICMSOP;
    private Double DET_ICMS51_PDIF;
    private Double DET_ICMS51_VICMSDIF;
    private Double DET_ICMS51_VICMS;
    private Integer DET_ICMS60_ORIG;
    private Integer DET_ICMS60_CST;
    private Double DET_ICMS60_VBCSTRET;
    private Double DET_ICMS60_VICMSSTRET;
    private Integer DET_ICMS70_ORIG;
    private Integer DET_ICMS70_CST;
    private Integer DET_ICMS70_MODBC;
    private Double DET_ICMS70_PREDBC;
    private Double DET_ICMS70_VBC;
    private Double DET_ICMS70_PICMS;
    private Double DET_ICMS70_VICMS;
    private Integer DET_ICMS70_MODBCST;
    private Double DET_ICMS70_PMVAST;
    private Double DET_ICMS70_PREDBCST;
    private Double DET_ICMS70_VBCST;
    private Double DET_ICMS70_PICMSST;
    private Double DET_ICMS70_VICMSST;
    private Double DET_ICMS70_VICMSDESON;
    private Integer DET_ICMS70_MOTDESICMS;
    private Integer DET_ICMS90_ORIG;
    private Integer DET_ICMS90_CST;
    private Integer DET_ICMS90_MODBC;
    private Double DET_ICMS90_VBC;
    private Double DET_ICMS90_PREDBC;
    private Double DET_ICMS90_PICMS;
    private Double DET_ICMS90_VICMS;
    private Integer DET_ICMS90_MODBCST;
    private Double DET_ICMS90_PMVAST;
    private Double DET_ICMS90_PREDBCST;
    private Double DET_ICMS90_VBCST;
    private Double DET_ICMS90_PICMSST;
    private Double DET_ICMS90_VICMSST;
    private Double DET_ICMS90_VICMSDESON;
    private Integer DET_ICMS90_MOTDESICMS;
    private Integer DET_IPI_CLENQ;
    private Long DET_IPI_CNPJPROD;
    private String DET_IPI_CSELO;
    private Long DET_IPI_QSELO;
    private Integer DET_IPI_CENQ;
    private Integer DET_IPITRIB_CST;
    private Double DET_IPITRIB_VBC;
    private Double DET_IPITRIB_PIPI;
    private Double DET_IPITRIB_QUNID;
    private Double DET_IPITRIB_VUNID;
    private Double DET_IPITRIB_VIPI;
    private Integer DET_PISALIQ_CST;
    private Double DET_PISALIQ_VBC;
    private Double DET_PISALIQ_PPIS;
    private Double DET_PISALIQ_VPIS;
    private Integer DET_PISQTDE_CST;
    private Double DET_PISQTDE_QBCPROD;
    private Double DET_PISQTDE_VALIQPROD;
    private Double DET_PISQTDE_VPIS;
    private Integer DET_PISOUTR_CST;
    private Double DET_PISOUTR_VBC;
    private Double DET_PISOUTR_PPIS;
    private Double DET_PISOUTR_VPIS;
    private Double DET_PISST_VBC;
    private Double DET_PISST_PPIS;
    private Double DET_PISST_QBCPROD;
    private Double DET_PISST_VALIQPROD;
    private Double DET_PISST_VPIS;
    private Integer DET_COFINSALIQ_CST;
    private Double DET_COFINSALIQ_VBC;
    private Double DET_COFINSALIQ_PCOFINS;
    private Double DET_COFINSALIQ_VCOFINS;
    private Integer DET_COFINSQTDE_CST;
    private Double DET_COFINSQTDE_QBCPROD;
    private Double DET_COFINSQTDE_VALIQPROD;
    private Double DET_COFINSQTDE_VCOFINS;
    private Integer DET_COFINSOUTR_CST;
    private Double DET_COFINSOUTR_VBC;
    private Double DET_COFINSOUTR_PCOFINS;
    private Double DET_COFINSOUTR_VCOFINS;
    private Double DET_COFINSST_VBC;
    private Double DET_COFINSST_PCOFINS;
    private Double DET_COFINSST_QBCPROD;
    private Double DET_COFINSST_VALIQPROD;
    private Double DET_COFINSST_VCOFINS;
    private String DET_INFADPROD;
    private Long DET_PROD_CEST;
    private String DET_PROD_CEAN;
    private String DET_PROD_CEANTRIB;
    private String DET_IBS_CST;
    private String DET_IBS_CCLASSTRIB;
    private Double DET_IBS_PIBSTOTAL;
    private Double DET_IBS_PIBSUF;
    private Double DET_IBS_PIBSMUN;
    private Double DET_IBSUF_VBC;
    private Double DET_IBSUF_PIBSUF;
    private Double DET_IBSUF_VIBSUF;
    private Double DET_IBSMUN_VBC;
    private Double DET_IBSMUN_PIBSMUN;
    private Double DET_IBSMUN_VIBSMUN;
    private Integer DET_IS_CST;
    private Long DET_IS_CCLASSTRIB;
    private Double DET_IS_VBCIMPSEL;
    private Double DET_IS_PIMPSEL;
    private Double DET_IS_VIMPSEL;
    private Double DET_IS_PIMPSELESPEC;
    private String DET_IS_UTRIB;
    private Double DET_IS_QTRIB;
    private Integer DET_IS_CSTIBSCBS;
    private Long DET_IS_CCLASSTRIBIBSCBS;
    private Integer DET_CBS_CST;
    private Long DET_CBS_CCLASSTRIB;
    private Double DET_CBS_PCBSREF;
    private Double DET_CBS_VBC;
    private Double DET_CBS_PCBS;
    private Double DET_CBS_VCBS;
    private List<NFEDETPRODdto> NFEDETPRODdto;

    public Integer getDET_PROD_NNF() {
        return DET_PROD_NNF;
    }

    public Integer getDET_PROD_ITEM() {
        return DET_PROD_ITEM;
    }

    public String getDET_PROD_CPROD() {
        return DET_PROD_CPROD;
    }

    public String getDET_PROD_XPROD() {
        return DET_PROD_XPROD;
    }

    public Long getDET_PROD_NCM() {
        return DET_PROD_NCM;
    }

    public Integer getDET_PROD_CFOP() {
        return DET_PROD_CFOP;
    }

    public String getDET_PROD_UCOM() {
        return DET_PROD_UCOM;
    }

    public Double getDET_PROD_QCOM() {
        return DET_PROD_QCOM;
    }

    public Double getDET_PROD_VUNCOM() {
        return DET_PROD_VUNCOM;
    }

    public Double getDET_PROD_VPROD() {
        return DET_PROD_VPROD;
    }

    public String getDET_PROD_UTRIB() {
        return DET_PROD_UTRIB;
    }

    public Double getDET_PROD_QTRIB() {
        return DET_PROD_QTRIB;
    }

    public Double getDET_PROD_VUNTRIB() {
        return DET_PROD_VUNTRIB;
    }

    public Double getDET_PROD_VFRETE() {
        return DET_PROD_VFRETE;
    }

    public Double getDET_PROD_VSEG() {
        return DET_PROD_VSEG;
    }

    public Double getDET_PROD_VDESC() {
        return DET_PROD_VDESC;
    }

    public Double getDET_PROD_VOUTRO() {
        return DET_PROD_VOUTRO;
    }

    public Integer getDET_PROD_INDTOT() {
        return DET_PROD_INDTOT;
    }

    public Double getDET_IMPOSTO_VTOTTRIB() {
        return DET_IMPOSTO_VTOTTRIB;
    }

    public Integer getDET_ICMS_CRT() {
        return DET_ICMS_CRT;
    }

    public Integer getDET_ICMS_CST() {
        return DET_ICMS_CST;
    }

    public Integer getDET_ICMS_ORIG() {
        return DET_ICMS_ORIG;
    }

    public Integer getDET_ICMS00_ORIG() {
        return DET_ICMS00_ORIG;
    }

    public Integer getDET_ICMS00_CST() {
        return DET_ICMS00_CST;
    }

    public Integer getDET_ICMS00_MODBC() {
        return DET_ICMS00_MODBC;
    }

    public Double getDET_ICMS00_VBC() {
        return DET_ICMS00_VBC;
    }

    public Double getDET_ICMS00_PICMS() {
        return DET_ICMS00_PICMS;
    }

    public Double getDET_ICMS00_VICMS() {
        return DET_ICMS00_VICMS;
    }

    public Integer getDET_ICMS10_ORIG() {
        return DET_ICMS10_ORIG;
    }

    public Integer getDET_ICMS10_CST() {
        return DET_ICMS10_CST;
    }

    public Integer getDET_ICMS10_MODBC() {
        return DET_ICMS10_MODBC;
    }

    public Double getDET_ICMS10_VBC() {
        return DET_ICMS10_VBC;
    }

    public Double getDET_ICMS10_PICMS() {
        return DET_ICMS10_PICMS;
    }

    public Double getDET_ICMS10_VICMS() {
        return DET_ICMS10_VICMS;
    }

    public Integer getDET_ICMS10_MODBCST() {
        return DET_ICMS10_MODBCST;
    }

    public Double getDET_ICMS10_PMVAST() {
        return DET_ICMS10_PMVAST;
    }

    public Double getDET_ICMS10_PREDBCST() {
        return DET_ICMS10_PREDBCST;
    }

    public Double getDET_ICMS10_VBCST() {
        return DET_ICMS10_VBCST;
    }

    public Double getDET_ICMS10_PICMSST() {
        return DET_ICMS10_PICMSST;
    }

    public Double getDET_ICMS10_VICMSST() {
        return DET_ICMS10_VICMSST;
    }

    public Integer getDET_ICMS20_ORIG() {
        return DET_ICMS20_ORIG;
    }

    public Integer getDET_ICMS20_CST() {
        return DET_ICMS20_CST;
    }

    public Integer getDET_ICMS20_MODBC() {
        return DET_ICMS20_MODBC;
    }

    public Double getDET_ICMS20_PREDBC() {
        return DET_ICMS20_PREDBC;
    }

    public Double getDET_ICMS20_VBC() {
        return DET_ICMS20_VBC;
    }

    public Double getDET_ICMS20_PICMS() {
        return DET_ICMS20_PICMS;
    }

    public Double getDET_ICMS20_VICMS() {
        return DET_ICMS20_VICMS;
    }

    public Double getDET_ICMS20_VICMSDESON() {
        return DET_ICMS20_VICMSDESON;
    }

    public Integer getDET_ICMS20_MOTDESICMS() {
        return DET_ICMS20_MOTDESICMS;
    }

    public Integer getDET_ICMS30_ORIG() {
        return DET_ICMS30_ORIG;
    }

    public Integer getDET_ICMS30_CST() {
        return DET_ICMS30_CST;
    }

    public Integer getDET_ICMS30_MODBCST() {
        return DET_ICMS30_MODBCST;
    }

    public Double getDET_ICMS30_PMVAST() {
        return DET_ICMS30_PMVAST;
    }

    public Double getDET_ICMS30_PREDBCST() {
        return DET_ICMS30_PREDBCST;
    }

    public Double getDET_ICMS30_VBCST() {
        return DET_ICMS30_VBCST;
    }

    public Double getDET_ICMS30_PICMSST() {
        return DET_ICMS30_PICMSST;
    }

    public Double getDET_ICMS30_VICMSST() {
        return DET_ICMS30_VICMSST;
    }

    public Double getDET_ICMS30_VICMSDESON() {
        return DET_ICMS30_VICMSDESON;
    }

    public Integer getDET_ICMS30_MOTDESICMS() {
        return DET_ICMS30_MOTDESICMS;
    }

    public Integer getDET_ICMS40_ORIG() {
        return DET_ICMS40_ORIG;
    }

    public Integer getDET_ICMS40_CST() {
        return DET_ICMS40_CST;
    }

    public Double getDET_ICMS40_VICMSDESON() {
        return DET_ICMS40_VICMSDESON;
    }

    public Integer getDET_ICMS40_MOTDESICMS() {
        return DET_ICMS40_MOTDESICMS;
    }

    public Integer getDET_ICMS51_ORIG() {
        return DET_ICMS51_ORIG;
    }

    public Integer getDET_ICMS51_CST() {
        return DET_ICMS51_CST;
    }

    public Integer getDET_ICMS51_MODBC() {
        return DET_ICMS51_MODBC;
    }

    public Double getDET_ICMS51_PREDBC() {
        return DET_ICMS51_PREDBC;
    }

    public Double getDET_ICMS51_VBC() {
        return DET_ICMS51_VBC;
    }

    public Double getDET_ICMS51_PICMS() {
        return DET_ICMS51_PICMS;
    }

    public Double getDET_ICMS51_VICMSOP() {
        return DET_ICMS51_VICMSOP;
    }

    public Double getDET_ICMS51_PDIF() {
        return DET_ICMS51_PDIF;
    }

    public Double getDET_ICMS51_VICMSDIF() {
        return DET_ICMS51_VICMSDIF;
    }

    public Double getDET_ICMS51_VICMS() {
        return DET_ICMS51_VICMS;
    }

    public Integer getDET_ICMS60_ORIG() {
        return DET_ICMS60_ORIG;
    }

    public Integer getDET_ICMS60_CST() {
        return DET_ICMS60_CST;
    }

    public Double getDET_ICMS60_VBCSTRET() {
        return DET_ICMS60_VBCSTRET;
    }

    public Double getDET_ICMS60_VICMSSTRET() {
        return DET_ICMS60_VICMSSTRET;
    }

    public Integer getDET_ICMS70_ORIG() {
        return DET_ICMS70_ORIG;
    }

    public Integer getDET_ICMS70_CST() {
        return DET_ICMS70_CST;
    }

    public Integer getDET_ICMS70_MODBC() {
        return DET_ICMS70_MODBC;
    }

    public Double getDET_ICMS70_PREDBC() {
        return DET_ICMS70_PREDBC;
    }

    public Double getDET_ICMS70_VBC() {
        return DET_ICMS70_VBC;
    }

    public Double getDET_ICMS70_PICMS() {
        return DET_ICMS70_PICMS;
    }

    public Double getDET_ICMS70_VICMS() {
        return DET_ICMS70_VICMS;
    }

    public Integer getDET_ICMS70_MODBCST() {
        return DET_ICMS70_MODBCST;
    }

    public Double getDET_ICMS70_PMVAST() {
        return DET_ICMS70_PMVAST;
    }

    public Double getDET_ICMS70_PREDBCST() {
        return DET_ICMS70_PREDBCST;
    }

    public Double getDET_ICMS70_VBCST() {
        return DET_ICMS70_VBCST;
    }

    public Double getDET_ICMS70_PICMSST() {
        return DET_ICMS70_PICMSST;
    }

    public Double getDET_ICMS70_VICMSST() {
        return DET_ICMS70_VICMSST;
    }

    public Double getDET_ICMS70_VICMSDESON() {
        return DET_ICMS70_VICMSDESON;
    }

    public Integer getDET_ICMS70_MOTDESICMS() {
        return DET_ICMS70_MOTDESICMS;
    }

    public Integer getDET_ICMS90_ORIG() {
        return DET_ICMS90_ORIG;
    }

    public Integer getDET_ICMS90_CST() {
        return DET_ICMS90_CST;
    }

    public Integer getDET_ICMS90_MODBC() {
        return DET_ICMS90_MODBC;
    }

    public Double getDET_ICMS90_VBC() {
        return DET_ICMS90_VBC;
    }

    public Double getDET_ICMS90_PREDBC() {
        return DET_ICMS90_PREDBC;
    }

    public Double getDET_ICMS90_PICMS() {
        return DET_ICMS90_PICMS;
    }

    public Double getDET_ICMS90_VICMS() {
        return DET_ICMS90_VICMS;
    }

    public Integer getDET_ICMS90_MODBCST() {
        return DET_ICMS90_MODBCST;
    }

    public Double getDET_ICMS90_PMVAST() {
        return DET_ICMS90_PMVAST;
    }

    public Double getDET_ICMS90_PREDBCST() {
        return DET_ICMS90_PREDBCST;
    }

    public Double getDET_ICMS90_VBCST() {
        return DET_ICMS90_VBCST;
    }

    public Double getDET_ICMS90_PICMSST() {
        return DET_ICMS90_PICMSST;
    }

    public Double getDET_ICMS90_VICMSST() {
        return DET_ICMS90_VICMSST;
    }

    public Double getDET_ICMS90_VICMSDESON() {
        return DET_ICMS90_VICMSDESON;
    }

    public Integer getDET_ICMS90_MOTDESICMS() {
        return DET_ICMS90_MOTDESICMS;
    }

    public Integer getDET_IPI_CLENQ() {
        return DET_IPI_CLENQ;
    }

    public Long getDET_IPI_CNPJPROD() {
        return DET_IPI_CNPJPROD;
    }

    public String getDET_IPI_CSELO() {
        return DET_IPI_CSELO;
    }

    public Long getDET_IPI_QSELO() {
        return DET_IPI_QSELO;
    }

    public Integer getDET_IPI_CENQ() {
        return DET_IPI_CENQ;
    }

    public Integer getDET_IPITRIB_CST() {
        return DET_IPITRIB_CST;
    }

    public Double getDET_IPITRIB_VBC() {
        return DET_IPITRIB_VBC;
    }

    public Double getDET_IPITRIB_PIPI() {
        return DET_IPITRIB_PIPI;
    }

    public Double getDET_IPITRIB_QUNID() {
        return DET_IPITRIB_QUNID;
    }

    public Double getDET_IPITRIB_VUNID() {
        return DET_IPITRIB_VUNID;
    }

    public Double getDET_IPITRIB_VIPI() {
        return DET_IPITRIB_VIPI;
    }

    public Integer getDET_PISALIQ_CST() {
        return DET_PISALIQ_CST;
    }

    public Double getDET_PISALIQ_VBC() {
        return DET_PISALIQ_VBC;
    }

    public Double getDET_PISALIQ_PPIS() {
        return DET_PISALIQ_PPIS;
    }

    public Double getDET_PISALIQ_VPIS() {
        return DET_PISALIQ_VPIS;
    }

    public Integer getDET_PISQTDE_CST() {
        return DET_PISQTDE_CST;
    }

    public Double getDET_PISQTDE_QBCPROD() {
        return DET_PISQTDE_QBCPROD;
    }

    public Double getDET_PISQTDE_VALIQPROD() {
        return DET_PISQTDE_VALIQPROD;
    }

    public Double getDET_PISQTDE_VPIS() {
        return DET_PISQTDE_VPIS;
    }

    public Integer getDET_PISOUTR_CST() {
        return DET_PISOUTR_CST;
    }

    public Double getDET_PISOUTR_VBC() {
        return DET_PISOUTR_VBC;
    }

    public Double getDET_PISOUTR_PPIS() {
        return DET_PISOUTR_PPIS;
    }

    public Double getDET_PISOUTR_VPIS() {
        return DET_PISOUTR_VPIS;
    }

    public Double getDET_PISST_VBC() {
        return DET_PISST_VBC;
    }

    public Double getDET_PISST_PPIS() {
        return DET_PISST_PPIS;
    }

    public Double getDET_PISST_QBCPROD() {
        return DET_PISST_QBCPROD;
    }

    public Double getDET_PISST_VALIQPROD() {
        return DET_PISST_VALIQPROD;
    }

    public Double getDET_PISST_VPIS() {
        return DET_PISST_VPIS;
    }

    public Integer getDET_COFINSALIQ_CST() {
        return DET_COFINSALIQ_CST;
    }

    public Double getDET_COFINSALIQ_VBC() {
        return DET_COFINSALIQ_VBC;
    }

    public Double getDET_COFINSALIQ_PCOFINS() {
        return DET_COFINSALIQ_PCOFINS;
    }

    public Double getDET_COFINSALIQ_VCOFINS() {
        return DET_COFINSALIQ_VCOFINS;
    }

    public Integer getDET_COFINSQTDE_CST() {
        return DET_COFINSQTDE_CST;
    }

    public Double getDET_COFINSQTDE_QBCPROD() {
        return DET_COFINSQTDE_QBCPROD;
    }

    public Double getDET_COFINSQTDE_VALIQPROD() {
        return DET_COFINSQTDE_VALIQPROD;
    }

    public Double getDET_COFINSQTDE_VCOFINS() {
        return DET_COFINSQTDE_VCOFINS;
    }

    public Integer getDET_COFINSOUTR_CST() {
        return DET_COFINSOUTR_CST;
    }

    public Double getDET_COFINSOUTR_VBC() {
        return DET_COFINSOUTR_VBC;
    }

    public Double getDET_COFINSOUTR_PCOFINS() {
        return DET_COFINSOUTR_PCOFINS;
    }

    public Double getDET_COFINSOUTR_VCOFINS() {
        return DET_COFINSOUTR_VCOFINS;
    }

    public Double getDET_COFINSST_VBC() {
        return DET_COFINSST_VBC;
    }

    public Double getDET_COFINSST_PCOFINS() {
        return DET_COFINSST_PCOFINS;
    }

    public Double getDET_COFINSST_QBCPROD() {
        return DET_COFINSST_QBCPROD;
    }

    public Double getDET_COFINSST_VALIQPROD() {
        return DET_COFINSST_VALIQPROD;
    }

    public Double getDET_COFINSST_VCOFINS() {
        return DET_COFINSST_VCOFINS;
    }

    public String getDET_INFADPROD() {
        return DET_INFADPROD;
    }

    public Long getDET_PROD_CEST() {
        return DET_PROD_CEST;
    }

    public String getDET_PROD_CEAN() {
        return DET_PROD_CEAN;
    }

    public String getDET_PROD_CEANTRIB() {
        return DET_PROD_CEANTRIB;
    }

    public String getDET_IBS_CST() {
        return DET_IBS_CST;
    }

    public String getDET_IBS_CCLASSTRIB() {
        return DET_IBS_CCLASSTRIB;
    }

    public Double getDET_IBS_PIBSTOTAL() {
        return DET_IBS_PIBSTOTAL;
    }

    public Double getDET_IBS_PIBSUF() {
        return DET_IBS_PIBSUF;
    }

    public Double getDET_IBS_PIBSMUN() {
        return DET_IBS_PIBSMUN;
    }

    public Double getDET_IBSUF_VBC() {
        return DET_IBSUF_VBC;
    }

    public Double getDET_IBSUF_PIBSUF() {
        return DET_IBSUF_PIBSUF;
    }

    public Double getDET_IBSUF_VIBSUF() {
        return DET_IBSUF_VIBSUF;
    }

    public Double getDET_IBSMUN_VBC() {
        return DET_IBSMUN_VBC;
    }

    public Double getDET_IBSMUN_PIBSMUN() {
        return DET_IBSMUN_PIBSMUN;
    }

    public Double getDET_IBSMUN_VIBSMUN() {
        return DET_IBSMUN_VIBSMUN;
    }

    public Integer getDET_IS_CST() {
        return DET_IS_CST;
    }

    public Long getDET_IS_CCLASSTRIB() {
        return DET_IS_CCLASSTRIB;
    }

    public Double getDET_IS_VBCIMPSEL() {
        return DET_IS_VBCIMPSEL;
    }

    public Double getDET_IS_PIMPSEL() {
        return DET_IS_PIMPSEL;
    }

    public Double getDET_IS_VIMPSEL() {
        return DET_IS_VIMPSEL;
    }

    public Double getDET_IS_PIMPSELESPEC() {
        return DET_IS_PIMPSELESPEC;
    }

    public String getDET_IS_UTRIB() {
        return DET_IS_UTRIB;
    }

    public Double getDET_IS_QTRIB() {
        return DET_IS_QTRIB;
    }

    public Integer getDET_IS_CSTIBSCBS() {
        return DET_IS_CSTIBSCBS;
    }

    public Long getDET_IS_CCLASSTRIBIBSCBS() {
        return DET_IS_CCLASSTRIBIBSCBS;
    }

    public Integer getDET_CBS_CST() {
        return DET_CBS_CST;
    }

    public Long getDET_CBS_CCLASSTRIB() {
        return DET_CBS_CCLASSTRIB;
    }

    public Double getDET_CBS_PCBSREF() {
        return DET_CBS_PCBSREF;
    }

    public Double getDET_CBS_VBC() {
        return DET_CBS_VBC;
    }

    public Double getDET_CBS_PCBS() {
        return DET_CBS_PCBS;
    }

    public Double getDET_CBS_VCBS() {
        return DET_CBS_VCBS;
    }

    public void setDET_PROD_NNF(Integer DET_PROD_NNF) {
        this.DET_PROD_NNF = DET_PROD_NNF;
    }

    public void setDET_PROD_ITEM(Integer DET_PROD_ITEM) {
        this.DET_PROD_ITEM = DET_PROD_ITEM;
    }

    public void setDET_PROD_CPROD(String DET_PROD_CPROD) {
        this.DET_PROD_CPROD = DET_PROD_CPROD;
    }

    public void setDET_PROD_XPROD(String DET_PROD_XPROD) {
        this.DET_PROD_XPROD = DET_PROD_XPROD;
    }

    public void setDET_PROD_NCM(Long DET_PROD_NCM) {
        this.DET_PROD_NCM = DET_PROD_NCM;
    }

    public void setDET_PROD_CFOP(Integer DET_PROD_CFOP) {
        this.DET_PROD_CFOP = DET_PROD_CFOP;
    }

    public void setDET_PROD_UCOM(String DET_PROD_UCOM) {
        this.DET_PROD_UCOM = DET_PROD_UCOM;
    }

    public void setDET_PROD_QCOM(Double DET_PROD_QCOM) {
        this.DET_PROD_QCOM = DET_PROD_QCOM;
    }

    public void setDET_PROD_VUNCOM(Double DET_PROD_VUNCOM) {
        this.DET_PROD_VUNCOM = DET_PROD_VUNCOM;
    }

    public void setDET_PROD_VPROD(Double DET_PROD_VPROD) {
        this.DET_PROD_VPROD = DET_PROD_VPROD;
    }

    public void setDET_PROD_UTRIB(String DET_PROD_UTRIB) {
        this.DET_PROD_UTRIB = DET_PROD_UTRIB;
    }

    public void setDET_PROD_QTRIB(Double DET_PROD_QTRIB) {
        this.DET_PROD_QTRIB = DET_PROD_QTRIB;
    }

    public void setDET_PROD_VUNTRIB(Double DET_PROD_VUNTRIB) {
        this.DET_PROD_VUNTRIB = DET_PROD_VUNTRIB;
    }

    public void setDET_PROD_VFRETE(Double DET_PROD_VFRETE) {
        this.DET_PROD_VFRETE = DET_PROD_VFRETE;
    }

    public void setDET_PROD_VSEG(Double DET_PROD_VSEG) {
        this.DET_PROD_VSEG = DET_PROD_VSEG;
    }

    public void setDET_PROD_VDESC(Double DET_PROD_VDESC) {
        this.DET_PROD_VDESC = DET_PROD_VDESC;
    }

    public void setDET_PROD_VOUTRO(Double DET_PROD_VOUTRO) {
        this.DET_PROD_VOUTRO = DET_PROD_VOUTRO;
    }

    public void setDET_PROD_INDTOT(Integer DET_PROD_INDTOT) {
        this.DET_PROD_INDTOT = DET_PROD_INDTOT;
    }

    public void setDET_IMPOSTO_VTOTTRIB(Double DET_IMPOSTO_VTOTTRIB) {
        this.DET_IMPOSTO_VTOTTRIB = DET_IMPOSTO_VTOTTRIB;
    }

    public void setDET_ICMS_CRT(Integer DET_ICMS_CRT) {
        this.DET_ICMS_CRT = DET_ICMS_CRT;
    }

    public void setDET_ICMS_CST(Integer DET_ICMS_CST) {
        this.DET_ICMS_CST = DET_ICMS_CST;
    }

    public void setDET_ICMS_ORIG(Integer DET_ICMS_ORIG) {
        this.DET_ICMS_ORIG = DET_ICMS_ORIG;
    }

    public void setDET_ICMS00_ORIG(Integer DET_ICMS00_ORIG) {
        this.DET_ICMS00_ORIG = DET_ICMS00_ORIG;
    }

    public void setDET_ICMS00_CST(Integer DET_ICMS00_CST) {
        this.DET_ICMS00_CST = DET_ICMS00_CST;
    }

    public void setDET_ICMS00_MODBC(Integer DET_ICMS00_MODBC) {
        this.DET_ICMS00_MODBC = DET_ICMS00_MODBC;
    }

    public void setDET_ICMS00_VBC(Double DET_ICMS00_VBC) {
        this.DET_ICMS00_VBC = DET_ICMS00_VBC;
    }

    public void setDET_ICMS00_PICMS(Double DET_ICMS00_PICMS) {
        this.DET_ICMS00_PICMS = DET_ICMS00_PICMS;
    }

    public void setDET_ICMS00_VICMS(Double DET_ICMS00_VICMS) {
        this.DET_ICMS00_VICMS = DET_ICMS00_VICMS;
    }

    public void setDET_ICMS10_ORIG(Integer DET_ICMS10_ORIG) {
        this.DET_ICMS10_ORIG = DET_ICMS10_ORIG;
    }

    public void setDET_ICMS10_CST(Integer DET_ICMS10_CST) {
        this.DET_ICMS10_CST = DET_ICMS10_CST;
    }

    public void setDET_ICMS10_MODBC(Integer DET_ICMS10_MODBC) {
        this.DET_ICMS10_MODBC = DET_ICMS10_MODBC;
    }

    public void setDET_ICMS10_VBC(Double DET_ICMS10_VBC) {
        this.DET_ICMS10_VBC = DET_ICMS10_VBC;
    }

    public void setDET_ICMS10_PICMS(Double DET_ICMS10_PICMS) {
        this.DET_ICMS10_PICMS = DET_ICMS10_PICMS;
    }

    public void setDET_ICMS10_VICMS(Double DET_ICMS10_VICMS) {
        this.DET_ICMS10_VICMS = DET_ICMS10_VICMS;
    }

    public void setDET_ICMS10_MODBCST(Integer DET_ICMS10_MODBCST) {
        this.DET_ICMS10_MODBCST = DET_ICMS10_MODBCST;
    }

    public void setDET_ICMS10_PMVAST(Double DET_ICMS10_PMVAST) {
        this.DET_ICMS10_PMVAST = DET_ICMS10_PMVAST;
    }

    public void setDET_ICMS10_PREDBCST(Double DET_ICMS10_PREDBCST) {
        this.DET_ICMS10_PREDBCST = DET_ICMS10_PREDBCST;
    }

    public void setDET_ICMS10_VBCST(Double DET_ICMS10_VBCST) {
        this.DET_ICMS10_VBCST = DET_ICMS10_VBCST;
    }

    public void setDET_ICMS10_PICMSST(Double DET_ICMS10_PICMSST) {
        this.DET_ICMS10_PICMSST = DET_ICMS10_PICMSST;
    }

    public void setDET_ICMS10_VICMSST(Double DET_ICMS10_VICMSST) {
        this.DET_ICMS10_VICMSST = DET_ICMS10_VICMSST;
    }

    public void setDET_ICMS20_ORIG(Integer DET_ICMS20_ORIG) {
        this.DET_ICMS20_ORIG = DET_ICMS20_ORIG;
    }

    public void setDET_ICMS20_CST(Integer DET_ICMS20_CST) {
        this.DET_ICMS20_CST = DET_ICMS20_CST;
    }

    public void setDET_ICMS20_MODBC(Integer DET_ICMS20_MODBC) {
        this.DET_ICMS20_MODBC = DET_ICMS20_MODBC;
    }

    public void setDET_ICMS20_PREDBC(Double DET_ICMS20_PREDBC) {
        this.DET_ICMS20_PREDBC = DET_ICMS20_PREDBC;
    }

    public void setDET_ICMS20_VBC(Double DET_ICMS20_VBC) {
        this.DET_ICMS20_VBC = DET_ICMS20_VBC;
    }

    public void setDET_ICMS20_PICMS(Double DET_ICMS20_PICMS) {
        this.DET_ICMS20_PICMS = DET_ICMS20_PICMS;
    }

    public void setDET_ICMS20_VICMS(Double DET_ICMS20_VICMS) {
        this.DET_ICMS20_VICMS = DET_ICMS20_VICMS;
    }

    public void setDET_ICMS20_VICMSDESON(Double DET_ICMS20_VICMSDESON) {
        this.DET_ICMS20_VICMSDESON = DET_ICMS20_VICMSDESON;
    }

    public void setDET_ICMS20_MOTDESICMS(Integer DET_ICMS20_MOTDESICMS) {
        this.DET_ICMS20_MOTDESICMS = DET_ICMS20_MOTDESICMS;
    }

    public void setDET_ICMS30_ORIG(Integer DET_ICMS30_ORIG) {
        this.DET_ICMS30_ORIG = DET_ICMS30_ORIG;
    }

    public void setDET_ICMS30_CST(Integer DET_ICMS30_CST) {
        this.DET_ICMS30_CST = DET_ICMS30_CST;
    }

    public void setDET_ICMS30_MODBCST(Integer DET_ICMS30_MODBCST) {
        this.DET_ICMS30_MODBCST = DET_ICMS30_MODBCST;
    }

    public void setDET_ICMS30_PMVAST(Double DET_ICMS30_PMVAST) {
        this.DET_ICMS30_PMVAST = DET_ICMS30_PMVAST;
    }

    public void setDET_ICMS30_PREDBCST(Double DET_ICMS30_PREDBCST) {
        this.DET_ICMS30_PREDBCST = DET_ICMS30_PREDBCST;
    }

    public void setDET_ICMS30_VBCST(Double DET_ICMS30_VBCST) {
        this.DET_ICMS30_VBCST = DET_ICMS30_VBCST;
    }

    public void setDET_ICMS30_PICMSST(Double DET_ICMS30_PICMSST) {
        this.DET_ICMS30_PICMSST = DET_ICMS30_PICMSST;
    }

    public void setDET_ICMS30_VICMSST(Double DET_ICMS30_VICMSST) {
        this.DET_ICMS30_VICMSST = DET_ICMS30_VICMSST;
    }

    public void setDET_ICMS30_VICMSDESON(Double DET_ICMS30_VICMSDESON) {
        this.DET_ICMS30_VICMSDESON = DET_ICMS30_VICMSDESON;
    }

    public void setDET_ICMS30_MOTDESICMS(Integer DET_ICMS30_MOTDESICMS) {
        this.DET_ICMS30_MOTDESICMS = DET_ICMS30_MOTDESICMS;
    }

    public void setDET_ICMS40_ORIG(Integer DET_ICMS40_ORIG) {
        this.DET_ICMS40_ORIG = DET_ICMS40_ORIG;
    }

    public void setDET_ICMS40_CST(Integer DET_ICMS40_CST) {
        this.DET_ICMS40_CST = DET_ICMS40_CST;
    }

    public void setDET_ICMS40_VICMSDESON(Double DET_ICMS40_VICMSDESON) {
        this.DET_ICMS40_VICMSDESON = DET_ICMS40_VICMSDESON;
    }

    public void setDET_ICMS40_MOTDESICMS(Integer DET_ICMS40_MOTDESICMS) {
        this.DET_ICMS40_MOTDESICMS = DET_ICMS40_MOTDESICMS;
    }

    public void setDET_ICMS51_ORIG(Integer DET_ICMS51_ORIG) {
        this.DET_ICMS51_ORIG = DET_ICMS51_ORIG;
    }

    public void setDET_ICMS51_CST(Integer DET_ICMS51_CST) {
        this.DET_ICMS51_CST = DET_ICMS51_CST;
    }

    public void setDET_ICMS51_MODBC(Integer DET_ICMS51_MODBC) {
        this.DET_ICMS51_MODBC = DET_ICMS51_MODBC;
    }

    public void setDET_ICMS51_PREDBC(Double DET_ICMS51_PREDBC) {
        this.DET_ICMS51_PREDBC = DET_ICMS51_PREDBC;
    }

    public void setDET_ICMS51_VBC(Double DET_ICMS51_VBC) {
        this.DET_ICMS51_VBC = DET_ICMS51_VBC;
    }

    public void setDET_ICMS51_PICMS(Double DET_ICMS51_PICMS) {
        this.DET_ICMS51_PICMS = DET_ICMS51_PICMS;
    }

    public void setDET_ICMS51_VICMSOP(Double DET_ICMS51_VICMSOP) {
        this.DET_ICMS51_VICMSOP = DET_ICMS51_VICMSOP;
    }

    public void setDET_ICMS51_PDIF(Double DET_ICMS51_PDIF) {
        this.DET_ICMS51_PDIF = DET_ICMS51_PDIF;
    }

    public void setDET_ICMS51_VICMSDIF(Double DET_ICMS51_VICMSDIF) {
        this.DET_ICMS51_VICMSDIF = DET_ICMS51_VICMSDIF;
    }

    public void setDET_ICMS51_VICMS(Double DET_ICMS51_VICMS) {
        this.DET_ICMS51_VICMS = DET_ICMS51_VICMS;
    }

    public void setDET_ICMS60_ORIG(Integer DET_ICMS60_ORIG) {
        this.DET_ICMS60_ORIG = DET_ICMS60_ORIG;
    }

    public void setDET_ICMS60_CST(Integer DET_ICMS60_CST) {
        this.DET_ICMS60_CST = DET_ICMS60_CST;
    }

    public void setDET_ICMS60_VBCSTRET(Double DET_ICMS60_VBCSTRET) {
        this.DET_ICMS60_VBCSTRET = DET_ICMS60_VBCSTRET;
    }

    public void setDET_ICMS60_VICMSSTRET(Double DET_ICMS60_VICMSSTRET) {
        this.DET_ICMS60_VICMSSTRET = DET_ICMS60_VICMSSTRET;
    }

    public void setDET_ICMS70_ORIG(Integer DET_ICMS70_ORIG) {
        this.DET_ICMS70_ORIG = DET_ICMS70_ORIG;
    }

    public void setDET_ICMS70_CST(Integer DET_ICMS70_CST) {
        this.DET_ICMS70_CST = DET_ICMS70_CST;
    }

    public void setDET_ICMS70_MODBC(Integer DET_ICMS70_MODBC) {
        this.DET_ICMS70_MODBC = DET_ICMS70_MODBC;
    }

    public void setDET_ICMS70_PREDBC(Double DET_ICMS70_PREDBC) {
        this.DET_ICMS70_PREDBC = DET_ICMS70_PREDBC;
    }

    public void setDET_ICMS70_VBC(Double DET_ICMS70_VBC) {
        this.DET_ICMS70_VBC = DET_ICMS70_VBC;
    }

    public void setDET_ICMS70_PICMS(Double DET_ICMS70_PICMS) {
        this.DET_ICMS70_PICMS = DET_ICMS70_PICMS;
    }

    public void setDET_ICMS70_VICMS(Double DET_ICMS70_VICMS) {
        this.DET_ICMS70_VICMS = DET_ICMS70_VICMS;
    }

    public void setDET_ICMS70_MODBCST(Integer DET_ICMS70_MODBCST) {
        this.DET_ICMS70_MODBCST = DET_ICMS70_MODBCST;
    }

    public void setDET_ICMS70_PMVAST(Double DET_ICMS70_PMVAST) {
        this.DET_ICMS70_PMVAST = DET_ICMS70_PMVAST;
    }

    public void setDET_ICMS70_PREDBCST(Double DET_ICMS70_PREDBCST) {
        this.DET_ICMS70_PREDBCST = DET_ICMS70_PREDBCST;
    }

    public void setDET_ICMS70_VBCST(Double DET_ICMS70_VBCST) {
        this.DET_ICMS70_VBCST = DET_ICMS70_VBCST;
    }

    public void setDET_ICMS70_PICMSST(Double DET_ICMS70_PICMSST) {
        this.DET_ICMS70_PICMSST = DET_ICMS70_PICMSST;
    }

    public void setDET_ICMS70_VICMSST(Double DET_ICMS70_VICMSST) {
        this.DET_ICMS70_VICMSST = DET_ICMS70_VICMSST;
    }

    public void setDET_ICMS70_VICMSDESON(Double DET_ICMS70_VICMSDESON) {
        this.DET_ICMS70_VICMSDESON = DET_ICMS70_VICMSDESON;
    }

    public void setDET_ICMS70_MOTDESICMS(Integer DET_ICMS70_MOTDESICMS) {
        this.DET_ICMS70_MOTDESICMS = DET_ICMS70_MOTDESICMS;
    }

    public void setDET_ICMS90_ORIG(Integer DET_ICMS90_ORIG) {
        this.DET_ICMS90_ORIG = DET_ICMS90_ORIG;
    }

    public void setDET_ICMS90_CST(Integer DET_ICMS90_CST) {
        this.DET_ICMS90_CST = DET_ICMS90_CST;
    }

    public void setDET_ICMS90_MODBC(Integer DET_ICMS90_MODBC) {
        this.DET_ICMS90_MODBC = DET_ICMS90_MODBC;
    }

    public void setDET_ICMS90_VBC(Double DET_ICMS90_VBC) {
        this.DET_ICMS90_VBC = DET_ICMS90_VBC;
    }

    public void setDET_ICMS90_PREDBC(Double DET_ICMS90_PREDBC) {
        this.DET_ICMS90_PREDBC = DET_ICMS90_PREDBC;
    }

    public void setDET_ICMS90_PICMS(Double DET_ICMS90_PICMS) {
        this.DET_ICMS90_PICMS = DET_ICMS90_PICMS;
    }

    public void setDET_ICMS90_VICMS(Double DET_ICMS90_VICMS) {
        this.DET_ICMS90_VICMS = DET_ICMS90_VICMS;
    }

    public void setDET_ICMS90_MODBCST(Integer DET_ICMS90_MODBCST) {
        this.DET_ICMS90_MODBCST = DET_ICMS90_MODBCST;
    }

    public void setDET_ICMS90_PMVAST(Double DET_ICMS90_PMVAST) {
        this.DET_ICMS90_PMVAST = DET_ICMS90_PMVAST;
    }

    public void setDET_ICMS90_PREDBCST(Double DET_ICMS90_PREDBCST) {
        this.DET_ICMS90_PREDBCST = DET_ICMS90_PREDBCST;
    }

    public void setDET_ICMS90_VBCST(Double DET_ICMS90_VBCST) {
        this.DET_ICMS90_VBCST = DET_ICMS90_VBCST;
    }

    public void setDET_ICMS90_PICMSST(Double DET_ICMS90_PICMSST) {
        this.DET_ICMS90_PICMSST = DET_ICMS90_PICMSST;
    }

    public void setDET_ICMS90_VICMSST(Double DET_ICMS90_VICMSST) {
        this.DET_ICMS90_VICMSST = DET_ICMS90_VICMSST;
    }

    public void setDET_ICMS90_VICMSDESON(Double DET_ICMS90_VICMSDESON) {
        this.DET_ICMS90_VICMSDESON = DET_ICMS90_VICMSDESON;
    }

    public void setDET_ICMS90_MOTDESICMS(Integer DET_ICMS90_MOTDESICMS) {
        this.DET_ICMS90_MOTDESICMS = DET_ICMS90_MOTDESICMS;
    }

    public void setDET_IPI_CLENQ(Integer DET_IPI_CLENQ) {
        this.DET_IPI_CLENQ = DET_IPI_CLENQ;
    }

    public void setDET_IPI_CNPJPROD(Long DET_IPI_CNPJPROD) {
        this.DET_IPI_CNPJPROD = DET_IPI_CNPJPROD;
    }

    public void setDET_IPI_CSELO(String DET_IPI_CSELO) {
        this.DET_IPI_CSELO = DET_IPI_CSELO;
    }

    public void setDET_IPI_QSELO(Long DET_IPI_QSELO) {
        this.DET_IPI_QSELO = DET_IPI_QSELO;
    }

    public void setDET_IPI_CENQ(Integer DET_IPI_CENQ) {
        this.DET_IPI_CENQ = DET_IPI_CENQ;
    }

    public void setDET_IPITRIB_CST(Integer DET_IPITRIB_CST) {
        this.DET_IPITRIB_CST = DET_IPITRIB_CST;
    }

    public void setDET_IPITRIB_VBC(Double DET_IPITRIB_VBC) {
        this.DET_IPITRIB_VBC = DET_IPITRIB_VBC;
    }

    public void setDET_IPITRIB_PIPI(Double DET_IPITRIB_PIPI) {
        this.DET_IPITRIB_PIPI = DET_IPITRIB_PIPI;
    }

    public void setDET_IPITRIB_QUNID(Double DET_IPITRIB_QUNID) {
        this.DET_IPITRIB_QUNID = DET_IPITRIB_QUNID;
    }

    public void setDET_IPITRIB_VUNID(Double DET_IPITRIB_VUNID) {
        this.DET_IPITRIB_VUNID = DET_IPITRIB_VUNID;
    }

    public void setDET_IPITRIB_VIPI(Double DET_IPITRIB_VIPI) {
        this.DET_IPITRIB_VIPI = DET_IPITRIB_VIPI;
    }

    public void setDET_PISALIQ_CST(Integer DET_PISALIQ_CST) {
        this.DET_PISALIQ_CST = DET_PISALIQ_CST;
    }

    public void setDET_PISALIQ_VBC(Double DET_PISALIQ_VBC) {
        this.DET_PISALIQ_VBC = DET_PISALIQ_VBC;
    }

    public void setDET_PISALIQ_PPIS(Double DET_PISALIQ_PPIS) {
        this.DET_PISALIQ_PPIS = DET_PISALIQ_PPIS;
    }

    public void setDET_PISALIQ_VPIS(Double DET_PISALIQ_VPIS) {
        this.DET_PISALIQ_VPIS = DET_PISALIQ_VPIS;
    }

    public void setDET_PISQTDE_CST(Integer DET_PISQTDE_CST) {
        this.DET_PISQTDE_CST = DET_PISQTDE_CST;
    }

    public void setDET_PISQTDE_QBCPROD(Double DET_PISQTDE_QBCPROD) {
        this.DET_PISQTDE_QBCPROD = DET_PISQTDE_QBCPROD;
    }

    public void setDET_PISQTDE_VALIQPROD(Double DET_PISQTDE_VALIQPROD) {
        this.DET_PISQTDE_VALIQPROD = DET_PISQTDE_VALIQPROD;
    }

    public void setDET_PISQTDE_VPIS(Double DET_PISQTDE_VPIS) {
        this.DET_PISQTDE_VPIS = DET_PISQTDE_VPIS;
    }

    public void setDET_PISOUTR_CST(Integer DET_PISOUTR_CST) {
        this.DET_PISOUTR_CST = DET_PISOUTR_CST;
    }

    public void setDET_PISOUTR_VBC(Double DET_PISOUTR_VBC) {
        this.DET_PISOUTR_VBC = DET_PISOUTR_VBC;
    }

    public void setDET_PISOUTR_PPIS(Double DET_PISOUTR_PPIS) {
        this.DET_PISOUTR_PPIS = DET_PISOUTR_PPIS;
    }

    public void setDET_PISOUTR_VPIS(Double DET_PISOUTR_VPIS) {
        this.DET_PISOUTR_VPIS = DET_PISOUTR_VPIS;
    }

    public void setDET_PISST_VBC(Double DET_PISST_VBC) {
        this.DET_PISST_VBC = DET_PISST_VBC;
    }

    public void setDET_PISST_PPIS(Double DET_PISST_PPIS) {
        this.DET_PISST_PPIS = DET_PISST_PPIS;
    }

    public void setDET_PISST_QBCPROD(Double DET_PISST_QBCPROD) {
        this.DET_PISST_QBCPROD = DET_PISST_QBCPROD;
    }

    public void setDET_PISST_VALIQPROD(Double DET_PISST_VALIQPROD) {
        this.DET_PISST_VALIQPROD = DET_PISST_VALIQPROD;
    }

    public void setDET_PISST_VPIS(Double DET_PISST_VPIS) {
        this.DET_PISST_VPIS = DET_PISST_VPIS;
    }

    public void setDET_COFINSALIQ_CST(Integer DET_COFINSALIQ_CST) {
        this.DET_COFINSALIQ_CST = DET_COFINSALIQ_CST;
    }

    public void setDET_COFINSALIQ_VBC(Double DET_COFINSALIQ_VBC) {
        this.DET_COFINSALIQ_VBC = DET_COFINSALIQ_VBC;
    }

    public void setDET_COFINSALIQ_PCOFINS(Double DET_COFINSALIQ_PCOFINS) {
        this.DET_COFINSALIQ_PCOFINS = DET_COFINSALIQ_PCOFINS;
    }

    public void setDET_COFINSALIQ_VCOFINS(Double DET_COFINSALIQ_VCOFINS) {
        this.DET_COFINSALIQ_VCOFINS = DET_COFINSALIQ_VCOFINS;
    }

    public void setDET_COFINSQTDE_CST(Integer DET_COFINSQTDE_CST) {
        this.DET_COFINSQTDE_CST = DET_COFINSQTDE_CST;
    }

    public void setDET_COFINSQTDE_QBCPROD(Double DET_COFINSQTDE_QBCPROD) {
        this.DET_COFINSQTDE_QBCPROD = DET_COFINSQTDE_QBCPROD;
    }

    public void setDET_COFINSQTDE_VALIQPROD(Double DET_COFINSQTDE_VALIQPROD) {
        this.DET_COFINSQTDE_VALIQPROD = DET_COFINSQTDE_VALIQPROD;
    }

    public void setDET_COFINSQTDE_VCOFINS(Double DET_COFINSQTDE_VCOFINS) {
        this.DET_COFINSQTDE_VCOFINS = DET_COFINSQTDE_VCOFINS;
    }

    public void setDET_COFINSOUTR_CST(Integer DET_COFINSOUTR_CST) {
        this.DET_COFINSOUTR_CST = DET_COFINSOUTR_CST;
    }

    public void setDET_COFINSOUTR_VBC(Double DET_COFINSOUTR_VBC) {
        this.DET_COFINSOUTR_VBC = DET_COFINSOUTR_VBC;
    }

    public void setDET_COFINSOUTR_PCOFINS(Double DET_COFINSOUTR_PCOFINS) {
        this.DET_COFINSOUTR_PCOFINS = DET_COFINSOUTR_PCOFINS;
    }

    public void setDET_COFINSOUTR_VCOFINS(Double DET_COFINSOUTR_VCOFINS) {
        this.DET_COFINSOUTR_VCOFINS = DET_COFINSOUTR_VCOFINS;
    }

    public void setDET_COFINSST_VBC(Double DET_COFINSST_VBC) {
        this.DET_COFINSST_VBC = DET_COFINSST_VBC;
    }

    public void setDET_COFINSST_PCOFINS(Double DET_COFINSST_PCOFINS) {
        this.DET_COFINSST_PCOFINS = DET_COFINSST_PCOFINS;
    }

    public void setDET_COFINSST_QBCPROD(Double DET_COFINSST_QBCPROD) {
        this.DET_COFINSST_QBCPROD = DET_COFINSST_QBCPROD;
    }

    public void setDET_COFINSST_VALIQPROD(Double DET_COFINSST_VALIQPROD) {
        this.DET_COFINSST_VALIQPROD = DET_COFINSST_VALIQPROD;
    }

    public void setDET_COFINSST_VCOFINS(Double DET_COFINSST_VCOFINS) {
        this.DET_COFINSST_VCOFINS = DET_COFINSST_VCOFINS;
    }

    public void setDET_INFADPROD(String DET_INFADPROD) {
        this.DET_INFADPROD = DET_INFADPROD;
    }

    public void setDET_PROD_CEST(Long DET_PROD_CEST) {
        this.DET_PROD_CEST = DET_PROD_CEST;
    }

    public void setDET_PROD_CEAN(String DET_PROD_CEAN) {
        this.DET_PROD_CEAN = DET_PROD_CEAN;
    }

    public void setDET_PROD_CEANTRIB(String DET_PROD_CEANTRIB) {
        this.DET_PROD_CEANTRIB = DET_PROD_CEANTRIB;
    }

    public void setDET_IBS_CST(String DET_IBS_CST) {
        this.DET_IBS_CST = DET_IBS_CST;
    }

    public void setDET_IBS_CCLASSTRIB(String DET_IBS_CCLASSTRIB) {
        this.DET_IBS_CCLASSTRIB = DET_IBS_CCLASSTRIB;
    }

    public void setDET_IBS_PIBSTOTAL(Double DET_IBS_PIBSTOTAL) {
        this.DET_IBS_PIBSTOTAL = DET_IBS_PIBSTOTAL;
    }

    public void setDET_IBS_PIBSUF(Double DET_IBS_PIBSUF) {
        this.DET_IBS_PIBSUF = DET_IBS_PIBSUF;
    }

    public void setDET_IBS_PIBSMUN(Double DET_IBS_PIBSMUN) {
        this.DET_IBS_PIBSMUN = DET_IBS_PIBSMUN;
    }

    public void setDET_IBSUF_VBC(Double DET_IBSUF_VBC) {
        this.DET_IBSUF_VBC = DET_IBSUF_VBC;
    }

    public void setDET_IBSUF_PIBSUF(Double DET_IBSUF_PIBSUF) {
        this.DET_IBSUF_PIBSUF = DET_IBSUF_PIBSUF;
    }

    public void setDET_IBSUF_VIBSUF(Double DET_IBSUF_VIBSUF) {
        this.DET_IBSUF_VIBSUF = DET_IBSUF_VIBSUF;
    }

    public void setDET_IBSMUN_VBC(Double DET_IBSMUN_VBC) {
        this.DET_IBSMUN_VBC = DET_IBSMUN_VBC;
    }

    public void setDET_IBSMUN_PIBSMUN(Double DET_IBSMUN_PIBSMUN) {
        this.DET_IBSMUN_PIBSMUN = DET_IBSMUN_PIBSMUN;
    }

    public void setDET_IBSMUN_VTRIBOP(Double DET_IBSMUN_VIBSMUN) {
        this.DET_IBSMUN_VIBSMUN = DET_IBSMUN_VIBSMUN;
    }

    public void setDET_IS_CST(Integer DET_IS_CST) {
        this.DET_IS_CST = DET_IS_CST;
    }

    public void setDET_IS_CCLASSTRIB(Long DET_IS_CCLASSTRIB) {
        this.DET_IS_CCLASSTRIB = DET_IS_CCLASSTRIB;
    }

    public void setDET_IS_VBCIMPSEL(Double DET_IS_VBCIMPSEL) {
        this.DET_IS_VBCIMPSEL = DET_IS_VBCIMPSEL;
    }

    public void setDET_IS_PIMPSEL(Double DET_IS_PIMPSEL) {
        this.DET_IS_PIMPSEL = DET_IS_PIMPSEL;
    }

    public void setDET_IS_VIMPSEL(Double DET_IS_VIMPSEL) {
        this.DET_IS_VIMPSEL = DET_IS_VIMPSEL;
    }

    public void setDET_IS_PIMPSELESPEC(Double DET_IS_PIMPSELESPEC) {
        this.DET_IS_PIMPSELESPEC = DET_IS_PIMPSELESPEC;
    }

    public void setDET_IS_UTRIB(String DET_IS_UTRIB) {
        this.DET_IS_UTRIB = DET_IS_UTRIB;
    }

    public void setDET_IS_QTRIB(Double DET_IS_QTRIB) {
        this.DET_IS_QTRIB = DET_IS_QTRIB;
    }

    public void setDET_IS_CSTIBSCBS(Integer DET_IS_CSTIBSCBS) {
        this.DET_IS_CSTIBSCBS = DET_IS_CSTIBSCBS;
    }

    public void setDET_IS_CCLASSTRIBIBSCBS(Long DET_IS_CCLASSTRIBIBSCBS) {
        this.DET_IS_CCLASSTRIBIBSCBS = DET_IS_CCLASSTRIBIBSCBS;
    }

    public void setDET_CBS_CST(Integer DET_CBS_CST) {
        this.DET_CBS_CST = DET_CBS_CST;
    }

    public void setDET_CBS_CCLASSTRIB(Long DET_CBS_CCLASSTRIB) {
        this.DET_CBS_CCLASSTRIB = DET_CBS_CCLASSTRIB;
    }

    public void setDET_CBS_PCBSREF(Double DET_CBS_PCBSREF) {
        this.DET_CBS_PCBSREF = DET_CBS_PCBSREF;
    }

    public void setDET_CBS_VBC(Double DET_CBS_VBC) {
        this.DET_CBS_VBC = DET_CBS_VBC;
    }

    public void setDET_CBS_PCBS(Double DET_CBS_PCBS) {
        this.DET_CBS_PCBS = DET_CBS_PCBS;
    }

    public void setDET_CBS_VCBS(Double DET_CBS_VCBS) {
        this.DET_CBS_VCBS = DET_CBS_VCBS;
    }

    public List<NFEDETPRODdto> getNFEDETPRODdto() {
        return NFEDETPRODdto;
    }

    public void setNFEDETPRODdto(List<NFEDETPRODdto> NFEDETPRODdto) {
        this.NFEDETPRODdto = NFEDETPRODdto;
    }

    public static NFEDETPRODdto convertNFEDETPRODtoNFEDETPRODdto(NFEDETPROD nFEDETPROD) {
        NFEDETPRODdto nFEDETPRODdto = new NFEDETPRODdto();
        nFEDETPRODdto.setDET_PROD_NNF(nFEDETPROD.getDET_PROD_NNF());
        nFEDETPRODdto.setDET_PROD_ITEM(nFEDETPROD.getDET_PROD_ITEM());
        nFEDETPRODdto.setDET_PROD_CPROD(nFEDETPROD.getDET_PROD_CPROD());
        nFEDETPRODdto.setDET_PROD_XPROD(nFEDETPROD.getDET_PROD_XPROD());
        nFEDETPRODdto.setDET_PROD_NCM(nFEDETPROD.getDET_PROD_NCM());
        nFEDETPRODdto.setDET_PROD_CFOP(nFEDETPROD.getDET_PROD_CFOP());
        nFEDETPRODdto.setDET_PROD_UCOM(nFEDETPROD.getDET_PROD_UCOM());
        nFEDETPRODdto.setDET_PROD_QCOM(nFEDETPROD.getDET_PROD_QCOM());
        nFEDETPRODdto.setDET_PROD_VUNCOM(nFEDETPROD.getDET_PROD_VUNCOM());
        nFEDETPRODdto.setDET_PROD_VPROD(nFEDETPROD.getDET_PROD_VPROD());
        nFEDETPRODdto.setDET_PROD_UTRIB(nFEDETPROD.getDET_PROD_UTRIB());
        nFEDETPRODdto.setDET_PROD_QTRIB(nFEDETPROD.getDET_PROD_QTRIB());
        nFEDETPRODdto.setDET_PROD_VUNTRIB(nFEDETPROD.getDET_PROD_VUNTRIB());
        nFEDETPRODdto.setDET_PROD_VFRETE(nFEDETPROD.getDET_PROD_VFRETE());
        nFEDETPRODdto.setDET_PROD_VSEG(nFEDETPROD.getDET_PROD_VSEG());
        nFEDETPRODdto.setDET_PROD_VDESC(nFEDETPROD.getDET_PROD_VDESC());
        nFEDETPRODdto.setDET_PROD_VOUTRO(nFEDETPROD.getDET_PROD_VOUTRO());
        nFEDETPRODdto.setDET_PROD_INDTOT(nFEDETPROD.getDET_PROD_INDTOT());
        nFEDETPRODdto.setDET_IMPOSTO_VTOTTRIB(nFEDETPROD.getDET_IMPOSTO_VTOTTRIB());
        nFEDETPRODdto.setDET_ICMS_CRT(nFEDETPROD.getDET_ICMS_CRT());
        nFEDETPRODdto.setDET_ICMS_CST(nFEDETPROD.getDET_ICMS_CST());
        nFEDETPRODdto.setDET_ICMS_ORIG(nFEDETPROD.getDET_ICMS_ORIG());
        nFEDETPRODdto.setDET_ICMS00_ORIG(nFEDETPROD.getDET_ICMS00_ORIG());
        nFEDETPRODdto.setDET_ICMS00_CST(nFEDETPROD.getDET_ICMS00_CST());
        nFEDETPRODdto.setDET_ICMS00_MODBC(nFEDETPROD.getDET_ICMS00_MODBC());
        nFEDETPRODdto.setDET_ICMS00_VBC(nFEDETPROD.getDET_ICMS00_VBC());
        nFEDETPRODdto.setDET_ICMS00_PICMS(nFEDETPROD.getDET_ICMS00_PICMS());
        nFEDETPRODdto.setDET_ICMS00_VICMS(nFEDETPROD.getDET_ICMS00_VICMS());
        nFEDETPRODdto.setDET_ICMS10_ORIG(nFEDETPROD.getDET_ICMS10_ORIG());
        nFEDETPRODdto.setDET_ICMS10_CST(nFEDETPROD.getDET_ICMS10_CST());
        nFEDETPRODdto.setDET_ICMS10_MODBC(nFEDETPROD.getDET_ICMS10_MODBC());
        nFEDETPRODdto.setDET_ICMS10_VBC(nFEDETPROD.getDET_ICMS10_VBC());
        nFEDETPRODdto.setDET_ICMS10_PICMS(nFEDETPROD.getDET_ICMS10_PICMS());
        nFEDETPRODdto.setDET_ICMS10_VICMS(nFEDETPROD.getDET_ICMS10_VICMS());
        nFEDETPRODdto.setDET_ICMS10_MODBCST(nFEDETPROD.getDET_ICMS10_MODBCST());
        nFEDETPRODdto.setDET_ICMS10_PMVAST(nFEDETPROD.getDET_ICMS10_PMVAST());
        nFEDETPRODdto.setDET_ICMS10_PREDBCST(nFEDETPROD.getDET_ICMS10_PREDBCST());
        nFEDETPRODdto.setDET_ICMS10_VBCST(nFEDETPROD.getDET_ICMS10_VBCST());
        nFEDETPRODdto.setDET_ICMS10_PICMSST(nFEDETPROD.getDET_ICMS10_PICMSST());
        nFEDETPRODdto.setDET_ICMS10_VICMSST(nFEDETPROD.getDET_ICMS10_VICMSST());
        nFEDETPRODdto.setDET_ICMS20_ORIG(nFEDETPROD.getDET_ICMS20_ORIG());
        nFEDETPRODdto.setDET_ICMS20_CST(nFEDETPROD.getDET_ICMS20_CST());
        nFEDETPRODdto.setDET_ICMS20_MODBC(nFEDETPROD.getDET_ICMS20_MODBC());
        nFEDETPRODdto.setDET_ICMS20_PREDBC(nFEDETPROD.getDET_ICMS20_PREDBC());
        nFEDETPRODdto.setDET_ICMS20_VBC(nFEDETPROD.getDET_ICMS20_VBC());
        nFEDETPRODdto.setDET_ICMS20_PICMS(nFEDETPROD.getDET_ICMS20_PICMS());
        nFEDETPRODdto.setDET_ICMS20_VICMS(nFEDETPROD.getDET_ICMS20_VICMS());
        nFEDETPRODdto.setDET_ICMS20_VICMSDESON(nFEDETPROD.getDET_ICMS20_VICMSDESON());
        nFEDETPRODdto.setDET_ICMS20_MOTDESICMS(nFEDETPROD.getDET_ICMS20_MOTDESICMS());
        nFEDETPRODdto.setDET_ICMS30_ORIG(nFEDETPROD.getDET_ICMS30_ORIG());
        nFEDETPRODdto.setDET_ICMS30_CST(nFEDETPROD.getDET_ICMS30_CST());
        nFEDETPRODdto.setDET_ICMS30_MODBCST(nFEDETPROD.getDET_ICMS30_MODBCST());
        nFEDETPRODdto.setDET_ICMS30_PMVAST(nFEDETPROD.getDET_ICMS30_PMVAST());
        nFEDETPRODdto.setDET_ICMS30_PREDBCST(nFEDETPROD.getDET_ICMS30_PREDBCST());
        nFEDETPRODdto.setDET_ICMS30_VBCST(nFEDETPROD.getDET_ICMS30_VBCST());
        nFEDETPRODdto.setDET_ICMS30_PICMSST(nFEDETPROD.getDET_ICMS30_PICMSST());
        nFEDETPRODdto.setDET_ICMS30_VICMSST(nFEDETPROD.getDET_ICMS30_VICMSST());
        nFEDETPRODdto.setDET_ICMS30_VICMSDESON(nFEDETPROD.getDET_ICMS30_VICMSDESON());
        nFEDETPRODdto.setDET_ICMS30_MOTDESICMS(nFEDETPROD.getDET_ICMS30_MOTDESICMS());
        nFEDETPRODdto.setDET_ICMS40_ORIG(nFEDETPROD.getDET_ICMS40_ORIG());
        nFEDETPRODdto.setDET_ICMS40_CST(nFEDETPROD.getDET_ICMS40_CST());
        nFEDETPRODdto.setDET_ICMS40_VICMSDESON(nFEDETPROD.getDET_ICMS40_VICMSDESON());
        nFEDETPRODdto.setDET_ICMS40_MOTDESICMS(nFEDETPROD.getDET_ICMS40_MOTDESICMS());
        nFEDETPRODdto.setDET_ICMS51_ORIG(nFEDETPROD.getDET_ICMS51_ORIG());
        nFEDETPRODdto.setDET_ICMS51_CST(nFEDETPROD.getDET_ICMS51_CST());
        nFEDETPRODdto.setDET_ICMS51_MODBC(nFEDETPROD.getDET_ICMS51_MODBC());
        nFEDETPRODdto.setDET_ICMS51_PREDBC(nFEDETPROD.getDET_ICMS51_PREDBC());
        nFEDETPRODdto.setDET_ICMS51_VBC(nFEDETPROD.getDET_ICMS51_VBC());
        nFEDETPRODdto.setDET_ICMS51_PICMS(nFEDETPROD.getDET_ICMS51_PICMS());
        nFEDETPRODdto.setDET_ICMS51_VICMSOP(nFEDETPROD.getDET_ICMS51_VICMSOP());
        nFEDETPRODdto.setDET_ICMS51_PDIF(nFEDETPROD.getDET_ICMS51_PDIF());
        nFEDETPRODdto.setDET_ICMS51_VICMSDIF(nFEDETPROD.getDET_ICMS51_VICMSDIF());
        nFEDETPRODdto.setDET_ICMS51_VICMS(nFEDETPROD.getDET_ICMS51_VICMS());
        nFEDETPRODdto.setDET_ICMS60_ORIG(nFEDETPROD.getDET_ICMS60_ORIG());
        nFEDETPRODdto.setDET_ICMS60_CST(nFEDETPROD.getDET_ICMS60_CST());
        nFEDETPRODdto.setDET_ICMS60_VBCSTRET(nFEDETPROD.getDET_ICMS60_VBCSTRET());
        nFEDETPRODdto.setDET_ICMS60_VICMSSTRET(nFEDETPROD.getDET_ICMS60_VICMSSTRET());
        nFEDETPRODdto.setDET_ICMS70_ORIG(nFEDETPROD.getDET_ICMS70_ORIG());
        nFEDETPRODdto.setDET_ICMS70_CST(nFEDETPROD.getDET_ICMS70_CST());
        nFEDETPRODdto.setDET_ICMS70_MODBC(nFEDETPROD.getDET_ICMS70_MODBC());
        nFEDETPRODdto.setDET_ICMS70_PREDBC(nFEDETPROD.getDET_ICMS70_PREDBC());
        nFEDETPRODdto.setDET_ICMS70_VBC(nFEDETPROD.getDET_ICMS70_VBC());
        nFEDETPRODdto.setDET_ICMS70_PICMS(nFEDETPROD.getDET_ICMS70_PICMS());
        nFEDETPRODdto.setDET_ICMS70_VICMS(nFEDETPROD.getDET_ICMS70_VICMS());
        nFEDETPRODdto.setDET_ICMS70_MODBCST(nFEDETPROD.getDET_ICMS70_MODBCST());
        nFEDETPRODdto.setDET_ICMS70_PMVAST(nFEDETPROD.getDET_ICMS70_PMVAST());
        nFEDETPRODdto.setDET_ICMS70_PREDBCST(nFEDETPROD.getDET_ICMS70_PREDBCST());
        nFEDETPRODdto.setDET_ICMS70_VBCST(nFEDETPROD.getDET_ICMS70_VBCST());
        nFEDETPRODdto.setDET_ICMS70_PICMSST(nFEDETPROD.getDET_ICMS70_PICMSST());
        nFEDETPRODdto.setDET_ICMS70_VICMSST(nFEDETPROD.getDET_ICMS70_VICMSST());
        nFEDETPRODdto.setDET_ICMS70_VICMSDESON(nFEDETPROD.getDET_ICMS70_VICMSDESON());
        nFEDETPRODdto.setDET_ICMS70_MOTDESICMS(nFEDETPROD.getDET_ICMS70_MOTDESICMS());
        nFEDETPRODdto.setDET_ICMS90_ORIG(nFEDETPROD.getDET_ICMS90_ORIG());
        nFEDETPRODdto.setDET_ICMS90_CST(nFEDETPROD.getDET_ICMS90_CST());
        nFEDETPRODdto.setDET_ICMS90_MODBC(nFEDETPROD.getDET_ICMS90_MODBC());
        nFEDETPRODdto.setDET_ICMS90_VBC(nFEDETPROD.getDET_ICMS90_VBC());
        nFEDETPRODdto.setDET_ICMS90_PREDBC(nFEDETPROD.getDET_ICMS90_PREDBC());
        nFEDETPRODdto.setDET_ICMS90_PICMS(nFEDETPROD.getDET_ICMS90_PICMS());
        nFEDETPRODdto.setDET_ICMS90_VICMS(nFEDETPROD.getDET_ICMS90_VICMS());
        nFEDETPRODdto.setDET_ICMS90_MODBCST(nFEDETPROD.getDET_ICMS90_MODBCST());
        nFEDETPRODdto.setDET_ICMS90_PMVAST(nFEDETPROD.getDET_ICMS90_PMVAST());
        nFEDETPRODdto.setDET_ICMS90_PREDBCST(nFEDETPROD.getDET_ICMS90_PREDBCST());
        nFEDETPRODdto.setDET_ICMS90_VBCST(nFEDETPROD.getDET_ICMS90_VBCST());
        nFEDETPRODdto.setDET_ICMS90_PICMSST(nFEDETPROD.getDET_ICMS90_PICMSST());
        nFEDETPRODdto.setDET_ICMS90_VICMSST(nFEDETPROD.getDET_ICMS90_VICMSST());
        nFEDETPRODdto.setDET_ICMS90_VICMSDESON(nFEDETPROD.getDET_ICMS90_VICMSDESON());
        nFEDETPRODdto.setDET_ICMS90_MOTDESICMS(nFEDETPROD.getDET_ICMS90_MOTDESICMS());
        nFEDETPRODdto.setDET_IPI_CLENQ(nFEDETPROD.getDET_IPI_CLENQ());
        nFEDETPRODdto.setDET_IPI_CNPJPROD(nFEDETPROD.getDET_IPI_CNPJPROD());
        nFEDETPRODdto.setDET_IPI_CSELO(nFEDETPROD.getDET_IPI_CSELO());
        nFEDETPRODdto.setDET_IPI_QSELO(nFEDETPROD.getDET_IPI_QSELO());
        nFEDETPRODdto.setDET_IPI_CENQ(nFEDETPROD.getDET_IPI_CENQ());
        nFEDETPRODdto.setDET_IPITRIB_CST(nFEDETPROD.getDET_IPITRIB_CST());
        nFEDETPRODdto.setDET_IPITRIB_VBC(nFEDETPROD.getDET_IPITRIB_VBC());
        nFEDETPRODdto.setDET_IPITRIB_PIPI(nFEDETPROD.getDET_IPITRIB_PIPI());
        nFEDETPRODdto.setDET_IPITRIB_QUNID(nFEDETPROD.getDET_IPITRIB_QUNID());
        nFEDETPRODdto.setDET_IPITRIB_VUNID(nFEDETPROD.getDET_IPITRIB_VUNID());
        nFEDETPRODdto.setDET_IPITRIB_VIPI(nFEDETPROD.getDET_IPITRIB_VIPI());
        nFEDETPRODdto.setDET_PISALIQ_CST(nFEDETPROD.getDET_PISALIQ_CST());
        nFEDETPRODdto.setDET_PISALIQ_VBC(nFEDETPROD.getDET_PISALIQ_VBC());
        nFEDETPRODdto.setDET_PISALIQ_PPIS(nFEDETPROD.getDET_PISALIQ_PPIS());
        nFEDETPRODdto.setDET_PISALIQ_VPIS(nFEDETPROD.getDET_PISALIQ_VPIS());
        nFEDETPRODdto.setDET_PISQTDE_CST(nFEDETPROD.getDET_PISQTDE_CST());
        nFEDETPRODdto.setDET_PISQTDE_QBCPROD(nFEDETPROD.getDET_PISQTDE_QBCPROD());
        nFEDETPRODdto.setDET_PISQTDE_VALIQPROD(nFEDETPROD.getDET_PISQTDE_VALIQPROD());
        nFEDETPRODdto.setDET_PISQTDE_VPIS(nFEDETPROD.getDET_PISQTDE_VPIS());
        nFEDETPRODdto.setDET_PISOUTR_CST(nFEDETPROD.getDET_PISOUTR_CST());
        nFEDETPRODdto.setDET_PISOUTR_VBC(nFEDETPROD.getDET_PISOUTR_VBC());
        nFEDETPRODdto.setDET_PISOUTR_PPIS(nFEDETPROD.getDET_PISOUTR_PPIS());
        nFEDETPRODdto.setDET_PISOUTR_VPIS(nFEDETPROD.getDET_PISOUTR_VPIS());
        nFEDETPRODdto.setDET_PISST_VBC(nFEDETPROD.getDET_PISST_VBC());
        nFEDETPRODdto.setDET_PISST_PPIS(nFEDETPROD.getDET_PISST_PPIS());
        nFEDETPRODdto.setDET_PISST_QBCPROD(nFEDETPROD.getDET_PISST_QBCPROD());
        nFEDETPRODdto.setDET_PISST_VALIQPROD(nFEDETPROD.getDET_PISST_VALIQPROD());
        nFEDETPRODdto.setDET_PISST_VPIS(nFEDETPROD.getDET_PISST_VPIS());
        nFEDETPRODdto.setDET_COFINSALIQ_CST(nFEDETPROD.getDET_COFINSALIQ_CST());
        nFEDETPRODdto.setDET_COFINSALIQ_VBC(nFEDETPROD.getDET_COFINSALIQ_VBC());
        nFEDETPRODdto.setDET_COFINSALIQ_PCOFINS(nFEDETPROD.getDET_COFINSALIQ_PCOFINS());
        nFEDETPRODdto.setDET_COFINSALIQ_VCOFINS(nFEDETPROD.getDET_COFINSALIQ_VCOFINS());
        nFEDETPRODdto.setDET_COFINSQTDE_CST(nFEDETPROD.getDET_COFINSQTDE_CST());
        nFEDETPRODdto.setDET_COFINSQTDE_QBCPROD(nFEDETPROD.getDET_COFINSQTDE_QBCPROD());
        nFEDETPRODdto.setDET_COFINSQTDE_VALIQPROD(nFEDETPROD.getDET_COFINSQTDE_VALIQPROD());
        nFEDETPRODdto.setDET_COFINSQTDE_VCOFINS(nFEDETPROD.getDET_COFINSQTDE_VCOFINS());
        nFEDETPRODdto.setDET_COFINSOUTR_CST(nFEDETPROD.getDET_COFINSOUTR_CST());
        nFEDETPRODdto.setDET_COFINSOUTR_VBC(nFEDETPROD.getDET_COFINSOUTR_VBC());
        nFEDETPRODdto.setDET_COFINSOUTR_PCOFINS(nFEDETPROD.getDET_COFINSOUTR_PCOFINS());
        nFEDETPRODdto.setDET_COFINSOUTR_VCOFINS(nFEDETPROD.getDET_COFINSOUTR_VCOFINS());
        nFEDETPRODdto.setDET_COFINSST_VBC(nFEDETPROD.getDET_COFINSST_VBC());
        nFEDETPRODdto.setDET_COFINSST_PCOFINS(nFEDETPROD.getDET_COFINSST_PCOFINS());
        nFEDETPRODdto.setDET_COFINSST_QBCPROD(nFEDETPROD.getDET_COFINSST_QBCPROD());
        nFEDETPRODdto.setDET_COFINSST_VALIQPROD(nFEDETPROD.getDET_COFINSST_VALIQPROD());
        nFEDETPRODdto.setDET_COFINSST_VCOFINS(nFEDETPROD.getDET_COFINSST_VCOFINS());
        nFEDETPRODdto.setDET_INFADPROD(nFEDETPROD.getDET_INFADPROD());
        nFEDETPRODdto.setDET_PROD_CEST(nFEDETPROD.getDET_PROD_CEST());
        nFEDETPRODdto.setDET_PROD_CEAN(nFEDETPROD.getDET_PROD_CEAN());
        nFEDETPRODdto.setDET_PROD_CEANTRIB(nFEDETPROD.getDET_PROD_CEANTRIB());
        nFEDETPRODdto.setDET_IBS_CST(nFEDETPROD.getDET_IBS_CST());
        nFEDETPRODdto.setDET_IBS_CCLASSTRIB(nFEDETPROD.getDET_IBS_CCLASSTRIB());
        nFEDETPRODdto.setDET_IBS_PIBSTOTAL(nFEDETPROD.getDET_IBS_PIBSTOTAL());
        nFEDETPRODdto.setDET_IBS_PIBSUF(nFEDETPROD.getDET_IBS_PIBSUF());
        nFEDETPRODdto.setDET_IBS_PIBSMUN(nFEDETPROD.getDET_IBS_PIBSMUN());
        nFEDETPRODdto.setDET_IBSUF_VBC(nFEDETPROD.getDET_IBSUF_VBC());
        nFEDETPRODdto.setDET_IBSUF_PIBSUF(nFEDETPROD.getDET_IBSUF_PIBSUF());
        nFEDETPRODdto.setDET_IBSUF_VIBSUF(nFEDETPROD.getDET_IBSUF_VIBSUF());
        nFEDETPRODdto.setDET_IBSMUN_VBC(nFEDETPROD.getDET_IBSMUN_VBC());
        nFEDETPRODdto.setDET_IBSMUN_PIBSMUN(nFEDETPROD.getDET_IBSMUN_PIBSMUN());
        nFEDETPRODdto.setDET_IBSMUN_VTRIBOP(nFEDETPROD.getDET_IBSMUN_VIBSMUN());
        nFEDETPRODdto.setDET_IS_CST(nFEDETPROD.getDET_IS_CST());
        nFEDETPRODdto.setDET_IS_CCLASSTRIB(nFEDETPROD.getDET_IS_CCLASSTRIB());
        nFEDETPRODdto.setDET_IS_VBCIMPSEL(nFEDETPROD.getDET_IS_VBCIMPSEL());
        nFEDETPRODdto.setDET_IS_PIMPSEL(nFEDETPROD.getDET_IS_PIMPSEL());
        nFEDETPRODdto.setDET_IS_VIMPSEL(nFEDETPROD.getDET_IS_VIMPSEL());
        nFEDETPRODdto.setDET_IS_PIMPSELESPEC(nFEDETPROD.getDET_IS_PIMPSELESPEC());
        nFEDETPRODdto.setDET_IS_UTRIB(nFEDETPROD.getDET_IS_UTRIB());
        nFEDETPRODdto.setDET_IS_QTRIB(nFEDETPROD.getDET_IS_QTRIB());
        nFEDETPRODdto.setDET_IS_CSTIBSCBS(nFEDETPROD.getDET_IS_CSTIBSCBS());
        nFEDETPRODdto.setDET_IS_CCLASSTRIBIBSCBS(nFEDETPROD.getDET_IS_CCLASSTRIBIBSCBS());
        nFEDETPRODdto.setDET_CBS_CST(nFEDETPROD.getDET_CBS_CST());
        nFEDETPRODdto.setDET_CBS_CCLASSTRIB(nFEDETPROD.getDET_CBS_CCLASSTRIB());
        nFEDETPRODdto.setDET_CBS_PCBSREF(nFEDETPROD.getDET_CBS_PCBSREF());
        nFEDETPRODdto.setDET_CBS_VBC(nFEDETPROD.getDET_CBS_VBC());
        nFEDETPRODdto.setDET_CBS_PCBS(nFEDETPROD.getDET_CBS_PCBS());
        nFEDETPRODdto.setDET_CBS_VCBS(nFEDETPROD.getDET_CBS_VCBS());
        return nFEDETPRODdto;
    }

    public static NFEDETPROD convertNFEDETPRODdtotoNFEDETPROD(NFEDETPRODdto nFEDETPRODdto) {
        NFEDETPROD nFEDETPROD = new NFEDETPROD();
        nFEDETPROD.setDET_PROD_NNF(nFEDETPRODdto.getDET_PROD_NNF());
        nFEDETPROD.setDET_PROD_ITEM(nFEDETPRODdto.getDET_PROD_ITEM());
        nFEDETPROD.setDET_PROD_CPROD(nFEDETPRODdto.getDET_PROD_CPROD());
        nFEDETPROD.setDET_PROD_XPROD(nFEDETPRODdto.getDET_PROD_XPROD());
        nFEDETPROD.setDET_PROD_NCM(nFEDETPRODdto.getDET_PROD_NCM());
        nFEDETPROD.setDET_PROD_CFOP(nFEDETPRODdto.getDET_PROD_CFOP());
        nFEDETPROD.setDET_PROD_UCOM(nFEDETPRODdto.getDET_PROD_UCOM());
        nFEDETPROD.setDET_PROD_QCOM(nFEDETPRODdto.getDET_PROD_QCOM());
        nFEDETPROD.setDET_PROD_VUNCOM(nFEDETPRODdto.getDET_PROD_VUNCOM());
        nFEDETPROD.setDET_PROD_VPROD(nFEDETPRODdto.getDET_PROD_VPROD());
        nFEDETPROD.setDET_PROD_UTRIB(nFEDETPRODdto.getDET_PROD_UTRIB());
        nFEDETPROD.setDET_PROD_QTRIB(nFEDETPRODdto.getDET_PROD_QTRIB());
        nFEDETPROD.setDET_PROD_VUNTRIB(nFEDETPRODdto.getDET_PROD_VUNTRIB());
        nFEDETPROD.setDET_PROD_VFRETE(nFEDETPRODdto.getDET_PROD_VFRETE());
        nFEDETPROD.setDET_PROD_VSEG(nFEDETPRODdto.getDET_PROD_VSEG());
        nFEDETPROD.setDET_PROD_VDESC(nFEDETPRODdto.getDET_PROD_VDESC());
        nFEDETPROD.setDET_PROD_VOUTRO(nFEDETPRODdto.getDET_PROD_VOUTRO());
        nFEDETPROD.setDET_PROD_INDTOT(nFEDETPRODdto.getDET_PROD_INDTOT());
        nFEDETPROD.setDET_IMPOSTO_VTOTTRIB(nFEDETPRODdto.getDET_IMPOSTO_VTOTTRIB());
        nFEDETPROD.setDET_ICMS_CRT(nFEDETPRODdto.getDET_ICMS_CRT());
        nFEDETPROD.setDET_ICMS_CST(nFEDETPRODdto.getDET_ICMS_CST());
        nFEDETPROD.setDET_ICMS_ORIG(nFEDETPRODdto.getDET_ICMS_ORIG());
        nFEDETPROD.setDET_ICMS00_ORIG(nFEDETPRODdto.getDET_ICMS00_ORIG());
        nFEDETPROD.setDET_ICMS00_CST(nFEDETPRODdto.getDET_ICMS00_CST());
        nFEDETPROD.setDET_ICMS00_MODBC(nFEDETPRODdto.getDET_ICMS00_MODBC());
        nFEDETPROD.setDET_ICMS00_VBC(nFEDETPRODdto.getDET_ICMS00_VBC());
        nFEDETPROD.setDET_ICMS00_PICMS(nFEDETPRODdto.getDET_ICMS00_PICMS());
        nFEDETPROD.setDET_ICMS00_VICMS(nFEDETPRODdto.getDET_ICMS00_VICMS());
        nFEDETPROD.setDET_ICMS10_ORIG(nFEDETPRODdto.getDET_ICMS10_ORIG());
        nFEDETPROD.setDET_ICMS10_CST(nFEDETPRODdto.getDET_ICMS10_CST());
        nFEDETPROD.setDET_ICMS10_MODBC(nFEDETPRODdto.getDET_ICMS10_MODBC());
        nFEDETPROD.setDET_ICMS10_VBC(nFEDETPRODdto.getDET_ICMS10_VBC());
        nFEDETPROD.setDET_ICMS10_PICMS(nFEDETPRODdto.getDET_ICMS10_PICMS());
        nFEDETPROD.setDET_ICMS10_VICMS(nFEDETPRODdto.getDET_ICMS10_VICMS());
        nFEDETPROD.setDET_ICMS10_MODBCST(nFEDETPRODdto.getDET_ICMS10_MODBCST());
        nFEDETPROD.setDET_ICMS10_PMVAST(nFEDETPRODdto.getDET_ICMS10_PMVAST());
        nFEDETPROD.setDET_ICMS10_PREDBCST(nFEDETPRODdto.getDET_ICMS10_PREDBCST());
        nFEDETPROD.setDET_ICMS10_VBCST(nFEDETPRODdto.getDET_ICMS10_VBCST());
        nFEDETPROD.setDET_ICMS10_PICMSST(nFEDETPRODdto.getDET_ICMS10_PICMSST());
        nFEDETPROD.setDET_ICMS10_VICMSST(nFEDETPRODdto.getDET_ICMS10_VICMSST());
        nFEDETPROD.setDET_ICMS20_ORIG(nFEDETPRODdto.getDET_ICMS20_ORIG());
        nFEDETPROD.setDET_ICMS20_CST(nFEDETPRODdto.getDET_ICMS20_CST());
        nFEDETPROD.setDET_ICMS20_MODBC(nFEDETPRODdto.getDET_ICMS20_MODBC());
        nFEDETPROD.setDET_ICMS20_PREDBC(nFEDETPRODdto.getDET_ICMS20_PREDBC());
        nFEDETPROD.setDET_ICMS20_VBC(nFEDETPRODdto.getDET_ICMS20_VBC());
        nFEDETPROD.setDET_ICMS20_PICMS(nFEDETPRODdto.getDET_ICMS20_PICMS());
        nFEDETPROD.setDET_ICMS20_VICMS(nFEDETPRODdto.getDET_ICMS20_VICMS());
        nFEDETPROD.setDET_ICMS20_VICMSDESON(nFEDETPRODdto.getDET_ICMS20_VICMSDESON());
        nFEDETPROD.setDET_ICMS20_MOTDESICMS(nFEDETPRODdto.getDET_ICMS20_MOTDESICMS());
        nFEDETPROD.setDET_ICMS30_ORIG(nFEDETPRODdto.getDET_ICMS30_ORIG());
        nFEDETPROD.setDET_ICMS30_CST(nFEDETPRODdto.getDET_ICMS30_CST());
        nFEDETPROD.setDET_ICMS30_MODBCST(nFEDETPRODdto.getDET_ICMS30_MODBCST());
        nFEDETPROD.setDET_ICMS30_PMVAST(nFEDETPRODdto.getDET_ICMS30_PMVAST());
        nFEDETPROD.setDET_ICMS30_PREDBCST(nFEDETPRODdto.getDET_ICMS30_PREDBCST());
        nFEDETPROD.setDET_ICMS30_VBCST(nFEDETPRODdto.getDET_ICMS30_VBCST());
        nFEDETPROD.setDET_ICMS30_PICMSST(nFEDETPRODdto.getDET_ICMS30_PICMSST());
        nFEDETPROD.setDET_ICMS30_VICMSST(nFEDETPRODdto.getDET_ICMS30_VICMSST());
        nFEDETPROD.setDET_ICMS30_VICMSDESON(nFEDETPRODdto.getDET_ICMS30_VICMSDESON());
        nFEDETPROD.setDET_ICMS30_MOTDESICMS(nFEDETPRODdto.getDET_ICMS30_MOTDESICMS());
        nFEDETPROD.setDET_ICMS40_ORIG(nFEDETPRODdto.getDET_ICMS40_ORIG());
        nFEDETPROD.setDET_ICMS40_CST(nFEDETPRODdto.getDET_ICMS40_CST());
        nFEDETPROD.setDET_ICMS40_VICMSDESON(nFEDETPRODdto.getDET_ICMS40_VICMSDESON());
        nFEDETPROD.setDET_ICMS40_MOTDESICMS(nFEDETPRODdto.getDET_ICMS40_MOTDESICMS());
        nFEDETPROD.setDET_ICMS51_ORIG(nFEDETPRODdto.getDET_ICMS51_ORIG());
        nFEDETPROD.setDET_ICMS51_CST(nFEDETPRODdto.getDET_ICMS51_CST());
        nFEDETPROD.setDET_ICMS51_MODBC(nFEDETPRODdto.getDET_ICMS51_MODBC());
        nFEDETPROD.setDET_ICMS51_PREDBC(nFEDETPRODdto.getDET_ICMS51_PREDBC());
        nFEDETPROD.setDET_ICMS51_VBC(nFEDETPRODdto.getDET_ICMS51_VBC());
        nFEDETPROD.setDET_ICMS51_PICMS(nFEDETPRODdto.getDET_ICMS51_PICMS());
        nFEDETPROD.setDET_ICMS51_VICMSOP(nFEDETPRODdto.getDET_ICMS51_VICMSOP());
        nFEDETPROD.setDET_ICMS51_PDIF(nFEDETPRODdto.getDET_ICMS51_PDIF());
        nFEDETPROD.setDET_ICMS51_VICMSDIF(nFEDETPRODdto.getDET_ICMS51_VICMSDIF());
        nFEDETPROD.setDET_ICMS51_VICMS(nFEDETPRODdto.getDET_ICMS51_VICMS());
        nFEDETPROD.setDET_ICMS60_ORIG(nFEDETPRODdto.getDET_ICMS60_ORIG());
        nFEDETPROD.setDET_ICMS60_CST(nFEDETPRODdto.getDET_ICMS60_CST());
        nFEDETPROD.setDET_ICMS60_VBCSTRET(nFEDETPRODdto.getDET_ICMS60_VBCSTRET());
        nFEDETPROD.setDET_ICMS60_VICMSSTRET(nFEDETPRODdto.getDET_ICMS60_VICMSSTRET());
        nFEDETPROD.setDET_ICMS70_ORIG(nFEDETPRODdto.getDET_ICMS70_ORIG());
        nFEDETPROD.setDET_ICMS70_CST(nFEDETPRODdto.getDET_ICMS70_CST());
        nFEDETPROD.setDET_ICMS70_MODBC(nFEDETPRODdto.getDET_ICMS70_MODBC());
        nFEDETPROD.setDET_ICMS70_PREDBC(nFEDETPRODdto.getDET_ICMS70_PREDBC());
        nFEDETPROD.setDET_ICMS70_VBC(nFEDETPRODdto.getDET_ICMS70_VBC());
        nFEDETPROD.setDET_ICMS70_PICMS(nFEDETPRODdto.getDET_ICMS70_PICMS());
        nFEDETPROD.setDET_ICMS70_VICMS(nFEDETPRODdto.getDET_ICMS70_VICMS());
        nFEDETPROD.setDET_ICMS70_MODBCST(nFEDETPRODdto.getDET_ICMS70_MODBCST());
        nFEDETPROD.setDET_ICMS70_PMVAST(nFEDETPRODdto.getDET_ICMS70_PMVAST());
        nFEDETPROD.setDET_ICMS70_PREDBCST(nFEDETPRODdto.getDET_ICMS70_PREDBCST());
        nFEDETPROD.setDET_ICMS70_VBCST(nFEDETPRODdto.getDET_ICMS70_VBCST());
        nFEDETPROD.setDET_ICMS70_PICMSST(nFEDETPRODdto.getDET_ICMS70_PICMSST());
        nFEDETPROD.setDET_ICMS70_VICMSST(nFEDETPRODdto.getDET_ICMS70_VICMSST());
        nFEDETPROD.setDET_ICMS70_VICMSDESON(nFEDETPRODdto.getDET_ICMS70_VICMSDESON());
        nFEDETPROD.setDET_ICMS70_MOTDESICMS(nFEDETPRODdto.getDET_ICMS70_MOTDESICMS());
        nFEDETPROD.setDET_ICMS90_ORIG(nFEDETPRODdto.getDET_ICMS90_ORIG());
        nFEDETPROD.setDET_ICMS90_CST(nFEDETPRODdto.getDET_ICMS90_CST());
        nFEDETPROD.setDET_ICMS90_MODBC(nFEDETPRODdto.getDET_ICMS90_MODBC());
        nFEDETPROD.setDET_ICMS90_VBC(nFEDETPRODdto.getDET_ICMS90_VBC());
        nFEDETPROD.setDET_ICMS90_PREDBC(nFEDETPRODdto.getDET_ICMS90_PREDBC());
        nFEDETPROD.setDET_ICMS90_PICMS(nFEDETPRODdto.getDET_ICMS90_PICMS());
        nFEDETPROD.setDET_ICMS90_VICMS(nFEDETPRODdto.getDET_ICMS90_VICMS());
        nFEDETPROD.setDET_ICMS90_MODBCST(nFEDETPRODdto.getDET_ICMS90_MODBCST());
        nFEDETPROD.setDET_ICMS90_PMVAST(nFEDETPRODdto.getDET_ICMS90_PMVAST());
        nFEDETPROD.setDET_ICMS90_PREDBCST(nFEDETPRODdto.getDET_ICMS90_PREDBCST());
        nFEDETPROD.setDET_ICMS90_VBCST(nFEDETPRODdto.getDET_ICMS90_VBCST());
        nFEDETPROD.setDET_ICMS90_PICMSST(nFEDETPRODdto.getDET_ICMS90_PICMSST());
        nFEDETPROD.setDET_ICMS90_VICMSST(nFEDETPRODdto.getDET_ICMS90_VICMSST());
        nFEDETPROD.setDET_ICMS90_VICMSDESON(nFEDETPRODdto.getDET_ICMS90_VICMSDESON());
        nFEDETPROD.setDET_ICMS90_MOTDESICMS(nFEDETPRODdto.getDET_ICMS90_MOTDESICMS());
        nFEDETPROD.setDET_IPI_CLENQ(nFEDETPRODdto.getDET_IPI_CLENQ());
        nFEDETPROD.setDET_IPI_CNPJPROD(nFEDETPRODdto.getDET_IPI_CNPJPROD());
        nFEDETPROD.setDET_IPI_CSELO(nFEDETPRODdto.getDET_IPI_CSELO());
        nFEDETPROD.setDET_IPI_QSELO(nFEDETPRODdto.getDET_IPI_QSELO());
        nFEDETPROD.setDET_IPI_CENQ(nFEDETPRODdto.getDET_IPI_CENQ());
        nFEDETPROD.setDET_IPITRIB_CST(nFEDETPRODdto.getDET_IPITRIB_CST());
        nFEDETPROD.setDET_IPITRIB_VBC(nFEDETPRODdto.getDET_IPITRIB_VBC());
        nFEDETPROD.setDET_IPITRIB_PIPI(nFEDETPRODdto.getDET_IPITRIB_PIPI());
        nFEDETPROD.setDET_IPITRIB_QUNID(nFEDETPRODdto.getDET_IPITRIB_QUNID());
        nFEDETPROD.setDET_IPITRIB_VUNID(nFEDETPRODdto.getDET_IPITRIB_VUNID());
        nFEDETPROD.setDET_IPITRIB_VIPI(nFEDETPRODdto.getDET_IPITRIB_VIPI());
        nFEDETPROD.setDET_PISALIQ_CST(nFEDETPRODdto.getDET_PISALIQ_CST());
        nFEDETPROD.setDET_PISALIQ_VBC(nFEDETPRODdto.getDET_PISALIQ_VBC());
        nFEDETPROD.setDET_PISALIQ_PPIS(nFEDETPRODdto.getDET_PISALIQ_PPIS());
        nFEDETPROD.setDET_PISALIQ_VPIS(nFEDETPRODdto.getDET_PISALIQ_VPIS());
        nFEDETPROD.setDET_PISQTDE_CST(nFEDETPRODdto.getDET_PISQTDE_CST());
        nFEDETPROD.setDET_PISQTDE_QBCPROD(nFEDETPRODdto.getDET_PISQTDE_QBCPROD());
        nFEDETPROD.setDET_PISQTDE_VALIQPROD(nFEDETPRODdto.getDET_PISQTDE_VALIQPROD());
        nFEDETPROD.setDET_PISQTDE_VPIS(nFEDETPRODdto.getDET_PISQTDE_VPIS());
        nFEDETPROD.setDET_PISOUTR_CST(nFEDETPRODdto.getDET_PISOUTR_CST());
        nFEDETPROD.setDET_PISOUTR_VBC(nFEDETPRODdto.getDET_PISOUTR_VBC());
        nFEDETPROD.setDET_PISOUTR_PPIS(nFEDETPRODdto.getDET_PISOUTR_PPIS());
        nFEDETPROD.setDET_PISOUTR_VPIS(nFEDETPRODdto.getDET_PISOUTR_VPIS());
        nFEDETPROD.setDET_PISST_VBC(nFEDETPRODdto.getDET_PISST_VBC());
        nFEDETPROD.setDET_PISST_PPIS(nFEDETPRODdto.getDET_PISST_PPIS());
        nFEDETPROD.setDET_PISST_QBCPROD(nFEDETPRODdto.getDET_PISST_QBCPROD());
        nFEDETPROD.setDET_PISST_VALIQPROD(nFEDETPRODdto.getDET_PISST_VALIQPROD());
        nFEDETPROD.setDET_PISST_VPIS(nFEDETPRODdto.getDET_PISST_VPIS());
        nFEDETPROD.setDET_COFINSALIQ_CST(nFEDETPRODdto.getDET_COFINSALIQ_CST());
        nFEDETPROD.setDET_COFINSALIQ_VBC(nFEDETPRODdto.getDET_COFINSALIQ_VBC());
        nFEDETPROD.setDET_COFINSALIQ_PCOFINS(nFEDETPRODdto.getDET_COFINSALIQ_PCOFINS());
        nFEDETPROD.setDET_COFINSALIQ_VCOFINS(nFEDETPRODdto.getDET_COFINSALIQ_VCOFINS());
        nFEDETPROD.setDET_COFINSQTDE_CST(nFEDETPRODdto.getDET_COFINSQTDE_CST());
        nFEDETPROD.setDET_COFINSQTDE_QBCPROD(nFEDETPRODdto.getDET_COFINSQTDE_QBCPROD());
        nFEDETPROD.setDET_COFINSQTDE_VALIQPROD(nFEDETPRODdto.getDET_COFINSQTDE_VALIQPROD());
        nFEDETPROD.setDET_COFINSQTDE_VCOFINS(nFEDETPRODdto.getDET_COFINSQTDE_VCOFINS());
        nFEDETPROD.setDET_COFINSOUTR_CST(nFEDETPRODdto.getDET_COFINSOUTR_CST());
        nFEDETPROD.setDET_COFINSOUTR_VBC(nFEDETPRODdto.getDET_COFINSOUTR_VBC());
        nFEDETPROD.setDET_COFINSOUTR_PCOFINS(nFEDETPRODdto.getDET_COFINSOUTR_PCOFINS());
        nFEDETPROD.setDET_COFINSOUTR_VCOFINS(nFEDETPRODdto.getDET_COFINSOUTR_VCOFINS());
        nFEDETPROD.setDET_COFINSST_VBC(nFEDETPRODdto.getDET_COFINSST_VBC());
        nFEDETPROD.setDET_COFINSST_PCOFINS(nFEDETPRODdto.getDET_COFINSST_PCOFINS());
        nFEDETPROD.setDET_COFINSST_QBCPROD(nFEDETPRODdto.getDET_COFINSST_QBCPROD());
        nFEDETPROD.setDET_COFINSST_VALIQPROD(nFEDETPRODdto.getDET_COFINSST_VALIQPROD());
        nFEDETPROD.setDET_COFINSST_VCOFINS(nFEDETPRODdto.getDET_COFINSST_VCOFINS());
        nFEDETPROD.setDET_INFADPROD(nFEDETPRODdto.getDET_INFADPROD());
        nFEDETPROD.setDET_PROD_CEST(nFEDETPRODdto.getDET_PROD_CEST());
        nFEDETPROD.setDET_PROD_CEAN(nFEDETPRODdto.getDET_PROD_CEAN());
        nFEDETPROD.setDET_PROD_CEANTRIB(nFEDETPRODdto.getDET_PROD_CEANTRIB());
        nFEDETPROD.setDET_IBS_CST(nFEDETPRODdto.getDET_IBS_CST());
        nFEDETPROD.setDET_IBS_CCLASSTRIB(nFEDETPRODdto.getDET_IBS_CCLASSTRIB());
        nFEDETPROD.setDET_IBS_PIBSTOTAL(nFEDETPRODdto.getDET_IBS_PIBSTOTAL());
        nFEDETPROD.setDET_IBS_PIBSUF(nFEDETPRODdto.getDET_IBS_PIBSUF());
        nFEDETPROD.setDET_IBS_PIBSMUN(nFEDETPRODdto.getDET_IBS_PIBSMUN());
        nFEDETPROD.setDET_IBSUF_VBC(nFEDETPRODdto.getDET_IBSUF_VBC());
        nFEDETPROD.setDET_IBSUF_PIBSUF(nFEDETPRODdto.getDET_IBSUF_PIBSUF());
        nFEDETPROD.setDET_IBSUF_VIBSUF(nFEDETPRODdto.getDET_IBSUF_VIBSUF());
        nFEDETPROD.setDET_IBSMUN_VBC(nFEDETPRODdto.getDET_IBSMUN_VBC());
        nFEDETPROD.setDET_IBSMUN_PIBSMUN(nFEDETPRODdto.getDET_IBSMUN_PIBSMUN());
        nFEDETPROD.setDET_IBSMUN_VIBSMUN(nFEDETPRODdto.getDET_IBSMUN_VIBSMUN());
        nFEDETPROD.setDET_IS_CST(nFEDETPRODdto.getDET_IS_CST());
        nFEDETPROD.setDET_IS_CCLASSTRIB(nFEDETPRODdto.getDET_IS_CCLASSTRIB());
        nFEDETPROD.setDET_IS_VBCIMPSEL(nFEDETPRODdto.getDET_IS_VBCIMPSEL());
        nFEDETPROD.setDET_IS_PIMPSEL(nFEDETPRODdto.getDET_IS_PIMPSEL());
        nFEDETPROD.setDET_IS_VIMPSEL(nFEDETPRODdto.getDET_IS_VIMPSEL());
        nFEDETPROD.setDET_IS_PIMPSELESPEC(nFEDETPRODdto.getDET_IS_PIMPSELESPEC());
        nFEDETPROD.setDET_IS_UTRIB(nFEDETPRODdto.getDET_IS_UTRIB());
        nFEDETPROD.setDET_IS_QTRIB(nFEDETPRODdto.getDET_IS_QTRIB());
        nFEDETPROD.setDET_IS_CSTIBSCBS(nFEDETPRODdto.getDET_IS_CSTIBSCBS());
        nFEDETPROD.setDET_IS_CCLASSTRIBIBSCBS(nFEDETPRODdto.getDET_IS_CCLASSTRIBIBSCBS());
        nFEDETPROD.setDET_CBS_CST(nFEDETPRODdto.getDET_CBS_CST());
        nFEDETPROD.setDET_CBS_CCLASSTRIB(nFEDETPRODdto.getDET_CBS_CCLASSTRIB());
        nFEDETPROD.setDET_CBS_PCBSREF(nFEDETPRODdto.getDET_CBS_PCBSREF());
        nFEDETPROD.setDET_CBS_VBC(nFEDETPRODdto.getDET_CBS_VBC());
        nFEDETPROD.setDET_CBS_PCBS(nFEDETPRODdto.getDET_CBS_PCBS());
        nFEDETPROD.setDET_CBS_VCBS(nFEDETPRODdto.getDET_CBS_VCBS());
        return nFEDETPROD;
    }

    public static List<NFEDETPRODdto> convertListNFEDETPRODtoListNFEDETPRODdto(List listNFEDETPROD) throws ParseException {
        List<NFEDETPRODdto> ListNFEDETPRODdto = new ArrayList<>();
        for (int i = 0; i < listNFEDETPROD.size(); i++) {
            NFEDETPROD nFEDETPROD = (NFEDETPROD) listNFEDETPROD.get(i);
            ListNFEDETPRODdto.add(convertNFEDETPRODtoNFEDETPRODdto(nFEDETPROD));
        }
        return ListNFEDETPRODdto;
    }

}
