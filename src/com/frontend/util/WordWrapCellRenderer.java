/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.md1.frontend.util;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Osvaldo Vidoy
 */
public class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {

    public WordWrapCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setFont(table.getFont());
        setBackground(table.getBackground());
        setForeground(table.getForeground());
        setText(value.toString());
        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
        if (table.getRowHeight(row) < getPreferredSize().height) {
            table.setRowHeight(row, getPreferredSize().height);
        } else {
            table.setRowHeight(row, getPreferredSize().height);
        }
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
            table.setSelectionBackground(table.getSelectionBackground());
        }
        return this;
    }
}
