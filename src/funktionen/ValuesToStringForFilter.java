/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package funktionen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import datenbank.DatenbankVerbindung;

public class ValuesToStringForFilter {


    // Sammlung

    /**
     * Methode, um die IDs zu holen und als Liste zu speichern
     */
    public static Integer[] getSammlungID() {
        List<Integer> sammlungIDList = new ArrayList<>();

        try (Connection con = DatenbankVerbindung.connectDB()) {
            String sql = "SELECT DISTINCT karten_id FROM sammlung";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String sammlungID = resultSet.getString("karten_id");
                    sammlungIDList.add(Integer.valueOf(sammlungID));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Collections.sort(sammlungIDList);

        return sammlungIDList.toArray(new Integer[0]);
    }

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

        // Seltenheit

        /**
         * Methode, um die Beschreibungen der Seltenheiten zu holen und als Liste auszugeben
         *
         * @return
         */
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

        /**
         * Methode, um die IDs der Seltenheiten zu holen und als Liste auszugeben
         *
         * @return
         */
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

        /**
         * Methode, um Zyklen zu holen und als Liste auszugeben für Ordner bzw. Erweiterung
         *
         * @return
         */
        public static String[] getZyklusOrdner() {
            List<String> zyklenList = new ArrayList<>();

            zyklenList.add("Alle");

            try (Connection con = DatenbankVerbindung.connectDB()) {
                String sql = "SELECT DISTINCT zyklus FROM ordner";
                try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                     ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String zyklen = resultSet.getString("zyklus");
                        zyklenList.add(zyklen);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Collections.sort(zyklenList);

            return zyklenList.toArray(new String[0]);
        }

        public static String[] getZyklusErweiterung() {
            List<String> zyklenList = new ArrayList<>();

            zyklenList.add("Alle");

            try (Connection con = DatenbankVerbindung.connectDB()) {
                String sql = "SELECT DISTINCT zyklus FROM erweiterungen";
                try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                     ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String zyklen = resultSet.getString("zyklus");
                        zyklenList.add(zyklen);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Collections.sort(zyklenList);

            return zyklenList.toArray(new String[0]);
        }

        public static Integer[] getJahrErweiterung() {
            List<Integer> jahrList = new ArrayList<>();

            try (Connection con = DatenbankVerbindung.connectDB()) {
                String sql = "SELECT DISTINCT jahr FROM erweiterungen WHERE jahr iS NOT NULL";
                try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                     ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String jahr = resultSet.getString("jahr");
                        jahrList.add(Integer.valueOf(jahr));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Collections.sort(jahrList);

            return jahrList.toArray(new Integer[0]);
        }

        public static Integer[] getOrdnerErweiterung() {
            List<Integer> ordnerList = new ArrayList<>();

            try (Connection con = DatenbankVerbindung.connectDB()) {
                String sql = "SELECT DISTINCT ordner_id FROM erweiterungen WHERE ordner_id iS NOT NULL";
                try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                     ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String ordner = resultSet.getString("ordner_id");
                        ordnerList.add(Integer.valueOf(ordner));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Collections.sort(ordnerList);

            return ordnerList.toArray(new Integer[0]);
        }

    }
