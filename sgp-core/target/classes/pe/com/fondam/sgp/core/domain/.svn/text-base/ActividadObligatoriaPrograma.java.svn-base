/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "actividad_obligatoria_programa")
public class ActividadObligatoriaPrograma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)   
    @Column(name = "Actividad_Obligatoria_Programa_ID")
    private Integer actividadObligatoriaProgramaID;
    
    @ManyToOne
    @JoinColumn(name = "Tipo_Actividad_Obligatoria_Programa_ID", referencedColumnName = "Tipo_Actividad_Obligatoria_Programa_ID")
    private TipoActividadObligatoriaPrograma tipoActividadObligatoriaPrograma;
    
    @ManyToOne
    @JoinColumn(name = "Programa_ID", referencedColumnName = "Programa_ID")
    private Programa programa;

    public Integer getActividadObligatoriaProgramaID() {
        return actividadObligatoriaProgramaID;
    }

    public void setActividadObligatoriaProgramaID(Integer actividadObligatoriaProgramaID) {
        this.actividadObligatoriaProgramaID = actividadObligatoriaProgramaID;
    }

    public TipoActividadObligatoriaPrograma getTipoActividadObligatoriaPrograma() {
        return tipoActividadObligatoriaPrograma;
    }

    public void setTipoActividadObligatoriaPrograma(TipoActividadObligatoriaPrograma tipoActividadObligatoriaPrograma) {
        this.tipoActividadObligatoriaPrograma = tipoActividadObligatoriaPrograma;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actividadObligatoriaProgramaID != null ? actividadObligatoriaProgramaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadObligatoriaPrograma)) {
            return false;
        }
        ActividadObligatoriaPrograma other = (ActividadObligatoriaPrograma) object;
        if ((this.actividadObligatoriaProgramaID == null && other.actividadObligatoriaProgramaID != null) || (this.actividadObligatoriaProgramaID != null && !this.actividadObligatoriaProgramaID.equals(other.actividadObligatoriaProgramaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.ActividadObligatoriaPrograma[actividadObligatoriaProgramaID=" + actividadObligatoriaProgramaID + "]";
    }

}
