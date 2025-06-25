package org.example.miercoles;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaNumeros {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int opcion;
        List<Integer> numeros = new ArrayList<>();
        do {
            System.out.println("-------MENU---------");
            System.out.println("1.- Agregar un numero a la lista ");
            System.out.println("2.- Remover un numero a la lista ");
            System.out.println("3.- Mostrar todos los numeros de la lista ");
            System.out.println("4.- Calcular la suma de los numeros de la lista ");
            System.out.println("5.- Mostrar los numero primos de la lista");
            System.out.println("6.- Salir ");
            opcion = s.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Ingrese el numero a insertar");
                    int numero = s.nextInt();
                    numeros.add(numero);
                }
                case 2 -> {
                    System.out.println("Ingrese el numero a elimnar");
                    int numero = s.nextInt();
                    for (int i = 0; i < numeros.size(); i++) {
                        if (numeros.get(i).equals(numero))
                            numeros.remove(i);
                        else
                            System.out.println("Ese numero no existe dentro de la lista");
                    }
                }
                case 3 -> {
                    System.out.println("Los numeros de la lista son");
                    numeros.forEach(System.out::println);
                }
                case 4 -> {
                    int suma = 0;
                    for (int i = 0; i < numeros.size(); i++) {
                        if (!numeros.isEmpty())
                            suma += numeros.get(i);
                        else
                            System.out.println("Lista Vacia1");
                    }
                    System.out.println("La suma de todas los numeros es; " + suma);

                    //int suma = numeros.stream().reduce(0, Integer::suma);
                }
                case 5 -> {
                    for (Integer num : numeros) {

                        if (num > 1) {
                            boolean esPrimo = true;
                            for (int i = 2; i < Math.sqrt(num); i++) {
                                if (num % i == 0){
                                    esPrimo = false;
                                    break;
                                }
                            }
                            if (esPrimo){
                                System.out.println(num);
                            }
                        }
                    }
                }
                case 6 -> System.out.println("Bait");
                default -> System.out.println("Opcion no encontrada");
            }
        }while (opcion != 6) ;
    }
}
