import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PokemonKarten extends JFrame {

    public PokemonKarten() {
        setTitle("Willkommen");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        // Besonderheiten anzeigen
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

        JPanel panel = new JPanel();
        panel.add(btnInsert);
        panel.add(btnBesonderheitenView);
        panel.add(btnSeltenheitenView);
        panel.add(btnErweiterungenView);
        panel.add(btnOrdnerView);
        panel.add(btnSammlungView);

        add(panel);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PokemonKarten().setVisible(true));
    }
}

