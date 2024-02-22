/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package guis;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import datenbank.DatenbankVerbindung;
import funktionen.AddComponentsToPanel;
import funktionen.Buttons;
import layout.Schrift;

public class ErweiterungenBearbeitenGUI extends AbstractGUI<ErweiterungenBearbeitenGUI> {

    // Code zum Einfuegen der Daten in die Datenbank
    Connection con = DatenbankVerbindung.connectDB(); // Stelle eine Verbindung zur Datenbank her
    private final JLabel editErweiterungIDLabel;
    private final JLabel erweiterungNameLabel;
    private final JLabel zyklusLabel;
    private final JLabel abkuerzungLabel;
    private final JLabel jahrLabel;
    private final JLabel anzahlKartenSammlungLabel;
    private final JLabel ordnerIDLabel;
    private final JTextField editErweiterungIDTextField;
    private final JTextField erweiterungNameTextField;
    private final JTextField zyklusTextField;
    private final JTextField abkuerzungTextField;
    private final JTextField jahrTextField;
    private final JTextField anzahlKartenSammlungTextField;
    private final JTextField ordnerIDTextField;
    private final JButton hinzufuegenButton;

    public ErweiterungenBearbeitenGUI() {
        setTitle("GUI Erweiterungen bearbeiten");
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6); // Abstand zwischen den Komponenten

        editErweiterungIDLabel = new JLabel("Erweiterung-ID");
        editErweiterungIDLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        editErweiterungIDTextField = new JTextField();
        editErweiterungIDTextField.setPreferredSize(new Dimension(150, 30));
        editErweiterungIDTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, editErweiterungIDLabel, editErweiterungIDTextField, gbc, 0, 0);

        erweiterungNameLabel = new JLabel("Name der Erweiterung");
        erweiterungNameLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        erweiterungNameTextField = new JTextField();
        erweiterungNameTextField.setPreferredSize(new Dimension(150, 30));
        erweiterungNameTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, erweiterungNameLabel, erweiterungNameTextField, gbc, 0, 2);

        zyklusLabel = new JLabel("Zyklus");
        zyklusLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        zyklusTextField = new JTextField();
        zyklusTextField.setPreferredSize(new Dimension(150, 30));
        zyklusTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, zyklusLabel, zyklusTextField, gbc, 1, 0);

        abkuerzungLabel = new JLabel("Abkürzung der Erweiterung");
        abkuerzungLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        abkuerzungTextField = new JTextField();
        abkuerzungTextField.setPreferredSize(new Dimension(150, 30));
        abkuerzungTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, abkuerzungLabel, abkuerzungTextField, gbc, 1, 2);

        jahrLabel = new JLabel("Jahr Herausgabe");
        jahrLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        jahrTextField = new JTextField();
        jahrTextField.setPreferredSize(new Dimension(150, 30));
        jahrTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, jahrLabel, jahrTextField, gbc, 2, 0);

        anzahlKartenSammlungLabel = new JLabel("Anzahl der Karten in Erweiterung");
        anzahlKartenSammlungLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        anzahlKartenSammlungTextField = new JTextField();
        anzahlKartenSammlungTextField.setPreferredSize(new Dimension(150, 30));
        anzahlKartenSammlungTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, anzahlKartenSammlungLabel, anzahlKartenSammlungTextField, gbc, 2, 2);


        ordnerIDLabel = new JLabel("Ordner-ID");
        ordnerIDLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        ordnerIDTextField = new JTextField();
        ordnerIDTextField.setPreferredSize(new Dimension(150, 30));
        ordnerIDTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, ordnerIDLabel, ordnerIDTextField, gbc, 3, 0);


        hinzufuegenButton = new JButton("Änderungen speichern");
        hinzufuegenButton.setFont(new Font("Arial", Font.PLAIN, 22));
        gbc.gridwidth = 2;
        gbc.gridx = 2;
        gbc.gridy = 3;
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

        JButton zurueck = Buttons.buttonZurueckErweiterungen(Schrift.zurueckButton());
        gbc.gridwidth = 2;
        gbc.gridx = 2;
        gbc.gridy = 5;

        panel.add(zurueck, gbc);
        panel.setBackground(Color.yellow);

        add(panel);
        add(Buttons.buttonAnzeigen(), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setFocusable(true);
    }

    private void updateExistingDataInDatabase() {
        String zyklus = zyklusTextField.getText().trim();
        String abkuerzung = abkuerzungTextField.getText().trim();
        String jahr = jahrTextField.getText().trim();
        String anzahlKartenSammlung = anzahlKartenSammlungTextField.getText().trim();
        String ordnerID = ordnerIDTextField.getText().trim();
        String editName = erweiterungNameTextField.getText().trim(); // Änderung hier

        try {
            // Initialize the SQL statement
            StringBuilder sqlUpdate = new StringBuilder("UPDATE erweiterungen SET ");
            boolean isFieldAdded = false;

            // Add non-empty fields to the SQL statement

            if (!zyklus.isEmpty()) {
                sqlUpdate.append("zyklus = ?, ");
                isFieldAdded = true;
            }
            if (!abkuerzung.isEmpty()) {
                sqlUpdate.append("abkuerzung = ?, ");
                isFieldAdded = true;
            }
            if (!jahr.isEmpty()) {
                sqlUpdate.append("jahr = ?, ");
                isFieldAdded = true;
            }
            if (!anzahlKartenSammlung.isEmpty()) {
                sqlUpdate.append("anzahl_karten_sammlung = ?, ");
                isFieldAdded = true;
            }
            if (!ordnerID.isEmpty()) {
                sqlUpdate.append("ordner_id = ?, ");
                isFieldAdded = true;
            }

            // Remove the trailing comma and space
            if (isFieldAdded) {
                sqlUpdate.delete(sqlUpdate.length() - 2, sqlUpdate.length());
            }

            // Complete the SQL statement
            sqlUpdate.append(" WHERE erweiterung_name = ?"); // Änderung hier
            PreparedStatement preparedStatementUpdate = con.prepareStatement(sqlUpdate.toString());

            // Set parameter values for non-empty fields
            int parameterIndex = 1;

            if (!zyklus.isEmpty()) {
                preparedStatementUpdate.setString(parameterIndex++, zyklus);
            }
            if (!abkuerzung.isEmpty()) {
                preparedStatementUpdate.setString(parameterIndex++, abkuerzung);
            }
            if (!jahr.isEmpty()) {
                preparedStatementUpdate.setInt(parameterIndex++, Integer.parseInt(jahr));
            }
            if (!anzahlKartenSammlung.isEmpty()) {
                preparedStatementUpdate.setInt(parameterIndex++, Integer.parseInt(anzahlKartenSammlung));
            }
            if (!ordnerID.isEmpty()) {
                preparedStatementUpdate.setInt(parameterIndex++, Integer.parseInt(ordnerID));
            }

            // Set the last parameter for the WHERE clause
            preparedStatementUpdate.setString(parameterIndex, editName); // Änderung hier

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
        erweiterungNameTextField.setText("");
        zyklusTextField.setText("");
        abkuerzungTextField.setText("");
        jahrTextField.setText("");
        anzahlKartenSammlungTextField.setText("");
        ordnerIDTextField.setText("");
        editErweiterungIDTextField.setText("");
    }
}
