/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package guis.views;

import static layout.TaskListWithIcon.iconPfad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
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
import funktionen.CustomHeaderRenderer;
import funktionen.FilterView;
import funktionen.ValuesToStringDB;
import layout.Borders;
import layout.Colors;
import layout.Schrift;

public class PokemonKartenErweiterungenView extends JFrame {

    public JTable table;

    public PokemonKartenErweiterungenView() {
        setTitle("Erweiterungen anzeigen");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        ImageIcon icon = new ImageIcon(iconPfad);
        setIconImage(icon.getImage());

        // DB-Verbindung herstellen
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;

        con = DatenbankVerbindung.connectDB();

        try {
            String sql = "SELECT * FROM erweiterungen ORDER BY id";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();

            // Erstelle ein DefaultTableModel für die JTable
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID Erweiterung");
            model.addColumn("Name Erweiterung");
            model.addColumn("Zyklus");
            model.addColumn("Abkürzung Erweiterung");
            model.addColumn("Jahr");
            model.addColumn("Anzahl der Karten in der Sammlung");
            model.addColumn("Anzahl Karten bereits gesammelt");
            model.addColumn("Verhältnis gesammelt");
            model.addColumn("ID Ordner");


            while (rs.next()) {
                // Füge die Zeilen zum Model hinzu
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("erweiterung_name"),
                        rs.getString("zyklus"),
                        rs.getString("abkuerzung"),
                        rs.getInt("jahr"),
                        rs.getInt("anzahl_karten_sammlung"),
                        rs.getInt("anzahl_karten_gesammelt"),
                        rs.getDouble("haben_relativ"),
                        rs.getInt("ordner_id")
                };
                model.addRow(row);
            }

            // Erstelle die JTable mit dem Model

            table = new JTable(model);
            table.setRowHeight(40);
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
            add(scrollPane);

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


            // Tool-Tip-Texte

            CustomHeaderRenderer.toolTipMaker(table, "ID der Erweiterung", 0);
            CustomHeaderRenderer.toolTipMaker(table, "Name der Erweiterung", 1);
            CustomHeaderRenderer.toolTipMaker(table, "Zyklus, in dem die Erweiterung erschienen ist", 2);
            CustomHeaderRenderer.toolTipMaker(table, "Abkürzung der Erweiterung", 3);
            CustomHeaderRenderer.toolTipMaker(table, "Jahr, in dem die Erweiterung erschienen ist", 4);
            CustomHeaderRenderer.toolTipMaker(table, "Anzahl der 'normalen' Karten in der Sammlung, ohne Extra-Karten", 5);
            CustomHeaderRenderer.toolTipMaker(table, "Anzahl der Karten, die bereits gesammelt wurden", 6);
            CustomHeaderRenderer.toolTipMaker(table, "Verhältnis der gesammelten Karten zur Anzahl der Karten in der Sammlung", 7);
            CustomHeaderRenderer.toolTipMaker(table, "Ordner, in dem sich die Erweiterung befindet", 8);

            // Filter

            // Filter fuer Zyklus

            String[] zyklusFilter = ValuesToStringDB.getZyklusErweiterung(true);
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
                    FilterView.filternNachString(selektierterZyklus, table, 2);
                }
            });

            // Filter fuer Jahr

            Integer[] jahrFilter = ValuesToStringDB.getJahrErweiterung();
            JComboBox<Integer> jahrFilterComboBox = new JComboBox<>(jahrFilter);
            JLabel jahrFilterLabel = new JLabel("Nach Jahr filtern: ");
            jahrFilterLabel.setFont(Schrift.normal());
            jahrFilterComboBox.setFont(Schrift.normal());

            jahrFilterComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Integer selektiertesJahr = (Integer) jahrFilterComboBox.getSelectedItem();
                    FilterView.filternNachInteger(selektiertesJahr, table, 4);
                }
            });

            // Filter fuer Ordner

            Integer[] ordnerFilter = ValuesToStringDB.getOrdnerErweiterung();
            JComboBox<Integer> ordnerFilterComboBox = new JComboBox<>(ordnerFilter);
            JLabel ordnerFilterLabel = new JLabel("Nach Ordner filtern: ");
            ordnerFilterLabel.setFont(Schrift.normal());
            ordnerFilterComboBox.setFont(Schrift.normal());

            ordnerFilterComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Integer selektierterOrdner = (Integer) ordnerFilterComboBox.getSelectedItem();
                    FilterView.filternNachInteger(selektierterOrdner, table, 8);
                }
            });

            // Aufbau der Panel

            GridBagConstraints gbc = new GridBagConstraints();

            // Panel fuer die Filter
            JPanel filterPanel = new JPanel();
            filterPanel.setPreferredSize(new Dimension(250, 100));
            filterPanel.setBackground(Colors.JAVA_COLOR_YELLOW);

            // Komponenten hinzufuegen
            AddComponentsToPanel.addLabelAndComboBox(filterPanel, zyklusFilterLabel, zyklusFilterComboBox, gbc, 0, 0);
            filterPanel.add(zyklusFilterComboBox);

            AddComponentsToPanel.addLabelAndComboBox(filterPanel, jahrFilterLabel, jahrFilterComboBox, gbc, 2, 0);
            filterPanel.add(jahrFilterComboBox);

            AddComponentsToPanel.addLabelAndComboBox(filterPanel, ordnerFilterLabel, ordnerFilterComboBox, gbc, 4, 0);
            filterPanel.add(ordnerFilterComboBox);

            JButton erweiterungenHinzufuegen = Buttons.btnErweiterungenHinzufuegen(Schrift.schriftartButtons());
            Borders.buttonBorder(erweiterungenHinzufuegen, Color.black);
            filterPanel.add(erweiterungenHinzufuegen);

            JButton erweiterungenBearbeiten = Buttons.btnErweiterungenBearbeiten(Schrift.schriftartButtons());
            Borders.buttonBorder(erweiterungenBearbeiten, Color.black);
            filterPanel.add(erweiterungenBearbeiten);

            add(filterPanel, BorderLayout.NORTH);
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        add(Buttons.buttonAnzeigen(), BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }
}
