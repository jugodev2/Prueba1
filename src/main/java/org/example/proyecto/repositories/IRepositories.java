package org.example.proyecto.repositories;

import java.util.List;
import java.util.Optional;

public interface IRepositories<T> {

    List<T> listar();
    void insertar(T elemento);
    void editor(T elemento);
    void eliminar(Long id);
    Optional<T> obtenerPorId(long id);


}
