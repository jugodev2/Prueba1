package org.example.martes;

public class EjemploMatriz {
    public static void main(String[] args) {
        int matriz [][] = {
                {35,40,50,2},
                {102,587,-600,7},
                {632,80,1,6}
        };
        int elemento = 6;
        boolean elementoEnontrado = false;
        int i, j =0;
        salir : for (i = 0; i < matriz.length; i++) {
            for (j  = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == elemento){
                    elementoEnontrado = true;
                    break salir;
                }
            }

        }
        System.out.println(elementoEnontrado? "Elemento " + elemento + " enontrado en las posiciones "
                +i+ " - "+ j : "Elemento " +elemento + " no enontrado");



    }
}
