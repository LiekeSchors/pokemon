/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import datenbank.DatenbankVerbindung;
import funktionen.AddComponentsToPanel;
import funktionen.Buttons;
import funktionen.FilterView;
import funktionen.ValuesToStringDB;
import layout.Borders;
import layout.Colors;
import layout.Schrift;

public class PokemonKartenOrdnerView extends JFrame {

    private JTable table;

    public PokemonKartenOrdnerView() {
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
            String sql = "SELECT * FROM ordner ORDER BY id";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();

            // Erstelle ein DefaultTableModel für die JTable
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Ordner-ID");
            model.addColumn("Enthält Zyklus");
            model.addColumn("Farbe");

            while (rs.next()) {
                // Füge die Zeilen zum Model hinzu
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("zyklus"),
                        rs.getString("farbe"),
                };
                model.addRow(row);
            }

            // Erstelle die JTable mit dem Model
            table = new JTable(model);
            table.setRowHeight(30);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.setEnabled(false);

            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);

            // Iteriere durch die Spalten und passe die Breite basierend auf dem Inhalt an
            for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
                TableColumn column = table.getColumnModel().getColumn(columnIndex);
                int preferredWidth = column.getMinWidth();

                for (int rowIndex = 0; rowIndex < table.getRowCount(); rowIndex++) {
                    TableCellRenderer cellRenderer = table.getCellRenderer(rowIndex, columnIndex);
                    Component cellComponent = table.prepareRenderer(cellRenderer, rowIndex, columnIndex);
                    preferredWidth = Math.max(preferredWidth, cellComponent.getPreferredSize().width);
                }
                column.setPreferredWidth(preferredWidth);
            }

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

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            // Filter

            // Filtern nach Zyklus
            String[] zyklusFilter = ValuesToStringDB.getZyklusOrdner();
            for (String zyklus : zyklusFilter) {
            }
            JComboBox<String> zyklusFilterComboBox = new JComboBox<>(zyklusFilter);
            JLabel zyklusFilterLabel = new JLabel("Zyklus: ");
            zyklusFilterLabel.setFont(Schrift.normal());
            zyklusFilterComboBox.setFont(Schrift.normal());

            zyklusFilterComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selektierterZyklus = (String) zyklusFilterComboBox.getSelectedItem();
                    FilterView.filternNachString(selektierterZyklus, table, 1);
                }
            });

            // Grosses Panel
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(Colors.JAVA_COLOR_ORANGE);
            panel.add(scrollPane);

            // Panel fuer Filter
            JPanel filterPanel = new JPanel();
            filterPanel.setPreferredSize(new Dimension(250, 100));
            filterPanel.setBackground(Colors.JAVA_COLOR_ORANGE);

            AddComponentsToPanel.addLabelAndComboBox(filterPanel, zyklusFilterLabel, zyklusFilterComboBox, gbc, 0, 0);
            filterPanel.add(zyklusFilterComboBox, gbc);

            JButton ordnerHinzufuegen = Buttons.btnOrdnerHinzufuegen(Schrift.schriftartButtons());
            Borders.buttonBorder(ordnerHinzufuegen, Color.BLACK);
            filterPanel.add(ordnerHinzufuegen);

            JButton ordnerBearbeiten = Buttons.btnOrdnerBearbeiten(Schrift.schriftartButtons());
            Borders.buttonBorder(ordnerBearbeiten, Color.BLACK);
            filterPanel.add(ordnerBearbeiten);

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