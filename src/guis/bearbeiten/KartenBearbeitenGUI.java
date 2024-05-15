/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package guis.bearbeiten;

import static layout.TaskListWithIcon.iconPfad;

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
import java.sql.Date;
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
import layout.Borders;
import layout.Colors;
import layout.mylabels.GUILabel;
import layout.Schrift;
import layout.mytextfields.GUIDateTextField;
import layout.mytextfields.GUIIntegerTextField;
import layout.mytextfields.GUITextField;

public class KartenBearbeitenGUI extends AbstractGUI<KartenBearbeitenGUI> {
    private JLabel kartenIDLabel, erweiterungAbkuerzungLabel,
            pokemonNameLabel, energieTypLabel, ursprungNameLabel, kartenNummerLabel,
            seltenheitIDLabel, wertInEuroLabel, besonderheitLabel, datumWertEingabeLabel,
            nameZusatzLabel, trainerZusatzLabel, kartenNummerZusatzLabel;

    private JTextField kartenIDTextField, erweiterungAbkuerzungTextField, pokemonNameTextField,
            energieTypTextField, ursprungNameTextField, kartenNummerTextField, seltenheitIDTextField,
            wertInEuroTextField, besonderheitTextField, datumWertEingabeTextField,
            nameZusatzTextField, trainerZusatzTextField, kartenNummerZusatzTextField;

    private JButton hinzufuegenButton;

    // Code zum Einfuegen der Daten in die Datenbank
    Connection con = DatenbankVerbindung.connectDB(); // Stelle eine Verbindung zur Datenbank her

    public KartenBearbeitenGUI() {
        setTitle("GUI Karten bearbeiten");
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(iconPfad);
        setIconImage(icon.getImage());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);

        kartenIDLabel = new GUILabel("Karten-ID");
        kartenIDTextField = new GUIIntegerTextField();
        AddComponentsToPanel.addLabelAndTextField(panel, kartenIDLabel, kartenIDTextField, gbc, 0, 0);

        erweiterungAbkuerzungLabel = new GUILabel("Abkürzung der Erweiterung");
        erweiterungAbkuerzungTextField = new GUITextField();
        AddComponentsToPanel.addLabelAndTextField(panel, erweiterungAbkuerzungLabel, erweiterungAbkuerzungTextField, gbc, 0, 2);

        pokemonNameLabel = new GUILabel("Pokémon-Name");
        pokemonNameTextField = new GUITextField();
        AddComponentsToPanel.addLabelAndTextField(panel, pokemonNameLabel, pokemonNameTextField, gbc, 1, 0);

        energieTypLabel = new GUILabel("Energie-Typ");
        energieTypTextField = new GUITextField();
        AddComponentsToPanel.addLabelAndTextField(panel, energieTypLabel, energieTypTextField, gbc, 1, 2);

        ursprungNameLabel = new GUILabel("Ursprung des Pokémons");
        ursprungNameTextField = new GUITextField();
        AddComponentsToPanel.addLabelAndTextField(panel, ursprungNameLabel, ursprungNameTextField, gbc, 2, 0);

        kartenNummerLabel = new GUILabel("Kartennummer");
        kartenNummerTextField = new GUIIntegerTextField();
        AddComponentsToPanel.addLabelAndTextField(panel, kartenNummerLabel, kartenNummerTextField, gbc, 2, 2);

