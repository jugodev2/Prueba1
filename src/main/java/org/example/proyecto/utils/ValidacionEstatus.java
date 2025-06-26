package org.example.proyecto.utils;

import org.example.proyecto.model.enums.Estatus;

import java.util.Scanner;

public class ValidacionEstatus {

    public static Estatus validacionEstatus(Scanner s){
        int opcion;

        do {
            System.out.println("INGRESE EL ESTATUS(1. DISPONIBLE, 2. NO DISPONIBLE)");

            if (s.hasNextInt()){
                opcion = s.nextInt();
                switch (opcion){
                    case 1: return Estatus.DISPONIBLE;
                    case 2: return Estatus.NO_DISPONIBLE;
                    default:
                        System.out.println("OPCION NO VALIDA, SOLO SE PRMITE 1 Y 2");
                }
            }else{
                System.out.println("SOLO SE PERMITEN NUMERO (1 Y 2).");
                s.nextLine();
            }

        }while (true);
    }

}
