package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelHamburguesa extends JPanel {

    public PanelHamburguesa(ventanaPrincipal ventana) {

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Personaliza tu Hamburguesa", SwingConstants.CENTER);
        titulo.setFont(new Font("JetBrains", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);
        
        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(4, 1));
        add(centro, BorderLayout.CENTER);

        // ---- 1. TIPO DE PAN ----
        JPanel panPanel = new JPanel();
        panPanel.setBorder(BorderFactory.createTitledBorder("Tipo de pan"));

        JRadioButton panBrioche = new JRadioButton("Brioche");
        JRadioButton panClásico = new JRadioButton("Clásico");
        JRadioButton panIntegral = new JRadioButton("Integral");

        ButtonGroup grupoPan = new ButtonGroup();
        grupoPan.add(panBrioche);
        grupoPan.add(panClásico);
        grupoPan.add(panIntegral);

        panClásico.setSelected(true);

        panPanel.add(panBrioche);
        panPanel.add(panClásico);
        panPanel.add(panIntegral);

        centro.add(panPanel);

        // ---- 2. PUNTO DE CARNE ----
        JPanel puntoPanel = new JPanel();
        puntoPanel.setBorder(BorderFactory.createTitledBorder("Punto de la carne"));
    

        JRadioButton pocoHecha = new JRadioButton("Poco hecha");
        JRadioButton alPunto = new JRadioButton("Al punto");
        JRadioButton muyHecha = new JRadioButton("Muy hecha");

        ButtonGroup grupoPunto = new ButtonGroup();
        grupoPunto.add(pocoHecha);
        grupoPunto.add(alPunto);
        grupoPunto.add(muyHecha);

        alPunto.setSelected(true);

        puntoPanel.add(pocoHecha);
        puntoPanel.add(alPunto);
        puntoPanel.add(muyHecha);

        centro.add(puntoPanel);

        // ---- 3. TAMAÑO DE CARNE ----
        JPanel tamañoPanel = new JPanel();
        tamañoPanel.setBorder(BorderFactory.createTitledBorder("Tamaño de la carne"));

        String[] tamaños = { "100g", "150g", "200g", "Doble 300g" };
        JComboBox<String> comboTamaño = new JComboBox<>(tamaños);

        tamañoPanel.add(comboTamaño);
        centro.add(tamañoPanel);

        

        // ---- 4. EXTRAS ----
        JPanel extrasPanel = new JPanel();
        extrasPanel.setBorder(BorderFactory.createTitledBorder("Extras"));
        

        JCheckBox extraQueso = new JCheckBox("Queso");
        JCheckBox extraCebolla = new JCheckBox("Cebolla caramelizada");
        JCheckBox extraBacon = new JCheckBox("Bacon");
        JCheckBox extraHuevo = new JCheckBox("Huevo");

        extrasPanel.add(extraQueso);
        extrasPanel.add(extraCebolla);
        extrasPanel.add(extraBacon);
        extrasPanel.add(extraHuevo);

        centro.add(extrasPanel);

        // ---- Botón CONTINUAR ----
        JButton btnContinuar = new JButton("Continuar");

        btnContinuar.addActionListener(e -> {

            String pan = panBrioche.isSelected() ? "Brioche" : panClásico.isSelected() ? "Clásico" : "Integral";

            String punto = pocoHecha.isSelected() ? "Poco hecha" : alPunto.isSelected() ? "Al punto" : "Muy hecha";

            String tamaño = (String) comboTamaño.getSelectedItem();

            List<String> extras = new ArrayList<>();
            if (extraQueso.isSelected())
                extras.add("Queso");
            if (extraCebolla.isSelected())
                extras.add("Cebolla caramelizada");
            if (extraBacon.isSelected())
                extras.add("Bacon");
            if (extraHuevo.isSelected())
                extras.add("Huevo");

            ventana.pedidoActual.setPan(pan);
            ventana.pedidoActual.setPuntoCarne(punto);
            ventana.pedidoActual.setTamañoCarne(tamaño);
            ventana.pedidoActual.setExtras(extras);

            // Pasamos al panel de pago
            ventana.cambiarPanel(new PanelPago(ventana));
        });

        add(btnContinuar, BorderLayout.SOUTH);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btnContinuar.setBackground(new Color(66, 133, 244)); // azul Google
        btnContinuar.setForeground(Color.black);
        btnContinuar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));

    }
}
