package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

import pe.com.fondam.sgp.core.domain.MetaPorActividad;
import pe.com.fondam.sgp.core.domain.ReporteAvance;

public class AvanceResultadoActividadBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4023825139573626341L;
	private Integer avanceResultadoActividadID;
    private int cantidadAvanceEjecutado;
    private String descripcionActividad;
    private String resumenEjecutivoPeriodo;
    private String elementoVerificacion;
    private String observaciones;
    private int ejecutado;
    private ReporteAvance reporteAvance;
    private MetaPorActividad metaPorActividad;
    private String descripcionUnidadMedida;
    private String periodo;
    private Integer resultadoId;
    private Integer actividadId;
    private Integer metaActividadId;

    public AvanceResultadoActividadBean(){
    	
    }

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

	public void setMetaPorActividad(
			MetaPorActividad metaPorActividad) {
		this.metaPorActividad = metaPorActividad;
	}

	public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
		this.descripcionUnidadMedida = descripcionUnidadMedida;
	}

	public String getDescripcionUnidadMedida() {
		return descripcionUnidadMedida;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getPeriodo() {
		return periodo;
	}

	public Integer getResultadoId() {
		return resultadoId;
	}

	public void setResultadoId(Integer resultadoId) {
		this.resultadoId = resultadoId;
	}

	public Integer getActividadId() {
		return actividadId;
	}

	public void setActividadId(Integer actividadId) {
		this.actividadId = actividadId;
	}

	public Integer getMetaActividadId() {
		return metaActividadId;
	}

	public void setMetaActividadId(Integer metaActividadId) {
		this.metaActividadId = metaActividadId;
	}
}
