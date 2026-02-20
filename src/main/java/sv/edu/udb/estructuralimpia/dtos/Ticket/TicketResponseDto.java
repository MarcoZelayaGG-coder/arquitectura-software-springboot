package sv.edu.udb.estructuralimpia.dtos.Ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDto {
    private Long id;
    private int cantidadEntradas;
    private double pago;
    private Long clienteId;
    private Long funcionId;
}
