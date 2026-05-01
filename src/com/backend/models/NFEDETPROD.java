package com.backend.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "nfe_det_prod")
@DynamicUpdate(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NFEDETPROD.NamedQueryFindByDetProdNNF",
            query = "SELECT c FROM NFEDETPROD c "
            + "WHERE "
            + "c.DET_PROD_CPROD IS NOT NULL AND "
            + "c.DET_PROD_NNF = :DET_PROD_NNF "
            + "ORDER BY c.DET_PROD_CPROD ASC, c.DET_PROD_ITEM ASC")
    ,
        @NamedQuery(name = "NFEDETPROD.NamedQueryFindByPRODNNFPRODITEM",
            query = "SELECT c FROM NFEDETPROD c "
            + "WHERE "
            + "c.DET_PROD_CPROD IS NOT NULL AND "
            + "c.DET_PROD_NNF = :DET_PROD_NNF AND "
            + "c.DET_PROD_ITEM = :DET_PROD_ITEM "
            + "ORDER BY c.DET_PROD_CPROD ASC, c.DET_PROD_ITEM ASC")
})

@SuppressWarnings("ConsistentAccessType")
public class NFEDETPROD implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer DET_PROD_NNF;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setDET_IBSUF_VIBSUF(Double DET_IBSUF_VTRIBOP) {
        this.DET_IBSUF_VIBSUF = DET_IBSUF_VTRIBOP;
    }

    public void setDET_IBSMUN_VBC(Double DET_IBSMUN_VBC) {
        this.DET_IBSMUN_VBC = DET_IBSMUN_VBC;
    }

    public void setDET_IBSMUN_PIBSMUN(Double DET_IBSMUN_PIBSMUN) {
        this.DET_IBSMUN_PIBSMUN = DET_IBSMUN_PIBSMUN;
    }

    public void setDET_IBSMUN_VIBSMUN(Double DET_IBSMUN_VTRIBOP) {
        this.DET_IBSMUN_VIBSMUN = DET_IBSMUN_VTRIBOP;
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

    public void setDET_CBS_VCBS(Double DET_CBS_VTRIBOP) {
        this.DET_CBS_VCBS = DET_CBS_VTRIBOP;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (DET_PROD_NNF != null ? DET_PROD_NNF.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caempres)) {
            return false;
        }
        NFEDETPROD other = (NFEDETPROD) object;
        if ((this.DET_PROD_NNF == null && other.DET_PROD_NNF != null) || (this.DET_PROD_NNF != null && !this.DET_PROD_NNF.equals(other.DET_PROD_NNF))) {
            return false;
        }
        return true;
    }

}
