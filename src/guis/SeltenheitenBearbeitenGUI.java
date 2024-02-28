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
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
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
import funktionen.Buttons;
import layout.Schrift;

public class SeltenheitenBearbeitenGUI extends AbstractGUI<SeltenheitenBearbeitenGUI> {
    private JLabel editIDLabel, beschreibungSeltenheitLabel;
    private JTextField editIDTextField, beschreibungSeltenheitTextField;
    private JButton speichernButton;

    Connection con = DatenbankVerbindung.connectDB();

    public SeltenheitenBearbeitenGUI() {
        setTitle("Seltenheiten bearbeiten");
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("C:\\Users\\lieke\\IdeaProjects\\pokemon_karten\\src\\layout\\ultra-ball.png");
        setIconImage(icon.getImage());

        editIDLabel = new JLabel("Seltenheit-ID");
        editIDTextField = new JTextField();
        editIDTextField.setPreferredSize(new Dimension(150, 50));
        editIDLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        editIDTextField.setFont(new Font("Arial", Font.PLAIN, 24));

        beschreibungSeltenheitLabel = new JLabel("Beschreibung Seltenheit");
        beschreibungSeltenheitTextField = new JTextField();
        beschreibungSeltenheitTextField.setPreferredSize(new Dimension(150, 50));
        beschreibungSeltenheitLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        beschreibungSeltenheitTextField.setFont(new Font("Arial", Font.PLAIN, 24));

        speichernButton = new JButton("Änderungen speichern");
        speichernButton.setFont(new Font("Arial", Font.PLAIN, 24));

        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateExistingDataInDatabase();
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(editIDLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(editIDTextField, gbc);

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

        speichernButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
        speichernButton.getActionMap().put("enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateExistingDataInDatabase();
                reloadPage();
            }
        });

        JButton zurueck = Buttons.buttonZurueckSeltenheiten(Schrift.zurueckButton());
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 5;

        panel.add(zurueck, gbc);
        panel.setBackground(Color.green);

        add(panel);
        add(Buttons.buttonAnzeigen(), BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setFocusable(true);
    }

    private void updateExistingDataInDatabase() {
        String neueBeschreibung = beschreibungSeltenheitTextField.getText();
        int editID = Integer.parseInt(editIDTextField.getText());

        try {
            String sqlUpdate = "UPDATE seltenheit SET beschreibung = ? WHERE id = ?";
            PreparedStatement preparedStatementUpdate = con.prepareStatement(sqlUpdate);
            preparedStatementUpdate.setString(1, neueBeschreibung);
            preparedStatementUpdate.setInt(2, editID);
            preparedStatementUpdate.executeUpdate();

            clearFields();

            preparedStatementUpdate.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        beschreibungSeltenheitTextField.setText("");
        editIDTextField.setText("");
    }
}