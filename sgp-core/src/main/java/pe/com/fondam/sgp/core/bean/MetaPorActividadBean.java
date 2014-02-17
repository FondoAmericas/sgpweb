package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

import pe.com.fondam.sgp.core.domain.Actividad;

public class MetaPorActividadBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1959869259420960269L;
	private Integer metaPorActividadID;
	private Integer fkIdtablaespTipoIndicadorActividad;
	private String descripcionTipoIndicadorActividad;
	private Integer fkIdtablaespUnidadMedida;
	private String descripcionUnidadMedida;
	private Integer cantidadMetaActividad;
	private Integer cantidadMetaActividadEjecutada;
	private Integer logroAlcanzado;
	private String contribucionProposito;
	private Actividad actividad;

	public MetaPorActividadBean(){
		
	}

	public Integer getMetaPorActividadID() {
		return metaPorActividadID;
	}

	public void setMetaPorActividadID(Integer metaPorActividadID) {
		this.metaPorActividadID = metaPorActividadID;
	}

	public Integer getFkIdtablaespTipoIndicadorActividad() {
		return fkIdtablaespTipoIndicadorActividad;
	}

	public void setFkIdtablaespTipoIndicadorActividad(
			Integer fkIdtablaespTipoIndicadorActividad) {
		this.fkIdtablaespTipoIndicadorActividad = fkIdtablaespTipoIndicadorActividad;
	}

	public String getDescripcionTipoIndicadorActividad() {
		return descripcionTipoIndicadorActividad;
	}

	public void setDescripcionTipoIndicadorActividad(
			String descripcionTipoIndicadorActividad) {
		this.descripcionTipoIndicadorActividad = descripcionTipoIndicadorActividad;
	}

	public Integer getFkIdtablaespUnidadMedida() {
		return fkIdtablaespUnidadMedida;
	}

	public void setFkIdtablaespUnidadMedida(Integer fkIdtablaespUnidadMedida) {
		this.fkIdtablaespUnidadMedida = fkIdtablaespUnidadMedida;
	}

	public String getDescripcionUnidadMedida() {
		return descripcionUnidadMedida;
	}

	public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
		this.descripcionUnidadMedida = descripcionUnidadMedida;
	}

	public Integer getCantidadMetaActividad() {
		return cantidadMetaActividad;
	}

	public void setCantidadMetaActividad(Integer cantidadMetaActividad) {
		this.cantidadMetaActividad = cantidadMetaActividad;
	}

	public Integer getCantidadMetaActividadEjecutada() {
		return cantidadMetaActividadEjecutada;
	}

	public void setCantidadMetaActividadEjecutada(
			Integer cantidadMetaActividadEjecutada) {
		this.cantidadMetaActividadEjecutada = cantidadMetaActividadEjecutada;
	}

	public Integer getLogroAlcanzado() {
		return logroAlcanzado;
	}

	public void setLogroAlcanzado(Integer logroAlcanzado) {
		this.logroAlcanzado = logroAlcanzado;
	}

	public String getContribucionProposito() {
		return contribucionProposito;
	}

	public void setContribucionProposito(String contribucionProposito) {
		this.contribucionProposito = contribucionProposito;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	
	
}
