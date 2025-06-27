package org.example.proyecto.utils;

import org.example.proyecto.exceptions.FechaAnteriorException;
import org.example.proyecto.exceptions.FechaPosteriorExcepcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidacionFecha {

    public static LocalDate parseFecha(String fecha) throws DateTimeParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fecha, format);
    }

    public static LocalDate validarFehaAnterior(String fecha) throws FechaAnteriorException {
        LocalDate fechaFormateada = parseFecha(fecha);
        LocalDate date = LocalDate.now();

        if (fechaFormateada.isBefore(LocalDate.now())) {
            return fechaFormateada;
        } else {
            throw new FechaAnteriorException("LA FECHA DEBE DE SER ANTERIOR A LA FECHA ACTUAL");
        }
    }

    public static LocalDate validarFehaPosterior(String fecha) throws FechaAnteriorException {
        LocalDate fechaFormateada = parseFecha(fecha);
        LocalDate date = LocalDate.now();

        if (fechaFormateada.isAfter(LocalDate.now())){
            return fechaFormateada;
        }else {
            throw new FechaPosteriorExcepcion("LA FECHA DEBE DE SER POSTERIOR A LA FECHA ACTUAL");
        }

    }
}
