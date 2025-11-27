import java.io.FileWriter;
import java.io.IOException;

public class pedirHamburgesas {
    public static void main(String[] args) throws InterruptedException {
        // Cliente registrado
        String[] clienteRegistrado = { "Alex", "Anna", "Marc", "Vicent", "Lucia", "Alberto", "Pau", "Alba", "Neus",
                "Joan", "David", "Andreu", "Oscar", "Nuria", "Eli", "Irene", "Martin", "Agueda", "Mar" };
        System.out.print("Introduce el ID: ");
        String cliente = System.console().readLine();

        boolean clienteValido = false;
        for (String c : clienteRegistrado) {
            if (c.equalsIgnoreCase(cliente)) {
                clienteValido = true;
                break;
            }
        }
        // Validar cliente

        if (!clienteValido) {
            System.out.println("El usuario no esta registrado, no puedes hacer pedidos");
            return;
        }
        System.out.println("Bienvenido/a " + cliente);

        // Pedir el tipo de hamburguesa
        System.out.print("Elige el tipo de hamburguesa(bigmac, whopper y baconcheddar): ");
        String tipoHamb = System.console().readLine();

        boolean existeHamb = true;
        boolean existeBeb = true;
        String[] ingredientes = {};
        double precio = 0;

        if (tipoHamb.equalsIgnoreCase("bigmac")) {
            ingredientes = new String[] { " pan", "carne de vacuno", "pepinillos", "cebolla", "cheddar" };
            precio = 6.00;
        } else if (tipoHamb.equalsIgnoreCase("whopper")) {
            ingredientes = new String[] { " pan", "carne de vacuno", "tomate", "lechuga", "cebolla", "pepinillos",
                    "mayonesa", "ketchup." };
            precio = 7.50;
        } else if (tipoHamb.equalsIgnoreCase("baconcheddar")) {
            ingredientes = new String[] { " pan", "carne de ternera", "bacon", "cebolla", "cheddar", "salsa cheddar." };
            precio = 15;
        } else {
            System.out.println("Esa hamburguesa todavía no está");
            existeHamb = false;
        }

        // Quitar ingredientes
        String[] ingredientesFinales = new String[ingredientes.length];
        String[] ingredientesEliminados = new String[ingredientes.length];
        int contadorFinales = 0;
        int contadorEliminados = 0;
        if (existeHamb) {
            for (int i = 0; i < ingredientes.length; i++) {
                System.out.print("Deseas eliminar " + ingredientes[i] + "? (si/no): ");
                String respuestas = System.console().readLine();
                if (respuestas.equalsIgnoreCase("si")) {
                    ingredientesEliminados[contadorEliminados] = ingredientes[i];
                    contadorEliminados++;
                } else {
                    ingredientesFinales[contadorFinales] = ingredientes[i];
                    contadorFinales++;
                }
            }
            System.out.println("\nIngredientes finales de tu " + tipoHamb + ":");
            if (contadorFinales == 0) {
                System.out.println("- (sin ingredientes)");
            } else {
                for (int i = 0; i < contadorFinales; i++) {
                    System.out.println("- " + ingredientesFinales[i]);
                }
            }

        }

        // Pedir extras
        String[] extrasElegidos = new String[6];
        int contadorExtras = 0;
        if (existeHamb) {
            String[] extrasDisponibles = { "Extra queso", "Extra pepinillos", "Extra bacon", "Salsa Emy", "Caviar",
                    "Cebolla caramelizada" };
            System.out.println("\n¿Quieres añadir extras a tu hamburguesa?");
            for (int i = 0; i < extrasDisponibles.length; i++) {
                System.out.print("¿Deseas añadir " + extrasDisponibles[i] + "? (si/no): ");
                String respuesta = System.console().readLine();
                if (respuesta.equalsIgnoreCase("si")) {
                    extrasElegidos[contadorExtras] = extrasDisponibles[i];
                    contadorExtras++;
                    precio += 2.5;
                }
            }
        }
        // Pedir bebida
        System.out.print("Elige bedida(cocacola, fanta, sprite, 7up, agua y cerveza): ");
        String bebida = System.console().readLine();

        if (bebida.equalsIgnoreCase(bebida)) {
            System.out.println("Has elegido: " + bebida);
        } else {
            System.out.println("Esa no la tenemos");
            existeBeb = false;
        }

        // Cuenta atras para hacer la hamburguesa
        if (existeHamb && existeBeb) {
            System.out.println("Preparando tu " + tipoHamb);
            if (contadorExtras == 0) {
                System.out.println("sin extras");
            } else {
                for (int i = 0; i < contadorExtras; i++) {
                    System.out.println("-" + extrasElegidos[i]);
                }
            }
        }

        System.out.print("Deseas confirmar el pedido? (si, no): ");
        String confirmar = System.console().readLine();

        while (!confirmar.equalsIgnoreCase("si") && !confirmar.equalsIgnoreCase("no")) {
            System.out.print("Por favor responde (si, no): ");
            confirmar = System.console().readLine();
        }

        if (confirmar.equalsIgnoreCase("no")) {
            System.out.print("Pedido cancelado");
            return;
        }
        System.out.println("Su pedido ha sido confirmado");

        int segundos = 30;
        for (int i = segundos; i >= 0; i--) {
            System.out.print("\rTu pedido estará en: " + i);
            Thread.sleep(1000);
        }
        System.out.print("\r Tu " + tipoHamb + " y " + bebida + " ya esta lista");
        System.out.printf("\n Precio total del pedido: %.2f \n", precio);

        int limitePedidos = 10;

        int contadorPedidos = 0;

        // Leer el contador actual
        try {
            java.io.File fileContador = new java.io.File("contador.txt");

            // Si no existe, lo creamos con 0
            if (!fileContador.exists()) {
                try (FileWriter fwCont = new FileWriter(fileContador)) {
                    fwCont.write("0");
                }
            }

            // Leer el número actual
            java.util.Scanner scanner = new java.util.Scanner(fileContador);
            contadorPedidos = Integer.parseInt(scanner.nextLine());
            scanner.close();

            // Sumamos 1 porque se va a generar un nuevo pedido
            contadorPedidos++;

            // Si llegamos al límite, reiniciar archivo pedidos.txt
            if (contadorPedidos >= limitePedidos) {
                System.out.println("\n⚠ Atención: se alcanzó el límite de " + limitePedidos + " pedidos.");
                System.out.println("El archivo pedidos.txt ha sido reiniciado.");

                // Borrar contenido del fichero pedidos.txt
                new FileWriter("pedidos.txt", false).close();

                // Reiniciar contador
                contadorPedidos = 0;
            }

            // Guardar el nuevo valor del contador
            try (FileWriter fwCont = new FileWriter("contador.txt", false)) {
                fwCont.write(String.valueOf(contadorPedidos));
            }

        } catch (IOException e) {
            System.out.println("Error al gestionar el contador de pedidos.");
        }

        // Imprimir pedido
        int numeroPedido = (int) (Math.random() * 1000 + 1);
        System.out.println("\nNúmero de pedido: #" + numeroPedido);
        try (FileWriter fw = new FileWriter("pedidos.txt", true)) {
            fw.write("=== Pedido #" + numeroPedido + " ===\n");
            fw.write("Cliente: " + cliente + "\n");
            fw.write("Hamburguesa: " + tipoHamb + "\n");
            fw.write("Ingredientes eliminados: \n");
            if (contadorEliminados == 0) {
                fw.write("- Ninguno \n");
            } else {
                for (int i = 0; i < contadorEliminados; i++) {
                    fw.write(" - " + ingredientesEliminados[i] + "\n");
                }
            }
            fw.write("Extras: \n");
            for (int i = 0; i < contadorExtras; i++) {
                fw.write(" - " + extrasElegidos[i] + "\n");
            }
            fw.write("Bebida: " + bebida + "\n");
            fw.write(String.format("Precio total: %.2f €\n", precio));
            fw.write("===========================\n\n");
        } catch (IOException e) {
            System.out.println("Error al guardar el pedido.");
        }

    }
}
