package org.example.jueves.empresa;

public abstract class EmpleadoBase implements Empleado {

    protected String nombre;
    protected double salario;
    protected Departamento departamento;

    public EmpleadoBase(String nombre, Departamento departamento, double salario) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
    }

    protected EmpleadoBase() {
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public double getSalario() {
        return this.salario;
    }

    @Override
    public Departamento getDepartamento() {
        return this.departamento;
    }




    public abstract void mostrarDatos();
}
