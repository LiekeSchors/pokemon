/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package layout.guitextfield;

public class GUIDoubleTextField extends GUITextField {
    private final static String DOUBLE_REGEX = "[1-9][0-9]*\\.[0-9]+"; // keine führende 0 bei anderen Vorkommastellen, beliebig viele Zahlen, . beliebig viele Nachkommazahlen

    public GUIDoubleTextField() {
        super(DOUBLE_REGEX);

    }
}
