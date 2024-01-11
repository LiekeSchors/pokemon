/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package funktionen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ButtonActionListener<T extends JFrame> implements ActionListener {
    private final JButton button;
    private final Class<T> clazz;

    /**
     * Diese Methode implementiert den ActionListener bei Buttons aus @link Buttons
     * @param button
     * @param clazz
     */
    public ButtonActionListener(JButton button, Class<T> clazz) {
        this.button = button;
        this.clazz = clazz;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        t.setVisible(true);
        OffenesFensterSchliessen.aktuellesFensterSchliessen(button);
    }
}


