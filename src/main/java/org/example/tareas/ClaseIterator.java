package org.example.tareas;

import java.util.ArrayList;
import java.util.Iterator;

public class ClaseIterator {

        public static void main(String[] args) {
            // Crear una lista de ejemplo
            ArrayList<String> nombres = new ArrayList<>();
            nombres.add("Ana");
            nombres.add("Luis");
            nombres.add("Carlos");

            // Obtener un iterador para la lista
            Iterator<String> iterador = nombres.iterator();

            // Recorrer la lista usando el iterador
            while (iterador.hasNext()) {
                String nombre = iterador.next();
                System.out.println(nombre);

                // Ejemplo de eliminación
                if (nombre.equals("Luis")) {
                    iterador.remove();
                }
            }

            System.out.println("Lista después de eliminar: " + nombres);
        }
    }

//
//En Java, una interfaz Iterator es parte del paquete java.util y se utiliza para recorrer elementos de una colección (como listas, conjuntos, etc.) de manera secuencial. Es una herramienta fundamental para trabajar con estructuras de datos en Java, ya que proporciona un mecanismo estándar para iterar sobre los elementos sin exponer los detalles internos de la colección.
//
//Métodos principales de la interfaz Iterator
//La interfaz Iterator define tres métodos clave:
//
//boolean hasNext()
//Verifica si hay más elementos en la colección para iterar.
//
//Devuelve true si hay más elementos disponibles.
//        Devuelve false si no hay más elementos.
//        E next()
//Devuelve el siguiente elemento en la iteración.
//
//Si no hay más elementos, lanza una excepción NoSuchElementException.
//void remove() (opcional)
//Elimina el último elemento devuelto por el iterador de la colección subyacente.
//
//Lanza una excepción UnsupportedOperationException si la operación no es compatible.
