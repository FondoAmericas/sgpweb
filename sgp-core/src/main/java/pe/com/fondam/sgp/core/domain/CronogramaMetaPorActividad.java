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
@Table(name = "cronograma_meta_por_actividad")
public class CronogramaMetaPorActividad implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -620691750768847444L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     
    @Column(name = "Cronograma_Meta_Por_Actividad_ID")
    private Integer cronogramaMetaPorActividadID;
     
    @Column(name = "periodo")
    private String periodo;
     
    @Column(name = "cantidad_meta_actividad_prog_por_periodo")
    private int cantidadMetaActividadProgPorPeriodo;
     
    @Column(name = "cantidad_meta_actividad_prog_por_periodo_ejecutada")
    private int cantidadMetaActividadProgPorPeriodoEjecutada;
    
    @JoinColumn(name = "Meta_Por_Actividad_ID", referencedColumnName = "Meta_Por_Actividad_ID")
    @ManyToOne(optional = false)
    private MetaPorActividad metaPorActividad;

    @Column(name = "estado_eliminado")
	private Integer estadoEliminado;

    @Column(name = "periodo_reportado")
	private Integer periodoReportado;

    
    public CronogramaMetaPorActividad() {
    }

    public Integer getCronogramaMetaPorActividadID() {
        return cronogramaMetaPorActividadID;
    }

    public void setCronogramaMetaPorActividadID(Integer cronorgramaMetaPorActividadID) {
        this.cronogramaMetaPorActividadID = cronorgramaMetaPorActividadID;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getCantidadMetaActividadProgPorPeriodo() {
        return cantidadMetaActividadProgPorPeriodo;
    }

    public void setCantidadMetaActividadProgPorPeriodo(int cantidadMetaActividadProgPorPeriodo) {
        this.cantidadMetaActividadProgPorPeriodo = cantidadMetaActividadProgPorPeriodo;
    }

    public int getCantidadMetaActividadProgPorPeriodoEjecutada() {
        return cantidadMetaActividadProgPorPeriodoEjecutada;
    }

    public void setCantidadMetaActividadProgPorPeriodoEjecutada(int cantidadMetaActividadProgPorPeriodoEjecutada) {
        this.cantidadMetaActividadProgPorPeriodoEjecutada = cantidadMetaActividadProgPorPeriodoEjecutada;
    }


    public MetaPorActividad getMetaPorActividad() {
        return metaPorActividad;
    }

    public void setMetaPorActividad(MetaPorActividad metaPorActividad) {
        this.metaPorActividad = metaPorActividad;
    }

    public void setEstadoEliminado(Integer estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	public Integer getEstadoEliminado() {
		return estadoEliminado;
	}

	public Integer getPeriodoReportado() {
		return periodoReportado;
	}

	public void setPeriodoReportado(Integer periodoReportado) {
		this.periodoReportado = periodoReportado;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (cronogramaMetaPorActividadID != null ? cronogramaMetaPorActividadID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CronogramaMetaPorActividad)) {
            return false;
        }
        CronogramaMetaPorActividad other = (CronogramaMetaPorActividad) object;
        if ((this.cronogramaMetaPorActividadID == null && other.cronogramaMetaPorActividadID != null) || (this.cronogramaMetaPorActividadID != null && !this.cronogramaMetaPorActividadID.equals(other.cronogramaMetaPorActividadID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.CronorgramaMetaPorActividad[cronorgramaMetaPorActividadID=" + cronogramaMetaPorActividadID + "]";
    }

}
