package org.mac.taller.msvc_calidad.models.entity;
import jakarta.persistence.Embeddable;

@Embeddable
public class CheckSeguridad {
    private String criterio;
    private boolean aprobado;
    public boolean isAprobado(){ return aprobado;}

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }
}
