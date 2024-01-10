/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package guis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import funktionen.Buttons;

public class PokemonKartenBearbeiten extends JFrame {

    public PokemonKartenBearbeiten() {
        setTitle("Sammlung bearbeiten");
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));

        JPanel grossesPanel = new JPanel(new GridLayout(1, 2));

        JPanel linkesPanel = new JPanel(new GridLayout(0, 1));
        linkesPanel.add(Buttons.buttonHinzufuegen());

        grossesPanel.add(linkesPanel);

        JPanel rechtesPanel = new JPanel(new GridLayout(0, 1));
        rechtesPanel.add(Buttons.buttonBearbeiten());

        grossesPanel.add(rechtesPanel);
        add(grossesPanel, BorderLayout.CENTER);

        add(Buttons.buttonAnzeigen(), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new PokemonKartenBearbeiten().setVisible(true));
    }
}