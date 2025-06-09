package utils;

import components.ActionBtnTableDelete;
import components.ActionBtnTableEdit;
import components.CellStatus;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TransactionsTable extends JTable {

    public TransactionsTable() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setOpaque(false);
        setBackground(new Color(51, 51, 51));
        setRowHeight(40);
        setBorder(null);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(value + "");
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (column == 5) {
                    StatusType type = (StatusType) value;
                    CellStatus cell = new CellStatus(type);
                    cell.setBackground(isSelected ? new Color(255, 255, 255, 50) : new Color(51, 51, 51));
                    cell.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
                    return cell;
                    
                }  else if (column == 6) {
                    ActionBtnTableEdit cell = new ActionBtnTableEdit();
                    cell.setBackground(isSelected ? new Color(255, 255, 255, 50) : new Color(51, 51, 51));
                    cell.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
                    return cell;
                } else if(column == 7) {
                    ActionBtnTableDelete cell = new ActionBtnTableDelete();
                    cell.setBackground(isSelected ? new Color(255, 255, 255, 50) : new Color(51, 51, 51));
                    cell.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
                    return cell;
                } else  {
                    Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    com.setBackground(isSelected ? new Color(255, 255, 255, 50) : new Color(51, 51, 51));
                    return com;
                }
            }
        });

    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
}
