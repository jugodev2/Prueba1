package org.example.jueves.empresa;

import java.util.List;

public class Marketing extends EmpleadoBase{
    public Marketing(String nombre,double salario) {
        super(nombre, Departamento.MARKETING, salario);
    }

    @Override
    public void mostrarDatos(){
        System.out.printf("El Diseñador: %s trabaja en el departamento de %s y su salario %s \n",
                this.getNombre(),this.getDepartamento(),this.getSalario());
    }

}
