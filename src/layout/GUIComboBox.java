/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package layout;

import java.awt.Dimension;

import javax.swing.JComboBox;

import layout.Schrift;

public class GUIComboBox<T> extends JComboBox<T> {

    public GUIComboBox(T[] items) {
        super(items);
        setFont(Schrift.normal());
        setPreferredSize(new Dimension(150, 30));
    }
}
