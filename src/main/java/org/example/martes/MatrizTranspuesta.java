package org.example.martes;

public class MatrizTranspuesta {

    public static void main(String[] args) {
        int [][] matriz = {
                {1,2,3,4},
                {4,5,6,7},
                {58,7899,135,36},
                {545,26,88,66}
        };
        System.out.println("Matriz Original");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
        int aux;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < i; j++) {
                aux = matriz[i][j];
                matriz[i][j] = matriz[j][i];
                matriz[j][i]=aux;
            }
        }
        System.out.println();
        System.out.println("Matriz Transpuesta");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
