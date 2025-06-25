package org.example.martes;

public class OperadoresAsignacion {
    public static void main(String[] args) {
        int i = 5;
        int r = i + 4;

        System.out.println("i = " + i);
        System.out.println("r = " + r);

        i+=2;
        System.out.println("i = "+i);

        i*=5;
        System.out.println("i = "+i);

        i/=5;
        System.out.println("i = "+i);

        //OPERADORES INCREMENTALES
        //PRE INCREMENTO
        i = 2;
        r = ++i;

        i+=2;
        System.out.println("i = " + i);
        System.out.println("r = " + r);

        i = 2;
        System.out.println("Valor inicial de i = " + i);

        r=i++;
        System.out.println("i = " + i);
        System.out.println("r = " + r);

        //POST INCREMENTO
        i = 2;
        r = i++;

        System.out.println("i = " + i);
        System.out.println("r = " + r);

        //PRE DECREMENTO
        i = 3;
        r = --i;

        System.out.println("i = " + i);
        System.out.println("r = " + r);

        //POST DECREMENTO
        i = 4;
        r = i--;

        System.out.println("i = " + i);
        System.out.println("r = " + r);

    }
}
