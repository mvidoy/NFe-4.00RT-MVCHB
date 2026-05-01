package app.models;

public class NFE {

    public NFE() {
    }

    public NFE eNFE;

    public NFE(NFE eNFE) {
        this.eNFE = eNFE;
    }

    private String infnfe_statusnfe;
    private String infnfe_versao;
    private String infnfe_id;
    private String infnfe_pedidovenda;
    private String infnfe_danfeimpresso;
    private String infnfe_xmlexportado;
    private String ide_cuf;
    private String ide_xuf;
    private String ide_cnf;
    private String ide_natop;
    private String ide_indpag;
    private String ide_mod;
    private String ide_serie;
    private String ide_nnf;
    private String ide_dhemi;
    private String ide_dhsaient;
    private String ide_tpnf;
    private String ide_iddest;
    private String ide_cmunfg;
    private String ide_xmunfg;
    private String ide_cmunfgibs;
    private String ide_indmultajuros;
    private String ide_tpimp;
    private String ide_tpemis;
    private String ide_cdv;
    private String ide_tpamb;
    private String ide_finnfe;
    private String ide_tpnfdebito;
    private String ide_tpnfcredito;
    private String ide_indfinal;
    private String ide_indpres;
    private String ide_procemi;
    private String ide_verproc;
    private String ide_dhcont;
    private String ide_xjust;
    private String emit_cnpj;
    private String emit_cpf;
    private String emit_xnome;
    private String emit_xfant;
    private String enderemit_xlgr;
    private String enderemit_nro;
    private String enderemit_xcpl;
    private String enderemit_xbairro;
    private String enderemit_cmun;
    private String enderemit_xmun;
    private String enderemit_uf;
    private String enderemit_cep;
    private String enderemit_cpais;
    private String enderemit_xpais;
    private String enderemit_fone;
    private String enderemit_ie;
    private String enderemit_iest;
    private String enderemit_im;
    private String enderemit_cnae;
    private String enderemit_crt;
    private String dest_cnpj;
    private String dest_cpf;
    private String dest_idestrangeiro;
    private String dest_xnome;
    private String enderdest_xlgr;
    private String enderdest_nro;
    private String enderdest_xcpl;
    private String enderdest_xbairro;
    private String enderdest_cmun;
    private String enderdest_xmun;
    private String enderdest_uf;
    private String enderdest_cep;
    private String enderdest_cpais;
    private String enderdest_xpais;
    private String enderdest_fone;
    private String enderdest_indiedest;
    private String enderdest_ie;
    private String enderdest_isuf;
    private String enderdest_im;
    private String enderdest_email;
    private String retirada_cnpj;
    private String retirada_cpf;
    private String retirada_xlgr;
    private String retirada_nro;
    private String retirada_xcpl;
    private String retirada_xbairro;
    private String retirada_cmun;
    private String retirada_xmun;
    private String retirada_uf;
    private String entrega_cnpj;
    private String entrega_cpf;
    private String entrega_xlgr;
    private String entrega_nro;
    private String entrega_xcpl;
    private String entrega_xbairro;
    private String entrega_cmun;
    private String entrega_xmun;
    private String entrega_uf;
    private String retenvinfe_versao;
    private String retenvinfe_cstat;
    private String retenvinfe_dhrecbto;
    private String retenvinfe_tpamb;
    private String retenvinfe_veraplic;
    private String retenvinfe_xmsg;
    private String retenvinfe_cuf;
    private String retenvinfe_tmed;
    private String retenvinfe_nrec;
    private String retconsrecinfe_versao;
    private String retconsrecinfe_cstat;
    private String retconsrecinfe_nrec;
    private String retconsrecinfe_dhrecbto;
    private String retconsrecinfe_tpamb;
    private String retconsrecinfe_veraplic;
    private String retconsrecinfe_xmsg;
    private String retconsrecinfe_cmsg;
    private String retconsrecinfe_xmotivo;
    private String retconsrecinfe_cuf;
    private String retconsrecinfe_protnfe_chnfe;
    private String retconsrecinfe_protnfe_versao;
    private String retconsrecinfe_protnfe_tpamb;
    private String retconsrecinfe_protnfe_veraplic;
    private String retconsrecinfe_protnfe_cstat;
    private String retconsrecinfe_protnfe_xmotivo;
    private String retconsrecinfe_protnfe_dhrecbto;
    private String retconsrecinfe_protnfe_xmsg;
    private String protnfe_versao;
    private String infprot_tpamb;
    private String infprot_veraplic;
    private String infprot_chnfe;
    private String infprot_dhrecbto;
    private String infprot_nprot;
    private String infprot_digval;
    private String infprot_cstat;
    private String infprot_xmotivo;
    private String icmstot_vbc;
    private String icmstot_vicms;
    private String icmstot_vicmsdeson;
    private String icmstot_vbcst;
    private String icmstot_vst;
    private String icmstot_vprod;
    private String icmstot_vfrete;
    private String icmstot_vseg;
    private String icmstot_vdesc;
    private String icmstot_vii;
    private String icmstot_vipi;
    private String icmstot_vpis;
    private String icmstot_vcofins;
    private String icmstot_voutro;
    private String icmstot_vnf;
    private String icmstot_vtottrib;
    private String transp_modfrete;
    private String transporta_cnpj;
    private String transporta_cpf;
    private String transporta_xnome;
    private String transporta_ie;
    private String transporta_xender;
    private String transporta_xmun;
    private String transporta_uf;
    private String vol_qvol;
    private String vol_esp;
    private String vol_marca;
    private String vol_nvol;
    private String vol_pesol;
    private String vol_pesob;
    private String fat_nfat;
    private String fat_vorig;
    private String fat_vdesc;
    private String fat_vliq;
    private String infadic_infadfisco;
    private String infadic_infcpl;
    private String signature_cnpj;
    private String signature_emissor;
    private String signature_assunto;
    private String signature_datainicial;
    private String signature_datafinal;
    private String retenvevento_versao;
    private String retenvevento_idlote;
    private String retenvevento_tpamb;
    private String retenvevento_veraplic;
    private String retenvevento_corgao;
    private String retenvevento_cstat;
    private String retenvevento_xmotivo;
    private String retenvevento_retevento_versao;
    private String retenvevento_retevento_infevento_id;
    private String retenvevento_retevento_infevento_tpamb;
    private String retenvevento_retevento_infevento_veraplic;
    private String retenvevento_retevento_infevento_corgao;
    private String retenvevento_retevento_infevento_cstat;
    private String retenvevento_retevento_infevento_xmotivo;
    private String retenvevento_retevento_infevento_chnfe;
    private String retenvevento_retevento_infevento_tpevento;
    private String retenvevento_retevento_infevento_xevento;
    private String retenvevento_retevento_infevento_nseqevento;
    private String retenvevento_retevento_infevento_cnpjdest;
    private String retenvevento_retevento_infevento_cpfdest;
    private String retenvevento_retevento_infevento_emaildest;
    private String retenvevento_retevento_infevento_dhregevento;
    private String retenvevento_retevento_infevento_nprot;
    private String xml_assinado;
    private String xml_autorizado;
    private String xml_envevento_assinado;
    private String xml_envevento_autorizado;

    private String infnfe_danfexmlenviado;
    private String infnfe_tituloregistrado;
    private String infnfe_boletoenviado;

    private String tot_is_vbcsel;
    private String tot_is_vimpsel;
    private String tot_is_vcrespres;

