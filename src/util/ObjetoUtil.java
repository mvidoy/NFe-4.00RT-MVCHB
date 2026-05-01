package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMResult;
import java.util.Collection;
import java.util.Comparator;
import javax.swing.JComboBox;

public final class ObjetoUtil {

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

    public static String NullToBlank(String obj) {
        obj = obj == null ? "" : obj;
        return obj;
    }

    public static Double NullToDouble(Double obj) {
        obj = obj == null ? 0.0 : obj;
        return obj;
    }

}
