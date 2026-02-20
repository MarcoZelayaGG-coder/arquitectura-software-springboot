package sv.edu.udb.estructuralimpia.service.implementation;

import org.springframework.stereotype.Service;
import sv.edu.udb.estructuralimpia.dtos.Pelicula.PeliculaRequestDto;
import sv.edu.udb.estructuralimpia.dtos.Pelicula.PeliculaResponseDto;
import sv.edu.udb.estructuralimpia.model.Pelicula;
import sv.edu.udb.estructuralimpia.repository.PeliculaRepository;
import sv.edu.udb.estructuralimpia.service.PeliculaService;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public PeliculaServiceImpl(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public PeliculaResponseDto crearPelicula(PeliculaRequestDto peliculaDto){
        Pelicula pelicula = new Pelicula();
        pelicula.setNombre(peliculaDto.getNombre());
        pelicula.setDuracion(peliculaDto.getDuracion());
        pelicula.setGenero(peliculaDto.getGenero());
        pelicula.setDirector(peliculaDto.getDirector());
        
        Pelicula savedPelicula = peliculaRepository.save(pelicula);
        return convertToResponseDto(savedPelicula);
    }

    public List<PeliculaResponseDto> listarPeliculas(){
        List<Pelicula> peliculas = peliculaRepository.findAll();
        return peliculas.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    public PeliculaResponseDto buscarPorId(Long id){
        Pelicula pelicula = peliculaRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado esa pelicula"));
        return convertToResponseDto(pelicula);
    }

    public void eliminarPelicula(Long id){
        peliculaRepository.deleteById(id);
    }

    private PeliculaResponseDto convertToResponseDto(Pelicula pelicula) {
        PeliculaResponseDto dto = new PeliculaResponseDto();
        dto.setId(pelicula.getId());
        dto.setNombre(pelicula.getNombre());
        dto.setDuracion(pelicula.getDuracion());
        dto.setGenero(pelicula.getGenero());
        dto.setDirector(pelicula.getDirector());
        return dto;
    }
}