    private String tot_ibs_uf_vcrespres;
    private String tot_ibs_uf_vcredprescondsus;
    private String tot_ibs_uf_vdif;
    private String tot_ibs_uf_vdevtrib;
    private String tot_ibs_uf_vdeson;
    private String tot_ibs_uf_vibs;

    private String tot_ibs_mun_vcrespres;
    private String tot_ibs_mun_vcredprescondsus;
    private String tot_ibs_mun_vdif;
    private String tot_ibs_mun_vdevtrib;
    private String tot_ibs_mun_vdeson;
    private String tot_ibs_mun_vibs;

    private String tot_ibs_vcrespres;
    private String tot_ibs_vcredprescondsus;

    private String tot_cbs_vcrespres;
    private String tot_cbs_vcredprescondsus;
    private String tot_cbs_vdif;
    private String tot_cbs_vdevtrib;
    private String tot_cbs_vdeson;
    private String tot_cbs_vcbs;

    private String tot_vbcibscbs;
    private String tot_vtotnf;

    public String getInfnfe_statusnfe() {
        return infnfe_statusnfe;
    }

    public void setInfnfe_statusnfe(String infnfe_statusnfe) {
        this.infnfe_statusnfe = infnfe_statusnfe;
    }

    public String getInfnfe_versao() {
        return infnfe_versao;
    }

    public void setInfnfe_versao(String infnfe_versao) {
        this.infnfe_versao = infnfe_versao;
    }

    public String getInfnfe_id() {
        return infnfe_id;
    }

    public void setInfnfe_id(String infnfe_id) {
        this.infnfe_id = infnfe_id;
    }

    public String getInfnfe_pedidovenda() {
        return infnfe_pedidovenda;
    }

    public void setInfnfe_pedidovenda(String infnfe_pedidovenda) {
        this.infnfe_pedidovenda = infnfe_pedidovenda;
    }

    public String getInfnfe_danfeimpresso() {
        return infnfe_danfeimpresso;
    }

    public void setInfnfe_danfeimpresso(String infnfe_danfeimpresso) {
        this.infnfe_danfeimpresso = infnfe_danfeimpresso;
    }

    public String getInfnfe_xmlexportado() {
        return infnfe_xmlexportado;
    }

    public void setInfnfe_xmlexportado(String infnfe_xmlexportado) {
        this.infnfe_xmlexportado = infnfe_xmlexportado;
    }

    public String getInfnfe_danfexmlenviado() {
        return infnfe_danfexmlenviado;
    }

    public String getInfnfe_tituloregistrado() {
        return infnfe_tituloregistrado;
    }

    public String getInfnfe_boletoenviado() {
        return infnfe_boletoenviado;
    }

    public void setInfnfe_danfexmlenviado(String infnfe_danfexmlenviado) {
        this.infnfe_danfexmlenviado = infnfe_danfexmlenviado;
    }

    public void setInfnfe_tituloregistrado(String infnfe_tituloregistrado) {
        this.infnfe_tituloregistrado = infnfe_tituloregistrado;
    }

    public void setInfnfe_boletoenviado(String infnfe_boletoenviado) {
        this.infnfe_boletoenviado = infnfe_boletoenviado;
    }

    public String getIde_cuf() {
        return ide_cuf;
    }

    public void setIde_cuf(String ide_cuf) {
        this.ide_cuf = ide_cuf;
    }

    public String getIde_xuf() {
        return ide_xuf;
    }

    public void setIde_xuf(String ide_xuf) {
        this.ide_xuf = ide_xuf;
    }

    public String getIde_cnf() {
        return ide_cnf;
    }

    public void setIde_cnf(String ide_cnf) {
        this.ide_cnf = ide_cnf;
    }

    public String getIde_natop() {
        return ide_natop;
    }

    public void setIde_natop(String ide_natop) {
        this.ide_natop = ide_natop;
    }

    public String getIde_indpag() {
        return ide_indpag;
    }

    public void setIde_indpag(String ide_indpag) {
        this.ide_indpag = ide_indpag;
    }

    public String getIde_mod() {
        return ide_mod;
    }

    public void setIde_mod(String ide_mod) {
        this.ide_mod = ide_mod;
    }

    public String getIde_serie() {
        return ide_serie;
    }

    public void setIde_serie(String ide_serie) {
        this.ide_serie = ide_serie;
    }

    public String getIde_nnf() {
        return ide_nnf;
    }

    public void setIde_nnf(String ide_nnf) {
        this.ide_nnf = ide_nnf;
    }

    public String getIde_dhemi() {
        return ide_dhemi;
    }

    public void setIde_dhemi(String ide_dhemi) {
        this.ide_dhemi = ide_dhemi;
    }

    public String getIde_dhsaient() {
        return ide_dhsaient;
    }

    public void setIde_dhsaient(String ide_dhsaient) {
        this.ide_dhsaient = ide_dhsaient;
    }

    public String getIde_tpnf() {
        return ide_tpnf;
    }

    public void setIde_tpnf(String ide_tpnf) {
        this.ide_tpnf = ide_tpnf;
    }

    public String getIde_iddest() {
        return ide_iddest;
    }

    public void setIde_iddest(String ide_iddest) {
        this.ide_iddest = ide_iddest;
    }

    public String getIde_cmunfg() {
        return ide_cmunfg;
    }

    public void setIde_cmunfg(String ide_cmunfg) {
        this.ide_cmunfg = ide_cmunfg;
    }

    public String getIde_xmunfg() {
        return ide_xmunfg;
    }

    public void setIde_xmunfg(String ide_xmunfg) {
        this.ide_xmunfg = ide_xmunfg;
    }

    public String getIde_cmunfgibs() {
        return ide_cmunfgibs;
    }

    public void setIde_cmunfgibs(String ide_cmunfgibs) {
        this.ide_cmunfgibs = ide_cmunfgibs;
    }

    public String getIde_indmultajuros() {
        return ide_indmultajuros;
    }

    public void setIde_indmultajuros(String ide_indmultajuros) {
        this.ide_indmultajuros = ide_indmultajuros;
    }

    public String getIde_tpimp() {
        return ide_tpimp;
    }

    public void setIde_tpimp(String ide_tpimp) {
        this.ide_tpimp = ide_tpimp;
    }

    public String getIde_tpemis() {
        return ide_tpemis;
    }

    public void setIde_tpemis(String ide_tpemis) {
        this.ide_tpemis = ide_tpemis;
    }

    public String getIde_cdv() {
        return ide_cdv;
    }

    public void setIde_cdv(String ide_cdv) {
        this.ide_cdv = ide_cdv;
    }

    public String getIde_tpamb() {
        return ide_tpamb;
    }

    public void setIde_tpamb(String ide_tpamb) {
        this.ide_tpamb = ide_tpamb;
    }

    public String getIde_finnfe() {
        return ide_finnfe;
    }

    public void setIde_finnfe(String ide_finnfe) {
        this.ide_finnfe = ide_finnfe;
    }

    public String getIde_tpnfdebito() {
        return ide_tpnfdebito;
    }

    public String getIde_tpnfcredito() {
        return ide_tpnfcredito;
    }

    public void setIde_tpnfdebito(String ide_tpnfdebito) {
        this.ide_tpnfdebito = ide_tpnfdebito;
    }

    public void setIde_tpnfcredito(String ide_tpnfcredito) {
        this.ide_tpnfcredito = ide_tpnfcredito;
    }

