package org.example.proyecto.utils;

import java.util.Scanner;

public class ValidacionesNumericas {

    public static long validarId(Scanner s,String tipo) {
        long id;
        while (true) {
            System.out.print("INGRESA EL ID DE " + tipo + ": ");
            if (s.hasNextLong()) {
                id = s.nextLong();
                s.nextLine();
                if (id >= 1) {
                    return id;
                } else {
                    System.out.println("EL ID DEBE SER MAYOR A CERO.");
                }
            } else {
                System.out.println("ERROR, EL ID DEBE SER UN ENTERO");
                s.nextLine();
            }
        }
    }
}
