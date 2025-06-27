package org.example.proyecto.repositories;

import org.example.proyecto.model.entities.Avion;
import org.example.proyecto.model.entities.Vuelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class VueloRepository implements IRepositories<Vuelo>{

    public final List<Vuelo> vuelos;

    public VueloRepository(){
        this.vuelos = new ArrayList<>();
    }

    @Override
    public List<Vuelo> listar() {
        return vuelos;
    }

    @Override
    public void insertar(Vuelo elemento) {
        elemento.setId(Vuelo.getLastId());
        Vuelo.setLastId(Vuelo.getLastId()+1);
        this.vuelos.add(elemento);
    }

    @Override
    public void editor(Vuelo elemento) {

        Optional<Vuelo> vueloOptional = obtenerPorId(elemento.getId());
        if (vueloOptional.isPresent()){
            Vuelo vuelo = vueloOptional.get();
            vuelo.setCodigoVuelo(elemento.getCodigoVuelo());
            vuelo.setAvion(elemento.getAvion());
            vuelo.setDestino(elemento.getDestino());
            vuelo.setOrigen(elemento.getOrigen());
            vuelo.setEstatus(elemento.getEstatus());
            vuelo.setFechaSalida(elemento.getFechaSalida());

            int index = this.vuelos.indexOf(vuelo);
            this.vuelos.set(index, vuelo);
            System.out.println("VUELOS ACTUALIZADA CORRECTAMENTE");
        }else {
            System.out.println("ERROR: VUELO NO ENCONTRADO");
        }

    }

    @Override
    public void eliminar(Long id) {
        Optional<Vuelo> vueloOptional = obtenerPorId(id);
        if (vueloOptional.isPresent()){
            this.vuelos.remove(vueloOptional.get());
            System.out.println("VUELO ELIMINADO EXITOSAMENTE");
        }else {
            System.out.println("ERROR: VUELO NO ENCONTRADO");
        }
    }

    @Override
    public Optional<Vuelo> obtenerPorId(long id) {
        for (Vuelo vuelo : this.vuelos){
            if (Objects.equals(vuelo.getId(), id)){
                return Optional.of(vuelo);
            }
        }
        return Optional.empty();
    }
}
