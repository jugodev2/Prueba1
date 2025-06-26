package org.example.proyecto.utils;

import java.util.Scanner;

public class ValidacionesNumericas {

    public static boolean validarNumeros(String cadena){
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
