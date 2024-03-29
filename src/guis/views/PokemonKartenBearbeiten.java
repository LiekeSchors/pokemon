/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package guis.views;

import static layout.TaskListWithIcon.iconPfad;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import funktionen.Buttons;

public class PokemonKartenBearbeiten extends JFrame {

    public PokemonKartenBearbeiten() {
        setTitle("Sammlung bearbeiten");
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        ImageIcon icon = new ImageIcon(iconPfad);
        setIconImage(icon.getImage());

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
}