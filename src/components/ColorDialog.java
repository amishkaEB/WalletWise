package components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;

public class ColorDialog {
    public static Color showDarkColorDialog(Component parent, Color initialColor) {
        JColorChooser chooser = new JColorChooser(initialColor);
        applyDark(chooser);
        JDialog dialog = JColorChooser.createDialog(parent, "Pick a Color", true, chooser, null, null);
        applyDark(dialog.getContentPane());
        dialog.setVisible(true);
        return chooser.getColor();
    }

    private static void applyDark(Component c) {
        if (c instanceof JComponent jc) {
            jc.setBackground(new Color(30, 30, 30));
            jc.setForeground(Color.WHITE);
        }
        if (c instanceof Container container) {
            for (Component child : container.getComponents()) {
                applyDark(child);
            }
        }
    }
}