        seltenheitIDLabel = new JLabel("Seltenheit-ID");
        seltenheitIDLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        seltenheitIDTextField = new JTextField();
        seltenheitIDTextField.setPreferredSize(new Dimension(150, 30));
        seltenheitIDTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, seltenheitIDLabel, seltenheitIDTextField, gbc, 3, 0);

        besonderheitLabel = new JLabel("Besonderheit");
        besonderheitLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        besonderheitTextField = new JTextField();
        besonderheitTextField.setPreferredSize(new Dimension(150, 30));
        besonderheitTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, besonderheitLabel, besonderheitTextField, gbc, 3, 2);

        wertInEuroLabel = new JLabel("Wert der Karte in €");
        wertInEuroLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        wertInEuroTextField = new JTextField();
        wertInEuroTextField.setPreferredSize(new Dimension(150, 30));
        wertInEuroTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, wertInEuroLabel, wertInEuroTextField, gbc, 4, 0);

        datumWertEingabeLabel = new GUILabel("Datum der Werteingabe");
        datumWertEingabeTextField = new GUIDateTextField();
        AddComponentsToPanel.addLabelAndTextField(panel, datumWertEingabeLabel, datumWertEingabeTextField, gbc, 4, 2);

        nameZusatzLabel = new GUILabel("Zusatz zum Namen des Pokémons (z.B. 'V' oder 'V-Star')");
        nameZusatzTextField = new GUITextField();
        AddComponentsToPanel.addLabelAndTextField(panel, nameZusatzLabel, nameZusatzTextField, gbc, 5, 0);

        trainerZusatzLabel = new GUILabel("Zusatz zum Trainer (z.B. 'Item', 'Unterstützer')");
        trainerZusatzTextField = new GUITextField();
        AddComponentsToPanel.addLabelAndTextField(panel, trainerZusatzLabel, trainerZusatzTextField, gbc, 5, 2);


        kartenNummerZusatzLabel = new GUILabel("Zusatz zur Kartennummer bzw. nicht-regelmäßige Kartennummer");
        kartenNummerZusatzTextField = new GUITextField();
        AddComponentsToPanel.addLabelAndTextField(panel, kartenNummerZusatzLabel, kartenNummerZusatzTextField, gbc, 6, 0);

        hinzufuegenButton = new JButton("Änderungen speichern");
        hinzufuegenButton.setFont(new Font("Arial", Font.PLAIN, 22));
        gbc.gridwidth = 2;
        gbc.gridx = 3;
        gbc.gridy = 6;
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

        JButton zurueck = Buttons.buttonZurueckKarten(Schrift.zurueckButton());
        gbc.gridwidth = 2;
        gbc.gridx = 3;
        gbc.gridy = 7;

        JPanel filterPanel = new JPanel();
        filterPanel.setPreferredSize(new Dimension(250, 100));
        filterPanel.setBackground(Colors.JAVA_COLOR_TUERKIS);

        JButton hinzufuegen = Buttons.btnKartenHinzufuegen(Schrift.schriftartButtons());
        Borders.buttonBorder(hinzufuegen, Color.WHITE);
        filterPanel.add(hinzufuegen, gbc);

        panel.setBackground(Colors.JAVA_COLOR_TUERKIS);
        panel.add(zurueck, gbc);

        add(panel);
        add(Buttons.buttonAnzeigen(), BorderLayout.SOUTH);
        add(filterPanel, BorderLayout.NORTH);

        setLocationRelativeTo(null);
        setFocusable(true);
    }

    private void updateExistingDataInDatabase() {
        String erweiterungAbkuerzung = erweiterungAbkuerzungTextField.getText().trim();
        String kartenNummer = kartenNummerTextField.getText().trim();
        String kartenNummerZusatz = kartenNummerZusatzTextField.getText().trim();
        String pokemonName = pokemonNameTextField.getText().trim();
        String energieTyp = energieTypTextField.getText().trim();
        String ursprungName = ursprungNameTextField.getText().trim();
        String seltenheitID = seltenheitIDTextField.getText().trim();
        String wertInEuro = wertInEuroTextField.getText().trim();
        String besonderheit = besonderheitTextField.getText().trim();
        Date datumWertEingabe = Date.valueOf(datumWertEingabeTextField.getText().trim());
        String nameZusatz = nameZusatzTextField.getText().trim();
        nameZusatz = (nameZusatz.isEmpty()) ? null : nameZusatz;
        String trainerZusatz = trainerZusatzTextField.getText().trim();

        try {
            // Initialize the SQL statement
            StringBuilder sqlUpdate = new StringBuilder("UPDATE sammlung SET ");
            boolean isFieldAdded = false;

            // Add non-empty fields to the SQL statement
            if (!pokemonName.isEmpty()) {
                sqlUpdate.append("pokemon_name = ?, ");
                isFieldAdded = true;
            }
            if (!energieTyp.isEmpty()) {
                sqlUpdate.append("energie_typ = ?, ");
                isFieldAdded = true;
            }
            if (ursprungName != null && !ursprungName.isEmpty()) {
                sqlUpdate.append("ursprung_name = ?, ");
                isFieldAdded = true;
            }
            if (!seltenheitID.isEmpty()) {
                sqlUpdate.append("seltenheit_id = ?, ");
                isFieldAdded = true;
            }
            if (!wertInEuro.isEmpty()) {
                sqlUpdate.append("wert_in_euro = ?, ");
                isFieldAdded = true;
            }
            if (!besonderheit.isEmpty()) {
                sqlUpdate.append("besonderheit = ?, ");
                isFieldAdded = true;
            }
            if (datumWertEingabe != null) {
                sqlUpdate.append("wert_eingegeben_am = ?, ");
                isFieldAdded = true;
            }
            if (nameZusatz != null) {
                sqlUpdate.append("name_zusatz = ?, ");
                isFieldAdded = true;
            }
            if (trainerZusatz != null) {
                sqlUpdate.append("trainer_zusatz = ?, ");
                isFieldAdded = true;
            }

            // Remove the trailing comma and space
            if (isFieldAdded) {
                sqlUpdate.delete(sqlUpdate.length() - 2, sqlUpdate.length());
            }

            // Complete the SQL statement
            sqlUpdate.append(" WHERE erweiterung_abkuerzung = ? AND (karten_nr = ? OR kartennr_zusatz = ?)");
            PreparedStatement preparedStatementUpdate = con.prepareStatement(sqlUpdate.toString());

            // Set parameter values for non-empty fields
            int parameterIndex = 1;
            if (!pokemonName.isEmpty()) {
                preparedStatementUpdate.setString(parameterIndex++, pokemonName);
            }
            if (!energieTyp.isEmpty()) {
                preparedStatementUpdate.setString(parameterIndex++, energieTyp);
            }
            if (!ursprungName.isEmpty()) {
                preparedStatementUpdate.setString(parameterIndex++, ursprungName);
            }
            if (!seltenheitID.isEmpty()) {
                preparedStatementUpdate.setInt(parameterIndex++, Integer.parseInt(seltenheitID));
            }
            if (!wertInEuro.isEmpty()) {
                preparedStatementUpdate.setDouble(parameterIndex++, Double.parseDouble(wertInEuro));
            }
            if (!besonderheit.isEmpty()) {
                preparedStatementUpdate.setString(parameterIndex++, besonderheit);
            }
            if (datumWertEingabe != null) {
                preparedStatementUpdate.setDate(parameterIndex++, datumWertEingabe);
            }
            if (nameZusatz != null) {
                preparedStatementUpdate.setString(parameterIndex++, nameZusatz);
            }
            if (trainerZusatz != null) {
                preparedStatementUpdate.setString(parameterIndex++, trainerZusatz);
            }

            // Set the last parameters for the WHERE clause
            preparedStatementUpdate.setString(parameterIndex++, erweiterungAbkuerzung);
            preparedStatementUpdate.setInt(parameterIndex++, Integer.parseInt(kartenNummer));
            preparedStatementUpdate.setString(parameterIndex, kartenNummerZusatz);

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
        erweiterungAbkuerzungTextField.setText("");
        pokemonNameTextField.setText("");
        energieTypTextField.setText("");
        ursprungNameTextField.setText("");
        kartenNummerTextField.setText("");
        seltenheitIDTextField.setText("");
        wertInEuroTextField.setText("");
        besonderheitTextField.setText("");
        datumWertEingabeTextField.setText("");
        nameZusatzTextField.setText("");
        trainerZusatzTextField.setText("");
        kartenNummerZusatzTextField.setText("");
    }
}



