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
@Table(name = "dato_usuario")
public class DatoUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Dato_Usuario_ID")
	private Integer datoUsuarioID;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "paterno")
	private String paterno;

	@Column(name = "materno")
	private String materno;

	@Column(name = "fk_idtablaesp_tipo_documento")
	private int fkIdtablaespTipoDocumento;

	@Column(name = "numero_documento")
	private String numeroDocumento;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "email")
	private String email;

	@Lob
	@Column(name = "observacion")
	private String observacion;

	@Column(name = "estado_eliminado")
	private int estadoEliminado;
	
	@Column(name = "Dato_Proyecto_ID")
	private Integer datoProyectoID;



	@Column(name = "fk_idtablaesp_jerarquia")
	private int jerarquia;

	@ManyToOne
	@JoinColumn(name = "Dep_Prov_Dist_ID", referencedColumnName = "Dep_Prov_Dist_ID")
	private DepProvDist depProvDist;
	

	public Integer getDatoProyectoID() {
		return datoProyectoID;
	}

	public void setDatoProyectoID(Integer datoProyectoID) {
		this.datoProyectoID = datoProyectoID;
	}
	
	public Integer getDatoUsuarioID() {
		return datoUsuarioID;
	}

	public void setDatoUsuarioID(Integer datoUsuarioID) {
		this.datoUsuarioID = datoUsuarioID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public int getEstadoEliminado() {
		return estadoEliminado;
	}

	public void setEstadoEliminado(int estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	public int getJerarquia() {
		return jerarquia;
	}

	public void setJerarquia(int jerarquia) {
		this.jerarquia = jerarquia;
	}

	public DepProvDist getDepProvDist() {
		return depProvDist;
	}

	public void setDepProvDist(DepProvDist depProvDist) {
		this.depProvDist = depProvDist;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (datoUsuarioID != null ? datoUsuarioID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof DatoUsuario)) {
			return false;
		}
		DatoUsuario other = (DatoUsuario) object;
		if ((this.datoUsuarioID == null && other.datoUsuarioID != null)
				|| (this.datoUsuarioID != null && !this.datoUsuarioID
						.equals(other.datoUsuarioID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.DatoUsuario[datoUsuarioID="
				+ datoUsuarioID + "]";
	}

}
