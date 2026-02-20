package sv.edu.udb.estructuralimpia.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.estructuralimpia.dtos.Pelicula.PeliculaRequestDto;
import sv.edu.udb.estructuralimpia.dtos.Pelicula.PeliculaResponseDto;
import sv.edu.udb.estructuralimpia.service.PeliculaService;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PeliculaController {

    private final PeliculaService peliculaService;

    @GetMapping
    public ResponseEntity<List<PeliculaResponseDto>> getAllPeliculas() {
        List<PeliculaResponseDto> peliculas = peliculaService.listarPeliculas();
        if (peliculas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(peliculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaResponseDto> getPeliculaById(@PathVariable Long id) {
        try {
            PeliculaResponseDto pelicula = peliculaService.buscarPorId(id);
            return ResponseEntity.ok(pelicula);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PeliculaResponseDto> createPelicula(@RequestBody PeliculaRequestDto pelicula) {
        if (pelicula.getNombre() == null || pelicula.getDirector() == null || pelicula.getDuracion() <= 0 || pelicula.getGenero() == null)
            return ResponseEntity.badRequest().build();
        PeliculaResponseDto nuevaPelicula = peliculaService.crearPelicula(pelicula);
        return ResponseEntity.ok(nuevaPelicula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaResponseDto> updatePelicula(@PathVariable Long id, @RequestBody PeliculaRequestDto pelicula) {
        try {
            PeliculaResponseDto existingPelicula = peliculaService.buscarPorId(id);
            // Create a new DTO with updated values
            PeliculaRequestDto updateRequest = new PeliculaRequestDto();
            updateRequest.setNombre(pelicula.getNombre() != null ? pelicula.getNombre() : existingPelicula.getNombre());
            updateRequest.setDuracion(pelicula.getDuracion() > 0 ? pelicula.getDuracion() : existingPelicula.getDuracion());
            updateRequest.setGenero(pelicula.getGenero() != null ? pelicula.getGenero() : existingPelicula.getGenero());
            updateRequest.setDirector(pelicula.getDirector() != null ? pelicula.getDirector() : existingPelicula.getDirector());
            
            PeliculaResponseDto updatedPelicula = peliculaService.crearPelicula(updateRequest);
            return ResponseEntity.ok(updatedPelicula);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelicula(@PathVariable Long id) {
        try {
            peliculaService.buscarPorId(id);
            peliculaService.eliminarPelicula(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
