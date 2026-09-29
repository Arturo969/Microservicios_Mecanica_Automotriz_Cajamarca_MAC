package org.mac.taller.msvc_tecnico.domain.exception;

public class TecnicoNotFoundException extends RuntimeException {
    public TecnicoNotFoundException(String message) {
        super(message);
    }

    public TecnicoNotFoundException(Long id) {
        super("Técnico no encontrado con id: " + id);
    }
}
