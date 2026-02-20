package sv.edu.udb.estructuralimpia.service.implementation;

import org.springframework.stereotype.Service;
import sv.edu.udb.estructuralimpia.dtos.Sala.SalaRequestDto;
import sv.edu.udb.estructuralimpia.dtos.Sala.SalaResponseDto;
import sv.edu.udb.estructuralimpia.model.Sala;
import sv.edu.udb.estructuralimpia.repository.SalaRepository;
import sv.edu.udb.estructuralimpia.service.SalaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaServiceImpl implements SalaService {

    private final SalaRepository salaRepository;

    public SalaServiceImpl(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public SalaResponseDto crearSala(SalaRequestDto salaDto){
        Sala sala = new Sala();
        sala.setNombre(salaDto.getNombre());
        sala.setCapacidad(salaDto.getCapacidad());
        
        Sala savedSala = salaRepository.save(sala);
        return convertToResponseDto(savedSala);
    }

    public List<SalaResponseDto> listarSalas(){
        List<Sala> salas = salaRepository.findAll();
        return salas.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    public SalaResponseDto buscarPorId(Long id){
        Sala sala = salaRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado esa sala"));
        return convertToResponseDto(sala);
    }

    public void eliminarPorId(Long id) {
        salaRepository.deleteById(id);
    }

    private SalaResponseDto convertToResponseDto(Sala sala) {
        SalaResponseDto dto = new SalaResponseDto();
        dto.setId(sala.getId());
        dto.setNombre(sala.getNombre());
        dto.setCapacidad(sala.getCapacidad());
        return dto;
    }
}
