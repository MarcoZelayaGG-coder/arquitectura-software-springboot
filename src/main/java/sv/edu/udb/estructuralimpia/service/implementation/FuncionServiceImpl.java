package sv.edu.udb.estructuralimpia.service.implementation;

import org.springframework.stereotype.Service;
import sv.edu.udb.estructuralimpia.dtos.Funcion.FuncionRequestDto;
import sv.edu.udb.estructuralimpia.dtos.Funcion.FuncionResponseDto;
import sv.edu.udb.estructuralimpia.model.Funcion;
import sv.edu.udb.estructuralimpia.model.Pelicula;
import sv.edu.udb.estructuralimpia.model.Sala;
import sv.edu.udb.estructuralimpia.repository.FuncionRepository;
import sv.edu.udb.estructuralimpia.repository.PeliculaRepository;
import sv.edu.udb.estructuralimpia.repository.SalaRepository;
import sv.edu.udb.estructuralimpia.service.FuncionService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionServiceImpl implements FuncionService {

    private final FuncionRepository funcionRepository;
    private final PeliculaRepository peliculaRepository;
    private final SalaRepository salaRepository;

    public FuncionServiceImpl(FuncionRepository funcionRepository, PeliculaRepository peliculaRepository, SalaRepository salaRepository) {
        this.funcionRepository = funcionRepository;
        this.peliculaRepository = peliculaRepository;
        this.salaRepository = salaRepository;
    }

    public FuncionResponseDto crearFuncion(FuncionRequestDto funcionDto) {
        Funcion funcion = new Funcion();
        funcion.setFechaHora(funcionDto.getFechaHora());
        funcion.setPrecio(funcionDto.getPrecio());
        
        Pelicula pelicula = peliculaRepository.findById(funcionDto.getPeliculaId())
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));
        Sala sala = salaRepository.findById(funcionDto.getSalaId())
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));
        
        funcion.setPelicula(pelicula);
        funcion.setSala(sala);
        
        Funcion savedFuncion = funcionRepository.save(funcion);
        return convertToResponseDto(savedFuncion);
    }

    public List<FuncionResponseDto> buscarFunciones() {
        List<Funcion> funciones = funcionRepository.findAll();
        return funciones.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    public List<FuncionResponseDto> listarFuncionesPorPelicula(Long peliculaId) {
        List<Funcion> funciones = funcionRepository.findFuncionByPeliculaId(peliculaId);
        return funciones.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    public Optional<FuncionResponseDto> encontrarPorId(Long id) {
        Optional<Funcion> funcion = funcionRepository.findById(id);
        return funcion.map(this::convertToResponseDto);
    }

    public void eliminarPorId(Long id) {
        funcionRepository.deleteById(id);
    }

    private FuncionResponseDto convertToResponseDto(Funcion funcion) {
        FuncionResponseDto dto = new FuncionResponseDto();
        dto.setId(funcion.getId());
        dto.setFechaHora(funcion.getFechaHora());
        dto.setPrecio(funcion.getPrecio());
        dto.setSalaId(funcion.getSala().getId());
        dto.setPeliculaId(funcion.getPelicula().getId());
        return dto;
    }
}
