/*
 * Copyright (c) 2024.
 * Lieke Schors
 */
/*
package layout;

import static java.awt.Frame.MAXIMIZED_BOTH;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public abstract class ViewEinstellungen<T extends JFrame> {
    public static <T extends JFrame> void viewEinstellungen(T frame) {
        T fenster = null;
        try {
            fenster = frame.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));

        fenster.setVisible(true);
    }
}
*/