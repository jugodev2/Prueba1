package org.example.proyecto;

import org.example.proyecto.services.ServicioAerolinea;
import org.example.proyecto.services.ServicioAeropuerto;
import org.example.proyecto.services.ServicioAvion;
import org.example.proyecto.services.ServiciosVuelo;

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
        ServiciosVuelo serviciosVuelo = new ServiciosVuelo(dbFake);
        ServicioAvion servicioAvion = new ServicioAvion(dbFake);

        int opcion;
        do {
            mostrarMenu();
            while (!s.hasNextInt()){
                System.out.println("INGRESE UN ENTERO");
                s.next();
            }
            opcion = s.nextInt();
            switch (opcion){
                case 1 -> servicioAerolinea.mostrarMenu();
                case 2 -> servicioAeropuerto.mostrarMenu();
                case 3 -> servicioAvion.mostrarMenu();
                case 4 -> serviciosVuelo.mostrarMenu();
                case 5 -> System.out.println("BAIIIII");
                default -> System.out.println("OPCION NO VALIDA");
            }

        } while(opcion!=5);




        servicioAerolinea.mostrarMenu();
    }
}
