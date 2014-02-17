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
@Table(name = "riesgo")
public class Riesgo implements Serializable {

	private static final long serialVersionUID = -2885481692113332658L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Riesgo_ID")
	private Integer riesgoID;

	@Lob
	@Column(name = "descripcion_riesgo")
	private String descripcionRiesgo;

	@Lob
	@Column(name = "mitigacion_riesgo")
	private String mitigacionRiesgo;

	@ManyToOne
	@JoinColumn(name = "Actividad_ID", referencedColumnName = "Actividad_ID")
	private Actividad actividad;

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (riesgoID != null ? riesgoID.hashCode() : 0);
		return hash;
	}

	public Integer getRiesgoID() {
		return riesgoID;
	}

	public void setRiesgoID(Integer riesgoID) {
		this.riesgoID = riesgoID;
	}

	public String getDescripcionRiesgo() {
		return descripcionRiesgo;
	}

	public void setDescripcionRiesgo(String descripcionRiesgo) {
		this.descripcionRiesgo = descripcionRiesgo;
	}

	public String getMitigacionRiesgo() {
		return mitigacionRiesgo;
	}

	public void setMitigacionRiesgo(String mitigacionRiesgo) {
		this.mitigacionRiesgo = mitigacionRiesgo;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Riesgo)) {
			return false;
		}
		Riesgo other = (Riesgo) object;
		if ((this.riesgoID == null && other.riesgoID != null)
				|| (this.riesgoID != null && !this.riesgoID
						.equals(other.riesgoID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.Riesgo[riesgoID=" + riesgoID
				+ "]";
	}

}
