package com.hexagonal.tareas.aplicacion.servicio;

import com.hexagonal.tareas.dominio.modelo.Tarea;
import com.hexagonal.tareas.dominio.puerto.RepositorioTarea;

import java.util.List;

public class ServicioTarea {

    private final RepositorioTarea repositorio;

    public ServicioTarea(RepositorioTarea repositorio) {
        this.repositorio = repositorio;
    }

    public void crearTarea(Long id, String descripcion) {
        Tarea tarea = new Tarea(id, descripcion);
        repositorio.guardar(tarea);
    }

    public List<Tarea> listarTareas() {
        return repositorio.listar();
    }

    public void completarTarea(Long id) {
        repositorio.buscarPorId(id).ifPresent(Tarea::completar);
    }

    public void eliminarTarea(Long id) {
        repositorio.eliminar(id);
    }
}
