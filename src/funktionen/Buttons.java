/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package funktionen;

import static funktionen.OffenesFensterSchliessen.aktuellesFensterSchliessen;
import static layout.Colors.JAVA_COLOR_HELLBLAU;
import static layout.Colors.JAVA_COLOR_ORANGE;
import static layout.Colors.JAVA_COLOR_PINK;
import static layout.Colors.JAVA_COLOR_TUERKIS;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import guis.BesonderheitenBearbeitenGUI;
import guis.BesonderheitenHinzufuegenGUI;
import guis.ErweiterungenBearbeitenGUI;
import guis.ErweiterungenHinzufuegenGUI;
import guis.KartenBearbeitenGUI;
import guis.KartenHinzufuegenGUI;
import guis.OrdnerBearbeitenGUI;
import guis.OrdnerHinzufuegenGUI;
import views.PokemonKarten;
import views.PokemonKartenBearbeiten;
import guis.SeltenheitenBearbeitenGUI;
import guis.SeltenheitenHinzufuegenGUI;
import layout.Schrift;
import views.PokemonKartenBesonderheitenView;
import views.PokemonKartenErweiterungenView;
import views.PokemonKartenOrdnerView;
import views.PokemonKartenSammlungView;
import views.PokemonKartenSeltenheitenView;

/**
 * Diese Klasse enthaelt viel verwendete Buttons sowie Button-Panels
 */
public class Buttons {

