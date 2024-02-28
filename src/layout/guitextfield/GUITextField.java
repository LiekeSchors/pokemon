/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package layout.guitextfield;

import java.awt.Dimension;

import javax.swing.JTextField;

import layout.Schrift;

public class GUITextField extends JTextField {
    private final String validierungsRegex;

    public GUITextField() {
        this(null);
    }

    public GUITextField(String validierungsRegex) {
        super();
        setFont(Schrift.normal());
        setPreferredSize(new Dimension(150, 30));
        this.validierungsRegex = validierungsRegex;
    }


    public String getValidierungsRegex() {
        return validierungsRegex;
    }

}
