package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

import pe.com.fondam.sgp.core.domain.MetaPorActividad;

public class CronogramaMetaPorActividadBean implements Serializable {

	   
    /**
	 * 
	 */
	private static final long serialVersionUID = 5174329882072846948L;
	private Integer cronogramaMetaPorActividadID;
    private String periodo;
    private int cantidadMetaActividadProgPorPeriodo;
    private int cantidadMetaActividadProgPorPeriodoEjecutada;
    private String descripcionUnidadMedida;
    private MetaPorActividad metaPorActividad;
    
    public CronogramaMetaPorActividadBean(){
    	
    }

	public Integer getCronogramaMetaPorActividadID() {
		return cronogramaMetaPorActividadID;
	}

	public void setCronogramaMetaPorActividadID(Integer cronogramaMetaPorActividadID) {
		this.cronogramaMetaPorActividadID = cronogramaMetaPorActividadID;
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

	public void setCantidadMetaActividadProgPorPeriodo(
			int cantidadMetaActividadProgPorPeriodo) {
		this.cantidadMetaActividadProgPorPeriodo = cantidadMetaActividadProgPorPeriodo;
	}

	public int getCantidadMetaActividadProgPorPeriodoEjecutada() {
		return cantidadMetaActividadProgPorPeriodoEjecutada;
	}

	public void setCantidadMetaActividadProgPorPeriodoEjecutada(
			int cantidadMetaActividadProgPorPeriodoEjecutada) {
		this.cantidadMetaActividadProgPorPeriodoEjecutada = cantidadMetaActividadProgPorPeriodoEjecutada;
	}

	public String getDescripcionUnidadMedida() {
		return descripcionUnidadMedida;
	}

	public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
		this.descripcionUnidadMedida = descripcionUnidadMedida;
	}

	public MetaPorActividad getMetaPorActividad() {
		return metaPorActividad;
	}

	public void setMetaPorActividad(MetaPorActividad metaPorActividad) {
		this.metaPorActividad = metaPorActividad;
	}
    
    
}
