package org.example.jueves.empresa;

public class Ventas extends EmpleadoBase{
    public Ventas(String nombre,double salario) {
        super(nombre, Departamento.VENTAS, salario);
    }

    @Override
    public void mostrarDatos(){
        System.out.printf("El vendedor: %s trabaja en el departamento de %s y su salario %s \n",
                this.getNombre(),this.getDepartamento(),this.getSalario());
    }
}
