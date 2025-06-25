package org.example.jueves.empresa;

public class RecursosHumanos extends EmpleadoBase{
    public RecursosHumanos(String nombre,double salario) {
        super(nombre, Departamento.RECURSOS_HUMANOS, salario);
    }

    @Override
    public void mostrarDatos(){
        System.out.printf("La Lic: %s trabaja en el departamento de %s y su salario %s \n",
                this.getNombre(),this.getDepartamento(),this.getSalario());
    }
}
