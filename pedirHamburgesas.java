public class pedirHamburgesas {
    public static void main(String[] args) throws InterruptedException {
        // Pedir el tipo de hamburguesa
        System.out.print("Elige el tipo de hamburguesa(bigmac, whopper y baconcheddar): ");
        String tipoHamb = System.console().readLine();

        boolean existeHamb = true;
        boolean existeBeb = true;
        
        if (tipoHamb.equalsIgnoreCase("bigmac")) {
            System.out.println("Ingredientes: pan, carne de vacuno, pepinillos, cebolla y cheddar");
        } else if (tipoHamb.equalsIgnoreCase("whopper")) {
            System.out.println("Ingredientes: pan, carne de vacuno, tomate, lechuga, cebolla, pepinillos, mayonesa y ketchup.");
        } else if (tipoHamb.equalsIgnoreCase("baconcheddar")) {
            System.out.println("Ingredientes: pan, carne de ternera, bacon, cebolla, cheddar y salsa cheddar.");
        } else {
            System.out.println("Esa hamburguesa todavía no está");
            existeHamb = false;
        }

        // Pedir extras
        String[] extrasElegidos = new String[6];
        int contadorExtras = 0;
        if (existeHamb) {
            String[] extrasDisponibles = {"Extra queso", "Extra pepinillos", "Extra bacon", "Salsa Emy", "Caviar", "Cebolla caramelizada"};
            System.out.println("\n¿Quieres añadir extras a tu hamburguesa?");
            for(int i = 0; i < extrasDisponibles.length; i++) {
                System.out.print("¿Deseas añadir " + extrasDisponibles[i] + "? (si/no): ");
                String respuesta = System.console().readLine();
                if (respuesta.equalsIgnoreCase("si")) {
                    extrasElegidos[contadorExtras] = extrasDisponibles[i];
                    contadorExtras++;
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
            System.out.println("Preparando tu " + tipoHamb + " con los extras ");
            if (contadorExtras == 0) {
                System.out.println("sin extras");
            }else {
                for(int i = 0; i < contadorExtras; i++) {
                    System.out.println("-" + extrasElegidos[i]);
                }
            }
        }

        int segundos = 30;
            for (int i = segundos; i >= 0; i--) {
                System.out.print("\rTu pedido estará en: " + i);
                Thread.sleep(1000);
            }
            System.out.print("\r Tu " + tipoHamb + " y " + bebida + " ya esta lista");
            

    }
}
