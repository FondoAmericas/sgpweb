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
@Table(name = "partida_especifica")
public class PartidaEspecifica implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Partida_Especifica_ID")
	private Integer partidaEspecificaID;

	@Column(name = "descripcion_partida_especifica")
	private String descripcionPartidaEspecifica;

	@Column(name = "estado_eliminado")
	private int estadoEliminado;

	@ManyToOne
	@JoinColumn(name = "Partida_Generica_ID", referencedColumnName = "Partida_Generica_ID")
	private PartidaGenerica partidaGenerica;

	public Integer getPartidaEspecificaID() {
		return partidaEspecificaID;
	}

	public void setPartidaEspecificaID(Integer partidaEspecificaID) {
		this.partidaEspecificaID = partidaEspecificaID;
	}

	public String getDescripcionPartidaEspecifica() {
		return descripcionPartidaEspecifica;
	}

	public void setDescripcionPartidaEspecifica(
			String descripcionPartidaEspecifica) {
		this.descripcionPartidaEspecifica = descripcionPartidaEspecifica;
	}

	public int getEstadoEliminado() {
		return estadoEliminado;
	}

	public void setEstadoEliminado(int estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	public PartidaGenerica getPartidaGenerica() {
		return partidaGenerica;
	}

	public void setPartidaGenerica(PartidaGenerica partidaGenerica) {
		this.partidaGenerica = partidaGenerica;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (partidaEspecificaID != null ? partidaEspecificaID.hashCode()
				: 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PartidaEspecifica)) {
			return false;
		}
		PartidaEspecifica other = (PartidaEspecifica) object;
		if ((this.partidaEspecificaID == null && other.partidaEspecificaID != null)
				|| (this.partidaEspecificaID != null && !this.partidaEspecificaID
						.equals(other.partidaEspecificaID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.PartidaEspecifica[partidaEspecificaID="
				+ partidaEspecificaID + "]";
	}

}
