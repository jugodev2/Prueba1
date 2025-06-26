package org.example.proyecto.model.entities;

import org.example.proyecto.model.enums.Estatus;

import java.time.LocalDate;

public class Aerolinea {

    private Long id;
    private String nombre;
    private String iate;
    private Estatus estatus;
    private String pais;
    private LocalDate fechaFundacion;
    //LA COMPARTEN TODAS
    private static Long lastId = 1L;

    public Aerolinea(){

    }

    public Aerolinea(Long id, String nombre, String iate, Estatus estatus, String pais, LocalDate fechaFundacion) {
        this.id = id;
        this.nombre = nombre;
        this.iate = iate;
        this.estatus = estatus;
        this.pais = pais;
        this.fechaFundacion = fechaFundacion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIate() {
        return iate;
    }

    public void setIate(String iate) {
        this.iate = iate;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public LocalDate getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(LocalDate fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public static Long getLastId() {
        return lastId;
    }

    public static void setLastId(Long lastId) {
        Aerolinea.lastId = lastId;
    }

    @Override
    public String toString() {
        return "Aerolinea{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", iate='" + iate + '\'' +
                ", estatus=" + estatus +
                ", pais='" + pais + '\'' +
                ", fechaFundacion=" + fechaFundacion +
                '}';
    }
}
