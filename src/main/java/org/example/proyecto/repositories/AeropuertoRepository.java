package org.example.proyecto.repositories;

import org.example.proyecto.model.entities.Aerolinea;
import org.example.proyecto.model.entities.Aeropuerto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AeropuertoRepository implements IRepositories<Aeropuerto> {

    public final List<Aeropuerto> aeropuertos;

    public AeropuertoRepository(){
        this.aeropuertos = new ArrayList<>();
    }

    @Override
    public List<Aeropuerto> listar() {
        return this.aeropuertos;
    }

    @Override
    public void insertar(Aeropuerto elemento) {
        elemento.setId(Aeropuerto.getLastId());
        Aeropuerto.setLastId(Aeropuerto.getLastId()+1);
        this.aeropuertos.add(elemento);
    }

    @Override
    public void editor(Aeropuerto elemento) {
        Optional<Aeropuerto> aeropuerto = obtenerPorId(elemento.getId());
        if (aeropuerto.isPresent()){
            Aeropuerto aeropuertoDB = aeropuerto.get();
            aeropuertoDB.setNombre(elemento.getNombre());
            aeropuertoDB.setPais(elemento.getPais());
            aeropuertoDB.setEstatus(elemento.getEstatus());
            aeropuertoDB.setLatitud(elemento.getLatitud());
            aeropuertoDB.setLatitud(elemento.getLongitud());

            int index = this.aeropuertos.indexOf(aeropuertoDB);
            this.aeropuertos.set(index, aeropuertoDB);
            System.out.println("AEROPUERTO ACTUALIZADA CORRECTAMENTE");
        }else {
            System.out.println("ERROR: AEROPUERTO NO ENCONTRADO");
        }
    }

    @Override
    public void eliminar(Long id) {
        Optional<Aeropuerto> aeropuerto = obtenerPorId(id);
        if (aeropuerto.isPresent()){
            this.aeropuertos.remove(aeropuerto.get());
            System.out.println("AEROPUERTO ELIMINADO EXITOSAMENTE");
        }else {
            System.out.println("ERROR: AEROPUERTO NO ENCONTRADA");
        }
    }

    @Override
    public Optional<Aeropuerto> obtenerPorId(long id) {
        for (Aeropuerto aeropuerto : this.aeropuertos){
            if (Objects.equals(aeropuerto.getId(), id)){
                return Optional.of(aeropuerto);
            }
        }
        return Optional.empty();
    }
}
