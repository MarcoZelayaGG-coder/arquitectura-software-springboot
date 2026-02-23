package com.hexagonal.tareas.aplicacion.servicio;

import com.hexagonal.tareas.dominio.modelo.Tarea;
import com.hexagonal.tareas.dominio.puerto.RepositorioTarea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ServicioTareaTest {

    private ServicioTarea servicio;
    private RepositorioFalso repositorioFalso;

    @BeforeEach
    void configurar() {
        repositorioFalso = new RepositorioFalso();
        servicio = new ServicioTarea(repositorioFalso);
    }

    @Test
    void deberiaCrearUnaTarea() {
        servicio.crearTarea(1L, "Estudiar arquitectura hexagonal");

        List<Tarea> tareas = repositorioFalso.listar();

        assertEquals(1, tareas.size());
        assertEquals("Estudiar arquitectura hexagonal", tareas.get(0).getDescripcion());
        assertFalse(tareas.get(0).isCompletada());
    }

    @Test
    void deberiaCompletarUnaTarea() {
        servicio.crearTarea(1L, "Estudiar");
        servicio.completarTarea(1L);

        Tarea tarea = repositorioFalso.buscarPorId(1L).get();

        assertTrue(tarea.isCompletada());
    }

    @Test
    void deberiaEliminarUnaTarea() {
        servicio.crearTarea(1L, "Eliminar esta tarea");
        servicio.eliminarTarea(1L);

        assertEquals(0, repositorioFalso.listar().size());
    }

}

class RepositorioFalso implements RepositorioTarea {

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