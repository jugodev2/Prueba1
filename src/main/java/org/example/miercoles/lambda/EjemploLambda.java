package org.example.miercoles.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EjemploLambda {
    public static void main(String[] args) {
        List<Integer> lista = Arrays.asList(1,2,3,4,5);
        List<Integer> nuevaLista = lista.stream()
                .filter(n -> n%2 !=0)
                .toList();

        System.out.println(lista);
        System.out.println(nuevaLista);
        int suma = lista.stream()
                .reduce(0, Integer::sum);
        System.out.println("La suma de los numeros es: "+suma);

        List<String> cadenas = Arrays.asList("Uno","Dos", "Tres");
        String cadena = cadenas.stream()
                .reduce("",String::concat);
        System.out.println("La suma de las cadenas es: "+cadena);

        List<Persona> personas = Arrays.asList(
                new Persona("Javier", 50),
                new Persona("Juan", 18),
                new Persona("Jaime", 18),
                new Persona("Jose", 35),
                new Persona("Joaquin", 35)
        );

        Map<Integer, List<Persona>> agrupadasEdad = personas.stream()
                .collect(Collectors.groupingBy(Persona::getEdad));
        System.out.println(agrupadasEdad);

        personas.sort(Comparator.comparing(Persona::getNombre).thenComparing(Persona::getEdad));
        System.out.println("Ordenados por nombre y edad "+ personas);

        personas.sort(Comparator.comparing(Persona::getEdad).thenComparing(Persona::getNombre));
        System.out.println("Ordenados por edad y nombre "+ personas);

        List<String> frutas = Arrays.asList("manzana","pera","banana","cereza","kiwi","uva");
        frutas.sort(String::compareTo);

        List<String> caracteres = frutas.stream()
                .map(f -> f.length() > 3 ? f.substring(0,3):f)
                .toList();
        System.out.println(caracteres);

        String texto = "Pedro Pablo Perez pereira, pobre pintor portuguez, pinta paisajes" +
                "por poca plata, para pasear por Paris ";

        Function<String,String> limpiar = c -> c.replace(" ", "").toUpperCase();
        System.out.println(limpiar.apply(texto));

        List<String> cadenas2 = Arrays.asList("Ejemplo","de","uso","de","joining");
        String resultado = cadenas2.stream()
                .collect(Collectors.joining(" "));
        System.out.println(resultado);

        List<Integer> enteros = Arrays.asList(1,2,3,4,5,6,7,8,9,-10);
        boolean sonPositivos = enteros.stream().
                allMatch(n -> n > 0);
        System.out.println(sonPositivos ? "Todos son positivos" : "No todos son positivos");

        boolean sonPositivos1 = enteros.stream().
                anyMatch(n -> n > 0);
        System.out.println(sonPositivos ? "Todos son positivos" : "No todos son positivos");

        boolean sonPositivos2 = enteros.stream().
                noneMatch(n -> n > 0);
        System.out.println(sonPositivos ? "Todos son positivos" : "No todos son positivos");

        List<Integer> enteros2 = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        int sumaImpares = enteros.stream()
                .filter(n -> n % 2 != 0)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Suma de Impares : " + sumaImpares);

    }
}
