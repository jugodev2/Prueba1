package org.example.martes;

import java.util.Arrays;
import java.util.Scanner;

public class Califiaciones {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int opcion ;

        double[] calificaciones = new double[5];
//EN EL SWITCH ORIGINAL SOLO SE USA EL BREAK
        do {
            System.out.println("-------MENU---------");
            System.out.println("1.- Agregar condiciones ");
            System.out.println("2.- Calcular promedio");
            System.out.println("3.- Salir");
            System.out.println("Selecciona una opcion");

            opcion = s.nextInt();

            switch (opcion){
                case 1 -> {
                    int cont = 0;
                    while (cont < calificaciones.length) {
                        System.out.println("Ingrese el las calificacion");
                        if (!s.hasNextDouble()){
                            System.out.println("Ingrese un valor valido");
                            s.next();
                            return;
                        }
                        double cal = s.nextDouble();

                        if (cal <= 10.00d && cal >= 0.0) {
                            calificaciones[cont] = cal;
                            cont++;
                        } else {
                                System.out.println("Ingresa un valor valido");
                        }
                    }
                    cont = 0;
                    Arrays.stream(calificaciones).forEach(numero -> System.out.print(numero + " "));
                }
                case 2 -> {
                    double total = 0;
                    if (calificaciones.length != 0){
                        for (int i = 0; i < calificaciones.length; i++) {
                            total += calificaciones[i];
                        }
                        System.out.println((total/calificaciones.length) >= 7 ? "El promedio es aprobatorio"
                                : "El promedio es reprobatorio ");
                    } else {
                        System.out.println("No se encontraron califiaciones");
                    }
                }
                case 3 -> {
                    System.out.println("Bai");
                    s.close();
                }
            default -> {
                System.out.println("Opcion no encontrada");
            }
        }
        }while (opcion!=3);




    }
}
