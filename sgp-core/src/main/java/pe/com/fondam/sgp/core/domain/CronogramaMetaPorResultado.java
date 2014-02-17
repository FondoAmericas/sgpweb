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
@Table(name = "cronograma_meta_por_resultado")
public class CronogramaMetaPorResultado implements Serializable {

	private static final long serialVersionUID = 6813815183273720257L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Cronograma_Meta_Por_Resultado_ID")
	private Integer cronogramaMetaPorResultadoID;

	@Column(name = "avance_meta")
	private Integer avanceMeta;

	@Column(name = "periodo")
	private String periodo;

	@ManyToOne
	@JoinColumn(name = "Resultado_ID", referencedColumnName = "Resultado_ID")
	private Resultado resultado;

	@Column(name = "estado_eliminado")
	private Integer estadoEliminado;
	
	public Integer getCronogramaMetaPorResultadoID() {
		return cronogramaMetaPorResultadoID;
	}

	public void setCronogramaMetaPorResultadoID(
			Integer cronogramaMetaPorResultadoID) {
		this.cronogramaMetaPorResultadoID = cronogramaMetaPorResultadoID;
	}

	public Integer getAvanceMeta() {
		return avanceMeta;
	}

	public void setAvanceMeta(Integer avanceMeta) {
		this.avanceMeta = avanceMeta;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public void setEstadoEliminado(Integer estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	public Integer getEstadoEliminado() {
		return estadoEliminado;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cronogramaMetaPorResultadoID != null ? cronogramaMetaPorResultadoID
				.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CronogramaMetaPorResultado)) {
			return false;
		}
		CronogramaMetaPorResultado other = (CronogramaMetaPorResultado) object;
		if ((this.cronogramaMetaPorResultadoID == null && other.cronogramaMetaPorResultadoID != null)
				|| (this.cronogramaMetaPorResultadoID != null && !this.cronogramaMetaPorResultadoID
						.equals(other.cronogramaMetaPorResultadoID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.CronogramaMetaPorResultado[cronogramaMetaPorResultadoID="
				+ cronogramaMetaPorResultadoID + "]";
	}

}
