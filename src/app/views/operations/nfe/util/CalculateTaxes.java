package app.views.operations.nfe.util;

import app.views.operations.nfe.exceptions.FieldsNullOrEmptyException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import util.FormatFields;

public class CalculateTaxes {

    FormatFields f = new FormatFields();

    public String CalculaImpostoGenerico(String vBase, String pAliq, String tCalc) throws Exception {
        System.setProperty("MsgInvalid", "");
        Double ww_det_value = 0.00;
        try {
            if (vBase == null || vBase.trim().length() <= 0) {
                vBase = "0,00";
            }
            if (pAliq == null || pAliq.trim().length() <= 0) {
                pAliq = "0,00";
            }
            if (tCalc == null || tCalc.trim().length() <= 0) {
                throw new FieldsNullOrEmptyException("tCalc não foi informado, verifique seus dados!");
            }
            Double.parseDouble(vBase.trim().replace(".", "").replace(",", "."));
            Double.parseDouble(pAliq.trim().replace(".", "").replace(",", "."));
            ww_det_value = (Double.parseDouble(vBase.trim().replace(".", "").replace(",", "."))
                    * Double.parseDouble(pAliq.trim().replace(".", "").replace(",", ".")) / 100);
            ww_det_value = BigDecimal.valueOf(ww_det_value)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        } catch (NumberFormatException ex) {
            System.setProperty("MsgInvalid", ex.getMessage());
            throw new FieldsNullOrEmptyException("Base ou aliquota inválida, verifique seus dados!");
        }
        return f.getFormated_2(ww_det_value).trim();
    }
}
