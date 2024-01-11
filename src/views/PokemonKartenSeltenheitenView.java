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

public class PokemonKartenSeltenheitenView extends JFrame {

    private JTable table;

    public PokemonKartenSeltenheitenView() {
        setTitle("Erweiterungen anzeigen");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));

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
                        rs.getString("beschreibung")
                };
                model.addRow(row);
            }

            // Erstelle die JTable mit dem Model
            table = new JTable(model);
            table.setRowHeight(40);
            table.setEnabled(false);

            // Füge einen TableRowSorter zum Sortieren hinzu
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);

            // Tabellenkopf anzeigen
            JTableHeader header = table.getTableHeader();
            header.setReorderingAllowed(false);

            // Setze die Tabelle in ein ScrollPane
            JScrollPane scrollPane = new JScrollPane(table);

            // Textgröße ändern
            Font font = new Font("Arial", Font.PLAIN, 20); // Ändere die Schriftart und Größe nach Bedarf
            table.setFont(font);
            header.setFont(font);

            header.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int columnIndex = table.columnAtPoint(e.getPoint());
                    sorter.toggleSortOrder(columnIndex);
                }
            });

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            JPanel filterPanel = new JPanel();
            filterPanel.setPreferredSize(new Dimension(250, 100));
            filterPanel.setBackground(Color.green);

            JButton seltenheitenHinzufuegen = Buttons.btnSeltenheitenHinzufuegen(Schrift.schriftartButtons());
            Borders.buttonBorder(seltenheitenHinzufuegen, Color.black);
            filterPanel.add(seltenheitenHinzufuegen);

            JButton seltenheitenBearbeiten = Buttons.btnSeltenheitenBearbeiten(Schrift.schriftartButtons());
            Borders.buttonBorder(seltenheitenBearbeiten, Color.black);
            filterPanel.add(seltenheitenBearbeiten);

            panel.add(scrollPane);
            panel.setBackground(Color.green);
            add(panel);
            add(filterPanel, BorderLayout.NORTH);
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        add(Buttons.buttonAnzeigen(), BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new PokemonKartenSeltenheitenView().setVisible(true);
    }
}
