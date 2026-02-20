package sv.edu.udb.estructuralimpia.dtos.Sala;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaResponseDto {
    private Long id;
    private String nombre;
    private int capacidad;
}
