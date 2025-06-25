package org.example.jueves.empresa;

public class Desarrollador extends EmpleadoBase{

    public Desarrollador(String nombre,double salario) {
        super(nombre, Departamento.DESARROLLO, salario);
    }

    @Override
    public void mostrarDatos(){
        System.out.printf("Desarrollador: %s trabaja en el departamento de %s y su salario %s \n",
                this.getNombre(),this.getDepartamento(),this.getSalario());
    }



}
