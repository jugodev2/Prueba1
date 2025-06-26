package org.example.proyecto;

import org.example.proyecto.repositories.AerolineaRepository;
import org.example.proyecto.repositories.AeropuertoRepository;
import org.example.proyecto.repositories.AvionRepository;
import org.example.proyecto.repositories.VueloRepository;

public class DBFake {
    private final AerolineaRepository aerolineaRepository;
    private final AeropuertoRepository aeropuertoRepository;
    private final AvionRepository avionRepository;
    private final VueloRepository vueloRepository;

    public DBFake(){
        this.aerolineaRepository = new AerolineaRepository();
        this.aeropuertoRepository = new AeropuertoRepository();
        this.avionRepository = new AvionRepository();
        this.vueloRepository = new VueloRepository();
    }

    public AerolineaRepository getAerolineaRepository(){
        return aerolineaRepository;
    }

    public AeropuertoRepository getAeropuertoRepository() {
        return aeropuertoRepository;
    }

    public AvionRepository getAvionRepository() {
        return avionRepository;
    }

    public VueloRepository getVueloRepository() {
        return vueloRepository;
    }
}
