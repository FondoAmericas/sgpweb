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
import javax.persistence.Transient;

@Entity
@Table(name = "fuente_financiadora")
public class FuenteFinanciadora implements Serializable {

	private static final long serialVersionUID = -5149407599344144518L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Fuente_Financiadora_ID")
	private Integer fuenteFinanciadoraID;

	@Column(name = "define_si_es_ejecutor")
	private Integer defineSiEsEjecutor;

	@Column(name = "fk_idtablaesp_tipo_fuente_financiadora")
	private Integer fkIdtablaespTipoFuenteFinanciadora;
	
	@Transient
	private String descripcionTipoFuenteFinanciadora;

	@Column(name = "monto_financiado")
	private Double montoFinanciado;

	@Column(name = "fk_idtablaesp_tipo_moneda")
	private Integer fkIdtablaespTipoMoneda;

	@Transient
	private String descripcionTipoMoneda;
	
	@ManyToOne
	@JoinColumn(name = "Institucion_ID", referencedColumnName = "Institucion_ID")
	private Institucion institucion;

	@ManyToOne
	@JoinColumn(name = "Dato_Proyecto_ID", referencedColumnName = "Dato_Proyecto_ID")
	private DatoProyecto datoProyecto;

	public Integer getFuenteFinanciadoraID() {
		return fuenteFinanciadoraID;
	}

	public void setFuenteFinanciadoraID(Integer fuenteFinanciadoraID) {
		this.fuenteFinanciadoraID = fuenteFinanciadoraID;
	}

	public Integer getDefineSiEsEjecutor() {
		return defineSiEsEjecutor;
	}

	public void setDefineSiEsEjecutor(Integer defineSiEsEjecutor) {
		this.defineSiEsEjecutor = defineSiEsEjecutor;
	}

	public Integer getFkIdtablaespTipoFuenteFinanciadora() {
		return fkIdtablaespTipoFuenteFinanciadora;
	}

	public void setFkIdtablaespTipoFuenteFinanciadora(
			Integer fkIdtablaespTipoFuenteFinanciadora) {
		this.fkIdtablaespTipoFuenteFinanciadora = fkIdtablaespTipoFuenteFinanciadora;
	}

	public Double getMontoFinanciado() {
		return montoFinanciado;
	}

	public void setMontoFinanciado(Double montoFinanciado) {
		this.montoFinanciado = montoFinanciado;
	}

	public String getDescripcionTipoFuenteFinanciadora() {
		return descripcionTipoFuenteFinanciadora;
	}

	public void setDescripcionTipoFuenteFinanciadora(
			String descripcionTipoFuenteFinanciadora) {
		this.descripcionTipoFuenteFinanciadora = descripcionTipoFuenteFinanciadora;
	}

	public String getDescripcionTipoMoneda() {
		return descripcionTipoMoneda;
	}

	public void setDescripcionTipoMoneda(String descripcionTipoMoneda) {
		this.descripcionTipoMoneda = descripcionTipoMoneda;
	}

	public Integer getFkIdtablaespTipoMoneda() {
		return fkIdtablaespTipoMoneda;
	}

	public void setFkIdtablaespTipoMoneda(Integer fkIdtablaespTipoMoneda) {
		this.fkIdtablaespTipoMoneda = fkIdtablaespTipoMoneda;
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
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
		hash += (fuenteFinanciadoraID != null ? fuenteFinanciadoraID.hashCode()
				: 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof FuenteFinanciadora)) {
			return false;
		}
		FuenteFinanciadora other = (FuenteFinanciadora) object;
		if ((this.fuenteFinanciadoraID == null && other.fuenteFinanciadoraID != null)
				|| (this.fuenteFinanciadoraID != null && !this.fuenteFinanciadoraID
						.equals(other.fuenteFinanciadoraID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.FuenteFinanciadora[fuenteFinanciadoraID="
				+ fuenteFinanciadoraID + "]";
	}

}
