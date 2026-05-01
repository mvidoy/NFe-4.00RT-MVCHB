/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.md1.frontend.util;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author osvaldo
 */
public class ValidFieldNumeric extends PlainDocument {

    private static final long serialVersionUID = 1L;

    //Sempre usar verbo no infinitivo para criar metodos
    public void insertString(int offs, String str,
            javax.swing.text.AttributeSet a) throws BadLocationException {
        // normalmente apenas uma letra é inserida por vez,
        // mas fazendo assim também previne caso o usuário
        // cole algum texto
        //Aceita virgula e ponto nos números
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
