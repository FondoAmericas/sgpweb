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
import javax.persistence.Transient;

@Entity
@Table(name = "indicador_resultado")
public class IndicadorResultado implements Serializable {

	private static final long serialVersionUID = 6960924694251545175L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Indicador_Resultado_ID")
	private Integer indicadorResultadoID;

	@Column(name = "fk_idtablaesp_tipo_indicador_resultado")
	private Integer fkIdtablaespTipoIndicadorResultado;
	
	@Transient
	private String tipoIndicadorResultadoString;
	
	@Column(name = "fk_idtablaesp_unidad_medida")
	private Integer fkIdtablaespUnidadMedida;

	@Transient
	private String unidadMedidaString;
	
	@Lob
	@Column(name = "definicion_indicador")
	private String definicionIndicador;
	 
	@Lob
	@Column(name = "medio_verificacion")
	private String medioVerificacion;

	@Lob
	@Column(name = "metodo_calculo")
	private String metodoCalculo;
	
	@Column(name = "situacion_actual")
	private Integer situacionActual;

	@Column(name = "situacion_final")
	private Integer situacionFinal;

	@Column(name = "logro_alcanzado")
	private Integer logroAlcanzado;

	@Lob	
	@Column(name = "observacion")
	private String observacion;

	@ManyToOne
	@JoinColumn(name = "Resultado_ID", referencedColumnName = "Resultado_ID")
	private Resultado resultado;

	@Column(name = "estado_eliminado")
	private Integer estadoEliminado;
	
	public Integer getIndicadorResultadoID() {
		return indicadorResultadoID;
	}

	public void setIndicadorResultadoID(Integer indicadorResultadoID) {
		this.indicadorResultadoID = indicadorResultadoID;
	}

	public Integer getFkIdtablaespTipoIndicadorResultado() {
		return fkIdtablaespTipoIndicadorResultado;
	}

	public void setFkIdtablaespTipoIndicadorResultado(
			Integer fkIdtablaespTipoIndicadorResultado) {
		this.fkIdtablaespTipoIndicadorResultado = fkIdtablaespTipoIndicadorResultado;
	}

	public String getDefinicionIndicador() {
		return definicionIndicador;
	}

	public void setDefinicionIndicador(String definicionIndicador) {
		this.definicionIndicador = definicionIndicador;
	}

	public Integer getFkIdtablaespUnidadMedida() {
		return fkIdtablaespUnidadMedida;
	}

	public void setFkIdtablaespUnidadMedida(Integer fkIdtablaespUnidadMedida) {
		this.fkIdtablaespUnidadMedida = fkIdtablaespUnidadMedida;
	}

	public String getMedioVerificacion() {
		return medioVerificacion;
	}

	public void setMedioVerificacion(String medioVerificacion) {
		this.medioVerificacion = medioVerificacion;
	}

	public void setMetodoCalculo(String metodoCalculo) {
		this.metodoCalculo = metodoCalculo;
	}

	public String getMetodoCalculo() {
		return metodoCalculo;
	}

	public Integer getSituacionActual() {
		return situacionActual;
	}

	public void setSituacionActual(Integer situacionActual) {
		this.situacionActual = situacionActual;
	}

	public Integer getSituacionFinal() {
		return situacionFinal;
	}

	public void setSituacionFinal(Integer situacionFinal) {
		this.situacionFinal = situacionFinal;
	}

	public Integer getLogroAlcanzado() {
		return logroAlcanzado;
	}

	public void setLogroAlcanzado(Integer logroAlcanzado) {
		this.logroAlcanzado = logroAlcanzado;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
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

	public String getTipoIndicadorResultadoString() {
		return tipoIndicadorResultadoString;
	}

	public void setTipoIndicadorResultadoString(String tipoIndicadorResultadoString) {
		this.tipoIndicadorResultadoString = tipoIndicadorResultadoString;
	}

	public String getUnidadMedidaString() {
		return unidadMedidaString;
	}

	public void setUnidadMedidaString(String unidadMedidaString) {
		this.unidadMedidaString = unidadMedidaString;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (indicadorResultadoID != null ? indicadorResultadoID.hashCode()
				: 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof IndicadorResultado)) {
			return false;
		}
		IndicadorResultado other = (IndicadorResultado) object;
		if ((this.indicadorResultadoID == null && other.indicadorResultadoID != null)
				|| (this.indicadorResultadoID != null && !this.indicadorResultadoID
						.equals(other.indicadorResultadoID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.com.fondam.sgp.core.domain.IndicadorResultado[indicadorResultadoID="
				+ indicadorResultadoID + "]";
	}

}
