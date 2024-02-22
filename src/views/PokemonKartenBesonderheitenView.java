/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import datenbank.DatenbankVerbindung;
import funktionen.Buttons;
import layout.Borders;
import layout.Colors;
import layout.Schrift;

public class PokemonKartenBesonderheitenView extends JFrame {

    private JTable table;

    public PokemonKartenBesonderheitenView() {
        setTitle("Besonderheiten anzeigen");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));


        // DB-Verbindung herstellen
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;

        con = DatenbankVerbindung.connectDB();

        try {
            String sql = "SELECT * FROM besonderheiten ORDER BY id";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();

            // Erstelle ein DefaultTableModel für die JTable
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID Besonderheit");
            model.addColumn("Beschreibung");

            while (rs.next()) {
                // Füge die Zeilen zum Model hinzu
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("beschreibung")

                };
                model.addRow(row);
            }

            // Erstelle die JTable mit dem Model
            table = new JTable(model);
            table.setRowHeight(40);
            table.setEnabled(false);

            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);

            // Header, Schriftart, Scrollpane
            JTableHeader header = table.getTableHeader();
            header.setReorderingAllowed(false);
            JScrollPane scrollPane = new JScrollPane(table);

            table.setFont(Schrift.normal());
            header.setFont(Schrift.normal());

            // Sortieren, wenn auf Header geklickt wird
            header.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int columnIndex = table.columnAtPoint(e.getPoint());
                    sorter.toggleSortOrder(columnIndex);
                }
            });

            // Panel fuer Tabelle
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(Colors.JAVA_COLOR_HELLBLAU);
            panel.add(scrollPane);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            // Panel fuer Filter/Buttons
            JPanel filterPanel = new JPanel();
            filterPanel.setPreferredSize(new Dimension(250, 100));
            filterPanel.setBackground(Colors.JAVA_COLOR_HELLBLAU);

            JButton besonderheitenHinzufuegen = Buttons.btnBesonderheitenHinzufuegen(Schrift.schriftartButtons());
            Borders.buttonBorder(besonderheitenHinzufuegen, Color.WHITE);
            filterPanel.add(besonderheitenHinzufuegen);

            JButton besonderheitenBearbeiten = Buttons.btnBesonderheitenBearbeiten(Schrift.schriftartButtons());
            Borders.buttonBorder(besonderheitenBearbeiten, Color.WHITE);
            filterPanel.add(besonderheitenBearbeiten);

            add(panel);
            add(filterPanel, BorderLayout.NORTH);

            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        add(Buttons.buttonAnzeigen(), BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }
}
