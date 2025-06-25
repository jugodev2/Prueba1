package org.example.miercoles.calculadora;

import java.util.function.BiFunction;

public class Calculadora {

    public double calcular(double a, double b, Aritmetica lambda){
        return lambda.operacion(a,b);
    }
    public double calcularConBiFunction(double a, double b,
                                        BiFunction<Double,Double,Double> lambda){
        return lambda.apply(a,b);
    }
}
