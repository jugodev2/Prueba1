package org.example.miercoles;

import java.util.Optional;

public class EjemploOptional {


    public static void main(String[] args) {

        Optional<String> nombre = Optional.empty();
        Optional<String> saludo = Optional.of("Hola Mundo");

        System.out.println("El nombre esta vacio?" + nombre.isEmpty());
        System.out.println("El saludo esta vacio?" + saludo.isPresent());

        System.out.println("El nombre esta vacio?" + nombre.isEmpty());
        System.out.println("El saludo esta vacio?" + saludo.isEmpty());

        nombre.ifPresent(System.out::println);
        saludo.ifPresent(System.out::println);

        System.out.println("Nombre: "+nombre.orElse("Valor por defecto si el nombre esya vacio"));
        System.out.println("Saludo: "+saludo.orElse("Valor por defecto si el saludo esya vacio"));

        System.out.println("Nombre generado: " + nombre.orElseGet( () ->
                "Nombre generado por la funcion provider"
                ));

        try {
            String nombreObtenido = nombre.orElseThrow( () -> new IllegalArgumentException(
                    "El nombre no esta presente, exepion arrojada y llega al cath."
            ));
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }


}
