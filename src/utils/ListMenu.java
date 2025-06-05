package utils;

import components.MenuItem;
import controller.EventMenuSelected;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;
import model.MMenu;

public class ListMenu<E extends Object> extends JList<E> {
    
    private final DefaultListModel model;
    private int selectedIndex = 0;
    private int overIndex =-1;
    
    private EventMenuSelected event;
    
    public void addEventMenuSelected (EventMenuSelected event){
        this.event = event;
    }
    
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
                               if(event != null){
                                   event.selected(index);
                               }
                        }
                        repaint();
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                overIndex = -1;
                repaint();
            }
            
            
            
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                if(index != overIndex){
                    Object o = model.getElementAt(index);
                    if(o instanceof MMenu){
                        MMenu menu = (MMenu) o;
                        if(menu.getType()== MMenu.MenuType.MENU){
                            overIndex = index;
                        } else {
                            overIndex = -1;
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
                item.setOver(overIndex == index);
                return item;
            }
            
        };
    }
    
    public void addItem(MMenu data){
        model.addElement(data);
    }
}
