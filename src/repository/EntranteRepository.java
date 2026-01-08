package repository;
import java.sql.*;
import java.util.ArrayList;

import ui.Conexion;

public class EntranteRepository {

    public EntranteRepository() {
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() {
        String sql = "CREATE TABLE IF NOT EXISTS entrantes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT UNIQUE NOT NULL, " +
                "precio REAL NOT NULL)";
        try (Connection con = Conexion.getConexion();
                Statement st = con.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creando tabla entrantes: " + e.getMessage());
        }
    }

    public ArrayList<Entrante> getEntrantes() {
        ArrayList<Entrante> lista = new ArrayList<>();
        String sql = "SELECT * FROM entrantes";

        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Entrante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio")));
            }

        } catch (SQLException e) {
            System.out.println("Error cargando entrantes: " + e.getMessage());
        }

        return lista;
    }

    public Entrante buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM entrantes WHERE nombre = ?";
        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre.toLowerCase());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Entrante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"));
            }

        } catch (SQLException e) {
            System.out.println("Error buscando entrante: " + e.getMessage());
        }
        return null;
    }
}
