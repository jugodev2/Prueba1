package org.example.proyecto.repositories;

import org.example.proyecto.model.entities.Aerolinea;
import org.example.proyecto.model.entities.Aeropuerto;
import org.example.proyecto.model.entities.Avion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AvionRepository implements IRepositories<Avion>{

    public final List<Avion> avioness;

    public AvionRepository(){
        this.avioness = new ArrayList<>();
    }

    @Override
    public List<Avion> listar() {
        return this.avioness;
    }

    @Override
    public void insertar(Avion elemento) {
        elemento.setId(Avion.getLastId());
        Avion.setLastId(Aeropuerto.getLastId()+1);
        this.avioness.add(elemento);
    }

    @Override
    public void editor(Avion elemento) {
        Optional<Avion> avionOpt = obtenerPorId(elemento.getId());
        if (avionOpt.isPresent()){
            Avion avion = avionOpt.get();
            avion.setNumeroRegistro(elemento.getNumeroRegistro());
            avion.setTipo(elemento.getTipo());
            avion.setCapacidad(elemento.getCapacidad());
            avion.setCodigoModelo(elemento.getCodigoModelo());
            avion.setEstatus(elemento.getEstatus());
            avion.setAerolinea(elemento.getAerolinea());

            int index = this.avioness.indexOf(avion);
            this.avioness.set(index, avion);
            System.out.println("AVION ACTUALIZADA CORRECTAMENTE");
        }else {
            System.out.println("ERROR: AVION NO ENCONTRADO");
        }
    }

    @Override
    public void eliminar(Long id) {
        Optional<Avion> avionOpt = obtenerPorId(id);
        if (avionOpt.isPresent()){
            this.avioness.remove(avionOpt.get());
            System.out.println("AVION ELIMINADO EXITOSAMENTE");
        }else {
            System.out.println("ERROR: AVION NO ENCONTRADA");
        }
    }

    @Override
    public Optional<Avion> obtenerPorId(long id) {
        for (Avion avion : this.avioness){
            if (Objects.equals(avion.getId(), id)){
                return Optional.of(avion);
            }
        }
        return Optional.empty();
    }
}
