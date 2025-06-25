package org.example.martes;

import java.util.Scanner;

public class MatrizH {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Ingrese el tamaño de la matriz (impar):");
        int n = s.nextInt();

        if (n % 2 == 0) {
            System.out.println("El valor es PAR, intentelo de nuevo");
        }else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (j == 0 || i == n / 2 || (j == n - 1 && i>=n/2)) {
                        System.out.print("X");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }
}
