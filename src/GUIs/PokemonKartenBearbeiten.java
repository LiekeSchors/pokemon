/*
 * Copyright (c) 2023.
 * Lieke Schors
 */

package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PokemonKartenBearbeiten extends JFrame {
    public static final Color JAVA_COLOR_PINK = new Color(255, 102, 255);
    public static final Color JAVA_COLOR_HELLBLAU = new Color(51, 102, 255);
    public static final Color JAVA_COLOR_ORANGE = new Color(255, 153, 51);
    public static final Color JAVA_COLOR_TUERKIS = new Color(0, 153, 153);

    public PokemonKartenBearbeiten() {
        setTitle("Sammlung bearbeiten");
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));

        JPanel hinzufuegenPanel = new JPanel(new GridLayout(5, 1));
        JButton btnBesonderheitenHinzufuegen = new JButton("Besonderheit hinzufügen");
        JButton btnSeltenheitenHinzufuegen = new JButton("Seltenheit hinzufügen");
        JButton btnErweiterungenHinzufuegen = new JButton("Erweiterung hinzufügen");
        JButton btnOrdnerHinzufuegen = new JButton("Ordner hinzufügen");
        JButton btnKartenHinzufuegen = new JButton("Karte hinzufügen");

        // Besonderheiten bearbeiten
        btnBesonderheitenHinzufuegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BesonderheitenBearbeitenGUI besonderheitenBearbeitenGUI = new BesonderheitenBearbeitenGUI();
                besonderheitenBearbeitenGUI.setVisible(true);
                setVisible(false);
            }
        });

        // Seltenheiten bearbeiten
        btnSeltenheitenHinzufuegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BesonderheitenBearbeitenGUI seltenheitenBearbeitenGUI = new BesonderheitenBearbeitenGUI();
                seltenheitenBearbeitenGUI.setVisible(true);
                setVisible(false);
            }
        });

        // Erweiterungen bearbeiten
        btnErweiterungenHinzufuegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErweiterungenBearbeitenGUI erweiterungenBearbeitenGUI = new ErweiterungenBearbeitenGUI();
                erweiterungenBearbeitenGUI.setVisible(true);
                setVisible(false);
            }
        });

        // Ordner bearbeiten
        btnOrdnerHinzufuegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdnerBearbeitenGUI ordnerBearbeitenGUI = new OrdnerBearbeitenGUI();
                ordnerBearbeitenGUI.setVisible(true);
                setVisible(false);
            }
        });

        // Karten bearbeiten
        btnKartenHinzufuegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KartenBearbeitenGUI kartenBearbeiten = new KartenBearbeitenGUI();
                kartenBearbeiten.setVisible(true);
                setVisible(false);
            }
        });

        btnBesonderheitenHinzufuegen.setBackground(JAVA_COLOR_HELLBLAU);
        btnSeltenheitenHinzufuegen.setBackground(Color.green);
        btnErweiterungenHinzufuegen.setBackground(Color.yellow);
        btnOrdnerHinzufuegen.setBackground(JAVA_COLOR_ORANGE);
        btnKartenHinzufuegen.setBackground(JAVA_COLOR_TUERKIS);

        hinzufuegenPanel.add(btnBesonderheitenHinzufuegen);
        hinzufuegenPanel.add(btnSeltenheitenHinzufuegen);
        hinzufuegenPanel.add(btnErweiterungenHinzufuegen);
        hinzufuegenPanel.add(btnOrdnerHinzufuegen);
        hinzufuegenPanel.add(btnKartenHinzufuegen);

        add(hinzufuegenPanel);


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
}
