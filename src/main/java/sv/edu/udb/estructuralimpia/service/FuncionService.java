package sv.edu.udb.estructuralimpia.service;

import sv.edu.udb.estructuralimpia.dtos.Funcion.FuncionRequestDto;
import sv.edu.udb.estructuralimpia.dtos.Funcion.FuncionResponseDto;
import sv.edu.udb.estructuralimpia.model.Funcion;

import java.util.List;
import java.util.Optional;

public interface FuncionService {
    FuncionResponseDto crearFuncion(FuncionRequestDto funcion);
    List<FuncionResponseDto> buscarFunciones();
    List<FuncionResponseDto> listarFuncionesPorPelicula(Long peliculaId);
    Optional<FuncionResponseDto> encontrarPorId(Long id);
    void eliminarPorId(Long id);
}
