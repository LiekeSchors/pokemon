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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import datenbank.DatenbankVerbindung;
import datenbank.GenerateNextID;
import funktionen.AddComponentsToPanel;
import funktionen.Buttons;
import funktionen.ValuesToStringDB;
import layout.Borders;
import layout.Colors;
import layout.Schrift;

public class KartenHinzufuegenGUI extends AbstractGUI<KartenHinzufuegenGUI> {
    private JLabel kartenIDLabel, erweiterungAbkuerzungLabel,
            pokemonNameLabel, energieTypLabel, ursprungNameLabel, kartenNummerLabel,
            seltenheitIDLabel, wertInEuroLabel, besonderheitIDLabel, datumWertEingabeLabel,
            nameZusatzLabel, trainerZusatzLabel, kartenNummerZusatzLabel;

    private JTextField kartenIDTextField, pokemonNameTextField, ursprungNameTextField, kartenNummerTextField,
            wertInEuroTextField, datumWertEingabeTextField, kartenNummerZusatzTextField;

    private JComboBox<String> abkuerzungErweiterungComboBox, energieTypComboBox, trainerZusatzComboBox, nameZusatzComboBox,
            seltenheitSymbolCombobox, besonderheitComboBox;

    private JButton hinzufuegenButton;

    Connection con = DatenbankVerbindung.connectDB(); // Stelle eine Verbindung zur Datenbank her

    public KartenHinzufuegenGUI() {
        setTitle("GUI Karten hinzufügen");
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

        String[] abkuerzungErweiterungComboboxStringArray = ValuesToStringDB.getEindeutigeErweiterungAbkuerzung(false);
        abkuerzungErweiterungComboBox = new JComboBox<>(abkuerzungErweiterungComboboxStringArray);
        abkuerzungErweiterungComboBox.setFont(Schrift.normal());

        erweiterungAbkuerzungLabel = new JLabel("Abkürzung der Erweiterung");
        erweiterungAbkuerzungLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        abkuerzungErweiterungComboBox.setPreferredSize(new Dimension(150, 30));
        AddComponentsToPanel.addLabelAndComboBox(panel, erweiterungAbkuerzungLabel, abkuerzungErweiterungComboBox, gbc, 0, 2);

        pokemonNameLabel = new JLabel("Pokémon-Name");
        pokemonNameLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        pokemonNameTextField = new JTextField();
        pokemonNameTextField.setPreferredSize(new Dimension(150, 30));
        pokemonNameTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, pokemonNameLabel, pokemonNameTextField, gbc, 1, 0);

        String[] energieTyp = ValuesToStringDB.getEnergieTyp(false);
        energieTypComboBox = new JComboBox<>(energieTyp);
        energieTypComboBox.setFont(Schrift.normal());

        energieTypLabel = new JLabel("Energie-Typ");
        energieTypLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        energieTypComboBox.setPreferredSize(new Dimension(150, 30));
        AddComponentsToPanel.addLabelAndComboBox(panel, energieTypLabel, energieTypComboBox, gbc, 1, 2);

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

        String[] seltenheitCombobox = ValuesToStringDB.getSymbolSeltenheit();
        seltenheitSymbolCombobox = new JComboBox<>(seltenheitCombobox);
        seltenheitSymbolCombobox.setFont(Schrift.farbigeUnicodeSymbole());

        seltenheitIDLabel = new JLabel("Seltenheit-ID");
        seltenheitIDLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        seltenheitSymbolCombobox.setPreferredSize(new Dimension(150, 30));
        AddComponentsToPanel.addLabelAndComboBox(panel, seltenheitIDLabel, seltenheitSymbolCombobox, gbc, 3, 0);


        String[] besonderheitBeschreibung = ValuesToStringDB.getBeschreibungBesonderheit();
        besonderheitComboBox = new JComboBox<>(besonderheitBeschreibung);
        besonderheitComboBox.setFont(Schrift.farbigeUnicodeSymbole());

        besonderheitIDLabel = new JLabel("Besonderheit-ID");
        besonderheitIDLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        besonderheitComboBox.setPreferredSize(new Dimension(150, 30));
        AddComponentsToPanel.addLabelAndComboBox(panel, besonderheitIDLabel, besonderheitComboBox, gbc, 3, 2);

