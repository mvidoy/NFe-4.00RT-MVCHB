package com.backend.dtos;

import com.backend.models.CANFEDUPLIC;
import com.backend.models.CANFEIRDUPL;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CANFEIRDUPLdto implements Serializable {

    private Long Id;
    private Long CANFENOTFIS_Id;
    private Long CANFEDUPLIC_Id;

    private String IRE_CODI;
    private String IRE_PARC;
    private String IRE_TIPO;
    private String IRE_CLIE;
    private Date IRE_DTPG;
    private String IRE_CONT;
    private String IRE_DOCU;
    private String IRE_NONU;
    private String IRE_DTPR;
    private String IRE_FLIM;
    private String IRE_DCDE;
    private String IRE_CHEQ;
    private Double IRE_VLPG;
    private Double IRE_VLDE;
    private Double IRE_VLVE;
    private Double IRE_VLJU;

    private CANFEDUPLIC CANFEDUPLIC;
    private List<CANFEIRDUPLdto> CANFEIRDUPLdto;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Long getCANFENOTFIS_Id() {
        return CANFENOTFIS_Id;
    }

    public void setCANFENOTFIS_Id(Long CANFENOTFIS_Id) {
        this.CANFENOTFIS_Id = CANFENOTFIS_Id;
    }

    public Long getCANFEDUPLIC_Id() {
        return CANFEDUPLIC_Id;
    }

    public void setCANFEDUPLIC_Id(Long CANFEDUPLIC_Id) {
        this.CANFEDUPLIC_Id = CANFEDUPLIC_Id;
    }

    public String getIRE_CODI() {
        return IRE_CODI;
    }

    public void setIRE_CODI(String IRE_CODI) {
        this.IRE_CODI = IRE_CODI;
    }

    public String getIRE_CLIE() {
        return IRE_CLIE;
    }

    public void setIRE_CLIE(String IRE_CLIE) {
        this.IRE_CLIE = IRE_CLIE;
    }

    public Date getIRE_DTPG() {
        return IRE_DTPG;
    }

    public void setIRE_DTPG(Date IRE_DTPG) {
        this.IRE_DTPG = IRE_DTPG;
    }

    public String getIRE_PARC() {
        return IRE_PARC;
    }

    public void setIRE_PARC(String IRE_PARC) {
        this.IRE_PARC = IRE_PARC;
    }

    public String getIRE_TIPO() {
        return IRE_TIPO;
    }

    public void setIRE_TIPO(String IRE_TIPO) {
        this.IRE_TIPO = IRE_TIPO;
    }

    public String getIRE_CONT() {
        return IRE_CONT;
    }

    public void setIRE_CONT(String IRE_CONT) {
        this.IRE_CONT = IRE_CONT;
    }

    public String getIRE_DOCU() {
        return IRE_DOCU;
    }

    public void setIRE_DOCU(String IRE_DOCU) {
        this.IRE_DOCU = IRE_DOCU;
    }

    public String getIRE_NONU() {
        return IRE_NONU;
    }

    public void setIRE_NONU(String IRE_NONU) {
        this.IRE_NONU = IRE_NONU;
    }

    public String getIRE_DTPR() {
        return IRE_DTPR;
    }

    public void setIRE_DTPR(String IRE_DTPR) {
        this.IRE_DTPR = IRE_DTPR;
    }

    public String getIRE_FLIM() {
        return IRE_FLIM;
    }

    public void setIRE_FLIM(String IRE_FLIM) {
        this.IRE_FLIM = IRE_FLIM;
    }

    public String getIRE_DCDE() {
        return IRE_DCDE;
    }

    public void setIRE_DCDE(String IRE_DCDE) {
        this.IRE_DCDE = IRE_DCDE;
    }

    public String getIRE_CHEQ() {
        return IRE_CHEQ;
    }

    public void setIRE_CHEQ(String IRE_CHEQ) {
        this.IRE_CHEQ = IRE_CHEQ;
    }

    public Double getIRE_VLPG() {
        return IRE_VLPG;
    }

    public void setIRE_VLPG(Double IRE_VLPG) {
        this.IRE_VLPG = IRE_VLPG;
    }

    public Double getIRE_VLDE() {
        return IRE_VLDE;
    }

    public void setIRE_VLDE(Double IRE_VLDE) {
        this.IRE_VLDE = IRE_VLDE;
    }

    public Double getIRE_VLVE() {
        return IRE_VLVE;
    }

    public void setIRE_VLVE(Double IRE_VLVE) {
        this.IRE_VLVE = IRE_VLVE;
    }

    public Double getIRE_VLJU() {
        return IRE_VLJU;
    }

    public void setIRE_VLJU(Double IRE_VLJU) {
        this.IRE_VLJU = IRE_VLJU;
    }

    public CANFEDUPLIC getCANFEDUPLIC() {
        return CANFEDUPLIC;
    }

    public void setCANFEDUPLIC(CANFEDUPLIC CANFEDUPLIC) {
        this.CANFEDUPLIC = CANFEDUPLIC;
    }

    public List<CANFEIRDUPLdto> getCANFEIRDUPLdto() {
        return CANFEIRDUPLdto;
    }

    public void setCANFEIRDUPLdto(List<CANFEIRDUPLdto> CANFEIRDUPLdto) {
        this.CANFEIRDUPLdto = CANFEIRDUPLdto;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public static CANFEIRDUPLdto convertCANFEIRDUPLtoCANFEIRDUPLdto(CANFEIRDUPL caNFEIRDUPL) {
        CANFEIRDUPLdto caNFEIRDUPLdto = new CANFEIRDUPLdto();
        caNFEIRDUPLdto.setId(caNFEIRDUPL.getId());
        caNFEIRDUPLdto.setCANFENOTFIS_Id(caNFEIRDUPL.getCANFENOTFIS_Id());
        caNFEIRDUPLdto.setCANFEDUPLIC_Id(caNFEIRDUPL.getCANFEDUPLIC_Id());
        caNFEIRDUPLdto.setIRE_CODI(caNFEIRDUPL.getIRE_CODI());
        caNFEIRDUPLdto.setIRE_PARC(caNFEIRDUPL.getIRE_PARC());
        caNFEIRDUPLdto.setIRE_TIPO(caNFEIRDUPL.getIRE_TIPO());
        caNFEIRDUPLdto.setIRE_CLIE(caNFEIRDUPL.getIRE_CLIE());
        caNFEIRDUPLdto.setIRE_DTPG(caNFEIRDUPL.getIRE_DTPG());
        caNFEIRDUPLdto.setIRE_CONT(caNFEIRDUPL.getIRE_CONT());
        caNFEIRDUPLdto.setIRE_DOCU(caNFEIRDUPL.getIRE_DOCU());
        caNFEIRDUPLdto.setIRE_NONU(caNFEIRDUPL.getIRE_NONU());
        caNFEIRDUPLdto.setIRE_DTPR(caNFEIRDUPL.getIRE_DTPR());
        caNFEIRDUPLdto.setIRE_FLIM(caNFEIRDUPL.getIRE_FLIM());
        caNFEIRDUPLdto.setIRE_DCDE(caNFEIRDUPL.getIRE_DCDE());
        caNFEIRDUPLdto.setIRE_CHEQ(caNFEIRDUPL.getIRE_CHEQ());
        caNFEIRDUPLdto.setIRE_VLPG(caNFEIRDUPL.getIRE_VLPG() != null ? caNFEIRDUPL.getIRE_VLPG() : 0.0);
        caNFEIRDUPLdto.setIRE_VLDE(caNFEIRDUPL.getIRE_VLDE() != null ? caNFEIRDUPL.getIRE_VLDE() : 0.0);
        caNFEIRDUPLdto.setIRE_VLVE(caNFEIRDUPL.getIRE_VLVE() != null ? caNFEIRDUPL.getIRE_VLVE() : 0.0);
        caNFEIRDUPLdto.setIRE_VLJU(caNFEIRDUPL.getIRE_VLJU() != null ? caNFEIRDUPL.getIRE_VLJU() : 0.0);
        return caNFEIRDUPLdto;
    }

    public static CANFEIRDUPL convertCANFEIRDUPLdtotoCANFEIRDUPL(CANFEIRDUPLdto caNFEIRDUPLdto) {
        CANFEIRDUPL caNFEIRDUPL = new CANFEIRDUPL();
        caNFEIRDUPL.setId(caNFEIRDUPLdto.getId());
        caNFEIRDUPL.setCANFENOTFIS_Id(caNFEIRDUPLdto.getCANFENOTFIS_Id());
        caNFEIRDUPL.setCANFEDUPLIC_Id(caNFEIRDUPLdto.getCANFEDUPLIC_Id());
        caNFEIRDUPL.setIRE_CODI(caNFEIRDUPLdto.getIRE_CODI());
        caNFEIRDUPL.setIRE_PARC(caNFEIRDUPLdto.getIRE_PARC());
        caNFEIRDUPL.setIRE_TIPO(caNFEIRDUPLdto.getIRE_TIPO());
        caNFEIRDUPL.setIRE_CLIE(caNFEIRDUPLdto.getIRE_CLIE());
        caNFEIRDUPL.setIRE_DTPG(caNFEIRDUPLdto.getIRE_DTPG());
        caNFEIRDUPL.setIRE_CONT(caNFEIRDUPLdto.getIRE_CONT());
        caNFEIRDUPL.setIRE_DOCU(caNFEIRDUPLdto.getIRE_DOCU());
        caNFEIRDUPL.setIRE_NONU(caNFEIRDUPLdto.getIRE_NONU());
        caNFEIRDUPL.setIRE_DTPR(caNFEIRDUPLdto.getIRE_DTPR());
        caNFEIRDUPL.setIRE_FLIM(caNFEIRDUPLdto.getIRE_FLIM());
        caNFEIRDUPL.setIRE_DCDE(caNFEIRDUPLdto.getIRE_DCDE());
        caNFEIRDUPL.setIRE_CHEQ(caNFEIRDUPLdto.getIRE_CHEQ());
        caNFEIRDUPL.setIRE_VLPG(caNFEIRDUPLdto.getIRE_VLPG());
        caNFEIRDUPL.setIRE_VLDE(caNFEIRDUPLdto.getIRE_VLDE());
        caNFEIRDUPL.setIRE_VLVE(caNFEIRDUPLdto.getIRE_VLVE());
        caNFEIRDUPL.setIRE_VLJU(caNFEIRDUPLdto.getIRE_VLJU());
        return caNFEIRDUPL;
    }

    public static List<CANFEIRDUPL> convertListCANFEIRDUPLdtotoListCANFEIRDUPL(List listCANFEIRDUPLdto) throws ParseException {
        List<CANFEIRDUPL> ListCANFEIRDUPL = new ArrayList<>();
        for (int i = 0; i < listCANFEIRDUPLdto.size(); i++) {
            CANFEIRDUPLdto caNFEIRDUPLdto = (CANFEIRDUPLdto) listCANFEIRDUPLdto.get(i);
            ListCANFEIRDUPL.add(convertCANFEIRDUPLdtotoCANFEIRDUPL(caNFEIRDUPLdto));
        }
        return ListCANFEIRDUPL;
    }

    public static List<CANFEIRDUPLdto> convertListCANFEIRDUPLtoListCANFEIRDUPLdto(List listCANFEIRDUPL, boolean caNFENOTFIS) throws ParseException {
        if (listCANFEIRDUPL == null) {
            return null;
        }
        List<CANFEIRDUPLdto> ListCANFEIRDUPLdto = new ArrayList<>();
        for (int i = 0; i < listCANFEIRDUPL.size(); i++) {
            CANFEIRDUPL caNFEIRDUPL = (CANFEIRDUPL) listCANFEIRDUPL.get(i);
            ListCANFEIRDUPLdto.add(convertCANFEIRDUPLtoCANFEIRDUPLdto(caNFEIRDUPL));
        }
        return ListCANFEIRDUPLdto;
    }

}
