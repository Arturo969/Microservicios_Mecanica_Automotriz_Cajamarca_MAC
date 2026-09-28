package org.mac.taller.msvc_bahia.infrastructure.adapters;

import org.mac.taller.msvc_bahia.domain.model.BahiaTrabajo;
import org.mac.taller.msvc_bahia.infrastructure.entities.BahiaTrabajoEntity;
import org.springframework.stereotype.Component;

@Component
public class BahiaMapper {
    // DE ENTITY (JPA) HACIA DOMINIO (PURO)
    public BahiaTrabajo toDomain(BahiaTrabajoEntity entity) {
        if (entity == null) return null;
        BahiaTrabajo dominio = new BahiaTrabajo();
        dominio.setId(entity.getId());
        dominio.setCodigo(entity.getCodigo());
        dominio.setEstado(entity.getEstado());
        dominio.setTipo(entity.getTipo());
        return dominio;
    }

    // DE DOMINIO HACIA ENTITY (JPA)
    public BahiaTrabajoEntity toEntity(BahiaTrabajo dominio) {
        if (dominio == null) return null;
        BahiaTrabajoEntity entity = new BahiaTrabajoEntity();
        entity.setId(dominio.getId());
        entity.setCodigo(dominio.getCodigo());
        entity.setEstado(dominio.getEstado());
        entity.setTipo(dominio.getTipo());
        return entity;
    }
}