/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package layout.mytextfields;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GUIDateTextField extends GUITextField {
    private final static String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";

    Date datum = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public GUIDateTextField() {
        super(DATE_REGEX);
        setText(dateFormat.format(datum));
    }
}
