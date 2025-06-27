package org.example.proyecto.utils;

import java.util.Scanner;

public class ValidacionesNumericas {

    public static long validarLongPosi(Scanner s) {
        long id;
        while (true) {
            if (s.hasNextLong()) {
                id = s.nextLong();
                s.nextLine();
                if (id > 0) {
                    return id;
                } else {
                    System.out.println("EL ID DEBE SER MAYOR A CERO.");
                }
            } else {
                s.next();
                System.out.println("ERROR, EL ID DEBE SER UN ENTERO");

            }

        }
    }

    public static int validarIntPosi(Scanner s, String tipo) {
        int valor;
        while (true) {
            System.out.print(tipo + "\n");
            if (s.hasNextInt()) {
                valor = s.nextInt();
                s.nextLine();
                if (valor > 0) {
                    return valor;
                } else {
                    System.out.println("EL VALOR DEBE SER MAYOR A CERO.");
                }
            } else {
                s.next();
                System.out.println("ERROR, EL VALOR DEBE SER UN ENTERO");

            }
        }
    }
}
