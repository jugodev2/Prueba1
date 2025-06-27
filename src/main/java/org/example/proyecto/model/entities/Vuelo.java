package org.example.proyecto.model.entities;

import org.example.proyecto.model.enums.Estatus;

import java.time.LocalDate;

public class Vuelo {
    private Long id;
    private String codigoVuelo;
    private Avion avion;
    private Aeropuerto origen;
    private Aeropuerto destino;
    private Estatus estatus;
    private LocalDate fechaSalida;
    //LA COMPARTEN TODAS
    private static Long lastId = 1L;

    public Vuelo(){

    }
    public Vuelo(Long id, String codigoVuelo, Avion avion, Aeropuerto origen, Aeropuerto destino, Estatus estatus, LocalDate fechaSalida) {
        this.id = id;
        this.codigoVuelo = codigoVuelo;
        this.avion = avion;
        this.origen = origen;
        this.destino = destino;
        this.estatus = estatus;
        this.fechaSalida = fechaSalida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Aeropuerto getOrigen() {
        return origen;
    }

    public void setOrigen(Aeropuerto origen) {
        this.origen = origen;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public void setDestino(Aeropuerto destino) {
        this.destino = destino;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public static Long getLastId() {
        return lastId;
    }

    public static void setLastId(Long lastId) {
        Vuelo.lastId = lastId;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "id=" + id +
                ", codigoVuelo='" + codigoVuelo + '\'' +
                ", avion=" + avion +
                ", origen=" + origen +
                ", destino=" + destino +
                ", estatus=" + estatus +
                ", fechaSalida=" + fechaSalida +
                '}';
    }
}
