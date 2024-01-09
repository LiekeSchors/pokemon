/*
 * Copyright (c) 2024.
 * Lieke Schors
 */

package datenbank;import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

/**
 * Methode zum automatischen Generieren einer ID fuer PKs
 */


public class GenerateNextID {
    public static void generateNextID(Connection connection, String tabelle, String spalte, JTextField textField) {
        try {
            String sqlSelectMaxID = "SELECT MAX(" + spalte + ") + 1 AS next_id FROM " + tabelle;
            PreparedStatement preparedStatementSelectMaxID = connection.prepareStatement(sqlSelectMaxID);
            ResultSet resultSet = preparedStatementSelectMaxID.executeQuery();
            if (resultSet.next()) {
                int nextID = resultSet.getInt("next_id");
                textField.setText(String.valueOf(nextID));
            }
            resultSet.close();
            preparedStatementSelectMaxID.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

