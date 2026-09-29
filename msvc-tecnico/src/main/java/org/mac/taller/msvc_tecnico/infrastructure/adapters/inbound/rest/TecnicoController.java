package org.mac.taller.msvc_tecnico.infrastructure.adapters.inbound.rest;

import org.mac.taller.msvc_tecnico.domain.exception.TecnicoNotFoundException;
import org.mac.taller.msvc_tecnico.domain.model.Tecnico;
import org.mac.taller.msvc_tecnico.domain.ports.inbound.ITecnicoUseCase;
import org.mac.taller.msvc_tecnico.infrastructure.adapters.inbound.rest.dto.TecnicoRequestDTO;
import org.mac.taller.msvc_tecnico.infrastructure.adapters.inbound.rest.dto.TecnicoResponseDTO;
import org.mac.taller.msvc_tecnico.infrastructure.adapters.inbound.rest.mapper.TecnicoRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tecnico")
public class TecnicoController {

    private final ITecnicoUseCase tecnicoUseCase;
    private final TecnicoRestMapper mapper;

    public TecnicoController(ITecnicoUseCase tecnicoUseCase, TecnicoRestMapper mapper) {
        this.tecnicoUseCase = tecnicoUseCase;
        this.mapper = mapper;
    }

    @GetMapping
    public List<TecnicoResponseDTO> listar() {
        return tecnicoUseCase.listar()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<TecnicoResponseDTO> crear(@RequestBody TecnicoRequestDTO dto) {
        Tecnico tecnico = mapper.toDomain(dto);
        Tecnico guardado = tecnicoUseCase.guardar(tecnico);
        return ResponseEntity.ok(mapper.toResponseDTO(guardado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoResponseDTO> detalle(@PathVariable Long id) {
        Optional<Tecnico> o = tecnicoUseCase.porId(id);
        return o.map(tecnico -> ResponseEntity.ok(mapper.toResponseDTO(tecnico)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/asignar")
    public ResponseEntity<?> asignarAOrden(@PathVariable Long id) {
        try {
            Tecnico asignado = tecnicoUseCase.asignarAOrden(id);
            return ResponseEntity.ok(mapper.toResponseDTO(asignado));
        } catch (TecnicoNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/liberar")
    public ResponseEntity<?> liberar(@PathVariable Long id) {
        try {
            Tecnico liberado = tecnicoUseCase.liberar(id);
            return ResponseEntity.ok(mapper.toResponseDTO(liberado));
        } catch (TecnicoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody TecnicoRequestDTO dto, @PathVariable Long id) {
        try {
            Tecnico tecnico = mapper.toDomain(dto);
            Tecnico editado = tecnicoUseCase.editar(id, tecnico);
            return ResponseEntity.ok(mapper.toResponseDTO(editado));
        } catch (TecnicoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            tecnicoUseCase.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (TecnicoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
