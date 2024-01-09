/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package views;import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PokemonKartenErweiterungenView extends JFrame {
    public static final Color JAVA_COLOR_PINK = new Color(255, 102, 255);
    public static final Color JAVA_COLOR_HELLBLAU = new Color(51, 102, 255);
    public static final Color JAVA_COLOR_ORANGE = new Color(255, 153, 51);
    public static final Color JAVA_COLOR_TUERKIS = new Color(0, 153, 153);

    public JTable table;

    public PokemonKartenErweiterungenView() {
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

            // Tool-Tip-Texte
            String tooltipTextID = "ID für die Erweiterung";
            table.getColumnModel().getColumn(0).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextID));

            String tooltipTextErweiterungName = "Name der Erweiterung";
            table.getColumnModel().getColumn(1).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextErweiterungName));

            String tooltipTextZyklus = "Zyklus, in dem die Erweiterung erschienen ist";
            table.getColumnModel().getColumn(2).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextZyklus));

            String tooltipTextAbkuerzung = "Abkürzung der Erweiterung";
            table.getColumnModel().getColumn(3).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextAbkuerzung));

            String tooltipTextJahr = "Jahr, in dem die Erweiterung erschienen ist";
            table.getColumnModel().getColumn(4).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextJahr));

            String tooltipTextAnzahlSammlung = "Anzahl der 'normalen' Karten in der Sammlung, ohne Extra-Karten";
            table.getColumnModel().getColumn(5).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextAnzahlSammlung));

            String tooltipTextAnzahlGesammelt = "Anzahl der Karten, die bereits gesammelt wurden";
            table.getColumnModel().getColumn(6).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextAnzahlGesammelt));

            String tooltipTextHabenRelativ = "Verhältnis der gesammelten Karten zur Anzahl der Karten in der Sammlung";
            table.getColumnModel().getColumn(7).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextHabenRelativ));

            String tooltipTextOrdnerID = "Ordner, in dem sich die Erweiterung befindet";
            table.getColumnModel().getColumn(8).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextOrdnerID));


            JTableHeader header = table.getTableHeader();
            header.setReorderingAllowed(false);

            // Setze die Tabelle in ein ScrollPane
            JScrollPane scrollPane = new JScrollPane(table);

            Font font = new Font("Arial", Font.PLAIN, 20);
            table.setFont(font);
            header.setFont(font);

            header.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int columnIndex = table.columnAtPoint(e.getPoint());
                    sorter.toggleSortOrder(columnIndex);
                }
            });

            // Füge das ScrollPane zum Frame hinzu
            add(scrollPane);

            JPanel filterPanel = new JPanel();
            filterPanel.setPreferredSize(new Dimension(200, 100));
            filterPanel.setBackground(Color.yellow);


            // Filter fuer Zyklus
            String[] zyklusFilter = {"Alle", "Karmesin & Purpur", "Schwert & Schild", "Sonne & Mond", "XY",
                    "Schwarz & Weiß", "HeartGold & SoulSilver", "Platin", "Diamant & Perl", "EX", "e-Card-Serie",
                    "Neo-Serie", "Basis-Serie", "Promos & Specials", "EX Trainer Kit"};
            JComboBox<String> zyklusFilterComboBox = new JComboBox<>(zyklusFilter);
            JLabel zyklusFilterLabel = new JLabel("Nach Zyklus filtern: ");

            zyklusFilterComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selektierterZyklus = (String) zyklusFilterComboBox.getSelectedItem();
                    FilterView.filternNachString(selektierterZyklus, table, 2);
                }
            });

            GridBagConstraints gbc = new GridBagConstraints();

            AddComponentsToPanel.addLabelAndComboBox(filterPanel, zyklusFilterLabel, zyklusFilterComboBox, gbc, 0, 0);
            filterPanel.add(zyklusFilterComboBox);

            // Filter fuer Jahr
            Integer[] jahrFilter = {0, 2024, 2023, 2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013, 2012,
                    2011, 2010, 2009, 2008, 2007, 2006, 2005, 2004, 2003, 2002, 2001, 2000};
            JComboBox<Integer> jahrFilterComboBox = new JComboBox<>(jahrFilter);
            JLabel jahrFilterLabel = new JLabel("Nach Jahr filtern: ");

            jahrFilterComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Integer selektiertesJahr = (Integer) jahrFilterComboBox.getSelectedItem();
                    FilterView.filternNachInteger(selektiertesJahr, table, 4);
                }
            });

            AddComponentsToPanel.addLabelAndComboBox(filterPanel, jahrFilterLabel, jahrFilterComboBox, gbc, 2, 0);
            filterPanel.add(jahrFilterComboBox);

            // Filter fuer Ordner
            Integer[] ordnerFilter = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
            JComboBox<Integer> ordnerFilterComboBox = new JComboBox<>(ordnerFilter);
            JLabel ordnerFilterLabel = new JLabel("Nach Ordner filtern: ");

            ordnerFilterComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Integer selektierterOrdner = (Integer) ordnerFilterComboBox.getSelectedItem();
                    FilterView.filternNachInteger(selektierterOrdner, table, 8);
                }
            });

            AddComponentsToPanel.addLabelAndComboBox(filterPanel, ordnerFilterLabel, ordnerFilterComboBox, gbc, 4, 0);
            filterPanel.add(ordnerFilterComboBox);


            add(filterPanel, BorderLayout.EAST);
        } catch (SQLException e) {
            System.out.println(e);
        }

        add(MenueAnzeigeUnten.menueAnzeigeUnten(), BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new PokemonKartenErweiterungenView().setVisible(true);
    }
}
