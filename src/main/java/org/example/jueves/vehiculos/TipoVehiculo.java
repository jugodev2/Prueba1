package org.example.jueves.vehiculos;

public enum TipoVehiculo {

    CAMION,
    MOTOCICLETA,
    AUTOMOVIL;


    public String getNombreVehiculo(){
        switch (this){
            case AUTOMOVIL -> {return "Automovil";}
            case CAMION -> {return "Camion";}
            case MOTOCICLETA -> {return "Motocicleta";}
            default -> {return "No valido";}
        }
    }
}

