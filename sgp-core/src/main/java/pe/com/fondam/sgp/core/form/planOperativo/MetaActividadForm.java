package pe.com.fondam.sgp.core.form.planOperativo;

import java.io.Serializable;

public class MetaActividadForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8882975890707164858L;
	private Integer metaActividadID;
	private Integer unidadMedidaId;
	private String unidadMedidaNombre;
	private Integer cantidadMetaActividad;
	private String contribucionProposito;
	private Integer tipoIndicadorActividadId;
	private String tipoIndicadorActividadNombre;
	private Integer cantTotalCronogramaMetaActividad;

	public Integer getMetaActividadID() {
		return metaActividadID;
	}
	
	public void setMetaActividadID(Integer metaActividadID) {
		this.metaActividadID = metaActividadID;
	}
	
	public Integer getUnidadMedidaId() {
		return unidadMedidaId;
	}
	
	public void setUnidadMedidaId(Integer unidadMedidaId) {
		this.unidadMedidaId = unidadMedidaId;
	}
	
	public String getUnidadMedidaNombre() {
		return unidadMedidaNombre;
	}
	
	public void setUnidadMedidaNombre(String unidadMedidaNombre) {
		this.unidadMedidaNombre = unidadMedidaNombre;
	}
	
	public Integer getCantidadMetaActividad() {
		return cantidadMetaActividad;
	}
	
	public void setCantidadMetaActividad(Integer cantidadMetaActividad) {
		this.cantidadMetaActividad = cantidadMetaActividad;
	}
	
	public String getContribucionProposito() {
		return contribucionProposito;
	}
	
	public void setContribucionProposito(String contribucionProposito) {
		this.contribucionProposito = contribucionProposito;
	}
	
	public Integer getTipoIndicadorActividadId() {
		return tipoIndicadorActividadId;
	}
	
	public void setTipoIndicadorActividadId(Integer tipoIndicadorActividadId) {
		this.tipoIndicadorActividadId = tipoIndicadorActividadId;
	}
	
	public String getTipoIndicadorActividadNombre() {
		return tipoIndicadorActividadNombre;
	}
	
	public void setTipoIndicadorActividadNombre(
			String tipoIndicadorActividadNombre) {
		this.tipoIndicadorActividadNombre = tipoIndicadorActividadNombre;
	}

	public Integer getCantTotalCronogramaMetaActividad() {
		return cantTotalCronogramaMetaActividad;
	}

	public void setCantTotalCronogramaMetaActividad(
			Integer cantTotalCronogramaMetaActividad) {
		this.cantTotalCronogramaMetaActividad = cantTotalCronogramaMetaActividad;
	}
}
