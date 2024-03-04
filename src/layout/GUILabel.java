/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package layout;

import javax.swing.JLabel;

public class GUILabel extends JLabel {

    public GUILabel(String name){
        super(name);
        setFont(Schrift.normal());
    }
}
