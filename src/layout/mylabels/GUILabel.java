/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package layout.mylabels;

import javax.swing.JLabel;

import layout.Schrift;

public class GUILabel extends JLabel {

    public GUILabel(String name){
        super(name);
        setFont(Schrift.normal());
    }
}
