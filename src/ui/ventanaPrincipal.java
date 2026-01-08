package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class ventanaPrincipal extends JFrame {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Button.font", new Font("jetbrains", Font.PLAIN, 14));
            UIManager.put("Label.font", new Font("jetbrains", Font.PLAIN, 14));
            UIManager.put("ComboBox.font", new Font("jetbrains", Font.PLAIN, 14));

            UIManager.put("Button.background", new Color(30, 144, 255));
            UIManager.put("Button.foreground", Color.BLACK);
            UIManager.put("Button.focus", new Color(0, 0, 0, 0));

            UIManager.put("Panel.background", new Color(245, 245, 245));

        } catch (Exception e) {
            e.printStackTrace();
        }
        new ventanaPrincipal();
    }

    public ClienteRepository clienteRepo = new ClienteRepository();
    public Pedido pedidoActual = new Pedido();

    public void cambiarPanel(JPanel nuevo) {
        setContentPane(nuevo);
        revalidate();
        repaint();
    }

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
    
}
