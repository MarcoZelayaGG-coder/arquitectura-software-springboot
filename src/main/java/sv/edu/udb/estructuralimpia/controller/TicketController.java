package sv.edu.udb.estructuralimpia.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.estructuralimpia.dtos.Ticket.TicketRequestDto;
import sv.edu.udb.estructuralimpia.dtos.Ticket.TicketResponseDto;
import sv.edu.udb.estructuralimpia.service.TicketService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketResponseDto>> getAllTickets() {
        List<TicketResponseDto> tickets = ticketService.listarReservasPorCliente(null);
        if (tickets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<TicketResponseDto>> getTicketsPorCliente(@PathVariable Long id) {
        try {
            List<TicketResponseDto> ticketsCliente = ticketService.listarReservasPorCliente(id);
            if (ticketsCliente.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(ticketsCliente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDto> getTicketPorId(@PathVariable Long id) {
        try {
            Optional<TicketResponseDto> ticketOpt = ticketService.encontrarPorId(id);
            if (ticketOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(ticketOpt.get());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TicketResponseDto> crearReserva(@RequestBody TicketRequestDto request) {
        try {
            if (request.getClienteId() == null || request.getFuncionId() == null || request.getCantidadEntradas() <= 0) {
                return ResponseEntity.badRequest().build();
            }
            TicketResponseDto ticketNuevo = ticketService.crearReserva(request);
            return ResponseEntity.ok(ticketNuevo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTicket(@PathVariable Long id) {
        try {
            Optional<TicketResponseDto> ticketOpt = ticketService.encontrarPorId(id);
            if (ticketOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            ticketService.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
