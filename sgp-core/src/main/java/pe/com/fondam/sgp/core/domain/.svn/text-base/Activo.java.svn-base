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
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "activo")
public class Activo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Activo_ID")
	private Integer activoID;

	@Column(name = "fk_idtablaesp_categoria_activo")
	private int fkIdtablaespCategoriaActivo;
	
	@Transient
	private String descripcionCategoriaActivo;

	@Column(name = "descripcion_activo")
	private String descripcionActivo;

	@Column(name = "estado_eliminado")
	private int estadoEliminado;

	public Integer getActivoID() {
		return activoID;
	}

	public void setActivoID(Integer activoID) {
		this.activoID = activoID;
	}

	public int getFkIdtablaespCategoriaActivo() {
		return fkIdtablaespCategoriaActivo;
	}

	public void setFkIdtablaespCategoriaActivo(int fkIdtablaespCategoriaActivo) {
		this.fkIdtablaespCategoriaActivo = fkIdtablaespCategoriaActivo;
	}

	public void setDescripcionCategoriaActivo(String descripcionCategoriaActivo) {
		this.descripcionCategoriaActivo = descripcionCategoriaActivo;
	}

	public String getDescripcionCategoriaActivo() {
		return descripcionCategoriaActivo;
	}

	public String getDescripcionActivo() {
		return descripcionActivo;
	}

	public void setDescripcionActivo(String descripcionActivo) {
		this.descripcionActivo = descripcionActivo;
	}

	public void setEstadoEliminado(int estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	public int getEstadoEliminado() {
		return estadoEliminado;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (activoID != null ? activoID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Activo)) {
			return false;
		}
		Activo other = (Activo) object;
		if ((this.activoID == null && other.activoID != null)
				|| (this.activoID != null && !this.activoID
						.equals(other.activoID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.Activo[activoID=" + activoID
				+ "]";
	}

}
