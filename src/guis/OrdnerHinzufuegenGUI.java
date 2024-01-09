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
import java.sql.ResultSet;
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
import datenbank.GenerateNextID;
import funktionen.AddComponentsToPanel;
import funktionen.Buttons;
import layout.Schrift;

public class OrdnerHinzufuegenGUI extends JFrame {
    private JLabel ordnerIDLabel, zyklusLabel, farbeLabel;
    private JTextField ordnerIDTextField, zyklusTextField, farbeTextField;
    private JButton hinzufuegenButton;

    // Code zum Einfuegen der Daten in die Datenbank
    Connection con = DatenbankVerbindung.connectDB(); // Stelle eine Verbindung zur Datenbank her

    public OrdnerHinzufuegenGUI() {
        setTitle("GUI Ordner hinzufügen");
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6); // Abstand zwischen den Komponenten

        ordnerIDLabel = new JLabel("Ordner-ID");
        ordnerIDLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        ordnerIDTextField = new JTextField();
        ordnerIDTextField.setPreferredSize(new Dimension(150, 30));
        ordnerIDTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, ordnerIDLabel, ordnerIDTextField, gbc, 0, 0);

        zyklusLabel = new JLabel("Zyklus");
        zyklusLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        zyklusTextField = new JTextField();
        zyklusTextField.setPreferredSize(new Dimension(150, 30));
        zyklusTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, zyklusLabel, zyklusTextField, gbc, 1, 0);

        farbeLabel = new JLabel("Farbe des Ordners");
        farbeLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        farbeTextField = new JTextField();
        farbeTextField.setPreferredSize(new Dimension(150, 30));
        farbeTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        AddComponentsToPanel.addLabelAndTextField(panel, farbeLabel, farbeTextField, gbc, 2, 0);

        hinzufuegenButton = new JButton("Ordner hinzufügen");
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
                updateDataInDatabase();
                reloadPage();
            }
        });

        GenerateNextID.generateNextID(con, "ordner", "id", ordnerIDTextField);

        JButton zurueck = Buttons.buttonZurueck(Schrift.zurueckButton());
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 5;

        panel.add(zurueck, gbc);

        add(panel);
        add(Buttons.buttonAnzeigen(), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setFocusable(true);
    }

    private void updateDataInDatabase() {
        String zyklus = zyklusTextField.getText();
        String farbe = farbeTextField.getText();

        try {
            // Einfuegen der Daten mit automatisch inkrementierter ID
            String sqlInsert = "INSERT INTO ordner (zyklus, farbe) VALUES (?, ?)";
            PreparedStatement preparedStatementInsert = con.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatementInsert.setString(1, zyklus);
            preparedStatementInsert.setString(2, farbe);

            int affectedRows = preparedStatementInsert.executeUpdate();

            if (affectedRows > 0) {
                // Abrufen der generierten ID
                ResultSet generatedKeys = preparedStatementInsert.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedID = generatedKeys.getInt(1);
                    ordnerIDTextField.setText(String.valueOf(generatedID));
                }

                clearFields();
                GenerateNextID.generateNextID(con, "ordner", "id", ordnerIDTextField);
                generatedKeys.close();
            }

            preparedStatementInsert.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        zyklusTextField.setText("");
        farbeTextField.setText("");
    }

    private void reloadPage() {
        SwingUtilities.invokeLater(() -> {
            OrdnerHinzufuegenGUI gui = new OrdnerHinzufuegenGUI();
            gui.setVisible(true);
            dispose();
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrdnerHinzufuegenGUI gui = new OrdnerHinzufuegenGUI();
            gui.setVisible(true);
        });
    }
}