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

@Entity
@Table(name = "categoria_actividad")
public class CategoriaActividad implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Categoria_Actividad_ID")
	private Integer categoriaActividadID;

	@Column(name = "fk_idtablaesp_tipo_actividad")
	private Integer fkIdtablaespTipoActividad;

	@Column(name = "descripcion_categoria_actividad")
	private String descripcionCategoriaActividad;

	public Integer getCategoriaActividadID() {
		return categoriaActividadID;
	}

	public void setCategoriaActividadID(Integer categoriaActividadID) {
		this.categoriaActividadID = categoriaActividadID;
	}

	public Integer getFkIdtablaespTipoActividad() {
		return fkIdtablaespTipoActividad;
	}

	public void setFkIdtablaespTipoActividad(Integer fkIdtablaespTipoActividad) {
		this.fkIdtablaespTipoActividad = fkIdtablaespTipoActividad;
	}

	public String getDescripcionCategoriaActividad() {
		return descripcionCategoriaActividad;
	}

	public void setDescripcionCategoriaActividad(
			String descripcionCategoriaActividad) {
		this.descripcionCategoriaActividad = descripcionCategoriaActividad;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (categoriaActividadID != null ? categoriaActividadID.hashCode()
				: 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CategoriaActividad)) {
			return false;
		}
		CategoriaActividad other = (CategoriaActividad) object;
		if ((this.categoriaActividadID == null && other.categoriaActividadID != null)
				|| (this.categoriaActividadID != null && !this.categoriaActividadID
						.equals(other.categoriaActividadID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.CategoriaActividad[categoriaActividadID="
				+ categoriaActividadID + "]";
	}

}
