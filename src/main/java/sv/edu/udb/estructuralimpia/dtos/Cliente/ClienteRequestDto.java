// Paquete que contiene los DTOs (Data Transfer Objects) para clientes
package sv.edu.udb.estructuralimpia.dtos.Cliente;

// Lombok: genera automáticamente getters, setters, constructores, toString, etc.
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para recibir datos de creación/actualización de clientes.
 * Se usa cuando el cliente envía datos a la API (POST, PUT).
 */
// @Data: genera getters, setters, toString(), equals(), hashCode()
// @NoArgsConstructor: genera constructor sin parámetros
// @AllArgsConstructor: genera constructor con todos los campos
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDto {
    // ID del cliente (no se usa en creación, se genera automáticamente)
    private Long id;
    
    // Nombre del cliente (requerido)
    private String nombre;
    
    // Correo electrónico del cliente (requerido)
    private String correo;
}
