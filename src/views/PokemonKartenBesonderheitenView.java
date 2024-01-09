/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package views;import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datenbank.DatenbankVerbindung;
import funktionen.MenueAnzeigeUnten;

public class PokemonKartenBesonderheitenView extends JFrame {
    public static final Color JAVA_COLOR_PINK = new Color(255, 102, 255);
    public static final Color JAVA_COLOR_HELLBLAU = new Color(51, 102, 255);
    public static final Color JAVA_COLOR_ORANGE = new Color(255, 153, 51);
    public static final Color JAVA_COLOR_TUERKIS = new Color(0, 153, 153);

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

            panel.add(scrollPane);
            add(panel);
        } catch (SQLException e) {
            System.out.println(e);
        }

        add(MenueAnzeigeUnten.menueAnzeigeUnten(), BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new PokemonKartenBesonderheitenView().setVisible(true);
    }
}
