package org.example.jueves.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private String nombre;
    private final List<Libro> libros;

    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.libros = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void agregarLibro(Libro libro){
        this.libros.add(libro);

    }

    public void mostrarLibros(){
        if (this.libros.isEmpty()){
            System.out.println("No hay ibros disponibles");
        }
        else {
            this.libros.forEach(lib -> System.out.println(lib.toString()));
        }
    }
}
