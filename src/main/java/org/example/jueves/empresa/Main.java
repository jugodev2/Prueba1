package org.example.jueves.empresa;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Marketing marketing = new Marketing("Josefina Lagos Moreno", 15000.50);
        Marketing marketing1 = new Marketing("Laura Santiago Laguirre", 12000.50);
        Marketing marketing2 = new Marketing("Norma Martinez Santi", 16000.50);

        Ventas venta = new Ventas("Ruben Huerta Perez", 10000);
        Ventas venta1 = new Ventas("Lola Huerta Hernandez", 10000);
        Ventas venta2 = new Ventas("Sofia Gutierrez Ramirez", 10000);

        Desarrollador dev = new Desarrollador("Ricardo Sanchez", 78000);
        Desarrollador dev1 = new Desarrollador("Martin Vargas Yosa", 35000);
        Desarrollador dev2 = new Desarrollador("Jose de Jesus ", 19000);

        RecursosHumanos rh = new RecursosHumanos("Carlos Medina Santi", 9000);
        RecursosHumanos rh1 = new RecursosHumanos("Julia Lopez Olvera", 12000);
        RecursosHumanos rh2 = new RecursosHumanos("Lulu Olmos Oliveira", 15000);

        Equipo<EmpleadoBase> equipo = new Equipo<>();

        equipo.agregarEmpleado(dev);
        equipo.agregarEmpleado(rh);
        equipo.agregarEmpleado(venta);
        equipo.agregarEmpleado(marketing);
        equipo.mostrarEmpleados();


    }
}
