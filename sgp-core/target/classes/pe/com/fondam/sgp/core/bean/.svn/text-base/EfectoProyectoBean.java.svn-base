/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.fondam.sgp.core.bean;

import java.io.Serializable;

import pe.com.fondam.sgp.core.domain.InformeFinal;

public class EfectoProyectoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7318135529799994370L;
	private Integer efectoProyectoID;
	private int fkidtablaespefectoProy;
	private String descripcionefectoProy;
	private String comentario;
	private InformeFinal informeFinal;

	public EfectoProyectoBean() {
	}

	public Integer getEfectoProyectoID() {
		return efectoProyectoID;
	}

	public void setEfectoProyectoID(Integer efectoProyectoID) {
		this.efectoProyectoID = efectoProyectoID;
	}

	public int getFkidtablaespefectoProy() {
		return fkidtablaespefectoProy;
	}

	public void setFkidtablaespefectoProy(int fkidtablaespefectoProy) {
		this.fkidtablaespefectoProy = fkidtablaespefectoProy;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public InformeFinal getInformeFinal() {
		return informeFinal;
	}

	public void setInformeFinal(InformeFinal informeFinal) {
		this.informeFinal = informeFinal;
	}

	public void setDescripcionefectoProy(String descripcionefectoProy) {
		this.descripcionefectoProy = descripcionefectoProy;
	}

	public String getDescripcionefectoProy() {
		return descripcionefectoProy;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (efectoProyectoID != null ? efectoProyectoID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof EfectoProyectoBean)) {
			return false;
		}
		EfectoProyectoBean other = (EfectoProyectoBean) object;
		if ((this.efectoProyectoID == null && other.efectoProyectoID != null)
				|| (this.efectoProyectoID != null && !this.efectoProyectoID
						.equals(other.efectoProyectoID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.EfectoProyecto[efectoProyectoID="
				+ efectoProyectoID + "]";
	}

}
