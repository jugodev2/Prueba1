
package org.example.martes;

public class FilasColumnas {
    public static void main(String[] args) {
        int [][] matriz = {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25},
        };
        int sumaFilas, sumaColumnas;

        for (int i = 0; i < matriz.length; i++) {
            sumaFilas = 0;
            sumaColumnas = 0;
            for (int j = 0; j < matriz.length; j++) {
                sumaFilas += matriz[i][j];
                sumaColumnas += matriz[j][i];

            }
            System.out.println("suma Filas " + i + " - " + sumaFilas);
            System.out.println("suma Columnas " + i + " - " + sumaColumnas);
        }

    }





}
