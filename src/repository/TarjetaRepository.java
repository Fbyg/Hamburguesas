package repository;
import java.util.HashMap;

public class TarjetaRepository {
    private HashMap<String, Tarjeta> tarjetas = new HashMap<>();

    // Guardar tarjeta de un cliente
    public void guardarTarjeta(String cliente, Tarjeta tarjeta) {
        tarjetas.put(cliente, tarjeta);
    }

    // Obtener tarjeta de un cliente
    public Tarjeta getTarjeta(String cliente) {
        return tarjetas.get(cliente);
    }
}

