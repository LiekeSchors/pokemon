/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package layout.mycomboboxes;

import java.awt.Dimension;

import layout.Schrift;

public class FilterComboBox<T> extends GUIComboBox<T> {

    public FilterComboBox(T[] items) {
        super(items);
        setFont(Schrift.normal());
        setPreferredSize(new Dimension(100, 30));
    }
}
