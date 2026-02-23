package com.hexagonal.tareas.infraestructura.adaptador.entrada;

import com.hexagonal.tareas.aplicacion.servicio.ServicioTarea;
import com.hexagonal.tareas.dominio.puerto.RepositorioTarea;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tareas")
public class ControladorTarea {

    private final ServicioTarea servicio;

    public ControladorTarea(RepositorioTarea repositorio) {
        this.servicio = new ServicioTarea(repositorio);
    }

    @PostMapping
    public void crear(@RequestParam Long id,
                      @RequestParam String descripcion) {
        servicio.crearTarea(id, descripcion);
    }

    @GetMapping
    public Object listar() {
        return servicio.listarTareas();
    }

    @PutMapping("/{id}")
    public void completar(@PathVariable Long id) {
        servicio.completarTarea(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminarTarea(id);
    }
}