    // Besonderheit
    public static JButton btnBesonderheitenHinzufuegen(Font font) {
        JButton btnBesonderheitenHinzufuegen = new JButton("Besonderheit hinzufügen");
        btnBesonderheitenHinzufuegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BesonderheitenHinzufuegenGUI besonderheitenHinzufuegenGUI = new BesonderheitenHinzufuegenGUI();
                besonderheitenHinzufuegenGUI.setVisible(true);
                aktuellesFensterSchliessen(btnBesonderheitenHinzufuegen);
            }
        });
        btnBesonderheitenHinzufuegen.setBackground(JAVA_COLOR_HELLBLAU);
        btnBesonderheitenHinzufuegen.setFont(font);

        return btnBesonderheitenHinzufuegen;
    }

    public static JButton btnBesonderheitenBearbeiten(Font font) {
        JButton btnBesonderheitenBearbeiten = new JButton("Besonderheit bearbeiten");
        btnBesonderheitenBearbeiten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BesonderheitenBearbeitenGUI besonderheitenBearbeitenGUI = new BesonderheitenBearbeitenGUI();
                besonderheitenBearbeitenGUI.setVisible(true);
                aktuellesFensterSchliessen(btnBesonderheitenBearbeiten);
            }
        });
        btnBesonderheitenBearbeiten.setBackground(JAVA_COLOR_HELLBLAU);
        btnBesonderheitenBearbeiten.setFont(font);

        return btnBesonderheitenBearbeiten;
    }

    public static JButton btnBesonderheitenAnzeigen(Font font) {
        JButton btnBesonderheitenView = new JButton("Besonderheiten anzeigen");
        btnBesonderheitenView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenBesonderheitenView pokemonKartenBesonderheitenView = new PokemonKartenBesonderheitenView();
                pokemonKartenBesonderheitenView.setVisible(true);
                aktuellesFensterSchliessen(btnBesonderheitenView);
            }
        });
        btnBesonderheitenView.setBackground(JAVA_COLOR_HELLBLAU);
        btnBesonderheitenView.setFont(font);

        return btnBesonderheitenView;
    }

    // Seltenheiten
    public static JButton btnSeltenheitenHinzufuegen(Font font) {
        JButton btnSeltenheitenHinzufuegen = new JButton("Seltenheit hinzufügen");
        btnSeltenheitenHinzufuegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SeltenheitenHinzufuegenGUI seltenheitenHinzufuegenGUI = new SeltenheitenHinzufuegenGUI();
                seltenheitenHinzufuegenGUI.setVisible(true);
                aktuellesFensterSchliessen(btnSeltenheitenHinzufuegen);
            }
        });
        btnSeltenheitenHinzufuegen.setBackground(Color.green);
        btnSeltenheitenHinzufuegen.setFont(font);

        return btnSeltenheitenHinzufuegen;
    }

    public static JButton btnSeltenheitenBearbeiten(Font font) {
        JButton btnSeltenheitenBearbeiten = new JButton("Seltenheit bearbeiten");
        btnSeltenheitenBearbeiten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SeltenheitenBearbeitenGUI seltenheitenBearbeitenGUI = new SeltenheitenBearbeitenGUI();
                seltenheitenBearbeitenGUI.setVisible(true);
                aktuellesFensterSchliessen(btnSeltenheitenBearbeiten);
            }
        });
        btnSeltenheitenBearbeiten.setFont(font);
        btnSeltenheitenBearbeiten.setBackground(Color.green);

        return btnSeltenheitenBearbeiten;
    }

    public static JButton btnSeltenheitenAnzeigen(Font font) {
        JButton btnSeltenheitenView = new JButton("Seltenheiten anzeigen");
        btnSeltenheitenView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenSeltenheitenView pokemonKartenSeltenheitenView = new PokemonKartenSeltenheitenView();
                pokemonKartenSeltenheitenView.setVisible(true);
                aktuellesFensterSchliessen(btnSeltenheitenView);
            }
        });
        btnSeltenheitenView.setBackground(Color.green);
        btnSeltenheitenView.setFont(font);

        return btnSeltenheitenView;
    }

    // Erweiterungen
    public static JButton btnErweiterungenHinzufuegen(Font font) {
        JButton btnErweiterungenHinzufuegen = new JButton("Erweiterung hinzufügen");
        btnErweiterungenHinzufuegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErweiterungenHinzufuegenGUI erweiterungenHinzufuegenGUI = new ErweiterungenHinzufuegenGUI();
                erweiterungenHinzufuegenGUI.setVisible(true);
                aktuellesFensterSchliessen(btnErweiterungenHinzufuegen);
            }
        });
        btnErweiterungenHinzufuegen.setBackground(Color.yellow);
        btnErweiterungenHinzufuegen.setFont(font);

        return btnErweiterungenHinzufuegen;
    }

    public static JButton btnErweiterungenBearbeiten(Font font) {
        JButton btnErweiterungenBearbeiten = new JButton("Erweiterungen bearbeiten");
        btnErweiterungenBearbeiten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErweiterungenBearbeitenGUI erweiterungenBearbeitenGUI = new ErweiterungenBearbeitenGUI();
                erweiterungenBearbeitenGUI.setVisible(true);
                aktuellesFensterSchliessen(btnErweiterungenBearbeiten);
            }
        });
        btnErweiterungenBearbeiten.setFont(font);
        btnErweiterungenBearbeiten.setBackground(Color.yellow);

        return btnErweiterungenBearbeiten;
    }

    public static JButton btnErweiterungenAnzeigen(Font font) {
        JButton btnErweiterungenView = new JButton("Erweiterungen anzeigen");
        btnErweiterungenView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenErweiterungenView pokemonKartenErweiterungenView = new PokemonKartenErweiterungenView();
                pokemonKartenErweiterungenView.setVisible(true);
                aktuellesFensterSchliessen(btnErweiterungenView);
            }
        });
        btnErweiterungenView.setBackground(Color.yellow);
        btnErweiterungenView.setFont(font);

        return btnErweiterungenView;
    }

    // Ordner
    public static JButton btnOrdnerHinzufuegen(Font font) {
        JButton btnOrdnerHinzufuegen = new JButton("Ordner hinzufügen");
        btnOrdnerHinzufuegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdnerHinzufuegenGUI ordnerHinzufuegenGUI = new OrdnerHinzufuegenGUI();
                ordnerHinzufuegenGUI.setVisible(true);
                aktuellesFensterSchliessen(btnOrdnerHinzufuegen);
            }
        });
        btnOrdnerHinzufuegen.setBackground(JAVA_COLOR_ORANGE);
        btnOrdnerHinzufuegen.setFont(font);

        return btnOrdnerHinzufuegen;
    }

    public static JButton btnOrdnerBearbeiten(Font font) {
        JButton btnOrdnerBearbeiten = new JButton("Ordner bearbeiten");
        btnOrdnerBearbeiten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdnerBearbeitenGUI ordnerBearbeitenGUI = new OrdnerBearbeitenGUI();
                ordnerBearbeitenGUI.setVisible(true);
                aktuellesFensterSchliessen(btnOrdnerBearbeiten);
            }
        });
        btnOrdnerBearbeiten.setFont(font);
        btnOrdnerBearbeiten.setBackground(JAVA_COLOR_ORANGE);

        return btnOrdnerBearbeiten;
    }

    public static JButton btnOrdnerAnzeigen(Font font) {
        JButton btnOrdnerView = new JButton("Ordner anzeigen");
        btnOrdnerView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenOrdnerView pokemonKartenOrdnerView = new PokemonKartenOrdnerView();
                pokemonKartenOrdnerView.setVisible(true);
                aktuellesFensterSchliessen(btnOrdnerView);
            }
        });
        btnOrdnerView.setBackground(JAVA_COLOR_ORANGE);
        btnOrdnerView.setFont(font);

        return btnOrdnerView;
    }

    // Sammlung
    public static JButton btnKartenHinzufuegen(Font font) {
        JButton btnKartenHinzufuegen = new JButton("Karten hinzufügen");
        btnKartenHinzufuegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KartenHinzufuegenGUI kartenHinzufuegenGUI = new KartenHinzufuegenGUI();
                kartenHinzufuegenGUI.setVisible(true);
                aktuellesFensterSchliessen(btnKartenHinzufuegen);
            }
        });
        btnKartenHinzufuegen.setBackground(JAVA_COLOR_TUERKIS);
        btnKartenHinzufuegen.setFont(font);

        return btnKartenHinzufuegen;
    }

    public static JButton btnKartenBearbeiten(Font font) {
        JButton btnKartenBearbeiten = new JButton("Karten bearbeiten");
        btnKartenBearbeiten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KartenBearbeitenGUI kartenBearbeitenGUI = new KartenBearbeitenGUI();
                kartenBearbeitenGUI.setVisible(true);
                aktuellesFensterSchliessen(btnKartenBearbeiten);
            }
        });
        btnKartenBearbeiten.setFont(font);
        btnKartenBearbeiten.setBackground(JAVA_COLOR_TUERKIS);

        return btnKartenBearbeiten;
    }

    public static JButton btnSammlungAnzeigen(Font font) {
        JButton btnSammlungView = new JButton("Sammlung anzeigen");
        btnSammlungView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenSammlungView pokemonKartenSammlungView = new PokemonKartenSammlungView();
                pokemonKartenSammlungView.setVisible(true);
                aktuellesFensterSchliessen(btnSammlungView);
            }
        });
        btnSammlungView.setBackground(JAVA_COLOR_TUERKIS);
        btnSammlungView.setFont(font);

        return btnSammlungView;
    }

    public static JButton btnSammlungBearbeiten(Font font) {
        JButton btnSammlungBearbeiten = new JButton("Sammlung bearbeiten");
        btnSammlungBearbeiten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonKartenBearbeiten pokemonKartenBearbeiten = new PokemonKartenBearbeiten();
                pokemonKartenBearbeiten.setVisible(true);
                aktuellesFensterSchliessen(btnSammlungBearbeiten);
            }
        });
        btnSammlungBearbeiten.setBackground(JAVA_COLOR_PINK);
        btnSammlungBearbeiten.setFont(font);

        return btnSammlungBearbeiten;
    }

    public static JPanel buttonHinzufuegen() {

        // Panel hinzufuegen, Buttons in Panel einfuegen
        JPanel buttonHinzufuegenPanel = new JPanel(new GridLayout(0, 1));

        buttonHinzufuegenPanel.add(btnBesonderheitenHinzufuegen(Schrift.schriftartButtons()));
        buttonHinzufuegenPanel.add(btnSeltenheitenHinzufuegen(Schrift.schriftartButtons()));
        buttonHinzufuegenPanel.add(btnErweiterungenHinzufuegen(Schrift.schriftartButtons()));
        buttonHinzufuegenPanel.add(btnOrdnerHinzufuegen(Schrift.schriftartButtons()));
        buttonHinzufuegenPanel.add(btnKartenHinzufuegen(Schrift.schriftartButtons()));

        return buttonHinzufuegenPanel;
    }

    public static JPanel buttonBearbeiten() {

        // Panel hinzufuegen, Buttons in Panel einfuegen
        JPanel buttonBearbeitenPanel = new JPanel(new GridLayout(0, 1));

        buttonBearbeitenPanel.add(btnBesonderheitenBearbeiten(Schrift.schriftartButtons()));
        buttonBearbeitenPanel.add(btnSeltenheitenBearbeiten(Schrift.schriftartButtons()));
        buttonBearbeitenPanel.add(btnErweiterungenBearbeiten(Schrift.schriftartButtons()));
        buttonBearbeitenPanel.add(btnOrdnerBearbeiten(Schrift.schriftartButtons()));
        buttonBearbeitenPanel.add(btnKartenBearbeiten(Schrift.schriftartButtons()));

        return buttonBearbeitenPanel;
    }

    public static JPanel buttonAnzeigen() {

        // Panel hinzufuegen, Buttons in Panel einfuegen
        JPanel buttonAnzeigenPanel = new JPanel();

        buttonAnzeigenPanel.add(btnBesonderheitenAnzeigen(Schrift.navigationsButtons()));
        buttonAnzeigenPanel.add(btnSeltenheitenAnzeigen(Schrift.navigationsButtons()));
        buttonAnzeigenPanel.add(btnErweiterungenAnzeigen(Schrift.navigationsButtons()));
        buttonAnzeigenPanel.add(btnOrdnerAnzeigen(Schrift.navigationsButtons()));
        buttonAnzeigenPanel.add(btnSammlungAnzeigen(Schrift.navigationsButtons()));
        buttonAnzeigenPanel.add(buttonZurueck(Schrift.navigationsButtons()));

        return buttonAnzeigenPanel;
    }

    public static JPanel buttonsStartSeite() {
        JPanel buttonsStartSeitePanel = new JPanel(new GridLayout(0, 2));

        buttonsStartSeitePanel.add(btnSammlungBearbeiten(Schrift.schriftartButtons()));
        buttonsStartSeitePanel.add(btnBesonderheitenAnzeigen(Schrift.schriftartButtons()));
        buttonsStartSeitePanel.add(btnSeltenheitenAnzeigen(Schrift.schriftartButtons()));
        buttonsStartSeitePanel.add(btnErweiterungenAnzeigen(Schrift.schriftartButtons()));
        buttonsStartSeitePanel.add(btnOrdnerAnzeigen(Schrift.schriftartButtons()));
        buttonsStartSeitePanel.add(btnSammlungAnzeigen(Schrift.schriftartButtons()));

        return buttonsStartSeitePanel;
    }

    // Button zurueck zur Hauptseite
    public static JButton buttonZurueck(Font font) {
        JButton btnBack = new JButton("Zurück");
        btnBack.addActionListener(e -> {
            PokemonKarten pokemonKarten = new PokemonKarten();
            pokemonKarten.setVisible(true);
            aktuellesFensterSchliessen(btnBack);
        });
        btnBack.setFont(font);

        return btnBack;
    }
}
