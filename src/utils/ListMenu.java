package utils;

import components.MenuItem;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import model.MMenu;

public class ListMenu<E extends Object> extends JList<E> {
    
    private final DefaultListModel model;
    private int selectedIndex = -1;
    
    public ListMenu() {
        model = new DefaultListModel();
        setModel(model);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)) {
                    int index = locationToIndex(e.getPoint());
                    Object o = model.getElementAt(index);
                    if(o instanceof MMenu) {
                        MMenu menu = (MMenu) o;
                        if(menu.getType() == MMenu.MenuType.MENU){
                            selectedIndex = index;
                        } else {
                            selectedIndex = index;
                        }
                        repaint();
                    }
                }
            }
            
        });
        
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean selected, boolean focus) {
                MMenu data;
                if(value instanceof MMenu) {
                    data = (MMenu) value;
                } else {
                    data = new MMenu("", value + "", MMenu.MenuType.EMPTY);
                }
                
                MenuItem item = new MenuItem(data);
                item.setSelected(selectedIndex == index);
                return item;
            }
            
        };
    }
    
    public void addItem(MMenu data){
        model.addElement(data);
    }
}
