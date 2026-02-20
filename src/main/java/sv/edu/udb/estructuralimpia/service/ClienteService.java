// Paquete que contiene las interfaces de los servicios de la aplicación
package sv.edu.udb.estructuralimpia.service;

// DTOs para transferencia de datos entre capas
import sv.edu.udb.estructuralimpia.dtos.Cliente.ClienteRequestDto;
import sv.edu.udb.estructuralimpia.dtos.Cliente.ClienteResponseDto;
// Entidad de base de datos (referencia para documentación)
import sv.edu.udb.estructuralimpia.model.Cliente;

import java.util.List;

/**
 * Interfaz que define los contratos del servicio de clientes.
 * Todas las implementaciones deben seguir estos contratos.
 * Usar interfaces facilita las pruebas y el cambio de implementaciones.
 */
public interface ClienteService {
    
    /**
     * Registra un nuevo cliente en el sistema.
     * 
     * @param cliente DTO con los datos del cliente a registrar
     * @return DTO del cliente registrado con su ID generado
     */
    ClienteResponseDto registrarCliente(ClienteRequestDto cliente);
    
    /**
     * Obtiene todos los clientes registrados.
     * 
     * @return lista de DTOs con todos los clientes
     */
    List<ClienteResponseDto> listarClientes();
    
    /**
     * Busca un cliente por su ID único.
     * 
     * @param id ID del cliente a buscar
     * @return DTO del cliente encontrado
     * @throws RuntimeException si no se encuentra el cliente
     */
    ClienteResponseDto buscarPorId(Long id);
    
    /**
     * Elimina un cliente por su ID.
     * No devuelve nada, solo elimina el registro.
     * 
     * @param id ID del cliente a eliminar
     */
    void eliminarPorId(Long id);
}
