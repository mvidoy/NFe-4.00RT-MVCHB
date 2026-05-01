package com.frontend.components;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class formata {

    String gCla_Valor = "";

    /**
     * Formata Valo wdefault (0) = formata Valor com (,) anterior a casas
     * desimais. wdefault (1) = formata Valor com (.) anterior a casas decimais.
     */
    public String Valor(String wtf_valor, int wdefault) {
        if (wtf_valor == null || wtf_valor.trim().length() <= 0) {
            return "";
        }

        if (wdefault == 1) {
            wtf_valor = wtf_valor.trim().replace(',', '.');
        } else {
            wtf_valor = wtf_valor.trim().replace('.', ',');
        }

        String wvalor = wtf_valor;
        String wdec = wtf_valor;
        String winteiro = wtf_valor;
        int idx = wdec.indexOf(",");
        if (idx <= 0) {
            wdec = "";
        } else {
            wdec = wdec.substring(idx + 1);
        }
        if (wdec.length() <= 0 && winteiro.trim().length() > 0) {
            winteiro = winteiro.substring(0, winteiro.trim().length());
        } else {
            if (winteiro.trim().length() > 0) {
                winteiro = winteiro.substring(0, winteiro.indexOf(","));
            }
        }
        if (wdec.length() > 2) {
            wdec = wdec.substring(0, 2);
            wvalor = winteiro + "," + wdec;
        }
        return wvalor;
    }

    public String Data(String data, int wdefault) {
        /**
         * Formata Data wdefault (0) = formata Data digitada. wdefault (1) =
         * formata Data recebida da base de dados. wdefault (2) = formata Data a
         * ser gravada na base de dados.
         */

        if (data == null || data.trim().length() < 10) {
            return "";
        }
        String retorno = "";
        String wdia = "";
        String wmes = "";
        String wano = "";
        data = data.trim().replace("-", "/");

        if (wdefault == 1) {
            wano = data.substring(0, 4);
            wmes = data.substring(5, 7);
            wdia = data.substring(8, 10);
        } else {
            wdia = data.substring(0, 2);
            wmes = data.substring(3, 5);
            wano = data.substring(6, 10);
        }

        if (wdefault == 2) {
            retorno = wano + "-" + wmes + "-" + wdia;
        } else {
            retorno = wdia + "/" + wmes + "/" + wano;
        }

        if (wdefault == 0) {
            if (Integer.parseInt(wdia) <= 0 || Integer.parseInt(wdia) > 31) {
                retorno = "1";
            }
            if (Integer.parseInt(wmes) <= 0 || Integer.parseInt(wmes) > 12) {
                retorno = "2";
            }
            if (Integer.parseInt(wano) <= 0 || Integer.parseInt(wano) <= 2000) {
                retorno = "3";
            }
        }
        return retorno;
    }

    public static String StrZero(String valor, int zerosEsquerda) {
        int numero = Integer.parseInt(valor);
        String formato = "%0" + zerosEsquerda + "d";
        return String.format(formato, numero);
    }

    public String retornacaracterendereco(String wString) {
        wString = wString.trim();
        int wTamanho = wString.length();
        String ret = "";
        int posicao;
        posicao = wString.indexOf(",");
        if (posicao >= 0) {
            ret = wString.substring(0, posicao);
        }
        return ret;
    }

    public String retornanumeroendereco(String wString) {
        wString = wString.trim();
        int wTamanho = wString.length();
        String ret = "";
        int posicao;
        posicao = wString.indexOf(",");
        if (posicao >= 0) {
            ret = wString.substring(posicao + 1, wString.length());
        }
        return ret.trim();
    }

    public String retornanumero(String wString) {
        wString = wString.trim();
        int wTamanho = wString.length();
        String ret = "";
        int posicao;
        ret = wString.replace("-", "");
        ret = ret.replace(".", "");
        ret = ret.replace("/", "");
        ret = ret.replace("(", "");
        ret = ret.replace(")", "");
        ret = ret.replace("[", "");
        ret = ret.replace("]", "");
        ret = ret.replace(" ", "");
        return ret.trim();
    }

    public boolean validaCaracteres(String item, String strValida, int l) {
        // Variaveis para auxilio
        String auxStr = "";
        char auxCode;

        // Faz um laco para percorrer a string em busca de caracteres invalidos
        for (int i = 0; i < item.length(); i++) {
            auxStr = item.substring(i, (i + 1));
            auxCode = auxStr.charAt(0);
            if ((auxCode != 13) && (auxCode != 10) && (auxCode != 0) && (auxCode != 8)) {
                if (strValida.indexOf(auxStr) == -1) {
                    // Retorna false se encontrar algum caracter nao permitido
                    return false;
                }
            }
        }

        if (item.length() > l) {
            return false;
        }

        // Retorna true se estiver tudo certo
        return true;
    }

    public String FormatNumber(double valor, String mascara) {
        String resultado2 = "";
        try {
            DecimalFormat dff = (DecimalFormat) DecimalFormat.getInstance();
            DecimalFormatSymbols dfs = new DecimalFormatSymbols(new Locale("us", "BR"));
            DecimalFormat decimal = new DecimalFormat(mascara, dfs);
            String retFormatNumber = decimal.format(valor);

            //retFormatNumber = String.valueOf(valor);
            // String retFormatNumber = String.valueOf(valor);
            // Double retFormatNumber1 = (Double) dff.parse(retFormatNumber);
            //DecimalFormat df = new DecimalFormat (mascara, dfs);
            //double d = df.parse (retFormatNumber).doubleValue();
            //String s = Double.toString (d); // resultado: 1234567.89
            //Double resultado = (Double) (retFormatNumber1);
            // String retFormatNumber = decimal.format(round(valor,casas));
            resultado2 = retFormatNumber;

        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return resultado2;

    }

    public String FormatNumberInt(int valor, String mascara) {
        String resultado2 = "";
        try {
            DecimalFormat dff = (DecimalFormat) DecimalFormat.getInstance();
            DecimalFormatSymbols dfs = new DecimalFormatSymbols(new Locale("us", "BR"));
            DecimalFormat decimal = new DecimalFormat(mascara, dfs);
            String retFormatNumber = decimal.format(valor);

            //retFormatNumber = String.valueOf(valor);
            // String retFormatNumber = String.valueOf(valor);
            // Double retFormatNumber1 = (Double) dff.parse(retFormatNumber);
            //DecimalFormat df = new DecimalFormat (mascara, dfs);
            //double d = df.parse (retFormatNumber).doubleValue();
            //String s = Double.toString (d); // resultado: 1234567.89
            //Double resultado = (Double) (retFormatNumber1);
            // String retFormatNumber = decimal.format(round(valor,casas));
            resultado2 = retFormatNumber;

        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return resultado2;

    }

}
