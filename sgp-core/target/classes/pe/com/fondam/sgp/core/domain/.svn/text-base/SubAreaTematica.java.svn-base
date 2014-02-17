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
@Table(name = "sub_area_tematica")
public class SubAreaTematica implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Sub_Area_Tematica_ID")
	private Integer subAreaTematicaID;

	@Column(name = "cod_interno_sub_at")
	private int codInternoSubAt;

	@Column(name = "cod_interno_sub_nivel")
	private int codInternoSubNivel;

	@Column(name = "descripcion_sub_at")
	private String descripcionSubAt;

	@Column(name = "descripcion_sub_nivel")
	private String descripcionSubNivel;

	@Column(name = "estado_eliminado")
	private int estadoEliminado;

	@Column(name = "fk_idtablaesp_area_tematica")
	private int fkIdtablaespAreaTematica;


	public Integer getSubAreaTematicaID() {
		return subAreaTematicaID;
	}

	public void setSubAreaTematicaID(Integer subAreaTematicaID) {
		this.subAreaTematicaID = subAreaTematicaID;
	}

	public int getCodInternoSubAt() {
		return codInternoSubAt;
	}

	public void setCodInternoSubAt(int codInternoSubAt) {
		this.codInternoSubAt = codInternoSubAt;
	}

	public int getCodInternoSubNivel() {
		return codInternoSubNivel;
	}

	public void setCodInternoSubNivel(int codInternoSubNivel) {
		this.codInternoSubNivel = codInternoSubNivel;
	}

	public String getDescripcionSubAt() {
		return descripcionSubAt;
	}

	public void setDescripcionSubAt(String descripcionSubAt) {
		this.descripcionSubAt = descripcionSubAt;
	}

	public String getDescripcionSubNivel() {
		return descripcionSubNivel;
	}

	public void setDescripcionSubNivel(String descripcionSubNivel) {
		this.descripcionSubNivel = descripcionSubNivel;
	}

	public int getEstadoEliminado() {
		return estadoEliminado;
	}

	public void setEstadoEliminado(int estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	public int getFkIdtablaespAreaTematica() {
		return fkIdtablaespAreaTematica;
	}

	public void setFkIdtablaespAreaTematica(int fkIdtablaespAreaTematica) {
		this.fkIdtablaespAreaTematica = fkIdtablaespAreaTematica;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (subAreaTematicaID != null ? subAreaTematicaID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof SubAreaTematica)) {
			return false;
		}
		SubAreaTematica other = (SubAreaTematica) object;
		if ((this.subAreaTematicaID == null && other.subAreaTematicaID != null)
				|| (this.subAreaTematicaID != null && !this.subAreaTematicaID
						.equals(other.subAreaTematicaID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.SubAreaTematica[subAreaTematicaID="
				+ subAreaTematicaID + "]";
	}

}
