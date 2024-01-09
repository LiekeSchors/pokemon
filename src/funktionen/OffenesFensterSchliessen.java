/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package funktionen;

import java.awt.Window;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

/**
 * Die Methode in dieser Klasse sorgt dafür, dass das Fenster, das gerade verlassen wird, geschlossen wird.
 */
public class OffenesFensterSchliessen {

    static void aktuellesFensterSchliessen(JButton button) {
        // Hole das aktuelle Fenster
        Window window = SwingUtilities.windowForComponent(button);

        // Schließe das Fenster
        if (window != null) {
            window.dispose();
        }
    }
}

