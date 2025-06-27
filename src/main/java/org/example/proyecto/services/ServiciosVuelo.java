package org.example.proyecto.services;

import org.example.proyecto.DBFake;
import org.example.proyecto.exceptions.FechaAnteriorException;
import org.example.proyecto.exceptions.FechaPosteriorExcepcion;
import org.example.proyecto.model.entities.Aeropuerto;
import org.example.proyecto.model.entities.Avion;
import org.example.proyecto.model.entities.Vuelo;
import org.example.proyecto.model.enums.Estatus;
import org.example.proyecto.utils.ValidacionEstatus;
import org.example.proyecto.utils.ValidacionFecha;
import org.example.proyecto.utils.ValidacionesCadenas;
import org.example.proyecto.utils.ValidacionesNumericas;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
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
            System.out.println("1- LISTAR VUELO");
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
        s.nextLine();
        String codigoVuelo = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL CODIGO DE VUELO");

        //VALIDACION DEL AVION
        System.out.println("SELECCIONA EL ID DEL AVION QUE DESEA");
        long avionID = ValidacionesNumericas.validarLongPosi(s);
        Optional<Avion> avionOptional = db.getAvionRepository().obtenerPorId(avionID);
        if (avionOptional.isPresent()){
            Avion avion = avionOptional.get();

            //LUGAR DE DESTINO
            System.out.println("SELECCIONA EL ID DEL AEROPUERTO DESTINO");
            long aeropuertoID = ValidacionesNumericas.validarLongPosi(s);
            Optional<Aeropuerto> aeropuertoDestOpt = db.getAeropuertoRepository().obtenerPorId(aeropuertoID);
            if (aeropuertoDestOpt.isPresent()){
                Aeropuerto aeropuertoDestino = aeropuertoDestOpt.get();

                //LUGAR DE DESTINO
                System.out.println("SELECCIONA ID DEL AEROPUERTO ORIGEN");
                long aeropuertoID2 = ValidacionesNumericas.validarLongPosi(s);
                Optional<Aeropuerto> aeropuertoOritOpt = db.getAeropuertoRepository().obtenerPorId(aeropuertoID2);
                if (aeropuertoOritOpt.isPresent() ){
                    Aeropuerto aeropuertoOrigen = aeropuertoOritOpt.get();

                    //COMPARAR QUE NO SEA EL MISMO LUGAR
                    if (!(aeropuertoDestino.getId().equals(aeropuertoOrigen.getId()))){
                        s.nextLine();
                        LocalDate fechSalida = null;
                        do {
                            System.out.println("AGREGA LA FECHA DE SALIDA DEL VUELO (dd/mm/aaaa)");
                            String fecha = s.nextLine();
                            try {
                                fechSalida = ValidacionFecha.validarFehaPosterior(fecha);
                            }catch (DateTimeParseException e){
                                System.out.println("EL FORMATO DE LA FECHA NO ES VALIDO, DEBE SER dd/MM/yyyy");
                            }catch (FechaPosteriorExcepcion e){
                                System.out.println(e.getMessage());
                            }
                        }while (fechSalida == null);

                        Estatus estatus = ValidacionEstatus.validacionEstatus(s);

                        Vuelo vuelo = new Vuelo();
                        vuelo.setCodigoVuelo(codigoVuelo);
                        vuelo.setAvion(avion);
                        vuelo.setDestino(aeropuertoOrigen);
                        vuelo.setOrigen(aeropuertoOrigen);
                        vuelo.setFechaSalida(fechSalida);
                        vuelo.setEstatus(estatus);

                        db.getVueloRepository().insertar(vuelo);
                    }else {
                        System.out.println("ERROR, NO SE PUEDE REALIZAR UN VUELO DONDE EL ORIGENY EL DESTINO SEAN IGUALES");
                    }
                } else {
                    System.out.println("ERROR, NO SE ENCONTRO EL ID DEL AEROPUERTO ORIGEN");
                }
            }else {
                System.out.println("ERROR, NO SE ENCONTRO EL ID DEL AEROPUERTO DESTINO");
            }
        }else {
            System.out.println("ERROR, NO SE ENCONTRO AL AVION");
        }

    }

    @Override
    public void actualizar() {
        System.out.println("INGRESA EL ID DEL VUELO A MODIFICAR");
        long idVuelo = ValidacionesNumericas.validarLongPosi(s);
        Optional<Vuelo> vueloOpt = db.getVueloRepository().obtenerPorId(idVuelo);
        if (vueloOpt.isPresent()){
            s.nextLine();
            String codigoVuelo = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL CODIGO DE VUELO");

            //VALIDACION DEL AVION
            System.out.println("SELECCIONA EL ID DEL AVION QUE DESEA");
            long avionID = ValidacionesNumericas.validarLongPosi(s);
            Optional<Avion> avionOptional = db.getAvionRepository().obtenerPorId(avionID);
            if (avionOptional.isPresent()){
                Avion avion = avionOptional.get();

                //LUGAR DE DESTINO
                System.out.println("SELECCIONA EL ID DEL AEROPUERTO DESTINO");
                long aeropuertoID = ValidacionesNumericas.validarLongPosi(s);
                Optional<Aeropuerto> aeropuertoDestOpt = db.getAeropuertoRepository().obtenerPorId(aeropuertoID);
                if (aeropuertoDestOpt.isPresent()){
                    Aeropuerto aeropuertoDestino = aeropuertoDestOpt.get();

                    //LUGAR DE DESTINO
                    System.out.println("SELECCIONA ID DEL AEROPUERTO ORIGEN");
                    long aeropuertoID2 = ValidacionesNumericas.validarLongPosi(s);
                    Optional<Aeropuerto> aeropuertoOritOpt = db.getAeropuertoRepository().obtenerPorId(aeropuertoID2);
                    if (aeropuertoOritOpt.isPresent() ){
                        Aeropuerto aeropuertoOrigen = aeropuertoOritOpt.get();

                        //COMPARAR QUE NO SEA EL MISMO LUGAR
                        if (!(aeropuertoDestino.getId().equals(aeropuertoOrigen.getId()))){
                            s.nextLine();
                            LocalDate fechSalida = null;
                            do {
                                System.out.println("AGREGA LA FECHA DE SALIDA DEL VUELO (dd/mm/aaaa)");
                                String fecha = s.nextLine();
                                try {
                                    fechSalida = ValidacionFecha.validarFehaPosterior(fecha);
                                }catch (DateTimeParseException e){
                                    System.out.println("EL FORMATO DE LA FECHA NO ES VALIDO, DEBE SER dd/MM/yyyy");
                                }catch (FechaPosteriorExcepcion e){
                                    System.out.println(e.getMessage());
                                }
                            }while (fechSalida == null);

                            Estatus estatus = ValidacionEstatus.validacionEstatus(s);

                            Vuelo vueloDB = vueloOpt.get();
                            vueloDB.setCodigoVuelo(codigoVuelo);
                            vueloDB.setAvion(avion);
                            vueloDB.setDestino(aeropuertoOrigen);
                            vueloDB.setOrigen(aeropuertoOrigen);
                            vueloDB.setFechaSalida(fechSalida);
                            vueloDB.setEstatus(estatus);

                            db.getVueloRepository().editor(vueloDB);
                        }else {
                            System.out.println("ERROR, NO SE PUEDE REALIZAR UN VUELO DONDE EL ORIGENY EL DESTINO SEAN IGUALES");
                        }
                    } else {
                        System.out.println("ERROR, NO SE ENCONTRO EL ID DEL AEROPUERTO ORIGEN");
                    }
                }else {
                    System.out.println("ERROR, NO SE ENCONTRO EL ID DEL AEROPUERTO DESTINO");
                }
            }else {
                System.out.println("ERROR, NO SE ENCONTRO AL AVION");
            }
        }else {
            System.out.println("NO SE ENCONTRO EL ID DEL VUELO");
        }

    }

    @Override
    public void eliminar() {
        System.out.println("INGRESA EL ID DEL VUELO A ELIMINAR");
        long idVuelo = ValidacionesNumericas.validarLongPosi(s);
        db.getVueloRepository().eliminar(idVuelo);
    }
}
