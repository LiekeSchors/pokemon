/*
 * Copyright (c) 2023.
 * Lieke Schors
 * GUI zum Bearbeiten der Tabelle 'sammlung'
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    public void KartenBearbeitenGUI() {
        setTitle("GUI Karten bearbeiten");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // GUI-Komponenten initialisieren
        kartenIDLabel = new JLabel("Karten-ID");
        kartenIDTextField = new JTextField();

        erweiterungAbkuerzungLabel = new JLabel("Abkürzung der Erweiterung");
        erweiterungAbkuerzungTextField = new JTextField();

        pokemonNameLabel = new JLabel("Pokémon-Name");
        pokemonNameTextField = new JTextField();

        energieTypLabel = new JLabel("Energie-Typ");
        energieTypTextField = new JTextField();

        ursprungNameLabel = new JLabel("Ursprung des Pokémons");
        ursprungNameTextField = new JTextField();

        kartenNummerLabel = new JLabel("Kartennummer");
        kartenNummerTextField = new JTextField();

        seltenheitIDLabel = new JLabel("Seltenheit-ID");
        seltenheitIDTextField = new JTextField();

        wertInEuroLabel = new JLabel("Wert der Karte in €");
        wertInEuroTextField = new JTextField();

        besonderheitIDLabel = new JLabel("Besonderheit-ID");
        besonderheitIDTextField = new JTextField();

        datumWertEingabeLabel = new JLabel("Datum der Werteingabe");
        datumWertEingabeTextField = new JTextField();

        nameZusatzLabel = new JLabel("Zusatz zum Namen des Pokémons (z.B. 'V' oder 'V-Star')");
        nameZusatzTextField = new JTextField();

        trainerZusatzLabel = new JLabel("Zusatz zum Trainer (z.B. 'Item', 'Unterstützer'");
        trainerZusatzTextField = new JTextField();

        kartenNummerZusatzLabel = new JLabel("Zusatz zur Kartennummer bzw. nicht-regelmäßige Kartennummer");
        kartenNummerZusatzTextField = new JTextField();

        hinzufuegenButton = new JButton("Karte hinzufügen");

        JPanel panel = new JPanel();
        panel.add(kartenIDLabel);
        panel.add(kartenIDTextField);

        panel.add(erweiterungAbkuerzungLabel);
        panel.add(erweiterungAbkuerzungTextField);

        panel.add(pokemonNameLabel);
        panel.add(pokemonNameTextField);

        panel.add(energieTypLabel);
        panel.add(energieTypTextField);

        panel.add(ursprungNameLabel);
        panel.add(ursprungNameTextField);

        panel.add(kartenNummerLabel);
        panel.add(kartenNummerTextField);

        panel.add(seltenheitIDLabel);
        panel.add(seltenheitIDTextField);

        panel.add(wertInEuroLabel);
        panel.add(wertInEuroTextField);

        panel.add(besonderheitIDLabel);
        panel.add(besonderheitIDTextField);

        panel.add(datumWertEingabeLabel);
        panel.add(datumWertEingabeTextField);

        panel.add(nameZusatzLabel);
        panel.add(nameZusatzTextField);

        panel.add(trainerZusatzLabel);
        panel.add(trainerZusatzTextField);

        panel.add(kartenNummerZusatzLabel);
        panel.add(kartenNummerZusatzTextField);

        panel.add(hinzufuegenButton);

        add(panel);
        
        hinzufuegenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataToDatabase();
            }
        });
        setLocationRelativeTo(null);

    }
    private void addDataToDatabase() {
        String newData = kartenIDTextField.getText();
        // Implementiere hier den Code zum Hinzufügen der Daten in die Datenbank
        System.out.println("Hinzufügen: " + newData);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KartenBearbeitenGUI kartenBearbeitenGUI = new KartenBearbeitenGUI();
            kartenBearbeitenGUI.setVisible(true);
        });
    }
}


