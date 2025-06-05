package utils;

import components.ActionBtnTableDelete;
import components.ActionBtnTableEdit;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;

public class IncomeCategoryTable extends JTable {

    public IncomeCategoryTable() {
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
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(value + "");
                return header;
            }
        });

        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                if (column == 2 && value instanceof String) {
                    JPanel panel = new JPanel();
                    panel.setOpaque(true);
                    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
                    panel.setBackground(isSelected ? new Color(255, 255, 255, 50) : new Color(51, 51, 51));

                    JPanel colorBox = new JPanel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            Graphics2D g2 = (Graphics2D) g;
                            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            Color fillColor;
                            try {
                                fillColor = Color.decode((String) value);
                            } catch (NumberFormatException e) {
                                fillColor = Color.GRAY;
                            }
                            g2.setColor(fillColor);
                            g2.fillOval(0, 0, getWidth(), getHeight());
                        }
                    };
                    colorBox.setOpaque(false);
                    colorBox.setPreferredSize(new Dimension(15, 15));
                    colorBox.setMaximumSize(new Dimension(15, 15));

                    JLabel label = new JLabel((String) value);
                    label.setForeground(isSelected ? Color.WHITE : new Color(204, 204, 204));
                    label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

                    panel.add(colorBox);
                    panel.add(label);

                    return panel;
                } else if (column != 3 && column != 4) {
                    Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    com.setBackground(isSelected ? new Color(255, 255, 255, 50) : new Color(51, 51, 51));
                    return com;
                } else if (column == 3) {
                    ActionBtnTableEdit cell = new ActionBtnTableEdit();
                    cell.setBackground(isSelected ? new Color(255, 255, 255, 50) : new Color(51, 51, 51));
                    cell.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
                    return cell;
                } else {
                    ActionBtnTableDelete cell = new ActionBtnTableDelete();
                    cell.setBackground(isSelected ? new Color(255, 255, 255, 50) : new Color(51, 51, 51));
                    cell.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
                    return cell;
                }
            }
        });

        
    }

    

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
}
