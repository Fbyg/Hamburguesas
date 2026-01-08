package ui;

import java.util.List;

public class Pedido {

    private String nombreCliente;
    private String pan;
    private String puntoCarne;
    private String tamañoCarne;
    private List<String> extras;

    // ---- CLIENTE ----
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    // ---- HAMBURGUESA ----
    public void setPan(String pan) {
        this.pan = pan;
    }

    public void setPuntoCarne(String puntoCarne) {
        this.puntoCarne = puntoCarne;
    }

    public void setTamañoCarne(String tamañoCarne) {
        this.tamañoCarne = tamañoCarne;
    }

    public void setExtras(List<String> extras) {
        this.extras = extras;
    }

    // ---- DEBUG / RESUMEN ----
    @Override
    public String toString() {
        return "Pedido de " + nombreCliente +
                "\nPan: " + pan +
                "\nPunto: " + puntoCarne +
                "\nTamaño: " + tamañoCarne +
                "\nExtras: " + extras;
    }
}
