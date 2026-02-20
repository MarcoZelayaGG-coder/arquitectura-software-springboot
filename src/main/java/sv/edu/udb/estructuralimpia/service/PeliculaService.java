package sv.edu.udb.estructuralimpia.service;

import sv.edu.udb.estructuralimpia.dtos.Pelicula.PeliculaRequestDto;
import sv.edu.udb.estructuralimpia.dtos.Pelicula.PeliculaResponseDto;
import sv.edu.udb.estructuralimpia.model.Pelicula;

import java.util.List;

public interface PeliculaService {
    PeliculaResponseDto crearPelicula(PeliculaRequestDto pelicula);
    List<PeliculaResponseDto> listarPeliculas();
    PeliculaResponseDto buscarPorId(Long id);
    void eliminarPelicula(Long id);
}
