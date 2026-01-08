package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:sqlite:hamburguesas.db";

    public static Connection getConexion() {
        try {
            // Cargar driver SQLite 
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(URL);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error conectando a la base de datos: " + e.getMessage());
            return null;
        }
    }
}
