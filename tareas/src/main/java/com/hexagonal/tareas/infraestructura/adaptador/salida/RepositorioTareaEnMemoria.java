package com.hexagonal.tareas.infraestructura.adaptador.salida;

import com.hexagonal.tareas.dominio.modelo.Tarea;
import com.hexagonal.tareas.dominio.puerto.RepositorioTarea;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RepositorioTareaEnMemoria implements RepositorioTarea {

    private final Map<Long, Tarea> tareas = new HashMap<>();

    @Override
    public void guardar(Tarea tarea) {
        tareas.put(tarea.getId(), tarea);
    }

    @Override
    public List<Tarea> listar() {
        return new ArrayList<>(tareas.values());
    }

    @Override
    public Optional<Tarea> buscarPorId(Long id) {
        return Optional.ofNullable(tareas.get(id));
    }

    @Override
    public void eliminar(Long id) {
        tareas.remove(id);
    }
}