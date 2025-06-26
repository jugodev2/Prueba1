package org.example.proyecto.repositories;

import org.example.proyecto.model.entities.Vuelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VueloRepository implements IRepositories<Vuelo>{

    public final List<Vuelo> vuelos;

    public VueloRepository(){
        this.vuelos = new ArrayList<>();
    }

    @Override
    public List<Vuelo> listar() {
        return List.of();
    }

    @Override
    public void insertar(Vuelo elemento) {
        elemento.setId(Vuelo.getLastId());
        Vuelo.setLastId(Vuelo.getLastId()+1);
        this.vuelos.add(elemento);
    }

    @Override
    public void editor(Vuelo elemento) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public Optional<Vuelo> obtenerPorId(long id) {
        return Optional.empty();
    }


}
