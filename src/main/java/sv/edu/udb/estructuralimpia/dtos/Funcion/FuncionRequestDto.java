package sv.edu.udb.estructuralimpia.dtos.Funcion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionRequestDto {
    private LocalDateTime fechaHora;
    private double precio;
    private Long salaId;
    private Long peliculaId;
}
