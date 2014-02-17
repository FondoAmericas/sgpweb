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
@Table(name = "directiva_beneficiario")
public class DirectivaBeneficiario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3515106048443941324L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Basic(optional = false)
	@Column(name = "Directiva_Beneficiario_ID")
	private Integer directivaBeneficiarioID;
	
	//@Basic(optional = false)
	@Column(name = "fk_idtablaesp_cargo")
	private int fkIdtablaespCargo;
	
	@Transient
	private String descripcionCargo;
	
	//@Basic(optional = false)
	@Column(name = "nombre_completo")
	private String nombreCompleto;
	
	//@Basic(optional = false)
	@Column(name = "fk_idtablaesp_tipo_documento")
	private int fkIdtablaespTipoDocumento;

	@Transient
	private String descripcionTipoDocumento;
	
	//@Basic(optional = false)
	@Column(name = "numero_documento")
	private String numeroDocumento;

	@Lob
	//@Basic(optional = false)
	@Column(name = "acreditacion")
	private String acreditacion;

	@Lob
	//@Basic(optional = false)
	@Column(name = "vigencia_poder")
	private String vigenciaPoder;
	
	@JoinColumn(name = "Propuesta_Transferencia_ID", referencedColumnName = "Propuesta_Transferencia_ID")
	@ManyToOne(optional = false)
	private PropuestaTransferencia propuestaTransferencia;

	public DirectivaBeneficiario() {
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (directivaBeneficiarioID != null ? directivaBeneficiarioID
				.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof DirectivaBeneficiario)) {
			return false;
		}
		DirectivaBeneficiario other = (DirectivaBeneficiario) object;
		if ((this.directivaBeneficiarioID == null && other.directivaBeneficiarioID != null)
				|| (this.directivaBeneficiarioID != null && !this.directivaBeneficiarioID
						.equals(other.directivaBeneficiarioID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.DirectivaBeneficiario[directivaBeneficiarioID="
				+ directivaBeneficiarioID + "]";
	}

}
