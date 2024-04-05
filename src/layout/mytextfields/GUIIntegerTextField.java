/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package layout.mytextfields;

public class GUIIntegerTextField extends GUITextField {
    private final static String INTEGER_REGEX = "[1-9][0-9]*";

    public GUIIntegerTextField() {
        super(INTEGER_REGEX);

    }
}