    public String getIde_indfinal() {
        return ide_indfinal;
    }

    public void setIde_indfinal(String ide_indfinal) {
        this.ide_indfinal = ide_indfinal;
    }

    public String getIde_indpres() {
        return ide_indpres;
    }

    public void setIde_indpres(String ide_indpres) {
        this.ide_indpres = ide_indpres;
    }

    public String getIde_procemi() {
        return ide_procemi;
    }

    public void setIde_procemi(String ide_procemi) {
        this.ide_procemi = ide_procemi;
    }

    public String getIde_verproc() {
        return ide_verproc;
    }

    public void setIde_verproc(String ide_verproc) {
        this.ide_verproc = ide_verproc;
    }

    public String getIde_dhcont() {
        return ide_dhcont;
    }

    public void setIde_dhcont(String ide_dhcont) {
        this.ide_dhcont = ide_dhcont;
    }

    public String getIde_xjust() {
        return ide_xjust;
    }

    public void setIde_xjust(String ide_xjust) {
        this.ide_xjust = ide_xjust;
    }

    public String getEmit_cnpj() {
        return emit_cnpj;
    }

    public void setEmit_cnpj(String emit_cnpj) {
        this.emit_cnpj = emit_cnpj;
    }

    public String getEmit_cpf() {
        return emit_cpf;
    }

    public void setEmit_cpf(String emit_cpf) {
        this.emit_cpf = emit_cpf;
    }

    public String getEmit_xnome() {
        return emit_xnome;
    }

    public void setEmit_xnome(String emit_xnome) {
        this.emit_xnome = emit_xnome;
    }

    public String getEmit_xfant() {
        return emit_xfant;
    }

    public void setEmit_xfant(String emit_xfant) {
        this.emit_xfant = emit_xfant;
    }

    public String getEnderemit_xlgr() {
        return enderemit_xlgr;
    }

    public void setEnderemit_xlgr(String enderemit_xlgr) {
        this.enderemit_xlgr = enderemit_xlgr;
    }

    public String getEnderemit_nro() {
        return enderemit_nro;
    }

    public void setEnderemit_nro(String enderemit_nro) {
        this.enderemit_nro = enderemit_nro;
    }

    public String getEnderemit_xcpl() {
        return enderemit_xcpl;
    }

    public void setEnderemit_xcpl(String enderemit_xcpl) {
        this.enderemit_xcpl = enderemit_xcpl;
    }

    public String getEnderemit_xbairro() {
        return enderemit_xbairro;
    }

    public void setEnderemit_xbairro(String enderemit_xbairro) {
        this.enderemit_xbairro = enderemit_xbairro;
    }

    public String getEnderemit_cmun() {
        return enderemit_cmun;
    }

    public void setEnderemit_cmun(String enderemit_cmun) {
        this.enderemit_cmun = enderemit_cmun;
    }

    public String getEnderemit_xmun() {
        return enderemit_xmun;
    }

    public void setEnderemit_xmun(String enderemit_xmun) {
        this.enderemit_xmun = enderemit_xmun;
    }

    public String getEnderemit_uf() {
        return enderemit_uf;
    }

    public void setEnderemit_uf(String enderemit_uf) {
        this.enderemit_uf = enderemit_uf;
    }

    public String getEnderemit_cep() {
        return enderemit_cep;
    }

    public void setEnderemit_cep(String enderemit_cep) {
        this.enderemit_cep = enderemit_cep;
    }

    public String getEnderemit_cpais() {
        return enderemit_cpais;
    }

    public void setEnderemit_cpais(String enderemit_cpais) {
        this.enderemit_cpais = enderemit_cpais;
    }

    public String getEnderemit_xpais() {
        return enderemit_xpais;
    }

    public void setEnderemit_xpais(String enderemit_xpais) {
        this.enderemit_xpais = enderemit_xpais;
    }

    public String getEnderemit_fone() {
        return enderemit_fone;
    }

    public void setEnderemit_fone(String enderemit_fone) {
        this.enderemit_fone = enderemit_fone;
    }

    public String getEnderemit_ie() {
        return enderemit_ie;
    }

    public void setEnderemit_ie(String enderemit_ie) {
        this.enderemit_ie = enderemit_ie;
    }

    public String getEnderemit_iest() {
        return enderemit_iest;
    }

    public void setEnderemit_iest(String enderemit_iest) {
        this.enderemit_iest = enderemit_iest;
    }

    public String getEnderemit_im() {
        return enderemit_im;
    }

    public void setEnderemit_im(String enderemit_im) {
        this.enderemit_im = enderemit_im;
    }

    public String getEnderemit_cnae() {
        return enderemit_cnae;
    }

    public void setEnderemit_cnae(String enderemit_cnae) {
        this.enderemit_cnae = enderemit_cnae;
    }

    public String getEnderemit_crt() {
        return enderemit_crt;
    }

    public void setEnderemit_crt(String enderemit_crt) {
        this.enderemit_crt = enderemit_crt;
    }

    public String getDest_cnpj() {
        return dest_cnpj;
    }

    public void setDest_cnpj(String dest_cnpj) {
        this.dest_cnpj = dest_cnpj;
    }

    public String getDest_cpf() {
        return dest_cpf;
    }

    public void setDest_cpf(String dest_cpf) {
        this.dest_cpf = dest_cpf;
    }

    public String getDest_idestrangeiro() {
        return dest_idestrangeiro;
    }

    public void setDest_idestrangeiro(String dest_idestrangeiro) {
        this.dest_idestrangeiro = dest_idestrangeiro;
    }

    public String getDest_xnome() {
        return dest_xnome;
    }

    public void setDest_xnome(String dest_xnome) {
        this.dest_xnome = dest_xnome;
    }

    public String getEnderdest_xlgr() {
        return enderdest_xlgr;
    }

    public void setEnderdest_xlgr(String enderdest_xlgr) {
        this.enderdest_xlgr = enderdest_xlgr;
    }

    public String getEnderdest_nro() {
        return enderdest_nro;
    }

    public void setEnderdest_nro(String enderdest_nro) {
        this.enderdest_nro = enderdest_nro;
    }

    public String getEnderdest_xcpl() {
        return enderdest_xcpl;
    }

    public void setEnderdest_xcpl(String enderdest_xcpl) {
        this.enderdest_xcpl = enderdest_xcpl;
    }

    public String getEnderdest_xbairro() {
        return enderdest_xbairro;
    }

    public void setEnderdest_xbairro(String enderdest_xbairro) {
        this.enderdest_xbairro = enderdest_xbairro;
    }

    public String getEnderdest_cmun() {
        return enderdest_cmun;
    }

    public void setEnderdest_cmun(String enderdest_cmun) {
        this.enderdest_cmun = enderdest_cmun;
    }

    public String getEnderdest_xmun() {
        return enderdest_xmun;
    }

    public void setEnderdest_xmun(String enderdest_xmun) {
        this.enderdest_xmun = enderdest_xmun;
    }

    public String getEnderdest_uf() {
        return enderdest_uf;
    }

    public void setEnderdest_uf(String enderdest_uf) {
        this.enderdest_uf = enderdest_uf;
    }

    public String getEnderdest_cep() {
        return enderdest_cep;
    }

    public void setEnderdest_cep(String enderdest_cep) {
        this.enderdest_cep = enderdest_cep;
    }

    public String getEnderdest_cpais() {
        return enderdest_cpais;
    }

