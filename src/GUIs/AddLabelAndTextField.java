/*
 * Copyright (c) 2023.
 * Lieke Schors
 */

package GUIs;import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Methode, um Label und Textfelder in ein Panel einzufuegen
 */
public class AddLabelAndTextField {
    public static void addLabelAndTextField(JPanel panel, JLabel label, JTextField textField, GridBagConstraints gbc, int row, int column) {
        gbc.gridx = column;
        gbc.gridy = row;
        panel.add(label, gbc);

        gbc.gridx = column + 1;
        gbc.gridy = row;
        panel.add(textField, gbc);
    }
}