package pe.com.fondam.sgp.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tabla_clase")
public class TablaClase implements Serializable {

	private static final long serialVersionUID = 3580511314006034701L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "table_clase_ID")
	private Integer tableclaseID;

	@Column(name = "tabla_nombre")
	private String tablaNombre;

	@Column(name = "clase_nombre")
	private String claseNombre;

	@Column(name = "titulo")
	private String titulo;
	
	public TablaClase(){
		
	}

	public Integer getTableclaseID() {
		return tableclaseID;
	}

	public void setTableclaseID(Integer tableclaseID) {
		this.tableclaseID = tableclaseID;
	}

	public String getTablaNombre() {
		return tablaNombre;
	}

	public void setTablaNombre(String tablaNombre) {
		this.tablaNombre = tablaNombre;
	}

	public String getClaseNombre() {
		return claseNombre;
	}

	public void setClaseNombre(String claseNombre) {
		this.claseNombre = claseNombre;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tableclaseID != null ? tableclaseID.hashCode() : 0);
		return hash;
	}
	
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TablaClase)) {
			return false;
		}
		TablaClase other = (TablaClase) object;
		if ((this.tableclaseID == null && other.tableclaseID != null)
				|| (this.tableclaseID != null && !this.tableclaseID
						.equals(other.tableclaseID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.TablaClase[tableclaseID="
				+ tableclaseID + "]";
	}

}
