package org.mac.taller.msvc_tecnico.infrastructure.controllers.mapper;

import org.mac.taller.msvc_tecnico.domain.model.Tecnico;
import org.mac.taller.msvc_tecnico.domain.model.TurnoHorario;
import org.mac.taller.msvc_tecnico.infrastructure.controllers.dto.TecnicoRequestDTO;
import org.mac.taller.msvc_tecnico.infrastructure.controllers.dto.TecnicoResponseDTO;
import org.mac.taller.msvc_tecnico.infrastructure.controllers.dto.TurnoHorarioDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TecnicoRestMapper {

    public Tecnico toDomain(TecnicoRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Tecnico(
                null,
                dto.getDni(),
                dto.getNombreCompleto(),
                dto.getEspecialidades() != null ? new ArrayList<>(dto.getEspecialidades()) : new ArrayList<>(),
                dto.getEstado(),
                toTurnoHorarioDomain(dto.getTurnoLaboral())
        );
    }

    public TecnicoResponseDTO toResponseDTO(Tecnico domain) {
        if (domain == null) {
            return null;
        }
        return new TecnicoResponseDTO(
                domain.getId(),
                domain.getDni(),
                domain.getNombreCompleto(),
                domain.getEspecialidades() != null ? new ArrayList<>(domain.getEspecialidades()) : new ArrayList<>(),
                domain.getEstado(),
                toTurnoHorarioDTO(domain.getTurnoLaboral())
        );
    }

    public TurnoHorario toTurnoHorarioDomain(TurnoHorarioDTO dto) {
        if (dto == null) {
            return null;
        }
        return new TurnoHorario(dto.getHoraInicio(), dto.getHoraFin());
    }

    public TurnoHorarioDTO toTurnoHorarioDTO(TurnoHorario domain) {
        if (domain == null) {
            return null;
        }
        return new TurnoHorarioDTO(domain.getHoraInicio(), domain.getHoraFin());
    }
}
