package org.example.martes;

import java.util.List;

public class SentenciasBucle2 {
    public static void main(String[] args) {
        for (int i = 0; i <=10 ; i++) {
            System.out.println("i = " + i);
        }

        for (int i = 15; i >= 10; i--) {
            System.out.println("i = " + i);
        }
        for (int i = 1 , j = 10; i < j; i++, j--) {
            System.out.println(i + " - " + j);
        }

        List<Integer> enteros = List.of(1,2,3,4,5,6,7,8,9);
        enteros.forEach(num -> {
            System.out.println("Imprimiendo =" + num);
        });

        for(Integer entero : enteros){
            System.out.println("Imprimiendo =" + entero);
        }

        String frase ="tres tristes tigres tragaban trigo en un trigal"; //47

        String palabra = "tres"; //4
        int cantidad = 0;
        int maxPalabras = palabra.length(); //4
        int maxFrase = frase.length() - maxPalabras; //43

        for (int i = 0; i <= maxFrase;) {//Hasta 47
            String subcadena = frase.substring(i,i + maxPalabras);
            if (subcadena.equals(palabra)){
                //subcadena.equalsIgnoreCase(palabra);
                cantidad++;
                i = i + maxPalabras;
            }else{
                i++;
            }
        }
        System.out.println("Encontrado = " + cantidad + " veces la palabra ´"+ palabra + "´ en la frase");
    }
}
