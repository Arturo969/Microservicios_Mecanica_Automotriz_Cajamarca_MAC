package org.mac.taller.msvc_tecnico.infrastructure.adapters.outbound.persistence.mapper;

import org.mac.taller.msvc_tecnico.domain.model.Tecnico;
import org.mac.taller.msvc_tecnico.domain.model.TurnoHorario;
import org.mac.taller.msvc_tecnico.infrastructure.adapters.outbound.persistence.entity.TecnicoEntity;
import org.mac.taller.msvc_tecnico.infrastructure.adapters.outbound.persistence.entity.TurnoHorarioEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TecnicoPersistenceMapper {

    public Tecnico toDomain(TecnicoEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Tecnico(
                entity.getId(),
                entity.getDni(),
                entity.getNombreCompleto(),
                entity.getEspecialidades() != null ? new ArrayList<>(entity.getEspecialidades()) : new ArrayList<>(),
                entity.getEstado(),
                toTurnoHorarioDomain(entity.getTurnoLaboral())
        );
    }

    public TecnicoEntity toEntity(Tecnico domain) {
        if (domain == null) {
            return null;
        }
        return new TecnicoEntity(
                domain.getId(),
                domain.getDni(),
                domain.getNombreCompleto(),
                domain.getEspecialidades() != null ? new ArrayList<>(domain.getEspecialidades()) : new ArrayList<>(),
                domain.getEstado(),
                toTurnoHorarioEntity(domain.getTurnoLaboral())
        );
    }

    public TurnoHorario toTurnoHorarioDomain(TurnoHorarioEntity entity) {
        if (entity == null) {
            return null;
        }
        return new TurnoHorario(entity.getHoraInicio(), entity.getHoraFin());
    }

    public TurnoHorarioEntity toTurnoHorarioEntity(TurnoHorario domain) {
        if (domain == null) {
            return null;
        }
        return new TurnoHorarioEntity(domain.getHoraInicio(), domain.getHoraFin());
    }
}
