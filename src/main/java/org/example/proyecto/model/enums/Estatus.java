package org.example.proyecto.model.enums;

public enum Estatus {
    DISPONIBLE,
    NO_DISPONIBLE;


    public String getEstatusEnum() {
        switch (this){
            case DISPONIBLE -> {return "Disponible";}
            case NO_DISPONIBLE -> {return "No Disponible";}
            default -> { return "Error";}
        }
    }
}
