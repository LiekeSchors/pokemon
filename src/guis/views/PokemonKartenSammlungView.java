/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package guis.views;

import static layout.Colors.JAVA_COLOR_TUERKIS;
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
import funktionen.FilterViews;
import funktionen.ValuesToStringDB;
import layout.Borders;
import layout.Schrift;

public class PokemonKartenSammlungView extends JFrame {

    private JTable table;

    public PokemonKartenSammlungView() {
        setTitle("Karten anzeigen");
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
            String sql = "SELECT * FROM sammlung ORDER BY karten_id";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();

            // Erstelle ein DefaultTableModel für die JTable
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Erweiterung");
            model.addColumn("Name");
            model.addColumn("Energie-Typ");
            model.addColumn("Entwicklung von");
            model.addColumn("Kartennr.");
            model.addColumn("Besonderheit");
            model.addColumn("Seltenheit-ID");
            model.addColumn("Wert in €");
            model.addColumn("Datum Werteingabe");
            model.addColumn("Zusatz Name");
            model.addColumn("Zusatz Trainer");
            model.addColumn("Zusatz Kartennummer");

            while (rs.next()) {
                // Füge die Zeilen zum Model hinzu
                Object[] row = {
                        rs.getInt("karten_id"),
                        rs.getString("erweiterung_abkuerzung"),
                        rs.getString("pokemon_name"),
                        rs.getString("energie_typ"),
                        rs.getString("ursprung_name"),
                        rs.getInt("karten_nr"),
                        rs.getString("besonderheit"),
                        rs.getString("seltenheit_id"),
                        rs.getDouble("wert_in_euro"),
                        rs.getDate("wert_eingegeben_am"),
                        rs.getString("name_zusatz"),
                        rs.getString("trainer_zusatz"),
                        rs.getString("kartennr_zusatz")
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
            CustomHeaderRenderer.toolTipMaker(table, "<html>Karten-ID<br>Fortlaufende und eindeutige Nummer für die Karte</html>", 0);
            CustomHeaderRenderer.toolTipMaker(table, "Abkürzung für die Erweiterung", 1);
            CustomHeaderRenderer.toolTipMaker(table, "Name des Pokémons", 2);
            CustomHeaderRenderer.toolTipMaker(table, "Energietyp des Pokémons", 3);
            CustomHeaderRenderer.toolTipMaker(table, "Pokémon, aus dem sich dieses Pokémon entwickelt hat", 4);
            CustomHeaderRenderer.toolTipMaker(table, "Kartennummer in der jeweiligen Erweiterung", 5);
            CustomHeaderRenderer.toolTipMaker(table, "<html>Identifikationsnummer für die Besonderheit<br><ul><li>0: Ohne</li><li>1: Glitzer-Karte</li><li>2: Glitzer-Rand</li><li>3: Glitzer-Komplett</li><li>4: Gold-Shiny</li></ul></html>", 6);
            CustomHeaderRenderer.toolTipMaker(table, "<html>Identifikationsnummer für die Seltenheit<br><ol><li>common</li><li>uncommon</li><li>rare</li><li>holo rare</li><li>double rare</li><li>triple star</li><li>rare illustration</li><li>special illustration rare</li><li>hype rare</li><li>ultra rare</li><li>ultra rare double</li><li>shiny holo rare</li><li>ultra rare fullart</li><li>secret rare</li><li>silver star</li><li>holo rare legende</li><li>promo</li><li>amazing rare</li></ol></html>", 7);
            CustomHeaderRenderer.toolTipMaker(table, "Wert der Karte in Euro", 8);
            CustomHeaderRenderer.toolTipMaker(table, "Datum, wann der Wert auf cardmarket.com abgerufen wurde", 9);
            CustomHeaderRenderer.toolTipMaker(table, "Namenszusätze wie 'V' und 'V Star'", 10);
            CustomHeaderRenderer.toolTipMaker(table, "Nähere Beschreibung der 'Trainerart'", 11);
            CustomHeaderRenderer.toolTipMaker(table, "<html>Karten mit anderer Kartennummer als gewöhnlich<br>(z.B. Kombi aus Buchstaben und Zahlen)</html>", 12);


            // Filter

            // Filter fuer Abkuerzung der Erweiterung

            String[] abkuerzungErweiterungFilter = ValuesToStringDB.getEindeutigeErweiterungAbkuerzung(true);
            JComboBox<String> abkuerzungErweiterungFilterComboBox = new JComboBox<>(abkuerzungErweiterungFilter);
            JLabel abkuerzungErweiterungFilterLabel = new JLabel("Abkürzung Erweiterung: ");
            abkuerzungErweiterungFilterLabel.setFont(Schrift.normal());
            abkuerzungErweiterungFilterComboBox.setFont(Schrift.normal());

            abkuerzungErweiterungFilterComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selektierteAbkuerzungErweiterung = (String) abkuerzungErweiterungFilterComboBox.getSelectedItem();
                    FilterViews.filternNachTyp(selektierteAbkuerzungErweiterung, table, 1);
                }
            });

