// Paquete que contiene los DTOs (Data Transfer Objects) para clientes
package sv.edu.udb.estructuralimpia.dtos.Cliente;

// Lombok: genera automáticamente getters, setters, constructores, toString, etc.
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para enviar datos de clientes al cliente de la API.
 * Se usa cuando la API responde al cliente (GET responses).
 * Contiene solo los datos necesarios y seguros para mostrar.
 */
// @Data: genera getters, setters, toString(), equals(), hashCode()
// @NoArgsConstructor: genera constructor sin parámetros
// @AllArgsConstructor: genera constructor con todos los campos
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDto {
    // ID único del cliente (generado por la base de datos)
    private Long id;
    
    // Nombre del cliente
    private String nombre;
    
    // Correo electrónico del cliente
    private String correo;
    
    // Lista de IDs de tickets del cliente (evita ciclos infinitos)
    // En lugar de guardar los objetos Ticket completos, guardamos solo los IDs
    // Esto previene errores de serialización circular
    private List<Long> ticketIds;
}
