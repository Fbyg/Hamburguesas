package ui;
import java.sql.*;
import java.util.ArrayList;



public class ClienteRepository {

    public ClienteRepository() {
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() {
        String sql = "CREATE TABLE IF NOT EXISTS clientes ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nombre TEXT UNIQUE NOT NULL)";
        try (Connection con = Conexion.getConexion();
                Statement st = con.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creando tabla clientes: " + e.getMessage());
        }
    }

    public ArrayList<String> getClientes() {
        ArrayList<String> lista = new ArrayList<>();
        String sql = "SELECT nombre FROM clientes";

        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
                    
                    while (rs.next()) {
                        lista.add(rs.getString("nombre"));
                    }
        } catch (SQLException e) {
            System.out.println("Error cargando clientes: " + e.getMessage());
        }

        return lista;
    }

    public void guardarCliente(String cliente) {
        String sql = "INSERT OR IGNORE INTO clientes(nombre) VALUES (?)";

        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al guardar el cliente: " + e.getMessage());
        }
    }
}
