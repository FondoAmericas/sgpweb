package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

public class ResumenProyectoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8232000580012613529L;
	private Integer resumenProyectoID;
	private int fkIdtablaespTipoResumenProy;
	private String descripcionTipoResumenProy;
	private String definicion;
	private int fkIdTablaGeneral;
	private int datoProyectoID;

	public ResumenProyectoBean() {
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

	public String getDefinicion() {
		return definicion;
	}

	public void setDefinicion(String definicion) {
		this.definicion = definicion;
	}

	public int getFkIdTablaGeneral() {
		return fkIdTablaGeneral;
	}

	public void setFkIdTablaGeneral(int fkIdTablaGeneral) {
		this.fkIdTablaGeneral = fkIdTablaGeneral;
	}

	public int getDatoProyectoID() {
		return datoProyectoID;
	}

	public void setDatoProyectoID(int datoProyectoID) {
		this.datoProyectoID = datoProyectoID;
	}

	public void setDescripcionTipoResumenProy(String descripcionTipoResumenProy) {
		this.descripcionTipoResumenProy = descripcionTipoResumenProy;
	}

	public String getDescripcionTipoResumenProy() {
		return descripcionTipoResumenProy;
	}

}
