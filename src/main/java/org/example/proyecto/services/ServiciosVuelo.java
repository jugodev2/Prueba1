package org.example.proyecto.services;

import org.example.proyecto.DBFake;
import org.example.proyecto.model.entities.Vuelo;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ServiciosVuelo implements IServices {
    private static final Scanner s = new Scanner(System.in);
    
    public final DBFake db;
    
    public ServiciosVuelo(DBFake db){
        this.db = db;
    }


    @Override
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("------------MENU----------");
            System.out.println("1- Listar VUELO");
            System.out.println("2- INSERTAR VUELO");
            System.out.println("3- EDITAR VUELO");
            System.out.println("4- ELIMINAR VUELO");
            System.out.println("5- VOLVER AL MENU PRINCIPAL");
            opcion = s.nextInt();
            switch (opcion){
                case 1 -> listar();
                case 2 -> insertar();
                case 3 -> actualizar();
                case 4 -> eliminar();
                case 5 -> {return;}
                default -> System.out.println("OPCION INVALIDA");
            }
        }while (opcion!=5);
    }

    @Override
    public void listar() {
        System.out.println("LISTA DE VUELOS: \n");
        List<Vuelo> vuelos = db.getVueloRepository().listar();
        if (vuelos.isEmpty()){
            System.out.println("NO HAY VUELOS");
        }else {
            System.out.println(vuelos.stream().map(v -> "VUELO: " +
                            v.getId() + "\n" +
                            "Codigo: " + v.getCodigoVuelo() + "\n" +
                            "Avion: " + v.getAvion().getCodigoModelo() + "\n" +
                            "Aeropuerto de Origen: " + v.getOrigen().getNombre() + "\n" +
                            "Aeropuerto de Desstino: " + v.getDestino() + "\n" +
                            "Estatus: " + v.getEstatus().getEstatusEnum() + "\n"+
                            "Fecha de Salida: "+ v.getFechaSalida()

                    )
                    .collect(Collectors.joining("\n")));
        }
    }

    @Override
    public void insertar() {

    }

    @Override
    public void actualizar() {

    }

    @Override
    public void eliminar() {

    }
}
