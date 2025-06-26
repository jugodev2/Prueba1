package org.example.proyecto.repositories;

import org.example.proyecto.model.entities.Aerolinea;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AerolineaRepository implements IRepositories<Aerolinea> {

    public final List<Aerolinea> aerolineas;

    public AerolineaRepository(){
        this.aerolineas = new ArrayList<>();
    }

    @Override
    public List<Aerolinea> listar() {
        return this.aerolineas;
    }

    @Override
    public void insertar(Aerolinea elemento) {
        elemento.setId(Aerolinea.getLastId());
        Aerolinea.setLastId(Aerolinea.getLastId()+1);
        this.aerolineas.add(elemento);
    }

    @Override
    public void editor(Aerolinea elemento) {
        Optional<Aerolinea> aerolinea = obtenerPorId(elemento.getId());
        if (aerolinea.isPresent()){
            Aerolinea aerolineaDB = aerolinea.get();
            aerolineaDB.setNombre(elemento.getNombre());
            aerolineaDB.setEstatus(elemento.getEstatus());
            aerolineaDB.setIate(elemento.getIate());
            aerolineaDB.setFechaFundacion(elemento.getFechaFundacion());
            aerolineaDB.setPais(elemento.getPais());
            int index = this.aerolineas.indexOf(aerolineaDB);
            this.aerolineas.set(index, aerolineaDB);
            System.out.println("AEROLINA ACTUALIZADA CORRECTAMENTE");
        }else {
            System.out.println("ERROR: AEROLINA NO ENCONTRADA");
        }
    }

    @Override
    public void eliminar(Long id) {

        Optional<Aerolinea> aerolinea = obtenerPorId(id);
        if (aerolinea.isPresent()){
            this.aerolineas.remove(aerolinea.get());
            System.out.println("AEROLINA ENCONTRADA EXITOSAMENTE");
        }else {
            System.out.println("ERROR: AEROLINA NO ENCONTRADA");
        }
    }

    @Override
    public Optional<Aerolinea> obtenerPorId(long id) {
        for (Aerolinea aerolinea : this.aerolineas){
            if (Objects.equals(aerolinea.getId(), id)){
                return Optional.of(aerolinea);
            }
        }
        return Optional.empty();
    }
}
