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
@Table(name = "tabla_especifica")
public class TablaEspecifica implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Tabla_Especifica_ID")
	private Integer tablaEspecificaID;

	@Column(name = "descripcion_cabecera")
	private String descripcionCabecera;

	@Column(name = "estado_eliminado")
	private int estadoEliminado;

	@ManyToOne
	@JoinColumn(name = "Tabla_General_ID", referencedColumnName = "Tabla_General_ID")
	private TablaGeneral tablaGeneral;

	public Integer getTablaEspecificaID() {
		return tablaEspecificaID;
	}

	public void setTablaEspecificaID(Integer tablaEspecificaID) {
		this.tablaEspecificaID = tablaEspecificaID;
	}

	public String getDescripcionCabecera() {
		return descripcionCabecera;
	}

	public void setDescripcionCabecera(String descripcionCabecera) {
		this.descripcionCabecera = descripcionCabecera;
	}

	public int getEstadoEliminado() {
		return estadoEliminado;
	}

	public void setEstadoEliminado(int estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	public TablaGeneral getTablaGeneral() {
		return tablaGeneral;
	}

	public void setTablaGeneral(TablaGeneral tablaGeneral) {
		this.tablaGeneral = tablaGeneral;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tablaEspecificaID != null ? tablaEspecificaID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TablaEspecifica)) {
			return false;
		}
		TablaEspecifica other = (TablaEspecifica) object;
		if ((this.tablaEspecificaID == null && other.tablaEspecificaID != null)
				|| (this.tablaEspecificaID != null && !this.tablaEspecificaID
						.equals(other.tablaEspecificaID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.TablaEspecifica[tablaEspecificaID="
				+ tablaEspecificaID + "]";
	}

}
