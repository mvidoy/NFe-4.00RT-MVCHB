/*
 * alinhacolJtable.java
 *
 * Created on 13 de Novembro de 2007, 11:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package util;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author osvaldo
 */
public class aligntableheaderleft extends DefaultTableCellRenderer {
       public Component getTableCellRendererComponent(JTable table, Object value,   
              boolean isSelected, boolean hasFocus, int row, int column) {   
              if (table != null) {   
                 JTableHeader header = table.getTableHeader();   
                 if (header != null) {   
                    setForeground(header.getForeground());   
                    setBackground(header.getBackground());   
                    setFont(header.getFont());   
                 }   
              }   
              setText((value == null) ? "" : value.toString());   
              setBorder(UIManager.getBorder("TableHeader.cellBorder"));   
              setHorizontalAlignment(JLabel.LEFT);   
              setHorizontalTextPosition(JLabel.LEFT);   
              return this; 
       } 
}