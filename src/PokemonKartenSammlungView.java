/*
 * Copyright (c) 2023.
 * Lieke Schors
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

public class PokemonKartenSammlungView extends JFrame {
    public static final Color JAVA_COLOR_PINK = new Color(255, 102, 255);
    public static final Color JAVA_COLOR_HELLBLAU = new Color(51, 102, 255);
    public static final Color JAVA_COLOR_ORANGE = new Color(255, 153, 51);
    public static final Color JAVA_COLOR_TUERKIS = new Color(0, 153, 153);

    private JTable table;

    public PokemonKartenSammlungView() {
        setTitle("Karten anzeigen");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));

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
            model.addColumn("Besonderheit-ID");
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
                        rs.getInt("besonderheit_id"),
                        rs.getInt("seltenheit_id"),
                        rs.getDouble("wert_in_euro"),
                        rs.getString("wert_eingegeben_am"),
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

            // Tool-Tip-Texte
            String tooltipTextID = "<html>Karten-ID<br>Fortlaufende und eindeutige Nummer für die Karte</html>";
            table.getColumnModel().getColumn(0).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextID));

            String tooltipTextErweiterung = "Abkürzung für die Erweiterung";
            table.getColumnModel().getColumn(1).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextErweiterung));

            String tooltipTextName = "Name des Pokémons";
            table.getColumnModel().getColumn(2).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextName));

            String tooltipTextEnergieTyp = "Energietyp des Pokémons";
            table.getColumnModel().getColumn(3).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextEnergieTyp));

            String tooltipTextEntwicklung = "Pokémon, aus dem sich dieses Pokémon entwickelt hat";
            table.getColumnModel().getColumn(4).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextEntwicklung));

            String tooltipTextKartenNr = "Kartennummer in der jeweiligen Erweiterung";
            table.getColumnModel().getColumn(5).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextKartenNr));

            String tooltipTextBesonderheitenID = "<html>Identifikationsnummer für die Besonderheit<br><ul><li>0: Ohne</li><li>1: Glitzer-Karte</li><li>2: Glitzer-Rand</li><li>3: Glitzer-Komplett</li><li>4: Gold-Shiny</li></ul></html>";
            table.getColumnModel().getColumn(6).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextBesonderheitenID));

            String tooltipTextSeltenheitenID = "<html>Identifikationsnummer für die Seltenheit<br><ol><li>common</li><li>uncommon</li><li>rare</li><li>holo rare</li><li>double rare</li><li>triple star</li><li>rare illustration</li><li>special illustration rare</li><li>hype rare</li><li>ultra rare</li><li>ultra rare double</li><li>shiny holo rare</li><li>ultra rare fullart</li><li>secret rare</li><li>silver star</li><li>holo rare legende</li><li>promo</li><li>amazing rare</li></ol></html>";
            table.getColumnModel().getColumn(7).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextSeltenheitenID));

            String tooltipTextWertEuro = "Wert der Karte in Euro";
            table.getColumnModel().getColumn(8).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextWertEuro));

            String tooltipTextDatumWert = "Datum, wann der Wert auf cardmarket.com abgerufen wurde";
            table.getColumnModel().getColumn(9).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextDatumWert));

            String tooltipTextZusatzName = "Namenszusätze wie 'V' und 'V Star'";
            table.getColumnModel().getColumn(10).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextZusatzName));

            String tooltipTextZusatzTrainer = "Nähere Beschreibung der 'Trainerart'";
            table.getColumnModel().getColumn(11).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextZusatzTrainer));

            String tooltipTextZusatzKartenNr = "<html>Karten mit anderer Kartennummer als gewöhnlich<br>(z.B. Kombi aus Buchstaben und Zahlen)</html>";
            table.getColumnModel().getColumn(12).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextZusatzKartenNr));

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
            GridBagConstraints gbc = new GridBagConstraints();

            JPanel filterPanel = new JPanel();
            filterPanel.setPreferredSize(new Dimension(200, 100));
            filterPanel.setBackground(JAVA_COLOR_TUERKIS);


            // Filter fuer Abkuerzung der Erweiterung
            String[] abkuerzungErweiterungFilter = ValuesToStringForFilter.getEindeutigeErweiterungAbkuerzung();
            for (String erweiterungAbkuerzung : abkuerzungErweiterungFilter) {
            }

            JComboBox<String> abkuerzungErweiterungFilterComboBox = new JComboBox<>(abkuerzungErweiterungFilter);
            JLabel abkuerzungErweiterungFilterLabel = new JLabel("Abkürzung Erweiterung: ");
            abkuerzungErweiterungFilterLabel.setFont(font);
            abkuerzungErweiterungFilterComboBox.setFont(font);

            abkuerzungErweiterungFilterComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selektierteAbkuerzungErweiterung = (String) abkuerzungErweiterungFilterComboBox.getSelectedItem();
                    FilterView.filternNachString(selektierteAbkuerzungErweiterung, table, 1);
                }
            });

            AddComponentsToPanel.addLabelAndComboBox(filterPanel, abkuerzungErweiterungFilterLabel, abkuerzungErweiterungFilterComboBox, gbc, 0, 0);
            filterPanel.add(abkuerzungErweiterungFilterComboBox);


            // Filter fuer Energie-Typ
            String[] energieTypFilter = ValuesToStringForFilter.getEnergieTyp();
            for (String energieTyp : energieTypFilter) {
            }

            JComboBox<String> energieTypFilterComboBox = new JComboBox<>(energieTypFilter);
            JLabel energieTypFilterLabel = new JLabel("Energie-Typ:");
            energieTypFilterLabel.setFont(font);
            energieTypFilterComboBox.setFont(font);

            energieTypFilterComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selektierterEnergieTyp = (String) energieTypFilterComboBox.getSelectedItem();
                    FilterView.filternNachString(selektierterEnergieTyp, table, 3);
                }
            });

            AddComponentsToPanel.addLabelAndComboBox(filterPanel, energieTypFilterLabel, energieTypFilterComboBox, gbc, 0, 2);
            filterPanel.add(energieTypFilterComboBox);

            // Filter fuer Besonderheit
            Integer[] besonderheitFilter = {0, 1, 2, 3, 4};
            JComboBox<Integer> besonderheitFilterComboBox = new JComboBox<>(besonderheitFilter);
            JLabel besonderheitFilterLabel = new JLabel("Nach Besonderheit filtern: ");
            besonderheitFilterLabel.setFont(font);
            besonderheitFilterComboBox.setFont(font);

            besonderheitFilterComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Integer selektierteBesonderheit = (Integer) besonderheitFilterComboBox.getSelectedItem();
                    FilterView.filternNachInteger(selektierteBesonderheit, table, 6);
                }
            });

            AddComponentsToPanel.addLabelAndComboBox(filterPanel, besonderheitFilterLabel, besonderheitFilterComboBox, gbc, 0, 4);
            filterPanel.add(besonderheitFilterComboBox);

            /*

            // Filter fuer Seltenheit-ID
            Integer[] seltenheitIDFilter = ValuesToStringForFilter.getSeltenheitID();
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
                    FilterView.filternNachInteger(selektierteSeltenheit, table, 4);
                }
            });

            AddComponentsToPanel.addLabelAndComboBox(filterPanel, seltenheitFilterLabel, seltenheitFilterComboBox, gbc, 0, 6);
            filterPanel.add(seltenheitFilterComboBox);

            // Filter fuer Beschreibung Seltenheit
            String[] beschreibungSeltenheitFilter = ValuesToStringForFilter.getBeschreibungSeltenheit();
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
                    FilterView.filternNachString(selektierteBeschreibungSeltenheit, table, 4);
                }
            });

            AddComponentsToPanel.addLabelAndComboBox(filterPanel, beschreibungSeltenheitFilterLabel, beschreibungSeltenheitFilterComboBox, gbc, 0, 8);
            filterPanel.add(beschreibungSeltenheitFilterComboBox);
            */

            // Filter nach Wert in Euro


            add(filterPanel, BorderLayout.NORTH);


        } catch (SQLException e) {
            System.out.println(e);
        }

        add(MenueAnzeigeUnten.menueAnzeigeUnten(), BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new PokemonKartenSammlungView().setVisible(true);
    }
}
