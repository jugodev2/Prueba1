package org.example.proyecto.services;

import org.example.proyecto.DBFake;
import org.example.proyecto.exceptions.FechaAnteriorException;
import org.example.proyecto.model.entities.Aerolinea;
import org.example.proyecto.model.entities.Avion;
import org.example.proyecto.model.enums.Estatus;
import org.example.proyecto.utils.ValidacionEstatus;
import org.example.proyecto.utils.ValidacionFecha;
import org.example.proyecto.utils.ValidacionesCadenas;
import org.example.proyecto.utils.ValidacionesNumericas;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ServicioAvion  implements IServices{
    private static final Scanner s = new Scanner(System.in);

    public final DBFake db;

    public ServicioAvion(DBFake db){
        this.db = db;
    }

    @Override
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("------------MENU----------");
            System.out.println("1- LISTAR AVIONES");
            System.out.println("2- INSERTAR AVION");
            System.out.println("3- EDITAR AVION");
            System.out.println("4- ELIMINAR AVION");
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
        System.out.println("LISTA DE AVIONES: \n");
        List<Avion> avions = db.getAvionRepository().listar();
        if (avions.isEmpty()){
            System.out.println("NO HAY AVIONES");
        }else {
            System.out.println(avions.stream().map(a -> "AVIONES: " +
                            a.getId() + "\n" +
                            "Numero de Registro: " + a.getNumeroRegistro() + "\n" +
                            "Tipo: " + a.getTipo() + "\n" +
                            "Condion Modelo: " + a.getCodigoModelo() + "\n" +
                            "Capacidad: " + a.getCapacidad() + "\n" +
                            "Fecha Primer Vuelo: " + a.getFechaPrimerVuelo() + "\n" +
                            "AeroLinea: " + a.getAerolinea() + "\n" +
                            "Estatus: " + a.getEstatus().getEstatusEnum() + "\n")
                    .collect(Collectors.joining("\n")));
        }

    }

    @Override
    public void insertar() {
        System.out.println("INGRESA EL NUMERO DE REGISTRO DE VUELO");
        Long numRegistro = ValidacionesNumericas.validarLongPosi(s);
        String tipo = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL TIPO");
        String codModelo = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL CODIGO DE MODELO");

        int capacidad = ValidacionesNumericas.validarIntPosi(s, "INGRESA LA CAPACIDAD");

        LocalDate fechaPrimerVuelo = null;
        do {
            System.out.println("AGREGA LA FECHA DE PRIMER VUELO DEL AVION (dd/mm/aaaa)");
            String fecha = s.nextLine();

            try {
                fechaPrimerVuelo = ValidacionFecha.validarFehaAnterior(fecha);
            }catch (DateTimeParseException e){
                System.out.println("EL FORMATO DE LA FECHA NO ES VALIDO, DEBE SER dd/MM/yyyy");
            }catch (FechaAnteriorException e){
                System.out.println(e.getMessage());
            }
        }while (fechaPrimerVuelo == null);

        Estatus estatus = ValidacionEstatus.validacionEstatus(s);
        /// AEROLINEA
        System.out.println("SELECCIONA EL ID DE LA AEROLINEA");
        long aeroID = ValidacionesNumericas.validarLongPosi(s);
        //long aeroID = s.nextLong();
        Optional<Aerolinea> aerolineaOptional = this.db.getAerolineaRepository().obtenerPorId(aeroID);
        Aerolinea aerolinea;
        if (aerolineaOptional.isPresent()){
            aerolinea = aerolineaOptional.get();

            Avion avionSave = new Avion(null, numRegistro,
                    tipo, codModelo, capacidad,
                    fechaPrimerVuelo, estatus, aerolinea);
            db.getAvionRepository().insertar(avionSave);
        }else {
            System.out.println("NO SE AGREGO EL AVION: LA AEROLINEA INGRESADA NO EXISTE");
        }

    }

    @Override
    public void actualizar() {
        long avionID = ValidacionesNumericas.validarLongPosi(s);
        Optional<Avion> optionalAvion = db.getAvionRepository().obtenerPorId(avionID);
        if (optionalAvion.isPresent()){

            Avion avionDB = optionalAvion.get();

            System.out.println("INGRESA EL NUMERO DE REGISTRO DE VUELO");
            Long numRegistro = ValidacionesNumericas.validarLongPosi(s);
            String tipo = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL TIPO");
            String codModelo = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL CODIGO DE MODELO");

            int capacidad = ValidacionesNumericas.validarIntPosi(s, "INGRESA LA CAPACIDAD");

            LocalDate fechaPrimerVuelo = null;
            do {
                System.out.println("AGREGA LA FECHA DE PRIMER VUELO DEL AVION (dd/mm/aaaa)");
                String fecha = s.nextLine();

                try {
                    fechaPrimerVuelo = ValidacionFecha.validarFehaAnterior(fecha);
                }catch (DateTimeParseException e){
                    System.out.println("EL FORMATO DE LA FECHA NO ES VALIDO, DEBE SER dd/MM/yyyy");
                }catch (FechaAnteriorException e){
                    System.out.println(e.getMessage());
                }
            }while (fechaPrimerVuelo == null);

            Estatus estatus = ValidacionEstatus.validacionEstatus(s);
            /// OBTENER AEROLINEA
            System.out.println("SELECCIONA EL ID DE LA AEROLINEA");
            long aeroID = ValidacionesNumericas.validarLongPosi(s);
            Optional<Aerolinea> aerolineaOptional = this.db.getAerolineaRepository().obtenerPorId(aeroID);
            if (aerolineaOptional.isPresent()){
                Aerolinea aerolinea = aerolineaOptional.get();

                avionDB.setNumeroRegistro(numRegistro);
                avionDB.setTipo(tipo);
                avionDB.setCodigoModelo(codModelo);
                avionDB.setCapacidad(capacidad);
                avionDB.setFechaPrimerVuelo(fechaPrimerVuelo);
                avionDB.setAerolinea(aerolinea);

                db.getAvionRepository().editor(avionDB);
            }else {
                System.out.println("NO SE AGREGO EL AVION: LA AEROLINEA DIGITALIZADA NO EXISTE");
            }
        }else {
            System.out.println("EL AVION NO EXISTE, ID NO RECONOCIDO");
        }
    }

    @Override
    public void eliminar() {

        long id = ValidacionesNumericas.validarLongPosi(s);
        //SE VALIDA LA EXISTENCIA DEL AVION
        Optional<Avion> avionOptional = db.getAvionRepository().obtenerPorId(id);
        if (avionOptional.isPresent()){
            Avion avion = avionOptional.get();

            boolean aerolinaExistente = db.getAerolineaRepository().listar()
                    .stream()
                    .anyMatch(ae -> (Objects.equals(ae.getId(), avion.getAerolinea().getId())));

            if (aerolinaExistente) {
                System.out.println("NO SE PUEDE ELIMINAR EL AVION YA QUE TIENE AEROLINEAS RELACIONADOS");
            }else {
                db.getAeropuertoRepository().eliminar(id);
            }
        }else {
            System.out.println("AVION NO ENCONTRADO");
        }

    }
}
