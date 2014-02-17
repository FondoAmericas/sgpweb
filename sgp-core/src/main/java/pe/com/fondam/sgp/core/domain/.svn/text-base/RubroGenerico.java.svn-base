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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rubro_generico")
public class RubroGenerico implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Rubro_Generico_ID")
	private Integer rubroGenericoID;

	@Column(name = "idcabecera_rubrogenerico")
	private Integer idcabeceraRubrogenerico;

	@Column(name = "descrip_cabecera_rubro_generico")
	private String descripCabeceraRubroGenerico;

	@Column(name = "idespecificacion_rubro_generico")
	private Integer idespecificacionRubroGenerico;

	@Column(name = "descrip_especificacion_rubro_generico")
	private String descripEspecificacionRubroGenerico;

	@ManyToOne
	@JoinColumn(name = "Categoria_Actividad_ID", referencedColumnName = "Categoria_Actividad_ID")
	private CategoriaActividad categoriaActividad;

	public Integer getRubroGenericoID() {
		return rubroGenericoID;
	}

	public void setRubroGenericoID(Integer rubroGenericoID) {
		this.rubroGenericoID = rubroGenericoID;
	}

	public Integer getIdcabeceraRubrogenerico() {
		return idcabeceraRubrogenerico;
	}

	public void setIdcabeceraRubrogenerico(Integer idcabeceraRubrogenerico) {
		this.idcabeceraRubrogenerico = idcabeceraRubrogenerico;
	}

	public String getDescripCabeceraRubroGenerico() {
		return descripCabeceraRubroGenerico;
	}

	public void setDescripCabeceraRubroGenerico(
			String descripCabeceraRubroGenerico) {
		this.descripCabeceraRubroGenerico = descripCabeceraRubroGenerico;
	}

	public int getIdespecificacionRubroGenerico() {
		return idespecificacionRubroGenerico;
	}

	public void setIdespecificacionRubroGenerico(
			int idespecificacionRubroGenerico) {
		this.idespecificacionRubroGenerico = idespecificacionRubroGenerico;
	}

	public String getDescripEspecificacionRubroGenerico() {
		return descripEspecificacionRubroGenerico;
	}

	public void setDescripEspecificacionRubroGenerico(
			String descripEspecificacionRubroGenerico) {
		this.descripEspecificacionRubroGenerico = descripEspecificacionRubroGenerico;
	}

	public CategoriaActividad getCategoriaActividad() {
		return categoriaActividad;
	}

	public void setCategoriaActividad(CategoriaActividad categoriaActividad) {
		this.categoriaActividad = categoriaActividad;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (rubroGenericoID != null ? rubroGenericoID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof RubroGenerico)) {
			return false;
		}
		RubroGenerico other = (RubroGenerico) object;
		if ((this.rubroGenericoID == null && other.rubroGenericoID != null)
				|| (this.rubroGenericoID != null && !this.rubroGenericoID
						.equals(other.rubroGenericoID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.RubroGenerico[rubroGenericoID="
				+ rubroGenericoID + "]";
	}

}
