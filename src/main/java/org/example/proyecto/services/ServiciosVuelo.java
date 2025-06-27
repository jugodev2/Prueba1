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
        Vuelo vuelo = new Vuelo();

        s.nextLine();
        String codigoVuelo = ValidacionesCadenas.validarCadenaVacia(s,
                "INGRESA EL CODIGO DE VUELO");
        vuelo.setCodigoVuelo(codigoVuelo);

        Avion avion = new Avion();

        System.out.println("SELECCIONA EL ID DEL AVION QUE DESEA");
        long avionID = s.nextLong();
        Optional<Avion> avionOptional = db.getAvionRepository().obtenerPorId(avionID);
            //AVION
        if (avionOptional.isPresent()){
            avion = avionOptional.get();
            vuelo.setAvion(avion);

            System.out.println("SELECCIONA EL ID DEL AEROPUERTO DESTINO");
            long aeropuertoID = s.nextLong();
            Optional<Aeropuerto> aeropuertoOptional = db.getAeropuertoRepository().obtenerPorId(aeropuertoID);
            Aeropuerto aeropuertoDestino = new Aeropuerto();
            //LUGAR DE DESTINO
            if (aeropuertoOptional.isPresent()){
                aeropuertoDestino = aeropuertoOptional.get();
                vuelo.setDestino(aeropuertoDestino);

                System.out.println("SELECCIONA ID DEL AEROPUERTO ORIGEN");
                long aeropuertoID2 = s.nextLong();
                Optional<Aeropuerto> aeropuertoOptional2 = db.getAeropuertoRepository().obtenerPorId(aeropuertoID2);
                Aeropuerto aeropuertoOrigen = new Aeropuerto();
                //LUGAR DE ORIGEN
                if (aeropuertoOptional2.isPresent() ){
                    aeropuertoOrigen = aeropuertoOptional2.get();
                    vuelo.setOrigen(aeropuertoOrigen);
                    //COMPARAR QUE NO SEA EL MISMO LUGAR
                    if (aeropuertoDestino.getId().equals(aeropuertoOrigen.getId())){
                        System.out.println("ERROR, No se puede guardar un VUELO con mismo destino y origen");
                    }else {
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

                        vuelo.setFechaSalida(fechSalida);
                        Estatus estatus = ValidacionEstatus.validacionEstatus(s);

                        vuelo.setEstatus(estatus);
                        System.out.println("LLEGO AL FINAL");
                        System.out.println(vuelo.getCodigoVuelo());
                        System.out.println(vuelo.getAvion());
                        System.out.println(vuelo.getDestino().getNombre());
                        System.out.println(vuelo.getEstatus());
                        System.out.println(vuelo.getFechaSalida());
                        System.out.println(vuelo.getOrigen());

                        db.getVueloRepository().insertar(vuelo);
                    }

                }
            }else {
                System.out.println("ERROR, NO SE EL ID DEL AEROPUERTO DESTINO");
            }

        }else {
            System.out.println("ERROR, NO SE ENCONTRO AL AVION");
        }


    }

    @Override
    public void actualizar() {
        listar();
        System.out.println("INGRESA EL ID DEL VUELO: ");

        while (!s.hasNextLong()){
            System.out.println("ENTRADA INVALIDA, SOLO SE PERMITEN ENTEROS");
            s.next();
            System.out.println("INGRESA EL ID DEL AEROPUERTO: ");
        }
        long id = s.nextLong();

        Optional<Vuelo> vueloOptional = db.getVueloRepository().obtenerPorId(id);
        if (vueloOptional.isPresent()) {
            s.nextLine();

            Vuelo vueloDB = vueloOptional.get();

            ServicioAvion servicioAvion = new ServicioAvion(this.db);

            servicioAvion.listar();
            s.nextLine();
            String codigoVuelo = ValidacionesCadenas.validarCadenaVacia(s,
                    "INGRESA EL CODIGO DE VUELO");

            Avion avion = new Avion();

            System.out.println("SELECCIONA EL ID DEL AVION QUE DESEA");
            long avionID = s.nextLong();
            Optional<Avion> avionOptional = this.db.getAvionRepository().obtenerPorId(avionID);
            //AVION
            if (avionOptional.isPresent()) {
                avion = avionOptional.get();

                System.out.println("SELECCIONA EL ID DEL AEROPUERTO DESTINO");
                long aeropuertoID = s.nextLong();
                Optional<Aeropuerto> aeropuertoOptional = this.db.getAeropuertoRepository().obtenerPorId(aeropuertoID);

                Aeropuerto aeropuertoDestino = new Aeropuerto();
                //LUGAR DE DESTINO
                if (aeropuertoOptional.isPresent()) {
                    aeropuertoDestino = aeropuertoOptional.get();

                    System.out.println("SELECCIONA ID DEL AEROPUERTO ORIGEN");
                    long aeropuertoID2 = s.nextLong();
                    Optional<Aeropuerto> aeropuertoOptional2 = db.getAeropuertoRepository().obtenerPorId(aeropuertoID2);
                    Aeropuerto aeropuertoOrigen = null;

                    //LUGAR DE ORIGEN
                    if (aeropuertoOptional2.isPresent()) {
                        aeropuertoOrigen = aeropuertoOptional2.get();
                        //COMPARAR QUE NO SEA EL MISMO LUGAR
                        if (aeropuertoDestino.getId().equals(aeropuertoOrigen.getId())) {
                            System.out.println("ERROR, No se puede guardar un VUELO con mismo destino y origen");
                        } else {

                            LocalDate fechSalida = null;
                            do {
                                System.out.println("AGREGA LA FECHA DE SALIDA DEL VUELO (dd/mm/aaaa)");
                                String fecha = s.nextLine();
                                try {
                                    fechSalida = ValidacionFecha.validarFehaPosterior(fecha);
                                } catch (DateTimeParseException e) {
                                    System.out.println("EL FORMATO DE LA FECHA NO ES VALIDO, DEBE SER dd/MM/yyyy");
                                } catch (FechaPosteriorExcepcion e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (fechSalida == null);

                            Estatus estatus = ValidacionEstatus.validacionEstatus(s);

                            vueloDB.setCodigoVuelo(codigoVuelo);
                            vueloDB.setAvion(avion);
                            vueloDB.setDestino(aeropuertoDestino);
                            vueloDB.setOrigen(aeropuertoOrigen);
                            vueloDB.setFechaSalida(fechSalida);
                            vueloDB.setEstatus(estatus);

                            db.getVueloRepository().editor(vueloDB);
                        }
                    }
                } else {
                    System.out.println("ERROR, NO SE EL ID DEL AEROPUERTO DESTINO");
                }

            } else {
                System.out.println("ERROR, NO SE ENCONTRO AL AVION");
            }
        }

    }

    @Override
    public void eliminar() {
        listar();
        System.out.println("INGRESA EL ID DEL VUELO");
        while (!s.hasNextLong()){
            System.out.println("ENTRADA INVALIDA, SOLO SE PERMITEN ENTEROS");
            s.next();
            System.out.println("INGRESA EL ID DE LA VUELO: ");
        }
        long id = s.nextLong();
        db.getAeropuertoRepository().eliminar(id);

        System.out.println("SE PUEDE ELIMINAR EL VUELO YA QUE TIENE AEROLINAS  RELACIONADOS");

//        boolean aeropuertoExistente = db.getAeropuertoRepository().listar()
//                .stream()
//                .anyMatch(ap -> (Objects.equals(ap.getId(), id)
//                ));
//
//        if (aeropuertoExistente) {
//            System.out.println("NO SE PUEDE ELIMINAR EL VUELO YA QUE TIENE AEROLINAS  RELACIONADOS");
//        }else {
//
//            System.out.println("VUELO ELIMINADA EXITOSAMENTE");
//        }
    }
}
