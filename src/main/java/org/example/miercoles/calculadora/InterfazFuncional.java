package org.example.miercoles.calculadora;

import java.util.function.BiFunction;

public class InterfazFuncional {
    public static void main(String[] args) {
        //Aritmetica suma = (a,b) -> a+b;
        Aritmetica suma =Double::sum;
        Aritmetica resta = (a,b) -> a-b;
        BiFunction<Double, Double, Double> division = (a,b) -> a/b;
        BiFunction<Double, Double, Double> producto = (a,b) -> a*b;

        Calculadora calculadora = new Calculadora();
        System.out.println(calculadora.calcular(5.0,2.0, suma));
        System.out.println(calculadora.calcular(5.0,2.0, resta));
        System.out.println(calculadora.calcularConBiFunction(5.0,2.0, division));
        System.out.println(calculadora.calcularConBiFunction(5.0,2.0, producto));
    }
}
