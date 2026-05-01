/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.domain.service.exception;

public class NomenclaturaException extends ValidacaoException {

    private static final long serialVersionUID = 5292642729761124983L;
    private static final String MESSAGE = "Classificação tributária (cClassTrib) de código %s só se aplica a %s (%s)";

    public NomenclaturaException(String cClassTrib, String nomenclatura, String tributo) {
        super(String.format(MESSAGE, cClassTrib, nomenclatura, tributo));
    }

}
