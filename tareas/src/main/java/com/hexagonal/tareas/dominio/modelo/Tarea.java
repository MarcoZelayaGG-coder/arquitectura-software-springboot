package com.hexagonal.tareas.dominio.modelo;

public class Tarea {
    private Long id;
    private String descripcion;
    private boolean completada;

    public Tarea(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.completada = false;
    }

    public void completar() {
        this.completada = true;
    }

    public Long getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public boolean isCompletada() { return completada; }
}