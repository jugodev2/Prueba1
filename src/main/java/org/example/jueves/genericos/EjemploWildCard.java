package org.example.jueves.genericos;

import java.util.List;


public class EjemploWildCard {

    public static void imprimirLista(List<?> lista){
        for (Object item : lista){
            System.out.print(item);
        }
    }

    public static void main(String[] args) {
        List<Integer> listEnteros = List.of(1,2,3,4,5,6,7,8,9);
        List<String> listString = List.of("sfs","ffddg","hug","ghg","fjkshkfj","fhsjf");

        imprimirLista(listEnteros);
        System.out.println();
        imprimirLista(listString);

    }

}
