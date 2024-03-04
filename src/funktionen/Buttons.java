/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package funktionen;

import static layout.Colors.JAVA_COLOR_HELLBLAU;
import static layout.Colors.JAVA_COLOR_ORANGE;
import static layout.Colors.JAVA_COLOR_PINK;
import static layout.Colors.JAVA_COLOR_TUERKIS;

import java.awt.Font;
import java.awt.GridLayout;

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
import guis.SeltenheitenBearbeitenGUI;
import guis.SeltenheitenHinzufuegenGUI;
import layout.Colors;
import layout.Schrift;
import views.PokemonKartenBearbeiten;
import views.PokemonKartenBesonderheitenView;
import views.PokemonKartenErweiterungenView;
import views.PokemonKartenOrdnerView;
import views.PokemonKartenSammlungView;
import views.PokemonKartenSeltenheitenView;
import views.PokemonKartenView;

/**
 * Diese Klasse enthaelt viel verwendete Buttons sowie Button-Panels
 */
public class Buttons {

    // Besonderheit
    public static JButton btnBesonderheitenHinzufuegen(Font font) {
        JButton btnBesonderheitenHinzufuegen = new JButton("Besonderheit hinzufügen");
        btnBesonderheitenHinzufuegen.addActionListener(new ButtonActionListener<>(btnBesonderheitenHinzufuegen, BesonderheitenHinzufuegenGUI.class));
        btnBesonderheitenHinzufuegen.setBackground(JAVA_COLOR_HELLBLAU);
        btnBesonderheitenHinzufuegen.setFont(font);

        return btnBesonderheitenHinzufuegen;
    }

    public static JButton btnBesonderheitenBearbeiten(Font font) {
        JButton btnBesonderheitenBearbeiten = new JButton("Besonderheit bearbeiten");
        btnBesonderheitenBearbeiten.addActionListener(new ButtonActionListener<>(btnBesonderheitenBearbeiten, BesonderheitenBearbeitenGUI.class));
        btnBesonderheitenBearbeiten.setBackground(JAVA_COLOR_HELLBLAU);
        btnBesonderheitenBearbeiten.setFont(font);

        return btnBesonderheitenBearbeiten;
    }

    public static JButton btnBesonderheitenAnzeigen(Font font) {
        JButton btnBesonderheitenView = new JButton("Besonderheiten anzeigen");
        btnBesonderheitenView.addActionListener(new ButtonActionListener<>(btnBesonderheitenView, PokemonKartenBesonderheitenView.class));
        btnBesonderheitenView.setBackground(JAVA_COLOR_HELLBLAU);
        btnBesonderheitenView.setFont(font);

        return btnBesonderheitenView;
    }

    // Seltenheiten
    public static JButton btnSeltenheitenHinzufuegen(Font font) {
        JButton btnSeltenheitenHinzufuegen = new JButton("Seltenheit hinzufügen");
        btnSeltenheitenHinzufuegen.addActionListener(new ButtonActionListener<>(btnSeltenheitenHinzufuegen, SeltenheitenHinzufuegenGUI.class));
        btnSeltenheitenHinzufuegen.setBackground(Colors.JAVA_COLOR_GREEN);
        btnSeltenheitenHinzufuegen.setFont(font);

        return btnSeltenheitenHinzufuegen;
    }

    public static JButton btnSeltenheitenBearbeiten(Font font) {
        JButton btnSeltenheitenBearbeiten = new JButton("Seltenheit bearbeiten");
        btnSeltenheitenBearbeiten.addActionListener(new ButtonActionListener<>(btnSeltenheitenBearbeiten, SeltenheitenBearbeitenGUI.class));
        btnSeltenheitenBearbeiten.setFont(font);
        btnSeltenheitenBearbeiten.setBackground(Colors.JAVA_COLOR_GREEN);

        return btnSeltenheitenBearbeiten;
    }

    public static JButton btnSeltenheitenAnzeigen(Font font) {
        JButton btnSeltenheitenView = new JButton("Seltenheiten anzeigen");
        btnSeltenheitenView.addActionListener(new ButtonActionListener<>(btnSeltenheitenView, PokemonKartenSeltenheitenView.class));
        btnSeltenheitenView.setBackground(Colors.JAVA_COLOR_GREEN);
        btnSeltenheitenView.setFont(font);

        return btnSeltenheitenView;
    }

    // Erweiterungen
    public static JButton btnErweiterungenHinzufuegen(Font font) {
        JButton btnErweiterungenHinzufuegen = new JButton("Erweiterung hinzufügen");
        btnErweiterungenHinzufuegen.addActionListener(new ButtonActionListener<>(btnErweiterungenHinzufuegen, ErweiterungenHinzufuegenGUI.class));
        btnErweiterungenHinzufuegen.setBackground(Colors.JAVA_COLOR_YELLOW);
        btnErweiterungenHinzufuegen.setFont(font);

        return btnErweiterungenHinzufuegen;
    }

    public static JButton btnErweiterungenBearbeiten(Font font) {
        JButton btnErweiterungenBearbeiten = new JButton("Erweiterungen bearbeiten");
        btnErweiterungenBearbeiten.addActionListener(new ButtonActionListener<>(btnErweiterungenBearbeiten, ErweiterungenBearbeitenGUI.class));
        btnErweiterungenBearbeiten.setFont(font);
        btnErweiterungenBearbeiten.setBackground(Colors.JAVA_COLOR_YELLOW);

        return btnErweiterungenBearbeiten;
    }

