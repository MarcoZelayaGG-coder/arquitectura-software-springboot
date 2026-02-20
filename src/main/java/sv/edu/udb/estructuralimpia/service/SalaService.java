package sv.edu.udb.estructuralimpia.service;

import sv.edu.udb.estructuralimpia.dtos.Sala.SalaRequestDto;
import sv.edu.udb.estructuralimpia.dtos.Sala.SalaResponseDto;
import sv.edu.udb.estructuralimpia.model.Sala;

import java.util.List;

public interface SalaService {
    SalaResponseDto crearSala(SalaRequestDto sala);
    List<SalaResponseDto> listarSalas();
    SalaResponseDto buscarPorId(Long id);
    void eliminarPorId(Long id);
}
