package ui;

import javax.swing.*;
import java.awt.*;

public class PanelPago extends JPanel {

    public PanelPago(ventanaPrincipal ventana) {
        setLayout(new BorderLayout(10, 10));

        // ----- TÍTULO -----
        JLabel titulo = new JLabel("Método de pago", SwingConstants.CENTER);
        titulo.setFont(new Font("JetBrains", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        // ----- BARRA DE PROGRESO -----
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setVisible(false);
        add(progressBar, BorderLayout.NORTH);

        // ----- MÉTODO DE PAGO -----
        String[] metodos = { "Tarjeta", "Bizum", "Efectivo" };
        JComboBox<String> comboPago = new JComboBox<>(metodos);

        JPanel panelMetodo = new JPanel();
        panelMetodo.add(new JLabel("Selecciona método:"));
        panelMetodo.add(comboPago);

        // ================== TARJETA ==================
        JPanel panelTarjeta = new JPanel(new GridLayout(4, 2, 5, 5));
        panelTarjeta.setBorder(BorderFactory.createTitledBorder("Datos de la tarjeta"));

        JTextField txtTitular = new JTextField();
        JTextField txtNumero = new JTextField();
        JTextField txtCaducidad = new JTextField();
        JPasswordField txtCVV = new JPasswordField();

        panelTarjeta.add(new JLabel("Titular:"));
        panelTarjeta.add(txtTitular);
        panelTarjeta.add(new JLabel("Número:"));
        panelTarjeta.add(txtNumero);
        panelTarjeta.add(new JLabel("Caducidad (MM/AA):"));
        panelTarjeta.add(txtCaducidad);
        panelTarjeta.add(new JLabel("CVV:"));
        panelTarjeta.add(txtCVV);

        // ================== BIZUM ==================
        JPanel panelBizum = new JPanel(new GridLayout(2, 2, 5, 5));
        panelBizum.setBorder(BorderFactory.createTitledBorder("Bizum"));

        String[] prefijos = { "+34 España", "+33 Francia", "+39 Italia", "+351 Portugal" };
        JComboBox<String> comboPrefijo = new JComboBox<>(prefijos);
        JTextField txtTelefono = new JTextField();

        panelBizum.add(new JLabel("Prefijo:"));
        panelBizum.add(comboPrefijo);
        panelBizum.add(new JLabel("Teléfono:"));
        panelBizum.add(txtTelefono);

        // ================== FORMULARIOS ==================
        CardLayout cardLayout = new CardLayout();
        JPanel panelFormularios = new JPanel(cardLayout);

        panelFormularios.add(new JPanel(), "Efectivo");
        panelFormularios.add(panelTarjeta, "Tarjeta");
        panelFormularios.add(panelBizum, "Bizum");

        // ----- CENTRO -----
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.add(panelMetodo);
        centro.add(panelFormularios);

        add(centro, BorderLayout.CENTER);

        // ----- CAMBIO DE MÉTODO -----
        comboPago.addActionListener(e -> {
            String metodo = (String) comboPago.getSelectedItem();
            cardLayout.show(panelFormularios, metodo);
        });

        // ----- BOTÓN FINALIZAR -----
        JButton btnFinalizar = new JButton("Finalizar");

        btnFinalizar.addActionListener(e -> {
            String metodo = (String) comboPago.getSelectedItem();

            // VALIDACIONES
            if ("Tarjeta".equals(metodo)) {
                if (txtTitular.getText().isEmpty()
                        || txtNumero.getText().isEmpty()
                        || txtCaducidad.getText().isEmpty()
                        || txtCVV.getPassword().length == 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Rellena todos los datos de la tarjeta",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if ("Bizum".equals(metodo)) {
                if (txtTelefono.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Introduce el número de teléfono para Bizum",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // ----- PROCESO CON BARRA DE CARGA -----
            btnFinalizar.setEnabled(false);
            progressBar.setValue(0);
            progressBar.setVisible(true);

            SwingWorker<Void, Integer> worker = new SwingWorker<>() {

                @Override
                protected Void doInBackground() throws Exception {
                    for (int i = 0; i <= 100; i += 1) {
                        Thread.sleep(300); // simula proceso
                        publish(i); // manda progreso a la UI
                    }
                    return null;
                }

                @Override
                protected void process(java.util.List<Integer> chunks) {
                    int valor = chunks.get(chunks.size() - 1);
                    progressBar.setValue(valor);
                }

                @Override
                protected void done() {
                    progressBar.setVisible(false);
                    btnFinalizar.setEnabled(true);

                    JOptionPane.showMessageDialog(
                            PanelPago.this,
                            "Pedido completado con pago: " + metodo,
                            "Listo",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            };

            worker.execute();
        });

        add(btnFinalizar, BorderLayout.SOUTH);

        // Mostrar el formulario inicial
        cardLayout.show(panelFormularios, "Tarjeta");
    }
}
