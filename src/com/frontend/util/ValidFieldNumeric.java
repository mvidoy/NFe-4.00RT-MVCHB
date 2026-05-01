package com.frontend.util;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class ValidFieldNumeric extends PlainDocument {

    private static final long serialVersionUID = 1L;

    public void insertString(int offs, String str,
            javax.swing.text.AttributeSet a) throws BadLocationException {
        for (int i = 0; i < str.length(); i++) // Se não for número, ponto ou vírgula retorna zero
        {
            if (!Character.isDigit(str.charAt(i))
                    && str.charAt(i) != '-'
                    && str.charAt(i) != ','
                    && str.charAt(i) != '.') {
                return;
            }
        }
        super.insertString(offs, str, a);
    }
}