    public void setEnderdest_cpais(String enderdest_cpais) {
        this.enderdest_cpais = enderdest_cpais;
    }

    public String getEnderdest_xpais() {
        return enderdest_xpais;
    }

    public void setEnderdest_xpais(String enderdest_xpais) {
        this.enderdest_xpais = enderdest_xpais;
    }

    public String getEnderdest_fone() {
        return enderdest_fone;
    }

    public void setEnderdest_fone(String enderdest_fone) {
        this.enderdest_fone = enderdest_fone;
    }

    public String getEnderdest_indiedest() {
        return enderdest_indiedest;
    }

    public void setEnderdest_indiedest(String enderdest_indiedest) {
        this.enderdest_indiedest = enderdest_indiedest;
    }

    public String getEnderdest_ie() {
        return enderdest_ie;
    }

    public void setEnderdest_ie(String enderdest_ie) {
        this.enderdest_ie = enderdest_ie;
    }

    public String getEnderdest_isuf() {
        return enderdest_isuf;
    }

    public void setEnderdest_isuf(String enderdest_isuf) {
        this.enderdest_isuf = enderdest_isuf;
    }

    public String getEnderdest_im() {
        return enderdest_im;
    }

    public void setEnderdest_im(String enderdest_im) {
        this.enderdest_im = enderdest_im;
    }

    public String getEnderdest_email() {
        return enderdest_email;
    }

    public void setEnderdest_email(String enderdest_email) {
        this.enderdest_email = enderdest_email;
    }

    public String getRetirada_cnpj() {
        return retirada_cnpj;
    }

    public void setRetirada_cnpj(String retirada_cnpj) {
        this.retirada_cnpj = retirada_cnpj;
    }

    public String getRetirada_cpf() {
        return retirada_cpf;
    }

    public void setRetirada_cpf(String retirada_cpf) {
        this.retirada_cpf = retirada_cpf;
    }

    public String getRetirada_xlgr() {
        return retirada_xlgr;
    }

    public void setRetirada_xlgr(String retirada_xlgr) {
        this.retirada_xlgr = retirada_xlgr;
    }

    public String getRetirada_nro() {
        return retirada_nro;
    }

    public void setRetirada_nro(String retirada_nro) {
        this.retirada_nro = retirada_nro;
    }

    public String getRetirada_xcpl() {
        return retirada_xcpl;
    }

    public void setRetirada_xcpl(String retirada_xcpl) {
        this.retirada_xcpl = retirada_xcpl;
    }

    public String getRetirada_xbairro() {
        return retirada_xbairro;
    }

    public void setRetirada_xbairro(String retirada_xbairro) {
        this.retirada_xbairro = retirada_xbairro;
    }

    public String getRetirada_cmun() {
        return retirada_cmun;
    }

    public void setRetirada_cmun(String retirada_cmun) {
        this.retirada_cmun = retirada_cmun;
    }

    public String getRetirada_xmun() {
        return retirada_xmun;
    }

    public void setRetirada_xmun(String retirada_xmun) {
        this.retirada_xmun = retirada_xmun;
    }

    public String getRetirada_uf() {
        return retirada_uf;
    }

    public void setRetirada_uf(String retirada_uf) {
        this.retirada_uf = retirada_uf;
    }

    public String getEntrega_cnpj() {
        return entrega_cnpj;
    }

    public void setEntrega_cnpj(String entrega_cnpj) {
        this.entrega_cnpj = entrega_cnpj;
    }

    public String getEntrega_cpf() {
        return entrega_cpf;
    }

    public void setEntrega_cpf(String entrega_cpf) {
        this.entrega_cpf = entrega_cpf;
    }

    public String getEntrega_xlgr() {
        return entrega_xlgr;
    }

    public void setEntrega_xlgr(String entrega_xlgr) {
        this.entrega_xlgr = entrega_xlgr;
    }

    public String getEntrega_nro() {
        return entrega_nro;
    }

    public void setEntrega_nro(String entrega_nro) {
        this.entrega_nro = entrega_nro;
    }

    public String getEntrega_xcpl() {
        return entrega_xcpl;
    }

    public void setEntrega_xcpl(String entrega_xcpl) {
        this.entrega_xcpl = entrega_xcpl;
    }

    public String getEntrega_xbairro() {
        return entrega_xbairro;
    }

    public void setEntrega_xbairro(String entrega_xbairro) {
        this.entrega_xbairro = entrega_xbairro;
    }

    public String getEntrega_cmun() {
        return entrega_cmun;
    }

    public void setEntrega_cmun(String entrega_cmun) {
        this.entrega_cmun = entrega_cmun;
    }

    public String getEntrega_xmun() {
        return entrega_xmun;
    }

    public void setEntrega_xmun(String entrega_xmun) {
        this.entrega_xmun = entrega_xmun;
    }

    public String getEntrega_uf() {
        return entrega_uf;
    }

    public void setEntrega_uf(String entrega_uf) {
        this.entrega_uf = entrega_uf;
    }

    public String getRetenvinfe_versao() {
        return retenvinfe_versao;
    }

    public void setRetenvinfe_versao(String retenvinfe_versao) {
        this.retenvinfe_versao = retenvinfe_versao;
    }

    public String getRetenvinfe_cstat() {
        return retenvinfe_cstat;
    }

    public void setRetenvinfe_cstat(String retenvinfe_cstat) {
        this.retenvinfe_cstat = retenvinfe_cstat;
    }

    public String getRetenvinfe_dhrecbto() {
        return retenvinfe_dhrecbto;
    }

    public void setRetenvinfe_dhrecbto(String retenvinfe_dhrecbto) {
        this.retenvinfe_dhrecbto = retenvinfe_dhrecbto;
    }

    public String getRetenvinfe_tpamb() {
        return retenvinfe_tpamb;
    }

    public void setRetenvinfe_tpamb(String retenvinfe_tpamb) {
        this.retenvinfe_tpamb = retenvinfe_tpamb;
    }

    public String getRetenvinfe_veraplic() {
        return retenvinfe_veraplic;
    }

    public void setRetenvinfe_veraplic(String retenvinfe_veraplic) {
        this.retenvinfe_veraplic = retenvinfe_veraplic;
    }

    public String getRetenvinfe_xmsg() {
        return retenvinfe_xmsg;
    }

    public void setRetenvinfe_xmsg(String retenvinfe_xmsg) {
        this.retenvinfe_xmsg = retenvinfe_xmsg;
    }

    public String getRetenvinfe_cuf() {
        return retenvinfe_cuf;
    }

    public void setRetenvinfe_cuf(String retenvinfe_cuf) {
        this.retenvinfe_cuf = retenvinfe_cuf;
    }

    public String getRetenvinfe_tmed() {
        return retenvinfe_tmed;
    }

    public void setRetenvinfe_tmed(String retenvinfe_tmed) {
        this.retenvinfe_tmed = retenvinfe_tmed;
    }

    public String getRetenvinfe_nrec() {
        return retenvinfe_nrec;
    }

    public void setRetenvinfe_nrec(String retenvinfe_nrec) {
        this.retenvinfe_nrec = retenvinfe_nrec;
    }

    public String getRetconsrecinfe_versao() {
        return retconsrecinfe_versao;
    }

    public void setRetconsrecinfe_versao(String retconsrecinfe_versao) {
        this.retconsrecinfe_versao = retconsrecinfe_versao;
    }

    public String getRetconsrecinfe_cstat() {
        return retconsrecinfe_cstat;
    }

