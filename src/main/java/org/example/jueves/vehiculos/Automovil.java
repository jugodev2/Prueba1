package org.example.jueves.vehiculos;

public class Automovil extends VehiculoBase{

    public Automovil(String marca, String modelo, int anio) {
        super(marca, modelo, anio, TipoVehiculo.AUTOMOVIL);
    }

    @Override
    public void mostrarDetalles(){
        System.out.println(TipoVehiculo.AUTOMOVIL.getNombreVehiculo() + " marca " + marca +
                " modelo "+ modelo + " anio " + anio);
    }

    @Override
    public void acelerar() {
        System.out.println("Metodo sobrescrito "+tipo.getNombreVehiculo() + " esta Acelerando.........");
    }

    @Override
    public void frenar() {
        System.out.println(tipo.getNombreVehiculo() + "esta frenando");
    }

    @Override
    public void arrancar() {
        System.out.println(tipo.getNombreVehiculo() + "esta arrancando");

    }

    @Override
    public void apagar() {
        System.out.println(tipo.getNombreVehiculo() + "esta apagando");

    }

    @Override
    public void encender() {
        System.out.println(tipo.getNombreVehiculo() + "esta encendiendo");

    }

}
