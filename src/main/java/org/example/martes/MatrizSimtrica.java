package org.example.martes;

public class MatrizSimtrica {
    public static void main(String[] args) {
        int [][] matriz = {
                {1,2,3,4},
                {2,1,0,5},
                {3,6,1,0},
                {4,5,6,1}
        };

        boolean simetrica = true;

       salir: for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] != matriz[j][i]) {
                    simetrica = false;
                    break salir;
                }
            }

        }
        System.out.println("La matriz "+ (simetrica ? "es simetrica" : "no es simtrica"));
    }

}
