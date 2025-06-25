package org.example.jueves.vehiculos;

public abstract class VehiculoBase implements Vehiculo{

    protected String marca;
    protected String modelo;
    protected int anio;
    protected TipoVehiculo tipo;


    public VehiculoBase(String marca, String modelo, int anio, TipoVehiculo tipo) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.tipo = tipo;
    }

    @Override
    public void encender() {
        System.out.println(this.tipo.getNombreVehiculo() + " Esta encendido");
    }

    @Override
    public void apagar() {
        System.out.println(this.tipo.getNombreVehiculo() + " Esta apagado");

    }

    @Override
    public void arrancar() {
        System.out.println(this.tipo.getNombreVehiculo() + " Esta arrancando");

    }

    @Override
    public void frenar() {
        System.out.println(this.tipo.getNombreVehiculo() + " Esta frenando");

    }

    @Override
    public void acelerar() {
        System.out.println("Metodo Sobreescrito" + this.tipo.getNombreVehiculo() + " Esta acelerando");
    }

    public abstract void mostrarDetalles();



}
