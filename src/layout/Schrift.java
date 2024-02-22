/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package layout;

import java.awt.Font;


/**
 * In dieser Klasse befinden sich unterschiedliche Schriftarten
 */
public class Schrift {

    public static Font navigationsButtons() {
        Font font = new Font("Arial", Font.BOLD, 16);
        return font;
    }

    public static Font normal() {
        Font font = new Font("Arial", Font.PLAIN, 20);
        return font;
    }

    public static Font schriftartButtons() {
        Font font = new Font("Arial", Font.BOLD, 24);
        return font;
    }

    public static Font zurueckButton() {
        Font font = new Font("Arial", Font.PLAIN, 24);
        return font;
    }

    public static Font farbigeUnicodeSymbole() {
        Font font = new Font("Noto Color Emoji", Font.PLAIN, 20);
        return font;
    }
}
