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
@Table(name = "acta_cierre")
public class ActaCierre implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Acta_Cierre_ID")
	private Integer actaCierreID;
	
	@Lob
	@Column(name = "descripcion")
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
	private DatoProyecto datoProyecto;


	public Integer getActaCierreID() {
		return actaCierreID;
	}

	public void setActaCierreID(Integer actaCierreID) {
		this.actaCierreID = actaCierreID;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public DatoProyecto getDatoProyecto() {
		return datoProyecto;
	}

	public void setDatoProyecto(DatoProyecto datoProyecto) {
		this.datoProyecto = datoProyecto;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (actaCierreID != null ? actaCierreID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ActaCierre)) {
			return false;
		}
		ActaCierre other = (ActaCierre) object;
		if ((this.actaCierreID == null && other.actaCierreID != null)
				|| (this.actaCierreID != null && !this.actaCierreID
						.equals(other.actaCierreID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.ActaCierre[actaCierreID="
				+ actaCierreID + "]";
	}

}
