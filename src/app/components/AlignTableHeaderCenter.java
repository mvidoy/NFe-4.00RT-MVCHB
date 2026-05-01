/*
 * alinhacolJtable.java
 *
 * Created on 13 de Novembro de 2007, 11:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package app.components;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class AlignTableHeaderCenter extends DefaultTableCellRenderer {
        
    private Color getCellColor(){
        
        // este é um teste qualquer que faço para decidir 
        // a cor da fonte de cada linha.
        // substitua por qualquer outro teste, se achar necessário
        
         return Color.GREEN;
     
    }   
    
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
              
              JLabel label = (JLabel)super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
              
              setText((value == null) ? "" : value.toString());   
              setBorder(UIManager.getBorder("TableHeader.cellBorder"));   
              setHorizontalAlignment(JLabel.CENTER);   
              setHorizontalTextPosition(JLabel.CENTER);   
              //setFont(new Font("Arial", Font.PLAIN, 8)); 
              if((row % 2) == 0){            
                //cor de fundo
               label.setBackground(Color.MAGENTA);  
              }   
            
              //cor da fonte
              //if(column != 0)
              //  label.setForeground(getCellColor(credor));                      
              //}
              
              return this; 
       } 
}