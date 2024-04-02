/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package funktionen;

import static javax.swing.RowFilter.andFilter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class FilterViews {

    // Methode zum Filtern nach einem gewissen Typ

    public static <T> void filternNachTyp(T selektierteSpalte, JTable table, int spaltenIndex) {

        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) table.getRowSorter();
        if (selektierteSpalte == null || selektierteSpalte.equals("Alle")) {
            sorter.setRowFilter(null); // Wenn "Alle" ausgewählt ist, zeige alle Zeilen an
        } else {
            if (selektierteSpalte instanceof String) {
                sorter.setRowFilter(RowFilter.regexFilter((String) selektierteSpalte, spaltenIndex));
            } else if (selektierteSpalte instanceof Integer) {
                sorter.setRowFilter(RowFilter.regexFilter(String.valueOf(selektierteSpalte), spaltenIndex));
            }
        }
    }

    // Methoden zum Kombinieren und Löschen von Filter

    private static List<RowFilter<Object, Object>> activeFilters = new ArrayList<>();

    public static void addFilter(JTable table, RowFilter<Object, Object> filter) {
        activeFilters.add(filter);
        applyFilters(table);
    }

    public static void removeFilter(JTable table, RowFilter<Object, Object> filter) {
        activeFilters.remove(filter);
        applyFilters(table);
    }

    public static void clearFilters(JTable table) {
        activeFilters.clear();
        applyFilters(table);
        // Filter auf "Alle" setzen
    }

    public static void applyFilters(JTable table) {
        TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) table.getRowSorter();
        if (!activeFilters.isEmpty()) {
            RowFilter<Object, Object> combinedFilter = andFilter(activeFilters);
            sorter.setRowFilter(combinedFilter);
        } else {
            sorter.setRowFilter(null);
        }
    }

}
