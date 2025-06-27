package org.example.proyecto;

import org.example.proyecto.model.entities.Aerolinea;
import org.example.proyecto.model.entities.Aeropuerto;
import org.example.proyecto.model.entities.Avion;
import org.example.proyecto.model.entities.Vuelo;
import org.example.proyecto.model.enums.Estatus;
import org.example.proyecto.services.ServicioAerolinea;
import org.example.proyecto.services.ServicioAeropuerto;
import org.example.proyecto.services.ServicioAvion;
import org.example.proyecto.services.ServiciosVuelo;

import java.time.LocalDate;
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

        LocalDate today = LocalDate.now();
        LocalDate vuelo = today.plusDays(-14);
        LocalDate tomorrow = today.plusDays(2);

        Avion avionPrueba =
                new Avion(null, 344l, "Boing",
                        "hukdf",145,vuelo, Estatus.DISPONIBLE, new Aerolinea()
                );

        Aeropuerto aeropuertoDestino = new Aeropuerto(null,
                "San Jose", "45","56","Paris", Estatus.DISPONIBLE);

        Aeropuerto aeropuertoOrigen = new Aeropuerto(null,
                "Yamaha", "1","8","London", Estatus.DISPONIBLE);


        Vuelo vuelo2 = new Vuelo(null,
                "3333333333",
                avionPrueba,
                aeropuertoOrigen,
                aeropuertoDestino,
                Estatus.DISPONIBLE,
                tomorrow
                );

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
                case 3 -> servicioAvion.mostrarMenu();
                case 4 -> serviciosVuelo.mostrarMenu();
                default -> System.out.println("OPCION NO VALIDA");
            }

        } while(opcion!=5);




        servicioAerolinea.mostrarMenu();
    }
}
