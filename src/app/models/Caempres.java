/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;

/**
 *
 * @author Osvaldo Vidoy
 */

@Table(name = "caempres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caempres.findAll", query = "SELECT c FROM Caempres c")
    , @NamedQuery(name = "Caempres.findByEmpCodi", query = "SELECT c FROM Caempres c WHERE c.EMP_CODI = :EMP_CODI")
    , @NamedQuery(name = "Caempres.findByEmpRzso", query = "SELECT c FROM Caempres c WHERE c.empRzso = :empRzso")
    , @NamedQuery(name = "Caempres.findByEmpEnde", query = "SELECT c FROM Caempres c WHERE c.empEnde = :empEnde")
    , @NamedQuery(name = "Caempres.findByEmpBair", query = "SELECT c FROM Caempres c WHERE c.empBair = :empBair")
    , @NamedQuery(name = "Caempres.findByEmpCida", query = "SELECT c FROM Caempres c WHERE c.empCida = :empCida")
    , @NamedQuery(name = "Caempres.findByEmpEsta", query = "SELECT c FROM Caempres c WHERE c.empEsta = :empEsta")
    , @NamedQuery(name = "Caempres.findByEmpCep0", query = "SELECT c FROM Caempres c WHERE c.empCep0 = :empCep0")
    , @NamedQuery(name = "Caempres.findByEmpCgc0", query = "SELECT c FROM Caempres c WHERE c.EMP_CGC0 = :EMP_CGC0")
    , @NamedQuery(name = "Caempres.findByEmpInse", query = "SELECT c FROM Caempres c WHERE c.empInse = :empInse")
    , @NamedQuery(name = "Caempres.findByEmpFone", query = "SELECT c FROM Caempres c WHERE c.empFone = :empFone")
    , @NamedQuery(name = "Caempres.findByEmpFax0", query = "SELECT c FROM Caempres c WHERE c.empFax0 = :empFax0")
    , @NamedQuery(name = "Caempres.findByEmpSigl", query = "SELECT c FROM Caempres c WHERE c.empSigl = :empSigl")
    , @NamedQuery(name = "Caempres.findByEmpMoed", query = "SELECT c FROM Caempres c WHERE c.empMoed = :empMoed")
    , @NamedQuery(name = "Caempres.findByEmpAlic", query = "SELECT c FROM Caempres c WHERE c.empAlic = :empAlic")
    , @NamedQuery(name = "Caempres.findByEmpAlis", query = "SELECT c FROM Caempres c WHERE c.empAlis = :empAlis")
    , @NamedQuery(name = "Caempres.findByEmpSavl", query = "SELECT c FROM Caempres c WHERE c.empSavl = :empSavl")
    , @NamedQuery(name = "Caempres.findByEmpSaic", query = "SELECT c FROM Caempres c WHERE c.empSaic = :empSaic")
    , @NamedQuery(name = "Caempres.findByEmpSape", query = "SELECT c FROM Caempres c WHERE c.empSape = :empSape")
    , @NamedQuery(name = "Caempres.findByEmpInst", query = "SELECT c FROM Caempres c WHERE c.empInst = :empInst")
    , @NamedQuery(name = "Caempres.findByEmpOmv0", query = "SELECT c FROM Caempres c WHERE c.empOmv0 = :empOmv0")
    , @NamedQuery(name = "Caempres.findByEmpUlpe", query = "SELECT c FROM Caempres c WHERE c.empUlpe = :empUlpe")
    , @NamedQuery(name = "Caempres.findByEmpUlno", query = "SELECT c FROM Caempres c WHERE c.empUlno = :empUlno")
    , @NamedQuery(name = "Caempres.findByEmpAlpis", query = "SELECT c FROM Caempres c WHERE c.empAlpis = :empAlpis")
    , @NamedQuery(name = "Caempres.findByEmpAlcofins", query = "SELECT c FROM Caempres c WHERE c.empAlcofins = :empAlcofins")
    , @NamedQuery(name = "Caempres.findByEmpCodigomunicipio", query = "SELECT c FROM Caempres c WHERE c.empCodigomunicipio = :empCodigomunicipio")
    , @NamedQuery(name = "Caempres.findByEmpNfeBackup", query = "SELECT c FROM Caempres c WHERE c.empNfeBackup = :empNfeBackup")
    , @NamedQuery(name = "Caempres.findByEmpPais", query = "SELECT c FROM Caempres c WHERE c.empPais = :empPais")
    , @NamedQuery(name = "Caempres.findByEmpNumero", query = "SELECT c FROM Caempres c WHERE c.empNumero = :empNumero")
    , @NamedQuery(name = "Caempres.findByEmpCnaefiscal", query = "SELECT c FROM Caempres c WHERE c.empCnaefiscal = :empCnaefiscal")
    , @NamedQuery(name = "Caempres.findByEmpInscrisaomunicipal", query = "SELECT c FROM Caempres c WHERE c.empInscrisaomunicipal = :empInscrisaomunicipal")
    , @NamedQuery(name = "Caempres.findByEmpInscrisaomunicipalsubsttributario", query = "SELECT c FROM Caempres c WHERE c.empInscrisaomunicipalsubsttributario = :empInscrisaomunicipalsubsttributario")
    , @NamedQuery(name = "Caempres.findByEmpRegimetributario", query = "SELECT c FROM Caempres c WHERE c.empRegimetributario = :empRegimetributario")
    , @NamedQuery(name = "Caempres.findByEmpLogradouro", query = "SELECT c FROM Caempres c WHERE c.empLogradouro = :empLogradouro")
    , @NamedQuery(name = "Caempres.findByEmpComplemento", query = "SELECT c FROM Caempres c WHERE c.empComplemento = :empComplemento")
    , @NamedQuery(name = "Caempres.findByEmpEmail", query = "SELECT c FROM Caempres c WHERE c.empEmail = :empEmail")
    , @NamedQuery(name = "Caempres.findByEmpLocalcertificado", query = "SELECT c FROM Caempres c WHERE c.empLocalcertificado = :empLocalcertificado")
    , @NamedQuery(name = "Caempres.findByEmpSenhacertificado", query = "SELECT c FROM Caempres c WHERE c.empSenhacertificado = :empSenhacertificado")
    , @NamedQuery(name = "Caempres.findByEmpLocalcertificadocacerts", query = "SELECT c FROM Caempres c WHERE c.empLocalcertificadocacerts = :empLocalcertificadocacerts")
    , @NamedQuery(name = "Caempres.findByEmpLocalcertificadocacertsmdfe", query = "SELECT c FROM Caempres c WHERE c.empLocalcertificadocacertsmdfe = :empLocalcertificadocacertsmdfe")
    , @NamedQuery(name = "Caempres.findByEmpAmbientenfe", query = "SELECT c FROM Caempres c WHERE c.empAmbientenfe = :empAmbientenfe")
    , @NamedQuery(name = "Caempres.findByEmpAmbientemdfe", query = "SELECT c FROM Caempres c WHERE c.empAmbientemdfe = :empAmbientemdfe")})
