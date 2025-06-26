package org.example.proyecto;

import org.example.proyecto.services.ServicioAerolinea;
import org.example.proyecto.services.ServicioAeropuerto;

import java.util.Scanner;

public class Main {
    public static void mostrarMenu(){
            System.out.println("\n------------MENU PRINCIPAL----------");
            System.out.println("1- CRUD AEROLINEA");
            System.out.println("2- CRUD AEROPUERTOS");
            System.out.println("3- CRUD AVIONES");
            System.out.println("4- CRUD VUELOS");
            System.out.println("5- SALIR");
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        DBFake dbFake = new DBFake();
        ServicioAerolinea servicioAerolinea = new ServicioAerolinea(dbFake);
        ServicioAeropuerto servicioAeropuerto = new ServicioAeropuerto(dbFake);

        int opcion;
        do {
            mostrarMenu();
            while (!s.hasNextInt()){
                s.next();
                System.out.println("INGRESE UNA OPCION VALIDA");
            }
            opcion = s.nextInt();
            switch (opcion){
                case 1 -> servicioAerolinea.mostrarMenu();
                case 2 -> servicioAeropuerto.mostrarMenu();
                default -> System.out.println("OPCION NO VALIDA");
            }

        } while(opcion!=5);

        servicioAerolinea.mostrarMenu();
    }
}
