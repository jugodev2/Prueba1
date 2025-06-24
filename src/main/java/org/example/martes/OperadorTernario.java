package org.example.martes;


import java.util.Scanner;

public class OperadorTernario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] usernames = {"Cristian", "Juan", "Pedro"};
        String[] passwords = {"12345", "54321", "123123"};

        System.out.println("Ingresa el usuario");
        String user = scanner.next();

        System.out.println("Ingresa la pass");
        String pass = scanner.next();

        boolean esAutenticado = false;

        for (int i = 0; i < usernames.length; i++) {
            esAutenticado = (usernames[i].equals(user) && passwords[i].equals(pass)) ? true : false;

            if (esAutenticado)
                break;
        }
        System.out.println("Usuario " + (esAutenticado ? "autenticado" : "no autenticado") );

    }
}
