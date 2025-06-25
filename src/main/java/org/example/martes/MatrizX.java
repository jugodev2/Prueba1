package org.example.martes;

import java.util.Scanner;

public class MatrizX {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Ingrese el tamaño de la matriz (impar):");
        int n = s.nextInt();

        if (n % 2 == 0) {
            System.out.println("El valor es PAR, intentelo de nuevo");
        }else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ((i == j || i + j == n-1)) {
                        System.out.print("X ");
                    } else {
                        System.out.print("  ");
                    }
                }
                System.out.println();
            }
        }
    }
}