    public void setRetconsrecinfe_cstat(String retconsrecinfe_cstat) {
        this.retconsrecinfe_cstat = retconsrecinfe_cstat;
    }

    public String getRetconsrecinfe_nrec() {
        return retconsrecinfe_nrec;
    }

    public void setRetconsrecinfe_nrec(String retconsrecinfe_nrec) {
        this.retconsrecinfe_nrec = retconsrecinfe_nrec;
    }

    public String getRetconsrecinfe_dhrecbto() {
        return retconsrecinfe_dhrecbto;
    }

    public void setRetconsrecinfe_dhrecbto(String retconsrecinfe_dhrecbto) {
        this.retconsrecinfe_dhrecbto = retconsrecinfe_dhrecbto;
    }

    public String getRetconsrecinfe_tpamb() {
        return retconsrecinfe_tpamb;
    }

    public void setRetconsrecinfe_tpamb(String retconsrecinfe_tpamb) {
        this.retconsrecinfe_tpamb = retconsrecinfe_tpamb;
    }

    public String getRetconsrecinfe_veraplic() {
        return retconsrecinfe_veraplic;
    }

    public void setRetconsrecinfe_veraplic(String retconsrecinfe_veraplic) {
        this.retconsrecinfe_veraplic = retconsrecinfe_veraplic;
    }

    public String getRetconsrecinfe_xmsg() {
        return retconsrecinfe_xmsg;
    }

    public void setRetconsrecinfe_xmsg(String retconsrecinfe_xmsg) {
        this.retconsrecinfe_xmsg = retconsrecinfe_xmsg;
    }

    public String getRetconsrecinfe_cmsg() {
        return retconsrecinfe_cmsg;
    }

    public void setRetconsrecinfe_cmsg(String retconsrecinfe_cmsg) {
        this.retconsrecinfe_cmsg = retconsrecinfe_cmsg;
    }

    public String getRetconsrecinfe_xmotivo() {
        return retconsrecinfe_xmotivo;
    }

    public void setRetconsrecinfe_xmotivo(String retconsrecinfe_xmotivo) {
        this.retconsrecinfe_xmotivo = retconsrecinfe_xmotivo;
    }

    public String getRetconsrecinfe_cuf() {
        return retconsrecinfe_cuf;
    }

    public void setRetconsrecinfe_cuf(String retconsrecinfe_cuf) {
        this.retconsrecinfe_cuf = retconsrecinfe_cuf;
    }

    public String getRetconsrecinfe_protnfe_chnfe() {
        return retconsrecinfe_protnfe_chnfe;
    }

    public void setRetconsrecinfe_protnfe_chnfe(String retconsrecinfe_protnfe_chnfe) {
        this.retconsrecinfe_protnfe_chnfe = retconsrecinfe_protnfe_chnfe;
    }

    public String getRetconsrecinfe_protnfe_versao() {
        return retconsrecinfe_protnfe_versao;
    }

    public void setRetconsrecinfe_protnfe_versao(String retconsrecinfe_protnfe_versao) {
        this.retconsrecinfe_protnfe_versao = retconsrecinfe_protnfe_versao;
    }

    public String getRetconsrecinfe_protnfe_tpamb() {
        return retconsrecinfe_protnfe_tpamb;
    }

    public void setRetconsrecinfe_protnfe_tpamb(String retconsrecinfe_protnfe_tpamb) {
        this.retconsrecinfe_protnfe_tpamb = retconsrecinfe_protnfe_tpamb;
    }

    public String getRetconsrecinfe_protnfe_veraplic() {
        return retconsrecinfe_protnfe_veraplic;
    }

    public void setRetconsrecinfe_protnfe_veraplic(String retconsrecinfe_protnfe_veraplic) {
        this.retconsrecinfe_protnfe_veraplic = retconsrecinfe_protnfe_veraplic;
    }

    public String getRetconsrecinfe_protnfe_cstat() {
        return retconsrecinfe_protnfe_cstat;
    }

    public void setRetconsrecinfe_protnfe_cstat(String retconsrecinfe_protnfe_cstat) {
        this.retconsrecinfe_protnfe_cstat = retconsrecinfe_protnfe_cstat;
    }

    public String getRetconsrecinfe_protnfe_xmotivo() {
        return retconsrecinfe_protnfe_xmotivo;
    }

    public void setRetconsrecinfe_protnfe_xmotivo(String retconsrecinfe_protnfe_xmotivo) {
        this.retconsrecinfe_protnfe_xmotivo = retconsrecinfe_protnfe_xmotivo;
    }

    public String getRetconsrecinfe_protnfe_dhrecbto() {
        return retconsrecinfe_protnfe_dhrecbto;
    }

    public void setRetconsrecinfe_protnfe_dhrecbto(String retconsrecinfe_protnfe_dhrecbto) {
        this.retconsrecinfe_protnfe_dhrecbto = retconsrecinfe_protnfe_dhrecbto;
    }

    public String getRetconsrecinfe_protnfe_xmsg() {
        return retconsrecinfe_protnfe_xmsg;
    }

    public void setRetconsrecinfe_protnfe_xmsg(String retconsrecinfe_protnfe_xmsg) {
        this.retconsrecinfe_protnfe_xmsg = retconsrecinfe_protnfe_xmsg;
    }

    public String getProtnfe_versao() {
        return protnfe_versao;
    }

    public void setProtnfe_versao(String protnfe_versao) {
        this.protnfe_versao = protnfe_versao;
    }

    public String getInfprot_tpamb() {
        return infprot_tpamb;
    }

    public void setInfprot_tpamb(String infprot_tpamb) {
        this.infprot_tpamb = infprot_tpamb;
    }

    public String getInfprot_veraplic() {
        return infprot_veraplic;
    }

    public void setInfprot_veraplic(String infprot_veraplic) {
        this.infprot_veraplic = infprot_veraplic;
    }

    public String getInfprot_chnfe() {
        return infprot_chnfe;
    }

    public void setInfprot_chnfe(String infprot_chnfe) {
        this.infprot_chnfe = infprot_chnfe;
    }

    public String getInfprot_dhrecbto() {
        return infprot_dhrecbto;
    }

    public void setInfprot_dhrecbto(String infprot_dhrecbto) {
        this.infprot_dhrecbto = infprot_dhrecbto;
    }

    public String getInfprot_nprot() {
        return infprot_nprot;
    }

    public void setInfprot_nprot(String infprot_nprot) {
        this.infprot_nprot = infprot_nprot;
    }

    public String getInfprot_digval() {
        return infprot_digval;
    }

    public void setInfprot_digval(String infprot_digval) {
        this.infprot_digval = infprot_digval;
    }

    public String getInfprot_cstat() {
        return infprot_cstat;
    }

    public void setInfprot_cstat(String infprot_cstat) {
        this.infprot_cstat = infprot_cstat;
    }

    public String getInfprot_xmotivo() {
        return infprot_xmotivo;
    }

    public void setInfprot_xmotivo(String infprot_xmotivo) {
        this.infprot_xmotivo = infprot_xmotivo;
    }

    public String getIcmstot_vbc() {
        return icmstot_vbc;
    }

    public void setIcmstot_vbc(String icmstot_vbc) {
        this.icmstot_vbc = icmstot_vbc;
    }

    public String getIcmstot_vicms() {
        return icmstot_vicms;
    }

    public void setIcmstot_vicms(String icmstot_vicms) {
        this.icmstot_vicms = icmstot_vicms;
    }

