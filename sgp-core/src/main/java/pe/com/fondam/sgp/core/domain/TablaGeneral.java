package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tabla_general")
public class TablaGeneral implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Tabla_General_ID")
	private Integer tablaGeneralID;

	@Column(name = "cabecera")
	private String cabecera;

	@Column(name = "prefijo")
	private String prefijo;

	@Column(name = "estado_eliminado")
	private int estadoEliminado;

	public Integer getTablaGeneralID() {
		return tablaGeneralID;
	}

	public void setTablaGeneralID(Integer tablaGeneralID) {
		this.tablaGeneralID = tablaGeneralID;
	}

	public String getCabecera() {
		return cabecera;
	}

	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public int getEstadoEliminado() {
		return estadoEliminado;
	}

	public void setEstadoEliminado(int estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tablaGeneralID != null ? tablaGeneralID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TablaGeneral)) {
			return false;
		}
		TablaGeneral other = (TablaGeneral) object;
		if ((this.tablaGeneralID == null && other.tablaGeneralID != null)
				|| (this.tablaGeneralID != null && !this.tablaGeneralID
						.equals(other.tablaGeneralID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.TablaGeneral[tablaGeneralID="
				+ tablaGeneralID + "]";
	}

}
