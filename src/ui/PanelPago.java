package ui;

import javax.swing.*;
import java.awt.*;

public class PanelPago extends JPanel {

    public PanelPago(ventanaPrincipal ventana) {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Método de pago", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        String[] metodos = {"Tarjeta", "Efectivo", "Bizum"};
        JComboBox<String> comboPago = new JComboBox<>(metodos);

        JPanel centro = new JPanel();
        centro.add(comboPago);
        add(centro, BorderLayout.CENTER);

        JButton btnFinalizar = new JButton("Finalizar");

        btnFinalizar.addActionListener(e -> {
            String metodo = (String) comboPago.getSelectedItem();
            System.out.println("Método de pago: " + metodo);

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido completado con pago: " + metodo,
                    "Listo",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        add(btnFinalizar, BorderLayout.SOUTH);
    }
}