            // Filter fuer Energie-Typ
            String[] energieTypFilter = ValuesToStringDB.getEnergieTyp(true);
            JComboBox<String> energieTypFilterComboBox = new JComboBox<>(energieTypFilter);
            JLabel energieTypFilterLabel = new JLabel("Energie-Typ:");
            energieTypFilterLabel.setFont(Schrift.normal());
            energieTypFilterComboBox.setFont(Schrift.normal());

            energieTypFilterComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selektierterEnergieTyp = (String) energieTypFilterComboBox.getSelectedItem();
                    FilterViews.filternNachTyp(selektierterEnergieTyp, table, 3);
                }
            });

            // Filter fuer Besonderheit

            String[] besonderheitFilter = ValuesToStringDB.getBeschreibungBesonderheit(true);
            JComboBox<String> besonderheitFilterComboBox = new JComboBox<>(besonderheitFilter);
            JLabel besonderheitFilterLabel = new JLabel("Nach Besonderheit filtern: ");
            besonderheitFilterLabel.setFont(Schrift.normal());
            besonderheitFilterComboBox.setFont(Schrift.normal());

            besonderheitFilterComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selektierteBesonderheit = (String) besonderheitFilterComboBox.getSelectedItem();
                    FilterViews.filternNachTyp(selektierteBesonderheit, table, 6);
                }
            });

            /*

            // Filter fuer Seltenheit-ID
            Integer[] seltenheitIDFilter = funktionen.ValuesToStringDB.getSeltenheitID();
            for (Integer seltenheitID : seltenheitIDFilter) {
            }
            JComboBox<Integer> seltenheitFilterComboBox = new JComboBox<>(seltenheitIDFilter);
            JLabel seltenheitFilterLabel = new JLabel("Nach Seltenheit filtern: ");
            seltenheitFilterLabel.setFont(font);
            seltenheitFilterComboBox.setFont(font);

            seltenheitFilterComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Integer selektierteSeltenheit = (Integer) seltenheitFilterComboBox.getSelectedItem();
                    funktionen.FilterViews.filternNachInteger(selektierteSeltenheit, table, 4);
                }
            });

            funktionen.AddComponentsToPanel.addLabelAndComboBox(filterPanel, seltenheitFilterLabel, seltenheitFilterComboBox, gbc, 0, 6);
            filterPanel.add(seltenheitFilterComboBox);

            // Filter fuer Beschreibung Seltenheit
            String[] beschreibungSeltenheitFilter = funktionen.ValuesToStringDB.getBeschreibungSeltenheit();
            for (String beschreibungSeltenheit : beschreibungSeltenheitFilter) {
            }
            JComboBox<String> beschreibungSeltenheitFilterComboBox = new JComboBox<>(beschreibungSeltenheitFilter);
            JLabel beschreibungSeltenheitFilterLabel = new JLabel("Beschreibung Seltenheit: ");
            seltenheitFilterLabel.setFont(font);
            seltenheitFilterComboBox.setFont(font);

            beschreibungSeltenheitFilterComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selektierteBeschreibungSeltenheit = (String) beschreibungSeltenheitFilterComboBox.getSelectedItem();
                    funktionen.FilterViews.filternNachString(selektierteBeschreibungSeltenheit, table, 4);
                }
            });

            funktionen.AddComponentsToPanel.addLabelAndComboBox(filterPanel, beschreibungSeltenheitFilterLabel, beschreibungSeltenheitFilterComboBox, gbc, 0, 8);
            filterPanel.add(beschreibungSeltenheitFilterComboBox);
            */

            // Filter nach Wert in Euro

            JComboBox[] comboBoxes = {abkuerzungErweiterungFilterComboBox, energieTypFilterComboBox, besonderheitFilterComboBox};

            // Aufbau der Panel
            GridBagConstraints gbc = new GridBagConstraints();
            JPanel filterPanel = new JPanel();
            filterPanel.setPreferredSize(new Dimension(200, 100));
            filterPanel.setBackground(JAVA_COLOR_TUERKIS);

            // Komponenten hinzufuegen
            AddComponentsToPanel.addLabelAndComboBox(filterPanel, abkuerzungErweiterungFilterLabel, abkuerzungErweiterungFilterComboBox, gbc, 0, 0);
            filterPanel.add(abkuerzungErweiterungFilterComboBox);

            AddComponentsToPanel.addLabelAndComboBox(filterPanel, energieTypFilterLabel, energieTypFilterComboBox, gbc, 0, 2);
            filterPanel.add(energieTypFilterComboBox);

            AddComponentsToPanel.addLabelAndComboBox(filterPanel, besonderheitFilterLabel, besonderheitFilterComboBox, gbc, 0, 4);
            filterPanel.add(besonderheitFilterComboBox);

            JButton kartenHinzufuegen = Buttons.btnKartenHinzufuegen(Schrift.schriftartButtons());
            Borders.buttonBorder(kartenHinzufuegen, Color.WHITE);
            filterPanel.add(kartenHinzufuegen);

            JButton kartenBearbeiten = Buttons.btnKartenBearbeiten(Schrift.schriftartButtons());
            Borders.buttonBorder(kartenBearbeiten, Color.WHITE);
            filterPanel.add(kartenBearbeiten);
            filterPanel.add(Buttons.clearAllFilters(table, comboBoxes));

            add(filterPanel, BorderLayout.NORTH);

            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        add(Buttons.buttonAnzeigen(), BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }
}
