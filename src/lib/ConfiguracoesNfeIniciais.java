package lib;

import app.models.EMITENTE;
import app.daos.EMITENTEdaoJpa;
import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import static br.com.swconsultoria.nfe.dom.enuns.EstadosEnum.SP;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.util.ConfiguracoesUtil;
import com.frontend.config.env;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class ConfiguracoesNfeIniciais {

    public ConfiguracoesNfe iniciaConfiguracoes() throws NfeException, CertificadoException, FileNotFoundException, SQLException, UnsupportedEncodingException {
        env Env = new env();
        EMITENTE eMITENTE = new EMITENTE();
        eMITENTE = EMITENTEdaoJpa.findCodigo(Env.getEMITENTE_DEFAULT());
        env env = new env();

        Certificado certificado = CertificadoService.certificadoPfxBytes(eMITENTE.getEMP_CERTIFICADO(), eMITENTE.getEMP_SENHACERTIFICADO());
        ConfiguracoesNfe configNfe = ConfiguracoesUtil.iniciaConfiguracoes(ConfiguracoesNfe.criarConfiguracoes(SP, (System.getProperty("tpAmb").equals("2") ? AmbienteEnum.HOMOLOGACAO : AmbienteEnum.PRODUCAO), certificado, env.getpath_schemas()));
        configNfe.setContigenciaSVC(System.getProperty("contigenciaSVC").equals("TRUE") ? true : false);        
        //System.out.println("Alias Certificado :" + certificado.getNome());
        //System.out.println("Dias Restantes Certificado :" + certificado.getDiasRestantes());
        //System.out.println("Validade Certificado :" + certificado.getVencimento());
        //Certificado certificado = CertificadoService.certificadoPfx(env.getpath_certificado() + env.getname_certificatoemitente(), env.getpassword_certificatoemitente());
        return configNfe;
    }

}
