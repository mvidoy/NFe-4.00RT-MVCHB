/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 *
 * @author Osvaldo Vidoy
 */
public class RemoveCharacterSpecials {

    public String RemoveCharacterSpecials(String str) {
        CharSequence cs = new StringBuilder(str == null ? "" : str);
        String ret = Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        ret = Normalizer.normalize(ret, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(ret).replaceAll("");
    }
}
