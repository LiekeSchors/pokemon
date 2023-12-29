/*
 * Copyright (c) 2023.
 * Lieke Schors
 */

package Views;import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

// Benutzerdefinierte TableCellRenderer für die Spaltenüberschrift
public class CustomHeaderRenderer implements TableCellRenderer {
    private final TableCellRenderer originalRenderer;
    private final String tooltipText;

    public CustomHeaderRenderer(TableCellRenderer originalRenderer, String tooltipText) {
        this.originalRenderer = originalRenderer;
        this.tooltipText = tooltipText;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component originalComponent = originalRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        ((JComponent) originalComponent).setToolTipText(tooltipText);
        return originalComponent;
    }
}