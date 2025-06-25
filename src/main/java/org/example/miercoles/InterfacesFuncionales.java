package org.example.miercoles;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.*;

public class InterfacesFuncionales {
    public static void main(String[] args) {
        //METODO ABSTRACTO DE Function es apply()
        Function<String, String> f1 = s -> "Hola que tal " + s;
        System.out.println(f1.apply("Nombre"));

        //Function<String,String> f2 = cadena -> cadena.toUpperCase();

        Function<String, String> f2 = String::toUpperCase;
        System.out.println(f2.apply("lo que sea"));

        BiFunction<String,String,String> f3 = (a,b) -> a.toUpperCase().concat(b.toLowerCase());
        System.out.println(f3.apply("Nombre", "Apellido"));

        BiFunction<String, String, Integer> f4 = String::compareTo;
        System.out.println(f4.apply("ABCD","ABCDE"));

        //accept metodo Abstracto de CONSUMER
        Consumer<Date> c1 = fecha -> {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println(fecha);
            System.out.println(formato.format(fecha));
        };

        c1.accept(new Date());


        BiConsumer<String, Integer> c2 = (nombre, edad) -> {
            System.out.println(nombre + ", tiene "+edad+" años");
        };
        c2.accept("Cristian",28);

        //test es el metodo abstracto de Predicate
        Predicate<Integer> p1 = n -> n > 10;
        System.out.println(p1.test(100));
        System.out.println(p1.test(1));

        //BiPredicate<String,String> p2 = (a,b) -> a.equals(b);
        BiPredicate<String, String> p2 = String::equals;
        System.out.println(p2.test("CADENA","CADENA") ? "Son iguales" : "No son iguales");

        //get es el metodo abstracto de Supplier
        Supplier<String> s1 = () -> "Hola mundo desde Supplier!";
        System.out.println(s1.get());

        Supplier<Double> s2 = Math::random;
        System.out.println(s2.get());

    }
}
