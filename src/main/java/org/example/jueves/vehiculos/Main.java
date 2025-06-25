package org.example.jueves.vehiculos;

public class Main {
    public static void main(String[] args) {
        Automovil automovil = new Automovil("Toyota","Corolla", 2022);
        Motocicleta motocicleta = new Motocicleta("Inmortalika","FT-150", 2018);
        Camion camion = new Camion("Mercedez","Futura", 1998);

        automovil.encender();
        automovil.acelerar();
        automovil.arrancar();
        automovil.frenar();
        automovil.apagar();
        automovil.mostrarDetalles();

        System.out.println();
        motocicleta.encender();
        motocicleta.acelerar();
        motocicleta.arrancar();
        motocicleta.frenar();
        motocicleta.apagar();
        motocicleta.mostrarDetalles();

        System.out.println();
        camion.encender();
        camion.acelerar();
        camion.arrancar();
        camion.frenar();
        camion.apagar();
        camion.mostrarDetalles();
        System.out.println();


    }
}
