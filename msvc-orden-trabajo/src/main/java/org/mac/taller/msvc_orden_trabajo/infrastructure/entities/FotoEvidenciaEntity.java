package org.mac.taller.msvc_orden_trabajo.infrastructure.entities;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class FotoEvidenciaEntity {
    private String urlFoto;
    private LocalDateTime fechaCaptura;

    public FotoEvidenciaEntity() {
        this.fechaCaptura = LocalDateTime.now();
    }
    
    public String getUrlFoto() { return urlFoto; }
    public void setUrlFoto(String urlFoto) { this.urlFoto = urlFoto; }
    public LocalDateTime getFechaCaptura() { return fechaCaptura; }
    public void setFechaCaptura(LocalDateTime fechaCaptura) { this.fechaCaptura = fechaCaptura; }
}