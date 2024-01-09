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
import java.awt.event.ActionListener;
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

public class SeltenheitenHinzufuegenGUI extends JFrame {
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

        idSeltenheitLabel = new JLabel("ID Seltenheit");
        idSeltenheitTextField = new JTextField();
        idSeltenheitTextField.setEditable(false); // Die ID ist schreibgeschützt (autoincrement)
        idSeltenheitTextField.setPreferredSize(new Dimension(150, 50));
        idSeltenheitLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        idSeltenheitTextField.setFont(new Font("Arial", Font.PLAIN, 24));

        beschreibungSeltenheitLabel = new JLabel("Beschreibung Seltenheit");
        beschreibungSeltenheitTextField = new JTextField();
        beschreibungSeltenheitTextField.setPreferredSize(new Dimension(150, 50));
        beschreibungSeltenheitLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        beschreibungSeltenheitTextField.setFont(new Font("Arial", Font.PLAIN, 24));

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

        add(panel);

        // ID beim Laden des GUIs generieren
        GenerateNextID.generateNextID(con, "seltenheit", "id", idSeltenheitTextField);

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

    private void reloadPage() {
        SwingUtilities.invokeLater(() -> {
            SeltenheitenHinzufuegenGUI gui = new SeltenheitenHinzufuegenGUI();
            gui.setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SeltenheitenHinzufuegenGUI gui = new SeltenheitenHinzufuegenGUI();
            gui.setVisible(true);
        });
    }
}
