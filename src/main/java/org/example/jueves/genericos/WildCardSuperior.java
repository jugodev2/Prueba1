package org.example.jueves.genericos;

import java.util.List;

public class WildCardSuperior {
//EXTENDS APUNTA HACIA A ABAJO y Super hacia arriba
    public static void imprimir(List<? extends Number> lista){
        double suma = 0;
        for(Number num : lista){
            suma += num.doubleValue();
        }
        System.out.println("Suma: "+suma);
    }

    public static void main(String[] args) {
        List<Integer> lisEnteros = List.of(1,2,3,4,5);
        List<Double> lisDoubke = List.of(1.1,2.2,3.2,4.4,5.5);
        List<Float> lisFloat = List.of(1.0f,2.0f,3.0f,4.2f,5.5f);

        imprimir(lisEnteros);
        imprimir(lisDoubke);
        imprimir(lisFloat);

    }

}
