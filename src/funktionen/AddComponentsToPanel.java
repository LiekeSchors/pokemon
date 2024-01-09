/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package funktionen;import java.awt.GridBagConstraints;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Methode, um Label und Textfelder in ein Panel einzufuegen
 */
public class AddComponentsToPanel {
    public static void addLabelAndTextField(JPanel panel, JLabel label, JTextField textField, GridBagConstraints gbc, int row, int column) {
        gbc.gridx = column;
        gbc.gridy = row;
        panel.add(label, gbc);

        gbc.gridx = column + 1;
        gbc.gridy = row;
        panel.add(textField, gbc);
    }

    public static void addLabelAndComboBox(JPanel panel, JLabel label, JComboBox comboBox, GridBagConstraints gbc, int row, int column) {
        gbc.gridx = column;
        gbc.gridy = row;
        panel.add(label, gbc);

        gbc.gridx = column + 1;
        gbc.gridy = row;
        panel.add(comboBox, gbc);
    }
}