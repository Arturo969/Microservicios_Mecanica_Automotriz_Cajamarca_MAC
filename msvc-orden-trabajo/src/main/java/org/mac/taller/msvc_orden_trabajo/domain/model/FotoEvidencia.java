package org.mac.taller.msvc_orden_trabajo.domain.model;



import java.time.LocalDateTime;

public class FotoEvidencia {
    private String urlFoto;
    private LocalDateTime fechaCaptura;
    public FotoEvidencia(){
        this.fechaCaptura = LocalDateTime.now();
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public LocalDateTime getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(LocalDateTime fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }
}
