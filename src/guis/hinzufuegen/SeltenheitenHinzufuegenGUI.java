/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package guis.hinzufuegen;

import static layout.TaskListWithIcon.iconPfad;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import datenbank.DatenbankVerbindung;
import datenbank.GenerateNextID;
import funktionen.Buttons;
import guis.AbstractGUI;
import layout.Colors;
import layout.GUILabel;
import layout.Schrift;
import layout.guitextfield.GUIIntegerTextField;
import layout.guitextfield.GUITextField;

public class SeltenheitenHinzufuegenGUI extends AbstractGUI<SeltenheitenHinzufuegenGUI> {
    private JLabel idSeltenheitLabel, beschreibungSeltenheitLabel;
    private JTextField idSeltenheitTextField, beschreibungSeltenheitTextField;
    private JButton speichernButton;

    // Code zum Einfuegen der Daten in die Datenbank
    Connection con = DatenbankVerbindung.connectDB(); // Stelle eine Verbindung zur Datenbank her

    public SeltenheitenHinzufuegenGUI() {
        setTitle("Seltenheiten bearbeiten");
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(iconPfad);
        setIconImage(icon.getImage());

        idSeltenheitLabel = new GUILabel("ID Seltenheit");
        idSeltenheitTextField = new GUIIntegerTextField();
        idSeltenheitTextField.setEditable(false); // Die ID ist schreibgeschützt (autoincrement)

        beschreibungSeltenheitLabel = new GUILabel("Beschreibung Seltenheit");
        beschreibungSeltenheitTextField = new GUITextField();

        speichernButton = new JButton("Seltenheit hinzufügen");
        speichernButton.setFont(new Font("Arial", Font.PLAIN, 24));

        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDataInDatabase();
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Abstand zwischen den Komponenten

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(idSeltenheitLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(idSeltenheitTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(beschreibungSeltenheitLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(beschreibungSeltenheitTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(speichernButton, gbc);

        // Button zum hinzufuegen einer Karte und gleichzeitigem Neuladen mit Enter
        speichernButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
        speichernButton.getActionMap().put("enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDataInDatabase();
                reloadPage();
            }
        });

        // ID beim Laden des GUIs generieren
        GenerateNextID.generateNextID(con, "seltenheit", "id", idSeltenheitTextField);

        JButton zurueck = Buttons.buttonZurueckSeltenheiten(Schrift.zurueckButton());
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 5;

        panel.add(zurueck, gbc);
        panel.setBackground(Colors.JAVA_COLOR_GREEN);

        add(panel);
        add(Buttons.buttonAnzeigen(), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setFocusable(true);
    }

    private void updateDataInDatabase() {
        String neueBeschreibung = beschreibungSeltenheitTextField.getText();

        try {
            // Einfuegen der Daten mit automatisch inkrementierter ID
            String sqlInsert = "INSERT INTO seltenheit (beschreibung) VALUES (?)";
            PreparedStatement preparedStatementInsert = con.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatementInsert.setString(1, neueBeschreibung);
            preparedStatementInsert.executeUpdate();

            // Abrufen der generierten ID
            ResultSet generatedKeys = preparedStatementInsert.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedID = generatedKeys.getInt(1);
                idSeltenheitTextField.setText(String.valueOf(generatedID));
            }

            clearFields();
            GenerateNextID.generateNextID(con, "seltenheit", "id", idSeltenheitTextField);

            generatedKeys.close();
            preparedStatementInsert.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void clearFields() {
        beschreibungSeltenheitTextField.setText("");
    }

}
