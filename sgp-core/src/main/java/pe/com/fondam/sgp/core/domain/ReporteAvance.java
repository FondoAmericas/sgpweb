/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "reporte_avance")

public class ReporteAvance implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Reporte_Avance_ID")
    private Integer reporteAvanceID;
    
    @Column(name = "periodo")
    private String periodo;
    
    @Column(name = "fk_id_detalle_estado_cab_est_rep_avance")
    private int fkIdDetalleEstadoCabEstRepAvance;

    @Column(name = "fecha_Inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    @Transient
    private String fechaInicioString;

    @Column(name = "fecha_Fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @Transient
    private String fechaFinString;

    @Lob
    @Column(name = "resumen")
    private String resumen;
    
    @JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
    @ManyToOne
    private DatoProyecto datoProyecto;
    
    @Transient
    private String estRepAvanceDesc;
    
    @Transient
    private String repAvanceDesc;
    
	@Transient
	private Integer cantObservacionesRelevantes;
  
    public String getRepAvanceDesc() {
		return repAvanceDesc;
	}

	public void setRepAvanceDesc(String repAvanceDesc) {
		this.repAvanceDesc = repAvanceDesc;
	}

	public String getEstRepAvanceDesc() {
		return estRepAvanceDesc;
	}

	public void setEstRepAvanceDesc(String estRepAvanceDesc) {
		this.estRepAvanceDesc = estRepAvanceDesc;
	}

	public ReporteAvance() {
    }

    public Integer getReporteAvanceID() {
        return reporteAvanceID;
    }

    public void setReporteAvanceID(Integer reporteAvanceID) {
        this.reporteAvanceID = reporteAvanceID;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getFkIdDetalleEstadoCabEstRepAvance() {
        return fkIdDetalleEstadoCabEstRepAvance;
    }

    public void setFkIdDetalleEstadoCabEstRepAvance(int fkIdDetalleEstadoCabEstRepAvance) {
        this.fkIdDetalleEstadoCabEstRepAvance = fkIdDetalleEstadoCabEstRepAvance;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

  
    public DatoProyecto getDatoProyecto() {
        return datoProyecto;
    }

    public void setDatoProyecto(DatoProyecto datoProyecto) {
        this.datoProyecto = datoProyecto;
    }


    public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getFechaInicioString() {
		return fechaInicioString;
	}

	public void setFechaInicioString(String fechaInicioString) {
		this.fechaInicioString = fechaInicioString;
	}

	public String getFechaFinString() {
		return fechaFinString;
	}

	public void setFechaFinString(String fechaFinString) {
		this.fechaFinString = fechaFinString;
	}

	public void setCantObservacionesRelevantes(
			Integer cantObservacionesRelevantes) {
		this.cantObservacionesRelevantes = cantObservacionesRelevantes;
	}

	public Integer getCantObservacionesRelevantes() {
		return cantObservacionesRelevantes;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (reporteAvanceID != null ? reporteAvanceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteAvance)) {
            return false;
        }
        ReporteAvance other = (ReporteAvance) object;
        if ((this.reporteAvanceID == null && other.reporteAvanceID != null) || (this.reporteAvanceID != null && !this.reporteAvanceID.equals(other.reporteAvanceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.ReporteAvance[reporteAvanceID=" + reporteAvanceID + "]";
    }

}
