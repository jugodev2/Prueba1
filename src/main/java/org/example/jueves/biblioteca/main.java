package org.example.jueves.biblioteca;

public class main {

    public static void main(String[] args) {
        Autor autor = new Autor("Gabriel Garia Marquez", "Colombiano");
        Autor autor2 = new Autor("Isebelle Allende", "Chilena");

        Libro libro = new Libro("Cien años de soledad", "978-3-16-148410",1967,autor);
        Libro libro2 = new Libro("La casa de los Espiritus", "978-1-86197-5",1967,autor2);

        Biblioteca biblioteca = new Biblioteca("Biblioteca Mexicana");
        biblioteca.mostrarLibros();

        biblioteca.agregarLibro(libro);
        biblioteca.agregarLibro(libro2);


        biblioteca.mostrarLibros();


    }

}
