package org.example.proyecto.exceptions;

public class FechaAnteriorException extends RuntimeException {
    public FechaAnteriorException(String message) {
        //MANDA A PADRE Y SOLO RECIVE EL MENSAJE QUE LE DAMOS
        super(message);
    }
}
