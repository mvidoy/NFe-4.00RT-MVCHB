/*
 * editarcelulatabela.java
 *
 * Created on 15 de Novembro de 2007, 00:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package util;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author osvaldo
 */
public class EditCellulaTable extends AbstractCellEditor implements TableCellEditor {
         // Este é o componente que vai segurar o valor da edicao da celula   
         JComponent component = new JTextField(); 
         formata formata = new formata(); 
        
          //Este metodo é chamado quando a celula é editada pelo usuario.     
         public Component getTableCellEditorComponent(JTable table, Object value,   
                 boolean isSelected, int rowIndex, int vColIndex) {   
             //'value'é o valor contido na celula que esta na localizacao ((rowIndex, vColIndex)   
                 
             if (isSelected)   
             {   
                 // Celula (e talvez outras celulas) sao selecionadas   
             }   
             // Configura o componente com o valor Especificado   
             ((JTextField)component).setText((String)value);   
        
             // Retorna a configuracao do componente   
             return component;   
         }   
        
        
         // Esse metodo é chamado quando é Terminado de Editar a Celula   
         // Ela retorna o novo valor da Celula.   
         public Object getCellEditorValue() {   
            
              //Chama a funcao insert update da Tabela1 e passa o valor do da celula   
                
              // Imprime o conteudo que o usuario digitou na Celula   
           //   System.out.println(((JTextField)component).getText());       
              
           //   AlteraAssociado(((JTextField)component).getText());                 
              //System.out.println(((JTextField)component).getText());                  
          return formata.Valor(((JTextField)component).getText().trim(),0);   
         }   
     }   

