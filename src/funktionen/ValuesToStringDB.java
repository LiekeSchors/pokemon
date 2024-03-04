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

public class ValuesToStringDB {


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
    public static String[] getEindeutigeErweiterungAbkuerzung(boolean alle) {
        List<String> eindeutigeErweiterungAbkuerzungList = new ArrayList<>();
        if (alle) {
            eindeutigeErweiterungAbkuerzungList.add("Alle");
        }

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

    public static String[] getErweiterungAbkuerzungAlle() {
        List<String> erweiterungAbkuerzungListAlle = new ArrayList<>();

        try (Connection con = DatenbankVerbindung.connectDB()) {
            String sql = "SELECT DISTINCT abkuerzung FROM erweiterungen";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String erwAbk = resultSet.getString("abkuerzung");
                    erweiterungAbkuerzungListAlle.add(erwAbk);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(erweiterungAbkuerzungListAlle);

        return erweiterungAbkuerzungListAlle.toArray(new String[0]);

    }


    /**
     * Methode, um Energie-Typen zu holen und als Liste auszugeben
     *
     * @return
     */
    public static String[] getEnergieTyp(boolean alle) {
        List<String> energieTypList = new ArrayList<>();

        if (alle) {
            energieTypList.add("Alle");
        }

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
     * Methode, um die Symbole der Seltenheiten zu holen und als Liste auszugeben
     *
     * @return
     */
    public static String[] getSymbolSeltenheit() {
        List<String> symbolSeltenheitList = new ArrayList<>();

        try (Connection con = DatenbankVerbindung.connectDB()) {
            String sql = "SELECT DISTINCT symbol FROM seltenheit";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String symbolSeltenheit = resultSet.getString("symbol");
                    symbolSeltenheitList.add(symbolSeltenheit);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(symbolSeltenheitList);

        return symbolSeltenheitList.toArray(new String[0]);
    }

    public static String[] getBeschreibungBesonderheit() {
        List<String> beschreibungBesonderheitList = new ArrayList<>();

        try (Connection con = DatenbankVerbindung.connectDB()) {
            String sql = "SELECT DISTINCT beschreibung FROM besonderheiten";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String beschreibungBesonderheit = resultSet.getString("beschreibung");
                    beschreibungBesonderheitList.add(beschreibungBesonderheit);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(beschreibungBesonderheitList);

        return beschreibungBesonderheitList.toArray(new String[0]);
    }

    //Zusatz

    /**
     * Methode, um den Trainer-Zusatz zu holen und als Liste auszugeben
     *
     * @return
     */
    public static String[] getTrainerZusatz() {
        List<String> trainerZusatzList = new ArrayList<>();

        try (Connection con = DatenbankVerbindung.connectDB()) {
            String sql = "SELECT DISTINCT trainer_zusatz FROM sammlung WHERE trainer_zusatz IS NOT NULL";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String trainerZusatz = resultSet.getString("trainer_zusatz");
                    if (trainerZusatz == null) {
                        trainerZusatz = "";
                    }
                    trainerZusatzList.add(trainerZusatz);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(trainerZusatzList);

        return trainerZusatzList.toArray(new String[0]);
    }

    /**
     * Methode, um den Trainer-Zusatz zu holen und als Liste auszugeben
     *
     * @return
     */
    public static String[] getNameZusatz() {
        List<String> nameZusatzList = new ArrayList<>();

        try (Connection con = DatenbankVerbindung.connectDB()) {
            String sql = "SELECT DISTINCT name_zusatz FROM sammlung";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String nameZusatz = resultSet.getString("name_zusatz");
                    if (nameZusatz == null) {
                        nameZusatz = "";
                    }
                    nameZusatzList.add(nameZusatz);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(nameZusatzList);

        return nameZusatzList.toArray(new String[0]);
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

    public static String[] getZyklusErweiterung(boolean alle) {
        List<String> zyklenList = new ArrayList<>();
        if (alle) {
            zyklenList.add("Alle");
        }

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
