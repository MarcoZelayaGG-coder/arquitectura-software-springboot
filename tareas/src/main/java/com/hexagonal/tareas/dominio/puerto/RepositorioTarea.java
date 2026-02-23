package com.hexagonal.tareas.dominio.puerto;

import com.hexagonal.tareas.dominio.modelo.Tarea;
import java.util.List;
import java.util.Optional;

public interface RepositorioTarea {

    void guardar(Tarea tarea);

    List<Tarea> listar();

    Optional<Tarea> buscarPorId(Long id);

    void eliminar(Long id);
}