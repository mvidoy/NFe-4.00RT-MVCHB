/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontend.components;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

/**
 *
 * @author osvaldo
 */
public class FormatFields {

    private JFormattedTextField cnpj_cpf;
    private JFormattedTextField f_IE;
    private JFormattedTextField f_CEP;
    private JFormattedTextField f_NFe;
    private JFormattedTextField w_Numero;

    public String getCpfCnpjFormatado(String cpfCnpj) {
        //String wcpfCnpj = cpfCnpj;
        // if (cpfCnpj.trim().length() < 14) {
        //     for (int i = 0; i < 14-cpfCnpj.length(); i++) {
        //         wcpfCnpj = "0" + wcpfCnpj.trim();
        //    }
        //    cpfCnpj = wcpfCnpj;
        // }        
        cnpj_cpf = new javax.swing.JFormattedTextField();
        String valor = cpfCnpj.replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "").replaceAll(" ", "");
        if (valor.length() == 11) {
            try {
                cnpj_cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            try {
                cnpj_cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        cnpj_cpf.setText(valor);

        return cnpj_cpf.getText();

    }

    public String getIEFormatado(String IE) {
        f_IE = new javax.swing.JFormattedTextField();
        String valor = IE.replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "").replaceAll(" ", "");
        if (valor.length() == 12) {
            try {
                f_IE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###.###")));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            try {
                f_IE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###/####")));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        f_IE.setText(valor);
        return f_IE.getText();
    }

    public String getCEPFormatado(String CEP) {
        f_CEP = new javax.swing.JFormattedTextField();
        if (CEP.length() == 7) {
            CEP = "0" + CEP;
        }

        String valor = CEP.replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "").replaceAll(" ", "");
        if (valor.length() == 8) {
            try {
                f_CEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            try {
                f_CEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###/####")));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        f_CEP.setText(valor);
        return f_CEP.getText();
    }

    public String getIdNFeFormatado(String NFe) {
        f_NFe = new javax.swing.JFormattedTextField();
        String valor = NFe.replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "").replaceAll(" ", "").replaceAll("NFe", "");
        //if (valor.length() == 36) {
        try {
            f_NFe.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#### #### #### #### #### #### #### #### #### #### ####")));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        //}
        f_NFe.setText(valor);
        return f_NFe.getText();
    }

    public String getIdNFeFormatadoDANFE(String NFe) {
        f_NFe = new javax.swing.JFormattedTextField();
        String valor = NFe.replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "").replaceAll(" ", "").replaceAll("NFe", "");
        //if (valor.length() == 36) {
        try {
            f_NFe.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###")));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        //}
        f_NFe.setText(valor);
        return f_NFe.getText();
    }

    public String getDataFormatado(String Data) throws ParseException {
        String dataEmUmFormato = "2009-10-30";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date data = formato.parse(Data);
        formato.applyPattern("dd/MM/yyyy");
        String dataFormatada = formato.format(data);
        return dataFormatada;
    }

    public String getFormated_2(Double wNumero) throws ParseException {
        Locale localeBR = new Locale("pt", "BR");
        NumberFormat inteiro = NumberFormat.getInstance();
        NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
        NumberFormat percentual = NumberFormat.getPercentInstance(localeBR);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(localeBR);
        return dinheiro.format(wNumero).replace("R$", "");
    }

    public String getNumeroFormatado2cd(String wNumero) throws ParseException {
        if (wNumero == null) {
            return null;
        }
        w_Numero = new javax.swing.JFormattedTextField();

        String valor = wNumero.trim().replace(".", "");
        if (wNumero.length() == 4) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#,##")));
        }
        if (wNumero.length() == 5) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##,##")));
        }
        if (wNumero.length() == 6) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###,##")));
        }
        if (wNumero.length() == 7) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.###,##")));
        }
        if (wNumero.length() == 8) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###,##")));
        }
        if (wNumero.length() == 9) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###,##")));
        }
        if (wNumero.length() == 10) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.###.###,##")));
        }
        if (wNumero.length() == 11) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###,##")));
        }
        if (wNumero.length() == 12) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###,##")));
        }
        w_Numero.setText(valor);

        return w_Numero.getText();

    }

    public String getNumeroFormatado4cd(String wNumero) throws ParseException {
        if (wNumero == null) {
            return null;
        }

        w_Numero = new javax.swing.JFormattedTextField();

        String valor = wNumero.trim().replace(".", "");
        if (wNumero.length() == 6) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#,####")));
        }
        if (wNumero.length() == 7) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##,####")));
        }
        if (wNumero.length() == 8) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###,####")));
        }
        if (wNumero.length() == 9) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.###,####")));
        }
        if (wNumero.length() == 10) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###,####")));
        }
        if (wNumero.length() == 11) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###,####")));
        }
        if (wNumero.length() == 12) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.###.###,####")));
        }
        if (wNumero.length() == 13) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###,####")));
        }
        if (wNumero.length() == 14) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###,####")));
        }
        w_Numero.setText(valor);

        return w_Numero.getText();

    }

    public String getNumeroFormatado10cd(String wNumero) throws ParseException {
        if (wNumero == null) {
            return null;
        }
        w_Numero = new javax.swing.JFormattedTextField();

        String valor = wNumero.trim().replace(".", "");
        if (wNumero.length() == 12) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#,##########")));
        }
        if (wNumero.length() == 13) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##,##########")));
        }
        if (wNumero.length() == 14) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###,##########")));
        }
        if (wNumero.length() == 15) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.###,##########")));
        }
        if (wNumero.length() == 16) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###,##########")));
        }
        if (wNumero.length() == 17) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###,##########")));
        }
        if (wNumero.length() == 18) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.###.###,##########")));
        }
        if (wNumero.length() == 19) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###,##########")));
        }
        if (wNumero.length() == 20) {
            w_Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###,##########")));
        }
        w_Numero.setText(valor);

        return w_Numero.getText();

    }

}
