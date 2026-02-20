// Paquete que contiene las implementaciones de los servicios de la aplicación
package sv.edu.udb.estructuralimpia.service.implementation;

// Spring: anotación para marcar esta clase como un servicio de Spring
import org.springframework.stereotype.Service;
// DTOs para transferencia de datos
import sv.edu.udb.estructuralimpia.dtos.Cliente.ClienteRequestDto;
import sv.edu.udb.estructuralimpia.dtos.Cliente.ClienteResponseDto;
// Entidad de base de datos
import sv.edu.udb.estructuralimpia.model.Cliente;
// Repositorio para acceder a la base de datos
import sv.edu.udb.estructuralimpia.repository.ClienteRepository;
// Interfaz del servicio que estamos implementando
import sv.edu.udb.estructuralimpia.service.ClienteService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de clientes.
 * Contiene la lógica de negocio para gestionar clientes.
 * Esta clase es un "bean" de Spring, lo que significa que Spring la gestiona.
 */
// @Service: indica a Spring que esta clase es un componente de servicio
// Spring la creará automáticamente y la inyectará donde se necesite
@Service
public class ClienteServiceImp implements ClienteService {

    // Repositorio para acceder a los datos de clientes en la base de datos
    // Spring inyectará automáticamente esta dependencia
    private final ClienteRepository clienteRepository;

    /**
     * Constructor para inyectar dependencias.
     * Spring llama a este constructor automáticamente para crear el servicio.
     * 
     * @param clienteRepository el repositorio para acceder a datos de clientes
     */
    public ClienteServiceImp(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Registra un nuevo cliente en el sistema.
     * Convierte el DTO a entidad, lo guarda y devuelve el resultado como DTO.
     * 
     * @param clienteDto datos del cliente a registrar
     * @return DTO del cliente registrado con su ID
     */
    public ClienteResponseDto registrarCliente(ClienteRequestDto clienteDto){
        // Crear una nueva entidad Cliente desde el DTO
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDto.getNombre());
        cliente.setCorreo(clienteDto.getCorreo());
        
        // Guardar el cliente en la base de datos
        // JPA genera automáticamente el ID
        Cliente savedCliente = clienteRepository.save(cliente);
        
        // Convertir la entidad guardada a DTO para devolverla
        return convertToResponseDto(savedCliente);
    }

    /**
     * Obtiene todos los clientes registrados en el sistema.
     * 
     * @return lista de DTOs de todos los clientes
     */
    public List<ClienteResponseDto> listarClientes(){
        // Obtener todas las entidades de la base de datos
        List<Cliente> clientes = clienteRepository.findAll();
        
        // Convertir cada entidad a DTO usando programación funcional
        // stream(): convierte la lista a un flujo de elementos
        // map(): aplica la función convertToResponseDto a cada elemento
        // collect(): convierte el flujo de vuelta a una lista
        return clientes.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    /**
     * Busca un cliente por su ID.
     * Si no lo encuentra, lanza una excepción.
     * 
     * @param id ID del cliente a buscar
     * @return DTO del cliente encontrado
     * @throws RuntimeException si no se encuentra el cliente
     */
    public ClienteResponseDto buscarPorId(Long id){
        // Buscar el cliente en la base de datos
        // orElseThrow(): si no encuentra nada, lanza la excepción
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        
        // Convertir la entidad a DTO
        return convertToResponseDto(cliente);
    }

    /**
     * Elimina un cliente por su ID.
     * No devuelve nada, solo elimina el registro.
     * 
     * @param id ID del cliente a eliminar
     */
    public void eliminarPorId(Long id) {
        // Eliminar directamente por ID
        // Si no existe, JPA lanzará una excepción que será capturada en el controlador
        clienteRepository.deleteById(id);
    }

    /**
     * Convierte una entidad Cliente a un ClienteResponseDto.
     * Este método es privado porque solo se usa internamente en esta clase.
     * 
     * @param cliente entidad a convertir
     * @return DTO con los datos del cliente
     */
    private ClienteResponseDto convertToResponseDto(Cliente cliente) {
        // Crear un nuevo DTO
        ClienteResponseDto dto = new ClienteResponseDto();
        
        // Copiar los datos básicos
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setCorreo(cliente.getCorreo());
        
        // Convertir la lista de tickets a lista de IDs para evitar ciclos infinitos
        // Esto es importante para prevenir errores de serialización JSON
        List<Long> ticketIds = cliente.getTickets() != null ? 
            cliente.getTickets().stream()                    // Convertir lista a flujo
                .map(ticket -> ticket.getId())                 // Extraer solo los IDs
                .collect(Collectors.toList()) :                // Convertir de vuelta a lista
            null;                                              // Si no hay tickets, dejar null
        
        dto.setTicketIds(ticketIds);
        return dto;
    }
}
