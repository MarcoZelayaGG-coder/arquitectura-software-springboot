package sv.edu.udb.estructuralimpia.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.estructuralimpia.dtos.Funcion.FuncionRequestDto;
import sv.edu.udb.estructuralimpia.dtos.Funcion.FuncionResponseDto;
import sv.edu.udb.estructuralimpia.service.FuncionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/funciones")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FuncionController {

    private final FuncionService funcionService;

    @GetMapping
    public ResponseEntity<List<FuncionResponseDto>> getAllFunciones() {
        List<FuncionResponseDto> funciones = funcionService.buscarFunciones();
        if (funciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(funciones);
    }

    @GetMapping("/pelicula/{id}")
    public ResponseEntity<List<FuncionResponseDto>> getFuncionesPorPelicula(@PathVariable Long id) {
        List<FuncionResponseDto> funciones = funcionService.listarFuncionesPorPelicula(id);
        if (funciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(funciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionResponseDto> getFuncionPorId(@PathVariable Long id) {
        try {
            Optional<FuncionResponseDto> funcionOpt = funcionService.encontrarPorId(id);
            if (funcionOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(funcionOpt.get());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FuncionResponseDto> createFuncion(@RequestBody FuncionRequestDto funcion) {
        if (funcion.getPrecio() <= 0 || funcion.getSalaId() == null || funcion.getFechaHora() == null || funcion.getPeliculaId() == null) {
            return ResponseEntity.badRequest().build();
        }
        FuncionResponseDto nuevaFuncion = funcionService.crearFuncion(funcion);
        return ResponseEntity.ok(nuevaFuncion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionResponseDto> updateFuncion(@PathVariable Long id, @RequestBody FuncionRequestDto funcion) {
        try {
            Optional<FuncionResponseDto> funcionOpt = funcionService.encontrarPorId(id);
            if (funcionOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            FuncionResponseDto existingFuncion = funcionOpt.get();
            // Create a new DTO with updated values
            FuncionRequestDto updateRequest = new FuncionRequestDto();
            updateRequest.setFechaHora(funcion.getFechaHora() != null ? funcion.getFechaHora() : existingFuncion.getFechaHora());
            updateRequest.setPrecio(funcion.getPrecio() > 0 ? funcion.getPrecio() : existingFuncion.getPrecio());
            updateRequest.setSalaId(funcion.getSalaId() != null ? funcion.getSalaId() : existingFuncion.getSalaId());
            updateRequest.setPeliculaId(funcion.getPeliculaId() != null ? funcion.getPeliculaId() : existingFuncion.getPeliculaId());
            
            FuncionResponseDto updatedFuncion = funcionService.crearFuncion(updateRequest);
            return ResponseEntity.ok(updatedFuncion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncion(@PathVariable Long id) {
        try {
            Optional<FuncionResponseDto> funcionOpt = funcionService.encontrarPorId(id);
            if (funcionOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            funcionService.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
