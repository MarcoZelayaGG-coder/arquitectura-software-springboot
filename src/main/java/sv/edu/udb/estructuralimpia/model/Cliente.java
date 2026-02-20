// Paquete que contiene las entidades de la base de datos
package sv.edu.udb.estructuralimpia.model;

// JPA: anotación para marcar esta clase como una tabla de base de datos
import jakarta.persistence.Entity;
// JPA: anotación para generar el ID automáticamente
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
// JPA: anotación para marcar el campo como clave primaria
import jakarta.persistence.Id;
// JPA: relación uno a muchos (un cliente tiene muchos tickets)
import jakarta.persistence.OneToMany;

// Lombok: genera automáticamente getters, setters, constructores, etc.
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Entidad que representa un cliente en el sistema.
 * Esta clase se mapea a la tabla "clientes" en la base de datos.
 * Contiene la información básica de un cliente y sus tickets asociados.
 */
// @Entity: indica a JPA que esta clase representa una tabla en la BD
// @Data: genera getters, setters, toString(), equals(), hashCode()
// @NoArgsConstructor: genera constructor sin parámetros (requerido por JPA)
// @AllArgsConstructor: genera constructor con todos los parámetros (útil para pruebas)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    
    // @Id: marca este campo como clave primaria de la tabla
    // @GeneratedValue: configura cómo se genera el ID automáticamente
    // GenerationType.IDENTITY: la base de datos genera el ID (auto-incremental)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Nombre del cliente (máximo 100 caracteres)
    private String nombre;
    
    // Correo electrónico del cliente (debe ser único)
    private String correo;
    
    // @OneToMany: define relación uno-a-muchos con Ticket
    // mappedBy = "cliente": indica que el campo "cliente" en Ticket gestiona esta relación
    // cascade = CascadeType.ALL: operaciones (guardar, eliminar) se propagan a los tickets
    // fetch = FetchType.LAZY: los tickets se cargan solo cuando se acceden (mejor rendimiento)
    @OneToMany(mappedBy = "cliente")
    private List<Ticket> tickets;
}
