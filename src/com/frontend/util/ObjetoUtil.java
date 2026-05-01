package com.frontend.util;

import app.views.operations.nfe.NFe.comboMultidata;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Base64;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.Collection;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.text.Normalizer;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import com.sun.istack.NotNull;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.backend.controllers.EMPRESAcontroller;
import com.backend.dtos.EMPRESAdto;
import com.backend.exceptions.MD1Exception;
import com.toedter.calendar.JDateChooser;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public final class ObjetoUtil {

    private static final String EMPRESAdto = "EMPRESAdto";

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> Element objectToElement(Object objeto, Class<T> classe, String qName) throws Exception {

        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            JAXB.marshal(new JAXBElement(new QName(qName), classe, objeto), new DOMResult(document));

            return document.getDocumentElement();

        } catch (ParserConfigurationException e) {
            throw new Exception("Erro Ao Converter Objeto em Elemento: " + e.getMessage());
        }
    }

    public static <T> T elementToObject(Element element, Class<T> classe) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(classe);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(element, classe).getValue();
    }

    public static String elementToString(Element element) {
        DOMImplementationLS lsImpl = (DOMImplementationLS) element.getOwnerDocument().getImplementation().getFeature("LS", "3.0");
        LSSerializer serializer = lsImpl.createLSSerializer();
        serializer.getDomConfig().setParameter("xml-declaration", false); //by default its true, so set it to false to get String without xml-declaration
        return serializer.writeToString(element);
    }

    public static String fileToByte(String caminhoArquivo) throws IOException {
        byte[] fileContent = Files.readAllBytes(new File(caminhoArquivo).toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public static byte[] FileToByte(String name) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(name);
            ByteArrayOutputStream byteArrayStream/* buffer */ = new ByteArrayOutputStream();
            int bytesread = 0;
            byte[] tbuff = new byte[1024];
            while ((bytesread = in.read(tbuff)) != -1) {
                byteArrayStream.write(tbuff, 0, bytesread);
            }
            return byteArrayStream.toByteArray();
        } catch (IOException e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e2) {
                }
            }
            return null;
        }
    }

    public ObjetoUtil() {
        super();
    }

    public static Boolean equalsNull(Object object) {
        return object == null;
    }

    public static Boolean differentNull(Object object) {
        return object != null;
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).size() == 0;
        }

        final String s = String.valueOf(obj).trim();

        return s.length() == 0 || s.equalsIgnoreCase("null");
    }

    @SuppressWarnings("rawtypes")
    public static Boolean equalsNull(Collection collection) {
        return collection == null;
    }

    @SuppressWarnings("rawtypes")
    public static Boolean differentNull(Collection collection) {
        return collection != null;
    }

    @SuppressWarnings("rawtypes")
    public static Boolean isEmpty(Collection collection) {
        return collection.isEmpty();
    }

    @SuppressWarnings("rawtypes")
    public static Boolean isNotEmpty(Collection collection) {
        return !collection.isEmpty();
    }

    @SuppressWarnings("rawtypes")
    public static Boolean getBarStatusFormUtil(int nReg, JTable jTable1, JLabel statusbar) {
        if (statusbar == null) {
            return null;
        }
        if (nReg == 0) {
            jTable1.getSelectionModel().setSelectionInterval(0, 0);
        } else if (nReg == 2) {
            jTable1.getSelectionModel().setSelectionInterval(jTable1.getSelectedRow() + 0, jTable1.getSelectedRow() + 0);
        } else if (nReg == 3) {
            jTable1.getSelectionModel().setSelectionInterval(jTable1.getRowCount() - 1, jTable1.getRowCount() - 1);
        } else if (jTable1.getSelectedRow() + nReg < jTable1.getRowCount()) {
            jTable1.getSelectionModel().setSelectionInterval(jTable1.getSelectedRow() + nReg, jTable1.getSelectedRow() + nReg);
        }
        statusbar.setText(" Registro: " + Integer.toString(jTable1.getSelectedRow() + 1) + "/" + jTable1.getRowCount());
        Rectangle cellRect = jTable1.getCellRect(jTable1.getSelectedRow(), 0, true);
        jTable1.scrollRectToVisible(cellRect);
        return null;
    }

    public static String getSelectedIndexFromJcbxUtil(@NotNull final JComboBox jcbx,
            @NotNull final String fieldIndex, Boolean Multidata) {
        jcbx.setSelectedIndex(0);
        if (fieldIndex == null || fieldIndex.trim().length() <= 0) {
            return fieldIndex;
        }
        for (int i = 0; i < jcbx.getItemCount(); i++) {
            if (Multidata) {
                if (((comboMultidata) jcbx.getItemAt(i)).getValor().trim().equals(fieldIndex.trim())) {
                    jcbx.setSelectedIndex(i);
                    break;
                }
            } else if (!Multidata && jcbx.getItemAt(i).toString().equals(fieldIndex)) {
                jcbx.setSelectedIndex(i);
                break;
            }
        }
        return fieldIndex;
    }

    public static String getSelectedIndexFromJcbxUtil(@NotNull final JComboBox jcbx,
            @NotNull final String valorIndex, int index) {
        jcbx.setSelectedIndex(0);
        if (valorIndex == null || valorIndex.trim().length() <= 0) {
            return valorIndex;
        }
        for (int i = 0; i < jcbx.getItemCount(); i++) {
            String itemString = jcbx.getItemAt(i).toString();
            if (itemString.substring(0, 1).equals(valorIndex)) {
                jcbx.setSelectedIndex(i);
                break;
            }
        }
        return valorIndex;
    }

    public static JComboBox setObjectToJcbxUtil(@NotNull final JComboBox jcbx,
            @NotNull final Object obj) throws MD1Exception, SQLException {
        switch (obj.getClass().getSimpleName()) {
            case EMPRESAdto:
                List<EMPRESAdto> mapEMPRESAdto = EMPRESAcontroller.List("","");
                if (mapEMPRESAdto.size() <= 0) {
                    return null;
                }
                jcbx.removeAllItems();
                jcbx.addItem(new comboMultidata("", ""));
                for (int i = 0; i < mapEMPRESAdto.size(); i++) {
                    if (mapEMPRESAdto.get(i).getEMP_RZSO().trim().length() > 0) {
                        jcbx.addItem(new comboMultidata(
                                mapEMPRESAdto.get(i).getEMP_CODI().trim(),
                                mapEMPRESAdto.get(i).getEMP_RZSO().trim()));
                    }
                }
                break;
                
            default:
                throw new MD1Exception("Objeto não mapeado no ObjetoUtil:" + obj.getClass().getSimpleName());
        }
        return jcbx;
    }

    public static void getFindJTable(JTable jTable, int Coluna, String Codigo, JLabel statusbar) throws AWTException {
        int prow = jTable.getRowCount();
        TableModel modelo = (TableModel) jTable.getModel();
        for (int i = 0; i < prow; i++) {
            if (modelo.getValueAt(i, Coluna).toString().equals(Codigo)) {
                jTable.getSelectionModel().setSelectionInterval(i, i);
                //statusbar.setText(" Registro: " + Integer.toString(jTable.getSelectedRow() + 1) + "/" + jTable.getRowCount());
                Rectangle cellRect = jTable.getCellRect(jTable.getSelectedRow(), 0, true);
                jTable.scrollRectToVisible(cellRect);
                break;
            }
        }
    }

    public static boolean getFindJTable(JTable jTable, int Coluna1, int Coluna2, String Codigo1, String Codigo2) throws AWTException {
        boolean find = false;
        int prow = jTable.getRowCount();
        TableModel modelo = (TableModel) jTable.getModel();
        for (int i = 0; i < prow; i++) {
            if (modelo.getValueAt(i, Coluna1).toString().equals(Codigo1)
                    && modelo.getValueAt(i, Coluna2).toString().equals(Codigo2)) {
                jTable.getSelectionModel().setSelectionInterval(i, i);
                Rectangle cellRect = jTable.getCellRect(jTable.getSelectedRow(), 0, true);
                jTable.scrollRectToVisible(cellRect);
                find = true;
                break;
            }
        }
        if (!find) {
            jTable.getSelectionModel().clearSelection();
        }
        return find;
    }

    public static boolean getFindJTable(JTable jTable, int Coluna1, int Coluna2, int Coluna3, String Codigo1, String Codigo2, String Codigo3) throws AWTException {
        boolean find = false;
        int prow = jTable.getRowCount();
        TableModel modelo = (TableModel) jTable.getModel();
        for (int i = 0; i < prow; i++) {
            if (modelo.getValueAt(i, Coluna1).toString().equals(Codigo1)
                    && modelo.getValueAt(i, Coluna2).toString().equals(Codigo2)
                    && modelo.getValueAt(i, Coluna3).toString().equals(Codigo3)) {
                jTable.getSelectionModel().setSelectionInterval(i, i);
                Rectangle cellRect = jTable.getCellRect(jTable.getSelectedRow(), 0, true);
                jTable.scrollRectToVisible(cellRect);
                find = true;
                break;
            }
        }
        if (!find) {
            jTable.getSelectionModel().clearSelection();
        }
        return find;
    }

    public static void getFindJTable(JTable jTable, int Row) throws AWTException {
        int prow = jTable.getRowCount();
        jTable.getSelectionModel().setSelectionInterval(Row, Row);
        Rectangle cellRect = jTable.getCellRect(jTable.getSelectedRow(), 0, true);
        jTable.scrollRectToVisible(cellRect);

    }

    public static void getFindJTable(JTable jTable, int Coluna, String Codigo) throws AWTException {
        int prow = jTable.getRowCount();
        TableModel modelo = (TableModel) jTable.getModel();
        for (int i = 0; i < prow; i++) {
            if (modelo.getValueAt(i, Coluna).toString().equals(Codigo)) {
                jTable.getSelectionModel().setSelectionInterval(i, i);
                Rectangle cellRect = jTable.getCellRect(jTable.getSelectedRow(), 0, true);
                jTable.scrollRectToVisible(cellRect);
                break;
            }
        }
    }

    public static boolean getFindJTable(JTable jTable, int Coluna1, int Coluna2, String Codigo1, String Codigo2, JLabel statusbar) throws AWTException {
        boolean find = false;
        int prow = jTable.getRowCount();
        TableModel modelo = (TableModel) jTable.getModel();
        for (int i = 0; i < prow; i++) {
            if (modelo.getValueAt(i, Coluna1).toString().equals(Codigo1)
                    && modelo.getValueAt(i, Coluna2).toString().equals(Codigo2)) {
                jTable.getSelectionModel().setSelectionInterval(i, i);
                statusbar.setText(" Registro: " + Integer.toString(jTable.getSelectedRow() + 1) + "/" + jTable.getRowCount());
                Rectangle cellRect = jTable.getCellRect(jTable.getSelectedRow(), 0, true);
                jTable.scrollRectToVisible(cellRect);
                find = true;
                break;
            }
        }
        if (!find) {
            jTable.getSelectionModel().clearSelection();
        }
        return find;
    }

    public static String NullToBlank(String obj) {
        obj = obj == null ? "" : obj;
        return obj;
    }

    public static Double NullToDouble(Double obj) {
        obj = obj == null ? 0.0 : obj;
        return obj;
    }

    public static String RemoveCharacterSpecials(String str) {
        CharSequence cs = new StringBuilder(str == null ? "" : str);
        String ret = Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        ret = Normalizer.normalize(ret, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(ret).replaceAll("").replace(".", "").replace("/", "").replace("-", "");
    }

    public static String getRetornaSoNumero(String str) {
        CharSequence cs = new StringBuilder(str == null ? "" : str);
        String ret = Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        ret = Normalizer.normalize(ret, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(ret).replaceAll("").replace(".", "").replace("/", "").replace("-", "").replace(" ", "").replace(")", "").replace("(", "");
    }

    public static String getRetornaLogrEndereco(String valor) {
        String xLgr = "";
        String xNumero = "";
        int index = valor.indexOf(',');
        if (index != -1) {
            xLgr
                    = valor.trim().substring(0,
                            valor.trim().indexOf(","));
            xNumero
                    = valor.trim().substring(
                            valor.trim().indexOf(",") + 1);
        } else {
            xLgr = valor.trim();
            xNumero = "";
        }
        return xLgr;
    }

    public static String getRetornaNumeroEndereco(String valor) {
        String xNumero = "";
        int index = valor.indexOf(',');
        if (index != -1) {
            xNumero
                    = valor.trim().substring(
                            valor.trim().indexOf(",") + 1);
        } else {
            xNumero = "";
        }
        return xNumero;
    }

    public static String getRetornaComplementoEndereco(String valor) {
        String complementoEndereco = "";

        for (int x = 0; x < valor.length(); x++) {
            if (valor.charAt(x) == '-') {
                complementoEndereco = valor.substring(x + 1).trim();
                break;
            }
        }
        complementoEndereco = (complementoEndereco.trim().length() <= 0) ? "" : complementoEndereco;
        return complementoEndereco;
    }

    public static boolean ValidFieldNumeric(String str, int dig) {
        boolean numeros = str.replace(",", ".").matches("^-[0-9]+$\\d{" + dig + ",}");
        return numeros;
    }

    public static void getErrorInStatusBar(String[] paramrs, JLabel statusbar) {
        if (paramrs[0] != null && paramrs[0].trim().length() > 0) {
            String error = "<html><font color=\"black\">" + statusbar.getText() + "</font><font color=\"red\">" + " Erro: " + paramrs[0] + "</font></html>";
            statusbar.setText(error);
        } else {
            statusbar.setText("Registro: ");
        }
    }

    public static String getNumberToFormat(Double obj, String Masc) {
        DecimalFormat newFormat = new DecimalFormat(Masc);

        // Divida a string em partes, usando o ponto como delimitador
        String[] parts = Masc.split("\\.");
        int count = 2;
        // Se houver partes suficientes após a divisão
        if (parts.length > 1) {
            String decimalPart = parts[1]; // A parte decimal após o ponto
            count = decimalPart.length(); // Contagem do número de dígitos na parte decimal
        }

        double wobj = BigDecimal.valueOf(obj)
                .setScale(count, RoundingMode.HALF_UP)
                .doubleValue();

        obj = obj == null ? null : obj;
        return obj == null ? null : newFormat.format(wobj).replace(".", ",");
    }

    public static String getNumberToFormat(int obj, String Masc) {
        DecimalFormat newFormat = new DecimalFormat(Masc);
        return newFormat.format(obj).replace(".", ",");
    }

    public static String getNumberToFormat(long obj, String Masc) {
        DecimalFormat newFormat = new DecimalFormat(Masc);
        return newFormat.format(obj).replace(".", ",");
    }

    public static double getRoundDouble(double obj, int scala) {
        //obj = BigDecimal.valueOf(obj)
        //        .setScale(scala + 1, RoundingMode.HALF_UP)
        //        .doubleValue();
        obj = BigDecimal.valueOf(obj)
                .setScale(scala, RoundingMode.HALF_UP)
                .doubleValue();
        return obj;
    }

    public static boolean getValidFieldNumeric(String str) {
        boolean wReturn = false;
        str = str.trim();
        if (str.length() <= 0) {
            wReturn = true;
            return wReturn;
        }
        for (int i = 0; i < str.length(); i++) // Se não for número, ponto ou vírgula retorna zero
        {
            if (!Character.isDigit(str.charAt(i))
                    && str.charAt(0) != '-'
                    && str.charAt(i) != ','
                    && str.charAt(i) != '.') {
                wReturn = true;
            }
            if (!Character.isDigit(str.charAt(i))
                    && str.charAt(0) != '-'
                    && str.charAt(i) != ','
                    && str.charAt(i) != '.') {
                wReturn = true;
            }
            if (!Character.isDigit(str.charAt(i))
                    && (str.charAt(0) == '-'
                    || str.charAt(i) == ','
                    || str.charAt(i) == '.')
                    && str.trim().length() <= 1) {
                wReturn = true;
            }
        }
        return wReturn;
    }

    public static String getJTextFieldFormated(String str, String wDigit) {
        DecimalFormat newFormatDig = new DecimalFormat(wDigit);
        String NewJTextFieldFormated = "";
        if (getValidFieldNumeric(str.trim().replace(",", "."))
                || str.trim().length() <= 0) {
            return null;
        }
        NewJTextFieldFormated = newFormatDig.format(Double.parseDouble(str.trim().replace(",", "."))).replace(".", ",");
        return NewJTextFieldFormated;
    }

    public static Double getJTextFieldDouble(String str) {
        if (getValidFieldNumeric(str.trim().replace(",", "."))
                || str.trim().length() <= 0) {
            return null;
        }
        return Double.parseDouble(str.trim().replace(",", "."));
    }

    public static boolean isDateValid(String strDate) {
        String dateFormat = "dd/MM/uuuu";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(dateFormat)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean ValidTfFields(javax.swing.JTextField obj, String label) {
        boolean flag = true;
        if (obj.getText().trim().length() <= 0) {
            JOptionPaneJDialog("Preenchimento do Campo [" + label + "] é obrigatório, verifique!!!", JOptionPane.ERROR_MESSAGE);
            flag = false;
            obj.grabFocus();
        }
        return flag;
    }

    public static boolean ValidTfFields(JDateChooser obj, String label) {
        boolean flag = true;
        if (obj.getDate() == null) {
            JOptionPaneJDialog("Preenchimento do Campo [" + label + "] é obrigatório, verifique!!!", JOptionPane.ERROR_MESSAGE);
            flag = false;
            obj.grabFocus();
        }
        return flag;
    }

    public static boolean ValidTJComboBox(javax.swing.JComboBox obj, String label) {
        boolean flag = true;
        if (obj.getSelectedIndex() <= 0) {
            JOptionPaneJDialog("Seleção do Campo [" + label + "] é obrigatório, verifique!!!", JOptionPane.ERROR_MESSAGE);
            flag = false;
            obj.grabFocus();
        }
        return flag;
    }

    public static void JOptionPaneJDialog(String message, int type) {
        JOptionPane optionPane = new JOptionPane(new JLabel(message, JLabel.LEFT));
        JDialog dialog = optionPane.createDialog("Mensagem do Sistema");
        optionPane.setMessageType(type);
        //optionPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        optionPane.setSize(optionPane.getWidth() + message.trim().length(), optionPane.getHeight() + 30);
        dialog.setSize(optionPane.getSize());
        dialog.setVisible(true);
    }

    public static String getNomeMunicipioIBGE(String UF, String codicoMunicipio) {
        String nomeMunIBGE = null;
        try {
            String urlApi = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + UF + "/municipios";
            HttpURLConnection connection = null;
            URL url = new URL(urlApi);
            boolean tentarNovamente = true;
            while (tentarNovamente) {
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5000); // Define um tempo limite de 5 segundos
                connection.setRequestMethod("GET");
                // Define o cabeçalho da requisição
                connection.setRequestProperty("Accept-Charset", "UTF-8");
                int codigoResposta = connection.getResponseCode();

                if (codigoResposta == 429) {
                    String tempoEsperaStr = connection.getHeaderField("Retry-After");
                    int tempoEspera = tempoEsperaStr != null ? Integer.parseInt(tempoEsperaStr) : 5; // tempo padrão de 5 segundos
                    //System.out.println("Servidor retornou código de resposta 429 - Too Many Requests. Tentando novamente em " + tempoEspera + " segundos...");
                    Thread.sleep(tempoEspera * 1000); // aguardar tempo especificado antes de tentar novamente
                } else {
                    tentarNovamente = false;
                    //System.out.println("Resposta do servidor: " + codigoResposta);
                    // Faça algo com a resposta do servidor
                }
            }
            //connection.setRequestProperty("Accept", "application/json");
            // Ler os dados da resposta
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // Analisar a resposta
            String json = response.toString();
            connection.disconnect();
            // Parse do JSON para encontrar o código do IBGE

            JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                String id = jsonObject.get("id").getAsString();
                //System.out.println(jsonObject.get("nome").getAsString());
                if (id.equals(codicoMunicipio)) {
                    nomeMunIBGE = jsonObject.get("nome").getAsString();
                    break;
                }
            }
            nomeMunIBGE = String.valueOf(nomeMunIBGE);
        } finally {
            return nomeMunIBGE;
        }
    }
    
    public static String getPrimeiraLetraMaiuscula(String frase) {
        frase = frase.toLowerCase();
        if (frase == null || frase.isEmpty()) {
            return frase;
        }

        String primeiraLetra = frase.substring(0, 1).toUpperCase();
        String restanteDaFrase = frase.substring(1);

        if (restanteDaFrase.contains("icms")) {
            restanteDaFrase = restanteDaFrase.replaceAll("icms", "ICMS");
        }

        if (restanteDaFrase.contains("ipi")) {
            restanteDaFrase = restanteDaFrase.replaceAll("ipi", "IPI");
        }

        frase = primeiraLetra + restanteDaFrase;

        if (frase.contains("Ipi")) {
            frase = frase.replaceAll("Ipi", "IPI");
        }
        return frase;
    }

    public static String getBuscarItemComboBox(JComboBox<String> comboBox, String prefixo, int tamanho) {
        String prefixoCompleto = String.format("%-" + tamanho + "s", prefixo).replace(" ", "_");
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            String item = comboBox.getItemAt(i);
            if (item.startsWith(prefixoCompleto)) {
                comboBox.setSelectedIndex(i);
                return item;
            }
        }
        return null;
    }

    public static String getNumeroDepoisDaVirgula(String endereco) {
        // Verificar se a string contém uma vírgula
        int posicaoVirgula = endereco.indexOf(",");
        if (posicaoVirgula >= 0) {
            // Se houver vírgula, extrair a parte após a vírgula (número)
            String numeroAposVirgula = endereco.substring(posicaoVirgula + 1).trim();
            return numeroAposVirgula;
        } else {
            // Se não houver vírgula, retornar uma string vazia
            return "";
        }
    }

    public static String getRemoveVirgulaENumero(String endereco) {
        // Remover a vírgula e o número usando replaceAll
        String enderecoSemNumero = endereco.replaceAll(",\\s*\\d+$", "");
        return enderecoSemNumero.trim();
    }

    public static class MyComparator implements Comparator<Object> {

        @Override
        public int compare(Object o1, Object o2) {
            // Aqui você pode implementar a lógica para classificar as colunas conforme necessário
            // Neste exemplo, classificamos como strings em ordem alfabética
            String s1 = o1.toString();
            String s2 = o2.toString();
            return s1.compareTo(s2);
        }
    }

    public static MyComparator getMyComparator() {
        return new MyComparator();
    }

    public static DateComparator getDateComparator() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return new DateComparator(format);
    }

    public static class DateComparator implements Comparator<Object> {

        private final SimpleDateFormat format;

        private DateComparator(SimpleDateFormat format) {
            this.format = format;
        }

        @Override
        public int compare(Object o1, Object o2) {
            // Parse as datas usando o formato de classificação (yyyy/MM/dd)
            // Obtém os valores da coluna 3 para os objetos o1 e o2
            try {
                String dateStr1 = o1.toString();
                String dateStr2 = o2.toString();

                SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy/MM/dd");

                java.util.Date date1 = inputDateFormat.parse(dateStr1);
                java.util.Date date2 = inputDateFormat.parse(dateStr2);

                // Convertendo as datas para o formato yyyy/MM/dd
                String convertedDate1 = outputDateFormat.format(date1);
                String convertedDate2 = outputDateFormat.format(date2);

                return convertedDate1.compareTo(convertedDate2);
            } catch (java.text.ParseException ex) {
                // Tratar qualquer exceção de análise aqui, se necessário
                ex.printStackTrace();
                return 0;
            }
        }
    }

    public static String getRepeat(String cHar, int iNdex) {
        String result = "";
        for (int i = 0; i < iNdex; i++) {
            result = result.concat(cHar);
        }
        return result;
    }

    public static class DateComparator2 implements Comparator<Object> {

        private final SimpleDateFormat inputDateFormat;

        // Construtor com o formato de entrada
        public DateComparator2(SimpleDateFormat inputDateFormat) {
            this.inputDateFormat = inputDateFormat;
        }

        @Override
        public int compare(Object o1, Object o2) {
            try {
                // Converte as strings para Date
                java.util.Date date1 = inputDateFormat.parse(o1.toString());
                java.util.Date date2 = inputDateFormat.parse(o2.toString());

                // Compara diretamente as datas
                return date1.compareTo(date2);
            } catch (java.text.ParseException ex) {
                // Tratar qualquer exceção de análise aqui, se necessário
                ex.printStackTrace();
                return 0;
            }
        }
    }
    public static class comboMultidata {

        private String valor;
        private String texto;

        public comboMultidata(String id, String label) {
            this.valor = id;
            this.texto = label;
        }

        public String getValor() {
            return valor.toString();
        }

        @Override
        public String toString() {
            return texto.toString();
        }
    }

}
