package sv.edu.udb.estructuralimpia.service.implementation;

import org.springframework.stereotype.Service;
import sv.edu.udb.estructuralimpia.dtos.Ticket.TicketRequestDto;
import sv.edu.udb.estructuralimpia.dtos.Ticket.TicketResponseDto;
import sv.edu.udb.estructuralimpia.model.Ticket;
import sv.edu.udb.estructuralimpia.repository.ClienteRepository;
import sv.edu.udb.estructuralimpia.repository.FuncionRepository;
import sv.edu.udb.estructuralimpia.repository.TicketRepository;
import sv.edu.udb.estructuralimpia.service.TicketService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionRepository funcionRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, ClienteRepository clienteRepository, FuncionRepository funcionRepository) {
        this.ticketRepository = ticketRepository;
        this.clienteRepository = clienteRepository;
        this.funcionRepository = funcionRepository;
    }

    public TicketResponseDto crearReserva(TicketRequestDto request) throws Exception {
        if (clienteRepository.findById(request.getClienteId()).isEmpty()) 
            throw new Exception("Ticket no creado: Cliente no encontrado");
        if (funcionRepository.findById(request.getFuncionId()).isEmpty()) 
            throw new Exception("Ticket no creado: Funcion no encontrada");
        
        double precio = funcionRepository.findById(request.getFuncionId()).get().getPrecio();
        double pago = request.getCantidadEntradas() * precio;
        
        Ticket ticket = new Ticket();
        ticket.setCantidadEntradas(request.getCantidadEntradas());
        ticket.setPago(pago);
        ticket.setCliente(clienteRepository.findById(request.getClienteId()).get());
        ticket.setFuncion(funcionRepository.findById(request.getFuncionId()).get());
        
        Ticket savedTicket = ticketRepository.save(ticket);
        return convertToResponseDto(savedTicket);
    }

    public List<TicketResponseDto> listarReservasPorCliente(Long clienteId){
        List<Ticket> tickets = ticketRepository.findTicketsByClienteId(clienteId);
        return tickets.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    public Optional<TicketResponseDto> encontrarPorId(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        return ticket.map(this::convertToResponseDto);
    }

    public void eliminarPorId(Long id) {
        ticketRepository.deleteById(id);
    }

    private TicketResponseDto convertToResponseDto(Ticket ticket) {
        TicketResponseDto dto = new TicketResponseDto();
        dto.setId(ticket.getId());
        dto.setCantidadEntradas(ticket.getCantidadEntradas());
        dto.setPago(ticket.getPago());
        dto.setClienteId(ticket.getCliente().getId());
        dto.setFuncionId(ticket.getFuncion().getId());
        return dto;
    }
}
