/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package guis;

import static layout.Colors.JAVA_COLOR_HELLBLAU;
import static layout.Colors.JAVA_COLOR_ORANGE;
import static layout.Colors.JAVA_COLOR_PINK;
import static layout.Colors.JAVA_COLOR_TUERKIS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import funktionen.Buttons;
import views.PokemonKartenBesonderheitenView;
import views.PokemonKartenErweiterungenView;
import views.PokemonKartenOrdnerView;
import views.PokemonKartenSammlungView;
import views.PokemonKartenSeltenheitenView;

public class PokemonKarten extends JFrame {

    public PokemonKarten() {
        setTitle("Willkommen");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        panel.add(Buttons.buttonsStartSeite());
        add(panel);
        add(Buttons.buttonAnzeigen(), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PokemonKarten().setVisible(true));
    }
}

