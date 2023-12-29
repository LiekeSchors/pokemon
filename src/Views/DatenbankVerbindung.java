/*
 * Copyright (c) 2023.
 * Lieke Schors
 */

package Views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatenbankVerbindung {
    public static Connection connectDB() {
        try {
            // Überprüfe, ob der Treiber geladen werden kann
            Class.forName("org.postgresql.Driver");

            // Verbinde mit der Datenbank
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/pokemon_karten", "postgres", "postgres");

            // Überprüfe, ob die Verbindung erfolgreich hergestellt wurde
            if (con != null && !con.isClosed()) {
                System.out.println("Datenbankverbindung hergestellt.");
                return con;
            } else {
                System.out.println("Datenbankverbindung fehlgeschlagen.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
