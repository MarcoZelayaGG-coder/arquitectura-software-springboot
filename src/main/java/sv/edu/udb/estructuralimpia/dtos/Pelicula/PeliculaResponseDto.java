package sv.edu.udb.estructuralimpia.dtos.Pelicula;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaResponseDto {
    private Long id;
    private String nombre;
    private int duracion;
    private String genero;
    private String director;
}
