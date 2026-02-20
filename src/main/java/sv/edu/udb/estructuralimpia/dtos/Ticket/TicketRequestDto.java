package sv.edu.udb.estructuralimpia.dtos.Ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequestDto {
    private Long clienteId;
    private Long funcionId;
    private int cantidadEntradas;
}
