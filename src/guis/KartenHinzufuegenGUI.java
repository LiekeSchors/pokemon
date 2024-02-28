/**
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
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.swing.SwingUtilities;

import datenbank.DatenbankVerbindung;
import datenbank.GenerateNextID;
import datenbank.SQLQuerys;
import funktionen.AddComponentsToPanel;
import funktionen.Buttons;
import funktionen.ValuesToStringDB;
import layout.Borders;
import layout.Colors;
import layout.GUIComboBox;
import layout.GUILabel;
import layout.Schrift;
import layout.guitextfield.GUIDateTextField;
import layout.guitextfield.GUIDoubleTextField;
import layout.guitextfield.GUIIntegerTextField;
import layout.guitextfield.GUITextField;

public class KartenHinzufuegenGUI extends AbstractGUI<KartenHinzufuegenGUI> {
    private JLabel kartenIDLabel, erweiterungAbkuerzungLabel,
            pokemonNameLabel, energieTypLabel, ursprungNameLabel, kartenNummerLabel,
            seltenheitIDLabel, wertInEuroLabel, besonderheitIDLabel, datumWertEingabeLabel,
            nameZusatzLabel, trainerZusatzLabel, kartenNummerZusatzLabel;

    private JTextField kartenIDTextField, pokemonNameTextField, ursprungNameTextField, kartenNummerTextField,
            wertInEuroTextField, datumWertEingabeTextField, kartenNummerZusatzTextField;

    private GUIComboBox erweiterungAbkuerzungComboBox;
    private GUIComboBox energieTypComboBox;
    private GUIComboBox trainerZusatzComboBox;
    private GUIComboBox nameZusatzComboBox;
    private GUIComboBox seltenheitSymbolCombobox;
    private GUIComboBox besonderheitComboBox;
    private String selectedValue;


    private JButton hinzufuegenButton;

    Connection con = DatenbankVerbindung.connectDB(); // Stelle eine Verbindung zur Datenbank her

    public KartenHinzufuegenGUI() {
        setTitle("GUI Karten hinzufügen");
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("C:\\Users\\lieke\\IdeaProjects\\pokemon_karten\\src\\layout\\ultra-ball.png");
        setIconImage(icon.getImage());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6); // Abstand zwischen den Komponenten

        kartenIDLabel = new GUILabel("Karten-ID");
        kartenIDTextField = new GUIIntegerTextField();
        AddComponentsToPanel.addLabelAndTextField(panel, kartenIDLabel, kartenIDTextField, gbc, 0, 0);

        erweiterungAbkuerzungLabel = new GUILabel("Abkürzung der Erweiterung");
        erweiterungAbkuerzungComboBox = new GUIComboBox<>(ValuesToStringDB.getEindeutigeErweiterungAbkuerzung(false));
        AddComponentsToPanel.addLabelAndComboBox(panel, erweiterungAbkuerzungLabel, erweiterungAbkuerzungComboBox, gbc, 0, 2);
        Object letzteErweiterung = SQLQuerys.getLetzteErweiterung();
        if (letzteErweiterung != null) {
            erweiterungAbkuerzungComboBox.setSelectedItem(letzteErweiterung);
        }

        pokemonNameLabel = new GUILabel("Pokémon-Name");
        pokemonNameTextField = new GUITextField();
        AddComponentsToPanel.addLabelAndTextField(panel, pokemonNameLabel, pokemonNameTextField, gbc, 1, 0);

        final String[] pokemonName = new String[1];
        final String[] entwicklung = new String[1];
        pokemonNameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                // Get the entered Pokémon name
                pokemonName[0] = pokemonNameTextField.getText();

                // Get the evolution information based on the entered Pokémon name
                entwicklung[0] = SQLQuerys.getEntwicklung(pokemonName[0]);

                // Set the obtained evolution information in the "Ursprung des Pokémons" field
                ursprungNameTextField.setText(entwicklung[0]);
            }
        });

        energieTypLabel = new GUILabel("Energie-Typ");
        energieTypComboBox = new GUIComboBox<>(ValuesToStringDB.getEnergieTyp(false));
        AddComponentsToPanel.addLabelAndComboBox(panel, energieTypLabel, energieTypComboBox, gbc, 1, 2);
        Object letzteEnergie = SQLQuerys.getLetzteEnergie();
        if (letzteEnergie != null) {
            energieTypComboBox.setSelectedItem(letzteEnergie);
        }

        ursprungNameLabel = new GUILabel("Ursprung des Pokémons");
        ursprungNameTextField = new GUITextField();
        AddComponentsToPanel.addLabelAndTextField(panel, ursprungNameLabel, ursprungNameTextField, gbc, 2, 0);

        kartenNummerLabel = new GUILabel("Kartennummer");
        kartenNummerTextField = new GUIIntegerTextField();
        AddComponentsToPanel.addLabelAndTextField(panel, kartenNummerLabel, kartenNummerTextField, gbc, 2, 2);

        seltenheitIDLabel = new GUILabel("Seltenheit");
        seltenheitSymbolCombobox = new GUIComboBox<>(ValuesToStringDB.getSymbolSeltenheit());
        seltenheitSymbolCombobox.setFont(Schrift.farbigeUnicodeSymbole());
        AddComponentsToPanel.addLabelAndComboBox(panel, seltenheitIDLabel, seltenheitSymbolCombobox, gbc, 3, 0);
        seltenheitSymbolCombobox.setSelectedItem("●");

        besonderheitIDLabel = new GUILabel("Besonderheit");
        besonderheitComboBox = new GUIComboBox<>(ValuesToStringDB.getBeschreibungBesonderheit());
        AddComponentsToPanel.addLabelAndComboBox(panel, besonderheitIDLabel, besonderheitComboBox, gbc, 3, 2);
        besonderheitComboBox.setSelectedItem("Ohne");

        wertInEuroLabel = new GUILabel("Wert der Karte in €");
        wertInEuroTextField = new GUIDoubleTextField();
        AddComponentsToPanel.addLabelAndTextField(panel, wertInEuroLabel, wertInEuroTextField, gbc, 4, 0);

        datumWertEingabeLabel = new GUILabel("Datum der Werteingabe");
        datumWertEingabeTextField = new GUIDateTextField();
        AddComponentsToPanel.addLabelAndTextField(panel, datumWertEingabeLabel, datumWertEingabeTextField, gbc, 4, 2);

        nameZusatzLabel = new GUILabel("Zusatz zum Namen des Pokémons (z.B. 'V' oder 'V-Star')");
        nameZusatzComboBox = new GUIComboBox<>(ValuesToStringDB.getNameZusatz());
        AddComponentsToPanel.addLabelAndComboBox(panel, nameZusatzLabel, nameZusatzComboBox, gbc, 5, 0);

        trainerZusatzLabel = new GUILabel("Zusatz zum Trainer (z.B. 'Item', 'Unterstützer')");
        trainerZusatzComboBox = new GUIComboBox<>(ValuesToStringDB.getTrainerZusatz());
        AddComponentsToPanel.addLabelAndComboBox(panel, trainerZusatzLabel, trainerZusatzComboBox, gbc, 5, 2);

        kartenNummerZusatzLabel = new GUILabel("Zusatz zur Kartennummer bzw. nicht-regelmäßige Kartennummer");
        kartenNummerZusatzTextField = new GUITextField();
        AddComponentsToPanel.addLabelAndTextField(panel, kartenNummerZusatzLabel, kartenNummerZusatzTextField, gbc, 6, 0);

        hinzufuegenButton = new JButton("Karte hinzufügen");
        hinzufuegenButton.setFont(new Font("Arial", Font.PLAIN, 22));
        hinzufuegenButton.setPreferredSize(new Dimension(200, 40));
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

        JButton zurueck = Buttons.buttonZurueckKarten(Schrift.normal());
        zurueck.setPreferredSize(new Dimension(200, 40));
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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                erweiterungAbkuerzungComboBox.requestFocus();
            }
        });

    }

    private void updateDataInDatabase() {
        String erweiterungAbkuerzung = (String) erweiterungAbkuerzungComboBox.getSelectedItem();
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
                    int generatedID = generatedKeys.getInt(12);
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




