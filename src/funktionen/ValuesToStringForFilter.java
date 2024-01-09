/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package funktionen;import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import datenbank.DatenbankVerbindung;

public class ValuesToStringForFilter {


    /**
     * Methode, um Abkürzungen der Erweiterung zu holen und als Liste zu speichern
     *
     * @return
     */
    public static String[] getEindeutigeErweiterungAbkuerzung() {
        List<String> eindeutigeErweiterungAbkuerzungList = new ArrayList<>();

        eindeutigeErweiterungAbkuerzungList.add("Alle");

        try (Connection con = DatenbankVerbindung.connectDB()) {
            String sql = "SELECT DISTINCT erweiterung_abkuerzung FROM sammlung";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String erweiterungAbkuerzung = resultSet.getString("erweiterung_abkuerzung");
                    eindeutigeErweiterungAbkuerzungList.add(erweiterungAbkuerzung);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(eindeutigeErweiterungAbkuerzungList);

        return eindeutigeErweiterungAbkuerzungList.toArray(new String[0]);
    }


    /**
     * Methode, um Energie-Typen zu holen und als Liste auszugeben
     *
     * @return
     */
    public static String[] getEnergieTyp() {
        List<String> energieTypList = new ArrayList<>();

        energieTypList.add("Alle");

        try (Connection con = DatenbankVerbindung.connectDB()) {
            String sql = "SELECT DISTINCT energie_typ FROM sammlung";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String energieTyp = resultSet.getString("energie_typ");
                    energieTypList.add(energieTyp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(energieTypList);

        return energieTypList.toArray(new String[0]);
    }

    public static String[] getBeschreibungSeltenheit() {
        List<String> beschreibungSeltenheitList = new ArrayList<>();

        beschreibungSeltenheitList.add("Alle");

        try (Connection con = DatenbankVerbindung.connectDB()) {
            String sql = "SELECT DISTINCT beschreibung FROM seltenheit";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String beschreibungSeltenheit = resultSet.getString("beschreibung");
                    beschreibungSeltenheitList.add(beschreibungSeltenheit);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(beschreibungSeltenheitList);

        return beschreibungSeltenheitList.toArray(new String[0]);
    }

    public static Integer[] getSeltenheitID() {
        List<Integer> seltenheitIDList = new ArrayList<>();

        try (Connection con = DatenbankVerbindung.connectDB()) {
            String sql = "SELECT DISTINCT id FROM seltenheit";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String seltenheitID = resultSet.getString("id");
                    seltenheitIDList.add(Integer.valueOf(seltenheitID));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(seltenheitIDList);

        return seltenheitIDList.toArray(new Integer[0]);
    }

}
