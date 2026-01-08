package repository;

public class Tarjeta {
    private String numero;
    private String caducidad;

    public Tarjeta(String numero, String caducidad) {
        this.numero = numero;
        this.caducidad = caducidad;
    }

    public String getNumero() {
        return numero;
    }

    public String getCaducidad() {
        return caducidad;
    }
}
