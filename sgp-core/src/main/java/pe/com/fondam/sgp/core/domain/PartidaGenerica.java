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
@Table(name = "partida_generica")
public class PartidaGenerica implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Partida_Generica_ID")
	private Integer partidaGenericaID;

//	@Column(name = "grupo_partida")
//	private String grupoPartida;
	
	@Column(name = "descripcion_partida_generica")
	private String descripcionPartidaGenerica;

	@Column(name = "estado_eliminado")
	private int estadoEliminado;

	@ManyToOne
	@JoinColumn(name = "Categoria_Actividad_ID", referencedColumnName = "Categoria_Actividad_ID")
	private CategoriaActividad categoriaActividad;
	
	@Column(name = "prefijo")
	private String prefijo;

	public Integer getPartidaGenericaID() {
		return partidaGenericaID;
	}

	public void setPartidaGenericaID(Integer partidaGenericaID) {
		this.partidaGenericaID = partidaGenericaID;
	}

//	public String getGrupoPartida() {
//		return grupoPartida;
//	}
//
//	public void setGrupoPartida(String grupoPartida) {
//		this.grupoPartida = grupoPartida;
//	}

	public String getDescripcionPartidaGenerica() {
		return descripcionPartidaGenerica;
	}

	public void setDescripcionPartidaGenerica(String descripcionPartidaGenerica) {
		this.descripcionPartidaGenerica = descripcionPartidaGenerica;
	}

	public int getEstadoEliminado() {
		return estadoEliminado;
	}

	public void setEstadoEliminado(int estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	public CategoriaActividad getCategoriaActividad() {
		return categoriaActividad;
	}

	public void setCategoriaActividad(CategoriaActividad categoriaActividad) {
		this.categoriaActividad = categoriaActividad;
	}

	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (partidaGenericaID != null ? partidaGenericaID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PartidaGenerica)) {
			return false;
		}
		PartidaGenerica other = (PartidaGenerica) object;
		if ((this.partidaGenericaID == null && other.partidaGenericaID != null)
				|| (this.partidaGenericaID != null && !this.partidaGenericaID
						.equals(other.partidaGenericaID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.PartidaGenerica[partidaGenericaID="
				+ partidaGenericaID + "]";
	}

}
