/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import funktionen.Buttons;

public class PokemonKartenView extends JFrame {

    public PokemonKartenView() {
        setTitle("Willkommen");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        panel.add(Buttons.buttonsStartSeite());
        add(panel);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PokemonKartenView().setVisible(true));
    }
}

