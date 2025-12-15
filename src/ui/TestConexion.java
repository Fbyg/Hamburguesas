package ui;

public class TestConexion {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Driver SQLite cargado correctamente");

            java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:sqlite:hamburguesas.db");
            System.out.println("Conexión abierta: " + con);

            con.close();
            System.out.println("Conexión cerrada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
