package org.example.proyecto.services;

import org.example.proyecto.DBFake;
import org.example.proyecto.exceptions.FechaAnteriorException;
import org.example.proyecto.model.entities.Aerolinea;
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

public class ServicioAerolinea implements IServices{

    private static final Scanner s = new Scanner(System.in);

    private final DBFake db;

    public ServicioAerolinea(DBFake db) {
        this.db = db;
    }

    @Override
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("------------MENU----------");
            System.out.println("1- Listar AEROLINEA");
            System.out.println("2- INSERTAR AEROLINEA");
            System.out.println("3- EDITAR AEROLINEA");
            System.out.println("4- ELIMINAR AEROLINEA");
            System.out.println("5- VOLVER AL MENU PRINCIPAL");

            while (!s.hasNextInt()){
                s.next();
                System.out.println("INGRESE UNA OPCION VALIDA");
            }
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
        System.out.println("LISTA DE AEROLINEAS: \n");
        List<Aerolinea> aerolineas = db.getAerolineaRepository().listar();
        if (aerolineas.isEmpty()){
            System.out.println("NO HAY AEROLINEAS");
        }else {
            System.out.println(aerolineas.stream().map(a -> "Aerolinea: " +
                    a.getId() + "\n" +
                    "Nombre: " + a.getNombre() + "\n" +
                    "IATA: " + a.getIate() + "\n" +
                    "Pais: " + a.getPais() + "\n" +
                    "Fecha de fundacion: " + a.getFechaFundacion() + "\n" +
                    "Estatus: " + a.getEstatus().getEstatusEnum() + "\n")
                    .collect(Collectors.joining("\n")));
        }
    }

    @Override
    public void insertar() {
        s.nextLine();
        String nombre = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL NOMBRE DE LA AEROLINEA");
        String iate = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA LA IATA DE LA AEROLINEA");
        String pais = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL NOMBRE DEL PAIS");

        LocalDate fechaFundacion = null;

        do {
            System.out.println("AGREGA LA FECHA DE FUNDACION DE LA AEROLINEA (dd/mm/aaaa)");
            String fecha = s.nextLine();

            try {
                fechaFundacion = ValidacionFecha.validarFehaAnterior(fecha);
            }catch (DateTimeParseException e){
                System.out.println("EL FORMATO DE LA FECHA NO ES VALIDO, DEBE SER dd/MM/yyyy");
            }catch (FechaAnteriorException e){
                System.out.println(e.getMessage());
            }
        }while (fechaFundacion == null);

        Estatus estatus = ValidacionEstatus.validacionEstatus(s);

        Aerolinea aerolinea = new Aerolinea(null, nombre, iate, estatus, pais, fechaFundacion);
        db.getAerolineaRepository().insertar(aerolinea);

    }

    @Override
    public void actualizar() {
        listar();
        System.out.println("INGRESA EL ID DE LA AEROLINEA");
        while (!s.hasNextLong()){
            System.out.println("ENTRADA INVALIDA, SOLO SE PERMITEN ENTEROS");
            s.next();
            System.out.println("INGRESA EL ID DE LA AEROLINEA: ");
        }
        long id = s.nextLong();
        Optional<Aerolinea> aerolineaOptional = db.getAerolineaRepository().obtenerPorId(id);
        if (aerolineaOptional.isPresent()) {
            s.nextLine();

            Aerolinea aerolineaDB = aerolineaOptional.get();

            String nombre = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL NOMBRE DE LA AEROLINEA");
            String iate = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA LA IATA DE LA AEROLINEA");
            String pais = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL NOMBRE DEL PAIS");
            aerolineaDB.setNombre(nombre);
            aerolineaDB.setIate(iate);
            aerolineaDB.setPais(pais);
            LocalDate fechaFundacion = null;

            do {
                System.out.println("AGREGA LA FECHA DE FUNDACION DE LA AEROLINEA (dd/mm/aaaa)");
                String fecha = s.nextLine();

                try {
                    fechaFundacion = ValidacionFecha.validarFehaAnterior(fecha);
                }catch (DateTimeParseException e){
                    System.out.println("EL FORMATO DE LA FECHA NO ES VALIDO, DEBE SER dd/MM/yyyy");
                }catch (FechaAnteriorException e){
                    System.out.println(e.getMessage());
                }
            }while (fechaFundacion == null);
            aerolineaDB.setFechaFundacion(fechaFundacion);

            Estatus estatus = ValidacionEstatus.validacionEstatus(s);
            aerolineaDB.setEstatus(estatus);

            db.getAerolineaRepository().editor(aerolineaDB);

        } else {
            System.out.println("ERROR, AEROLINEA NO ENCONTRADA");
        }
    }

    @Override
    public void eliminar() {
        listar();
        System.out.println("INGRESA EL ID DE LA AEROLINEA");
        while (!s.hasNextLong()){
            System.out.println("ENTRADA INVALIDA, SOLO SE PERMITEN ENTEROS");
            s.next();
            System.out.println("INGRESA EL ID DE LA AEROLINEA: ");
        }
        long id = s.nextLong();

        //VALIDAR QUE NO EXISTAN AVIONES DENTRO DE ESA AEROLINEA
        boolean avionesRelacionados = db.getAvionRepository().listar()
                .stream()
                .anyMatch(av -> Objects.equals(av.getAerolinea().getId(), id));

        if (avionesRelacionados) {
            System.out.println("NO SE PUEDE ELIMINAR LA AEROLINEA YA QUE TIENE REGISTROS SELECCIONADOS");
        }else {
            db.getAerolineaRepository().eliminar(id);
            System.out.println("AEROLINEA ELIMINADA EXITOSAMENTE");
        }

    }
}
