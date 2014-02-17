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
import javax.persistence.Transient;

@Entity
@Table(name = "resumen_proyecto")

public class ResumenProyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "Resumen_Proyecto_ID")
    private Integer resumenProyectoID;
    
    @Column(name = "fk_idtablaesp_tipo_resumen_proy")
    private int fkIdtablaespTipoResumenProy;
    
    @Transient
    private String descripcionTipoResumenProy;
    
    @Lob
    @Column(name = "definicion")
    private String definicion;
    
    @Column(name = "fk_id_tabla_general")
    private int fkIdTablaGeneral;
    
    @ManyToOne
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    private DatoProyecto datoProyecto;

    public ResumenProyecto() {
    }

    public ResumenProyecto(Integer resumenProyectoID) {
        this.resumenProyectoID = resumenProyectoID;
    }

    public ResumenProyecto(Integer resumenProyectoID, int fkIdtablaespTipoResumenProy, String definicion) {
        this.resumenProyectoID = resumenProyectoID;
        this.fkIdtablaespTipoResumenProy = fkIdtablaespTipoResumenProy;
        this.definicion = definicion;
    }

    public Integer getResumenProyectoID() {
        return resumenProyectoID;
    }

    public void setResumenProyectoID(Integer resumenProyectoID) {
        this.resumenProyectoID = resumenProyectoID;
    }

    public int getFkIdtablaespTipoResumenProy() {
        return fkIdtablaespTipoResumenProy;
    }

    public void setFkIdtablaespTipoResumenProy(int fkIdtablaespTipoResumenProy) {
        this.fkIdtablaespTipoResumenProy = fkIdtablaespTipoResumenProy;
    }

    public int getFkIdTablaGeneral() {
		return fkIdTablaGeneral;
	}

	public void setFkIdTablaGeneral(int fkIdTablaGeneral) {
		this.fkIdTablaGeneral = fkIdTablaGeneral;
	}

	public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    public DatoProyecto getDatoProyecto() {
        return datoProyecto;
    }

    public void setDatoProyecto(DatoProyecto datoProyecto) {
        this.datoProyecto = datoProyecto;
    }

    public void setDescripcionTipoResumenProy(String descripcionTipoResumenProy) {
		this.descripcionTipoResumenProy = descripcionTipoResumenProy;
	}

	public String getDescripcionTipoResumenProy() {
		return descripcionTipoResumenProy;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (resumenProyectoID != null ? resumenProyectoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResumenProyecto)) {
            return false;
        }
        ResumenProyecto other = (ResumenProyecto) object;
        if ((this.resumenProyectoID == null && other.resumenProyectoID != null) || (this.resumenProyectoID != null && !this.resumenProyectoID.equals(other.resumenProyectoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.ResumenProyecto[resumenProyectoID=" + resumenProyectoID + "]";
    }

}
