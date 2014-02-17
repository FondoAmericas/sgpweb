/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

import pe.com.fondam.sgp.core.domain.PropuestaTransferencia;


public class DirectivaBeneficiarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6485198179407509182L;
	private Integer directivaBeneficiarioID;
	private int fkIdtablaespCargo;
	private String descripcionCargo;
	private String nombreCompleto;
	private int fkIdtablaespTipoDocumento;
	private String descripcionTipoDocumento;
	private String numeroDocumento;
	private String acreditacion;
	private String vigenciaPoder;
	private PropuestaTransferencia propuestaTransferencia;

	public DirectivaBeneficiarioBean() {
	}

	public Integer getDirectivaBeneficiarioID() {
		return directivaBeneficiarioID;
	}

	public void setDirectivaBeneficiarioID(Integer directivaBeneficiarioID) {
		this.directivaBeneficiarioID = directivaBeneficiarioID;
	}

	public int getFkIdtablaespCargo() {
		return fkIdtablaespCargo;
	}

	public void setFkIdtablaespCargo(int fkIdtablaespCargo) {
		this.fkIdtablaespCargo = fkIdtablaespCargo;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public int getFkIdtablaespTipoDocumento() {
		return fkIdtablaespTipoDocumento;
	}

	public void setFkIdtablaespTipoDocumento(int fkIdtablaespTipoDocumento) {
		this.fkIdtablaespTipoDocumento = fkIdtablaespTipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getAcreditacion() {
		return acreditacion;
	}

	public void setAcreditacion(String acreditacion) {
		this.acreditacion = acreditacion;
	}

	public String getVigenciaPoder() {
		return vigenciaPoder;
	}

	public void setVigenciaPoder(String vigenciaPoder) {
		this.vigenciaPoder = vigenciaPoder;
	}

	public PropuestaTransferencia getPropuestaTransferencia() {
		return propuestaTransferencia;
	}

	public void setPropuestaTransferencia(
			PropuestaTransferencia propuestaTransferencia) {
		this.propuestaTransferencia = propuestaTransferencia;
	}

	public String getDescripcionCargo() {
		return descripcionCargo;
	}

	public void setDescripcionCargo(String descripcionCargo) {
		this.descripcionCargo = descripcionCargo;
	}

	public String getDescripcionTipoDocumento() {
		return descripcionTipoDocumento;
	}

	public void setDescripcionTipoDocumento(String descripcionTipoDocumento) {
		this.descripcionTipoDocumento = descripcionTipoDocumento;
	}

}
