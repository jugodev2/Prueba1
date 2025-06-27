package org.example.proyecto.services;

import org.example.proyecto.DBFake;
import org.example.proyecto.model.entities.Aeropuerto;
import org.example.proyecto.model.enums.Estatus;
import org.example.proyecto.utils.ValidacionEstatus;
import org.example.proyecto.utils.ValidacionesCadenas;
import org.example.proyecto.utils.ValidacionesNumericas;
;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ServicioAeropuerto implements IServices {

    private static final Scanner s = new Scanner(System.in);

    private final DBFake db;

    public ServicioAeropuerto(DBFake db) {
        this.db = db;
    }


    @Override
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("------------MENU----------");
            System.out.println("1- Listar AEROPUERTO");
            System.out.println("2- INSERTAR AEROPUERTO");
            System.out.println("3- EDITAR AEROPUERTO");
            System.out.println("4- ELIMINAR AEROPUERTO");
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
        System.out.println("LISTA DE AEROPUERTOS: \n");
        List<Aeropuerto> aeropuertos = db.getAeropuertoRepository().listar();
        if (aeropuertos.isEmpty()){
            System.out.println("NO HAY AEROPUERTOS");
        }else {
            System.out.println(aeropuertos.stream().map(a -> "Aeropuerto: " +
                            a.getId() + "\n" +
                            "Nombre: " + a.getNombre() + "\n" +
                            "Latitud: " + a.getLatitud() + "\n" +
                            "Longitud: " + a.getLongitud() + "\n" +
                            "Pais: " + a.getPais() + "\n" +
                            "Estatus: " + a.getEstatus().getEstatusEnum() + "\n")
                    .collect(Collectors.joining("\n")));
        }
    }

    @Override
    public void insertar() {
        s.nextLine();
        String nombre = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL NOMBRE DEL AEROPUERTO");
        String latitud = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA LA LATITUD");
        String longitud = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA LA LONGITUD");
        String pais = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL NOMBRE DEL PAIS");

        Estatus estatus = ValidacionEstatus.validacionEstatus(s);

        Aeropuerto aeropuerto = new Aeropuerto(null, nombre, latitud, longitud, pais, estatus);
        db.getAeropuertoRepository().insertar(aeropuerto);

    }

    @Override
    public void actualizar() {
        //System.out.println("INGRESA EL ID DEL : ");
//        while (!s.hasNextLong()){
//            System.out.println("ENTRADA INVALIDA, SOLO SE PERMITEN ENTEROS");
//            s.next();
//            System.out.println("INGRESA EL ID DEL AEROPUERTO: ");
//        }
        //long id = s.nextLong();
        //listar();
        System.out.println("INGRESA EL ID DE AEROPUERTO");
        long id = ValidacionesNumericas.validarLongPosi(s);
        Optional<Aeropuerto> aeropuertoOptional = db.getAeropuertoRepository().obtenerPorId(id);
        if (aeropuertoOptional.isPresent()) {
            s.nextLine();
        Aeropuerto aeropuertoDB = aeropuertoOptional.get();
        String nombre = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL NOMBRE DEL AEROPUERTO");
        String latitud = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA LA LATITUD");
        String longitud = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA LA LONGITUD");
        String pais = ValidacionesCadenas.validarCadenaVacia(s, "INGRESA EL NOMBRE DEL PAIS");
        Estatus estatus = ValidacionEstatus.validacionEstatus(s);

        aeropuertoDB.setNombre(nombre);
        aeropuertoDB.setLatitud(latitud);
        aeropuertoDB.setLongitud(longitud);
        aeropuertoDB.setPais(pais);
        aeropuertoDB.setEstatus(estatus);

        db.getAeropuertoRepository().editor(aeropuertoDB);

    } else {
        System.out.println("ERROR, AEROLINEA NO ENCONTRADA");
        }
    }

    @Override
    public void eliminar() {
        listar();
        System.out.println("INGRESA EL ID DE AEROPUERTO");
        long id = ValidacionesNumericas.validarLongPosi(s);

            boolean vuelosVinlados = db.getVueloRepository().listar()
                    .stream()
                    .anyMatch(al -> (Objects.equals(al.getId(), id)));
            if (vuelosVinlados) {
                System.out.println("NO SE PUEDE ELIMINAR EL AEROPERTO YA QUE TIENE VUELOS ASIGNADOS");
            }else {
                db.getAeropuertoRepository().eliminar(id);
            }
    }
}
