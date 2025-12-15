package ui;

import javax.swing.*;

public class ventanaPrincipal extends JFrame {

    public static void main(String[] args) {
        new ventanaPrincipal();
    }

    public ClienteRepository clienteRepo = new ClienteRepository();
    public Pedido pedidoActual = new Pedido();

    public ventanaPrincipal() {
    setTitle("Gestor de pedidos");
    setSize(500, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Mostramos primero el PanelID
    setContentPane(new PanelID(this));
    setVisible(true);
}


    // Cambiar al siguiente panel
    public void cambiarPanel(JPanel nuevo) {
        setContentPane(nuevo);
        revalidate();
        repaint();
    }
}