    public String getIcmstot_vicmsdeson() {
        return icmstot_vicmsdeson;
    }

    public void setIcmstot_vicmsdeson(String icmstot_vicmsdeson) {
        this.icmstot_vicmsdeson = icmstot_vicmsdeson;
    }

    public String getIcmstot_vbcst() {
        return icmstot_vbcst;
    }

    public void setIcmstot_vbcst(String icmstot_vbcst) {
        this.icmstot_vbcst = icmstot_vbcst;
    }

    public String getIcmstot_vst() {
        return icmstot_vst;
    }

    public void setIcmstot_vst(String icmstot_vst) {
        this.icmstot_vst = icmstot_vst;
    }

    public String getIcmstot_vprod() {
        return icmstot_vprod;
    }

    public void setIcmstot_vprod(String icmstot_vprod) {
        this.icmstot_vprod = icmstot_vprod;
    }

    public String getIcmstot_vfrete() {
        return icmstot_vfrete;
    }

    public void setIcmstot_vfrete(String icmstot_vfrete) {
        this.icmstot_vfrete = icmstot_vfrete;
    }

    public String getIcmstot_vseg() {
        return icmstot_vseg;
    }

    public void setIcmstot_vseg(String icmstot_vseg) {
        this.icmstot_vseg = icmstot_vseg;
    }

    public String getIcmstot_vdesc() {
        return icmstot_vdesc;
    }

    public void setIcmstot_vdesc(String icmstot_vdesc) {
        this.icmstot_vdesc = icmstot_vdesc;
    }

    public String getIcmstot_vii() {
        return icmstot_vii;
    }

    public void setIcmstot_vii(String icmstot_vii) {
        this.icmstot_vii = icmstot_vii;
    }

    public String getIcmstot_vipi() {
        return icmstot_vipi;
    }

    public void setIcmstot_vipi(String icmstot_vipi) {
        this.icmstot_vipi = icmstot_vipi;
    }

    public String getIcmstot_vpis() {
        return icmstot_vpis;
    }

    public void setIcmstot_vpis(String icmstot_vpis) {
        this.icmstot_vpis = icmstot_vpis;
    }

    public String getIcmstot_vcofins() {
        return icmstot_vcofins;
    }

    public void setIcmstot_vcofins(String icmstot_vcofins) {
        this.icmstot_vcofins = icmstot_vcofins;
    }

    public String getIcmstot_voutro() {
        return icmstot_voutro;
    }

    public void setIcmstot_voutro(String icmstot_voutro) {
        this.icmstot_voutro = icmstot_voutro;
    }

    public String getIcmstot_vnf() {
        return icmstot_vnf;
    }

    public void setIcmstot_vnf(String icmstot_vnf) {
        this.icmstot_vnf = icmstot_vnf;
    }

    public String getIcmstot_vtottrib() {
        return icmstot_vtottrib;
    }

    public void setIcmstot_vtottrib(String icmstot_vtottrib) {
        this.icmstot_vtottrib = icmstot_vtottrib;
    }

    public String getTransp_modfrete() {
        return transp_modfrete;
    }

    public void setTransp_modfrete(String transp_modfrete) {
        this.transp_modfrete = transp_modfrete;
    }

    public String getTransporta_cnpj() {
        return transporta_cnpj;
    }

    public void setTransporta_cnpj(String transporta_cnpj) {
        this.transporta_cnpj = transporta_cnpj;
    }

    public String getTransporta_cpf() {
        return transporta_cpf;
    }

    public void setTransporta_cpf(String transporta_cpf) {
        this.transporta_cpf = transporta_cpf;
    }

    public String getTransporta_xnome() {
        return transporta_xnome;
    }

    public void setTransporta_xnome(String transporta_xnome) {
        this.transporta_xnome = transporta_xnome;
    }

    public String getTransporta_ie() {
        return transporta_ie;
    }

    public void setTransporta_ie(String transporta_ie) {
        this.transporta_ie = transporta_ie;
    }

    public String getTransporta_xender() {
        return transporta_xender;
    }

    public void setTransporta_xender(String transporta_xender) {
        this.transporta_xender = transporta_xender;
    }

    public String getTransporta_xmun() {
        return transporta_xmun;
    }

    public void setTransporta_xmun(String transporta_xmun) {
        this.transporta_xmun = transporta_xmun;
    }

    public String getTransporta_uf() {
        return transporta_uf;
    }

    public void setTransporta_uf(String transporta_uf) {
        this.transporta_uf = transporta_uf;
    }

    public String getVol_qvol() {
        return vol_qvol;
    }

    public void setVol_qvol(String vol_qvol) {
        this.vol_qvol = vol_qvol;
    }

    public String getVol_esp() {
        return vol_esp;
    }

    public void setVol_esp(String vol_esp) {
        this.vol_esp = vol_esp;
    }

    public String getVol_marca() {
        return vol_marca;
    }

    public void setVol_marca(String vol_marca) {
        this.vol_marca = vol_marca;
    }

    public String getVol_nvol() {
        return vol_nvol;
    }

    public void setVol_nvol(String vol_nvol) {
        this.vol_nvol = vol_nvol;
    }

    public String getVol_pesol() {
        return vol_pesol;
    }

    public void setVol_pesol(String vol_pesol) {
        this.vol_pesol = vol_pesol;
    }

    public String getVol_pesob() {
        return vol_pesob;
    }

    public void setVol_pesob(String vol_pesob) {
        this.vol_pesob = vol_pesob;
    }

    public String getFat_nfat() {
        return fat_nfat;
    }

    public void setFat_nfat(String fat_nfat) {
        this.fat_nfat = fat_nfat;
    }

    public String getFat_vorig() {
        return fat_vorig;
    }

    public void setFat_vorig(String fat_vorig) {
        this.fat_vorig = fat_vorig;
    }

    public String getFat_vdesc() {
        return fat_vdesc;
    }

    public void setFat_vdesc(String fat_vdesc) {
        this.fat_vdesc = fat_vdesc;
    }

    public String getFat_vliq() {
        return fat_vliq;
    }

    public void setFat_vliq(String fat_vliq) {
        this.fat_vliq = fat_vliq;
    }

    public String getInfadic_infadfisco() {
        return infadic_infadfisco;
    }

    public void setInfadic_infadfisco(String infadic_infadfisco) {
        this.infadic_infadfisco = infadic_infadfisco;
    }

    public String getInfadic_infcpl() {
        return infadic_infcpl;
    }

    public void setInfadic_infcpl(String infadic_infcpl) {
        this.infadic_infcpl = infadic_infcpl;
    }

    public String getSignature_cnpj() {
        return signature_cnpj;
    }

    public void setSignature_cnpj(String signature_cnpj) {
        this.signature_cnpj = signature_cnpj;
    }

    public String getSignature_emissor() {
        return signature_emissor;
    }

    public void setSignature_emissor(String signature_emissor) {
        this.signature_emissor = signature_emissor;
    }

    public String getSignature_assunto() {
        return signature_assunto;
    }

    public void setSignature_assunto(String signature_assunto) {
        this.signature_assunto = signature_assunto;
    }

    public String getSignature_datainicial() {
        return signature_datainicial;
    }

    public void setSignature_datainicial(String signature_datainicial) {
        this.signature_datainicial = signature_datainicial;
    }

    public String getSignature_datafinal() {
        return signature_datafinal;
    }

    public void setSignature_datafinal(String signature_datafinal) {
        this.signature_datafinal = signature_datafinal;
    }