    public static JButton btnErweiterungenAnzeigen(Font font) {
        JButton btnErweiterungenView = new JButton("Erweiterungen anzeigen");
        btnErweiterungenView.addActionListener(new ButtonActionListener<>(btnErweiterungenView, PokemonKartenErweiterungenView.class));
        btnErweiterungenView.setBackground(Colors.JAVA_COLOR_YELLOW);
        btnErweiterungenView.setFont(font);

        return btnErweiterungenView;
    }

    // Ordner
    public static JButton btnOrdnerHinzufuegen(Font font) {
        JButton btnOrdnerHinzufuegen = new JButton("Ordner hinzufügen");
        btnOrdnerHinzufuegen.addActionListener(new ButtonActionListener<>(btnOrdnerHinzufuegen, OrdnerHinzufuegenGUI.class));
        btnOrdnerHinzufuegen.setBackground(JAVA_COLOR_ORANGE);
        btnOrdnerHinzufuegen.setFont(font);

        return btnOrdnerHinzufuegen;
    }

    public static JButton btnOrdnerBearbeiten(Font font) {
        JButton btnOrdnerBearbeiten = new JButton("Ordner bearbeiten");
        btnOrdnerBearbeiten.addActionListener(new ButtonActionListener<>(btnOrdnerBearbeiten, OrdnerBearbeitenGUI.class));
        btnOrdnerBearbeiten.setFont(font);
        btnOrdnerBearbeiten.setBackground(JAVA_COLOR_ORANGE);

        return btnOrdnerBearbeiten;
    }

    public static JButton btnOrdnerAnzeigen(Font font) {
        JButton btnOrdnerView = new JButton("Ordner anzeigen");
        btnOrdnerView.addActionListener(new ButtonActionListener<>(btnOrdnerView, PokemonKartenOrdnerView.class));
        btnOrdnerView.setBackground(JAVA_COLOR_ORANGE);
        btnOrdnerView.setFont(font);

        return btnOrdnerView;
    }

    // Sammlung
    public static JButton btnKartenHinzufuegen(Font font) {
        JButton btnKartenHinzufuegen = new JButton("Karten hinzufügen");
        btnKartenHinzufuegen.addActionListener(new ButtonActionListener<>(btnKartenHinzufuegen, KartenHinzufuegenGUI.class));
        btnKartenHinzufuegen.setBackground(JAVA_COLOR_TUERKIS);
        btnKartenHinzufuegen.setFont(font);

        return btnKartenHinzufuegen;
    }

    public static JButton btnKartenBearbeiten(Font font) {
        JButton btnKartenBearbeiten = new JButton("Karten bearbeiten");
        btnKartenBearbeiten.addActionListener(new ButtonActionListener<>(btnKartenBearbeiten, KartenBearbeitenGUI.class));
        btnKartenBearbeiten.setFont(font);
        btnKartenBearbeiten.setBackground(JAVA_COLOR_TUERKIS);

        return btnKartenBearbeiten;
    }

    public static JButton btnSammlungAnzeigen(Font font) {
        JButton btnSammlungView = new JButton("Sammlung anzeigen");
        btnSammlungView.addActionListener(new ButtonActionListener<>(btnSammlungView, PokemonKartenSammlungView.class));
        btnSammlungView.setBackground(JAVA_COLOR_TUERKIS);
        btnSammlungView.setFont(font);

        return btnSammlungView;
    }

    public static JButton btnSammlungBearbeiten(Font font) {
        JButton btnSammlungBearbeiten = new JButton("Sammlung bearbeiten");
        btnSammlungBearbeiten.addActionListener(new ButtonActionListener<>(btnSammlungBearbeiten, PokemonKartenBearbeiten.class));
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

        buttonAnzeigenPanel.add(btnSammlungBearbeiten(Schrift.navigationsButtons()));
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
        JButton btnBack = new JButton("Zurück zum Hauptmenü");
        btnBack.addActionListener(new ButtonActionListener<>(btnBack, PokemonKartenView.class));
        btnBack.setFont(font);

        return btnBack;
    }

    public static JButton buttonZurueckBesonderheiten(Font font) {
        JButton btnBack = new JButton("Zurück zu Besonderheiten");
        btnBack.addActionListener(new ButtonActionListener<>(btnBack, PokemonKartenBesonderheitenView.class));
        btnBack.setFont(font);

        return btnBack;
    }

    public static JButton buttonZurueckSeltenheiten(Font font) {
        JButton btnBack = new JButton("Zurück zu Seltenheiten");
        btnBack.addActionListener(new ButtonActionListener<>(btnBack, PokemonKartenSeltenheitenView.class));
        btnBack.setFont(font);

        return btnBack;
    }

    public static JButton buttonZurueckErweiterungen(Font font) {
        JButton btnBack = new JButton("Zurück zu Erweiterungen");
        btnBack.addActionListener(new ButtonActionListener<>(btnBack, PokemonKartenErweiterungenView.class));
        btnBack.setFont(font);

        return btnBack;
    }

    public static JButton buttonZurueckOrdner(Font font) {
        JButton btnBack = new JButton("Zurück zu Ordner");
        btnBack.addActionListener(new ButtonActionListener<>(btnBack, PokemonKartenOrdnerView.class));
        btnBack.setFont(font);

        return btnBack;
    }

    public static JButton buttonZurueckKarten(Font font) {
        JButton btnBack = new JButton("Zurück zu Karten");
        btnBack.addActionListener(new ButtonActionListener<>(btnBack, PokemonKartenSammlungView.class));
        btnBack.setFont(font);

        return btnBack;
    }
}
