/*
 * Copyright (c) 2023.
 * Lieke Schors
 */

package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

// Benutzerdefinierte TableCellRenderer für die gesamte Tabelle
public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Hier kannst du den Tooltip-Text für jede Zelle festlegen
        String columnName = table.getColumnName(column);
        String tooltipText = "Tooltip für " + columnName;
        ((JComponent) component).setToolTipText(tooltipText);

        return component;
    }
}