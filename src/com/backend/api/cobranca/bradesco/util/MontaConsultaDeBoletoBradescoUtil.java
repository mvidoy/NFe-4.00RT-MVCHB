package com.backend.api.cobranca.bradesco.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.AGENCIA;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.CONTA;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.EMPRESA_CNPJ;
import com.backend.dtos.CANFEDUPLICdto;
import com.backend.api.cobranca.bradesco.dtos.BodyJsonCONSULTADEBOLETOBRADESCOdto;
import java.io.IOException;

public class MontaConsultaDeBoletoBradescoUtil {

    static String Agencia = AGENCIA.get();
    static String Conta = CONTA.get();

    public static String montaConsultaDeBoletoBradesco(CANFEDUPLICdto dto) throws IOException {

        String inscricao = EMPRESA_CNPJ.get().replaceAll("[^0-9]", "");

        if (inscricao.length() < 14) {
            throw new IllegalArgumentException(
                    "Inscrição inválida para montagem do JSON: " + inscricao
            );
        }

        long raiz = Long.parseLong(inscricao.substring(0, 9));     // ? 9 dígitos
        int filial = Integer.parseInt(inscricao.substring(9, 13)); // ? 4 dígitos
        int controle = Integer.parseInt(inscricao.substring(13));  // ? 2 dígitos

        String nonu = dto.getPAR_NONU();
        if (nonu != null && nonu.length() > 11) {
            nonu = nonu.substring(0, 11);
        }

        long negociacao = Long.parseLong(Agencia + Conta);
        long nossoNumero = Long.parseLong(nonu.replaceAll("[^0-9]", ""));

        ObjectMapper mapper = new ObjectMapper();

        BodyJsonCONSULTADEBOLETOBRADESCOdto body = new BodyJsonCONSULTADEBOLETOBRADESCOdto();
        BodyJsonCONSULTADEBOLETOBRADESCOdto.CpfCnpj cpfCnpj
                = new BodyJsonCONSULTADEBOLETOBRADESCOdto.CpfCnpj();

        // ? Ambiente HOMOLOGAÇÃO (tpAmb = 2) usa dados FIXOS
        if (System.getProperty("tpAmb").equals("2")) {

            cpfCnpj.setCpfCnpj(31759488L);
            cpfCnpj.setFilial(0);
            cpfCnpj.setControle(55);

            body.setCpfCnpj(cpfCnpj);
            body.setProduto(9);
            body.setNegociacao(2856022652L);
            body.setNossoNumero(50130000040L);
            body.setSequencia(0);
            body.setStatus(0);

        } // ? Ambiente PRODUÇÃO usa dados REAIS do cliente/título
        else {

            cpfCnpj.setCpfCnpj(raiz);
            cpfCnpj.setFilial(filial);
            cpfCnpj.setControle(controle);

            body.setCpfCnpj(cpfCnpj);
            body.setProduto(9);
            body.setNegociacao(negociacao);
            body.setNossoNumero(nossoNumero);
            body.setSequencia(0);
            body.setStatus(0);
        }

        String json = mapper.writeValueAsString(body);
        System.out.println(json);

        return json;
    }

}
