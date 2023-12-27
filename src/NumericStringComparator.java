/*
 * Copyright (c) 2023.
 * Lieke Schors
 */

import java.util.Comparator;

/**
 * Comparator zum Sortieren von Integers, die zuvor als String sortiert wurden.
 */
public class NumericStringComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        // Konvertiere die Zeichenketten zu Zahlen für den Vergleich
        Integer num1 = Integer.parseInt(s1);
        Integer num2 = Integer.parseInt(s2);
        return num1.compareTo(num2);
    }
}