/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package funktionen;import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
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

    public static void addLabel(JPanel panel, JLabel label, GridBagConstraints gbc, int row, int column) {
        gbc.gridx = column;
        gbc.gridy = row;
        panel.add(label, gbc);
    }

    public static void addLabelAndComboBox(JPanel panel, JLabel label, JComboBox comboBox, GridBagConstraints gbc, int row, int column) {
        gbc.gridx = column;
        gbc.gridy = row;
        gbc.insets = new Insets(0, 5, 0, 5);
        panel.add(label, gbc);

        gbc.gridx = column + 1;
        gbc.gridy = row;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(comboBox, gbc);
    }


    public static void addButtonToPanel(JPanel panel, JButton button, GridBagConstraints gbc, int row, int column) {
        gbc.gridx = column;
        gbc.gridy = row;
        panel.add(button, gbc);
    }
}