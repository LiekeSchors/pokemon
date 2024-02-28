/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
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
import layout.Schrift;

public class PokemonKartenSeltenheitenView extends JFrame {

    private JTable table;

    public PokemonKartenSeltenheitenView() {
        setTitle("Erweiterungen anzeigen");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        ImageIcon icon = new ImageIcon("C:\\Users\\lieke\\IdeaProjects\\pokemon_karten\\src\\layout\\ultra-ball.png");
        setIconImage(icon.getImage());

        // DB-Verbindung herstellen
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;

        con = DatenbankVerbindung.connectDB();

        try {
            String sql = "SELECT * FROM seltenheit ORDER BY id";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();

            // Erstelle ein DefaultTableModel für die JTable
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID Seltenheit");
            model.addColumn("Beschreibung");

            while (rs.next()) {
                // Füge die Zeilen zum Model hinzu
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("beschreibung"),
                        rs.getString("symbol")
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
            panel.setBackground(Color.green);
            panel.add(scrollPane);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            // Panel fuer Filter/Buttons
            JPanel filterPanel = new JPanel();
            filterPanel.setPreferredSize(new Dimension(250, 100));
            filterPanel.setBackground(Color.green);

            JButton seltenheitenHinzufuegen = Buttons.btnSeltenheitenHinzufuegen(Schrift.schriftartButtons());
            Borders.buttonBorder(seltenheitenHinzufuegen, Color.black);
            filterPanel.add(seltenheitenHinzufuegen);

            JButton seltenheitenBearbeiten = Buttons.btnSeltenheitenBearbeiten(Schrift.schriftartButtons());
            Borders.buttonBorder(seltenheitenBearbeiten, Color.black);
            filterPanel.add(seltenheitenBearbeiten);

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
