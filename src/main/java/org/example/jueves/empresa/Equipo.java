package org.example.jueves.empresa;

import java.util.ArrayList;
import java.util.List;

public  class Equipo<T extends EmpleadoBase> {

    private final List<T> empleados;


    public Equipo() {
        this.empleados = new ArrayList<>();
    }

    public void agregarEmpleado(T empleado) {
        empleados.add(empleado);
    }
    public void mostrarEmpleados(){
        if (this.empleados.isEmpty()){
            System.out.println("Empleado vacio");
        }else {
            for(Empleado empleado : this.empleados){
                System.out.printf("La Lic: %s trabaja en el departamento de %s y su salario %s \n",
                        empleado.getNombre(), empleado.getDepartamento(), empleado.getSalario());
            }
            System.out.println("Se encuentra en el mismo equipo ");
        }


    }

}

