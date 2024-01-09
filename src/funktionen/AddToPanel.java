/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package funktionen;import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Methode, um Label und Textfelder in ein Panel einzufuegen
 */
public class AddToPanel {
    public static void addToPanel(JPanel panel, JButton button, GridBagConstraints gbc, int row, int column) {
        gbc.gridx = column;
        gbc.gridy = row;
        panel.add(button, gbc);
    }
}
