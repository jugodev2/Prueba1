package org.example.proyecto.utils;

import java.util.Scanner;

public class ValidacionesCadenas {

    public static String validarCadenaVacia(Scanner s, String mensaje){
        String dato;
        do {
            System.out.println(mensaje);
            dato = s.nextLine().trim();
            if (dato.isEmpty()){
                System.out.println("EL CAMPO NO DEBE SER VACIO");
            }
        }while (dato.isEmpty());
        return dato;
    }



}
