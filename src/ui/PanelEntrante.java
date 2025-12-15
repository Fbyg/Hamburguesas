package ui;

import javax.swing.*;
import java.awt.*;

public class PanelEntrante extends JPanel {

    public PanelEntrante(ventanaPrincipal ventana) {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Elige tu entrante", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        String[] opciones = {"Aros de cebolla", "Nuggets", "Alitas de pollo"};
        JComboBox<String> comboEntrante = new JComboBox<>(opciones);

        JPanel centro = new JPanel();
        centro.add(comboEntrante);
        add(centro, BorderLayout.CENTER);

        JButton btnContinuar = new JButton("Continuar");

        btnContinuar.addActionListener(e -> {
            String elegido = (String) comboEntrante.getSelectedItem();

            // Aquí puedes guardar en tu lógica de negocio lo elegido
            System.out.println("Entrante elegido: " + elegido);

            ventana.cambiarPanel(new PanelPago(ventana));  // siguiente pantalla
        });

        add(btnContinuar, BorderLayout.SOUTH);
    }
}
