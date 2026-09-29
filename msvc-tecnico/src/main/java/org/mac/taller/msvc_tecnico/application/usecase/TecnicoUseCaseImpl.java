package org.mac.taller.msvc_tecnico.application.usecase;

import org.mac.taller.msvc_tecnico.domain.exception.TecnicoNotFoundException;
import org.mac.taller.msvc_tecnico.domain.model.Tecnico;
import org.mac.taller.msvc_tecnico.domain.ports.inbound.ITecnicoUseCase;
import org.mac.taller.msvc_tecnico.domain.ports.outbound.ITecnicoRepositoryPort;

import java.util.List;
import java.util.Optional;

public class TecnicoUseCaseImpl implements ITecnicoUseCase {

    private final ITecnicoRepositoryPort tecnicoRepositoryPort;

    public TecnicoUseCaseImpl(ITecnicoRepositoryPort tecnicoRepositoryPort) {
        this.tecnicoRepositoryPort = tecnicoRepositoryPort;
    }

    @Override
    public List<Tecnico> listar() {
        return tecnicoRepositoryPort.findAll();
    }

    @Override
    public Optional<Tecnico> porId(Long id) {
        return tecnicoRepositoryPort.findById(id);
    }

    @Override
    public Tecnico guardar(Tecnico tecnico) {
        return tecnicoRepositoryPort.save(tecnico);
    }

    @Override
    public Tecnico editar(Long id, Tecnico tecnico) {
        Tecnico db = tecnicoRepositoryPort.findById(id)
                .orElseThrow(() -> new TecnicoNotFoundException(id));
        db.setDni(tecnico.getDni());
        db.setNombreCompleto(tecnico.getNombreCompleto());
        db.setEstado(tecnico.getEstado());
        db.setTurnoLaboral(tecnico.getTurnoLaboral());
        if (tecnico.getEspecialidades() != null) {
            db.getEspecialidades().clear();
            db.getEspecialidades().addAll(tecnico.getEspecialidades());
        }
        return tecnicoRepositoryPort.save(db);
    }

    @Override
    public Tecnico asignarAOrden(Long id) {
        Tecnico tecnico = tecnicoRepositoryPort.findById(id)
                .orElseThrow(() -> new TecnicoNotFoundException(id));
        tecnico.asignarAOrden();
        return tecnicoRepositoryPort.save(tecnico);
    }

    @Override
    public Tecnico liberar(Long id) {
        Tecnico tecnico = tecnicoRepositoryPort.findById(id)
                .orElseThrow(() -> new TecnicoNotFoundException(id));
        tecnico.liberarTecnico();
        return tecnicoRepositoryPort.save(tecnico);
    }

    @Override
    public void eliminar(Long id) {
        if (!tecnicoRepositoryPort.existsById(id)) {
            throw new TecnicoNotFoundException(id);
        }
        tecnicoRepositoryPort.deleteById(id);
    }
}