    public String getRetenvevento_versao() {
        return retenvevento_versao;
    }

    public void setRetenvevento_versao(String retenvevento_versao) {
        this.retenvevento_versao = retenvevento_versao;
    }

    public String getRetenvevento_idlote() {
        return retenvevento_idlote;
    }

    public void setRetenvevento_idlote(String retenvevento_idlote) {
        this.retenvevento_idlote = retenvevento_idlote;
    }

    public String getRetenvevento_tpamb() {
        return retenvevento_tpamb;
    }

    public void setRetenvevento_tpamb(String retenvevento_tpamb) {
        this.retenvevento_tpamb = retenvevento_tpamb;
    }

    public String getRetenvevento_veraplic() {
        return retenvevento_veraplic;
    }

    public void setRetenvevento_veraplic(String retenvevento_veraplic) {
        this.retenvevento_veraplic = retenvevento_veraplic;
    }

    public String getRetenvevento_corgao() {
        return retenvevento_corgao;
    }

    public void setRetenvevento_corgao(String retenvevento_corgao) {
        this.retenvevento_corgao = retenvevento_corgao;
    }

    public String getRetenvevento_cstat() {
        return retenvevento_cstat;
    }

    public void setRetenvevento_cstat(String retenvevento_cstat) {
        this.retenvevento_cstat = retenvevento_cstat;
    }

    public String getRetenvevento_xmotivo() {
        return retenvevento_xmotivo;
    }

    public void setRetenvevento_xmotivo(String retenvevento_xmotivo) {
        this.retenvevento_xmotivo = retenvevento_xmotivo;
    }

    public String getRetenvevento_retevento_versao() {
        return retenvevento_retevento_versao;
    }

    public void setRetenvevento_retevento_versao(String retenvevento_retevento_versao) {
        this.retenvevento_retevento_versao = retenvevento_retevento_versao;
    }

    public String getRetenvevento_retevento_infevento_id() {
        return retenvevento_retevento_infevento_id;
    }

    public void setRetenvevento_retevento_infevento_id(String retenvevento_retevento_infevento_id) {
        this.retenvevento_retevento_infevento_id = retenvevento_retevento_infevento_id;
    }

    public String getRetenvevento_retevento_infevento_tpamb() {
        return retenvevento_retevento_infevento_tpamb;
    }

    public void setRetenvevento_retevento_infevento_tpamb(String retenvevento_retevento_infevento_tpamb) {
        this.retenvevento_retevento_infevento_tpamb = retenvevento_retevento_infevento_tpamb;
    }

    public String getRetenvevento_retevento_infevento_veraplic() {
        return retenvevento_retevento_infevento_veraplic;
    }

    public void setRetenvevento_retevento_infevento_veraplic(String retenvevento_retevento_infevento_veraplic) {
        this.retenvevento_retevento_infevento_veraplic = retenvevento_retevento_infevento_veraplic;
    }

    public String getRetenvevento_retevento_infevento_corgao() {
        return retenvevento_retevento_infevento_corgao;
    }

    public void setRetenvevento_retevento_infevento_corgao(String retenvevento_retevento_infevento_corgao) {
        this.retenvevento_retevento_infevento_corgao = retenvevento_retevento_infevento_corgao;
    }

    public String getRetenvevento_retevento_infevento_cstat() {
        return retenvevento_retevento_infevento_cstat;
    }

    public void setRetenvevento_retevento_infevento_cstat(String retenvevento_retevento_infevento_cstat) {
        this.retenvevento_retevento_infevento_cstat = retenvevento_retevento_infevento_cstat;
    }

    public String getRetenvevento_retevento_infevento_xmotivo() {
        return retenvevento_retevento_infevento_xmotivo;
    }

    public void setRetenvevento_retevento_infevento_xmotivo(String retenvevento_retevento_infevento_xmotivo) {
        this.retenvevento_retevento_infevento_xmotivo = retenvevento_retevento_infevento_xmotivo;
    }

    public String getRetenvevento_retevento_infevento_chnfe() {
        return retenvevento_retevento_infevento_chnfe;
    }

    public void setRetenvevento_retevento_infevento_chnfe(String retenvevento_retevento_infevento_chnfe) {
        this.retenvevento_retevento_infevento_chnfe = retenvevento_retevento_infevento_chnfe;
    }

    public String getRetenvevento_retevento_infevento_tpevento() {
        return retenvevento_retevento_infevento_tpevento;
    }

    public void setRetenvevento_retevento_infevento_tpevento(String retenvevento_retevento_infevento_tpevento) {
        this.retenvevento_retevento_infevento_tpevento = retenvevento_retevento_infevento_tpevento;
    }

    public String getRetenvevento_retevento_infevento_xevento() {
        return retenvevento_retevento_infevento_xevento;
    }

    public void setRetenvevento_retevento_infevento_xevento(String retenvevento_retevento_infevento_xevento) {
        this.retenvevento_retevento_infevento_xevento = retenvevento_retevento_infevento_xevento;
    }

    public String getRetenvevento_retevento_infevento_nseqevento() {
        return retenvevento_retevento_infevento_nseqevento;
    }

    public void setRetenvevento_retevento_infevento_nseqevento(String retenvevento_retevento_infevento_nseqevento) {
        this.retenvevento_retevento_infevento_nseqevento = retenvevento_retevento_infevento_nseqevento;
    }

    public String getRetenvevento_retevento_infevento_cnpjdest() {
        return retenvevento_retevento_infevento_cnpjdest;
    }

    public void setRetenvevento_retevento_infevento_cnpjdest(String retenvevento_retevento_infevento_cnpjdest) {
        this.retenvevento_retevento_infevento_cnpjdest = retenvevento_retevento_infevento_cnpjdest;
    }

    public String getRetenvevento_retevento_infevento_cpfdest() {
        return retenvevento_retevento_infevento_cpfdest;
    }

    public void setRetenvevento_retevento_infevento_cpfdest(String retenvevento_retevento_infevento_cpfdest) {
        this.retenvevento_retevento_infevento_cpfdest = retenvevento_retevento_infevento_cpfdest;
    }

    public String getRetenvevento_retevento_infevento_emaildest() {
        return retenvevento_retevento_infevento_emaildest;
    }

    public void setRetenvevento_retevento_infevento_emaildest(String retenvevento_retevento_infevento_emaildest) {
        this.retenvevento_retevento_infevento_emaildest = retenvevento_retevento_infevento_emaildest;
    }

    public String getRetenvevento_retevento_infevento_dhregevento() {
        return retenvevento_retevento_infevento_dhregevento;
    }

    public void setRetenvevento_retevento_infevento_dhregevento(String retenvevento_retevento_infevento_dhregevento) {
        this.retenvevento_retevento_infevento_dhregevento = retenvevento_retevento_infevento_dhregevento;
    }

    public String getRetenvevento_retevento_infevento_nprot() {
        return retenvevento_retevento_infevento_nprot;
    }

    public void setRetenvevento_retevento_infevento_nprot(String retenvevento_retevento_infevento_nprot) {
        this.retenvevento_retevento_infevento_nprot = retenvevento_retevento_infevento_nprot;
    }

    public String getXml_assinado() {
        return xml_assinado;
    }

    public void setXml_assinado(String xml_assinado) {
        this.xml_assinado = xml_assinado;
    }

    public String getXml_autorizado() {
        return xml_autorizado;
    }