public class Caempres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "EMP_CODI")
     Long EMP_CODI;
    @Column(name = "emp_rzso")
    private String empRzso;
    @Column(name = "emp_ende")
    private String empEnde;
    @Column(name = "emp_bair")
    private String empBair;
    @Column(name = "emp_cida")
    private String empCida;
    @Column(name = "emp_esta")
    private String empEsta;
    @Column(name = "emp_cep0")
    private String empCep0;
    @Column(name = "EMP_CGC0")
    private String EMP_CGC0;
    @Column(name = "emp_inse")
    private String empInse;
    @Column(name = "emp_fone")
    private String empFone;
    @Column(name = "emp_fax0")
    private String empFax0;
    @Column(name = "emp_sigl")
    private String empSigl;
    @Column(name = "emp_moed")
    private String empMoed;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "emp_alic")
    private Double empAlic;
    @Column(name = "emp_alis")
    private Double empAlis;
    @Column(name = "emp_savl")
    private Double empSavl;
    @Column(name = "emp_saic")
    private Double empSaic;
    @Column(name = "emp_sape")
    private Double empSape;
    @Column(name = "emp_inst")
    private String empInst;
    @Column(name = "emp_omv0")
    private String empOmv0;
    @Column(name = "emp_ulpe")
    private String empUlpe;
    @Column(name = "emp_ulno")
    private String empUlno;
    @Column(name = "emp_alpis")
    private Double empAlpis;
    @Column(name = "emp_alcofins")
    private Double empAlcofins;
    @Column(name = "emp_codigomunicipio")
    private String empCodigomunicipio;
    @Column(name = "emp_nfe_backup")
    @Temporal(TemporalType.DATE)
    private Date empNfeBackup;
    @Column(name = "emp_pais")
    private String empPais;
    @Column(name = "emp_numero")
    private String empNumero;
    @Column(name = "emp_cnaefiscal")
    private String empCnaefiscal;
    @Column(name = "emp_inscrisaomunicipal")
    private String empInscrisaomunicipal;
    @Column(name = "emp_inscrisaomunicipalsubsttributario")
    private String empInscrisaomunicipalsubsttributario;
    @Column(name = "emp_regimetributario")
    private String empRegimetributario;
    @Column(name = "emp_logradouro")
    private String empLogradouro;
    @Column(name = "emp_complemento")
    private String empComplemento;
    @Column(name = "emp_email")
    private String empEmail;
    @Column(name = "emp_localcertificado")
    private String empLocalcertificado;
    @Column(name = "emp_senhacertificado")
    private String empSenhacertificado;
    @Column(name = "emp_localcertificadocacerts")
    private String empLocalcertificadocacerts;
    @Column(name = "emp_localcertificadocacertsmdfe")
    private String empLocalcertificadocacertsmdfe;
    @Column(name = "emp_ambientenfe")
    private String empAmbientenfe;
    @Column(name = "emp_ambientemdfe")
    private String empAmbientemdfe;

    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "EMP_CERTIFICADO")
    private byte[] EMP_CERTIFICADO;

    public Caempres() {
    }

  
    public Caempres(Long empCodi) {
        this.EMP_CODI = empCodi;
    }

    public Long getEmpCodi() {
        return EMP_CODI;
    }
     
    public void setEmpCodi(Long empCodi) {
        this.EMP_CODI = empCodi;
    }
     
    public String getEmpRzso() {
        return empRzso;
    }

    public void setEmpRzso(String empRzso) {
        this.empRzso = empRzso;
    }

    public String getEmpEnde() {
        return empEnde;
    }

    public void setEmpEnde(String empEnde) {
        this.empEnde = empEnde;
    }

    public String getEmpBair() {
        return empBair;
    }

    public void setEmpBair(String empBair) {
        this.empBair = empBair;
    }

    public String getEmpCida() {
        return empCida;
    }

    public void setEmpCida(String empCida) {
        this.empCida = empCida;
    }

    public String getEmpEsta() {
        return empEsta;
    }

    public void setEmpEsta(String empEsta) {
        this.empEsta = empEsta;
    }

    public String getEmpCep0() {
        return empCep0;
    }

    public void setEmpCep0(String empCep0) {
        this.empCep0 = empCep0;
    }

    public String getEmpCgc0() {
        return EMP_CGC0;
    }

    public void setEmpCgc0(String empCgc0) {
        this.EMP_CGC0 = empCgc0;
    }

    public String getEmpInse() {
        return empInse;
    }

    public void setEmpInse(String empInse) {
        this.empInse = empInse;
    }

    public String getEmpFone() {
        return empFone;
    }

    public void setEmpFone(String empFone) {
        this.empFone = empFone;
    }

    public String getEmpFax0() {
        return empFax0;
    }

    public void setEmpFax0(String empFax0) {
        this.empFax0 = empFax0;
    }

    public String getEmpSigl() {
        return empSigl;
    }

    public void setEmpSigl(String empSigl) {
        this.empSigl = empSigl;
    }

    public String getEmpMoed() {
        return empMoed;
    }

    public void setEmpMoed(String empMoed) {
        this.empMoed = empMoed;
    }

    public Double getEmpAlic() {
        return empAlic;
    }

    public void setEmpAlic(Double empAlic) {
        this.empAlic = empAlic;
    }

    public Double getEmpAlis() {
        return empAlis;
    }

    public void setEmpAlis(Double empAlis) {
        this.empAlis = empAlis;
    }

    public Double getEmpSavl() {
        return empSavl;
    }

    public void setEmpSavl(Double empSavl) {
        this.empSavl = empSavl;
    }

    public Double getEmpSaic() {
        return empSaic;
    }

    public void setEmpSaic(Double empSaic) {
        this.empSaic = empSaic;
    }

    public Double getEmpSape() {
        return empSape;
    }

    public void setEmpSape(Double empSape) {
        this.empSape = empSape;
    }

    public String getEmpInst() {
        return empInst;
    }

    public void setEmpInst(String empInst) {
        this.empInst = empInst;
    }

    public String getEmpOmv0() {
        return empOmv0;
    }

    public void setEmpOmv0(String empOmv0) {
        this.empOmv0 = empOmv0;
    }

    public String getEmpUlpe() {
        return empUlpe;
    }

    public void setEmpUlpe(String empUlpe) {
        this.empUlpe = empUlpe;
    }

    public String getEmpUlno() {
        return empUlno;
    }

    public void setEmpUlno(String empUlno) {
        this.empUlno = empUlno;
    }

    public Double getEmpAlpis() {
        return empAlpis;
    }

    public void setEmpAlpis(Double empAlpis) {
        this.empAlpis = empAlpis;
    }

    public Double getEmpAlcofins() {
        return empAlcofins;
    }

    public void setEmpAlcofins(Double empAlcofins) {
        this.empAlcofins = empAlcofins;
    }

    public String getEmpCodigomunicipio() {
        return empCodigomunicipio;
    }

    public void setEmpCodigomunicipio(String empCodigomunicipio) {
        this.empCodigomunicipio = empCodigomunicipio;
    }

    public Date getEmpNfeBackup() {
        return empNfeBackup;
    }

    public void setEmpNfeBackup(Date empNfeBackup) {
        this.empNfeBackup = empNfeBackup;
    }

    public String getEmpPais() {
        return empPais;
    }

    public void setEmpPais(String empPais) {
        this.empPais = empPais;
    }

    public String getEmpNumero() {
        return empNumero;
    }

    public void setEmpNumero(String empNumero) {
        this.empNumero = empNumero;
    }

    public String getEmpCnaefiscal() {
        return empCnaefiscal;
    }

    public void setEmpCnaefiscal(String empCnaefiscal) {
        this.empCnaefiscal = empCnaefiscal;
    }

    public String getEmpInscrisaomunicipal() {
        return empInscrisaomunicipal;
    }

    public void setEmpInscrisaomunicipal(String empInscrisaomunicipal) {
        this.empInscrisaomunicipal = empInscrisaomunicipal;
    }

    public String getEmpInscrisaomunicipalsubsttributario() {
        return empInscrisaomunicipalsubsttributario;
    }

    public void setEmpInscrisaomunicipalsubsttributario(String empInscrisaomunicipalsubsttributario) {
        this.empInscrisaomunicipalsubsttributario = empInscrisaomunicipalsubsttributario;
    }

    public String getEmpRegimetributario() {
        return empRegimetributario;
    }

    public void setEmpRegimetributario(String empRegimetributario) {
        this.empRegimetributario = empRegimetributario;
    }

    public String getEmpLogradouro() {
        return empLogradouro;
    }

    public void setEmpLogradouro(String empLogradouro) {
        this.empLogradouro = empLogradouro;
    }

    public String getEmpComplemento() {
        return empComplemento;
    }

    public void setEmpComplemento(String empComplemento) {
        this.empComplemento = empComplemento;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpLocalcertificado() {
        return empLocalcertificado;
    }

    public void setEmpLocalcertificado(String empLocalcertificado) {
        this.empLocalcertificado = empLocalcertificado;
    }

    public String getEmpSenhacertificado() {
        return empSenhacertificado;
    }

    public void setEmpSenhacertificado(String empSenhacertificado) {
        this.empSenhacertificado = empSenhacertificado;
    }

    public String getEmpLocalcertificadocacerts() {
        return empLocalcertificadocacerts;
    }

    public void setEmpLocalcertificadocacerts(String empLocalcertificadocacerts) {
        this.empLocalcertificadocacerts = empLocalcertificadocacerts;
    }

    public String getEmpLocalcertificadocacertsmdfe() {
        return empLocalcertificadocacertsmdfe;
    }

    public void setEmpLocalcertificadocacertsmdfe(String empLocalcertificadocacertsmdfe) {
        this.empLocalcertificadocacertsmdfe = empLocalcertificadocacertsmdfe;
    }

    public String getEmpAmbientenfe() {
        return empAmbientenfe;
    }

    public void setEmpAmbientenfe(String empAmbientenfe) {
        this.empAmbientenfe = empAmbientenfe;
    }

    public String getEmpAmbientemdfe() {
        return empAmbientemdfe;
    }

    public void setEmpAmbientemdfe(String empAmbientemdfe) {
        this.empAmbientemdfe = empAmbientemdfe;
    }

    @Column(name = "EMP_CERTIFICADO")
    @Basic(fetch = FetchType.LAZY)
    public byte[] getEMP_CERTIFICADO() {
        return EMP_CERTIFICADO;
    }

    public void setEMP_CERTIFICADO(byte[] empCertificado) {
        this.EMP_CERTIFICADO = empCertificado;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (EMP_CODI != null ? EMP_CODI.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caempres)) {
            return false;
        }
        Caempres other = (Caempres) object;
        if ((this.EMP_CODI == null && other.EMP_CODI != null) || (this.EMP_CODI != null && !this.EMP_CODI.equals(other.EMP_CODI))) {
            return false;
        }
        return true;
    }
    

    //@Override
    //public String toString() {
    //return "app.models.Caempres[ empCodi=" + empCodi + " ]";
    //}
}
