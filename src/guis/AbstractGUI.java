/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package guis;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public abstract class AbstractGUI<T extends JFrame> extends JFrame {

    /**
     * Diese Methode erledigt das Refreshen einer Seite nach Eingabe eines Datensatzes.
     */
    protected void reloadPage() {
        SwingUtilities.invokeLater(() -> {
            setVisible(false);
            T gui;
            try {
                gui = (T) this.getClass().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            gui.setVisible(true);
        });
    }

}
