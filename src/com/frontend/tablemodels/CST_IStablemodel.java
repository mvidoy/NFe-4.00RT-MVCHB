package com.frontend.tablemodels;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import com.backend.dtos.CST_ISdto;
import app.components.AlignTableHeaderCenter;
import app.components.AlignTableHeaderLeft;

public class CST_IStablemodel extends AbstractTableModel {

    private final String column[] = {
        "Coluna0",
        "coluna1"};

    private final int COLUNA_0 = 0;
    private final int COLUNA_1 = 1;
    public String CRUD_Jtable = "";
    CST_ISdto listCST_ISdto = new CST_ISdto();

    public CST_IStablemodel(JTable jtable, List<CST_ISdto> dados) {
        listCST_ISdto.setCST_ISdto(dados);
    }

    public JTable CST_IStable(JTable jtable) {
        jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        TableCellRenderer aligntableheaderleft = (TableCellRenderer) new AlignTableHeaderLeft();
        TableCellRenderer aligntableheadercenter = (TableCellRenderer) new AlignTableHeaderCenter();

        jtable.getColumnModel().getColumn(0).setHeaderValue("<html><td align=center>Código</td></html>");
        jtable.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(aligntableheaderleft);
        jtable.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(60);
        jtable.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(esquerda);

        jtable.getColumnModel().getColumn(1).setHeaderValue("<html><td align=center>Descrição</td></html>");
        jtable.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(aligntableheadercenter);
        jtable.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(860);
        jtable.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);

        return jtable;
    }

    public boolean canEdit(String CRUD_Jtable, int rowIndex, int columnIndex) {
        boolean[] canEdit = new boolean[]{
            false, false
        };
        if (columnIndex == 1 && CRUD_Jtable.equals("UPDATE")) {
            //canEdit[columnIndex] = true;
            return canEdit[columnIndex];
        }
        if ((columnIndex == 0 || columnIndex == 1)
                && CRUD_Jtable.equals("CREATE")) {
            //canEdit[columnIndex] = true;
            return canEdit[columnIndex];
        }
        return canEdit[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit(this.CRUD_Jtable, rowIndex, columnIndex);
    }

    public boolean isCellEditable(String CRUD_Jtable, int rowIndex, int columnIndex) {
        this.CRUD_Jtable = CRUD_Jtable;
        return true;
    }

    @Override
    public int getRowCount() {
        return listCST_ISdto.getCST_ISdto().size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    @Override
    public String getColumnName(int indice) {
        return column[indice];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case COLUNA_0:
                return Integer.class;
            case COLUNA_1:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (listCST_ISdto.getCST_ISdto().size() <= 0) {
            return null;
        }
        CST_ISdto sITUACAOTRIBUTARIAdto = listCST_ISdto.getCST_ISdto().get(rowIndex);
        switch (columnIndex) {
            case COLUNA_0:
                return sITUACAOTRIBUTARIAdto.getCST_CODIGO();
            case COLUNA_1:
                return sITUACAOTRIBUTARIAdto.getCST_DESCRICAO();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        CST_ISdto sITUACAOTRIBUTARIAdto = listCST_ISdto.getCST_ISdto().get(rowIndex);
        switch (columnIndex) {
            case COLUNA_0:
                sITUACAOTRIBUTARIAdto.setCST_CODIGO((String) aValue);
                break;
            case COLUNA_1:
                sITUACAOTRIBUTARIAdto.setCST_DESCRICAO((String) aValue.toString().toUpperCase());
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        this.fireTableRowsUpdated(rowIndex, rowIndex); //Avisa que a outra coluna também mudou
    }

    public CST_ISdto getCST_ISdto(int rowIndex) {
        return listCST_ISdto.getCST_ISdto().get(rowIndex);
    }

    public void addCST_ISdto(CST_ISdto sITUACAOTRIBUTARIAdto) {
        listCST_ISdto.getCST_ISdto().add(sITUACAOTRIBUTARIAdto);
        int lastIndex = getRowCount() - 1;
        fireTableRowsInserted(lastIndex, lastIndex);
    }

    public void removeCST_ISdto(int rowIndex) {
        listCST_ISdto.getCST_ISdto().remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

     public void updateCST_ISdto(int rowIndex, CST_ISdto sITUACAOTRIBUTARIAdto) {
        listCST_ISdto.getCST_ISdto().set(rowIndex, sITUACAOTRIBUTARIAdto);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
    
    public void addListCST_ISdto(List<CST_ISdto> sITUACAOTRIBUTARIAdto) {
        int rowIndex = getRowCount();
        listCST_ISdto.getCST_ISdto().addAll(sITUACAOTRIBUTARIAdto);
        fireTableRowsInserted(rowIndex, rowIndex + sITUACAOTRIBUTARIAdto.size());
    }

    public List<CST_ISdto> getListCST_ISdto() {
        return listCST_ISdto.getCST_ISdto();
    }

    public void clearListCST_ISdto() {
        listCST_ISdto.getCST_ISdto().clear();
        fireTableDataChanged();
    }
}
