package ui;
import javax.swing.*;

import java.awt.*;


public class PanelID extends JPanel {

    public PanelID(ventanaPrincipal ventana) {

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Bienvenido a Hamburguesas", SwingConstants.CENTER);
        titulo.setFont(new Font("JetBrains", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(3, 1, 2, 2));
        add(centro, BorderLayout.CENTER);

        JLabel lblId = new JLabel("Introduce tu nombre o ID:", SwingConstants.CENTER);
        JTextField txtId = new JTextField(15);

        centro.add(lblId);
        centro.add(txtId);

        JButton btnContinuar = new JButton("Continuar");
        centro.add(btnContinuar);

        btnContinuar.addActionListener(e -> {

            String cliente = txtId.getText().trim();

            if (cliente.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Introduce un nombre válido.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Revisar si existe en el repo
            ClienteRepository repo = ventana.clienteRepo;

            boolean existe = repo.getClientes().contains(cliente);

            if (!existe) {
                int opcion = JOptionPane.showConfirmDialog(
                        this,
                        "No estás registrado. ¿Deseas registrarte?",
                        "Registrar usuario",
                        JOptionPane.YES_NO_OPTION
                );

                if (opcion == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this,
                            "No puedes continuar sin registrarte.");
                    return;
                }

                // Registrar
                repo.guardarCliente(cliente);
                JOptionPane.showMessageDialog(this,
                        "Usuario registrado correctamente.");
            }

            // Guardar en pedidoActual
            ventana.pedidoActual.setNombreCliente(cliente);

            // Avanzar al siguiente panel
            ventana.cambiarPanel(new PanelEntrante(ventana));
        });
    }
}
