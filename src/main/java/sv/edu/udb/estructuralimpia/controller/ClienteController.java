package sv.edu.udb.estructuralimpia.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.estructuralimpia.dtos.Cliente.ClienteRequestDto;
import sv.edu.udb.estructuralimpia.dtos.Cliente.ClienteResponseDto;
import sv.edu.udb.estructuralimpia.service.ClienteService;

import java.util.List;

/**
 * Controlador REST para gestionar clientes.
 * Define los endpoints HTTP para operaciones CRUD sobre clientes.
 */
// @RestController: indica que esta clase es un controlador REST
// @RequestMapping: define la URL base para todos los endpoints: /api/clientes
// @AllArgsConstructor: genera automáticamente el constructor con todos los campos
// @CrossOrigin: permite peticiones desde cualquier origen (útil para desarrollo frontend)
@RestController
@RequestMapping("/api/clientes")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ClienteController {

    // Inyección de dependencias: Spring inyecta automáticamente el servicio
    private final ClienteService clienteService;

    /**
     * Endpoint para obtener todos los clientes.
     * GET /api/clientes
     */
    // @GetMapping: mapea peticiones HTTP GET a este método
    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> getAllClientes(){
        List<ClienteResponseDto> listaClientes = clienteService.listarClientes();
        if (listaClientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaClientes);
    }

    /**
     * Endpoint para obtener un cliente por su ID.
     * GET /api/clientes/{id}
     */
    // @PathVariable: extrae el ID de la URL
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> getClienteById(@PathVariable Long id){
        try {
            ClienteResponseDto cliente = clienteService.buscarPorId(id);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para crear un nuevo cliente.
     * POST /api/clientes
     */
    // @RequestBody: convierte el JSON del cuerpo de la petición a un objeto
    @PostMapping
    public ResponseEntity<ClienteResponseDto> createCliente(@RequestBody ClienteRequestDto cliente){
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty() || cliente.getCorreo() == null || cliente.getCorreo().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        ClienteResponseDto nuevoCliente = clienteService.registrarCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    /**
     * Endpoint para actualizar un cliente existente.
     * PUT /api/clientes/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> updateCliente(@PathVariable Long id, @RequestBody ClienteRequestDto cliente){
        try {
            ClienteResponseDto existingCliente = clienteService.buscarPorId(id);
            // Crea un DTO con los valores actualizados
            // Si el campo viene null, mantiene el valor existente
            ClienteRequestDto updateRequest = new ClienteRequestDto();
            updateRequest.setNombre(cliente.getNombre() != null ? cliente.getNombre() : existingCliente.getNombre());
            updateRequest.setCorreo(cliente.getCorreo() != null ? cliente.getCorreo() : existingCliente.getCorreo());
            
            ClienteResponseDto updatedCliente = clienteService.registrarCliente(updateRequest);
            return ResponseEntity.ok(updatedCliente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para eliminar un cliente.
     * DELETE /api/clientes/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id){
        try {
            // Verifica que el cliente existe antes de eliminar
            clienteService.buscarPorId(id);
            // Elimina el cliente
            clienteService.eliminarPorId(id);
            // Retorna 204 No Content (eliminación exitosa sin contenido)
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // Si el cliente no existe, retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para obtener los tickets de un cliente.
     * GET /api/clientes/{id}/tickets
     */
    @GetMapping("/{id}/tickets")
    public ResponseEntity<?> getClientTickets(@PathVariable Long id){
        try {
            // Busca el cliente
            ClienteResponseDto cliente = clienteService.buscarPorId(id);
            // Verifica si tiene tickets
            if (cliente.getTicketIds() == null || cliente.getTicketIds().isEmpty()) {
                // Si no tiene tickets, retorna un mensaje
                return ResponseEntity.ok("No hay tickets para este usuario");
            }
            // Si tiene tickets, retorna la lista de IDs
            return ResponseEntity.ok(cliente.getTicketIds());
        } catch (RuntimeException e) {
            // Si el cliente no existe, retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}
