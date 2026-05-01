package com.backend.api.cobranca.bradesco.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.AGENCIA;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.CONTA;
import static com.backend.api.cobranca.bradesco.dom.enuns.SacadorBradescoEnum.EMPRESA_CNPJ;
import com.backend.api.cobranca.bradesco.dtos.BodyJsonBAIXADEBOLETOBRADESCOdto;
import com.backend.dtos.CANFEDUPLICdto;

public class MontaBaixaDeBoletoBradescoUtil {

    static String Agencia = AGENCIA.get();
    static String Conta = CONTA.get();

    public static String montaBaixaDeBoletoBradesco(CANFEDUPLICdto dto) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        BodyJsonBAIXADEBOLETOBRADESCOdto body = new BodyJsonBAIXADEBOLETOBRADESCOdto();
        BodyJsonBAIXADEBOLETOBRADESCOdto.CpfCnpj cpfCnpj = new BodyJsonBAIXADEBOLETOBRADESCOdto.CpfCnpj();

        if (System.getProperty("tpAmb").equals("2")) {
            // ? Ambiente HOMOLOGAÇÃO
            cpfCnpj.setCpfCnpj(31759488L);
            cpfCnpj.setFilial(0);
            cpfCnpj.setControle(55);

            body.setCpfCnpj(cpfCnpj);
            body.setProduto(9);
            body.setNegociacao(2856022652L);
            body.setNossoNumero(50130000040L);
            body.setSequencia(0);
            body.setCodigoBaixa(57);
        } else {
            // ? Ambiente PRODUÇÃO
            String inscricao = EMPRESA_CNPJ.get().replaceAll("[^0-9]", "");

            if (inscricao.length() < 14) {
                throw new IllegalArgumentException(
                        "Inscrição inválida para montagem do JSON: " + inscricao
                );
            }

            long raiz = Long.parseLong(inscricao.substring(0, 9));     // ? 9 dígitos
            int filial = Integer.parseInt(inscricao.substring(9, 13)); // ? 4 dígitos
            int controle = Integer.parseInt(inscricao.substring(13));  // ? 2 dígitos

            long negociacao = Long.parseLong(Agencia + Conta);
            long nossoNumero = Long.parseLong(dto.getPAR_NONU().replaceAll("[^0-9]", ""));

            cpfCnpj.setCpfCnpj(raiz);
            cpfCnpj.setFilial(filial);
            cpfCnpj.setControle(controle);

            body.setCpfCnpj(cpfCnpj);
            body.setProduto(9);
            body.setNegociacao(negociacao);
            body.setNossoNumero(nossoNumero);
            body.setSequencia(0);
            body.setCodigoBaixa(57);
        }

        String json = mapper.writeValueAsString(body);
        System.out.println(json);

        return json;
    }
}
