package com.backend.dtos;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CALCULADORARETORNOdto {

    @SerializedName("objetos")
    private List<Objeto> objetos;

    @SerializedName("total")
    private Total total;

    public List<Objeto> getObjetos() {
        return objetos;
    }

    public void setObjetos(List<Objeto> objetos) {
        this.objetos = objetos;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public static class Objeto {

        @SerializedName("nObj")
        private int nObj;

        @SerializedName("tribCalc")
        private TribCalc tribCalc;

        public int getnObj() {
            return nObj;
        }

        public void setnObj(int nObj) {
            this.nObj = nObj;
        }

        public TribCalc getTribCalc() {
            return tribCalc;
        }

        public void setTribCalc(TribCalc tribCalc) {
            this.tribCalc = tribCalc;
        }
    }

    public static class TribCalc {

        @SerializedName("IBSCBS")
        private IBSCBS ibscbs;

        public IBSCBS getIbscbs() {
            return ibscbs;
        }

        public void setIbscbs(IBSCBS ibscbs) {
            this.ibscbs = ibscbs;
        }
    }

    public static class IBSCBS {

        @SerializedName("CST")
        private String cst;

        @SerializedName("cClassTrib")
        private String cClassTrib;

        @SerializedName("gIBSCBS")
        private GIBSCBS gibscbs;

        public String getCst() {
            return cst;
        }

        public void setCst(String cst) {
            this.cst = cst;
        }

        public String getcClassTrib() {
            return cClassTrib;
        }

        public void setcClassTrib(String cClassTrib) {
            this.cClassTrib = cClassTrib;
        }

        public GIBSCBS getGibscbs() {
            return gibscbs;
        }

        public void setGibscbs(GIBSCBS gibscbs) {
            this.gibscbs = gibscbs;
        }
    }

    public static class GIBSCBS {

        @SerializedName("vBC")
        private String vBC;

        @SerializedName("gIBSUF")
        private GIBSUF gibSUF;

        @SerializedName("gIBSMun")
        private GIBSMun gibSMun;

        @SerializedName("gCBS")
        private GCBS gcbs;

        public String getvBC() {
            return vBC;
        }

        public void setvBC(String vBC) {
            this.vBC = vBC;
        }

        public GIBSUF getGibSUF() {
            return gibSUF;
        }

        public void setGibSUF(GIBSUF gibSUF) {
            this.gibSUF = gibSUF;
        }

        public GIBSMun getGibSMun() {
            return gibSMun;
        }

        public void setGibSMun(GIBSMun gibSMun) {
            this.gibSMun = gibSMun;
        }

        public GCBS getGcbs() {
            return gcbs;
        }

        public void setGcbs(GCBS gcbs) {
            this.gcbs = gcbs;
        }
    }

    public static class GIBSUF {

        @SerializedName("pIBSUF")
        private String pIBSUF;

        @SerializedName("vIBSUF")
        private String vIBSUF;

        @SerializedName("memoriaCalculo")
        private String memoriaCalculo;

        public String getpIBSUF() {
            return pIBSUF;
        }

        public void setpIBSUF(String pIBSUF) {
            this.pIBSUF = pIBSUF;
        }

        public String getvIBSUF() {
            return vIBSUF;
        }

        public void setvIBSUF(String vIBSUF) {
            this.vIBSUF = vIBSUF;
        }

        public String getMemoriaCalculo() {
            return memoriaCalculo;
        }

        public void setMemoriaCalculo(String memoriaCalculo) {
            this.memoriaCalculo = memoriaCalculo;
        }
    }

    public static class GIBSMun {

        @SerializedName("pIBSMun")
        private String pIBSMun;

        @SerializedName("vIBSMun")
        private String vIBSMun;

        @SerializedName("memoriaCalculo")
        private String memoriaCalculo;

        public String getpIBSMun() {
            return pIBSMun;
        }

        public void setpIBSMun(String pIBSMun) {
            this.pIBSMun = pIBSMun;
        }

        public String getvIBSMun() {
            return vIBSMun;
        }

        public void setvIBSMun(String vIBSMun) {
            this.vIBSMun = vIBSMun;
        }

        public String getMemoriaCalculo() {
            return memoriaCalculo;
        }

        public void setMemoriaCalculo(String memoriaCalculo) {
            this.memoriaCalculo = memoriaCalculo;
        }
    }

    public static class GCBS {

        @SerializedName("pCBS")
        private String pCBS;

        @SerializedName("vCBS")
        private String vCBS;

        @SerializedName("memoriaCalculo")
        private String memoriaCalculo;

        public String getpCBS() {
            return pCBS;
        }

        public void setpCBS(String pCBS) {
            this.pCBS = pCBS;
        }

        public String getvCBS() {
            return vCBS;
        }

        public void setvCBS(String vCBS) {
            this.vCBS = vCBS;
        }

        public String getMemoriaCalculo() {
            return memoriaCalculo;
        }

        public void setMemoriaCalculo(String memoriaCalculo) {
            this.memoriaCalculo = memoriaCalculo;
        }
    }

    public static class Total {

        @SerializedName("IBSCBSTot")
        private IBSCBSTot ibscbsTot;

        public IBSCBSTot getIbscbsTot() {
            return ibscbsTot;
        }

        public void setIbscbsTot(IBSCBSTot ibscbsTot) {
            this.ibscbsTot = ibscbsTot;
        }
    }

    public static class IBSCBSTot {

        @SerializedName("vBCIBSCBS")
        private String vBCIBSCBS;

        @SerializedName("gIBS")
        private GIBS gibs;

        @SerializedName("gCBS")
        private GCBS gcbs;

        public String getvBCIBSCBS() {
            return vBCIBSCBS;
        }

        public void setvBCIBSCBS(String vBCIBSCBS) {
            this.vBCIBSCBS = vBCIBSCBS;
        }

        public GIBS getGibs() {
            return gibs;
        }

        public void setGibs(GIBS gibs) {
            this.gibs = gibs;
        }

        public GCBS getGcbs() {
            return gcbs;
        }

        public void setGcbs(GCBS gcbs) {
            this.gcbs = gcbs;
        }
    }

    public static class GIBS {

        @SerializedName("gIBSUF")
        private GIBSUF gibsUF;

        @SerializedName("gIBSMun")
        private GIBSMun gibsMun;

        @SerializedName("vIBS")
        private String vIBS;

        @SerializedName("vCredPres")
        private String vCredPres;

        @SerializedName("vCredPresCondSus")
        private String vCredPresCondSus;

        public GIBSUF getGibsUF() {
            return gibsUF;
        }

        public void setGibsUF(GIBSUF gibsUF) {
            this.gibsUF = gibsUF;
        }

        public GIBSMun getGibsMun() {
            return gibsMun;
        }

        public void setGibsMun(GIBSMun gibsMun) {
            this.gibsMun = gibsMun;
        }

        public String getvIBS() {
            return vIBS;
        }

        public void setvIBS(String vIBS) {
            this.vIBS = vIBS;
        }

        public String getvCredPres() {
            return vCredPres;
        }

        public void setvCredPres(String vCredPres) {
            this.vCredPres = vCredPres;
        }

        public String getvCredPresCondSus() {
            return vCredPresCondSus;
        }

        public void setvCredPresCondSus(String vCredPresCondSus) {
            this.vCredPresCondSus = vCredPresCondSus;
        }
    }
} 
