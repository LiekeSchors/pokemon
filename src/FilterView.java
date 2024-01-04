/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class FilterView {

    // Methode zum Filtern nach Zyklus

    public static void filternNachString(String selektierteSpalte, JTable table, int spaltenIndex) {

        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) table.getRowSorter();

        if (selektierteSpalte.equals("Alle")) {
            // Wenn "Alle" ausgewählt ist, zeige alle Zeilen an
            sorter.setRowFilter(null);
        } else {
            // Andernfalls filtere nach dem ausgewählten Zyklus
            sorter.setRowFilter(RowFilter.regexFilter(selektierteSpalte, spaltenIndex));
        }
    }

    public static void filternNachInteger(Integer selektierteSpalte, JTable table, int spaltenIndex) {

        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) table.getRowSorter();

        if (selektierteSpalte.equals(0)) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(String.valueOf(selektierteSpalte), spaltenIndex));
        }
    }
}
