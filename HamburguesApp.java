import java.util.ArrayList;
import java.util.Scanner;

import repository.ClienteRepository;
import repository.Tarjeta;
import repository.TarjetaRepository;

public class HamburguesApp {
    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        ClienteRepository repo = new ClienteRepository();
        TarjetaRepository tarjetaRepo = new TarjetaRepository();

        ArrayList<String> clientesRegistrados = repo.getClientes();

        // Solicitar ID del cliente
        System.out.print("Introduce tu nombre o ID: ");
        String cliente = sc.nextLine();

        boolean clienteValido = clientesRegistrados.contains(cliente);

        if (!clienteValido) {
            System.out.print("Usuario no registrado. ¿Quieres registrarte? (si/no): ");
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("si")) {
                repo.guardarCliente(cliente);
                clienteValido = true;
                System.out.println("Usuario registrado correctamente.");
            } else {
                System.out.println("No puedes hacer pedidos sin registrarte.");
                sc.close();
                return;
            }
        }

        System.out.println("Bienvenido/a " + cliente);

        System.out.print("Elige entrantes (aros de cebolla, nuggets, alitas de pollo): ");
        String tipoEntrante = sc.nextLine();
        double precioEntrante = 0;
        switch (tipoEntrante.toLowerCase()) {
            case "aros de cebolla":
                precioEntrante = 2.50;
                break;
            case "nuggets":
                precioEntrante = 3.60;
                break;
            case "alitas de pollo":
                precioEntrante = 5.50;
                break;
            default:
                System.out.println("Ese entrante no esta en la carta");
        }

        System.out.print("Elige hamburguesa (bigmac, whopper, baconcheddar): ");
        String tipoHamb = sc.nextLine();
        boolean existeHamb = true;
        boolean existeBeb = true;
        String[] ingredientes = {};
        double precio = 0;

        switch (tipoHamb.toLowerCase()) {
            case "bigmac":
                ingredientes = new String[] { "pan", "carne de vacuno", "pepinillos", "cebolla", "cheddar" };
                precio = 9.0;
                break;
            case "whopper":
                ingredientes = new String[] { "pan", "carne de vacuno", "tomate", "lechuga", "cebolla", "pepinillos",
                        "mayonesa", "ketchup" };
                precio = 9.5;
                break;
            case "baconcheddar":
                ingredientes = new String[] { "pan", "carne de ternera", "bacon", "cebolla", "cheddar",
                        "salsa cheddar" };
                precio = 15.0;
                break;
            default:
                System.out.println("Esa hamburguesa no está disponible.");
                existeHamb = false;
        }

        // Punto de la carne
        String puntoCarne = "";
        if (existeHamb) {
            System.out.print("¿Cómo quieres el punto de la carne? (poco hecha / al punto / muy hecha): ");
            puntoCarne = sc.nextLine().toLowerCase();

            switch (puntoCarne) {
                case "poco hecha":
                case "al punto":
                case "muy hecha":
                    break;
                default:
                    System.out.println("No entiendo ese punto. La pondré al punto.");
                    puntoCarne = "al punto";
            }
        }

        // Tipo de pan
        String tipoPan = "";
        if (existeHamb) {
            System.out.print("Elige tipo de pan (normal / brioche / integral / sin gluten): ");
            tipoPan = sc.nextLine().toLowerCase();

            switch (tipoPan) {
                case "normal":
                case "brioche":
                case "integral":
                case "sin gluten":
                    break;
                default:
                    System.out.println("Ese pan no está disponible. Usaré pan normal.");
                    tipoPan = "normal";
            }
        }

        String[] ingredientesFinales = new String[ingredientes.length];
        String[] ingredientesEliminados = new String[ingredientes.length];
        int contadorFinales = 0;
        int contadorEliminados = 0;

        if (existeHamb) {
            for (String ing : ingredientes) {
                System.out.print("Deseas eliminar " + ing + "? (si/no): ");
                String resp = sc.nextLine();
                if (resp.equalsIgnoreCase("si"))
                    ingredientesEliminados[contadorEliminados++] = ing;
                else
                    ingredientesFinales[contadorFinales++] = ing;
            }
        }

        // Extras
        String[] extrasDisponibles = { "Extra queso", "Extra pepinillos", "Extra bacon", "Salsa Emy", "Caviar",
                "Cebolla caramelizada" };
        String[] extrasElegidos = new String[extrasDisponibles.length];
        int contadorExtras = 0;

        if (existeHamb) {
            for (String extra : extrasDisponibles) {
                System.out.print("¿Deseas añadir " + extra + "? (si/no): ");
                String resp = sc.nextLine();
                if (resp.equalsIgnoreCase("si")) {
                    extrasElegidos[contadorExtras++] = extra;
                    precio += 2.5;
                }
            }
        }

        // Bebida
        System.out.print("Elige bebida (cocacola, fanta, sprite, 7up, agua, cerveza): ");
        String bebida = sc.nextLine();
        double precioBebida = 0;
        switch (bebida.toLowerCase()) {
            case "cocacola":
                precioBebida = 2.50;
                break;
            case "fanta":
                precioBebida = 1.20;
                break;
            case "sprite":
                precioBebida = 1.50;
                break;
            case "7up":
                precioBebida = 1.20;
                break;
            case "agua":
                precioBebida = 0.90;
            case "cerveza":
                precioBebida = 1.10;
                break;
            default:
                System.out.println("Bebida no disponible");
        }
        if (!existeBeb)
            System.out.println("Bebida no disponible.");

        // Elegir si recoger en el local o domicilio
        System.out.print("Recoger en local o domicilio,(domicilio/local): ");
        String entrega = sc.nextLine().toLowerCase();

        boolean envioDomicilio = false;
        String direccion = "";

        switch (entrega) {
            case "domicilio":
                envioDomicilio = true;
                System.out.println("Has elegido entrega a domicilio. +2€ por gastos de envío.");

                // Pedir dirección completa
                System.out.print("Introduce tu dirección (calle, número, piso...): ");
                direccion = sc.nextLine();

                // Validación básica por si está vacío
                while (direccion.trim().isEmpty()) {
                    System.out.println("La dirección no puede estar vacía.");
                    System.out.print("Introduce tu dirección completa: ");
                    direccion = sc.nextLine();
                }

                break;

            case "local":
                System.out.println("Has elegido recoger en el local.");
                break;
            default:
                break;
        }

        // Confirmar pedido
        System.out.print("\nDeseas confirmar el pedido? (si/no): ");
        String confirmar = sc.nextLine();
        if (confirmar.equalsIgnoreCase("no")) {
            System.out.println("Pedido cancelado.");
            sc.close();
            return;
        }

        new CocinaUI();

        // Metodo de pago
        System.out.print("Elige método de pago (efectivo/tarjeta/paypal): ");
        String metodoPago = sc.nextLine();

        switch (metodoPago.toLowerCase()) {
            case "efectivo":
                System.out.print("¿Pagará al entregar o ya tiene cambio? (entregar/cambio): ");
                String efectivo = sc.nextLine();
                break;

            case "tarjeta":
                Tarjeta tarjetaGuardada = tarjetaRepo.getTarjeta(cliente);
                if (tarjetaGuardada != null) {
                    System.out.println("Se ha detectado que ya tienes tarjeta registrada.");
                    System.out.print("¿Deseas usarla para este pago? (si/no): ");
                    String usarRegistrada = sc.nextLine();
                    if (!usarRegistrada.equalsIgnoreCase("si")) {
                        tarjetaGuardada = pedirDatosTarjeta(sc);
                        preguntarGuardarTarjeta(cliente, tarjetaGuardada, tarjetaRepo, sc);
                    }
                } else {
                    tarjetaGuardada = pedirDatosTarjeta(sc);
                    preguntarGuardarTarjeta(cliente, tarjetaGuardada, tarjetaRepo, sc);
                }
                break;

            case "paypal":
                System.out.print("Introduce tu correo de PayPal: ");
                String correoPaypal = sc.nextLine();
                break;

            default:
                System.out.println("Método de pago no reconocido. Se asumirá efectivo.");
                metodoPago = "efectivo";
        }

        barraCarga();
        double precioFinal = precio + precioEntrante + precioBebida;
        if (envioDomicilio) {
            precioFinal += 5;
        }
        System.out.println("Pedido hecho. Precio total: " + precioFinal + " euros");
        sc.close();
    }

    // Datos de la tarjeta
    public static Tarjeta pedirDatosTarjeta(Scanner sc) {
        System.out.print("Introduce el número de tarjeta: ");
        String numeroTarjeta = sc.nextLine();
        System.out.print("Introduce la fecha de caducidad (MM/AA): ");
        String caducidad = sc.nextLine();
        System.out.print("Introduce el CVV: ");
        String cvv = sc.nextLine();
        return new Tarjeta(numeroTarjeta, caducidad);
    }

    public static void preguntarGuardarTarjeta(String cliente, Tarjeta tarjeta, TarjetaRepository repo, Scanner sc) {
        System.out.print("¿Deseas registrar esta tarjeta para futuras compras? (si/no): ");
        String guardar = sc.nextLine();
        if (guardar.equalsIgnoreCase("si")) {
            repo.guardarTarjeta(cliente, tarjeta);
            System.out.println("Tarjeta registrada correctamente.");
        }
    }

    public static void barraCarga() throws InterruptedException {
        final String VERDE = "\u001B[32m";
        final String RESET = "\u001B[0m";

        int total = 20;

        System.out.println("\nPreparando tu pedido...");

        for (int i = 0; i <= total; i++) {
            int porcentaje = (i * 100) / total;

            StringBuilder barra = new StringBuilder();
            barra.append("[");
            for (int j = 0; j < i; j++)
                barra.append(VERDE + "█" + RESET);
            for (int j = i; j < total; j++)
                barra.append(" ");
            barra.append("]");

            System.out.print("\r" + barra + " " + VERDE + porcentaje + "%" + RESET);
            Thread.sleep(450);
        }

        System.out.println("\n" + VERDE + "Pedido completado " + RESET + "\n");
    }

}
