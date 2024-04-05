/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package layout.mylabels;

import java.awt.Dimension;
import java.awt.Font;

import layout.Schrift;

public class FilterLabel extends GUILabel {
    public FilterLabel(String name) {
        super(name);
        setFont(Schrift.normal().deriveFont(Font.BOLD));
        setPreferredSize(new Dimension(50, 30));
    }
}
