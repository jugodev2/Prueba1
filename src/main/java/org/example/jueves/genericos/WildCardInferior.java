package org.example.jueves.genericos;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

public class WildCardInferior {
    //LLLEGA HASTA INTEGER COMO CLASE BAJA Y SUS CLASES SUPERIORES
    //EXTENDES ES EL LIMITE INFERIOR CUANDO HEREDA
    public static void agregarElemento(List<? super Integer>  lista){
        lista.add(Integer.valueOf(45));
    }

    public static void main(String[] args) {
        List<Number> listNumber = new ArrayList<>(List.of(-1,-2,3.4564,4.00002,0.000008,8));
        List<Object> listObject = new ArrayList<>(List.of("a", 2,3.1415, true));

        agregarElemento(listNumber);
        agregarElemento(listObject);

        System.out.println(listNumber);
        System.out.println(listObject);

    }

}
