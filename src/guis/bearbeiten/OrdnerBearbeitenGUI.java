/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package guis.bearbeiten;

import static layout.TaskListWithIcon.iconPfad;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import datenbank.DatenbankVerbindung;
import funktionen.AddComponentsToPanel;
import funktionen.Buttons;
import guis.AbstractGUI;
import layout.Colors;
import layout.GUILabel;
import layout.Schrift;
import layout.guitextfield.GUIIntegerTextField;
import layout.guitextfield.GUITextField;

public class OrdnerBearbeitenGUI extends AbstractGUI<OrdnerBearbeitenGUI> {
    private JLabel ordnerIDLabel, zyklusLabel, farbeLabel;
    private JTextField ordnerIDTextField, zyklusTextField, farbeTextField;
    private JButton hinzufuegenButton;

    // Code zum Einfuegen der Daten in die Datenbank
    Connection con = DatenbankVerbindung.connectDB(); // Stelle eine Verbindung zur Datenbank her

    public OrdnerBearbeitenGUI() {
        setTitle("GUI Ordner bearbeiten");
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(iconPfad);
        setIconImage(icon.getImage());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6); // Abstand zwischen den Komponenten

        ordnerIDLabel = new GUILabel("Ordner-ID");
        ordnerIDTextField = new GUIIntegerTextField();
        AddComponentsToPanel.addLabelAndTextField(panel, ordnerIDLabel, ordnerIDTextField, gbc, 0, 0);

        zyklusLabel = new GUILabel("Zyklus");
        zyklusTextField = new GUITextField();
        AddComponentsToPanel.addLabelAndTextField(panel, zyklusLabel, zyklusTextField, gbc, 1, 0);

        farbeLabel = new GUILabel("Farbe des Ordners");
        farbeTextField = new GUITextField();
        AddComponentsToPanel.addLabelAndTextField(panel, farbeLabel, farbeTextField, gbc, 2, 0);

        hinzufuegenButton = new JButton("Änderungen speichern");
        hinzufuegenButton.setFont(new Font("Arial", Font.PLAIN, 22));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(hinzufuegenButton, gbc);

        // Button zum hinzufuegen einer Karte und gleichzeitigem Neuladen mit Enter
        hinzufuegenButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
        hinzufuegenButton.getActionMap().put("enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateExistingDataInDatabase();
                reloadPage();
            }
        });

        JButton zurueck = Buttons.buttonZurueckOrdner(Schrift.zurueckButton());
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 5;

        panel.add(zurueck, gbc);
        panel.setBackground(Colors.JAVA_COLOR_ORANGE);

        add(panel);
        add(Buttons.buttonAnzeigen(), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setFocusable(true);
    }

    private void updateExistingDataInDatabase() {
        String zyklus = zyklusTextField.getText().trim();
        String farbe = farbeTextField.getText().trim();
        int ordnerID = Integer.parseInt(ordnerIDTextField.getText().trim());

        try {
            // Initialize the SQL statement
            StringBuilder sqlUpdate = new StringBuilder("UPDATE ordner SET ");
            boolean isFieldAdded = false;

            // Add non-empty fields to the SQL statement

            if (!zyklus.isEmpty()) {
                sqlUpdate.append("zyklus = ?, ");
                isFieldAdded = true;
            }
            if (!farbe.isEmpty()) {
                sqlUpdate.append("farbe = ?, ");
                isFieldAdded = true;
            }

            // Remove the trailing comma and space
            if (isFieldAdded) {
                sqlUpdate.delete(sqlUpdate.length() - 2, sqlUpdate.length());
            }

            // Complete the SQL statement
            sqlUpdate.append(" WHERE id = ?"); // Änderung hier
            PreparedStatement preparedStatementUpdate = con.prepareStatement(sqlUpdate.toString());

            // Set parameter values for non-empty fields
            int parameterIndex = 1;

            if (!zyklus.isEmpty()) {
                preparedStatementUpdate.setString(parameterIndex++, zyklus);
            }
            if (!farbe.isEmpty()) {
                preparedStatementUpdate.setString(parameterIndex++, farbe);
            }

            // Set the last parameter for the WHERE clause
            preparedStatementUpdate.setInt(parameterIndex, ordnerID); // Änderung hier

            // Execute the update
            preparedStatementUpdate.executeUpdate();

            // Clear fields
            clearFields();

            preparedStatementUpdate.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        zyklusTextField.setText("");
        farbeTextField.setText("");
        ordnerIDTextField.setText("");
    }
}