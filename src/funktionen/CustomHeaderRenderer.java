/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package funktionen;import javax.swing.*;
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

    /**
     * Methode zum Hinzufuegen eines Tool-Tip-Textes
     */
    public static void toolTipMaker(JTable table, String tooltipText, int colIdx) {;
        table.getColumnModel().getColumn(colIdx).setHeaderRenderer(new CustomHeaderRenderer(table.getTableHeader().getDefaultRenderer(), tooltipText));
    }
}