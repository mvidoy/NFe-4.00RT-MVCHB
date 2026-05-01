package com.frontend.tablemodels;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JInternalFrame;
import java.util.stream.Collectors;

import com.backend.dtos.NFEDETPRODdto;

import com.frontend.components.AlignTableHeaderCenter;
import com.frontend.components.AlignTableHeaderRight;
import static com.frontend.util.ObjetoUtil.getNumberToFormat;
import static com.frontend.util.ObjetoUtil.getRoundDouble;
import java.util.ArrayList;

public class NFEDETPRODtablemodel extends AbstractTableModel {

    private final String column[] = {
        "column0",
        "column1",
        "column2",
        "column3",
        "column4",
        "column5",
        "column6"};

    private final int COLUMN_0 = 0;
    private final int COLUMN_1 = 1;
    private final int COLUMN_2 = 2;
    private final int COLUMN_3 = 3;
    private final int COLUMN_4 = 4;
    private final int COLUMN_5 = 5;
    private final int COLUMN_6 = 6;
    public String CRUD_Jtable = "";
    public JTable jtable;
    public JInternalFrame jInternalFrame;
    NFEDETPRODdto listNFEDETPRODdto = new NFEDETPRODdto();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    JDateChooser jDateChooser = new JDateChooser(new Date(), "dd/MM/yyyy");

    public NFEDETPRODtablemodel(JTable jtable, List<NFEDETPRODdto> dados, JInternalFrame jInternalFrame) {
        Locale.setDefault(new Locale("pt", "BR"));
        getSortedTableModel(dados);
        //listNFEDETPRODdto.setNFEDETPRODdto(dados);
        this.jtable = jtable;
        this.jInternalFrame = jInternalFrame;
    }

    public JTable NFEDETPRODtable(JTable jtable) {
        jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        esquerda.setHorizontalAlignment(SwingConstants.LEFT);
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        TableCellRenderer aligntableheadercenter = (TableCellRenderer) new AlignTableHeaderCenter();
        TableCellRenderer aligntableheaderright = (TableCellRenderer) new AlignTableHeaderRight();

        jtable.getColumnModel().getColumn(0).setHeaderValue("<html><td align=center>Código</td><td align=center>Produto</td></html>");
        jtable.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(aligntableheadercenter);
        jtable.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(100);
        jtable.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(centro);

        jtable.getColumnModel().getColumn(1).setHeaderValue("<html><td align=center>Descrição do Produto/Serviço</td></html>");
        jtable.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(aligntableheadercenter);
        jtable.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(1180);
        jtable.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(esquerda);

        jtable.getColumnModel().getColumn(2).setHeaderValue("<html><td align=center>Un</td></html>");
        jtable.getTableHeader().getColumnModel().getColumn(2).setHeaderRenderer(aligntableheadercenter);
        jtable.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(100);
        jtable.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(centro);

        jtable.getColumnModel().getColumn(3).setHeaderValue("<html><td align=center>STR</td></html>");
        jtable.getTableHeader().getColumnModel().getColumn(3).setHeaderRenderer(aligntableheadercenter);
        jtable.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(100);
        jtable.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(centro);

        jtable.getColumnModel().getColumn(4).setHeaderValue("<html><td align=center>Qtde</td></html>");
        jtable.getTableHeader().getColumnModel().getColumn(4).setHeaderRenderer(aligntableheadercenter);
        jtable.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(140);
        jtable.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(direita);

        jtable.getColumnModel().getColumn(5).setHeaderValue("<html><td align=center>Valor</td><td align=center>Unitário</td></html>");
        jtable.getTableHeader().getColumnModel().getColumn(5).setHeaderRenderer(aligntableheaderright);
        jtable.getTableHeader().getColumnModel().getColumn(5).setPreferredWidth(140);
        jtable.getTableHeader().getColumnModel().getColumn(5).setCellRenderer(direita);

        jtable.getColumnModel().getColumn(6).setHeaderValue("<html><td align=center>Valor</td><td align=center>Total</td></html>");
        jtable.getTableHeader().getColumnModel().getColumn(6).setHeaderRenderer(aligntableheaderright);
        jtable.getTableHeader().getColumnModel().getColumn(6).setPreferredWidth(140);
        jtable.getTableHeader().getColumnModel().getColumn(6).setCellRenderer(direita);
        return jtable;
    }

