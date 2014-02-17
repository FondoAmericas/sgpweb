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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "observacion_proy")

public class ObservacionProy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "Observacion_Proy_ID")
    private Integer observacionProyID;
    
    @Column(name = "tipo_observacion")
    private String tipoObservacion;
    
    @Lob
    @Column(name = "observacion")
    private String observacion;
    
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    @ManyToOne
    private DatoProyecto datoProyecto;

    public ObservacionProy() {
    }

    public ObservacionProy(Integer observacionProyID) {
        this.observacionProyID = observacionProyID;
    }

    public ObservacionProy(Integer observacionProyID, String tipoObservacion, String observacion) {
        this.observacionProyID = observacionProyID;
        this.tipoObservacion = tipoObservacion;
        this.observacion = observacion;
    }

    public Integer getObservacionProyID() {
        return observacionProyID;
    }

    public void setObservacionProyID(Integer observacionProyID) {
        this.observacionProyID = observacionProyID;
    }

    public String getTipoObservacion() {
        return tipoObservacion;
    }

    public void setTipoObservacion(String tipoObservacion) {
        this.tipoObservacion = tipoObservacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public DatoProyecto getDatoProyecto() {
        return datoProyecto;
    }

    public void setDatoProyecto(DatoProyecto datoProyecto) {
        this.datoProyecto = datoProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (observacionProyID != null ? observacionProyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObservacionProy)) {
            return false;
        }
        ObservacionProy other = (ObservacionProy) object;
        if ((this.observacionProyID == null && other.observacionProyID != null) || (this.observacionProyID != null && !this.observacionProyID.equals(other.observacionProyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.ObservacionProy[observacionProyID=" + observacionProyID + "]";
    }

}
