package org.example.proyecto.services;

import org.example.proyecto.DBFake;
import org.example.proyecto.exceptions.FechaAnteriorException;
import org.example.proyecto.model.entities.Aerolinea;
import org.example.proyecto.model.entities.Avion;
import org.example.proyecto.model.enums.Estatus;
import org.example.proyecto.utils.ValidacionEstatus;
import org.example.proyecto.utils.ValidacionFecha;
import org.example.proyecto.utils.ValidacionesCadenas;

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
            System.out.println("1- Listar AVIONES");
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
                            "AeroLinea: " + a.getAerolinea().getNombre() + "\n" +
                            "Estatus: " + a.getEstatus().getEstatusEnum() + "\n")
                    .collect(Collectors.joining("\n")));
        }

    }

    @Override
    public void insertar() {
        ServicioAerolinea servicioAerolinea = new ServicioAerolinea(this.db);
        servicioAerolinea.listar();

        s.nextLine();
        System.out.println("INGRESA EL NUMERO DE REGISTRO DE VUELO");
        Long numRegistro = s.nextLong();
        s.nextLine();

        String tipo = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL TIPO");

        String codModelo = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL CODIGO DE MODELO");

        System.out.println("INGRESA LA CAPACIDAD");
        int capacidad = s.nextInt();

        //VALIDACION DE LA CAPACIDAD
        while (capacidad <= 0) {
            System.out.println("La capacidad debe ser un número positivo.");
            capacidad = s.nextInt();
        }
        s.nextLine();
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

        System.out.println("SELECCIONA EL ID DE LA AEROLINEA");
        long aeroID = s.nextLong();
        Optional<Aerolinea> aerolineaOptional = this.db.getAerolineaRepository().obtenerPorId(aeroID);
        Aerolinea aerolinea = new Aerolinea();
        if (aerolineaOptional.isPresent()){
            System.out.println("Entro alOptional");
            aerolinea = aerolineaOptional.get();

            Avion avionSave = new Avion(null, numRegistro,
                    tipo, codModelo, capacidad,
                    fechaPrimerVuelo, estatus, aerolinea);
            db.getAvionRepository().insertar(avionSave);
        }else {
            System.out.println("No se puede INSERTAR, la aerolinea no EXISTE");
        }

    }

    @Override
    public void actualizar() {
        Avion avionDB = new Avion();

        System.out.println("INGRESA EL NUMERO DE REGISTRO DE VUELO");
        Long numRegistro = s.nextLong();
        s.nextLine();
        avionDB.setNumeroRegistro(numRegistro);
        String tipo = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL TIPO");
        avionDB.setTipo(tipo);
        String codModelo = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL CODIGO DE MODELO");
        avionDB.setCodigoModelo(codModelo);
        System.out.println("INGRESA LA CAPACIDAD");
        int capacidad = s.nextInt();

        while (capacidad <= 0) {
            System.out.println("La capacidad debe ser un número positivo.");
            capacidad = s.nextInt();
        }
        avionDB.setCapacidad(capacidad);
        s.nextLine();
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
            avionDB.setFechaPrimerVuelo(fechaPrimerVuelo);
            Estatus estatus = ValidacionEstatus.validacionEstatus(s);
            avionDB.setEstatus(estatus);

            System.out.println("SELECCIONA EL ID DE LA AEROLINEA");
            long aeroID = s.nextLong();
            Optional<Aerolinea> aerolineaOptional = this.db.getAerolineaRepository().obtenerPorId(aeroID);
            Aerolinea aerolinea = new Aerolinea();
            if (aerolineaOptional.isPresent()){
                System.out.println("Entro alOptional");
                aerolinea = aerolineaOptional.get();
                avionDB.setAerolinea(aerolinea);

                db.getAvionRepository().editor(avionDB);
            }else {
                System.out.println("ERROR, AEROLINEA NO ENCONTRADA");
            }

    }

    @Override
    public void eliminar() {
        listar();
        System.out.println("INGRESA EL ID DEL AVION");
        while (!s.hasNextLong()){
            System.out.println("ENTRADA INVALIDA, SOLO SE PERMITEN ENTEROS");
            s.next();
            System.out.println("INGRESA EL ID DE LA AEROLINEA: ");
        }
        long id = s.nextLong();

        boolean vuelosExistentes = db.getVueloRepository().listar()
                .stream()
                .anyMatch(vl -> (Objects.equals(vl.getDestino().getId(), id)
                        || Objects.equals(vl.getOrigen().getId(), id)));

        if (vuelosExistentes) {
            System.out.println("NO SE PUEDE ELIMINAR EL AVION YA QUE TIENE AEROLINEAS RELACIONADOS");
        }else {
            db.getAeropuertoRepository().eliminar(id);
            System.out.println("AVION ELIMINADA EXITOSAMENTE");
        }
    }
}
