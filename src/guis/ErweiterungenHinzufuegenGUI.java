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
import funktionen.AddComponentsToPanel;
import funktionen.Buttons;
import layout.Schrift;

public class ErweiterungenHinzufuegenGUI extends AbstractGUI<ErweiterungenBearbeitenGUI> {
    // Code zum Einfuegen der Daten in die Datenbank
    Connection con = DatenbankVerbindung.connectDB(); // Stelle eine Verbindung zur Datenbank her
    private final JLabel erweiterungIDLabel;
    private final JLabel erweiterungNameLabel;
    private final JLabel zyklusLabel;
    private final JLabel abkuerzungLabel;
    private final JLabel jahrLabel;
    private final JLabel anzahlKartenSammlungLabel;
    private final JLabel ordnerIDLabel;
    private final JTextField erweiterungIDTextField;
    private final JTextField erweiterungNameTextField;
    private final JTextField zyklusTextField;
    private final JTextField abkuerzungTextField;
    private final JTextField jahrTextField;
    private final JTextField anzahlKartenSammlungTextField;
    private final JTextField ordnerIDTextField;
    private final JButton hinzufuegenButton;

    public ErweiterungenHinzufuegenGUI() {
        setTitle("GUI Erweiterungen hinzufuegen");
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("C:\\Users\\lieke\\IdeaProjects\\pokemon_karten\\src\\layout\\ultra-ball.png");
        setIconImage(icon.getImage());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6); // Abstand zwischen den Komponenten

        erweiterungIDLabel = new JLabel("Erweiterung-ID");
        erweiterungIDLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        erweiterungIDTextField = new JTextField();
        erweiterungIDTextField.setPreferredSize(new Dimension(150, 30));
        erweiterungIDTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, erweiterungIDLabel, erweiterungIDTextField, gbc, 0, 0);

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


        hinzufuegenButton = new JButton("Erweiterung hinzufügen");
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
                updateDataInDatabase();
                reloadPage();
            }
        });

        GenerateNextID.generateNextID(con, "erweiterungen", "id", erweiterungIDTextField);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ErweiterungenHinzufuegenGUI gui = new ErweiterungenHinzufuegenGUI();
            gui.setVisible(true);
        });
    }

    private void updateDataInDatabase() {
        String erweiterungName = erweiterungNameTextField.getText();
        String zyklus = zyklusTextField.getText();
        String abkuerzung = abkuerzungTextField.getText();
        String jahr = jahrTextField.getText();
        String anzahlKartenSammlung = anzahlKartenSammlungTextField.getText();
        String ordnerID = ordnerIDTextField.getText();

        try {
            // Einfuegen der Daten mit automatisch inkrementierter ID
            String sqlInsert = "INSERT INTO erweiterungen (erweiterung_name, zyklus, abkuerzung, jahr, anzahl_karten_sammlung, ordner_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatementInsert = con.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatementInsert.setString(1, erweiterungName);
            preparedStatementInsert.setString(2, zyklus);
            preparedStatementInsert.setString(3, abkuerzung);
            preparedStatementInsert.setInt(4, Integer.parseInt(jahr));
            preparedStatementInsert.setInt(5, Integer.parseInt(anzahlKartenSammlung));
            preparedStatementInsert.setInt(6, Integer.parseInt(ordnerID));


            int affectedRows = preparedStatementInsert.executeUpdate();

            if (affectedRows > 0) {
                // Abrufen der generierten ID
                ResultSet generatedKeys = preparedStatementInsert.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedID = generatedKeys.getInt(1);
                    erweiterungIDTextField.setText(String.valueOf(generatedID));
                }

                clearFields();
                GenerateNextID.generateNextID(con, "erweiterungen", "id", erweiterungIDTextField);
                generatedKeys.close();
            }

            preparedStatementInsert.close();
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
    }
}