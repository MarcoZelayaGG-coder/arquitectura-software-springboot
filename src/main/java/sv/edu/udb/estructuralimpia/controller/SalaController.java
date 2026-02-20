package sv.edu.udb.estructuralimpia.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.estructuralimpia.dtos.Sala.SalaRequestDto;
import sv.edu.udb.estructuralimpia.dtos.Sala.SalaResponseDto;
import sv.edu.udb.estructuralimpia.service.SalaService;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class SalaController {

    private final SalaService salaService;

    @GetMapping
    public ResponseEntity<List<SalaResponseDto>> getSalas() {
        List<SalaResponseDto> salas = salaService.listarSalas();
        if (salas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(salas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaResponseDto> getSala(@PathVariable Long id) {
        try {
            SalaResponseDto sala = salaService.buscarPorId(id);
            return ResponseEntity.ok(sala);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SalaResponseDto> agregarSala(@RequestBody SalaRequestDto sala) {
        if (sala.getNombre() == null || sala.getNombre().trim().isEmpty() || sala.getCapacidad() <= 0) {
            return ResponseEntity.badRequest().build();
        }
        SalaResponseDto salaCreada = salaService.crearSala(sala);
        return ResponseEntity.ok(salaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaResponseDto> updateSala(@PathVariable Long id, @RequestBody SalaRequestDto sala) {
        try {
            SalaResponseDto existingSala = salaService.buscarPorId(id);
            // Create a new DTO with updated values
            SalaRequestDto updateRequest = new SalaRequestDto();
            updateRequest.setNombre(sala.getNombre() != null ? sala.getNombre() : existingSala.getNombre());
            updateRequest.setCapacidad(sala.getCapacidad() > 0 ? sala.getCapacidad() : existingSala.getCapacidad());
            
            SalaResponseDto updatedSala = salaService.crearSala(updateRequest);
            return ResponseEntity.ok(updatedSala);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSala(@PathVariable Long id) {
        try {
            salaService.buscarPorId(id);
            salaService.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
