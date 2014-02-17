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
@Table(name = "avance_resultado_actividad")
public class AvanceResultadoActividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  
    @Column(name = "Avance_Resultado_Actividad_ID")
    private Integer avanceResultadoActividadID;
     
    @Column(name = "cantidad_avance_ejecutado")
    private int cantidadAvanceEjecutado;
     
    @Lob
    @Column(name = "descripcion_actividad")
    private String descripcionActividad;
     
    @Lob
    @Column(name = "resumen_ejecutivo_periodo")
    private String resumenEjecutivoPeriodo;
     
    @Lob
    @Column(name = "elemento_verificacion")
    private String elementoVerificacion;
    
    @Lob
    @Column(name = "observaciones")
    private String observaciones;
     
    @Column(name = "ejecutado")
    private int ejecutado;
    
    @ManyToOne
    @JoinColumn(name = "Reporte_Avance_ID", referencedColumnName = "Reporte_Avance_ID")
    private ReporteAvance reporteAvance;
    
    @ManyToOne
    @JoinColumn(name = "Meta_por_actividad_ID", referencedColumnName = "Meta_Por_Actividad_ID")
    private MetaPorActividad metaPorActividad;

    @Column(name = "estado_eliminado")
	private Integer estadoEliminado;
	
    public Integer getAvanceResultadoActividadID() {
        return avanceResultadoActividadID;
    }

    public void setAvanceResultadoActividadID(Integer avanceResultadoActividadID) {
        this.avanceResultadoActividadID = avanceResultadoActividadID;
    }

    public int getCantidadAvanceEjecutado() {
        return cantidadAvanceEjecutado;
    }

    public void setCantidadAvanceEjecutado(int cantidadAvanceEjecutado) {
        this.cantidadAvanceEjecutado = cantidadAvanceEjecutado;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public String getResumenEjecutivoPeriodo() {
        return resumenEjecutivoPeriodo;
    }

    public void setResumenEjecutivoPeriodo(String resumenEjecutivoPeriodo) {
        this.resumenEjecutivoPeriodo = resumenEjecutivoPeriodo;
    }

    public String getElementoVerificacion() {
        return elementoVerificacion;
    }

    public void setElementoVerificacion(String elementoVerificacion) {
        this.elementoVerificacion = elementoVerificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getEjecutado() {
        return ejecutado;
    }

    public void setEjecutado(int ejecutado) {
        this.ejecutado = ejecutado;
    }

    public ReporteAvance getReporteAvance() {
        return reporteAvance;
    }

    public void setReporteAvance(ReporteAvance reporteAvance) {
        this.reporteAvance = reporteAvance;
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

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (avanceResultadoActividadID != null ? avanceResultadoActividadID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AvanceResultadoActividad)) {
            return false;
        }
        AvanceResultadoActividad other = (AvanceResultadoActividad) object;
        if ((this.avanceResultadoActividadID == null && other.avanceResultadoActividadID != null) || (this.avanceResultadoActividadID != null && !this.avanceResultadoActividadID.equals(other.avanceResultadoActividadID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.fondam.sgp.core.domain.AvanceResultadoActividad[avanceResultadoActividadID=" + avanceResultadoActividadID + "]";
    }

}
