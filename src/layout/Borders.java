/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package layout;

import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.StrokeBorder;

public class Borders {

    public static JButton buttonBorder(JButton button, Color color) {
        EmptyBorder emptyBorder = new EmptyBorder(3, 3, 3, 3);
        StrokeBorder linie = (StrokeBorder) BorderFactory.createStrokeBorder(new BasicStroke(3.0f), color);
        CompoundBorder liniePad3 = BorderFactory.createCompoundBorder(linie, emptyBorder);
        JButton buttonBorderLine3 = button;
        buttonBorderLine3.setBorder(liniePad3);

        return buttonBorderLine3;
    }
}
