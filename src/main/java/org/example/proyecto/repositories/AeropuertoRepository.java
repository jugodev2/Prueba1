package org.example.proyecto.repositories;

import org.example.proyecto.model.entities.Aeropuerto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AeropuertoRepository implements IRepositories<Aeropuerto> {

    public final List<Aeropuerto> aeropuertos;

    public AeropuertoRepository(){
        this.aeropuertos = new ArrayList<>();
    }


    @Override
    public List<Aeropuerto> listar() {
        return List.of();
    }

    @Override
    public void insertar(Aeropuerto elemento) {

    }

    @Override
    public void editor(Aeropuerto elemento) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public Optional<Aeropuerto> obtenerPorId(long id) {
        return Optional.empty();
    }
}
