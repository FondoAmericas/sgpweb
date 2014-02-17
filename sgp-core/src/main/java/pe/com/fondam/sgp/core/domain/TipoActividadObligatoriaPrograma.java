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
import javax.persistence.Table;

@Entity
@Table(name = "tipo_actividad_obligatoria_programa")

public class TipoActividadObligatoriaPrograma implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 580942908012862435L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    @Column(name = "Tipo_Actividad_Obligatoria_Programa_ID")
    private Integer tipoActividadObligatoriaProgramaID;
    
    @Column(name = "fk_idtablaesp_tipo_resul_activo_oblig")
    private int fkIdtablaespTipoResulActivoOblig;
    
    @Column(name = "descripcion")
    private String descripcion;
  

    public Integer getTipoActividadObligatoriaProgramaID() {
        return tipoActividadObligatoriaProgramaID;
    }

    public void setTipoActividadObligatoriaProgramaID(Integer tipoActividadObligatoriaProgramaID) {
        this.tipoActividadObligatoriaProgramaID = tipoActividadObligatoriaProgramaID;
    }

    public int getFkIdtablaespTipoResulActivoOblig() {
        return fkIdtablaespTipoResulActivoOblig;
    }

    public void setFkIdtablaespTipoResulActivoOblig(int fkIdtablaespTipoResulActivoOblig) {
        this.fkIdtablaespTipoResulActivoOblig = fkIdtablaespTipoResulActivoOblig;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoActividadObligatoriaProgramaID != null ? tipoActividadObligatoriaProgramaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoActividadObligatoriaPrograma)) {
            return false;
        }
        TipoActividadObligatoriaPrograma other = (TipoActividadObligatoriaPrograma) object;
        if ((this.tipoActividadObligatoriaProgramaID == null && other.tipoActividadObligatoriaProgramaID != null) || (this.tipoActividadObligatoriaProgramaID != null && !this.tipoActividadObligatoriaProgramaID.equals(other.tipoActividadObligatoriaProgramaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.TipoActividadObligatoriaPrograma[tipoActividadObligatoriaProgramaID=" + tipoActividadObligatoriaProgramaID + "]";
    }

}
