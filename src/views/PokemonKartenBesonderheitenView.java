/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import guis.BesonderheitenBearbeitenGUI;
import guis.BesonderheitenHinzufuegenGUI;
import layout.Schrift;

public class PokemonKartenBesonderheitenView extends JFrame {

    private JTable table;

    public PokemonKartenBesonderheitenView() {
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

            panel.add(Buttons.btnBesonderheitenHinzufuegen(Schrift.normal()));
            panel.add(Buttons.btnBesonderheitenBearbeiten(Schrift.normal()));

            panel.add(scrollPane);
            add(panel);
        } catch (SQLException e) {
            System.out.println(e);
        }

        add(Buttons.buttonAnzeigen(), BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new PokemonKartenBesonderheitenView().setVisible(true);
    }
}
