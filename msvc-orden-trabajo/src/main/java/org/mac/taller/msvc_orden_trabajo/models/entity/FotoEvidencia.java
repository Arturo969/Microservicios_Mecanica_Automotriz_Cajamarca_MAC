package org.mac.taller.msvc_orden_trabajo.models.entity;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class FotoEvidencia {
    private String urlFoto;
    private LocalDateTime fechaCaptura;

    public FotoEvidencia() {
        this.fechaCaptura = LocalDateTime.now();
    }
    
    public String getUrlFoto() { return urlFoto; }
    public void setUrlFoto(String urlFoto) { this.urlFoto = urlFoto; }
    public LocalDateTime getFechaCaptura() { return fechaCaptura; }
    public void setFechaCaptura(LocalDateTime fechaCaptura) { this.fechaCaptura = fechaCaptura; }
}