    public void setXml_autorizado(String xml_autorizado) {
        this.xml_autorizado = xml_autorizado;
    }

    public String getXml_envevento_assinado() {
        return xml_envevento_assinado;
    }

    public void setXml_envevento_assinado(String xml_envevento_assinado) {
        this.xml_envevento_assinado = xml_envevento_assinado;
    }

    public String getXml_envevento_autorizado() {
        return xml_envevento_autorizado;
    }

    public void setXml_envevento_autorizado(String xml_envevento_autorizado) {
        this.xml_envevento_autorizado = xml_envevento_autorizado;
    }

    public String getTot_is_vbcsel() {
        return tot_is_vbcsel;
    }

    public String getTot_is_vimpsel() {
        return tot_is_vimpsel;
    }

    public String getTot_is_vcrespres() {
        return tot_is_vcrespres;
    }

    public String getTot_ibs_uf_vcrespres() {
        return tot_ibs_uf_vcrespres;
    }

    public String getTot_ibs_uf_vcredprescondsus() {
        return tot_ibs_uf_vcredprescondsus;
    }

    public String getTot_ibs_uf_vdif() {
        return tot_ibs_uf_vdif;
    }

    public String getTot_ibs_uf_vdevtrib() {
        return tot_ibs_uf_vdevtrib;
    }

    public String getTot_ibs_uf_vdeson() {
        return tot_ibs_uf_vdeson;
    }

    public String getTot_ibs_uf_vibs() {
        return tot_ibs_uf_vibs;
    }

    public String getTot_ibs_mun_vcrespres() {
        return tot_ibs_mun_vcrespres;
    }

    public String getTot_ibs_mun_vcredprescondsus() {
        return tot_ibs_mun_vcredprescondsus;
    }

    public String getTot_ibs_mun_vdif() {
        return tot_ibs_mun_vdif;
    }

    public String getTot_ibs_mun_vdevtrib() {
        return tot_ibs_mun_vdevtrib;
    }

    public String getTot_ibs_mun_vdeson() {
        return tot_ibs_mun_vdeson;
    }

    public String getTot_ibs_mun_vibs() {
        return tot_ibs_mun_vibs;
    }

    public String getTot_cbs_vcrespres() {
        return tot_cbs_vcrespres;
    }

    public String getTot_cbs_vcredprescondsus() {
        return tot_cbs_vcredprescondsus;
    }

    public String getTot_cbs_vdif() {
        return tot_cbs_vdif;
    }

    public String getTot_cbs_vdevtrib() {
        return tot_cbs_vdevtrib;
    }

    public String getTot_cbs_vdeson() {
        return tot_cbs_vdeson;
    }

    public String getTot_cbs_vcbs() {
        return tot_cbs_vcbs;
    }

    public String getTot_vbcibscbs() {
        return tot_vbcibscbs;
    }

    public String getTot_vtotnf() {
        return tot_vtotnf;
    }

    public void setTot_is_vbcsel(String tot_is_vbcsel) {
        this.tot_is_vbcsel = tot_is_vbcsel;
    }

    public void setTot_is_vimpsel(String tot_is_vimpsel) {
        this.tot_is_vimpsel = tot_is_vimpsel;
    }

    public void setTot_is_vcrespres(String tot_is_vcrespres) {
        this.tot_is_vcrespres = tot_is_vcrespres;
    }

    public void setTot_ibs_uf_vcrespres(String tot_ibs_uf_vcrespres) {
        this.tot_ibs_uf_vcrespres = tot_ibs_uf_vcrespres;
    }

    public void setTot_ibs_uf_vcredprescondsus(String tot_ibs_uf_vcredprescondsus) {
        this.tot_ibs_uf_vcredprescondsus = tot_ibs_uf_vcredprescondsus;
    }

    public void setTot_ibs_uf_vdif(String tot_ibs_uf_vdif) {
        this.tot_ibs_uf_vdif = tot_ibs_uf_vdif;
    }

    public void setTot_ibs_uf_vdevtrib(String tot_ibs_uf_vdevtrib) {
        this.tot_ibs_uf_vdevtrib = tot_ibs_uf_vdevtrib;
    }

    public void setTot_ibs_uf_vdeson(String tot_ibs_uf_vdeson) {
        this.tot_ibs_uf_vdeson = tot_ibs_uf_vdeson;
    }

    public void setTot_ibs_uf_vibs(String tot_ibs_uf_vibsuf) {
        this.tot_ibs_uf_vibs = tot_ibs_uf_vibsuf;
    }

    public void setTot_ibs_mun_vcrespres(String tot_ibs_mun_vcrespres) {
        this.tot_ibs_mun_vcrespres = tot_ibs_mun_vcrespres;
    }

    public void setTot_ibs_mun_vcredprescondsus(String tot_ibs_mun_vcredprescondsus) {
        this.tot_ibs_mun_vcredprescondsus = tot_ibs_mun_vcredprescondsus;
    }

    public void setTot_ibs_mun_vdif(String tot_ibs_mun_vdif) {
        this.tot_ibs_mun_vdif = tot_ibs_mun_vdif;
    }

    public void setTot_ibs_mun_vdevtrib(String tot_ibs_mun_vdevtrib) {
        this.tot_ibs_mun_vdevtrib = tot_ibs_mun_vdevtrib;
    }

    public void setTot_ibs_mun_vdeson(String tot_ibs_mun_vdeson) {
        this.tot_ibs_mun_vdeson = tot_ibs_mun_vdeson;
    }

    public void setTot_ibs_mun_vibs(String tot_ibs_mun_vibsuf) {
        this.tot_ibs_mun_vibs = tot_ibs_mun_vibsuf;
    }

    public String getTot_ibs_vcrespres() {
        return tot_ibs_vcrespres;
    }

    public String getTot_ibs_vcredprescondsus() {
        return tot_ibs_vcredprescondsus;
    }

    public void setTot_ibs_vcrespres(String tot_ibs_vcrespres) {
        this.tot_ibs_vcrespres = tot_ibs_vcrespres;
    }

    public void setTot_ibs_vcredprescondsus(String tot_ibs_vcredprescondsus) {
        this.tot_ibs_vcredprescondsus = tot_ibs_vcredprescondsus;
    }

    public void setTot_cbs_vcrespres(String tot_cbs_vcrespres) {
        this.tot_cbs_vcrespres = tot_cbs_vcrespres;
    }

    public void setTot_cbs_vcredprescondsus(String tot_cbs_vcredprescondsus) {
        this.tot_cbs_vcredprescondsus = tot_cbs_vcredprescondsus;
    }

    public void setTot_cbs_vdif(String tot_cbs_vdif) {
        this.tot_cbs_vdif = tot_cbs_vdif;
    }

    public void setTot_cbs_vdevtrib(String tot_cbs_vdevtrib) {
        this.tot_cbs_vdevtrib = tot_cbs_vdevtrib;
    }

    public void setTot_cbs_vdeson(String tot_cbs_vdeson) {
        this.tot_cbs_vdeson = tot_cbs_vdeson;
    }

    public void setTot_cbs_vcbs(String tot_cbs_vcbs) {
        this.tot_cbs_vcbs = tot_cbs_vcbs;
    }

    public void setTot_vbcibscbs(String tot_vbcibscbs) {
        this.tot_vbcibscbs = tot_vbcibscbs;
    }

    public void setTot_vtotnf(String tot_vtotnf) {
        this.tot_vtotnf = tot_vtotnf;
    }

}
