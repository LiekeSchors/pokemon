import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                        rs.getInt("ordner_id")
                };
                model.addRow(row);
            }

            // Erstelle die JTable mit dem Model
            table = new JTable(model);
            table.setRowHeight(40);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

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

            String tooltipTextOrdnerID = "Ordner, in dem sich die Erweiterung befindet";
            table.getColumnModel().getColumn(7).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipTextOrdnerID));


            JTableHeader header = table.getTableHeader();
            header.setReorderingAllowed(false);

            // Setze die Tabelle in ein ScrollPane
            JScrollPane scrollPane = new JScrollPane(table);

            Font font = new Font("Arial", Font.PLAIN, 20);
            table.setFont(font);
            header.setFont(font);

            // Füge das ScrollPane zum Frame hinzu
            add(scrollPane);

        } catch (SQLException e) {
            System.out.println(e);
        }

        JButton btnInsert = new JButton("Karten hinzufügen");
        JButton btnBesonderheitenView = new JButton("Besonderheiten anzeigen");
        JButton btnSeltenheitenView = new JButton("Seltenheiten anzeigen");
        JButton btnErweiterungenView = new JButton("Erweiterungen anzeigen");
        JButton btnOrdnerView = new JButton("Ordner anzeigen");
        JButton btnSammlungView = new JButton("Sammlung anzeigen");

        // Karten hinzufuegen
        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenBearbeiten pokemonKartenBearbeiten = new PokemonKartenBearbeiten();
                pokemonKartenBearbeiten.setVisible(true);
                setVisible(false);
            }
        });

        // Besonderheiten anzeigen
        btnBesonderheitenView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenBesonderheitenView pokemonKartenBesonderheitenView = new PokemonKartenBesonderheitenView();
                pokemonKartenBesonderheitenView.setVisible(true);
                setVisible(false);
            }
        });

        // Seltenheiten anzeigen
        btnSeltenheitenView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenSeltenheitenView pokemonKartenSeltenheitenView = new PokemonKartenSeltenheitenView();
                pokemonKartenSeltenheitenView.setVisible(true);
                setVisible(false);
            }
        });

        // Erweiterungen anzeigen
        btnErweiterungenView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenErweiterungenView pokemonKartenErweiterungenView = new PokemonKartenErweiterungenView();
                pokemonKartenErweiterungenView.setVisible(true);
                setVisible(false);
            }
        });

        // Ordner anzeigen
        btnOrdnerView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenOrdnerView pokemonKartenOrdnerView = new PokemonKartenOrdnerView();
                pokemonKartenOrdnerView.setVisible(true);
                setVisible(false);
            }
        });

        // Sammlung anzeigen
        btnSammlungView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenSammlungView pokemonKartenSammlungView = new PokemonKartenSammlungView();
                pokemonKartenSammlungView.setVisible(true);
                setVisible(false);
            }
        });

        JButton btnBack = new JButton("Zurück");
        btnBack.addActionListener(e -> {
            PokemonKarten pokemonKarten = new PokemonKarten();
            pokemonKarten.setVisible(true);
            setVisible(false);
        });

        btnInsert.setBackground(JAVA_COLOR_PINK);
        btnBesonderheitenView.setBackground(JAVA_COLOR_HELLBLAU);
        btnSeltenheitenView.setBackground(Color.green);
        btnErweiterungenView.setBackground(Color.yellow);
        btnOrdnerView.setBackground(JAVA_COLOR_ORANGE);
        btnSammlungView.setBackground(JAVA_COLOR_TUERKIS);

        JPanel panel = new JPanel();
        panel.add(btnInsert);
        panel.add(btnBesonderheitenView);
        panel.add(btnSeltenheitenView);
        panel.add(btnErweiterungenView);
        panel.add(btnOrdnerView);
        panel.add(btnSammlungView);
        panel.add(btnBack);

        add(panel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new PokemonKartenErweiterungenView().setVisible(true);
    }
}
