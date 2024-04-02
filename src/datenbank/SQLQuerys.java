/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package datenbank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLQuerys {

    public static String getEntwicklung(String pokemonName) {
        Connection con = DatenbankVerbindung.connectDB();
        PreparedStatement p = null;
        ResultSet rs = null;
        String entwicklung = "";
        try {
            String sql = "SELECT ursprung_name FROM sammlung WHERE pokemon_name = ?";
            p = con.prepareStatement(sql);
            p.setString(1, pokemonName);
            rs = p.executeQuery();

            if (rs.next()) {
                entwicklung = rs.getString("ursprung_name");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (p != null) {
                    p.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return entwicklung;
    }

    public static Object getLetzteErweiterung() {
        Connection con = DatenbankVerbindung.connectDB();
        PreparedStatement p = null;
        ResultSet rs = null;
        String erwAbk = null;
        try {
            String sql = "SELECT erweiterung_abkuerzung FROM sammlung WHERE karten_id = (SELECT MAX(karten_id) FROM sammlung)";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();

            if (rs.next()) {
                erwAbk = rs.getString("erweiterung_abkuerzung");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (p != null) {
                    p.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return erwAbk;
    }

    public static Object getLetzteEnergie() {
        Connection con = DatenbankVerbindung.connectDB();
        PreparedStatement p = null;
        ResultSet rs = null;
        String energieTyp = null;
        try {
            String sql = "SELECT energie_typ FROM sammlung WHERE karten_id = (SELECT MAX(karten_id) FROM sammlung)";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();

            if (rs.next()) {
                energieTyp = rs.getString("energie_typ");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (p != null) {
                    p.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return energieTyp;
    }

    public static Object getLetzteSeltenheit(String erweiterung, String kartennr) {
        Connection con = DatenbankVerbindung.connectDB();
        PreparedStatement p = null;
        ResultSet rs = null;
        String seltenheit = null;
        try {
            String sql = "SELECT seltenheit_id FROM sammlung WHERE karten_nr = ? AND erweiterung_abkuerzung = ?";
            p = con.prepareStatement(sql);
            p.setString(1, erweiterung);
            p.setString(2, kartennr);

            rs = p.executeQuery();

            if (rs.next()) {
                seltenheit = rs.getString("seltenheit_id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (p != null) {
                    p.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return seltenheit;
    }
}