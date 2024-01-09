/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package funktionen;import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import guis.PokemonKartenBearbeiten;
import views.PokemonKartenBesonderheitenView;
import views.PokemonKartenErweiterungenView;
import views.PokemonKartenOrdnerView;
import views.PokemonKartenSammlungView;
import views.PokemonKartenSeltenheitenView;

/**
 * Die Methode in dieser Klasse bildet das Menue, das unten in den einzelnen Views und GUIs angezeigt wird.
 */
public class MenueAnzeigeUnten {

    private static void aktuellesFensterSchliessen(JButton button) {
        // Hole das aktuelle Fenster
        Window window = SwingUtilities.windowForComponent(button);

        // Schließe das Fenster
        if (window != null) {
            window.dispose();
        }
    }
    public static JPanel menueAnzeigeUnten() {
        final Color JAVA_COLOR_PINK = new Color(255, 102, 255);
        final Color JAVA_COLOR_HELLBLAU = new Color(51, 102, 255);
        final Color JAVA_COLOR_ORANGE = new Color(255, 153, 51);
        final Color JAVA_COLOR_TUERKIS = new Color(0, 153, 153);

        // Menue-Anzeige
        JButton btnInsert = new JButton("Sammlung bearbeiten");
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
                aktuellesFensterSchliessen(btnInsert);
            }
        });

        // Besonderheiten anzeigen
        btnBesonderheitenView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenBesonderheitenView pokemonKartenBesonderheitenView = new PokemonKartenBesonderheitenView();
                pokemonKartenBesonderheitenView.setVisible(true);
                aktuellesFensterSchliessen(btnBesonderheitenView);
            }
        });

        // Seltenheiten anzeigen
        btnSeltenheitenView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenSeltenheitenView pokemonKartenSeltenheitenView = new PokemonKartenSeltenheitenView();
                pokemonKartenSeltenheitenView.setVisible(true);
                aktuellesFensterSchliessen(btnSeltenheitenView);
            }
        });

        // Erweiterungen anzeigen
        btnErweiterungenView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenErweiterungenView pokemonKartenErweiterungenView = new PokemonKartenErweiterungenView();
                pokemonKartenErweiterungenView.setVisible(true);
                aktuellesFensterSchliessen(btnErweiterungenView);
            }
        });

        // Ordner anzeigen
        btnOrdnerView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenOrdnerView pokemonKartenOrdnerView = new PokemonKartenOrdnerView();
                pokemonKartenOrdnerView.setVisible(true);
                aktuellesFensterSchliessen(btnOrdnerView);
            }
        });

        // Sammlung anzeigen
        btnSammlungView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenSammlungView pokemonKartenSammlungView = new PokemonKartenSammlungView();
                pokemonKartenSammlungView.setVisible(true);
                aktuellesFensterSchliessen(btnSammlungView);
            }
        });

        JButton btnBack = new JButton("Zurück");
        btnBack.addActionListener(e -> {
            PokemonKarten pokemonKarten = new PokemonKarten();
            pokemonKarten.setVisible(true);
            aktuellesFensterSchliessen(btnBack);
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
        return panel;
    }
}