        wertInEuroLabel = new JLabel("Wert der Karte in €");
        wertInEuroLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        wertInEuroTextField = new JTextField();
        wertInEuroTextField.setPreferredSize(new Dimension(150, 30));
        wertInEuroTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, wertInEuroLabel, wertInEuroTextField, gbc, 4, 0);


        Date datum = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        datumWertEingabeLabel = new JLabel("Datum der Werteingabe");
        datumWertEingabeLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        datumWertEingabeTextField = new JTextField();
        datumWertEingabeTextField.setText(dateFormat.format(datum));
        datumWertEingabeTextField.setPreferredSize(new Dimension(150, 30));
        datumWertEingabeTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, datumWertEingabeLabel, datumWertEingabeTextField, gbc, 4, 2);


        String[] nameZusatz = ValuesToStringDB.getNameZusatz();
        nameZusatzComboBox = new JComboBox<>(nameZusatz);
        nameZusatzComboBox.setFont(Schrift.normal());

        nameZusatzLabel = new JLabel("Zusatz zum Namen des Pokémons (z.B. 'V' oder 'V-Star')");
        nameZusatzLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        nameZusatzComboBox.setPreferredSize(new Dimension(150, 30));
        nameZusatzComboBox.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndComboBox(panel, nameZusatzLabel, nameZusatzComboBox, gbc, 5, 0);


        String[] trainerZusatz = ValuesToStringDB.getTrainerZusatz();
        trainerZusatzComboBox = new JComboBox<>(trainerZusatz);
        trainerZusatzComboBox.setFont(Schrift.normal());

        trainerZusatzLabel = new JLabel("Zusatz zum Trainer (z.B. 'Item', 'Unterstützer')");
        trainerZusatzLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        trainerZusatzComboBox.setPreferredSize(new Dimension(150, 30));
        trainerZusatzComboBox.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndComboBox(panel, trainerZusatzLabel, trainerZusatzComboBox, gbc, 5, 2);

        kartenNummerZusatzLabel = new JLabel("Zusatz zur Kartennummer bzw. nicht-regelmäßige Kartennummer");
        kartenNummerZusatzLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        kartenNummerZusatzTextField = new JTextField();
        kartenNummerZusatzTextField.setPreferredSize(new Dimension(150, 30));
        kartenNummerZusatzTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, kartenNummerZusatzLabel, kartenNummerZusatzTextField, gbc, 6, 0);

        hinzufuegenButton = new JButton("Karte hinzufügen");
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
                updateDataInDatabase();
                reloadPage();
            }
        });

        GenerateNextID.generateNextID(con, "sammlung", "karten_id", kartenIDTextField);

        JButton zurueck = Buttons.buttonZurueckKarten(Schrift.zurueckButton());
        gbc.gridwidth = 2;
        gbc.gridx = 3;
        gbc.gridy = 7;

        JPanel filterPanel = new JPanel();
        filterPanel.setPreferredSize(new Dimension(250, 100));
        filterPanel.setBackground(Colors.JAVA_COLOR_TUERKIS);

        JButton bearbeiten = Buttons.btnKartenBearbeiten(Schrift.schriftartButtons());
        Borders.buttonBorder(bearbeiten, Color.WHITE);
        filterPanel.add(bearbeiten, gbc);

        panel.setBackground(Colors.JAVA_COLOR_TUERKIS);
        panel.add(zurueck, gbc);

        add(panel);
        add(Buttons.buttonAnzeigen(), BorderLayout.SOUTH);
        add(filterPanel, BorderLayout.NORTH);

        setLocationRelativeTo(null);
        setFocusable(true);
    }

    private void updateDataInDatabase() {
        String erweiterungAbkuerzung = (String) abkuerzungErweiterungComboBox.getSelectedItem();
        String pokemonName = pokemonNameTextField.getText();
        String energieTyp = (String) energieTypComboBox.getSelectedItem();
        String ursprungName = ursprungNameTextField.getText();
        ursprungName = (ursprungName.isEmpty()) ? null : ursprungName;
        String kartenNummer = kartenNummerTextField.getText();
        String symbolSeltenheit = (String) seltenheitSymbolCombobox.getSelectedItem();
        String wertInEuro = wertInEuroTextField.getText();
        String besonderheitID = (String) besonderheitComboBox.getSelectedItem();
        String datumWertEingabe = datumWertEingabeTextField.getText();
        datumWertEingabe = (datumWertEingabe.isEmpty()) ? null : datumWertEingabe;
        String nameZusatz = (String) nameZusatzComboBox.getSelectedItem();
        nameZusatz = (nameZusatz.isEmpty()) ? null : nameZusatz;
        String trainerZusatz = (String) trainerZusatzComboBox.getSelectedItem();
        trainerZusatz = (trainerZusatz.isEmpty()) ? " " : trainerZusatz;
        String kartenNummerZusatz = kartenNummerZusatzTextField.getText();
        kartenNummerZusatz = (kartenNummerZusatz.isEmpty()) ? null : kartenNummerZusatz;


        try {
            // Einfuegen der Daten mit automatisch inkrementierter ID
            String sqlInsert = "INSERT INTO sammlung (erweiterung_abkuerzung, pokemon_name, energie_typ, ursprung_name, karten_nr, seltenheit_id, wert_in_euro, besonderheit_id, wert_eingegeben_am, name_zusatz, trainer_zusatz, kartennr_zusatz) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatementInsert = con.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatementInsert.setString(1, erweiterungAbkuerzung);
            preparedStatementInsert.setString(2, pokemonName);
            preparedStatementInsert.setString(3, energieTyp);
            preparedStatementInsert.setString(4, ursprungName);
            preparedStatementInsert.setInt(5, Integer.parseInt(kartenNummer));
            preparedStatementInsert.setString(6, symbolSeltenheit);
            preparedStatementInsert.setDouble(7, Double.parseDouble(wertInEuro));
            preparedStatementInsert.setString(8, besonderheitID);
            preparedStatementInsert.setString(9, datumWertEingabe);
            preparedStatementInsert.setString(10, nameZusatz);
            preparedStatementInsert.setString(11, trainerZusatz);
            preparedStatementInsert.setString(12, kartenNummerZusatz);


            int affectedRows = preparedStatementInsert.executeUpdate();

            if (affectedRows > 0) {
                // Abrufen der generierten ID
                ResultSet generatedKeys = preparedStatementInsert.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedID = generatedKeys.getInt(1);
                    kartenIDTextField.setText(String.valueOf(generatedID));
                }

                clearFields();
                GenerateNextID.generateNextID(con, "sammlung", "karten_id", kartenIDTextField);
                generatedKeys.close();
            }

            preparedStatementInsert.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        pokemonNameTextField.setText("");
        ursprungNameTextField.setText("");
        kartenNummerTextField.setText("");
        wertInEuroTextField.setText("");
        datumWertEingabeTextField.setText("");
        kartenNummerZusatzTextField.setText("");
    }
}



