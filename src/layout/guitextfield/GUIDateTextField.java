/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package layout.guitextfield;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GUIDateTextField extends GUITextField {
    private final static String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";

    Date datum = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public GUIDateTextField() {
        super(DATE_REGEX);
        setText(dateFormat.format(datum));
    }
}
