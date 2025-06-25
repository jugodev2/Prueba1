package org.example.jueves.empresa;

public enum Departamento {
    DESARROLLO,
    RECURSOS_HUMANOS,
    VENTAS,
    MARKETING;

    public String getNombreDepartamento(){
        switch (this){
            case DESARROLLO -> {return "Departamento de Desarrollo";}
            case RECURSOS_HUMANOS -> {return "Departamento de Recursos Humanos";}
            case VENTAS -> {return "Departamento de Ventas";}
            case MARKETING -> {return "Departamento de Markting";}
            default -> {return "No valido";}
        }

    }
}
