package org.example.jueves.genericos;

public class CajaGenrico<T> {

    private T valor;

    public CajaGenrico(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }

    public static <T> void imprimir(T dato){
        System.out.println(dato);
        }

    public static void main(String[] args) {

        CajaGenrico<String> cajaString = new CajaGenrico<>("Prueba Caja");
        System.out.println("Valor de la caja " + cajaString.getValor());

        CajaGenrico<Integer> cajaInteger = new CajaGenrico<>(1521);
        System.out.println("Valor de la caja " + cajaInteger.getValor());

        CajaGenrico<Double> cajaDouble = new CajaGenrico<>(1521.23);
        System.out.println("Valor de la caja " + cajaDouble.getValor());

        System.out.println();
        imprimir(100);
        imprimir("Hola Mundo!!");
        imprimir(3.1411516);

    }


}