    public boolean canEdit(String CRUD_Jtable, int rowIndex, int columnIndex) {
        boolean[] canEdit = new boolean[]{
            false,
            false,
            false,
            false,
            false,
            false,
            false
        };

        if ((columnIndex == 0
                || columnIndex == 3
                || columnIndex == 4)
                && (CRUD_Jtable.equals("CREATE")
                || CRUD_Jtable.equals("UPDATE"))) {
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
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return 0;
        } else {
            return listNFEDETPRODdto.getNFEDETPRODdto().size();
        }
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
            case COLUMN_0:
                return Integer.class;
            case COLUMN_1:
                return String.class;
            case COLUMN_2:
                return String.class;
            case COLUMN_3:
                return String.class;
            case COLUMN_4:
                return Double.class;
            case COLUMN_5:
                return Double.class;
            case COLUMN_6:
                return Double.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NFEDETPRODdto nFEDETPRODdto = listNFEDETPRODdto.getNFEDETPRODdto().get(rowIndex);
        switch (columnIndex) {
            case COLUMN_0:
                return getNumberToFormat(nFEDETPRODdto.getDET_PROD_ITEM(), "000000");
            case COLUMN_1:
                return nFEDETPRODdto.getDET_PROD_XPROD();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        NFEDETPRODdto nFEDETPRODdto = listNFEDETPRODdto.getNFEDETPRODdto().get(rowIndex);
        switch (columnIndex) {
            case COLUMN_0:
                nFEDETPRODdto.setDET_PROD_ITEM((int) aValue);
                break;
            case COLUMN_1:
                nFEDETPRODdto.setDET_PROD_XPROD((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        this.fireTableRowsUpdated(rowIndex, rowIndex); //Avisa que a outra coluna também mudou
    }

    public NFEDETPRODdto getNFEDETPRODdto(int rowIndex) {
        return listNFEDETPRODdto.getNFEDETPRODdto().get(rowIndex);
    }

    public void addNFEDETPRODdto(NFEDETPRODdto nFEDETPRODdto) {
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            listNFEDETPRODdto.setNFEDETPRODdto(new ArrayList<>()); // Crie uma nova lista vazia, se ainda não existir
        }
        //nFEDETPRODdto.setId(null);
        listNFEDETPRODdto.getNFEDETPRODdto().add(nFEDETPRODdto);
        int lastIndex = getRowCount() - 1;
        fireTableRowsInserted(lastIndex, lastIndex);
        getSortedTableModel(listNFEDETPRODdto.getNFEDETPRODdto());
    }

    public void removeNFEDETPRODdto(int rowIndex) {
        listNFEDETPRODdto.getNFEDETPRODdto().remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void updateNFEDETPRODdto(int rowIndex, NFEDETPRODdto nFEDETPRODdto) {
        listNFEDETPRODdto.getNFEDETPRODdto().set(rowIndex, nFEDETPRODdto);
        getSortedTableModel(listNFEDETPRODdto.getNFEDETPRODdto());
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public void addListNFEDETPRODdto(List<NFEDETPRODdto> nFEDETPRODdto) {
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return;
        }
        int rowIndex = getRowCount();
        listNFEDETPRODdto.getNFEDETPRODdto().addAll(nFEDETPRODdto);
        getSortedTableModel(listNFEDETPRODdto.getNFEDETPRODdto());
        fireTableRowsInserted(rowIndex, rowIndex + nFEDETPRODdto.size());
    }

    public void clearListNFEDETPRODdto() {
        if (listNFEDETPRODdto.getNFEDETPRODdto() != null) {
            listNFEDETPRODdto.getNFEDETPRODdto().clear();
            fireTableDataChanged();
        }
    }

    public Double getDET_PROD_VLPR() {
        Double ValorTotal = 0.0;
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VPROD() != null) {
                ValorTotal
                        += (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM()
                        * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VPROD());
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public Double getDET_PROD_VALORBASEICMS() {
        Double ValorTotal = 0.0;
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_ICMS00_PICMS() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_ICMS00_PICMS() > 0.0) {
                ValorTotal
                        += (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM()
                        * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM());
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public Double getDET_PROD_VALORBASEIPI() {
        Double ValorTotal = 0.0;
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IPITRIB_PIPI() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IPITRIB_PIPI() > 0.0) {
                ValorTotal
                        += (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM()
                        * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM());
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public Double getDET_PROD_VALORBASEPIS() {
        Double ValorTotal = 0.0;
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PISALIQ_PPIS() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PISALIQ_PPIS() > 0.0) {
                ValorTotal
                        += (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM()
                        * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM());
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public Double getDET_PROD_VALORBASECOFINS() {
        Double ValorTotal = 0.0;
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_COFINSALIQ_PCOFINS() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_COFINSALIQ_PCOFINS() > 0.0) {
                ValorTotal
                        += (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM()
                        * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM());
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public Double getDET_PROD_VALORIPI() {
        double ValorTotal = 0.0;
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM() != null) {
                if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IPITRIB_PIPI() != null) {
                    ValorTotal += getRoundDouble(((listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM()
                            * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM())
                            * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IPITRIB_PIPI() / 100), 2);
                }
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public Double getDET_PROD_VALORPIS() {
        Double ValorTotal = 0.0;
        Double valorTotal = 0.0;
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM() != null) {
                if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PISALIQ_PPIS() != null) {
                    ValorTotal += getRoundDouble(((listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM()
                            * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM())
                            * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PISALIQ_PPIS() / 100), 2);
                }
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public Double getDET_PROD_VALORCOFINS() {
        Double ValorTotal = 0.0;
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM() != null) {
                if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_COFINSALIQ_PCOFINS() != null) {
                    ValorTotal += getRoundDouble(((listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM()
                            * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM())
                            * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_COFINSALIQ_PCOFINS() / 100), 2);
                }
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public Double getDET_PROD_PESOBRUTO() {
        Double ValorTotal = 0.0;
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM() != null) {
                if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_COFINSALIQ_PCOFINS() != null) {
                    ValorTotal += listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM();
                }
            }
        }
        return getRoundDouble(ValorTotal, 3);
    }

    public Double getDET_PROD_VALORNOTA() {
        Double ValorTotal = 0.0;
        Double valorProd = 0.0;
        Double valorIpi = 0.0;
        String ww = "";
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM() != null) {
                valorProd = (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM()
                        * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM());
                if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IPITRIB_PIPI() != null) {
                    valorIpi = ((listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM()
                            * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM())
                            * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IPITRIB_PIPI() / 100);
                }
                ValorTotal += getRoundDouble(valorProd + valorIpi, 2);
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public Double getDET_PROD_VALORICMS() {
        Double ValorTotal = 0.0;
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM() != null) {
                //ValorTotal
                //        += (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM()
                //        * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM());
                if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_ICMS00_PICMS() != null) {
                    ValorTotal += (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM()
                            * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM())
                            * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_ICMS00_PICMS() / 100;
                }
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public Double getTOT_VBCIBSCBS() {
        Double ValorTotal = 0.0;
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IBSUF_PIBSUF() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IBSUF_PIBSUF() > 0.0
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IBSMUN_PIBSMUN() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IBSMUN_PIBSMUN() > 0.0
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_CBS_PCBS() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_CBS_PCBS() > 0.0) {
                ValorTotal
                        += (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM()
                        * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM());
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public Double getTOT_UF_VIBS() {
        Double ValorTotal = 0.0;
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IBSUF_VIBSUF() != null) {
                ValorTotal
                        += (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IBSUF_VIBSUF());
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public Double getTOT_MUN_VIBS() {
        Double ValorTotal = 0.0;
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IBSMUN_VIBSMUN() != null) {
                ValorTotal
                        += (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IBSMUN_VIBSMUN());
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public Double getTOT_IS_VBCSEL() {
        Double ValorTotal = 0.0;
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IS_VBCIMPSEL() != null) {
                ValorTotal
                        += (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IS_VBCIMPSEL());
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public Double getTOT_IS_VIMPSEL() {
        Double ValorTotal = 0.0;
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IS_VIMPSEL() != null) {
                ValorTotal
                        += (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IS_VIMPSEL());
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public Double getTOT_CBS_VCBS() {
        Double ValorTotal = 0.0;
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_CBS_VCBS() != null) {
                ValorTotal
                        += (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_CBS_VCBS());
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public Double getTOT_IBSCBSIS_VALORNOTA() {
        Double ValorTotal = 0.0;
        Double valorProd = 0.0;
        Double valorIpi = 0.0;
        String ww = "";
        if (listNFEDETPRODdto.getNFEDETPRODdto() == null) {
            return ValorTotal;
        }
        for (int i = 0; i < listNFEDETPRODdto.getNFEDETPRODdto().size(); i++) {
            if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM() != null
                    && listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM() != null) {
                valorProd = (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM()
                        * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM());
                if (listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IPITRIB_PIPI() != null) {
                    valorIpi = ((listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_QCOM()
                            * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_PROD_VUNCOM())
                            * listNFEDETPRODdto.getNFEDETPRODdto().get(i).getDET_IPITRIB_PIPI() / 100);
                }
                ValorTotal += getRoundDouble(
                        valorProd
                        + valorIpi
                        + getTOT_UF_VIBS()
                        + getTOT_MUN_VIBS()
                        + getTOT_IS_VIMPSEL()
                        + getTOT_CBS_VCBS(), 2);
            }
        }
        return getRoundDouble(ValorTotal, 2);
    }

    public void getSortedTableModel(List<NFEDETPRODdto> dados) {
        if (dados == null) {
            return;
        }
        dados = dados.stream().sorted((a1, a2)
                -> a1.getDET_PROD_ITEM()
                        .compareTo(a2.getDET_PROD_ITEM()))
                .collect(Collectors.toList());
        listNFEDETPRODdto.setNFEDETPRODdto(dados);
    }
}
