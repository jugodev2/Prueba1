package org.example.proyecto.repositories;

import org.example.proyecto.model.entities.Avion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AvionRepository implements IRepositories<Avion>{

    public final List<Avion> avioness;

    public AvionRepository(){
        this.avioness = new ArrayList<>();
    }

    @Override
    public List<Avion> listar() {
        return List.of();
    }

    @Override
    public void insertar(Avion elemento) {

    }

    @Override
    public void editor(Avion elemento) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public Optional<Avion> obtenerPorId(long id) {
        return Optional.empty();
    }
}
