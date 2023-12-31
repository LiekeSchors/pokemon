/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package guis;

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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import datenbank.DatenbankVerbindung;

public class KartenBearbeitenGUI extends JFrame {
    private JLabel kartenIDLabel, erweiterungAbkuerzungLabel,
            pokemonNameLabel, energieTypLabel, ursprungNameLabel, kartenNummerLabel,
            seltenheitIDLabel, wertInEuroLabel, besonderheitIDLabel, datumWertEingabeLabel,
            nameZusatzLabel, trainerZusatzLabel, kartenNummerZusatzLabel;

    private JTextField kartenIDTextField, erweiterungAbkuerzungTextField, pokemonNameTextField,
            energieTypTextField, ursprungNameTextField, kartenNummerTextField, seltenheitIDTextField,
            wertInEuroTextField, besonderheitIDTextField, datumWertEingabeTextField,
            nameZusatzTextField, trainerZusatzTextField, kartenNummerZusatzTextField;

    private JButton hinzufuegenButton;

    // Code zum Einfuegen der Daten in die Datenbank
    Connection con = DatenbankVerbindung.connectDB(); // Stelle eine Verbindung zur Datenbank her

    public KartenBearbeitenGUI() {
        setTitle("GUI Karten bearbeiten");
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6); // Abstand zwischen den Komponenten

        kartenIDLabel = new JLabel("Karten-ID");
        kartenIDLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        kartenIDTextField = new JTextField();
        kartenIDTextField.setPreferredSize(new Dimension(150, 30));
        kartenIDTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, kartenIDLabel, kartenIDTextField, gbc, 0, 0);

        erweiterungAbkuerzungLabel = new JLabel("Abkürzung der Erweiterung");
        erweiterungAbkuerzungLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        erweiterungAbkuerzungTextField = new JTextField();
        erweiterungAbkuerzungTextField.setPreferredSize(new Dimension(150, 30));
        erweiterungAbkuerzungTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, erweiterungAbkuerzungLabel, erweiterungAbkuerzungTextField, gbc, 0, 2);

        pokemonNameLabel = new JLabel("Pokémon-Name");
        pokemonNameLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        pokemonNameTextField = new JTextField();
        pokemonNameTextField.setPreferredSize(new Dimension(150, 30));
        pokemonNameTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, pokemonNameLabel, pokemonNameTextField, gbc, 1, 0);

        energieTypLabel = new JLabel("Energie-Typ");
        energieTypLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        energieTypTextField = new JTextField();
        energieTypTextField.setPreferredSize(new Dimension(150, 30));
        energieTypTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, energieTypLabel, energieTypTextField, gbc, 1, 2);

        ursprungNameLabel = new JLabel("Ursprung des Pokémons");
        ursprungNameLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        ursprungNameTextField = new JTextField();
        ursprungNameTextField.setPreferredSize(new Dimension(150, 30));
        ursprungNameTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, ursprungNameLabel, ursprungNameTextField, gbc, 2, 0);

        kartenNummerLabel = new JLabel("Kartennummer");
        kartenNummerLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        kartenNummerTextField = new JTextField();
        kartenNummerTextField.setPreferredSize(new Dimension(150, 30));
        kartenNummerTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, kartenNummerLabel, kartenNummerTextField, gbc, 2, 2);

        seltenheitIDLabel = new JLabel("Seltenheit-ID");
        seltenheitIDLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        seltenheitIDTextField = new JTextField();
        seltenheitIDTextField.setPreferredSize(new Dimension(150, 30));
        seltenheitIDTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, seltenheitIDLabel, seltenheitIDTextField, gbc, 3, 0);

        besonderheitIDLabel = new JLabel("Besonderheit-ID");
        besonderheitIDLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        besonderheitIDTextField = new JTextField();
        besonderheitIDTextField.setPreferredSize(new Dimension(150, 30));
        besonderheitIDTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, besonderheitIDLabel, besonderheitIDTextField, gbc, 3, 2);

        wertInEuroLabel = new JLabel("Wert der Karte in €");
        wertInEuroLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        wertInEuroTextField = new JTextField();
        wertInEuroTextField.setPreferredSize(new Dimension(150, 30));
        wertInEuroTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, wertInEuroLabel, wertInEuroTextField, gbc, 4, 0);

        datumWertEingabeLabel = new JLabel("Datum der Werteingabe");
        datumWertEingabeLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        datumWertEingabeTextField = new JTextField();
        datumWertEingabeTextField.setPreferredSize(new Dimension(150, 30));
        datumWertEingabeTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, datumWertEingabeLabel, datumWertEingabeTextField, gbc, 4, 2);


        nameZusatzLabel = new JLabel("Zusatz zum Namen des Pokémons (z.B. 'V' oder 'V-Star')");
        nameZusatzLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        nameZusatzTextField = new JTextField();
        nameZusatzTextField.setPreferredSize(new Dimension(150, 30));
        nameZusatzTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, nameZusatzLabel, nameZusatzTextField, gbc, 5, 0);


        trainerZusatzLabel = new JLabel("Zusatz zum Trainer (z.B. 'Item', 'Unterstützer')");
        trainerZusatzLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        trainerZusatzTextField = new JTextField();
        trainerZusatzTextField.setPreferredSize(new Dimension(150, 30));
        trainerZusatzTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, trainerZusatzLabel, trainerZusatzTextField, gbc, 5, 2);


        kartenNummerZusatzLabel = new JLabel("Zusatz zur Kartennummer bzw. nicht-regelmäßige Kartennummer");
        kartenNummerZusatzLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        kartenNummerZusatzTextField = new JTextField();
        kartenNummerZusatzTextField.setPreferredSize(new Dimension(150, 30));
        kartenNummerZusatzTextField.setFont(new Font("Arial", Font.PLAIN, 22));
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

        add(panel);

        JButton btnBack = new JButton("Zurück");
        btnBack.setFont(new Font("Arial", Font.PLAIN, 22));
        btnBack.addActionListener(e -> {
            PokemonKartenBearbeiten pokemonKartenBearbeiten = new PokemonKartenBearbeiten();
            pokemonKartenBearbeiten.setVisible(true);
            setVisible(false);
        });

        JPanel navPanel = new JPanel();
        navPanel.add(btnBack);
        add(navPanel, BorderLayout.SOUTH);

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
        String besonderheitID = besonderheitIDTextField.getText().trim();
        String datumWertEingabe = datumWertEingabeTextField.getText().trim();
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
            if (!besonderheitID.isEmpty()) {
                sqlUpdate.append("besonderheit_id = ?, ");
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
            if (!besonderheitID.isEmpty()) {
                preparedStatementUpdate.setInt(parameterIndex++, Integer.parseInt(besonderheitID));
            }
            if (datumWertEingabe != null) {
                preparedStatementUpdate.setString(parameterIndex++, datumWertEingabe);
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
        besonderheitIDTextField.setText("");
        datumWertEingabeTextField.setText("");
        nameZusatzTextField.setText("");
        trainerZusatzTextField.setText("");
        kartenNummerZusatzTextField.setText("");
    }

    private void reloadPage() {
        SwingUtilities.invokeLater(() -> {
            KartenBearbeitenGUI gui = new KartenBearbeitenGUI();
            gui.setVisible(true);
            dispose();
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KartenBearbeitenGUI gui = new KartenBearbeitenGUI();
            gui.setVisible(true);
        });
    }
}



