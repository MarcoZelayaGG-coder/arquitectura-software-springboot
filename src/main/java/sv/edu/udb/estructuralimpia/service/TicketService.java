package sv.edu.udb.estructuralimpia.service;

import sv.edu.udb.estructuralimpia.dtos.Ticket.TicketRequestDto;
import sv.edu.udb.estructuralimpia.dtos.Ticket.TicketResponseDto;
import sv.edu.udb.estructuralimpia.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    TicketResponseDto crearReserva(TicketRequestDto request) throws Exception;
    List<TicketResponseDto> listarReservasPorCliente(Long clienteId);
    Optional<TicketResponseDto> encontrarPorId(Long id);
    void eliminarPorId(Long id);
}
